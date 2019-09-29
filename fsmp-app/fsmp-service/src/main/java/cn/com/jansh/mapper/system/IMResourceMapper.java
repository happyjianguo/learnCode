package cn.com.jansh.mapper.system;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.system.IMReSource;

public interface IMResourceMapper {

	public IMReSource selectById(String resourceId);

	public List<IMReSource> selectAll();

	public List<Map<String, String>> selectResourceList(String roleid);
	
	public List<Map<String, String>> selectAllResourceList();
}
