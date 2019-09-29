package cn.yufu.system.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期处理工具类
 * 
 * @author mengfp
 * 
 */
public class DateUtil {
	// 本地线程变量
	private static final ThreadLocal<Map<String, SimpleDateFormat>> formatsCache = new ThreadLocal<Map<String, SimpleDateFormat>>();
	private static final String DATE_FORMATE_STRING_DEFAULT = "yyyyMMddHHmmss";
	private static final String DATE_FORMATE_STRING_A = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMATE_STRING_B = "yyyy-MM-dd";
	private static final String DATE_FORMATE_STRING_C = "MM/dd/yyyy HH:mm:ss a";
	private static final String DATE_FORMATE_STRING_D = "yyyy-MM-dd HH:mm:ss a";
	private static final String DATE_FORMATE_STRING_E = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static final String DATE_FORMATE_STRING_F = "yyyy-MM-dd'T'HH:mm:ssZ";
	private static final String DATE_FORMATE_STRING_G = "yyyy-MM-dd'T'HH:mm:ssz";
	private static final String DATE_FORMATE_STRING_H = "yyyyMMdd";
	private static final String DATE_FORMATE_STRING_I = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String DATE_FORMATE_STRING_J = "yyyyMMddHHmmss.SSS";

	/**
	 * 初始化
	 */
	public static void initDateFormatCache() {
		Map<String, SimpleDateFormat> formats = new HashMap<String, SimpleDateFormat>();
		formats.put(DATE_FORMATE_STRING_DEFAULT, new SimpleDateFormat(DATE_FORMATE_STRING_DEFAULT));
		formats.put(DATE_FORMATE_STRING_A, new SimpleDateFormat(DATE_FORMATE_STRING_A));
		formats.put(DATE_FORMATE_STRING_B, new SimpleDateFormat(DATE_FORMATE_STRING_B));
		formats.put(DATE_FORMATE_STRING_C, new SimpleDateFormat(DATE_FORMATE_STRING_C));
		formats.put(DATE_FORMATE_STRING_D, new SimpleDateFormat(DATE_FORMATE_STRING_D));
		formats.put(DATE_FORMATE_STRING_E, new SimpleDateFormat(DATE_FORMATE_STRING_E));
		formats.put(DATE_FORMATE_STRING_F, new SimpleDateFormat(DATE_FORMATE_STRING_F));
		formats.put(DATE_FORMATE_STRING_G, new SimpleDateFormat(DATE_FORMATE_STRING_G));
		formats.put(DATE_FORMATE_STRING_H, new SimpleDateFormat(DATE_FORMATE_STRING_H));
		formats.put(DATE_FORMATE_STRING_I, new SimpleDateFormat(DATE_FORMATE_STRING_I));
		formats.put(DATE_FORMATE_STRING_J, new SimpleDateFormat(DATE_FORMATE_STRING_J));
		formatsCache.set(formats);
	}

	/**
	 * 将Date转换为 pattern 格式的字符串，格式为： yyyyMMddHHmmss yyyy-MM-dd HH:mm:ss
	 * yyyy-MM-dd MM/dd/yyyy HH:mm:ss a yyyy-MM-dd HH:mm:ss a
	 * yyyy-MM-dd'T'HH:mm:ss'Z' yyyy-MM-dd'T'HH:mm:ssZ yyyy-MM-dd'T'HH:mm:ssz
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return String --格式化的日期字符串
	 * @see java.util.Date
	 */
	public static String getFormatTimeString(Date date, String pattern) {
		SimpleDateFormat sDateFormat = getDateFormat(pattern);
		// 单实例,SimpleDateFormat非线程安全
		synchronized (sDateFormat) {
			return sDateFormat.format(date);
		}
	}

	/**
	 * 将Date转换为默认的YYYYMMDDHHMMSS 格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDefaultFormateTimeString(Date date) {
		return getFormatTimeString(date, DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 根据pattern取得的date formate
	 * 
	 * @param pattern
	 * @return
	 */
	public static SimpleDateFormat getDateFormat(String pattern) {
		if (formatsCache.get() == null || formatsCache.get().isEmpty()) {
			initDateFormatCache(); // 先初始化本地线程表
		}
		SimpleDateFormat sDateFormat = formatsCache.get().get(pattern);
		if (sDateFormat == null) {
			sDateFormat = new SimpleDateFormat(pattern);
			formatsCache.get().put(pattern, sDateFormat);
		}
		return sDateFormat;
	}

	/**
	 * 将格式将日期字符串转换为Date对象
	 * 
	 * @param date
	 *            字符串
	 * @param pattern
	 *            格式如下： yyyyMMddHHmmss yyyy-MM-dd HH:mm:ss yyyy-MM-dd MM/dd/yyyy
	 *            HH:mm:ss a yyyy-MM-dd HH:mm:ss a yyyy-MM-dd'T'HH:mm:ss'Z'
	 *            yyyy-MM-dd'T'HH:mm:ssZ yyyy-MM-dd'T'HH:mm:ssz
	 * @return 日期Date对象
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static Date getDateFromString(String date, String pattern) throws ParseException {
		SimpleDateFormat sDateFormat = getDateFormat(pattern);
		// 单实例,SimpleDateFormat非线程安全
		synchronized (sDateFormat) {
			return sDateFormat.parse(date);
		}
	}

	/**
	 * 将日期字符串转化成默认格式YYYYMMDDHHMMSS的Date对象
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDefaultDateFromString(String date) throws ParseException {
		return getDateFromString(date, DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 取当前时间,格式为YYYYMMDDHHMMSS的日期字符串
	 * 
	 * @return 当前日期字符串
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static String getNowDefault() {
		return getNow(DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 按照pattern格式取当前日期字符串
	 * 
	 * @param pattern
	 *            日期字符串格式
	 * @return 格式化后的当前日期字符串
	 */
	public static String getNow(String pattern) {
		return getFormatTimeString(new Date(), pattern);
	}

	/**
	 * 取当前时间,格式为YYYYMMDD
	 * 
	 * @return 当前日期字符串
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static String getNowII() {
		return getFormatTimeString(new Date(), DATE_FORMATE_STRING_H);
	}
	/**
	 * 将输入pattern格式的日期字符串转换为取时间的毫秒数 since 1976
	 * 
	 * @return 时间毫秒数
	 * @throws ParseException
	 * @see java.util.Date
	 */
	public static long dateString2Long(String str, String pattern) throws ParseException {
		return getDateFromString(str, pattern).getTime();
	}

	/**
	 * 把since1976的毫秒数转成默认格式yyyyMMddHHmmss的String日期字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String longToDateStringDefault(long time) {
		return getFormatTimeString(new Date(time), DATE_FORMATE_STRING_DEFAULT);
	}

	/**
	 * 将时间的毫秒数 since 1976转换为按照pattern格式的日期字符串
	 * 
	 * @return 日期字符串
	 * @see java.util.Date
	 */
	public static String longToDateString(long time, String pattern) {
		return getFormatTimeString(new Date(time), pattern);
	}

	/**
	 * 将Date对象转成since 1976的毫秒数
	 * 
	 * @param date
	 * @return since1976的毫秒数
	 */
	public static long date2Long(Date date) {
		return date.getTime();
	}

	/**
	 * 将since1976毫秒数转成Date对象
	 * 
	 * @param time
	 * @return
	 */
	public static Date longToDate(long time) {
		return new Date(time);
	}

	/**
	 * 自动适配两种格式的日期字符串转换为date对象 A格式 : yyyy-MM-dd HH:mm:ss B格式 : yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromStringAdaptTwoPattern(String date) throws ParseException {
		try {
			return getDateFromString(date, DATE_FORMATE_STRING_A);
		} catch (ParseException e) {
			return getDateFromString(date, DATE_FORMATE_STRING_B);
		}
	}

	/**
	 * for test
	 * 
	 * @author root
	 *
	 */
	static class Tm extends Thread {
		public String str = null;

		public Tm(String str) {
			this.str = str;
		}

		@Override
		public void run() {
			while (true) {
				Date date = null;
				;
				try {
					date = DateUtil.getDefaultDateFromString(str);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println(DateUtil.getDefaultFormateTimeString(date));
			}
		}
	}

	/**
	 * 取前一天日期
	 * 
	 * @param string
	 * @return
	 */
	public static String getProvDay(String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // 得到前一天
		Date date = calendar.getTime();
		return getFormatTimeString(date, pattern);
	}
	
	/**
	 * 获取指定日期的前一天
	 * @param date
	 * @return
	 */
	public static Date getProvDate(Date date){
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date1 = new Date(calendar.getTimeInMillis());
		return date1;
	} 

	/**
	 * 取前一月日期
	 * 
	 * @param string
	 * @return
	 */
	public static String getLastMonthDay(String pattern) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一月
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
	
	/**
	 * 获取当前时间
	 * @param pattern	时间格式
	 * @return
	 */
	public static String getNowDt(String pattern){
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(new Date());
	}
	
	/**
	 * for test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		Tm tm = new Tm("31111111000000");
//		tm.start();
//		Tm tm2 = new Tm("31111111000001");
//		tm2.start();
//		Tm tm3 = new Tm("31111111000003");
//		tm3.start();
//		System.out.println(getFormatTimeString(new Date(),DATE_FORMATE_STRING_A));
	}
	

}
