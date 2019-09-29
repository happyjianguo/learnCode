/**
 * ClPlatOrgMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月17日
 */
package cn.com.jansh.mapper.system;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.system.CloudplatformorginationEntity;

/**
 * 平台机构处理层
 * @author Mr.wong
 * @version 1.0
 */
public interface ClPlatOrgMapper {

	/**
	 * 获取一个平台机构
	 * @param map
	 * @return
	 */
	public CloudplatformorginationEntity selectOne(Map<String, Object> map);
	/**
	 * 获取多个平台机构
	 * @param map
	 * @return
	 */
	public List<CloudplatformorginationEntity> select(Map<String, Object> map);
}
