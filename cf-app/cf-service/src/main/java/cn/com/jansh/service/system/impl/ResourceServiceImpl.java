package cn.com.jansh.service.system.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.jansh.entity.system.IMReSource;
import cn.com.jansh.mapper.system.IMResourceMapper;
import cn.com.jansh.service.system.ResourceService;

/**
 * 资源管理业务逻辑层
 * 
 * @author Mr.wong
 */
@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private IMResourceMapper resoureceMapper;

	@Override
	public List<IMReSource> queryAllResource() {
		List<IMReSource> list = resoureceMapper.selectAll();
		return list;
	}

	@Override
	public List<Map<String, String>> queryResourceList(String roleid) {
		if (StringUtils.isBlank(roleid)) {
			return resoureceMapper.selectAllResourceList();
		} else {
			return resoureceMapper.selectResourceList(roleid);
		}
	}
}
