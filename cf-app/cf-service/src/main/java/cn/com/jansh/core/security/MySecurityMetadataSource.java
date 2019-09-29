package cn.com.jansh.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import cn.com.jansh.core.entity.sys.IMResourceRoleSEC;
import cn.com.jansh.core.mapper.SysCoreMapper;
import cn.com.jansh.core.menu.MenuAuths;
import cn.com.jansh.core.service.sys.MenuConfig;

/**
 * 加载资源与权限的对应关系
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LogManager.getLogger(MySecurityMetadataSource.class);

	//避免用户每请求一次都要去数据库读取资源、权限
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private PathMatcher pathMatcher = new AntPathMatcher();

	@Autowired
	private SysCoreMapper sysCoreMapper;
	@Autowired
	private MenuConfig menuConfig;

	/**
	 * 加载所有资源与权限的关系
	 * 
	 * 关联代码：UserDetailServiceImpl.obtionGrantedAuthorities
	 * 
	 * 修改角色时需要刷新权限
	 */
	@PostConstruct
	public void loadResourceDefine() {
		
		HashMap<String, Collection<ConfigAttribute>> resourceMapTemp = new HashMap<String, Collection<ConfigAttribute>>();
		
		List<IMResourceRoleSEC> resourcesRole = sysCoreMapper.queryRoleResource();
		for (IMResourceRoleSEC resourceRole : resourcesRole) {
			Collection<ConfigAttribute> configAttributes = resourceMapTemp.get(resourceRole.getResourcekey());
			if (configAttributes == null) {
				configAttributes = new ArrayList<ConfigAttribute>();
				resourceMapTemp.put(resourceRole.getResourcekey(), configAttributes);
			}
			ConfigAttribute configAttribute = new SecurityConfig(resourceRole.getRoleid());
			configAttributes.add(configAttribute);
			// resourceMap.putIfAbsent(resourceRole.getResourcekey(),
			// configAttributes);
		}
		
		resourceMap = resourceMapTemp;
		// 加载菜单权限
		loadMenuAuths();
	}

	/**
	 * 加载菜单权限
	 */
	private void loadMenuAuths() {
		if (resourceMap == null) {
			return;
		}
		// 菜单列表
		menuConfig.loadMenuDefine();
		List<MenuAuths> menuList = menuConfig.getMenuList();
		if (menuList == null) {
			return;
		}
		// 加载菜单权限
		for (MenuAuths menu : menuList) {
			for (Entry<String, Collection<ConfigAttribute>> resource : resourceMap.entrySet()) {
				if (!("#".equals(resource.getKey()) || "#".equals(menu.getMenuurl())) && pathMatcher.match(resource.getKey(), menu.getMenuurl())) {
					menu.setAuths(resource.getValue());
				}
			}
		}
	}

	/**
	 * 返回所请求资源所需要的权限 2015-11-20 21:43 nfx
	 * 
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {

		// object getRequestUrl 是获取用户请求的url地址
		String requestUrl = ((FilterInvocation) object).getRequestUrl();

		if (resourceMap == null) {
			loadResourceDefine();
		}

		if (requestUrl.indexOf("?") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}

		for (Entry<String, Collection<ConfigAttribute>> resource : resourceMap.entrySet()) {
			String url = resource.getKey();
			if (pathMatcher.match(url, requestUrl)) {
				return resource.getValue();
			}
		}
		// 没有配置的资源-设为无权限
		logger.info("RESOURCE_NOT_CONFIG_ERROR,{}", requestUrl);
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		ConfigAttribute configAttribute = new SecurityConfig("RESOURCE_NOT_CONFIG_ERROR");
		configAttributes.add(configAttribute);
		return configAttributes;
//		return null;
	}

	// public Collection<ConfigAttribute> getAttributes(String requestUrl) {
	// if(requestUrl == null || requestUrl.equals("")){
	// return null;
	// }
	//
	// if (requestUrl.indexOf("?") > -1) {
	// requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
	// }
	//
	// for (Entry<String, Collection<ConfigAttribute>> resource :
	// resourceMap.entrySet()) {
	// String url = resource.getKey();
	// if (pathMatcher.match(url, requestUrl)) {
	// return resource.getValue();
	// }
	// }
	// return null;
	// }

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Map<String, Collection<ConfigAttribute>> getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(Map<String, Collection<ConfigAttribute>> resMap) {
		resourceMap = resMap;
	}
}