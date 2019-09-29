package cn.com.jansh.utils;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.service.WxAuthService;

public class AccessTokenUtils {
	@Autowired
	private static WxAuthService wxAuthService;	//获取accesstoken接口
	
	public static String getAuthAccessToken(String appid){
		String Auth_access_token = wxAuthService.getAuthAccessToken(appid);
		return Auth_access_token;
	}
}
