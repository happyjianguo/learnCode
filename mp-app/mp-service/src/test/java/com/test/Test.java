/**
 * Test.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月1日
 */
package com.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.JsonUtil;
import com.jansh.comm.util.StringUtil;


/**
 * @author gll
 * @version 1.0
 */
public class Test {

	public static void main(String[] args) throws Exception{
		String  url="http://192.168.23.160:8081/llg_game_dzp/singleaction/acquireTotalUv";
		Map<String,String> map=new HashMap<String,String>();
		map.put("gameid", "Q23k5EUPRM5W");
		map.put("status", "1");
		map.put("startDate", "20161110132521");
		map.put("endDate", "20161201181600");
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "465";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		String wxStateJson = HttpClientUtil.httpPost(url,request);
		System.out.println(wxStateJson);
		Map<String, Object> newStateMap = JsonUtil.readMapObject(wxStateJson);
		System.out.println(newStateMap.get("errorCode"));
	}
}
