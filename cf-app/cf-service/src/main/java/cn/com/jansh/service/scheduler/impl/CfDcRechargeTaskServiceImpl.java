package cn.com.jansh.service.scheduler.impl;

import java.math.BigDecimal;
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
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfRechargeEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfAccesspriceMapper;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.mapper.wsfdn.CfRechargeMapper;
import cn.com.jansh.mapper.wsfdn.CfSupplierpriceMapper;
import cn.com.jansh.service.component.OuFeiComponent;
import cn.com.jansh.service.component.WangSuFdnComponent;
import cn.com.jansh.service.scheduler.CfDcRechargeTaskService;

@Service
public class CfDcRechargeTaskServiceImpl implements CfDcRechargeTaskService {
	
	private static final Logger logger = LogManager.getLogger(CfDcRechargeTaskServiceImpl.class);
	
	@Autowired
	private CfAccesspriceMapper cfAccesspriceMapper;
	
	@Autowired
	private CfRechargeMapper cfRechargeMapper;
	
	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;
	
	@Autowired
	private CfSupplierpriceMapper cfSupplierpriceMapper;
	
	@Autowired
	private WangSuFdnComponent wangSuFdnComponent;
	
	@Autowired
	private  OuFeiComponent ouFeiComponent;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	
	@Override
	public void order() {
		List<CfRechargeEntity> rechargelist = cfRechargeMapper.selectByStatus(AppCommonsCode.RECHARGE_30.value());
		if(null != rechargelist && rechargelist.size() > 0){
			for (CfRechargeEntity cfRechargeEntity : rechargelist) {
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				TransactionStatus status = transactionManager.getTransaction(def);
				//充值
				orderaction(cfRechargeEntity);
				transactionManager.commit(status);
			}
		}
	}

	public void orderaction(CfRechargeEntity cfRechargeEntity){
		
		/*生成流水号*/
		String bizid=IDUtils.getTimeRandon();
		cfRechargeEntity.setBizid(bizid);
		
		CfAccesspriceEntity accessprice = null;
		CfSupplierpriceEntity supplierprice=null;
		CfSupplierpriceEntity supplierprice1=null;
		
		/** 1、更新状态*/
		if(updateStatus33(cfRechargeEntity)<=0){
			return ;
		}
		
		/** 2、接入者状态为关闭状态*/
		CfAccessclientEntity cfAccessclientEntity = cfAccessclientMapper.selectByid(cfRechargeEntity.getAcid());
		cfAccessclientEntity.getStatus();
		if(cfAccessclientEntity != null){
			if(!cfAccessclientEntity.getStatus().equals(AppCommonsCode.STATUS_OK.value())){
				//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.STATUS_ACCESS_NO.value());
				rechargeToCurrbusilog(cfRechargeEntity,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.STATUS_ACCESS_NO.value(),BusiLogStatus.FAIL.value());
				return ;
			}
		}
		
		/** 2-1、根据条件查询是否有接入者报价  */
		accessprice = checkAccessprice(cfRechargeEntity);
		
		/** 2-2、接入者报价为null则查询全国的，若都为空则返回*/
		if(null == accessprice){
			String pro = cfRechargeEntity.getProvince();
			cfRechargeEntity.setProvince("qg");
			accessprice = checkAccessprice(cfRechargeEntity);
			cfRechargeEntity.setProvince(pro);
			if(null == accessprice){
				//接入者报价为null
				//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.NO_ACCESSPRICE.value());
				rechargeToCurrbusilog(cfRechargeEntity,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.NO_ACCESSPRICE.value(),BusiLogStatus.FAIL.value());
				return ;
			}
		}
		
		/** 3、根据条件查询供应商报价*/
		supplierprice =checkSupplierprice(cfRechargeEntity);
		
		/** 3-1、判断全国的套餐还是地方的套餐价格低*/
		cfRechargeEntity.setProvince("qg");
		supplierprice1 = checkSupplierprice(cfRechargeEntity);
		cfRechargeEntity.setProvince(accessprice.getProvince());
		
		if(supplierprice == null && supplierprice1!=null){
			supplierprice = supplierprice1;
			cfRechargeEntity.setProvince("qg");
		}else if(supplierprice1 != null&&supplierprice!=null){
			if(new BigDecimal(supplierprice1.getPrice()).compareTo(new BigDecimal(supplierprice.getPrice()))<= 0 ){
				supplierprice = supplierprice1;
				cfRechargeEntity.setProvince("qg");
			}
			
		}
		
		/** 3-2、供应商报价为空则结束*/
		if(null == supplierprice){
			//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.NO_SUPPLIERPRICE.value());
			rechargeToCurrbusilog(cfRechargeEntity,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.NO_SUPPLIERPRICE.value(),BusiLogStatus.FAIL.value());
			return ;
		}
		
		/** 4、查询接入者是否超出预算*/
		if(!checkArrears(cfRechargeEntity,accessprice.getPrice())){
			//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.NO_ARREARS.value());
			rechargeToCurrbusilog(cfRechargeEntity,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.NO_ARREARS.value(),BusiLogStatus.FAIL.value());
			return ;
		}
		
		/**
		 *  5、计算累计消费
		 */
		if(countCumulative(accessprice.getPrice(),cfRechargeEntity.getAcid())<=0){
			//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.NO_ARREARS.value());
			//rechargeToCurrbusilog(cfRechargeEntity,accessprice,supplierprice,BusiLogStatus.NO_ARREARS.value(),BusiLogStatus.FAIL.value());
			return ;
		}
		
		/** 6、存流水表*/
		rechargeToCurrbusilog(cfRechargeEntity,accessprice,supplierprice,BusiLogStatus.PLATFORM_CF.value()+BusiLogStatus.STATUS_OK.value(),BusiLogStatus.INPUT.value());
		
		/** 7、充值:流量充值（网宿），话费充值（欧飞）*/
		String result = "";
		try {
			if(cfRechargeEntity.getIsptype().equals(AppCommonsCode.IPSTYPE_Flow.value())){
				result = wangSuFdnComponent.order(cfRechargeEntity.getPhone(), bizid, supplierprice.getCpordernos());
				readWangSuResult(accessprice.getPrice(),cfRechargeEntity,bizid,result);
				logger.info("wangsufdn返回结果：{}", result);
			}else{
				result = ouFeiComponent.order(supplierprice.getSize(), bizid, cfRechargeEntity.getPhone());
				readOuFeiResult(accessprice.getPrice(),cfRechargeEntity,bizid,result);
				logger.info("ouFeifdn返回结果：{}", result);
			}
		}
		catch (Exception e) {
			logger.error("通讯异常：{}", e);
			//不成功需减少消费
			//minusCumulative(accessprice.getPrice(),cfRechargeEntity.getAcid());
			//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.UNKNOW.value());
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
	private void readWangSuResult(String price,CfRechargeEntity cfRechargeEntity ,String bizid , String result){
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
					//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.ACCEPT.value());
					// 更新流水表(成功)
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(responseCode);
					cfCurrbusilogEntity.setSystransno(transNo);
					cfCurrbusilogEntity.setOrderid(orderId);
					cfCurrbusilogEntity.setStatus(BusiLogStatus.ACCEPT.value());
					cfCurrbusilogMapper.update(cfCurrbusilogEntity);
				}else{
					//不成功需减少消费
					minusCumulative(price,cfRechargeEntity.getAcid());
					//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.FAIL.value());
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(responseCode);
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
	private void readOuFeiResult(String price,CfRechargeEntity cfRechargeEntity,String bizid , String result){
		// 解析返回结果
		if (StringUtils.isNotBlank(result)) {
			Map<String, String> resmap = null;
			try {
				resmap = readStringXml(result);
			} catch (Exception e) {
				logger.error("解析返回结果异常：{}", e);
				return;
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
					//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.ACCEPT.value());
					// 更新流水表(成功)
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(retcode);
					cfCurrbusilogEntity.setSystransno(sporder_id);
					cfCurrbusilogEntity.setOrderid(orderid);
					cfCurrbusilogEntity.setStatus(BusiLogStatus.ACCEPT.value());
					cfCurrbusilogMapper.update(cfCurrbusilogEntity);
				}else{
					//不成功需较少消费
					minusCumulative(price,cfRechargeEntity.getAcid());
					//updateStatus(cfRechargeEntity.getOrderid(),BusiLogStatus.FAIL.value());
					CfCurrbusilogEntity cfCurrbusilogEntity = new CfCurrbusilogEntity();
					cfCurrbusilogEntity.setBizid(bizid);
					cfCurrbusilogEntity.setResponsecode(retcode);
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
	 * 校验接入者报价为空
	 * @param cfRechargeEntity
	 * @return
	 */
	private CfAccesspriceEntity checkAccessprice(CfRechargeEntity cfRechargeEntity){
		/** 根据条件查询有没有配置接入者报价  */
		CfAccesspriceEntity cfAccesspriceEntity = new CfAccesspriceEntity();
		cfAccesspriceEntity.setAcid(cfRechargeEntity.getAcid());
		cfAccesspriceEntity.setFacevalue(cfRechargeEntity.getFacevalue());
		cfAccesspriceEntity.setIpstype(cfRechargeEntity.getIsptype());
		cfAccesspriceEntity.setIspno(cfRechargeEntity.getIspno());
		cfAccesspriceEntity.setProvince(cfRechargeEntity.getProvince());
		cfAccesspriceEntity.setStatus(AppCommonsCode.STATUS_OK.value());
		/*查询接入者报价*/
		CfAccesspriceEntity cccessprice = null;
		cccessprice = cfAccesspriceMapper.selectprice(cfAccesspriceEntity);
		if(null == cccessprice){
			return null;
		}else{
			return cccessprice;
		}
	}
	/**
	 * 根据条件查询是否有供应商报价
	 * @param cfRechargeEntity
	 * @return	private CfSupplierpriceEntity checkSupplierprice(CfRechargeEntity cfRechargeEntity){

	 */
	private CfSupplierpriceEntity checkSupplierprice(CfRechargeEntity cfRechargeEntity){
		CfSupplierpriceEntity cfSupplierpriceEntity= new CfSupplierpriceEntity();
		cfSupplierpriceEntity.setIspno(cfRechargeEntity.getIspno());
		cfSupplierpriceEntity.setIpstype(cfRechargeEntity.getIsptype());
		cfSupplierpriceEntity.setPno(cfRechargeEntity.getProvince());
		cfSupplierpriceEntity.setSize(cfRechargeEntity.getFacevalue());
		cfSupplierpriceEntity.setStatus(AppCommonsCode.STATUS_OK.value());
		/*查询供应商报价*/
		List<CfSupplierpriceEntity> g = cfSupplierpriceMapper.queryrecharge(cfSupplierpriceEntity);
		if(null == g || g.size() <=0 ){
			return null;
		}else{
			return g.get(0);
		}
	}
	
	/**
	 * 查询是否欠费
	 * @param cfRechargeEntity
	 * @return
	 */
	private boolean checkArrears(CfRechargeEntity cfRechargeEntity,String apprice){
		CfAccessclientEntity ac = cfAccessclientMapper.selectByid(cfRechargeEntity.getAcid());
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
	private int updateStatus33(CfRechargeEntity cfRecharge){
		CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setOrderid(cfRecharge.getOrderid());
		cfRechargeEntity.setBizid(cfRecharge.getBizid());
		cfRechargeEntity.setStatus(AppCommonsCode.RECHARGE_33.value());
		return cfRechargeMapper.updateStatus(cfRechargeEntity);
	}
	
	/**
	 * 修改状态
	 * @param acid
	private void updateStatus1(String id,String status){
		CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setOrderid(id);
		cfRechargeEntity.setStatus(status);
		cfRechargeMapper.updateStatus(cfRechargeEntity);
	}
	*/
	
	/**
	 * 存流水表(单独事务)
	 * @param cfRechargeEntity
	 * @param c
	 * @param g1
	 */
	private void rechargeToCurrbusilog(CfRechargeEntity cfRechargeEntity, CfAccesspriceEntity accessprice,CfSupplierpriceEntity supplierprice,String responsecode,String status){
		/*DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = transactionManager.getTransaction(def);*/
		
		CfCurrbusilogEntity currbusilog = new CfCurrbusilogEntity();
		
		currbusilog.setStatus(BusiLogStatus.INPUT.value());
		
		currbusilog.setBizid(cfRechargeEntity.getBizid());
		currbusilog.setPhone(cfRechargeEntity.getPhone());
		currbusilog.setIspno(cfRechargeEntity.getIspno());
		currbusilog.setIpstype(cfRechargeEntity.getIsptype());
		currbusilog.setProvince(cfRechargeEntity.getProvince());
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
	