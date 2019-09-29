/**
 * HttpClientUtils.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:yangfan 2015-6-9
 */
package cn.com.jansh.service.impl.tools;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 被动请求微信报文入口 
 * @author 聂凤鑫
 * @version 1.0.0
 */
public class HttpClientUtils {

	private static final Logger logger = LogManager.getLogger(HttpClientUtils.class);
	
	/**
	 * 
	 * @param url 请求路径
	 * @param data 请求数据
	 * @param method 请求方式
	 * @return	505:通讯异常
	 * @throws Exception
	 */
	public static String sendmsg(String url, String data, String method) throws Exception {
		logger.info("请求微信数据:"+data);
		logger.info("请求微信方式:"+method);
		if ("GET".equalsIgnoreCase(method)) {
			return doHttpGet(url);
		} else if ("POST".equalsIgnoreCase(method)) {
			return doHttpPost(url, data);
		} else {
			logger.error("unsupported method :"+method);
			throw new Exception("unsupported method!");
		}
	}
	
	/**
	 * 
	 * POST请求发送
	 * @param url
	 * @param data
	 * @return 
	 * 			505:通讯异常
	 * @throws Exception
	 */
	private static String doHttpPost(String url, String data) throws Exception {
		String result = "";
		CloseableHttpClient httpclient = createHttpsClient();
		HttpPost httpPost = new HttpPost(url);
		logger.info("http request :"+httpPost.getRequestLine());
		logger.info("http request data :"+data);
		StringEntity reqentity = new StringEntity(data,"UTF-8");
		httpPost.setEntity(reqentity);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			logger.info(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity resentity = response.getEntity();
				result = EntityUtils.toString(resentity, "UTF-8");
				logger.info("result:"+result);
				// EntityUtils.consume(resentity);
			} else {
				//通讯异常
				result = "505";
			}
		} catch (Exception e) {
			logger.error("doHttpPost error :"+e);
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				logger.error(e);
			} finally {
				try {
					if (httpclient != null) {
						httpclient.close();
					}
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * GET请求发送
	 * @param url
	 * @return 505:通讯异常
	 * @throws Exception
	 */
	private static String doHttpGet(String url) throws Exception {
		String result = "";
		CloseableHttpClient httpclient = createHttpsClient();
		HttpGet httpGet = new HttpGet(url);
		logger.info("http request :"+httpGet.getRequestLine());
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
			logger.info(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
				logger.info("result:"+result);
				EntityUtils.consume(entity);
			} else {
				result = "505";
			}
		} catch (Exception e) {
			logger.error("doHttpGet error:"+e);
			throw e;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				logger.error(e);
			} finally {
				try {
					if (httpclient != null) {
						httpclient.close();
					}
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private static CloseableHttpClient createHttpsClient()
			throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { trustManager }, null);
		} catch (NoSuchAlgorithmException e) {
			logger.error("createHttpsClient error NoSuchAlgorithmException :"+e);
			throw e;
		} catch (KeyManagementException e) {
			logger.error("createHttpsClient error KeyManagementException :"+e);
			throw e;
		}
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
}
