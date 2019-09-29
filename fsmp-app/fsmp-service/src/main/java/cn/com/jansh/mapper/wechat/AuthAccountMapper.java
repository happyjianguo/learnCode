/**
 * AuthAccountMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月18日
 */
package cn.com.jansh.mapper.wechat;


import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.wechat.AuthAccount;


/**
 * @author Mr.wong
 * @version 1.0
 */
public interface AuthAccountMapper {

	/**
	 * 插入授权公众号信息
	 * 
	 */
	public void insert(AuthAccount account);
	/**
	 * 更新授权公众号信息
	 * 
	 */
	public void update(AuthAccount account);
	/**
	 * 更新授权公众号状态
	 * 
	 */
	public void modifyStatus(String appid);
	/**
	 * 通过原始id获取公众号
	 * 
	 */
	public AuthAccount selectOneByAppid(String appid);
	/**
	 * 根据查询条件批量获取公众号
	 * 
	 * @param orgid
	 * @param start
	 * @param length
	 * 
	 */
	public List<AuthAccount> select(Map<String, Object> map);
	/**
	 * 获取公众号数量
	 * @param orgid
	 * @param start
	 * @param length
	 */
	public int selectCount(Map<String, Object> map);
	/**
	 * 删除公众号信息（未使用）
	 * 
	 */
	public void deleteByAppid(String appid);
	/**
	 * 获取全部 公众号
	 * 
	 */
	public List<AuthAccount> selectAll();
	/**
	 * 指定默认公众号
	 * @param map
	 */
//	public void modifyDefault1(Map<String, String> map);
	/**
	 * 指定非默认公众号
	 * @param map
	 */
//	public void modifyDefault0(Map<String, String> map);
	
}
