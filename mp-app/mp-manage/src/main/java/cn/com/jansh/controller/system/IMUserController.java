package cn.com.jansh.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.exception.AppException;

import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.model.system.UserManageModel;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.service.system.RoleService;

/**
 * 系统管理-用户管理
 * 
 */
@Controller
@RequestMapping(value = "/imuser")
public class IMUserController {

	private static final Logger logger = LogManager.getLogger(IMUserController.class);

	@Autowired
	private IMUserService imUserService;

	@Autowired
	private RoleService roleService;

	/**
	 * 跳转初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(UserManageModel userManageModel) {
		logger.info("获取用户角色列表");
		List<IMUser> userList = imUserService.selectImuser(userManageModel);
		for(int i=0; i<userList.size();i++){
			userList.get(i).setCreatetime(DateUtil.formatDateTimestamp(userList.get(i).getCreatetime()));
		}
		userManageModel.setUserList(userList);

		logger.info("获取用户角色列表");
		List<IMRole> roleList = roleService.queryRoleList();
		userManageModel.setRoleList(roleList);
		return "sysmanage/usermanage/usermanage";
	}

	/**
	 * 新增页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/addinit")
	public String addinit(UserManageModel userManageModel) {
		logger.info("获取用户角色列表");
		List<IMRole> roleList = roleService.queryRoleList();
		userManageModel.setRoleList(roleList);
		return "sysmanage/usermanage/usermanageadd";
	}

	/**
	 * 增加数据
	 * 
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/adduser")
	@ExceptionHandle("/imuser/addinit")
	@OperationResult(value = Operation.CREATE)
	@OperationLog(value = Operation.CREATE)
	@SecurityRequest(repeatRequest = true)
	public String useradd(UserManageModel userManageModel) throws AppException {
		logger.info("添加数据:{}", userManageModel);
		imUserService.useradd(userManageModel);
		return init(userManageModel);
	}

	/**
	 * 修改页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/editinit")
	public String editinit(UserManageModel userManageModel, Model model) {
		logger.info("修改用户" + userManageModel.getUserid());
		userManageModel = imUserService.selectModelByUserid(userManageModel.getUserid());
		model.addAttribute(userManageModel);
		List<IMRole> roleList = roleService.queryRoleList();
		userManageModel.setRoleList(roleList);
		return "sysmanage/usermanage/usermanageedit";
	}

	/**
	 * 修改数据
	 * 
	 * @param imuser
	 * @return
	 */
	@RequestMapping(value = "/editdata")
	@OperationResult(value = Operation.UPDATE)
	@OperationLog(value = Operation.UPDATE)
	@ExceptionHandle("/imuser/editinit")
	public String useredit(UserManageModel userManageModel) throws AppException {
		logger.info("修改数据:{}", userManageModel);
		IMUser imuser = new IMUser();
		imuser.setUserid(userManageModel.getUserid());
		imuser.setOusername(userManageModel.getUsername());
		imuser.setUsername(userManageModel.getUsername());
		imuser.setCname(userManageModel.getCname());
		imuser.setPhoneno(userManageModel.getPhoneno());
		imuser.setEmail(userManageModel.getEmail());
		imuser.setRoleid(userManageModel.getRoleids());
		imUserService.updateImuser(imuser);

		return init(userManageModel);
	}

	/**
	 * 删除页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/delinit")
	public String delinit(UserManageModel userManageModel, Model model) {
		logger.info("删除用户" + userManageModel.getUserid());
		userManageModel = imUserService.selectModelByUserid(userManageModel.getUserid());
		model.addAttribute(userManageModel);
		List<IMRole> roleList = roleService.queryRoleList();
		userManageModel.setRoleList(roleList);
		return "sysmanage/usermanage/usermanagedel";
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@OperationResult(value = Operation.DELETE)
	@OperationLog(value = Operation.DELETE)
	public String deldata(UserManageModel userManageModel) throws Exception {
		logger.info("删除用户" + userManageModel.getUserid());
		try {
			List<String> useridli = new ArrayList<String>();
			useridli.add(userManageModel.getUserid());
			imUserService.delImuser(useridli);
		} catch (Exception e) {
			throw e;
		}
		return init(userManageModel);
	}

	/**
	 * 密码重置
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/initPwd")
	@OperationResult(value = Operation.DEFULT)
	@OperationLog(value = Operation.DEFULT)
	public String initPwd(UserManageModel userManageModel) {
		logger.info("密码重置{}", userManageModel.getUserid());
		try {
			if (StringUtils.isBlank(userManageModel.getUserid())) {
				/*
				 * ajaxObj.setMsg("密码重置异常"); ajaxObj.setResult(0); return
				 * ajaxObj;
				 */
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userManageModel.getUserid());
			int i = imUserService.initPwd(map);

			if (i > 0) {
				/*
				 * ajaxObj.setMsg("密码重置成功"); ajaxObj.setResult(1); return
				 * ajaxObj;
				 */
			} else {
				/*
				 * ajaxObj.setMsg("密码重置失败"); ajaxObj.setResult(0); return
				 * ajaxObj;
				 */
			}
		} catch (Exception e) {
			/*
			 * logger.error("密码重置异常{}", e); ajaxObj.setMsg("密码重置异常");
			 * ajaxObj.setResult(0); return ajaxObj;
			 */
		}
		return init(userManageModel);
	}
}
