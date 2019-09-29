package cn.com.jansh.service.system;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.system.IMReSource;

/**
 *@author Mr.wong
 *
 */
public interface ResourceService {
	
	/**
	 *获取所有的资源
	 */
	public List<IMReSource> queryAllResource();

	public List<Map<String, String>> queryResourceList(String roleid);
 }
