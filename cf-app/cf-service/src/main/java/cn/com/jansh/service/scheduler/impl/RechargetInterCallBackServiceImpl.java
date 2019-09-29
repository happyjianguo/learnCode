package cn.com.jansh.service.scheduler.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.util.HttpClientRequest;
import cn.com.jansh.core.util.HttpClientUtil;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfRechargeEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.mapper.wsfdn.CfRechargeMapper;
import cn.com.jansh.model.wsfdn.RechargeQueryOrder;
import cn.com.jansh.service.scheduler.RechargetInterCallBackService;

@Service
public class RechargetInterCallBackServiceImpl implements RechargetInterCallBackService {

	private static final Logger logger = LogManager.getLogger(RechargetInterCallBackServiceImpl.class);
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private CfRechargeMapper cfRechargeMapper;
	
	@Autowired
	private CfCurrbusilogMapper CfCurrbusilogMapper;
	
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Override
	public void CallBack() {
		List<CfRechargeEntity> li = cfRechargeMapper.queryCallBack(AppCommonsCode.SOURCE_INTERFACE.value(), AppCommonsCode.CALLBACK_INIT.value(),AppCommonsCode.RECHARGE_30.value());
		for(CfRechargeEntity cfRecharge :li){
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			TransactionStatus status = transactionManager.getTransaction(def);
			try{
				RechargeQueryOrder rechargeQueryOrder = new RechargeQueryOrder();
				CfRechargeEntity cf = new CfRechargeEntity();
				cf.setBizid(cfRecharge.getBizid());
				cf.setCbstatus(AppCommonsCode.CALLBACK_OK.value());
				if(cfRechargeMapper.updateStatusByBizid(cf)<=0){
					continue;
				}
				CfCurrbusilogEntity cfCurrbusilogEntity = CfCurrbusilogMapper.queryCurrbusilogByid(cfRecharge.getBizid());
				CfAccessclientEntity cfAccessclientEntity = cfAccessclientMapper.selectByid(cfCurrbusilogEntity.getApno());
				String callbackurl = cfAccessclientEntity.getCallbackurl();
				
				rechargeQueryOrder.setCporder(cfRecharge.getOrderid());
				rechargeQueryOrder.setOrdertime(cfCurrbusilogEntity.getCreatetime());
				rechargeQueryOrder.setPhone(cfRecharge.getPhone());
				rechargeQueryOrder.setSporder(cfRecharge.getBizid());
				rechargeQueryOrder.setStatus(cfCurrbusilogEntity.getStatus());
				rechargeQueryOrder.setSysid(cfRecharge.getAcid());
				rechargeQueryOrder.setApprice(cfCurrbusilogEntity.getApprice());
				rechargeQueryOrder.setIpstype(cfCurrbusilogEntity.getIpstype());
				String Json ="";
				try {
					Json = mapper.writeValueAsString(rechargeQueryOrder);
				} catch (JsonProcessingException e) {
					logger.error("充值接口回调Entity转JSON异常{}",e);
					transactionManager.rollback(status);
					continue;
				} 
				
				HttpClientRequest request = new HttpClientRequest();
				request.setBody(Json);
				try {
					HttpClientUtil.httpPost(callbackurl, request);
				} catch (Exception e) {
					logger.error("充值接口回调发送异常{}",e);
					transactionManager.rollback(status);
					continue;
				}
			}catch (Exception e) {
				transactionManager.rollback(status);
				continue;
			}
		transactionManager.commit(status);
		}
	}

}
