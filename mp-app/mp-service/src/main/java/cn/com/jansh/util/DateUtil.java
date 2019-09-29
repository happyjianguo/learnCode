package cn.com.jansh.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类，用于数据分析-粉丝分布和粉丝关注
 * @author gll
 * @version 1.0
 */
public class DateUtil {
	@SuppressWarnings("deprecation")
	public static String date2IsoDateTimeString(Date date) {
		String isoDateTime = DateFormatUtils.ISO_DATETIME_FORMAT.format(date);
		return isoDateTime;
	}

	@SuppressWarnings("deprecation")
	public static String date2DateTimeString(Date date) {

		return DateFormatUtils.ISO_DATE_FORMAT.format(date) + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(date);
	}

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

	/**
	 * 返回当前系统时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 返回当前系统时间
	 */
	public static final String getDate() {
		Date newdate = getCurrentDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateformat = sf.format(newdate);
		return dateformat;
	}

	/**
	 * 获取当月第一天
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getLongFirstDay() {
		Date date = parseDate(getFirstDay());
		return getDateTime(date);
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
		
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();
		
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
	 * 日期类型转换成字符串类型
	 * yyyy-MM-dd HH:mm:ss
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

	/**
	 * 
	 * 功能：与取得指定日期相加减的日期 参数：date : 日期 day:相差的天数 如果为正数表示指定日期加上这个天数
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
	 * 
	 * 返回当前日期的第前30的日期
	 * yyyy-MM-dd
	 * @return
	 */
	public static String getBegin30Date() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.SIMPLIFIED_CHINESE);
		String nowDate=sdf.format(new Date());
		return addDate(nowDate,-30);
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
}
