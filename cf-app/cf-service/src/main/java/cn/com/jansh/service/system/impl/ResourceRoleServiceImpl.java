package cn.com.jansh.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.entity.system.IMReSource;
import cn.com.jansh.entity.system.IMResourceRole;
import cn.com.jansh.mapper.system.IMResourceMapper;
import cn.com.jansh.mapper.system.IMResourceRoleMapper;
import cn.com.jansh.service.system.ResourceRoleService;

/**
 * 角色资源业务
 * 
 *@author MR.wong
 *
 */
@Service
public class ResourceRoleServiceImpl implements ResourceRoleService {
	
	@Autowired
	private IMResourceMapper resourceMapper;
	@Autowired
	private IMResourceRoleMapper resourceRoleMapper;
	
	@Override
	public List<IMReSource> queryResourceByIds(List<IMResourceRole> resourceRoles) {
		List<IMReSource> reSources = new ArrayList<IMReSource>();
		for (IMResourceRole resourceRole : resourceRoles) {
			IMReSource resource = resourceMapper.selectById(resourceRole.getResourceId());
			reSources.add(resource);
		}
		return reSources;
	}
	@Override
	public void addResourceRole(IMResourceRole resourceRole) {
		resourceRoleMapper.insert(resourceRole);
	}
	@Override
	public void addResourceRoles(String roleId, String[] resourceIds) {
		int length = resourceIds.length;
		for (int i = 0; i < length; i++) {
			IMResourceRole resourceRole = new IMResourceRole();
			resourceRole.setRoleId(roleId);
			resourceRole.setResourceId(resourceIds[i]);
			resourceRole.setStatus("1");
			resourceRole.setCreateTime(DateUtil.getDateTime());
			resourceRole.setUpdateTime(DateUtil.getDateTime());
			addResourceRole(resourceRole);
		}
		
	}
	/**
	 *
	 *批量删除资源
	 */
	@Override
	public void deleteResourceRolesByRoleId(String roleId) {
		resourceRoleMapper.deleteByRoleId(roleId);
	}

}
