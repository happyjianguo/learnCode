package com.jansh.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.jansh.core.entity.sys.SysResourceRole;
import com.jansh.core.menu.MenuAuths;
import com.jansh.core.security.userdetails.UserDetail;
import com.jansh.core.service.sys.SecurityMetadataSourceService;
import com.jansh.core.web.util.AppUtil;

/**
 * 加载资源与权限的对应关系
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LogManager.getLogger(MySecurityMetadataSource.class);

	private static final String SPRING_SECURITY_METADATA_SOURCE = "SECURITY_METADATA_SOURCE";

	private String appName;

	// 避免用户每请求一次都要去数据库读取资源、权限
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private List<MenuAuths> menuAuthsList = new ArrayList<MenuAuths>();

	private PathMatcher pathMatcher = new AntPathMatcher();

	// @Autowired
	// private MenuConfig menuConfig;

	private SecurityMetadataSourceService sourceService;

	private RedisTemplate<String, Map<String, Collection<ConfigAttribute>>> redisTemplate;

	
	/**
	 * 加载所有资源与权限的关系
	 * 
	 */
	public MySecurityMetadataSource(SecurityMetadataSourceService securityMetadataSourceService) {
		this(securityMetadataSourceService, null, null);
	}
	/**
	 * 加载所有资源与权限的关系
	 * 
	 */
	public MySecurityMetadataSource(SecurityMetadataSourceService securityMetadataSourceService,
			RedisTemplate<String, Map<String, Collection<ConfigAttribute>>> redisTemplate) {
		this(securityMetadataSourceService, redisTemplate, null);
	}
	
	/**
	 * 加载所有资源与权限的关系
	 * 
	 */
	public MySecurityMetadataSource(SecurityMetadataSourceService securityMetadataSourceService,
			RedisTemplate<String, Map<String, Collection<ConfigAttribute>>> redisTemplate, String appName) {
		this.sourceService = securityMetadataSourceService;
		this.redisTemplate = redisTemplate;
		this.appName = appName;
		loadResourceDefine();
	}

	/**
	 * 加载所有资源与权限的关系
	 * 
	 * 关联代码：UserDetailServiceImpl.obtionGrantedAuthorities
	 * 
	 * 修改角色时需要刷新权限
	 */
	public void loadResourceDefine() {

		Map<String, Collection<ConfigAttribute>> resourceMapTemp = new HashMap<String, Collection<ConfigAttribute>>();
		List<SysResourceRole> resourcesRoles = this.sourceService.queryRoleResource();
		for (SysResourceRole rr : resourcesRoles) {
			Collection<ConfigAttribute> configAttributes = resourceMapTemp.putIfAbsent(rr.getResourcekey(),
					new ArrayList<ConfigAttribute>());
			if (configAttributes == null) {
				configAttributes = resourceMapTemp.get(rr.getResourcekey());
			}
			ConfigAttribute configAttribute = new SecurityConfig(rr.getRoleid());
			configAttributes.add(configAttribute);
		}
		resourceMap = resourceMapTemp;
		if (redisTemplate != null) {
			// 权限信息存入redis中
			redisTemplate.opsForHash().putAll(getMetadataSourceKey(), resourceMap);
		}
		// Map<Object, Object> entries =
		// redisTemplate.opsForHash().entries("SECURITY_METADATA_SOURCE");
		// for (Entry sysResourceRole : entries.entrySet()) {
		// logger.info(sysResourceRole.getKey()+"====="+sysResourceRole.getValue());
		// }
		// 加载菜单权限
		loadMenuAuths();
	}

	/**
	 * 加载菜单权限
	 */
	private void loadMenuAuths() {
		Map<String, Collection<ConfigAttribute>> resourceMapTemp = null;
		if (resourceMap != null && !resourceMap.isEmpty()) {
			resourceMapTemp = resourceMap;
		} else if (redisTemplate != null) {
			// 从redis中获取权限信息
			resourceMapTemp = new HashMap<String, Collection<ConfigAttribute>>();
			Map<String, Collection<ConfigAttribute>> redisResourceMap = redisTemplate
					.<String, Collection<ConfigAttribute>>opsForHash().entries(getMetadataSourceKey());
			if (redisResourceMap == null || redisResourceMap.isEmpty()) {
				return;
			} else {
				resourceMapTemp = redisResourceMap;
			}
			// for (Entry<Object, Object> enty : entries.entrySet()) {
			// logger.info(enty.getKey()+"====="+enty.getValue());
			// resourceMapTemp.put(enty.getKey(), enty.getValue());
			// }
		} else {
			return;
		}
		logger.info("资源权限信息，{}", resourceMapTemp);
		// 菜单列表
		List<MenuAuths> menuList = this.sourceService.queryMenu();
		// menuConfig.loadMenuDefine();
		// List<MenuAuths> menuList = menuConfig.getMenuList();
		if (menuList == null) {
			return;
		}
		// 加载菜单权限
		for (MenuAuths menu : menuList) {
			for (Entry<String, Collection<ConfigAttribute>> resource : resourceMapTemp.entrySet()) {
				if (!("#".equals(resource.getKey()) || "#".equals(menu.getMenuurl()))
						&& pathMatcher.match(resource.getKey(), menu.getMenuurl())) {
					menu.setAuths(resource.getValue());
				}
			}
		}
		menuAuthsList = menuList;
	}

	/**
	 * 返回所请求资源所需要的权限 2015-11-20 21:43 nfx
	 * 
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {

		// object getRequestUrl 是获取用户请求的url地址
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		Map<String, Collection<ConfigAttribute>> resourceMapTemp = null;
		if (resourceMap == null || resourceMap.isEmpty()) {
			// 20161101 支持单应用多session权限存储
			// 获取redis存储key
			String metadataSourceKey = "";
			UserDetail userDetail = AppUtil.getUserDetail();
			if (userDetail != null) {
				metadataSourceKey = userDetail.getMetadataSourceKey();
			}
			if (StringUtils.isBlank(metadataSourceKey)) {
				metadataSourceKey = getMetadataSourceKey();
			}
			// 从redis中获取权限信息
			resourceMapTemp = redisTemplate.<String, Collection<ConfigAttribute>>opsForHash().entries(metadataSourceKey);
			if (resourceMapTemp == null || resourceMapTemp.isEmpty()) {
				// redis中没有权限信息，尝试从数据库中重新加载
				loadResourceDefine();
			}

		}

		if (requestUrl.indexOf("?") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		
		if (resourceMapTemp == null || resourceMapTemp.isEmpty()) {
			resourceMapTemp = resourceMap;
		}
		for (Entry<String, Collection<ConfigAttribute>> resource : resourceMapTemp.entrySet()) {
			String url = resource.getKey();
			if (pathMatcher.match(url, requestUrl)) {
				return resource.getValue();
			}
		}
		// 没有配置的资源-设为无权限
		logger.warn("RESOURCE_NOT_CONFIG_ERROR,{}", requestUrl);
		Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		ConfigAttribute configAttribute = new SecurityConfig("RESOURCE_NOT_CONFIG_ERROR");
		configAttributes.add(configAttribute);
		return configAttributes;
		// return null;
	}

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

	public List<MenuAuths> getMenuAuthsList() {
		return menuAuthsList;
	}

	public String getAppName() {
		return appName;
	}

	/**
	 * 返回资源存储key
	 * 
	 * @return
	 */
	public String getMetadataSourceKey() {
		String postAppName = "";
		if (StringUtils.isNotBlank(appName)) {
			postAppName = "_" + appName;
		}
		return SPRING_SECURITY_METADATA_SOURCE + postAppName;
	}
}