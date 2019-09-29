/**
 * TellimitServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.service.system.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.security.userdetails.UserDetail;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.entity.system.CloudSecurityLimitEntity;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.mapper.system.CloudSecurityLimitMapper;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.model.system.CheckWayModel;
import cn.com.jansh.service.system.CloudSecurityLimitService;
import cn.com.jansh.utils.CcpSmsUtil;
import cn.com.jansh.utils.RandomNumUtil;
import cn.com.jansh.vo.AjaxObj;

/**
 * 验证码发送频率限制服务实现
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class CloudSecurityLimitServiceImpl implements CloudSecurityLimitService {

	private static final Logger logger = LogManager.getLogger(CloudSecurityLimitServiceImpl.class);
	@Autowired
	private CloudSecurityLimitMapper  limitMapper;
	@Autowired
	private CcpSmsUtil smsutil;
	@Autowired
	private GlobalProperties globalProperties; 
	@Autowired
	private IMUserMapper userMapper;
	/**
	 *	限制发送验证码频率
	 *
	 */
	@Override
	public AjaxObj limitSendFrequency(CheckWayModel checkWayModel , HttpSession session) {
		
		AjaxObj  ajaxObj = new AjaxObj();
		/** 校验手机号是否存在*/
		IMUser check = userMapper.selectOneByPhoneno(checkWayModel.getPhoneNo());
		if(checkWayModel.getCheckFlag() == 1 && check == null){
			ajaxObj.setMsg("用户不存在");
			ajaxObj.setResult(0);
			return ajaxObj;
		}
		
		if(checkWayModel.getCheckFlag() == 0 && check != null){
			ajaxObj.setMsg("用户已存在");
			ajaxObj.setResult(0);
			return ajaxObj;
		}
		
		/** 获取6位随机数 **/
		String msgcode = RandomNumUtil.getRandomNum(6);
		logger.info("msgcode:"+msgcode);
		checkWayModel.setMsgcode(msgcode);
		/** 此处应配置银行短信接口向银行卡手机发送验证码短信 **/
		Map<String,String> codemap = new HashMap<String,String>();
		codemap.put("msgcode", msgcode);
		Date date = new Date();
		codemap.put("time", String.valueOf(date.getTime()));
		session.setAttribute("_app_msg_code", codemap);
		
		logger.info("开始限制发送频率");
		String current = DateUtil.getDateTimestamp();
		int interval = Integer.parseInt(globalProperties.getSendInterval());
		/*if(StringUtils.isNoneBlank(checkWayModel.getMailName())){
			String mailStandardModel = globalProperties.getRegisterMailModel();
			String mailModel = String.format(mailStandardModel, checkWayModel.getMsgcode());
			CloudSecurityLimitEntity mailResult = limitMapper.selectByPhone(checkWayModel.getMailName());
			if(mailResult == null){
				mailResult = new CloudSecurityLimitEntity();
				mailResult.setReportno(checkWayModel.getMailName());
				mailResult.setUpdatetime(current);
				logger.info("开始向邮箱（"+checkWayModel.getMailName()+"）发送邮件，发送时间是 ："+current);
				try {
					emailService.sendmail(checkWayModel.getMailName(), globalProperties.getRegisterMailTitle(),mailModel);
				} catch (EmailException e) {
					logger.error("邮箱发送验证码异常！",e);
					ajaxObj.setResult(0);
					ajaxObj.setSuccess(false);
					ajaxObj.setMsg("验证码发送失败！");
					return ajaxObj;
				}
				limitMapper.insert(mailResult);
			}else{
				//有发送记录则判断是否被限制，没有限制则更新记录
				String updatetime = mailResult.getUpdatetime();
				try {
					Date update = new SimpleDateFormat("yyyyMMddHHmmss").parse(updatetime);
					Date currentdate = new SimpleDateFormat("yyyyMMddHHmmss").parse(current);
					
					if(currentdate.getTime() - update.getTime() >= interval*60*1000 ){
						mailResult.setUpdatetime(current);
						logger.info("开始向邮箱（"+checkWayModel.getMailName()+"）发送邮件！");
						try {
							emailService.sendmail(checkWayModel.getMailName(), globalProperties.getRegisterMailTitle(),mailModel);
						} catch (EmailException e) {
							logger.error("邮箱发送验证码异常！",e);
							ajaxObj.setResult(0);
							ajaxObj.setSuccess(false);
							ajaxObj.setMsg("验证码发送失败！");
							return ajaxObj;
						}
						limitMapper.update(mailResult);
					}else{
						logger.info("距离上次验证码发送时间已经过的秒数是:"+((currentdate.getTime() - update.getTime())/1000));
						ajaxObj.setResult(0);
						ajaxObj.setSuccess(false);
						ajaxObj.setMsg("验证码发送过于频繁！");
						return ajaxObj;
					}
				} catch (ParseException e1) {
					logger.error("解析日期异常！",e1);
					ajaxObj.setResult(0);
					ajaxObj.setSuccess(false);
					ajaxObj.setMsg("验证码发送失败！");
					return ajaxObj;
				}
			}
		}*/
		UserDetail userDetail = AppUtil.getUserDetail();
		String phoneno = null;
		if(userDetail!=null){
			String userid = userDetail.getUserid();
			IMUserN user = userMapper.selectNewByUserid(userid);
			phoneno = user.getPhoneno();
		}
		if(StringUtils.isNotBlank(checkWayModel.getPhoneNo())||StringUtils.isNotBlank(phoneno)){
			if(StringUtils.isBlank(phoneno)){
				phoneno = checkWayModel.getPhoneNo();
			}
			CloudSecurityLimitEntity phoneResult = limitMapper.selectByPhone(phoneno);
			if(phoneResult == null){
				/*没有发送记录则插入记录*/
				phoneResult = new CloudSecurityLimitEntity();
				phoneResult.setReportno(phoneno);
				phoneResult.setUpdatetime(current);
				logger.info("开始向手机号"+phoneno+"发送验证码，验证码发送时间是 ："+current);
				smsutil.sendTemplateSMS(phoneno, checkWayModel.getMsgModel(),new String[]{checkWayModel.getMsgcode(), String.valueOf(interval)});
				limitMapper.insert(phoneResult);
				ajaxObj.setResult(1);
				ajaxObj.setSuccess(true);
				ajaxObj.setMsg("验证码发送成功！");
				return ajaxObj;
			}else{
				/*有发送记录则判断是否被限制，没有限制则更新记录*/
				String updatetime = phoneResult.getUpdatetime();
				try {
					Date update = new SimpleDateFormat("yyyyMMddHHmmss").parse(updatetime);
					Date currentdate = new SimpleDateFormat("yyyyMMddHHmmss").parse(current);
					if(currentdate.getTime() - update.getTime() >= interval*60*1000 ){
						phoneResult.setUpdatetime(current);
						logger.info("验证码发送时间是 ："+current);
						smsutil.sendTemplateSMS(phoneno, checkWayModel.getMsgModel(),new String[]{checkWayModel.getMsgcode(),String.valueOf(interval)});
						limitMapper.update(phoneResult);
						ajaxObj.setResult(1);
						ajaxObj.setSuccess(true);
						ajaxObj.setMsg("验证码发送成功！");
						return ajaxObj;
					}else{
						logger.info("距离上次验证码发送时间已经过的秒数是:"+((currentdate.getTime() - update.getTime())/1000));
						ajaxObj.setResult(0);
						ajaxObj.setSuccess(false);
						ajaxObj.setMsg("验证码发送过于频繁！");
						return ajaxObj;
					}
				} catch (ParseException e1) {
					logger.error("解析日期异常！",e1);
					ajaxObj.setResult(0);
					ajaxObj.setSuccess(false);
					ajaxObj.setMsg("验证码发送失败！");
					return ajaxObj;
				}
			}
		}
		ajaxObj.setResult(0);
		ajaxObj.setSuccess(false);
		ajaxObj.setMsg("手机号不能为空！");
		return ajaxObj;
	}


}
