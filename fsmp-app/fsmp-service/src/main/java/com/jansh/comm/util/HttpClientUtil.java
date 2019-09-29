/**
 * HttpClientUtils.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:yangfan 2015-6-9
 */
package com.jansh.comm.util;

import java.nio.charset.Charset;

import com.jansh.core.service.component.HttpClientComponent;

/**
 * http请求工具
 * 
 * @author 聂凤鑫
 * @version 1.0.0
 */
public class HttpClientUtil {

//	private static final Logger logger = LogManager.getLogger(HttpClientUtil.class);

	/**
	 * 
	 * GET请求发送 支持http、https,默认字符集UTF-8
	 * 
	 * @param url
	 * @throws Exception
	 */
	public static String httpGet(String url) throws Exception {
		return new HttpClientComponent().httpGet(url);
	}

	public static String httpGet(String url, Charset charset) throws Exception {
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
	public static String httpGet(String url, HttpClientRequest request) throws Exception {
		return new HttpClientComponent().httpGet(url, request);
	}

	/**
	 * 
	 * POST请求发送 支持http、https
	 * 
	 * @param url
	 * @param data
	 * @throws Exception
	 */
	public static String httpPost(String url, HttpClientRequest request) throws Exception {
		return new HttpClientComponent().httpPost(url, request);
	}

}
