package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.AuthAccount;

/**
 * 获取accesstoken
 * @author gll
 *
 */
public interface WxbCloudComponentAuthMapper {

	/**
	 * 获取accesstoken
	 * @param appid
	 * @return
	 */
	public AuthAccount getAuthAccessToken(String appid);

	/**
	 * 更新授权公众号信息
	 * 
	 */
	public void update(AuthAccount authAccount);
}
