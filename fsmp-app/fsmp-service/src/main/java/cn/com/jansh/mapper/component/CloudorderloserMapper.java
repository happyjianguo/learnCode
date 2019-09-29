/**
 * CloudorderloserMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年12月22日
 */
package cn.com.jansh.mapper.component;

import java.util.Map;

import cn.com.jansh.entity.component.CloudorderloserEntity;

/**
 * 失败订单中间表Mapper
 * @author Mr.wong
 * @version 1.0
 */
public interface CloudorderloserMapper {

	/**
	 * 插入订单关系实体
	 * @param cloudorderloserEntity
	 */
	public void insert(CloudorderloserEntity cloudorderloserEntity);
	/**
	 * 查询订单关系实体
	 * @param map
	 * @return
	 */
	public CloudorderloserEntity select(Map<String, String> map);
}
