/**
 * IBankAssembleDownMessage.java
 * 版权所有(C) 2011 深圳市银之杰科技股份有限公司
 * 创建:YangFan 2011-3-9
 */
package cn.com.jansh.ebank.ife.ibank.common;

/**
 * 下传报文封装工具类
 * @author YangFan
 * @version 1.0.0
 */
public class IBankAssembleDownMessage {
	/**
	 * 
	 * 解析下传报文
	 * 返回格式 ：报文总长度8 + 报文体
	 * @param tradeSet TradeSet实例
	 * @param byteDataDown 下传报文
	 * @return 返回对应格式的字符串
	 */
	public static boolean getMessageDownString(TradeSet tradeSet, byte[] byteDataDown){
		boolean result = false;
		String messageBodyString = "";
		String hostCode = "";
		messageBodyString = new String(byteDataDown).substring(8).trim();
		
		String arrayBody[] = messageBodyString.split("</>");
		for(int bodyNumber=0; bodyNumber<arrayBody.length; bodyNumber++){
			String nodeString = arrayBody[bodyNumber];
			if(nodeString.length()>0){
				//单个节点字符串
				String array[] = nodeString.split(">");
				String name = array[0].substring(1);
				String value = array[1];
				if(name.equals("hostSeqNo") && value.length()>0){
					tradeSet.put("hostSeqNo", value);
				}else if(name.equals("hostCode") && value.length()>0){
					hostCode = value;
					if(value.equals("000000")){
						tradeSet.setTradeResult(true);
					}else{
						tradeSet.setTradeResult(false);
					}
					tradeSet.setHostCode(value);
				}else if(name.equals("hostMsg") && value.length()>0){
					tradeSet.setHostMsg(value);
				}
			}
		}
		
		if(hostCode!=null && hostCode.length()>0){
			result = true;
		}
		return result;
	}
	

	
	
	/**
	 * 
	 * 节点值长度还原
	 * @param messageDown 下传报文
	 * @param key 节点名称
	 * @param length 节点长度
	 * @return 符合长度的字符串
	 */
	public String getNodeValueString(String messageDown, String key, int length){
		String result = "";
		String messageBodyString = messageDown.substring(58).trim();
		String arrayBody[] = messageBodyString.split("</>");
		for(int bodyNumber=0; bodyNumber<arrayBody.length; bodyNumber++){
			String nodeString = arrayBody[bodyNumber];
			if(nodeString.length()>0){
				//单个节点字符串 <RspCode>000000</>
				String array[] = nodeString.split(">");
				String name = array[0].substring(1);
				String value = array[1];
				if(name.equals(key)){
					result = getLengthString(value, length);
					break;
				}
			}
		}
		
		return result;
		
	}
	

	/**
	 * 
	 * 修复字符串长度
	 * @param value 修复值
	 * @param length 修复长度
	 * @return 返回修复长度的字符串
	 */
	public String getLengthString(String value, int length){
		String resultLenStr = value;//返回修复后的字符串
		int lengthNow = value.length();//现有长度
		if(lengthNow >= length){
			resultLenStr = value.substring(0,length);
		}else{
			for(int number=lengthNow; number<length; number++){
				resultLenStr = resultLenStr + " ";
			}
		}
		
		return resultLenStr;
		
	}
	
}
