package cn.com.jansh.service.scheduler.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.constant.BusiLogStatus;
import cn.com.jansh.constant.CallBackCode;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfAccesspriceEntity;
import cn.com.jansh.entity.wsfdn.CfBatchinfoEntity;
import cn.com.jansh.entity.wsfdn.CfBatchrechargeEntity;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfBatchinfoMapper;
import cn.com.jansh.mapper.wsfdn.CfBatchrechargeMapper;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.service.component.OuFeiComponent;
import cn.com.jansh.service.component.WangSuFdnComponent;
import cn.com.jansh.service.scheduler.CfDcBatchRechargeTaskService;
import cn.com.jansh.service.scheduler.QCellCoreService;

@Service
public class CfDcBatchRechargeTaskServiceImpl implements CfDcBatchRechargeTaskService{
	
	private static final Logger logger = LogManager.getLogger(CfDcBatchRechargeTaskServiceImpl.class);
	
	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;
	
	@Autowired
	private WangSuFdnComponent wangSuFdnComponent;
	
	@Autowired
	private  OuFeiComponent ouFeiComponent;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	
	@Autowired
	private CfBatchinfoMapper cfBatchinfoMapper;
	
	@Autowired
	private CfBatchrechargeMapper cfBatchrechargeMapper;
	
	@Autowired
	private QCellCoreService qCellCoreService;
	
	
	@Override
	public void order() {
		
		List<CfBatchinfoEntity> cfBatchinfoli= cfBatchinfoMapper.queryCfBatchinfo(AppCommonsCode.RECHARGE_22.value());
		if(null != cfBatchinfoli && cfBatchinfoli.size()>0){
			for(CfBatchinfoEntity cfBatchinfoEntity: cfBatchinfoli){
				List<String> li = new ArrayList<String>();
				li.add(cfBatchinfoEntity.getBatchid());
			
			
				List<CfBatchrechargeEntity> Batchrechargeli = cfBatchrechargeMapper.queryBatchrechargeByInfo(li);
				Map<String, Object> params = new HashMap<String, Object>(2);
				params.put("batchid", cfBatchinfoEntity.getBatchid());
				params.put("status", AppCommonsCode.RECHARGE_33.value());
				if(cfBatchinfoMapper.updataBatchCfBatchinfo(params)<=0){
					continue;
				}
			
				if(null != Batchrechargeli && Batchrechargeli.size() > 0){
					for (CfBatchrechargeEntity cfBatchrecharge : Batchrechargeli) {
						DefaultTransactionDefinition def = new DefaultTransactionDefinition();
						def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
						TransactionStatus status = transactionManager.getTransaction(def);
						//充值
						orderaction(cfBatchinfoEntity,cfBatchrecharge);
						transactionManager.commit(status);
					}
				}
			}
		}
	}
	
	private void orderaction(CfBatchinfoEntity cfBatchinfoEntity,CfBatchrechargeEntity cfBatchrecharge){
		
		
		//查不到归属地直接返回
		Map<String,String> map = qCellCoreService.resultQCellCore(cfBatchrecharge.getPhone());
		
		String province = map.get("province");
		
		/*生成流水号*/
		String bizid=IDUtils.getTimeRandon();
		cfBatchrecharge.setBizid(bizid);
		
		CfAccesspriceEntity accessprice = null;
		CfSupplierpriceEntity supplierprice= null;
		
		if(StringUtils.isBlank(map.get("province"))){
			//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.STATUS_PROVINCE_NO.value());
			rechargeToCurrbusilog(province,cfBatchrecharge,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.STATUS_PROVINCE_NO.value(),BusiLogStatus.FAIL.value());
			return ;
		}
		
		/** 1、更新状态*/
		if(updateStatus33(cfBatchrecharge)<=0){
			return ;
		}
		
		/** 2、接入者状态为关闭状态*/
		CfAccessclientEntity cfAccessclientEntity = cfAccessclientMapper.selectByid(cfBatchinfoEntity.getApno());
		cfAccessclientEntity.getStatus();
		if(cfAccessclientEntity != null){
			if(cfAccessclientEntity.getStatus().equals(AppCommonsCode.STATUS_NO.value())){
				//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.STATUS_ACCESS_NO.value());
				rechargeToCurrbusilog(province,cfBatchrecharge,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.STATUS_ACCESS_NO.value(),BusiLogStatus.FAIL.value());
				return ;
			}
		}
		
		/** 2-1、获取接入者报价*/
		accessprice = cfBatchrechargeMapper.queryAPInfoByBatchrecharge(cfBatchrecharge.getId(),province,AppCommonsCode.STATUS_OK.value());
		/** 2-2、接入者报价为null  */
		if(null == accessprice){
			accessprice = cfBatchrechargeMapper.queryAPInfoByBatchrecharge(cfBatchrecharge.getId(),"qg",AppCommonsCode.STATUS_OK.value());
			if(null == accessprice){
				//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.NO_ACCESSPRICE.value());
				rechargeToCurrbusilog(province,cfBatchrecharge,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.NO_ACCESSPRICE.value(),BusiLogStatus.FAIL.value());
				return ;
			}
		}
		/** 3、获取供应商报价*/
		supplierprice = cfBatchrechargeMapper.querSPInfoByBatchrecharge(province,cfBatchrecharge.getId(),AppCommonsCode.STATUS_OK.value());
		/** 3-1、判断全国的套餐还是地方的套餐价格低*/
		CfSupplierpriceEntity supplierprice1=null;
		supplierprice1= cfBatchrechargeMapper.querSPInfoByBatchrecharge("qg",cfBatchrecharge.getId(),AppCommonsCode.STATUS_OK.value());
		
		if(supplierprice == null &&supplierprice1 != null){
			supplierprice = supplierprice1;
		}else if(supplierprice1 != null&&supplierprice!=null){
			if(new BigDecimal(supplierprice1.getPrice()).compareTo(new BigDecimal(supplierprice.getPrice()))<= 0 ){
				supplierprice = supplierprice1;
			}
		}
		
		/** 4-2、供应商报价为空则结束*/
		if(null == supplierprice){
			//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.NO_SUPPLIERPRICE.value());
			rechargeToCurrbusilog(province,cfBatchrecharge,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.NO_SUPPLIERPRICE.value(),BusiLogStatus.FAIL.value());
			return ;
		}
		
		/** 4、查询接入者是否超出预算*/
		if(!checkArrears(supplierprice.getPrice(),accessprice.getAcid())){
			//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.NO_ARREARS.value());
			rechargeToCurrbusilog(province,cfBatchrecharge,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.NO_ARREARS.value(),BusiLogStatus.FAIL.value());
			return ;
		}
		
		/**
		 *  5、计算累计消费
		 */
		if(countCumulative(supplierprice.getPrice(),accessprice.getAcid())<=0){
			//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.NO_ARREARS.value());
			//rechargeToCurrbusilog(province,cfBatchrecharge,accessprice,supplierprice,null);
			return ;
		}
		
		/** 6、存流水表*/
		rechargeToCurrbusilog(province,cfBatchrecharge,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.STATUS_OK.value(),BusiLogStatus.INPUT.value());
		
		/** 7、充值:流量充值（网宿），话费充值（欧飞）*/
		String result = "";
		try {
			if(cfBatchrecharge.getIsptype().equals(AppCommonsCode.IPSTYPE_Flow.value())){
				result = wangSuFdnComponent.order(cfBatchrecharge.getPhone(), bizid, supplierprice.getCpordernos());
				readWangSuResult(accessprice.getPrice(),accessprice.getAcid(),cfBatchrecharge,bizid,result);
				logger.info("wangsufdn返回结果：{}", result);
			}else{
				result = ouFeiComponent.order(cfBatchrecharge.getFacevalue(), bizid, cfBatchrecharge.getPhone());
				readOuFeiResult(accessprice.getPrice(),accessprice.getAcid(),cfBatchrecharge,bizid,result);
				logger.info("ouFeifdn返回结果：{}", result);
			}
		}
		catch (Exception e) {
			logger.error("通讯异常：{}", e);
			//不成功需减少消费
			//minusCumulative(accessprice.getPrice(),accessprice.getAcid());
			//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.UNKNOW.value());
			CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
			cfCurrbusilogEntity.setBizid(bizid);
			cfCurrbusilogEntity.setStatus(BusiLogStatus.UNKNOW.value());
			cfCurrbusilogMapper.update(cfCurrbusilogEntity);
		}
	}
	/**
	 * 根据返回结果更新表中数据(网宿)
	 * @param id
	 * @param result
	 */
	private void readWangSuResult(String apprice,String acid,CfBatchrechargeEntity cfBatchrecharge, String bizid , String result){
		// 解析返回结果
		if (StringUtils.isNotBlank(result)) {
			Map<String, Object> resmap = null;
			String responseCode="";
			String responseMsg= "";
			String transNo = "";
			String orderId= "";
			try {
				resmap = JsonUtil.readMapObject(result);
			} catch (Exception e) {
				logger.error("解析返回结果异常：{}", e);
			}
			if (resmap != null) {
				responseCode = (String) resmap.get("responseCode");
				responseMsg = (String) resmap.get("responseMsg");
				
				logger.info("响应码：{}", responseCode);
				logger.info("描述：{}", responseMsg);
				if (CallBackCode.WS_STRAIGHT_OK.value().equals(responseCode)) {
					// 请求接收成功
					@SuppressWarnings("unchecked")
					Map<String, String> responseData = (Map<String, String>) resmap.get("responseData");
					if (responseData != null) {
						orderId = responseData.get("orderId");
						transNo = responseData.get("transNo");
						logger.info("网宿订单id：{}", orderId);
						logger.info("平台订单id：{}", transNo);
					}
					//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.ACCEPT.value());
					// 更新流水表(成功)
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(BusiLogStatus.PLATFORM_WS.value()+responseCode);
					cfCurrbusilogEntity.setSystransno(transNo);
					cfCurrbusilogEntity.setOrderid(orderId);
					cfCurrbusilogEntity.setStatus(BusiLogStatus.ACCEPT.value());
					cfCurrbusilogMapper.update(cfCurrbusilogEntity);
				}else{
					//不成功需减少消费
					minusCumulative(apprice,acid);
					//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.FAIL.value());
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(BusiLogStatus.PLATFORM_WS.value()+responseCode);
					cfCurrbusilogEntity.setSystransno(transNo);
					cfCurrbusilogEntity.setStatus(BusiLogStatus.FAIL.value());
					cfCurrbusilogMapper.update(cfCurrbusilogEntity);
				}
			} 
		}
	}
	
	/**
	 * 根据返回结果更新表中数据(欧飞)
	 * @param id
	 * @param result
	 */
	private void readOuFeiResult(String apprice,String acid,CfBatchrechargeEntity cfBatchrecharge ,String bizid , String result){
		// 解析返回结果
		if (StringUtils.isNotBlank(result)) {
			Map<String, String> resmap = null;
			try {
				resmap = readStringXml(result);
			} catch (Exception e) {
				logger.error("解析返回结果异常：{}", e);
			}
			if (resmap != null) {
/*				String err_msg = (String) resmap.get("err_msg");*/
				String retcode = (String) resmap.get("retcode");
				String orderid = (String) resmap.get("orderid");
/*				String cardid = (String) resmap.get("cardid");
				String cardnum = (String) resmap.get("cardnum");
				String ordercash = (String) resmap.get("ordercash");
				String cardname = (String) resmap.get("cardname");*/
				String sporder_id = (String) resmap.get("sporder_id");
/*				String game_userid = (String) resmap.get("game_userid");
				String game_state = (String) resmap.get("game_state");*/
				
				if (CallBackCode.OF_STRAIGHT_OK.value().equals(retcode)) {
					//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.ACCEPT.value());
					// 更新流水表(成功)
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(BusiLogStatus.PLATFORM_OF.value()+retcode);
					cfCurrbusilogEntity.setSystransno(sporder_id);
					cfCurrbusilogEntity.setOrderid(orderid);
					cfCurrbusilogEntity.setStatus(BusiLogStatus.ACCEPT.value());
					cfCurrbusilogMapper.update(cfCurrbusilogEntity);
				}else{
					//不成功需较少消费
					minusCumulative(apprice,acid);
					//updateStatus(cfBatchrecharge.getId(),BusiLogStatus.FAIL.value());
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(BusiLogStatus.PLATFORM_OF.value()+retcode);
					cfCurrbusilogEntity.setSystransno(sporder_id);
					cfCurrbusilogEntity.setStatus(BusiLogStatus.FAIL.value());
					cfCurrbusilogMapper.update(cfCurrbusilogEntity);
				}
			}
		}
	}
	private Map<String,String> readStringXml(String xml) {
		Map<String ,String > map = new HashMap<String,String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            @SuppressWarnings("unchecked")
			Iterator<Element> iter = rootElt.elementIterator("orderinfo"); // 获取根节点下的子节点head
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                
                String err_msg = recordEle.elementTextTrim("err_msg"); // 拿到head节点下的子节点title值
                String retcode = recordEle.elementTextTrim("retcode"); // 获取子节点head下的子节点script
                String orderid = recordEle.elementTextTrim("orderid");
                String cardid =  recordEle.elementTextTrim("cardid");
                String cardnum = recordEle.elementTextTrim("cardnum");
                
                String ordercash = recordEle.elementTextTrim("ordercash");
                String cardname = recordEle.elementTextTrim("cardname"); 
                String sporder_id = recordEle.elementTextTrim("sporder_id"); 
                String game_userid = recordEle.elementTextTrim("game_userid"); 
                String game_state = recordEle.elementTextTrim("game_state"); 
                
                map.put("err_msg", err_msg);
                map.put("retcode", retcode);
                map.put("orderid", orderid);
                map.put("cardid", cardid);
                map.put("cardnum", cardnum);
                
                map.put("ordercash", ordercash);
                map.put("cardname", cardname);
                map.put("sporder_id", sporder_id);
                map.put("game_userid", game_userid);
                map.put("game_state", game_state);
                
                logger.info("map:{}" , map.toString());
            }
        } catch (DocumentException e) {
        	logger.error("解析欧飞充值订单状态xml异常{}",e);
        }
        return map;
    }
	
	/**
	 * 查询是否欠费
	 * @param cfRechargeEntity
	 * @return
	 */
	private boolean checkArrears(String apprice,String acid){
		CfAccessclientEntity ac = cfAccessclientMapper.selectByid(acid);
		if(new BigDecimal(ac.getBudget()).compareTo(new BigDecimal(ac.getCumulative()).add(new BigDecimal(apprice))) >= 0 ){
			return true;
		}
		return false;
	}
	/**
	 * 更新状态(定时任务抓取标记)
	 * @param acid
	 * @param bizid
	 */
	private int updateStatus33(CfBatchrechargeEntity cfBatch){
		CfBatchrechargeEntity cfBatchrecharge = new CfBatchrechargeEntity();
		cfBatchrecharge.setId(cfBatch.getId());
		cfBatchrecharge.setStatus(AppCommonsCode.RECHARGE_33.value());
		cfBatchrecharge.setBizid(cfBatch.getBizid());
		return cfBatchrechargeMapper.updateBatch(cfBatchrecharge);
	}
	
	/**
	 * 修改状态
	 * @param acid
	 
	private void updateStatus1(String id,String status){
		CfBatchrechargeEntity cfBatchrecharge = new CfBatchrechargeEntity();
		cfBatchrecharge.setId(id);
		cfBatchrecharge.setStatus(status);
		cfBatchrechargeMapper.updateBatch(cfBatchrecharge);
	}
	*/
	/**
	 * 存流水表(单独事务)
	 * @param cfRechargeEntity
	 * @param c
	 * @param g1
	 */
	private void rechargeToCurrbusilog(String province , CfBatchrechargeEntity cfRechargeEntity, CfAccesspriceEntity accessprice,CfSupplierpriceEntity supplierprice,String responsecode,String status){
		
		
		CfCurrbusilogEntity currbusilog = new CfCurrbusilogEntity();
		
		currbusilog.setStatus(BusiLogStatus.INPUT.value());
		
		currbusilog.setBizid(cfRechargeEntity.getBizid());
		currbusilog.setPhone(cfRechargeEntity.getPhone());
		currbusilog.setIspno(cfRechargeEntity.getIspno());
		currbusilog.setIpstype(cfRechargeEntity.getIsptype());
		currbusilog.setProvince(province);
		currbusilog.setFacevalue(cfRechargeEntity.getFacevalue());
		currbusilog.setSystransno(cfRechargeEntity.getBizid());
		
		currbusilog.setOrderid("");
		if(responsecode == null){
			currbusilog.setResponsecode("");
		}else{
			currbusilog.setResponsecode(responsecode);
		}
		
		if(null != supplierprice){
			currbusilog.setCporderno(supplierprice.getCpordernos());
			currbusilog.setSpprice(supplierprice.getPrice());
			currbusilog.setSpno(supplierprice.getSid());
		}else{
			currbusilog.setCporderno("");
			currbusilog.setSpprice("");
			currbusilog.setSpno("");
		}
		
		if(null != accessprice){
			currbusilog.setApprice(accessprice.getPrice());
			currbusilog.setApno(accessprice.getAcid());
			
		}else{
			currbusilog.setApprice("");
			currbusilog.setApno("");
		}
		
		if(status != null){
			currbusilog.setStatus(status);
		}
		
		currbusilog.setCreatetime(DateUtil.getTimestamp());
		currbusilog.setUpdatetime(DateUtil.getTimestamp());
		
		cfCurrbusilogMapper.insert(currbusilog);
	}
	
	/**
	 * 累计消费
	 * @param price
	 * @param acid
	 */
	private int countCumulative(String price,String acid){
		return cfAccessclientMapper.countCumulative(price, acid);
	}
	
	/**
	 * 减少消费
	 * @param price
	 * @param acid
	 */
	private void minusCumulative(String price,String acid){
		cfAccessclientMapper.minusCumulative(price, acid);
	}

}
