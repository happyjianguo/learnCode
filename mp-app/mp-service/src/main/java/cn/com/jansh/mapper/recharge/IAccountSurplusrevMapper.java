/**
 * AccountSurplusrevMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月19日
 */
package cn.com.jansh.mapper.recharge;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudaccountsurplusrevEntity;

/**
 * 人工充值数据库操作
 * @author xieliangliang
 * @version 1.0
 */
public interface IAccountSurplusrevMapper {

	/**
	 * 人工充值操作新增数据
	 */
	public void insert(CloudaccountsurplusrevEntity entity);

	/**
	 * 查询所有审核充值记录
	 */
	public List<CloudaccountsurplusrevEntity> queryAll(CloudaccountsurplusrevEntity modelToEntity);

	/**
	 * 根据ID查询审核记录数据
	 */
	public CloudaccountsurplusrevEntity queryById(String id);

	/**
	 * 更新充值审核记录信息
	 */
	public void updateById(CloudaccountsurplusrevEntity entity);
	
}
