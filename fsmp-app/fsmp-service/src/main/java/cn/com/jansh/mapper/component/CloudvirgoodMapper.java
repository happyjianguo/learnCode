/**
 * CloudvirgoodMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月21日
 */
package cn.com.jansh.mapper.component;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.jansh.entity.component.CloudvirgoodEntity;

/**
 * 商品信息持久层处理接口
 * @author duanmuyn
 * @version 1.0
 */
public interface CloudvirgoodMapper {

	/**
	 * 查询商品
	 * @param cloudvirgood
	 */
	public List<CloudvirgoodEntity> selectStatusOk();
	/**
	 * 查询商品
	 * @param cloudvirgood
	 */
	public CloudvirgoodEntity selectStatusOkByapid(@Param("id") String apid,@Param("status") String status);
	/**
	 * 添加商品
	 * @param cloudvirgood
	 */
	public void insert(CloudvirgoodEntity cloudvirgood);
	/**
	 * 批量添加
	 * @param list
	 */
	public void insertList(List<CloudvirgoodEntity> list);
	/**
	 * 更新商品
	 * @param cloudvirgood
	 */
	public void update(CloudvirgoodEntity cloudvirgood);
	/**
	 * 批量更新
	 * @param list
	 */
	public void updateList(List<CloudvirgoodEntity> list);
	
	/**
	 * 删除商品
	 * @param cloudvirgood
	 */
	public void delete();
}
