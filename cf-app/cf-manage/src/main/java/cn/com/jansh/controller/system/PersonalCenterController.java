package cn.com.jansh.controller.system;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.model.system.UserManageModel;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.service.system.RoleService;

@Controller
@RequestMapping(value = "/personalcenter")
public class PersonalCenterController {
	
	private static final Logger logger = LogManager.getLogger(PersonalCenterController.class);

	@Autowired
	private IMUserService imUserService;
	@Autowired
	private RoleService roleService;

	/**
	 * 初始化页面
	 * 
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping("/init")
	public String initData(UserManageModel userManageModel) {
		String userId = AppUtil.getUserDetail().getUserid();
		logger.info("获取用户信息");
		userManageModel.setUserid(userId);
		IMUser user = imUserService.selectByUserid(userId);
		userManageModel.setCname(user.getCname());
		userManageModel.setEmail(user.getEmail());
		userManageModel.setPhoneno(user.getPhoneno());
		userManageModel.setUsername(user.getUsername());

		logger.info("获取用户角色列表");	
		List<String> roleids = roleService.getRoreidByuserid(userId);
		List<IMRole> rolelist = roleService.getRolelistByroleids(roleids);
	/*	List<IMRole> roleList = roleService.queryRoleList();
		userManageModel.setRoleList(roleList);*/
		userManageModel.setRoleList(rolelist);
		return "sysmanage/personalcenter/personalcenter";
	}

	/**
	 * 修改个人信息
	 * 
	 * @param userManageModel
	 * @return
	 * @throws AppException 
	 */
	@RequestMapping("/update")
	@OperationResult(value = Operation.UPDATE)
	@ExceptionHandle("/personalcenter/init")
	public String updateUser(UserManageModel userManageModel) throws AppException {
		String userId = AppUtil.getUserDetail().getUserid();
		logger.info("获取用户信息");
		IMUser imuser = new IMUser();
		imuser.setUserid(userId);
		imuser.setPhoneno(userManageModel.getPhoneno());
		imuser.setEmail(userManageModel.getEmail());
		imUserService.updateUserInfo(imuser);
		return initData(userManageModel);
	}
}
