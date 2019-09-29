package cn.yufu.system.common.shiro.client;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.yufu.system.common.shiro.PrincipalBean;
import cn.yufu.system.common.shiro.remote.RemoteServiceInterface;



public class ClientRealm extends AuthorizingRealm {
	private RemoteServiceInterface remoteService;
	private String appKey;
	public void setRemoteService(RemoteServiceInterface remoteService) {
		this.remoteService = remoteService;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		PrincipalBean principal = (PrincipalBean) getAvailablePrincipal(principals);
		AuthorizationInfo authorizationInfo = remoteService.getPermissions(appKey, principal.getLoginName());
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// ��Զ���ᱻ����
		throw new UnsupportedOperationException("��Զ���ᱻ����");
	}
}
