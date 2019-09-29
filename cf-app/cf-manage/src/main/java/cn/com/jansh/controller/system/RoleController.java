package cn.com.jansh.controller.system;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.jansh.core.annotation.ExceptionHandle;
import cn.com.jansh.core.annotation.OperationResult;
import cn.com.jansh.core.constant.Operation;
import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.security.MySecurityMetadataSource;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.entity.system.IMReSource;
import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.model.system.RoleManageModel;
import cn.com.jansh.service.system.ResourceService;
import cn.com.jansh.service.system.RoleService;

/**
 * 系统管理 — 角色管理
 * 
 * @author
 */
@Controller("roleController")
@RequestMapping(value = "/role")
public class RoleController {

	private static final Logger logger = LogManager.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resoureceService;
	@Autowired
	private MySecurityMetadataSource securityMetadataSource;

	/**
	 * 跳转初始化页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init(RoleManageModel roleManageModel) {
		logger.info("获取角色列表");
		List<IMRole> roleList = roleService.queryRoleList();
		roleManageModel.setRolelist(roleList);

		logger.info("获取资源列表");
		List<IMReSource> resourceList = resoureceService.queryAllResource();
		roleManageModel.setImresourcdlist(resourceList);
		return "sysmanage/rolemanage/rolemanage";
	}

	/**
	 * 新增页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/addinit")
	public String addinit(RoleManageModel roleManageModel) throws JsonProcessingException {

		logger.info("获取资源列表");
		List<Map<String, String>> resourceList = resoureceService.queryResourceList(null);
		roleManageModel.setResourceList(JsonUtil.obj2json(resourceList));

		return "sysmanage/rolemanage/roleadd";
	}

	/**
	 * 增加数据
	 * 
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/adddata")
	@OperationResult(value = Operation.CREATE)
	@ExceptionHandle("/role/addinit")
	public String roleadd(RoleManageModel roleManageModel) throws AppException {
		logger.info("添加数据:{}", roleManageModel);
		roleService.addRole(roleManageModel);
		// 刷新缓存资源
		securityMetadataSource.loadResourceDefine();
		return init(roleManageModel);

	}

	/**
	 * 修改页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/editinit")
	public String editinit(RoleManageModel roleManageModel) throws JsonProcessingException {
		logger.info("修改用户" + roleManageModel.getRoleId());

		logger.info("获取资源列表");
		List<Map<String, String>> resourceList = resoureceService.queryResourceList(roleManageModel.getRoleId());
		roleManageModel.setResourceList(JsonUtil.obj2json(resourceList));

		return "sysmanage/rolemanage/roleedit";

	}

	/**
	 * 修改数据
	 * 
	 * @param roleManageModel
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/editdata")
	@ExceptionHandle("/role/editinit")
	@OperationResult(value = Operation.UPDATE)
	public String roleedit(RoleManageModel roleManageModel) throws AppException {
		logger.info("添加数据:{}", roleManageModel);

		IMRole role = new IMRole();
		role.setRoleId(roleManageModel.getRoleId());
		role.setReSourceIds(roleManageModel.getResourceId());
		role.setRoleName(roleManageModel.getRoleName());
		role.setStatus("1");
		role.setUpdateTime(DateUtil.getDateTime());

		roleService.updateRole(role);

		// 刷新缓存资源
		securityMetadataSource.loadResourceDefine();
		return init(roleManageModel);
	}

	/**
	 * 删除页面初始化
	 * 
	 * @param userManageModel
	 * @return
	 */
	@RequestMapping(value = "/delinit")
	public String delinit(RoleManageModel roleManageModel) throws JsonProcessingException {
		logger.info("删除用户" + roleManageModel.getRoleId());
		List<Map<String, String>> resourceList = resoureceService.queryResourceList(roleManageModel.getRoleId());
		roleManageModel.setResourceList(JsonUtil.obj2json(resourceList));		
		return "sysmanage/rolemanage/roledel";
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata")
	@OperationResult(value = Operation.DELETE)
	@ExceptionHandle("/role/delinit")
	public String deldata(RoleManageModel roleManageModel) throws Exception {
		logger.info("删除用户" + roleManageModel.getRoleId());

		String[] str = roleManageModel.getRoleId().split(",");
		roleService.deleteRoleByIds(str);
		// 刷新缓存资源
		securityMetadataSource.loadResourceDefine();

		return init(roleManageModel);
	}

}
