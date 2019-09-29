package cn.yufu.system.common.shiro.remote;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yufu.system.modules.sys.entity.Menu;
import cn.yufu.system.modules.sys.entity.Role;
import cn.yufu.system.modules.sys.utils.UserUtils;

public class RemoteService implements RemoteServiceInterface {

	@Autowired
	private SessionDAO sessionDAO;

	@Override
	public Session getSession(String appKey, Serializable sessionId) {
		return sessionDAO.readSession(sessionId);
	}

	@Override
	public Serializable createSession(Session session) {
		return sessionDAO.create(session);
	}

	@Override
	public void updateSession(String appKey, Session session) {
		sessionDAO.update(session);
	}

	@Override
	public void deleteSession(String appKey, Session session) {
		sessionDAO.delete(session);
	}

	@Override
	public AuthorizationInfo getPermissions(String appKey, String username) {
	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Menu> list = UserUtils.getMenuList(username);
		for (Menu menu : list) {
			if (StringUtils.isNotBlank(menu.getPermission())) {
				// 添加基于Permission的权限信息
				for (String permission : StringUtils.split(menu.getPermission(), ",")) {
					info.addStringPermission(permission);
				}
			}
		}
		// 添加用户权限
		info.addStringPermission("user");
		// 添加用户角色信息
		for (Role role : UserUtils.getByLoginName(username).getRoleList()) {
			info.addRole(role.getEnname());
		}
		return info;
	}
}
