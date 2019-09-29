package cn.com.jansh.mapper.wsfdn;

import java.util.List;
import java.util.Map;

public interface CzIspProductMapper {

	public String queryordernos(Map<String, String> map);

	/**
	 * 获取流量大小列表
	 * @return
	 */
	public List<String> queryUniquePro();
	/**
	 * 按运营商查询
	 * @return
	 */
	public List<String> queryByIspno(String ispno);
}
