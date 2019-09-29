/**
 * IBankAssembleCodeVerify.java
 * 版权所有(C) 2011 深圳市银之杰科技股份有限公司
 * 创建:YangFan 2011-3-8
 */
package cn.com.jansh.ebank.ife.ibank.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.jansh.util.DateUtil;


/**
 * 上传报文封装工具类
 * @author YangFan
 * @version 1.0.0
 */
public class IBankAssembleUpMessage {

	/**
	 * 
	 * 报文体节点封装
	 * @param tagId 节点名称
	 * @param tagFormat 节点格式
	 * @param tagName 节点值名称
	 * @param tradeSet 交易模板数据
	 * @return 返回报文体节点字符串
	 */
	public static String getTallyNodeString(String[] tagId, String[] tagFormat, String[] tagName, TradeSet tradeSet){
		String nodeString = "";
		
		for (int i = 0; i < tagId.length; i++) {
			String dbName = tagId[i];
			String dbNameValue = tradeSet.get(tagName[i])+"";
			
			if(dbNameValue!=null&&dbNameValue.length()>0){
				String valueFormat = formatValue(dbNameValue,tagFormat[i]);
				nodeString += "<" + dbName + ">";
				nodeString += valueFormat;
				nodeString += "</>";
			}
		}
		
		return nodeString;
	}
	
	
	
	
	
	
	/**
	 * 将值按格式字符串格式化后返回
	 * 
	 * @param value
	 *            原值
	 * @param format
	 *            格式字符串
	 * @return
	 */
	public static String formatValue(String value, String format) {
		int longer = Integer.parseInt(format.substring(0, format.length() - 1));
		char type = format.charAt(format.length() - 1);
		int valueLonger = value.length();
		switch (type) {
		case 'd':// 日期型
			// 默认不是10位就是8位，否则出错
			if (longer == 8 && valueLonger == 10) {
				return DateUtil.format10to8(value);
			} else if (longer == 10 && valueLonger == 8) {
				return DateUtil.format(value);
			} else {
				return value;
			}
		case 'n':// 数字型。不足的之前补0
			if (valueLonger <= longer) {
				return getLengthtoString(Integer.parseInt(value),longer);
			} else {
				return value.substring(valueLonger - longer, valueLonger);// 丢弃之前的几位
			}
		case 'x':// 字符型。不足的之后补半角空格
			if (valueLonger <= longer) {
				return getFileName(value,longer);
			} else {
				return value.substring(0, longer);// 丢弃之后的
			}
		case '0':// 不确定型。不动
			return value;
		}
		return null;
	}
	
	/**
	 * 
	 * 报文总长度、报文体长度(8位)
	 * 右对齐、前补零，例如报文总长度 123，则 00000123
	 * @param length 实际长度
	 * @return 返回填充后的八位字符串
	 */
	public static String getLengthString(int length){
		String lengthString = Integer.toString(length);
		
		for(int number=lengthString.length(); number<8; number++){
			lengthString = "0" + lengthString;
		}
		
		return lengthString;
		
	}
	
	/**
	 * 
	 * 数字填充长度，不足的之前补0
	 * @param length
	 * @param allLength
	 * @return 返回填补后的字符串
	 */
	public static String getLengthtoString(int length,int allLength){
		String lengthString = Integer.toString(length);
		
		for(int number=lengthString.length(); number<allLength; number++){
			lengthString = "0" + lengthString;
		}
		
		return lengthString;
		
	}
	

	
	/**
	 * 
	 * 去掉字串前后所有的空格
	 * @param cleanString 待清理的字符串
	 * @return 返回清理所有空格后的字符串
	 */
	public String cleanStringBlank(String cleanString){
		cleanString = cleanString.trim();
		while(cleanString.startsWith(" ")){
			cleanString = cleanString.substring(1,cleanString.length()).trim();
		}
		while(cleanString.endsWith(" ")){
			cleanString = cleanString.substring(0,cleanString.length()-1).trim();
		}

		return cleanString;
		
	}
	
	/**
	 * 
	 * 判断中文个数
	 * @param string 待判断的字符串
	 * @return 返回字符串中中文个数
	 */
	public static int getChineseCount(String string){
		String stringChinese = "";
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FA5]+");
		Matcher matcher = pattern.matcher(string); 
		while(matcher.find()){
			stringChinese += matcher.group(0);
		}
		
		return stringChinese.length();
		
	}
	
	/**
	 * 
	 *  不可见字符（0x00)填充长度
	 * @param fileName 需要填充的字符串
	 * @param allLength 填充后的总长度
	 * @return 返回填充后字符串
	 */
	private static String getFileName(String fileName, int allLength){
		String changeFileName = fileName;
		int a=0x00; 
		char b=(char)a;
		if(fileName.length()>0){
			int nowLength = fileName.length() + getChineseCount(fileName);//现有长度
			for(int i=0; i<allLength-nowLength; i++){
				changeFileName += b;
			}
		}
		
		return changeFileName;
	}
}
