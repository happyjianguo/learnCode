package com.jansh.comm.util;

import java.util.HashMap;
import java.util.Map;

public class HttpClientResponse {

	private Map<String, String> headers;

	private String body;

	public void addHeader(String name, String value) {
		if (headers == null) {
			headers = new HashMap<>();
		}
		this.headers.put(name, value);
	}

	public String getHeader(String name) {
		if (headers == null) {
			return null;
		}
		return headers.get(name);
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

	public void setBody(String body) {
		this.body = body;
	}
}
