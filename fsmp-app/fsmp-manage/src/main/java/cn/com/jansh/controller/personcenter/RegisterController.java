/**
 * RegisterController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月20日
 */
package cn.com.jansh.controller.personcenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jansh.core.exception.AppException;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.model.system.CheckWayModel;
import cn.com.jansh.model.system.RegisterAccountModel;
import cn.com.jansh.service.system.CloudSecurityLimitService;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 用户注册controller
 * @author Mr.wong
 * @version 1.0
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	private static final Logger logger = LogManager.getLogger(RegisterController.class);
	
	@Autowired
	private  IMUserService  imuserService;
	@Autowired
	private CloudSecurityLimitService limitService;
	@Autowired
	private GlobalProperties globalProperties; 
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("/init")
	public String init(){
		logger.info("跳转到注册页面");
		return "login/register";
	}
	/**
	 * 跳转到注册错误页面
	 * @return
	 */
	@RequestMapping("/error")
	public String error(){
		logger.info("跳转到注册错误页面");
		return "login/regerror";
	}
	
	/**
	 * 获取6随机数
	 * @param model
	 * @return 
	 */
	@RequestMapping("/getvalidecode")
	@ResponseBody
	public AjaxObj getValidatedCode(CheckWayModel checkWayModel , HttpSession session){
		logger.info("开始发送验证码");
		String msgModel = globalProperties.getRegisterValidModel();
		checkWayModel.setMsgModel(msgModel);
		return limitService.limitSendFrequency(checkWayModel,session);
	}
	/**
	 * 跳转到登录页
	 * @param model
	 * @return 
	 */
	@RequestMapping("/success")
	public String toLogin(){
		logger.info("开始跳转登录页面");
		return "redirect:/login";
	}
	
	/**
	 * 校验验证码，注册用户，跳转成功页面
	 * @param model
	 * @return 
	 */
	@RequestMapping("/do")
	public String doRegister(HttpServletRequest request , HttpSession session,@Valid RegisterAccountModel registerAccountModel){
		logger.info("开始用户注册");
		try {
			imuserService.insertImuserNew(registerAccountModel , session);
		} catch (AppException e) {
			String errorCode = e.getErrCode();
			WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
			String errorMsg = ac.getMessage(errorCode, null, RequestContextUtils.getLocale(request));
			request.setAttribute("errorCode", errorCode);
			request.setAttribute("errorMsg", errorMsg);
			return "/login/register";
		}
		return "/login/regsuccess";
	}
}
