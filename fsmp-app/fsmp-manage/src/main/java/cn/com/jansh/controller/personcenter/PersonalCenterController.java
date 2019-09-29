package cn.com.jansh.controller.personcenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.exception.AppException;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.model.system.CheckWayModel;
import cn.com.jansh.model.system.UserManageModel;
import cn.com.jansh.service.system.CloudSecurityLimitService;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 客户中心-个人信息修改
 * 
 * @author duanmuyn
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/personalcenter")
public class PersonalCenterController {

	private static final Logger logger = LogManager.getLogger(PersonalCenterController.class);

	@Autowired
	private IMUserService imUserService;
	@Autowired
	private CloudSecurityLimitService limitService;
	@Autowired
	private GlobalProperties globalProperties; 
	
	/**
	 * 初始化页面
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping("/init")
	public String initData(UserManageModel userManageModel) {
		logger.info("获取用户信息");
		IMUser user = imUserService.getCurrentUser();
		userManageModel.setUserid(user.getUserid());
		userManageModel.setCname(user.getCname());
		userManageModel.setEmail(user.getEmail());
		userManageModel.setPhoneno(user.getPhoneno());
		userManageModel.setUsername(user.getUsername());
		return "sysmanage/personalcenter/personalcenter";
	}

	/**
	 * 修改个人信息
	 * @param userManageModel
	 * @return
	 * @throws AppException
	 */
	@RequestMapping("/update")
	@SecurityRequest(repeatRequest=true)
	@ExceptionHandle("/personalcenter/init")
	@OperationResult(value=Operation.UPDATE,msgCode="340201")
	@OperationLog(value=Operation.UPDATE,key="340101")
	public String updateUser( @Valid UserManageModel userManageModel ,HttpServletRequest request) throws AppException {
		logger.info("更新用户信息");
		imUserService.updateUserInfo(request , userManageModel);
		return initData(userManageModel);
	}
	
	@RequestMapping("/show")
	@ResponseBody
	public void show(HttpServletRequest request , HttpServletResponse response){
		imUserService.showUserHead(request,response);
	}
	
	@RequestMapping("/checkphone")
	@ResponseBody
	public AjaxObj checkPhoneNo(String phoneno){
		logger.info("开始查询手机号");
		return imUserService.checkPhoneNo(phoneno);
	}
	@RequestMapping("/phoneexist")
	@ResponseBody
	public AjaxObj checkPhoneExist(String phoneno ,String email,String username){
		logger.info("开始查询手机号");
		return imUserService.checkPhoneExist(phoneno , email,username);
	}
	@RequestMapping("/checkvalidate")
	@ResponseBody
	public AjaxObj checkValidate(HttpServletRequest request){
		logger.info("开始校验验证码");
		return imUserService.checkValidate(request);
	}
	
	
	@RequestMapping("/getvalidcode")
	@ResponseBody
	public AjaxObj getValidCode(CheckWayModel checkWayModel ,HttpSession session){
		logger.info("开始获取验证码");
		String msgModel = globalProperties.getModifyValidModel();
		checkWayModel.setMsgModel(msgModel);
		return limitService.limitSendFrequency(checkWayModel,session);
	};
}
