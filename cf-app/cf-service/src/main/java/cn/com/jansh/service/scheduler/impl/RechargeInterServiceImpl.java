package cn.com.jansh.service.scheduler.impl;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;
import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfRechargeEntity;
import cn.com.jansh.mapper.wsfdn.CfAccessclientMapper;
import cn.com.jansh.mapper.wsfdn.CfCurrbusilogMapper;
import cn.com.jansh.mapper.wsfdn.CfRechargeMapper;
import cn.com.jansh.model.wsfdn.RechargeOrder;
import cn.com.jansh.model.wsfdn.RechargeQueryOrder;
import cn.com.jansh.service.scheduler.QCellCoreService;
import cn.com.jansh.service.scheduler.RechargeInterService;

@Service
public class RechargeInterServiceImpl implements RechargeInterService {

	private static final Logger logger = LogManager.getLogger(RechargeInterServiceImpl.class);
	
	@Autowired
	private CfAccessclientMapper cfAccessclientMapper;
	
	@Autowired
	private CfRechargeMapper cfRechargeMapper;
	
	@Autowired
	private CfCurrbusilogMapper cfCurrbusilogMapper;
	
	@Autowired
	private QCellCoreService qCellCoreService;
	
	/***
	 * 直冲接口
	 */
	@Override
	public ViewObject billorder(String janshAuth, String msg) {
		
		ViewObject ViewObject = new ViewObject();
		RechargeOrder rechargeOrder = null;
		try {
			rechargeOrder = JsonUtil.readObject(msg, RechargeOrder.class);
		} catch (Exception e1) {
			logger.error("解析请求参数异常：{}", e1);
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_PARSE.value());
			ViewObject.setErrorMsg("参数异常");
			return ViewObject;
		}
		// 检查请求是否合法
		boolean reqfalg = checkJanshAuth(janshAuth, msg, rechargeOrder.getSysid());
		if (!reqfalg) {
			logger.error("http header 属性 JANSHAUTH error ：{}", janshAuth);
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_REQUEST.value());
			ViewObject.setErrorMsg("非法请求");
			return ViewObject;
		}
		if(rechargeOrder != null){
			rechargeOrder.setFacevalue(rechargeOrder.getFacevalue().toUpperCase());
			if(rechargeOrder.getIsptype().equals(AppCommonsCode.IPSTYPE_Flow.value())){
				if(!(rechargeOrder.getFacevalue().indexOf("M")>0 || rechargeOrder.getFacevalue().indexOf("G")>0)){
					logger.error("http header 属性 JANSHAUTH error ：{}", janshAuth);
					ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_PARSE.value());
					ViewObject.setErrorMsg("充值流量无单位（M、G）");
					return ViewObject;
				}
			}
		}
		
		if(cfRechargeMapper.queryByidAndSysid(rechargeOrder.getCporder(), rechargeOrder.getSysid())!=null){
			logger.error("http header 属性 JANSHAUTH error ：{}", janshAuth);
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_PARSE.value());
			ViewObject.setErrorMsg("订单号重复");
			return ViewObject;
		}
		/**查询归属地、运营商*/
		Map<String,String> map = qCellCoreService.resultQCellCore(rechargeOrder.getPhone());
		String province = map.get("province");
		String supplier = map.get("supplier");
		
		//存入单笔充值表，返回受理成功
		CfRechargeEntity cfRechargeEntity = new CfRechargeEntity();
		cfRechargeEntity.setId(IDUtils.getTimeRandon());
		cfRechargeEntity.setAcid(rechargeOrder.getSysid());
		CfAccessclientEntity cfAccessclientEntity = cfAccessclientMapper.selectByid(rechargeOrder.getSysid());
		cfRechargeEntity.setCid(cfAccessclientEntity.getCid());
		cfRechargeEntity.setFacevalue(rechargeOrder.getFacevalue());
		cfRechargeEntity.setOrderid(rechargeOrder.getCporder());
		
		/**若有归属地、运营商就用传过来的*/
		if(StringUtils.isBlank(rechargeOrder.getIsptype())){
			cfRechargeEntity.setIspno(supplier);
		}else{
			cfRechargeEntity.setIspno(rechargeOrder.getIsptype());
		}
		
		if(StringUtils.isBlank(rechargeOrder.getProvince())){
			cfRechargeEntity.setProvince(province);
		}else{
			cfRechargeEntity.setProvince(rechargeOrder.getProvince());
		}
		cfRechargeEntity.setIsptype(rechargeOrder.getIsptype());
		cfRechargeEntity.setPhone(rechargeOrder.getPhone());
		
		cfRechargeEntity.setStatus(AppCommonsCode.RECHARGE_30.value());
		cfRechargeEntity.setCbstatus(AppCommonsCode.CALLBACK_INIT.value());
		cfRechargeEntity.setSource(AppCommonsCode.SOURCE_INTERFACE.value());
		cfRechargeEntity.setUserid(AppCommonsCode.SOURCE_INTERFACE.value());
		cfRechargeMapper.insert(cfRechargeEntity);
		return ViewObject;
	}

	

	/**
	 * 订单状态查询接口
	 */
	@Override
	public ViewObject queryorder(String janshAuth, String msg) {
		ViewObject ViewObject = new ViewObject();
		RechargeQueryOrder rechargeQueryOrder = null;
		try {
			rechargeQueryOrder = JsonUtil.readObject(msg, RechargeQueryOrder.class);
		} catch (Exception e1) {
			logger.error("解析请求参数异常：{}", e1);
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_PARSE.value());
			ViewObject.setErrorMsg("参数异常");
			return ViewObject;
		}
		
		// 检查请求是否合法
		boolean reqfalg = checkJanshAuth(janshAuth, msg, rechargeQueryOrder.getSysid());
		if (!reqfalg) {
			logger.error("http header 属性 JANSHAUTH error ：{}", janshAuth);
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_REQUEST.value());
			ViewObject.setErrorMsg("非法请求");
			return ViewObject;
		}
		CfRechargeEntity cfRechargeEntity = cfRechargeMapper.queryByidAndSysid(rechargeQueryOrder.getCporder(),rechargeQueryOrder.getSysid());
		if(cfRechargeEntity == null){
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR.value());
			ViewObject.setErrorMsg("无此订单");
			ViewObject.setData(rechargeQueryOrder);
			return ViewObject;
		}else{
			if(AppCommonsCode.RECHARGE_30.value().equals(cfRechargeEntity.getStatus())){
				ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR_WAIT.value());
				ViewObject.setErrorMsg("订单受理中");
				ViewObject.setData(rechargeQueryOrder);
				return ViewObject;
			}
		}
		CfCurrbusilogEntity cfCurrbusilog = cfCurrbusilogMapper.queryCurrbusilogByid(cfRechargeEntity.getBizid());
		
		if(cfCurrbusilog!=null){
			rechargeQueryOrder.setCporder(cfRechargeEntity.getOrderid());
			rechargeQueryOrder.setOrdertime(DateUtil.getDateTime());
			rechargeQueryOrder.setPhone(cfRechargeEntity.getPhone());
			rechargeQueryOrder.setSporder(cfCurrbusilog.getBizid());
			rechargeQueryOrder.setStatus(cfCurrbusilog.getStatus());
			rechargeQueryOrder.setSysid(cfRechargeEntity.getAcid());
			rechargeQueryOrder.setApprice(cfCurrbusilog.getApprice());
			
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_OK.value());
			ViewObject.setErrorMsg("查询成功");
			ViewObject.setData(rechargeQueryOrder);
			return ViewObject;
		}else{
			ViewObject.setErrorCode(AppCommonsCode.RECHARGEINTER_ERROR.value());
			ViewObject.setErrorMsg("无此订单");
			ViewObject.setData(rechargeQueryOrder);
			return ViewObject;
		}
		
	}
	
	/**
	 * 检查请求是否合法
	 * 
	 * @param janshAuth
	 * @param msg
	 * @return
	 */
	private boolean checkJanshAuth(String janshAuth, String msg, String sysid) {
		CfAccessclientEntity c = cfAccessclientMapper.selectByid(sysid);
		if (c == null) {
			return false;
		}
		if (StringUtils.isEmpty(c.getAckey())) {
			return false;
		}
		String salt = janshAuth.substring(0, 8);
		String source = salt + msg + c.getAckey();
		String digest = DigestUtils.md5Hex(source);
		if (janshAuth.equals(salt + digest)) {
			return true;
		}
		return false;
	}
}
