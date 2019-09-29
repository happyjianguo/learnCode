package com.jansh.core.service.component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientResponse;

/**
 * http请求工具 ,增加http连接池支持
 * 
 * @author 聂凤鑫
 * 
 */
public class HttpClientComponent {

	private static final Logger logger = LogManager.getLogger(HttpClientComponent.class);

	// 创建httpclient连接池
	private PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
	/**
	 * 连接超时时间(ms)
	 */
	private int connectTimeout = 10000;
	/**
	 * 从connect Manager获取Connection超时时间(ms)
	 */
	private int connectionRequestTimeout = 10000;
	/**
	 * 请求获取数据的超时时间(ms)
	 */
	private int socketTimeout = 10000;
	// /**
	// * 连接池最大连接数
	// */
	// private int maxTotal = 100;
	// /**
	// * 连接池单路由最大连接数
	// */
	// private int defaultMaxPerRoute = 20;
	// /**
	// * 验证空闲链接时间(ms)
	// */
	// private int validateAfterInactivity = 180000;

	public HttpClientComponent() {
		httpClientConnectionManager.setMaxTotal(100);
		httpClientConnectionManager.setDefaultMaxPerRoute(20);
		httpClientConnectionManager.setValidateAfterInactivity(18000);
	}

	/**
	 * 
	 * GET请求发送 支持http、https,默认字符集UTF-8
	 * 
	 * @param url
	 * @throws Exception
	 */
	public String httpGet(String url) throws Exception {
		return httpGet(url, new HttpClientRequest());
	}

	/**
	 * GET请求发送 支持http、https
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String httpGet(String url, Charset charset) throws Exception {
		HttpClientRequest request = new HttpClientRequest();
		request.setBodyCharset(charset);
		return httpGet(url, request);
	}

	/**
	 * GET请求发送 支持http、https
	 * 
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String httpGet(String url, HttpClientRequest request) throws Exception {
		HttpClientResponse hcr = httpGetResponse(url, request);
		return hcr.getBody();
	}

	/**
	 * GET请求发送 支持http、https
	 * 
	 * @param url
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public HttpClientResponse httpGetResponse(String url, HttpClientRequest request) throws Exception {
		CloseableHttpClient httpclient = null;
		if (url != null && url.length() >= 5) {
			if ("HTTPS".equals(url.substring(0, 5).toUpperCase())) {
				httpclient = createHttpsClient();
			}
		}
		// http请求
		if (httpclient == null) {
			// httpclient = HttpClientBuilder.create().build();
			// 创建全局的requestConfig
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
					.setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
			httpclient = HttpClients.custom().setConnectionManager(httpClientConnectionManager)
					.setDefaultRequestConfig(requestConfig).build();
		}
		if (request == null) {
			request = new HttpClientRequest();
		}
		return doHttpGet(url, request, httpclient);
	}

	/**
	 * 
	 * POST请求发送 支持http、https
	 * 
	 * @param url
	 * @param data
	 * @throws Exception
	 */
	public String httpPost(String url, HttpClientRequest request) throws Exception {
		HttpClientResponse hcr = httpPostResponse(url, request);
		return hcr.getBody();
	}

	/**
	 * POST请求发送 支持http、https
	 * 
	 * @param url
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public HttpClientResponse httpPostResponse(String url, HttpClientRequest request) throws Exception {
		CloseableHttpClient httpclient = null;
		if (url != null && url.length() >= 5) {
			if ("HTTPS".equals(url.substring(0, 5).toUpperCase())) {
				httpclient = createHttpsClient();
			}
		}
		// http请求
		if (httpclient == null) {
			httpclient = HttpClientBuilder.create().build();
		}
		return doHttpPost(url, request, httpclient);
	}

	/**
	 * 
	 * POST请求发送
	 * 
	 * @param url
	 * @param data
	 * @throws Exception
	 */
	private HttpClientResponse doHttpPost(String url, HttpClientRequest request, CloseableHttpClient httpclient)
			throws Exception {
		HttpClientResponse hcResponse = new HttpClientResponse();
		// String result = "";

		HttpPost httpPost = new HttpPost(url);

		// 设置超时时间
		if (request.getSocketTimeout() > 0) {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(request.getSocketTimeout()).build();
			httpPost.setConfig(requestConfig);
		}

		// 设置headers
		Map<String, String> headers = request.getHeaders();
		if (!(headers == null || headers.isEmpty())) {
			for (Entry<String, String> entry : headers.entrySet()) {
				httpPost.setHeader(entry.getKey(), entry.getValue());
			}
		}

		Charset bodyCharset = request.getBodyCharset();
		if (bodyCharset == null) {
			bodyCharset = StandardCharsets.UTF_8;
		}

		logger.info("http request :" + httpPost.getRequestLine());
		if (request.getBody() != null) {
			logger.info("http request data :" + request.getBody());
			StringEntity reqentity = new StringEntity(request.getBody(), bodyCharset);
			httpPost.setEntity(reqentity);
		}
		if (request.getNameValueList() != null) {
			logger.info("http request NameValueList :" + request.getNameValueList());
			httpPost.setEntity(new UrlEncodedFormEntity(request.getNameValueList()));
		}
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			logger.info(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity resentity = response.getEntity();
				String result = EntityUtils.toString(resentity, bodyCharset);
				logger.info("result:{}", result);
				hcResponse.setBody(result);
				Header[] resheaders = response.getAllHeaders();
				for (Header resheader : resheaders) {
					hcResponse.addHeader(resheader.getName(), resheader.getValue());
				}
			} else {
				logger.error("http error, response status " + response.getStatusLine().getStatusCode());
				// 通讯异常
				throw new Exception("http error, response status " + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			logger.error("doHttpPost error :" + e);
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
		return hcResponse;
	}

	/**
	 * 
	 * GET请求发送
	 * 
	 * @param url
	 * @throws Exception
	 */
	private HttpClientResponse doHttpGet(String url, HttpClientRequest request, CloseableHttpClient httpclient)
			throws Exception {
		HttpClientResponse hcResponse = new HttpClientResponse();
		// String result = "";

		HttpGet httpGet = new HttpGet(url);
		// 设置超时时间
		if (request.getSocketTimeout() > 0) {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(request.getSocketTimeout()).build();
			httpGet.setConfig(requestConfig);
		}

		// 设置headers
		Map<String, String> headers = request.getHeaders();
		if (!(headers == null || headers.isEmpty())) {
			for (Entry<String, String> entry : headers.entrySet()) {
				httpGet.setHeader(entry.getKey(), entry.getValue());
			}
		}

		// 设置字符集
		Charset bodyCharset = request.getBodyCharset();
		if (bodyCharset == null) {
			bodyCharset = StandardCharsets.UTF_8;
		}
		logger.info("http request :" + httpGet.getRequestLine());
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
			logger.info(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity, bodyCharset);
				hcResponse.setBody(result);
				logger.info("result:" + result);
				Header[] resheaders = response.getAllHeaders();
				for (Header resheader : resheaders) {
					hcResponse.addHeader(resheader.getName(), resheader.getValue());
				}
				// 释放资源、关闭流
				EntityUtils.consume(entity);
			} else {
				logger.error("http error, response status " + response.getStatusLine().getStatusCode());
				throw new Exception("http error");
			}
		} catch (Exception e) {
			logger.error("doHttpGet error:" + e);
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
		return hcResponse;
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private CloseableHttpClient createHttpsClient() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
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
			logger.error("createHttpsClient error NoSuchAlgorithmException :" + e);
			throw e;
		} catch (KeyManagementException e) {
			logger.error("createHttpsClient error KeyManagementException :" + e);
			throw e;
		}

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
		// return
		// HttpClients.custom().setConnectionManager(httpClientConnectionManager)
		// .setDefaultRequestConfig(requestConfig).setSSLSocketFactory(sslsf).build();
		return HttpClientBuilder.create().setConnectionManager(httpClientConnectionManager)
				.setConnectionManagerShared(true).setDefaultRequestConfig(requestConfig).setSSLSocketFactory(sslsf)
				.build();
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	/**
	 * 设置连接池最大数量
	 * 
	 * @param maxTotal
	 */
	public void setMaxTotal(int maxTotal) {
		this.httpClientConnectionManager.setMaxTotal(maxTotal);
	}

	/**
	 * 每个路由的最大连接数量
	 * 
	 * @param maxPerRoute
	 */
	public void setDefaultMaxPerRoute(int maxPerRoute) {
		this.httpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
	}

	/**
	 * 验证空闲链接时间(ms)
	 */
	public void setValidateAfterInactivity(int validateAfterInactivity) {
		this.httpClientConnectionManager.setValidateAfterInactivity(validateAfterInactivity);
	}

}
