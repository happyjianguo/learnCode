package cn.yufu.system.common.shiro.client;

import java.io.Serializable;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import cn.yufu.system.common.shiro.remote.RemoteServiceInterface;




public class ClientSessionDAO extends CachingSessionDAO {
	HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            //System.out.println("Warning: URL Host: " + urlHostName + " vs. "+ session.getPeerHost());
            return true;
        }
    };
	private RemoteServiceInterface remoteService;
	private String appKey;

	public void setRemoteService(RemoteServiceInterface remoteService) {
		this.remoteService = remoteService;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	@Override
	protected void doDelete(Session session) {
		remoteService.deleteSession(appKey, session);
	}

	@Override
	protected void doUpdate(Session session) {
		remoteService.updateSession(appKey, session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = remoteService.createSession(session);
		assignSessionId(session, sessionId);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		try {
			trustAllHttpsCertificates();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
		return remoteService.getSession(appKey, sessionId);
	}
	
	private static void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}

	static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}

}
