/**
 * DefaultAccountMapper.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年1月9日
 */
package cn.com.jansh.mapper.wechat;

import java.util.Map;

import cn.com.jansh.entity.wechat.DefaultAccount;

/**
 * 默认公众号 Mapper
 * 
 * @author gll
 * @version 1.0
 */
public interface DefaultAccountMapper {

	/**
	 * 通过userid查询默认公众号
	 * 
	 * @param userid
	 * @return
	 */
	public DefaultAccount selectByUserid(String userid);

	/**
	 * 更新数据
	 * @param map
	 */
	public void updateDefaultAccount(Map<String, String> map);

	/**
	 * 插入数据
	 * @param map
	 */
	public void insertDefaultAccount(Map<String, String> map);

	/**
	 * 删除数据
	 * @param map
	 */
	public void deleteDefaultAccount(Map<String, String> map);

}
