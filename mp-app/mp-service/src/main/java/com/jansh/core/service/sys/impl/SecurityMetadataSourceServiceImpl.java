package com.jansh.core.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.core.entity.sys.IMMenu;
import com.jansh.core.entity.sys.SysResourceRole;
import com.jansh.core.mapper.SysCoreMapper;
import com.jansh.core.menu.MenuAuths;
import com.jansh.core.service.sys.SecurityMetadataSourceService;

public class SecurityMetadataSourceServiceImpl implements SecurityMetadataSourceService {

	private static final Logger logger = LogManager.getLogger(SecurityMetadataSourceServiceImpl.class);

	@Autowired
	private SysCoreMapper sysCoreMapper;

	/**
	 * 查询所有资源及权限信息
	 * 
	 * @return
	 */
	@Override
	public List<SysResourceRole> queryRoleResource() {
		logger.info("==========> queryRoleResource");
		List<SysResourceRole> resourcesRole = sysCoreMapper.queryRoleResource();
		return resourcesRole;
	}

	@Override
	public List<MenuAuths> queryMenu() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("status", "1");
		List<IMMenu> menuList = sysCoreMapper.selectMenu(param);
		List<MenuAuths> menuAuthsList = new ArrayList<MenuAuths>();
		for (IMMenu menu : menuList) {
			MenuAuths menuAuths = new MenuAuths();
			BeanUtils.copyProperties(menu, menuAuths);
			menuAuthsList.add(menuAuths);
		}
		// BeanUtils.copyProperties(menuList, menuAuthsList);
		return menuAuthsList;
	}

}
