/**
 * DynamicDefine.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司 
 * 创建:YangFan 2015-5-20
 */
package cn.com.jansh.config;

/**
 * 动态常量定义
 * @author YangFan
 * @version 1.0.0
 */
public class DynamicDefine {
	
	/**
	 * 
	 * 设置请求方式
	 * @param msgcd 报文编号
	 * @return 请求方式
	 */
	public static String getSendMethod(String msgcd){
		String result = "POST";
		if(msgcd.equals("WX.276.001.01")){
			result = "GET";
		}else if(msgcd.equals("WX.284.001.01")){
			result = "POST";
		}else if(msgcd.equals("WX.286.001.01")){
			result = "POST";
		}
		
		return result;
	}
	
	/**
	 * 
	 * 设置报文编号
	 * @param msgtype 消息类型
	 * @param event 事件类型
	 * @return 报文编号
	 */
	public static String getMsgCd(String msgtype, String event){
		String result = "";
		if(msgtype.equals("text")){
			result = "WX.100.001.01";
		}else if(msgtype.equals("event") && event.equals("CLICK")){
			result = "WX.156.001.01";
		}else if(msgtype.equals("event") && event.equals("VIEW")){
			result = "WX.158.001.01";
		}else if(msgtype.equals("event") && event.equals("subscribe")){
			result = "WX.150.001.01";
		}else if(msgtype.equals("event") && event.equals("unsubscribe")){
			result = "WX.152.001.01";
		}
		
		return result;
	}
	
	/**
	 * 
	 * 设置错误码
	 * @param msgtype 消息类型
	 * @param event 事件类型
	 * @return 错误码
	 */
	public static String getErrorCode(String msgtype, String event){
		String result = "";
		if("text".equals(msgtype)){
			result = "COM001";
		}else if("image".equals(msgtype)){
			result = "COM002";
		}else if("voice".equals(msgtype)){
			result = "COM003";
		}else if("video".equals(msgtype)){
			result = "COM004";
		}else if("shortvideo".equals(msgtype)){
			result = "COM005";
		}else if("location".equals(msgtype)){
			result = "COM006";
		}else if("link".equals(msgtype)){
			result = "COM007";
		}else if("event".equals(msgtype) && "CLICK".equals(event)){
			result = "COM011";
		}else if("event".equals(msgtype) && "VIEW".equals(event)){
			result = "COM012";
		}else if("event".equals(msgtype) && "subscribe".equals(event)){
			result = "COM009";
		}else if("event".equals(msgtype) && "unsubscribe".equals(event)){
			result = "COM008";
		}else if("event".equals(msgtype) && "LOCATION".equals(event)){
			result = "COM010";
		}else if("noBindError".equals(msgtype)) {
			result = "COM013";
		}else if("bindError".equals(msgtype)) {
			result = "COM014";
		}else if("cCardNoBind".equals(msgtype)) {
			result = "COM015";
		}
		else{
			result = "COM000";
		}
		
		return result;
	}
}
