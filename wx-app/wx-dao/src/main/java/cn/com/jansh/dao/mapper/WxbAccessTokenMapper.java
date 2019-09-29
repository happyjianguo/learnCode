package cn.com.jansh.dao.mapper;

import cn.com.jansh.dao.entity.WXBAccessToken;

public interface WxbAccessTokenMapper{

	
	public WXBAccessToken getAccessTokenByAppid(String appid);

	public void insetAccessToken(WXBAccessToken wxbAccessTokenModel);
	
	public void deleteAccessToken(String appid);

	public void updateAccessToken(WXBAccessToken wxbAccessTokenModel);
	
}
