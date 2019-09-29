/**
 * BlackInterServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月25日
 */
package cn.com.jansh.service.risk.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.constant.AppCommonsCode;
import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.ContextCode;

import com.jansh.comm.util.JsonUtil;
import com.jansh.core.exception.AppException;
import com.jansh.core.web.servlet.ViewObject;
import cn.com.jansh.entity.risk.BlacklistEntity;
import cn.com.jansh.mapper.risk.BlackMapper;
import cn.com.jansh.model.risk.BlacklistModel;
import cn.com.jansh.service.risk.BlackInterService;

/**
 * 获取黑名单接口
 * @author gll
 * @version 1.0
 */
@Service
public class BlackInterServiceImpl implements BlackInterService {

	private static final Logger logger = LogManager.getLogger(BlackInterServiceImpl.class);
	
	@Autowired
	public BlackMapper blackMapper;
	/**
	 * 获取黑名单接口
	 * @throws AppException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ViewObject acquireblack(String janshAuth, String msg) throws AppException {
		ViewObject ViewObject = new ViewObject();
		BlacklistModel blacklistModel = null;
		try {
			blacklistModel = JsonUtil.readObject(msg, BlacklistModel.class);
		} catch (Exception e1) {
			logger.error("解析请求参数异常：{}", e1);
			ViewObject.setErrorCode(AppCommonsCode.BLACKINTER_ERROR_PARSE.value());
			ViewObject.setErrorMsg("交易请求错误，检查请求报文");
			return ViewObject;
		}
		if (blacklistModel == null) {
			logger.error("参数异常，blacklistModel is null");
			ViewObject.setErrorCode(AppCommonsCode.BLACKINTER_ERROR_PARSE.value());
			ViewObject.setErrorMsg("交易请求错误，检查请求报文");
			return ViewObject;
		}
		// 检查请求是否合法
		boolean reqfalg = checkJanshAuth(janshAuth, msg, blacklistModel.getBlackvalue());
		if (!reqfalg) {
			logger.error("http header 属性 JANSHAUTH error ：{}", janshAuth);
			ViewObject.setErrorCode(AppCommonsCode.BLACKINTER_ERROR_REQUEST.value());
			ViewObject.setErrorMsg("验签失败");
			return ViewObject;
		}
		//活动id不为空校验
		if (StringUtils.isBlank(blacklistModel.getActionid())) {
			logger.error("活动id不能为空");
			throw new AppException(AppErrorCode.E230002);
		}
		//黑名单值不为空校验
		if (StringUtils.isBlank(blacklistModel.getBlackvalue())) {
			logger.error("黑名单值不为空");
			throw new AppException(AppErrorCode.E230004);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("actionid", blacklistModel.getActionid());
		map.put("blackvalue", blacklistModel.getBlackvalue());
		map.put("status", ContextCode.BLACKLIST_STATUS_SHENGXIAO.value());
		
		BlacklistEntity c = blackMapper.selectBlackInter(map);
		BlacklistModel blacklistModel1 = new BlacklistModel();
		
		if(null != c){
			blacklistModel1.setActionid(c.getActionid());
			blacklistModel1.setBlackid(c.getBlackid());
			blacklistModel1.setBlacktype(c.getBlacktype());
			blacklistModel1.setBlackvalue(c.getBlackvalue());
			blacklistModel1.setOvertime(c.getOvertime());
			blacklistModel1.setRemark(c.getRemark());
			blacklistModel1.setStatus(c.getStatus());
			ViewObject.setErrorCode(AppCommonsCode.BLACKINTER_OK.value());
			ViewObject.setErrorMsg("查询结果，是黑名单");
			ViewObject.setData(blacklistModel1);
			return ViewObject;
		}else{
			ViewObject.setErrorCode(AppCommonsCode.BLACKINTER_ERROR.value());
			ViewObject.setErrorMsg("查询结果，不是黑名单");
			ViewObject.setData(blacklistModel1);
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
	private boolean checkJanshAuth(String janshAuth, String msg, String blackvalue) {
		/*BlacklistEntity  list = blackMapper.selectByblackvalue(blackvalue);
		if (null == list) {
			return false;
		}*/
		String salt = janshAuth.substring(0, 8);
		String source = salt + msg + "465";
		String digest = DigestUtils.md5Hex(source);
		if (janshAuth.equals(salt + digest)) {
			return true;
		}
		return false;
	}

}
