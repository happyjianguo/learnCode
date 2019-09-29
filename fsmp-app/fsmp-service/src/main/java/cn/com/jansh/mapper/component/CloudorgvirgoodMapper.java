/**
 * CloudorgvirgoodMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月7日
 */
package cn.com.jansh.mapper.component;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.component.CloudorgvirgoodEntity;
import cn.com.jansh.entity.component.bo.CloudorgvirgoodBO;

/**
 * 机构虚拟商品处理接口
 * @author Mr.wong
 * @version 1.0
 */
public interface CloudorgvirgoodMapper {

	/**
	 * 
	 * 插入机构虚拟商品	
	 *	
	 */
	public void insert(CloudorgvirgoodEntity cloudorgvirgoodEntity);
	/**
	 * 
	 * 查询机构虚拟商品	
	 *	
	 */
	public List<CloudorgvirgoodBO> select(Map<String, String> map);
} 
