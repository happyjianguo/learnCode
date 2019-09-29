package cn.yufu.system.common.shiro.client;

import java.io.Serializable;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

	protected void doDelete(Session session) {
		this.remoteService.deleteSession(this.appKey, session);
	}

	protected void doUpdate(Session session) {
		this.remoteService.updateSession(this.appKey, session);
	}

	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.remoteService.createSession(session);
		assignSessionId(session, sessionId);
		return sessionId;
	}

	protected Session doReadSession(Serializable sessionId) {
		try {
			trustAllHttpsCertificates();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultHostnameVerifier(this.hv);
		return this.remoteService.getSession(this.appKey, sessionId);
	}

	private static void trustAllHttpsCertificates() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	static class miTM implements TrustManager, X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType)
				throws CertificateException {
		}
	}
}