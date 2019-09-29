package cn.com.jansh.dao.mapper;


import cn.com.jansh.dao.entity.WxbUser;

public interface WxbUserMapper {

	/**
	 * 根据OPENID和SUBSCRIPTTYPE查询用户
	 * IWxbBindingMapper
	 * @param openid 
	 * @return
	 */
	public WxbUser selectByWxbUser(WxbUser user);
	/**
	 * 向用户表插入一条数据
	 */
	public int insertWxbUser(WxbUser wxbUser);
	/**
	 * 绑定及解绑时使用
	 * 根据OPENID更新表数据：SUBSCRIPTTYPE, UPDATETIME 
	 * @param wxbUser
	 * @return
	 */
	public int updateType(WxbUser wxbUser);
	
	/**
	 * 更新表数据
	 * @param wxbUser
	 * @return
	 */
	public int updateWxbUser(WxbUser wxbUser);
	
}