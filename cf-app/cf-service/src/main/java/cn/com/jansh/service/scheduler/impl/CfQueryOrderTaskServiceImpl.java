package cn.com.jansh.service.scheduler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.constant.BusiLogStatus;
import cn.com.jansh.constant.CallBackCode;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfBatchrechargeMapper;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.mapper.wsfdn.CfRechargeMapper;
import cn.com.jansh.service.component.OuFeiComponent;
import cn.com.jansh.service.component.WangSuFdnComponent;
import cn.com.jansh.service.scheduler.CfQueryOrderTaskService;

@Service
public class CfQueryOrderTaskServiceImpl implements CfQueryOrderTaskService {

	private static final Logger logger = LogManager.getLogger(CfQueryOrderTaskServiceImpl.class);
	
	/**
	 * 设置超时时间，超过此时间若返回状态还是不正常则标记为失败
	 */
	private static final int timeout = 2;
	
	/**
	 * 每小时36000000毫秒
	 */
	private static final int hourMilliSecond = 3600000;
	
	@Autowired
	private WangSuFdnComponent wangSuFdnComponent;
	
	@Autowired
	private  OuFeiComponent ouFeiComponent;
	
	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;
	
	/*@Autowired
	private CfRechargeMapper cfRechargeMapper;
	
	@Autowired
	private CfBatchrechargeMapper cfBatchrechargeMapper;*/
	
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	
	/**
	 * 定时更新订单状态（状态为）
	 */
	@Override
	public void queryOrder()  {
		List<String> li = new ArrayList<String>();
		li.add(BusiLogStatus.INPUT.value());
		li.add(BusiLogStatus.UNKNOW.value());
		li.add(BusiLogStatus.ACCEPT.value());
		List<CfCurrbusilogEntity> currbusilogList =  cfCurrbusilogMapper.selectByStatus(li);
		if(null != currbusilogList && currbusilogList.size() > 0){
			for (CfCurrbusilogEntity cfCurrbusilogEntity : currbusilogList) {
				String result = "";
				try {
					if(cfCurrbusilogEntity.getIpstype().equals(AppCommonsCode.IPSTYPE_Flow.value())){
						result = wangSuFdnComponent.queryOrder(cfCurrbusilogEntity.getCporderno(), cfCurrbusilogEntity.getSystransno());
						readWangSuResult(cfCurrbusilogEntity,result);
						logger.info("wangsufdn返回结果：{}", result);
					}else if(cfCurrbusilogEntity.getIpstype().equals(AppCommonsCode.IPSTYPE_BILL.value())){
						String status = ouFeiComponent.queryOrder(cfCurrbusilogEntity.getBizid());
						if(CallBackCode.OF_REFRESH_OK.value().equals(status)){
							readOufeiResult(cfCurrbusilogEntity,status);
							logger.info("ouFei返回结果：{}", result);
						}else if(CallBackCode.OF_REFRESH_HANDLE.value().equals(status)){
							//充值中，不做处理
						}else if(CallBackCode.OF_REFRESH_ERROR.value().equals(status)){
							ofOrderERROR(cfCurrbusilogEntity);
						}else if(CallBackCode.OF_REFRESH_NOORDER.value().equals(status)){
							Date date1 = DateUtil.parseDateTime2(cfCurrbusilogEntity.getCreatetime());
							Date date2 = DateUtil.parseDateTime2(DateUtil.getTimestamp());
							//查询重置不成功，若大于2小时则标记为失败
							if((date2.getTime() - date1.getTime())/hourMilliSecond>timeout){
								ofOrderERROR(cfCurrbusilogEntity);
							}	
						}
					}
				} catch (Exception e) {
					logger.error("定时更新订单状态异常{}",e);
				}
			}
		}
	}
	
	/**
	 * 根据返回结果更新表中数据(欧非)
	 * @param id
	 * @param result
	 */
	private void readOufeiResult(CfCurrbusilogEntity cfCurrbusilogEntity,String result){
		if (StringUtils.isNotBlank(result)) {
				String game_state = result;
				if (CallBackCode.OF_REFRESH_OK.value().equals(game_state)) {
					ofOrderDetailsOK(cfCurrbusilogEntity,game_state);
				}
		}
	}
	/*private Map<String,String> readStringXml(String xml) {
		Map<String ,String > map = new HashMap<String,String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            @SuppressWarnings("unchecked")
			Iterator<Element> iter = rootElt.elementIterator("orderinfo"); // 获取根节点下的子节点head
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                
                map.put("err_msg", recordEle.elementTextTrim("err_msg"));
                map.put("retcode", recordEle.elementTextTrim("retcode"));
                map.put("orderid", recordEle.elementTextTrim("orderid"));
                map.put("cardid", recordEle.elementTextTrim("cardid"));
                map.put("cardnum", recordEle.elementTextTrim("cardnum"));
                
                map.put("ordercash", recordEle.elementTextTrim("ordercash"));
                map.put("cardname", recordEle.elementTextTrim("cardname"));
                map.put("sporder_id", recordEle.elementTextTrim("sporder_id"));
                map.put("game_state", recordEle.elementTextTrim("game_state"));
            }
        } catch (DocumentException e) {
            logger.error("解析欧飞查询订单状态xml异常{}",e);
        }
        return map;
    }*/
	
	/**
	 * 根据返回结果更新表中数据(网宿)
	 * @param id
	 * @param result
	 */
	private void readWangSuResult(CfCurrbusilogEntity cfCurrbusilogEntity,String result){
		// 解析返回结果
		if (StringUtils.isNotBlank(result)) {
			Map<String, Object> resmap = null;
			String responseCode="";
			String responseMsg= "";
			
			String transNo = "";
			String orderId= "";
			String resultCode = "";
			//String resultMsg = "";
			try {
				resmap = JsonUtil.readMapObject(result);
			} catch (Exception e) {
				logger.error("解析返回结果异常：{}", e);
				return ;
			}
			if (resmap != null) {
				responseCode = (String) resmap.get("responseCode");
				responseMsg = (String) resmap.get("responseMsg");
				
				logger.info("响应码：{}", responseCode);
				logger.info("描述：{}", responseMsg);
				if (CallBackCode.WS_REFRESH_REQUEST_OK.value().equals(responseCode)) {
					// 请求接收成功
					Map<String, String> responseData = (Map<String, String>) resmap.get("responseData");
					if (responseData != null) {
						orderId = responseData.get("orderId");
						transNo = responseData.get("transNo");
						resultCode = responseData.get("resultCode");
						//resultMsg = responseData.get("resultMsg");
						logger.info("订购流水号：{},客户订单编号：{},订购结果代码：{}", orderId,transNo,resultCode);
					}
					if(CallBackCode.WS_REFRESH_OK.value().equals(resultCode)){
						
						// 更新流水表(成功)
						wsOrderOk(transNo,resultCode,cfCurrbusilogEntity);
						
					}else{
						try {
							wsOrderERROR(transNo,resultCode,cfCurrbusilogEntity);
						} catch (Exception e) {
							logger.error("时间转换异常{}",e);
						}
					}
				}else if(CallBackCode.WS_REFRESH_HANDLE.value().equals(responseCode)){
					//订单处理中，不做处理
				}else if(CallBackCode.WS_REFRESH_NOORDER.value().equals(responseCode)){
					//订单不存在（网宿）
					try {
						//查询重置不成功，若大于2小时则标记为失败
						Date date1 = DateUtil.parseDateTime2(cfCurrbusilogEntity.getCreatetime());
						Date date2 = DateUtil.parseDateTime2(DateUtil.getTimestamp());
						if((date2.getTime() - date1.getTime())/hourMilliSecond>timeout){
							wsOrderERROR(transNo,responseCode,cfCurrbusilogEntity);
						}
					} catch (Exception e) {
						logger.error("时间转换异常{}",e);
					}
				}
			} 
		}
	}
	
	/**
	 * 欧飞查询充值成功
	 * @param cfCurrbusilogEntity
	 * @param resmap
	 */
	private void ofOrderDetailsOK(CfCurrbusilogEntity cfCurrbusilogEntity,String resmap){
		
		CfCurrbusilogEntity cfCurrbusilog = new CfCurrbusilogEntity();
		cfCurrbusilog.setBizid(cfCurrbusilogEntity.getBizid());
		//cfCurrbusilog.setResponsecode(resmap);
		cfCurrbusilog.setStatus(BusiLogStatus.SUCCEED.value());
		cfCurrbusilogMapper.update(cfCurrbusilog);
		
/*		CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfRechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());
		cfRechargeMapper.updateStatusByBizid(cfRechargeEntity);
		
		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
		cfBatchrechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfBatchrechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());
		cfBatchrechargeMapper.updateBatchByBizid(cfBatchrechargeEntity);*/
	}
	
	/**
	 * 欧飞查询充值失败
	 * @param cfCurrbusilogEntity
	 */
	private void ofOrderERROR(CfCurrbusilogEntity cfCurrbusilogEntity){
		
		
		CfCurrbusilogEntity cfCurrbusilog = new CfCurrbusilogEntity();
		cfCurrbusilog.setBizid(cfCurrbusilogEntity.getBizid());
		cfCurrbusilog.setUpdatetime(DateUtil.getTimestamp());
		cfCurrbusilog.setStatus(BusiLogStatus.FAIL.value());
		if(cfCurrbusilogMapper.update(cfCurrbusilog)<=0){
			return ;
		}
		//录入和已受理的若错误减少预算
		minusCumulative(cfCurrbusilogEntity);
		
		/*CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfRechargeEntity.setStatus(BusiLogStatus.FAIL.value());
		cfRechargeMapper.updateStatusByBizid(cfRechargeEntity);
		
		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
		cfBatchrechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfBatchrechargeEntity.setStatus(BusiLogStatus.FAIL.value());
		cfBatchrechargeMapper.updateBatchByBizid(cfBatchrechargeEntity);*/
	}
	/**
	 * 网宿更新流水-成功
	 * @param transNo
	 * @param responseCode
	 * @param cfCurrbusilogEntity
	 */
	private void wsOrderOk(String transNo,String responseCode, CfCurrbusilogEntity cfCurrbusilogEntity){
		
		CfCurrbusilogEntity cfCurrbusilog = new CfCurrbusilogEntity();
		cfCurrbusilog.setBizid(transNo);
		cfCurrbusilog.setResponsecode(BusiLogStatus.PLATFORM_WS.value()+responseCode);
		cfCurrbusilog.setUpdatetime(DateUtil.getTimestamp());
		cfCurrbusilog.setStatus(BusiLogStatus.SUCCEED.value());
		cfCurrbusilogMapper.update(cfCurrbusilog);
		
		/*CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfRechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());
		cfRechargeMapper.updateStatusByBizid(cfRechargeEntity);
		
		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
		cfBatchrechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfBatchrechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());
		cfBatchrechargeMapper.updateBatchByBizid(cfBatchrechargeEntity);*/
		
	}
	
	/**
	 * 网宿更新流水-错误
	 * @param transNo
	 * @param responseCode
	 * @param cfCurrbusilogEntity
	 */
	private void wsOrderERROR(String transNo,String responseCode, CfCurrbusilogEntity cfCurrbusilogEntity){
		CfCurrbusilogEntity cfCurrbusilog = new CfCurrbusilogEntity();
		cfCurrbusilog.setBizid(cfCurrbusilogEntity.getBizid());
		cfCurrbusilog.setResponsecode(BusiLogStatus.PLATFORM_WS.value()+responseCode);
		cfCurrbusilog.setUpdatetime(DateUtil.getTimestamp());
		cfCurrbusilog.setStatus(BusiLogStatus.FAIL.value());
		if(cfCurrbusilogMapper.update(cfCurrbusilog)<=0){
			return ;
		}
		//录入和已受理的若错误减少预算
		minusCumulative(cfCurrbusilogEntity);
		
		/*CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfRechargeEntity.setStatus(BusiLogStatus.FAIL.value());
		cfRechargeMapper.updateStatusByBizid(cfRechargeEntity);
		
		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
		cfBatchrechargeEntity.setBizid(cfCurrbusilogEntity.getBizid());
		cfBatchrechargeEntity.setStatus(BusiLogStatus.FAIL.value());
		cfBatchrechargeMapper.updateBatchByBizid(cfBatchrechargeEntity);*/
	}
	
	/**
	 * 减少消费
	 * @param price
	 * @param acid
	 */
	private void minusCumulative(CfCurrbusilogEntity cfCurrbusilogEntity){
		cfAccessclientMapper.minusCumulative(cfCurrbusilogEntity.getApprice(),cfCurrbusilogEntity.getApno());
	}
}
