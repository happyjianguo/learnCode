package cn.com.jansh.util;

import java.net.HttpURLConnection;
import java.net.URL;

/**
* 文件名称为：URLAvailability.java
* 文件功能简述： 描述一个URL地址是否有效
*/
public class URLAvailability {
	
	/*所引用的远程对象的连接*/	
	
	private static HttpURLConnection con;
	
	/*响应消息获取状态码*/
	
	private static int state = -1;

    /**
	* 功能：检测当前URL是否可连接或是否有效,
	* 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用
	* @param urlStr 指定URL网络地址
	* @return URL
	*/
	public static boolean isConnect(String urlStr) {
		
		boolean b = false;
		
		int counts = 0;
		
		//判断URL是否为空
		if (urlStr == null || urlStr.length() <= 0) {
			b = false;
		}
		
		//请求失败，最多发送5次请求
		while (counts < 1) {
			
			try {
				
				URL url = new URL(urlStr);
				
				//到 URL 所引用的远程对象的连接
				con = (HttpURLConnection) url.openConnection();
				
				//设置连接主机超时（单位：毫秒）
				con.setConnectTimeout(1000);
				
				
				//设置从主机读取数据超时（单位：毫秒）
		        con.setReadTimeout(1000); 
		        
		        //从 HTTP 响应消息获取状态码
				state = con.getResponseCode();
				
				//判断是否为成功状态码
				if (state == 200) {
					b = true;
				}
				
				break;
				
			} catch (Exception ex) {
				
				counts++;
				
				urlStr = null;
				
				if(counts>=5){
					b = false;
				}
				
				continue;
			}
		}
		return b;
		
	}
	public static void main(String[] args){
		System.out.println(isConnect("http://cs.ftsafe.com/llg_game_dzp?gameid=Q47bgb9tPug7"));
	}
}

