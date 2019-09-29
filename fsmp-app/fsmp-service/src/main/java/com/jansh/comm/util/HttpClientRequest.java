package com.jansh.comm.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientRequest {
	/**
	 * 请求header
	 */
	private Map<String, String> headers;
	/**
	 * 请求body
	 */
	private String body;
	/**
	 * form表单数据
	 */
	private List<NameValuePair> nameValueList;
	/**
	 * 字符集，默认UTF-8
	 */
	private Charset bodyCharset;
	/**
	 * 请求获取数据的超时时间(ms)
	 */
	private int socketTimeout;

	public void addHeader(String name, String value) {
		if (headers == null) {
			headers = new HashMap<>();
		}
		this.headers.put(name, value);
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public Charset getBodyCharset() {
		return bodyCharset;
	}

	public void setBodyCharset(Charset bodyCharset) {
		this.bodyCharset = bodyCharset;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public List<NameValuePair> getNameValueList() {
		return nameValueList;
	}

	public void setNameValueList(List<NameValuePair> nameValueList) {
		this.nameValueList = nameValueList;
	}
	
	public void setNameValueMap(Map<String, String> map) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Entry<String, String> nv : map.entrySet()) {
			pairs.add(new BasicNameValuePair(nv.getKey(),nv.getValue()));
		}
		this.nameValueList = pairs;
	}
	
}
