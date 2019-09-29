/**
 * TellimitMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.mapper.system;

import cn.com.jansh.entity.system.CloudSecurityLimitEntity;

/**
 * 手机发送频率限制接口
 * @author Mr.wong
 * @version 1.0
 */
public interface CloudSecurityLimitMapper {

	/*插入数据*/
	public void insert(CloudSecurityLimitEntity tellimitEntity);
	/*更新数据*/
	public void update(CloudSecurityLimitEntity tellimitEntity);
	/*获取数据*/
	public CloudSecurityLimitEntity selectByPhone(String phone);
}
