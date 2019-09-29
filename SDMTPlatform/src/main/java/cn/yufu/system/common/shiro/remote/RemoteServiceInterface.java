package cn.yufu.system.common.shiro.remote;

import java.io.Serializable;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.session.Session;

public interface RemoteServiceInterface {

	public Session getSession(String appKey, Serializable sessionId);
	Serializable createSession(Session session);
	public void updateSession(String appKey, Session session);
	public void deleteSession(String appKey, Session session);

	public AuthorizationInfo getPermissions(String appKey, String username);
}
