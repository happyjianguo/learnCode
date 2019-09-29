/**
 * DateUtils.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月14日
 */
package cn.com.jansh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换
 * @author gll
 * @version 1.0
 */
public class DateUtils {
	public static void main(String[] args){
		
	}
	/**
	   * 获取现在时间
	   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	   */
	public static String getNowDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   return formatter.format(currentTime);
	}
	/**
	 * 获取现在时间
	 * @return 返回时间类型yyyy-MM-dd
	 */
	public static String getNowDate2() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(currentTime);
	}
	/**
	   * 获取现在时间
	   * @return 返回时间类型 yyyyMMddHHmmss
	   */
	public static String getStringToday() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	   return formatter.format(currentTime);
	}
	/**
	   * 将长时间格式字符串 yyyy-MM-dd HH:mm:ss转换为Date
	   * @param strDate
	   * @return
	   * @throws ParseException 
	   */
	public static Date strToDateLong(String strDate) throws ParseException {
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date strtodate = formatter.parse(strDate);
	   return strtodate;
	}
	/**
	   * 将长时间格式字符串 yyyyMMddHHmmss转换为Date
	   * @param strDate
	   * @return
	   * @throws ParseException 
	   */
	public static Date strToDateLong2(String strDate) throws ParseException {
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	   Date strtodate = formatter.parse(strDate);
	   return strtodate;
	}
	/**
	 * 将长时间格式字符串 yyyy-MM-dd转换为Date
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Date strToDateLong3(String strDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date strtodate = formatter.parse(strDate);
		return strtodate;
	}
	/**
	 * 将Date转换成短时间格式yyyy-MM-dd
	 * @param s1
	 * @return
	 */
	public static String parseShortDate(Date Time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(Time);
	}
	/**
	 * 
	 * @return Date yyyyMMddHHmmss
	 */
	public static String getStringToday(Date Time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(Time);
		return dateString;
	}
	/**
	 * 
	 * @return Date yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringToday2(Date Time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(Time);
		return dateString;
	}
	

}
