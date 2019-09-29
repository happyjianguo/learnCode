package com.jansh.core.security.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.mapper.SysCoreMapper;
import com.jansh.core.menu.MenuAuths;
import com.jansh.core.menu.MenuUtils;
import com.jansh.core.security.MySecurityMetadataSource;

public class UserDetailServiceImpl implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger(UserDetailServiceImpl.class);

	@Autowired
	private SysCoreMapper sysCoreMapper;

	@Autowired
	private MySecurityMetadataSource securityMetadataSource;

	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 查询用户
		IMUser user = sysCoreMapper.selectUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		// 默认权限
		Collection<GrantedAuthority> auths = obtionGrantedAuthorities(user.getUserid());
		logger.info("user {}, roles {}", username, auths);
		// 封装成spring security的user
		UserDetail userdetail = new UserDetail(username, user.getPasswd(), "1".equals(user.getStatus()) ? true : false, // 账号状态，0：表示停用；1：表示启用
				true, true, true, auths);// 用户的权限
		userdetail.setMenuList(getUserMenu(securityMetadataSource.getMenuAuthsList(), auths));
		userdetail.setUserid(user.getUserid());
		userdetail.setCnname(user.getCname());
		//资源权限key
		userdetail.setMetadataSourceKey(securityMetadataSource.getMetadataSourceKey());
		
		return userdetail;
	}

	// 取得用户的权限
	private Collection<GrantedAuthority> obtionGrantedAuthorities(String userid) {
		// 查询用户角色
		List<String> roles = sysCoreMapper.selectUserRole(userid);
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			auths.add(new SimpleGrantedAuthority(role));
		}
		return auths;
	}

	/**
	 * 加载菜单
	 * 
	 * @return
	 */
	private List<MenuAuths> getUserMenu(List<MenuAuths> menuList, Collection<GrantedAuthority> auths) {
		// // 过滤有权限的菜单
		List<MenuAuths> userMenuList = new ArrayList<MenuAuths>();
		boolean userflag = false;
		for (MenuAuths menu : menuList) {
			userflag = false;
			if ("#".equals(menu.getMenuurl()) || "".equals(menu.getMenuurl())) {
				// 如果菜单url为#或空时，允许访问
				userMenuList.add(menu);
				continue;
			}
			if (menu.getAuths() == null || menu.getAuths().size() == 0) {
				// 如果菜单没有配置权限，则不允许问
				continue;
			}
			for (ConfigAttribute configAttribute : menu.getAuths()) {
				// // 访问所请求资源所需要的权限
				String needPermission = configAttribute.getAttribute();
				// // 用户所拥有的权限authentication
				for (GrantedAuthority ga : auths) {
					if (needPermission.equals(ga.getAuthority())) {
						userMenuList.add(menu);
						userflag = true;
						break;
					}
				}
				if (userflag) {
					break;
				}
			}
		}
		List<MenuAuths> userMenu = MenuUtils.list2Tree(MenuUtils.cloneMenuList(userMenuList));
		logger.debug("user menu : " + userMenu);
		return userMenu;
	}
}
