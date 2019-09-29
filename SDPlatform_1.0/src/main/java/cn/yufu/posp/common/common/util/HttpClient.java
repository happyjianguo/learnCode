package cn.yufu.posp.common.common.util;

import java.io.*;
import java.net.*;
import java.util.*;

public class HttpClient {
	private String uri = "http://10.11.252.55:8080/oasms";

	public static void main(String[] args) throws Exception {
		HttpClient httpclient = new HttpClient();
		Map params = new HashMap();

		params.put("mphone", "13521017894");

		params.put("content", "¶ÌÐÅ²âÊÔ12345£¡");
		params.put("begin_date", "20061218161414");
		params.put("expire_date", "20070531171143");

		System.out.print(httpclient.send(params));
		System.out.println(httpclient.statusCode);
		System.out.print(params);
	}

	protected int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public String send(Map params) {
		StringBuffer returnValue = new StringBuffer("");
		HttpURLConnection httpconn = null;
		StringBuffer content = new StringBuffer();
		if (params != null && params.size() > 0) {
			boolean notFirst = false;
			Iterator it = params.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				if (notFirst) {
					content.append("&");
				}
				String v = String.valueOf(e.getValue());
				try {
					v = java.net.URLEncoder.encode(v, "GBK");
				} catch (Exception ex) {

				}
				content.append(e.getKey()).append("=").append(v);
				notFirst = true;
			}
		}
		try {
			URL url = new URL(uri);
			int len = content.length();
			httpconn = (HttpURLConnection) url.openConnection();
			httpconn.setRequestMethod(len == 0 ? "GET" : "POST");
			httpconn.setDoOutput(true);
			httpconn.setDoInput(true);
			if (len > 0) {
				httpconn.setRequestProperty("Content-Length", String.valueOf(len));
				httpconn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				BufferedWriter printwriter = new BufferedWriter(new OutputStreamWriter(httpconn.getOutputStream()));
				printwriter.write(content.toString());
				printwriter.flush();
				printwriter.close();
			}
			String contentType = httpconn.getContentType();
			// Content-Type: text/html;charset=UTF-8
			if (contentType != null) {
				int i = contentType.lastIndexOf("=");
				if (i > 0) {
					contentType = contentType.substring(i + 1);
				} else {
					contentType = "GBK";
				}
			} else {
				contentType = "GBK";
			}
			BufferedReader instream = new BufferedReader(new InputStreamReader(httpconn.getInputStream(), contentType));
			String line;
			while ((line = instream.readLine()) != null) {
				returnValue.append(line).append("\r\n");
			}
			instream.close();
			statusCode = httpconn.getResponseCode();

			httpconn.disconnect();
		} catch (Exception e) {
			statusCode = -1;
			e.printStackTrace();
			returnValue.append("ERR");
		}
		return returnValue.toString();
	}
}
