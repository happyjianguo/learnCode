/**
 * HuodongrewardMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月13日
 */
package cn.com.jansh.mapper.data;

import java.util.List;

import cn.com.jansh.entity.system.CloudplatformorginationEntity;

/**
 * 活动获奖情况Mapper
 * @author gll
 * @version 1.0
 */
public interface HuodongrewardMapper {

	/**
	 * 获取所有机构的名称和CODE
	 * @return
	 */
	public List<CloudplatformorginationEntity> queryAllDate();

}
