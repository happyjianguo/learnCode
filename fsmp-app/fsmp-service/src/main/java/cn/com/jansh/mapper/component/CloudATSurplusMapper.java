/**
 * CloudATSurplusMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月27日
 */
package cn.com.jansh.mapper.component;

import java.util.Map;

import cn.com.jansh.entity.component.CloudATSurplusEntitiy;

/**
 * 账户余额处理接口
 * @author Mr.wong
 * @version 1.0
 */
public interface CloudATSurplusMapper {

	/**
	 * 插入账户信息
	 */ 
	public void insert(CloudATSurplusEntitiy atSurplusEntitiy);
	/**
	 * 更新账户信息
	 */ 
	public void update(CloudATSurplusEntitiy atSurplusEntitiy);
	/**
	 * 查询账户信息
	 */ 
	public CloudATSurplusEntitiy selectByOrgid (String orderid);
	/**
	 * 更新账户余额
	 */ 
	public void updateBalance(Map<String, Object> map);
	
}
