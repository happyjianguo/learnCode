/**
 * AccountSurplusrevService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月19日
 */
package cn.com.jansh.service.recharge;

import java.util.List;

import cn.com.jansh.entity.recharge.CloudaccountsurplusrevEntity;
import cn.com.jansh.model.recharge.CloudaccountsurplusrevModel;

/**
 * 人工充值接口处理
 * @author xieliangliang
 * @version 1.0
 */
public interface AccountSurplusrevService {
	
	/**
	 * 人工充值插入记录
	 */
	public void insertAmt(CloudaccountsurplusrevEntity entity);

	/**
	 * 查询所有充值审核数据
	 */
	public List<CloudaccountsurplusrevEntity> queryAll(CloudaccountsurplusrevEntity modelToEntity);

	/**
	 * 根据ID查询出审核记录的数据
	 */
	public CloudaccountsurplusrevEntity queryById(String id);

	/**
	 * 根据机构ID更新审核状态信息
	 */
	public void updateByOrgid(CloudaccountsurplusrevEntity entity);

	/**
	 * 充值实现
	 */
	public void recharge(CloudaccountsurplusrevModel cloudaccsupMolde); 

}
