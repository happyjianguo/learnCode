/**
 * DropSecretController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月20日
 */
package cn.com.jansh.controller.personcenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.exception.AppException;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.model.system.CheckWayModel;
import cn.com.jansh.service.system.CloudSecurityLimitService;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 忘记密码
 * 
 * @author Mr.wong
 * @version 1.0
 */
@Controller
@RequestMapping("/drop")
public class DropSecretController {

	private static final Logger logger = LogManager.getLogger(DropSecretController.class);
	
	@Autowired
	private IMUserService imuserService;
	@Autowired
	private CloudSecurityLimitService limitService;
	@Autowired
	private GlobalProperties globalProperties; 

	/**
	 * 跳转忘记密码页面
	 * 
	 * @param model
	 * @return 跳转忘记密码页面
	 */
	@RequestMapping("/init")
	public String init(Model model) {
		logger.info("跳转修改密码页面");
		return "login/checkvalide";
	}
	/**
	 * 跳转错误页面
	 * 
	 * @param model
	 * @return 跳转错误页面
	 */
	@RequestMapping("/error")
	public String error(Model model) {
		logger.info("跳转修改密码错误页面");
		return "login/reterror";
	}

	/**
	 * 获取6随机数
	 * @param model
	 * @return
	 */
	@RequestMapping("/getvalidecode")
	@ResponseBody
	public AjaxObj getValidatedCode(CheckWayModel checkWayModel , HttpSession session) {
		logger.info("开始校验验证码");
		String msgModel = globalProperties.getRetrieveValidModel();
		checkWayModel.setMsgModel(msgModel);
		return limitService.limitSendFrequency(checkWayModel,session);
	}

	/**
	 * 校验验证码
	 * @param model
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping("/checkvalidecode")
	public String checkValideCode(HttpServletRequest request, HttpSession session, String username, String phoneno,
			String validecode) {
		logger.info("开始校验验证码");
		try {
			imuserService.checkValidCode(request);
		} catch (AppException e) {
			String errorCode = e.getErrCode();
			WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
			String errorMsg = ac.getMessage(errorCode, null, RequestContextUtils.getLocale(request));
			request.setAttribute("errorCode", errorCode);
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("username", username);
			request.setAttribute("phoneno", phoneno);
			request.setAttribute("validecode", validecode);
			return "login/checkvalide";
		}
		return "login/modifysecret";
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
	 * 根据验证码修改账户密码,跳转到成功页面
	 * @param imuser
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping("/modifysecret")
	@ExceptionHandle("/drop/error")
	public String modifypassword(HttpServletRequest request) throws AppException {
		logger.info("开始修改密码！");
		imuserService.modifypassword(request);
		return "/login/retsuccess";
	}

}
