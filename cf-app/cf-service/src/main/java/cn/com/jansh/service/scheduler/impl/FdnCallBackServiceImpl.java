package cn.com.jansh.service.scheduler.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.BusiLogStatus;
import cn.com.jansh.constant.CallBackCode;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.model.wsfdn.WsfdnOrdercb;
import cn.com.jansh.service.scheduler.FdnCallBackService;

@Service
public class FdnCallBackServiceImpl implements FdnCallBackService{

	private static final Logger logger = LogManager.getLogger(FdnCallBackServiceImpl.class);
	
	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;
	
//	@Autowired
//	private CfRechargeMapper cfRechargeMapper;
//	
//	@Autowired
//	private CfBatchrechargeMapper cfBatchrechargeMapper;
	
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	/**
	 * 流量订购-网宿（回调刷状态）
	 * 
	 * @return
	 */
	@Override
	public void wsOrdercb(WsfdnOrdercb wsfdnOrdercb) {
		String resultCode = wsfdnOrdercb.getResultCode();
		
		CfCurrbusilogEntity cfCurrbusilog = cfCurrbusilogMapper.queryCurrbusilogByid(wsfdnOrdercb.getTransNo());
		
		logger.info("更新流水表");
		CfCurrbusilogEntity updateCzCurrBusiLog = new CfCurrbusilogEntity();
		updateCzCurrBusiLog.setBizid(wsfdnOrdercb.getTransNo());
		updateCzCurrBusiLog.setUpdatetime(DateUtil.getTimestamp());
//		logger.info("更新单笔充值表");
//		CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
//		cfRechargeEntity.setBizid(wsfdnOrdercb.getTransNo());
//		logger.info("更新批量充值表");
//		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
//		cfBatchrechargeEntity.setBizid(wsfdnOrdercb.getTransNo());
		
		if (CallBackCode.WS_REFRESH_OK.value().equals(resultCode)) {
			countCumulative(cfCurrbusilog);
			updateCzCurrBusiLog.setStatus(BusiLogStatus.SUCCEED.value());
			//cfRechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());
			//cfBatchrechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());
		}else{
			minusCumulative(cfCurrbusilog);
			updateCzCurrBusiLog.setStatus(BusiLogStatus.FAIL.value());
			/*cfRechargeEntity.setStatus(BusiLogStatus.FAIL.value());
			cfBatchrechargeEntity.setStatus(BusiLogStatus.FAIL.value());*/
		}
		updateCzCurrBusiLog.setOrderid(wsfdnOrdercb.getOrderId());
		updateCzCurrBusiLog.setCporderno(wsfdnOrdercb.getCpOrderNo());
		updateCzCurrBusiLog.setResponsecode(BusiLogStatus.PLATFORM_WS.value()+resultCode);
		cfCurrbusilogMapper.update(updateCzCurrBusiLog);
//		cfRechargeMapper.updateStatusByBizid(cfRechargeEntity);
//		cfBatchrechargeMapper.updateBatchByBizid(cfBatchrechargeEntity);
	}
	
	/**
	 * 话费订购-欧飞（回调刷状态）
	 * @return
	 */
	@Override
	public void ofOrdercb(Map<String, String> map) {
		String ret_code = map.get("ret_code");
		String sporder_id = map.get("sporder_id");
		String ordersuccesstime = map.get("ordersuccesstime");
		
		CfCurrbusilogEntity cfCurrbusilog = cfCurrbusilogMapper.queryCurrbusilogByid(sporder_id);
		logger.info("更新流水表");
		CfCurrbusilogEntity updateCzCurrBusiLog = new CfCurrbusilogEntity();
		updateCzCurrBusiLog.setBizid(sporder_id);
		updateCzCurrBusiLog.setUpdatetime(ordersuccesstime);
//		logger.info("更新单笔充值表");
//		CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
//		cfRechargeEntity.setBizid(sporder_id);
//		
//		logger.info("更新批量充值表");
//		CfBatchrechargeEntity cfBatchrechargeEntity = new CfBatchrechargeEntity();
//		cfBatchrechargeEntity.setBizid(sporder_id);
		
		if (CallBackCode.OF_REFRESH_OK.value().equals(ret_code)) {
			countCumulative(cfCurrbusilog);
			updateCzCurrBusiLog.setStatus(BusiLogStatus.SUCCEED.value());
			/*cfRechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());
			cfBatchrechargeEntity.setStatus(BusiLogStatus.SUCCEED.value());*/
		}else{
			minusCumulative(cfCurrbusilog);
			updateCzCurrBusiLog.setStatus(BusiLogStatus.FAIL.value());
			/*cfRechargeEntity.setStatus(BusiLogStatus.FAIL.value());
			cfBatchrechargeEntity.setStatus(BusiLogStatus.FAIL.value());*/
		}
		cfCurrbusilogMapper.update(updateCzCurrBusiLog);
//		cfRechargeMapper.updateStatusByBizid(cfRechargeEntity);
//		cfBatchrechargeMapper.updateBatchByBizid(cfBatchrechargeEntity);
	}
	
	/**
	 * 累计消费
	 * @param price
	 * @param acid
	 */
	private void countCumulative(CfCurrbusilogEntity cfCurrbusilogEntity){
		if(cfCurrbusilogEntity.getStatus().equals(BusiLogStatus.UNKNOW.value())){
			cfAccessclientMapper.countCumulative(cfCurrbusilogEntity.getApprice(),cfCurrbusilogEntity.getApno());
		}
	}
	
	/**
	 * 减少消费
	 * @param price
	 * @param acid
	 */
	private void minusCumulative(CfCurrbusilogEntity cfCurrbusilogEntity){
		if(cfCurrbusilogEntity.getStatus().equals(BusiLogStatus.INPUT.value())||
				cfCurrbusilogEntity.getStatus().equals(BusiLogStatus.ACCEPT.value())){
			cfAccessclientMapper.minusCumulative(cfCurrbusilogEntity.getApprice(),cfCurrbusilogEntity.getApno());
		}
	}
}
