package cn.com.jansh.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static long currentTimeMillis() {
		return getCurrentDate().getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	public static final String getTimestamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(getCurrentDate());
	}

	/**
	 * 将yyyy-MM-dd转为yyyyMMddHHmmss
	 * 
	 * @param time
	 * @return
	 */
	public static final String formatTimestamp(String time) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(parseDate(time));
	}
	/**
	 * 将yyyyMMddHHmmss转为yyyy-MM-dd
	 */
	public static final String formatTimestamp2(String time) {
		return new SimpleDateFormat("yyyy-MM-dd").format(parseDateTime2(time));
	}
	/**
	 * 返回当前系统时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 返回当前系统时间
	 */
	public static final String getDateTime() {
		Date newdate = getCurrentDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateformat = sf.format(newdate);
		return dateformat;
	}

	/**
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		Calendar gcLast = Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();
	}

	/**
	 * 获取本月最后一天
	 * 
	 * @return
	 */
	public static String getLastDay() {
		// 获取Calendar
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();

		Calendar gcLast = Calendar.getInstance();
		// 设置Calendar月份数为下一个月
		gcLast.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		// 设置Calendar日期为下一个月一号
		gcLast.set(Calendar.DATE, 1);
		// 设置Calendar日期减一,为本月末
		gcLast.add(Calendar.DATE, -1);

		String day_last = format.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_last);
		return str.toString();
	}

	/**
	 * 添加秒数
	 * @param date
	 * @return
	 */
	public static Date addOSecond(Date date,int second) {    
	    Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(date);    
	    calendar.add(Calendar.SECOND, second);    
	    return calendar.getTime();    
	}  
	/**
	 * 获取当前天 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static final String getDateDay() {
		Date newdate = getCurrentDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateformat = sf.format(newdate);
		return dateformat;
	}

	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-MM-dd");
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 日期类型转换成字符串类型 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param aDate
	 * @return
	 */
	public static String getDateTime(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	/**
	 * 日期类型转换成字符串类型yyyyMMddHHmmss
	 */
	public static String getDateTime2(Date a1Date) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (a1Date != null) {
			df = new SimpleDateFormat("yyyyMMddHHmmss");
			returnValue = df.format(a1Date);
		}

		return (returnValue);
	}	
	/**
	 * 功能：将yyyy－MM－dd格式的字符串转换为Date类型".
	 */
	public static Date parseDate(String src) {
		return parse(src, "yyyy-MM-dd");
	}

	/**
	 * 功能：将yyyy-MM-dd HH:mm:ss格式的字符串转换为Date类型".
	 */
	public static Date parseDateTime(String src) {
		return parse(src, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 功能：将yyyyMMddHHmmss格式的字符串转换为Date类型".
	 */
	public static Date parseDateTime2(String src) {
		return parse(src, "yyyyMMddHHmmss");
	}
	/**
	 * 字符串转日期格式
	 * 
	 * @param src
	 * @param pattern
	 * @return
	 */
	public static Date parse(String src, String pattern) {
		if (pattern == null)
			throw new IllegalArgumentException("日期格式不能为空");
		if (src == null)
			return null;
		try {
			return new SimpleDateFormat(pattern).parse(src);
		} catch (Exception ex) {
			throw new IllegalArgumentException("日期格式转换出错,src=" + src + ",pattern=" + pattern);
		}
	}
	/** 天数加一   */
	public static String addDate2(String time,int day){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(parseDateTime2(time));
        rightNow.add(Calendar.DAY_OF_YEAR,day);//日期加1天       
        return getDateTime2(rightNow.getTime());
	}
	/**
	 * 
	 * 功能：yyyyMMddHHmmss与取得指定日期相加减的日期 参数：date : 日期 day:相差的天数 如果为正数表示指定日期加上这个天数
	 */
	public static Date addDate(Date date, int day) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.DATE, day);
		return cl.getTime();
	}

	/**
	 * yyyy-MM-dd 增加日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDate(String date, int day) {
		Date myDate = parseDate(date);
		myDate = addDate(myDate, day);
		return getDate(myDate);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss 增加日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String dateTimeAdd(String date, int day) {
		Date myDate = parseDateTime(date);
		myDate = addDate(myDate, day);
		return getDateTime(myDate);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(String startDate, String endDate) {
		long dayNumber = 0;
		long DAY = 24L * 60L * 60L * 1000L;
		Date d1 = parseDate(startDate);
		Date d2 = parseDate(endDate);
		dayNumber = (d2.getTime() - d1.getTime()) / DAY;
		return Integer.parseInt(String.valueOf(dayNumber));
	}

	public static void main(String[] args) {
		// for (int i = 0; 1 < 1000; i++) {
		// System.out.println(new Date().getTime());
		// System.out.println(System.currentTimeMillis());
		// }
//		System.out.println(formatTimestamp(addDate("2015-01-23", 1)));
	}

}
