package cn.com.jansh.service.system.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.core.exception.AppException;
import com.jansh.core.exception.SysErrorCode;

import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.entity.system.IMResourceRole;
import cn.com.jansh.entity.system.IMRole;
import cn.com.jansh.entity.system.IMUserRole;
import cn.com.jansh.mapper.system.IMResourceRoleMapper;
import cn.com.jansh.mapper.system.IMRoleMapper;
import cn.com.jansh.mapper.system.IMUserRoleMapper;
import cn.com.jansh.model.system.RoleManageModel;
import cn.com.jansh.service.system.RoleService;

/**
 * 角色管理业务逻辑层实现
 *
 * @author Mr.wong
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

	@Autowired
	private IMRoleMapper roleMapper;
	@Autowired
	private IMResourceRoleMapper resourceRMapper;
	@Autowired
	private IMUserRoleMapper userRoleMapper;

	/**
	 * 增加角色
	 * 
	 */
	@Override
	public void addRole(RoleManageModel roleManageModel) throws AppException {
		IMRole role = roleMapper.selectOneByName(roleManageModel.getRoleName());
		if (null != role) {
			// 用户已存在
			throw new AppException(AppErrorCode.E200001);
		}

		IMRole imrole = new IMRole();
		imrole.setRoleId(IDUtils.getTimeRandon());

		imrole.setRoleName(roleManageModel.getRoleName());
		imrole.setStatus("1");

		imrole.setCreateTime(DateUtil.getDateTimestamp());
		imrole.setUpdateTime(DateUtil.getDateTimestamp());

		try {
			roleMapper.insert(imrole);
			if (StringUtils.isNotBlank(roleManageModel.getResourceId())) {
				String[] resourceIds = roleManageModel.getResourceId().split(",");
				for (String resourceid : resourceIds) {
					IMResourceRole resourceRole = new IMResourceRole();
					resourceRole.setRoleId(imrole.getRoleId());
					resourceRole.setResourceId(resourceid);
					resourceRole.setStatus("1");
					resourceRole.setCreateTime(DateUtil.getDateTimestamp());
					resourceRole.setUpdateTime(DateUtil.getDateTimestamp());
					resourceRMapper.insert(resourceRole);
				}
			}
		} catch (Exception e) {
			throw new AppException(SysErrorCode.E100001);
		}
	}

	/**
	 * 获取角色列表
	 * 
	 */
	@Override
	public List<IMRole> queryRoleList() {
		List<IMRole> list = roleMapper.selectAll();
		for (IMRole imRole : list) {

			List<IMResourceRole> resourceRoles = resourceRMapper.selectAllByRoleId(imRole.getRoleId());
			if (resourceRoles != null && resourceRoles.size() > 0) {
				StringBuilder builder = new StringBuilder();
				for (IMResourceRole resourceRole : resourceRoles) {
					builder.append(resourceRole.getResourceId() + ",");
				}
				String ids = builder.toString();
				ids = ids.substring(0, ids.length() - 1);
				imRole.setReSourceIds(ids);
			} else {
				imRole.setReSourceIds("");
			}
		}
		return list;
	}

	/**
	 * 根据角色名获取角色
	 * 
	 */
	@Override
	public IMRole queryRoleByRoleName(String roleName) {
		IMRole role = roleMapper.selectOneByName(roleName);
		return role;
	}

	/**
	 * 更新角色信息
	 * 
	 * @throws AppException
	 * 
	 */
	@Override
	public void updateRole(IMRole role) throws AppException {
		IMRole manager = roleMapper.selectOneByName(role.getRoleName());
		if (manager != null && !role.getRoleId().equals(manager.getRoleId())) {
			// 相同的角色名大于一个，则肯定与其他的一条数据有相同的角色名
			throw new AppException(AppErrorCode.E200001);
		} else {
			// 更改的角色名查找不到相应的数据，或者 角色名没有更改
			role.setUpdateTime(DateUtil.getDateTimestamp());
			roleMapper.update(role);
			resourceRMapper.deleteByRoleId(role.getRoleId());
			if (!TextUtils.isEmpty(role.getReSourceIds())) {
				String[] resourceIds = role.getReSourceIds().split(",");
				int length = resourceIds.length;
				for (int i = 0; i < length; i++) {
					IMResourceRole resourceRole = new IMResourceRole();
					resourceRole.setRoleId(role.getRoleId());
					resourceRole.setResourceId(resourceIds[i]);
					resourceRole.setStatus("1");
					resourceRole.setCreateTime(DateUtil.getDateTimestamp());
					resourceRole.setUpdateTime(DateUtil.getDateTimestamp());
					resourceRMapper.insert(resourceRole);
				}
			}
		}
	}

	/**
	 * 删除角色
	 * 
	 */
	@Override
	public void deleteRole(String roleId) {
		roleMapper.delete(roleId);
		resourceRMapper.deleteByRoleId(roleId);
		userRoleMapper.deleteByRoleId(roleId);
	}

	/**
	 * 批量删除角色
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public void deleteRoleByIds(String[] roleIds) throws Exception {
		try {
			for (String roleId : roleIds) {
				List<IMUserRole> userRole = userRoleMapper.select(roleId);
				if (userRole != null && userRole.size() > 0) {
					throw new AppException(AppErrorCode.E200002);
					// ajaxObj.setMsg("该角色正在使用，无法删除");
				}
				// 删除角色信息
				roleMapper.delete(roleId);
				// 删除角色资源信息
				resourceRMapper.deleteByRoleId(roleId);
				// 删除用户角色信息
				userRoleMapper.deleteByRoleId(roleId);
			}
		} catch (Exception e) {
			logger.error("删除角色信息异常：{}", e);
			throw e;
		}
	}

	/**
	 * 通过角色id获取角色
	 * 
	 */
	@Override
	public IMRole queryRoleByRoleId(String roleId) {
		IMRole role = roleMapper.selectOneById(roleId);
		return role;
	}

	@Override
	public List<IMResourceRole> queryResourceIdById(String roleId) {
		List<IMResourceRole> resourceRoles = resourceRMapper.selectAllByRoleId(roleId);
		return resourceRoles;
	}

	public String getResourceString(String roleid) {
		StringBuilder builder = new StringBuilder();
		List<IMResourceRole> resourceRoles = resourceRMapper.selectAllByRoleId(roleid);
		if (resourceRoles != null && resourceRoles.size() > 0) {

			for (IMResourceRole resourceRole : resourceRoles) {
				builder.append(resourceRole.getResourceId() + ",");
			}
			String ids = builder.toString();
			ids = ids.substring(0, ids.length() - 1);
			return ids.toString();
		} else {
			return "";
		}
	}
	/**
	 * 通过用户id获取角色
	 * 
	 */
	@Override	
	public List<String> getRoreidByuserid(String userid) {
		List<String> roleids = userRoleMapper.selectRoleidByUserid(userid);
		return roleids;
	}
	@Override	
	public List<IMRole> getRolelistByroleids(List<String> roleids) {
		List<IMRole> rolelist = roleMapper.selectRolelistByroleids(roleids);
		for (IMRole imRole : rolelist) {

			List<IMResourceRole> resourceRoles = resourceRMapper.selectAllByRoleId(imRole.getRoleId());
			if (resourceRoles != null && resourceRoles.size() > 0) {
				StringBuilder builder = new StringBuilder();
				for (IMResourceRole resourceRole : resourceRoles) {
					builder.append(resourceRole.getResourceId() + ",");
				}
				String ids = builder.toString();
				ids = ids.substring(0, ids.length() - 1);
				imRole.setReSourceIds(ids);
			} else {
				imRole.setReSourceIds("");
			}
		}
		return rolelist;
	}
}
