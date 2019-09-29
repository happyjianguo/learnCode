package com.yufupos.system.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yufupos.system.common.service.TreeService;
import com.yufupos.system.common.utils.CacheUtils;
import com.yufupos.system.modules.sys.dao.OfficeDao;
import com.yufupos.system.modules.sys.entity.Office;

@Service
public class OAuthService extends TreeService<OfficeDao, Office> {
	public static final String CODE_CACHE = "codeCache";

	@Autowired
	private SysClientService clientService;

	public void addAuthCode(String authCode, String username) {
		CacheUtils.put(CODE_CACHE, authCode, username);
	}
	public void addAccessToken(String accessToken, String username) {
		CacheUtils.put(CODE_CACHE, accessToken, username);
	}
	public String getUsernameByAuthCode(String authCode) {
		return (String) CacheUtils.get(CODE_CACHE, authCode);
	}
	public String getUsernameByAccessToken(String accessToken) {
		return (String) CacheUtils.get(CODE_CACHE, accessToken);
	}
	public boolean checkAuthCode(String authCode) {
		return CacheUtils.get(CODE_CACHE, authCode) != null;
	}
	public boolean checkAccessToken(String accessToken) {
		return CacheUtils.get(CODE_CACHE, accessToken) != null;
	}
	public boolean checkClientId(String clientId) {
		return clientService.getByClientId(clientId) != null;
	}
	public boolean checkClientSecret(String clientSecret) {
		return clientService.getByClientSecret(clientSecret) != null;
	}
	public long getExpireIn() {
		return 3600L;
	}
}
