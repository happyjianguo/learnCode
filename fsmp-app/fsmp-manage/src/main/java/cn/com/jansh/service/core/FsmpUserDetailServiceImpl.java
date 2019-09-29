/**
 * FsmpUserDetailServiceImpl.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年11月24日
 */
package cn.com.jansh.service.core;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jansh.core.mapper.SysCoreMapper;
import com.jansh.core.menu.MenuAuths;
import com.jansh.core.menu.MenuUtils;
import com.jansh.core.security.MySecurityMetadataSource;
import com.jansh.core.security.userdetails.UserDetail;

import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.mapper.system.ClPlatOrgMapper;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.service.system.IMUserService;

public class FsmpUserDetailServiceImpl implements UserDetailsService {

		private static final Logger logger = LogManager.getLogger(FsmpUserDetailServiceImpl.class);

		@Autowired
		private IMUserService userService;
		
		@Autowired
		private ClPlatOrgMapper orgMapper;
		
		@Autowired
		private SysCoreMapper sysCoreMapper;
		
		@Autowired
		private IMUserMapper iMUserMapper;
		
		@Autowired
		private MySecurityMetadataSource securityMetadataSource;

		// 登录验证
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

			// 查询用户
			IMUserN user = iMUserMapper.selectNewByUserNameOrPhoneOrEmail(username);
			
			if (user == null || StringUtils.isEmpty(user.getOrgid())) {
				throw new UsernameNotFoundException("User '" + username + "' not found");
			}
			
			// 默认权限
			Collection<GrantedAuthority> auths = obtionGrantedAuthorities(user.getUserid());
			logger.info("user {}, roles {}", username, auths);
			// 封装成spring security的user
			
			
			//判断组织机构状态是否激活
			IMUserN userN = userService.selectNewByUserid(user.getUserid());
			Map<String, Object> map = new HashMap<>();
			map.put("id", userN.getOrgid());
			
		
			CloudplatformorginationEntity orgination = orgMapper.selectOne(map);
			if(orgination !=null&&"0".equals(orgination.getOrgstatus())){
				user.setStatus("0");//用户组织状态为0，将用户对象的状态也改为0
			}
			UserDetail userdetail =new UserDetail(username, user.getPasswd(), "1".equals(user.getStatus()) ? true : false, // 账号状态，0：表示停用；1：表示启用
					true, true, true, auths);// 用户的权限
			userdetail.setMenuList(getUserMenu(securityMetadataSource.getMenuAuthsList(), auths));
			userdetail.setUserid(user.getUserid());
			userdetail.setCnname(user.getCname());
			//userdetail.setFsmpContext("fsmp-manage");
			//资源权限key
			userdetail.setMetadataSourceKey(securityMetadataSource.getMetadataSourceKey());
			
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			logger.debug("fsmp-manage发布名称：{}",request.getContextPath());
			HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("contextPath", request.getContextPath());
//			userdetail.setParamMap(paramMap);
			
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
