/**
 * CommonUtil.java
 * 版权所有(C) 2012 北京坚石诚信科技有限公司 
 * 创建:YangFan 2012-4-16
 */
package cn.com.jansh.util;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

 /**
 * 通用处理工具类
 * @author YangFan
 * @version 1.0.0
 */
public class CommonUtil {
	
	/**
	 * 报文路径解析
	 * @param pathfileName 报文完整路径
	 */
	public static String setFileName(String pathfileName) {
		String path[] = pathfileName.split("/");
		// 获得文件名称
		String fileName = path[path.length - 1];
		// 获得文件路径
		String filePath="";
		for (int i = 0; i < path.length - 1; i++) {
			filePath = filePath + path[i];
			if (i != path.length - 2) {
				filePath = filePath + "/";
			}
		}
		return filePath+","+fileName;
	}
	
	/**
	 * 格式化XML文档,并解决中文问题
	 * @param fileTemname 临时文件名
	 * @param filename 需建立的文件名
	 * @return返回操作结果, 0表失败, 1表成功
	 */
	public static int formatXMLFile(String fileTemname, String filename) {
		int returnValue = 0;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(fileTemname));
			XMLWriter writer = null;
			/** 格式化输出,类型IE浏览一样 */
			OutputFormat format = OutputFormat.createPrettyPrint();
			/** 指定XML编码 */
			format.setEncoding("utf-8");
			writer = new XMLWriter(new FileWriter(new File(filename)), format);
			writer.write(document);
			writer.flush();
			writer.close();
			/** 执行成功,需返回1 */
			returnValue = 1;
		} catch (Exception ex) {
			returnValue = 0;
			ex.printStackTrace();
		}
		return returnValue;
	}
	
	/**
	 * 在将数据存入数据库前转换
	 * 
	 * @param strVal
	 *            要转换的字符串
	 * @return 从“GB2312”到“utf-8”得到的字符串
	 */
	public static String GBtoUTF(String strVal){
		try {
			if (strVal == null) {
				return null;
			} else {
				 strVal = new String(strVal.getBytes("GB2312"), "utf-8");
				return strVal;
			}
		} catch (Exception exp) {
			return null;
		}
	}
	
	/**
	 * 报文类型第二域值
	 * WX.100.001.01
	 * @param MsgType
	 * @return 返回第二域值；如：100
	 */
	public static Integer messageType(String MsgType) {
		if (MsgType.length()>0) {
			String array[] = MsgType.split("\\.");
			return Integer.parseInt(array[1]);
		} else {
			return Integer.parseInt(MsgType);
		}
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
				return value;
			} else {
				return value.substring(valueLonger - longer, valueLonger);// 丢弃之前的几位
			}

		case 'x':// 字符型。不足的之后补半角空格
			if (valueLonger <= longer) {
				return value;
			} else {
				return value.substring(0, longer);// 丢弃之后的
			}
		case 'g':// 字符串型。不动
			return value;
		}
		return null;
	}
	
	/**
	 * 更新报文标识号，通信级标识号长度
	 * @param docID 业务流水号
	 * @param length 修改长度
	 * @return 返回修改长度后的字符串
	 */
	public static String upLengValue(String docID, int length){
		return addLength(docID, length);
	}
	
	/**
	 * 整理字段长度
	 * @param str 原字符串
	 * @param length 整理后的长度
	 * @return 返回整理后的字符串
	 */
	public static String addLength(String str, int length){
		String value = str;
		int leng = value.length();
		if(leng>=length){
			value = str.substring(leng - length, leng);
		}else{
			for(int i=leng; i<length;i++){
				value = "0"+value;
			}
		}
		return value;
	}
	
	/**
	 * 转换节点值，用于拼接加签窜
	 * @param value 数据库存储节点值
	 * /TranChannelType/value
	 * @return 返回过滤后的节点值
	 * value
	 */
	public static String nodeDelValue(String value){
		String result = "";
		if(value.substring(0,1).equals("/")){
			value = value.substring(1);
			String array[] = value.split("/");
			if(array.length>1){
				result = array[1];
			}
		}else{
			result = value;
		}
		
		return result;
	}
	
	/**
	 * 附加域名称
	 * @param value 数据库存储节点值
	 * /TranChannelType/value
	 * @return 返回附加域节点名称
	 * TranChannelType
	 */
	public static String nodeDelValueFist(String value){
		String result = "";
		if(value.substring(0,1).equals("/")){
			value = value.substring(1);
			String array[] = value.split("/");
			if(array.length>1){
				result = array[0];
			}
		}else{
			result = value;
		}
		
		return result;
	}
	
	public static void main(String[] arg){
		
		String MsgType = "NPS.140.001.01";
		int messageType = messageType(MsgType);
		System.out.println("messageType="+messageType);
		
		String tt = nodeDelValue("/TranChannelType/value2313");
		System.out.println("tt="+tt);
		
		
	}
}
