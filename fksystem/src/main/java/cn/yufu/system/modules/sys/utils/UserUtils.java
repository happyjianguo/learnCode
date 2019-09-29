/**
 * Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.yufu.system.common.service.BaseService;
import cn.yufu.system.common.shiro.PrincipalBean;
import cn.yufu.system.common.utils.CacheUtils;
import cn.yufu.system.common.utils.SpringContextHolder;
import cn.yufu.system.modules.sys.dao.AreaDao;
import cn.yufu.system.modules.sys.dao.MenuDao;
import cn.yufu.system.modules.sys.dao.OfficeDao;
import cn.yufu.system.modules.sys.dao.RoleDao;
import cn.yufu.system.modules.sys.dao.UserDao;
import cn.yufu.system.modules.sys.entity.Area;
import cn.yufu.system.modules.sys.entity.Menu;
import cn.yufu.system.modules.sys.entity.Office;
import cn.yufu.system.modules.sys.entity.Role;
import cn.yufu.system.modules.sys.entity.User;

/**
 * 用户工具类
 * 
 * @author king
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	public static final String CACHE_SPLIT_ = "_";

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userDao.get(id);
			if (user == null) {
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null) {
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null) {
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		UserUtils.clearCache(getUser());
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(User user) {
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		if (user.getOffice() != null && user.getOffice().getId() != null) {
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}

	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static User getUser() {
		PrincipalBean principal = getPrincipal();
		if (principal != null) {
			User user = get(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * 
	 * @return
	 */
	public static List<Role> getRoleList() {
		User user = getUser();
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>) getCache(user.getId() + CACHE_SPLIT_ + CACHE_ROLE_LIST);
		if (roleList == null) {
			if (user.isAdmin()) {
				roleList = roleDao.findAllList(new Role());
			} else {
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(user.getId() + CACHE_SPLIT_ + CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList() {
		User user = getUser();
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) getCache(user.getId() + CACHE_SPLIT_ + CACHE_MENU_LIST);
		if (menuList == null) {
			if (user.isAdmin()) {
				menuList = menuDao.findAllList(new Menu());
			} else {
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(user.getId() + CACHE_SPLIT_ + CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 获取当前用户授权菜单（接口调用）
	 * 
	 * @return
	 */
	public static List<Menu> getMenuList(String loginName) {
		User user = getByLoginName(loginName);
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) getCache(user.getId() + CACHE_SPLIT_ + CACHE_MENU_LIST);
		if (menuList == null) {
			if (user.isAdmin()) {
				menuList = menuDao.findAllList(new Menu());
			} else {
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(user.getId() + CACHE_SPLIT_ + CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 获取当前用户授权的区域
	 * 
	 * @return
	 */
	public static List<Area> getAreaList() {
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>) getCache(CACHE_AREA_LIST);
		if (areaList == null) {
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * 
	 * @return
	 */
	public static List<Office> getOfficeList() {
		User user = getUser();
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(user.getId() + CACHE_SPLIT_ + CACHE_OFFICE_LIST);
		if (officeList == null) {
			if (user.isAdmin()) {
				officeList = officeDao.findAllList(new Office());
			} else {
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(user.getId() + CACHE_SPLIT_ + CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * 
	 * @return
	 */
	public static List<Office> getOfficeAllList() {
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null) {
			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static PrincipalBean getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			PrincipalBean principal = (PrincipalBean) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			// subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	/**
	 * 获取SESSIONID
	 */
	public static String getSessionId() {
		try {
			return (String) getSession().getId();
		} catch (Exception e) {
			return "";
		}
	}

	// ============== User Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		// Object obj = getCacheMap().get(key);
		// Object obj = getSession().getAttribute(key);
		// return obj==null?defaultValue:obj;
		return CacheUtils.get(USER_CACHE, key);
	}

	public static void putCache(String key, Object value) {
		// getCacheMap().put(key, value);
		// getSession().setAttribute(key, value);
		CacheUtils.put(USER_CACHE, key, value);
	}

	public static void removeCache(String key) {
		// getCacheMap().remove(key);
		// getSession().removeAttribute(key);
		CacheUtils.removeAll(USER_CACHE);
	}

	// public static Map<String, Object> getCacheMap(){
	// Principal principal = getPrincipal();
	// if(principal!=null){
	// return principal.getCacheMap();
	// }
	// return new HashMap<String, Object>();
	// }

}
