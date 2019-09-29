/**
 * DateUtil.java
 * 版权所有(C) 2012 北京坚石诚信科技有限公司 
 * 创建:YangFan 2012-4-16
 */
package cn.com.jansh.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期工具类
 * @author YangFan
 * @version 1.0.0
 */
public class DateUtil {
	/**
	 * 返回当前系统时间
	 * yyyyMMddHHmmssSSS
	 * @return 返回当前系统时间
	 */
	public static String getCurrentDate() {
		return dateToString(new Date());
	}

	public static Long getLongDate(){
		Date newdate = new Date();
		return newdate.getTime();
	}
	
	public static String dateToString(Date dt) {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return myFmt.format(dt);
	}
	
	/**
	 * 返回当前系统时间
	 * yyyy-MM-dd HH:mm:ss
	 * @return 返回当前系统时间
	 */
	public static final String getDate() {
		Date newdate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateformat = sf.format(newdate);
		return dateformat;
	}
	
	/**
	 * 
	 * 返回当前日期
	 * yyyy-MM-dd
	 * @return
	 */
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.SIMPLIFIED_CHINESE);
		return sdf.format(new Date());
	}
	
	/**
	 * 
	 * 返回当前日期
	 * yyyyMMdd
	 * @return
	 */
	public static String getNewDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.SIMPLIFIED_CHINESE);
		return sdf.format(new Date());
	}
	
	/**
	 * 取得yyyyMMddHHmmssSSS格式时间
	 * 返回当前系统时间
	 * @return
	 */
	public String getCurTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(date);
	}
	
	/**
	 * 取得yyyy-MM-dd-HH.mm.ss.SSS000格式时间
	 * 
	 * @return
	 */
	public String getSystemTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS000");
		return sdf.format(date);
	}
	
	/**
	 * 将yyyyMMdd格式的时间字符串转化为yyyy-MM-dd格式
	 * 
	 * @return 失败则返回原字符串
	 */
	public static String format(String dateStr) {
		if (dateStr.length() == 8) {
			return dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
					+ "-" + dateStr.substring(6, 8);
		} else {
			return dateStr;
		}
	}

	/**
	 * 将yyyy-MM-dd格式的时间字符串转化为yyyyMMdd格式
	 * 
	 * @return 失败则返回原字符串
	 */
	public static String format10to8(String dateStr) {
		if (dateStr.length() == 10) {
			return dateStr.replaceAll("\\-", "");
		} else {
			return dateStr;
		}
	}
	
	/**
	 * 
	 *  将HHMMSS格式的时间字符串转化为HH:MM:SS格式
	 * @param time
	 * @return
	 */
	public static String formattime6to8(String time){
		if (time.length() == 6) {
			return time.substring(0,2)+":"+time.substring(2,4)+":"+time.substring(4,6);
		} else {
			return time;
		}
	}
	
	/**
	 * 
	 * 表示日期和时间
	 * 格式为yyyy-mm-ddTHH:MM:SS
	 * @return 返回当前符合格式的系统时间,失败则返回原字符串
	 */
	public static final String formatTDate(String date){
		String value = "";
		Date newdate = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String dateformat = sf.format(newdate);
		if(date.length()>0 && dateformat.length()>0){
			value = format(date)+"T"+dateformat.substring(12);
		}else{
			value = date;
		}
		
		return value;
	}
	
	/**
	 * 比较日期，传入两个日期，第一个日期为targetDate，第二个日期参数为comparedDate，如果targetDate >
	 * comparedDate 返回1，否则返回0，出现错误时返回-1
	 * 
	 * @param targetDate
	 * @param comparedDate
	 * @return
	 */
	public static int compareDates(String targetDate, String comparedDate) {
		int year = Integer.valueOf(targetDate.substring(0, 4));
		int month = Integer.valueOf(targetDate.substring(5, 7));
		int date = Integer.valueOf(targetDate.substring(8, 10));

		Calendar calendarTarget = Calendar.getInstance();
		calendarTarget.set(year, month - 1, date);

		year = Integer.valueOf(comparedDate.substring(0, 4));
		month = Integer.valueOf(comparedDate.substring(5, 7));
		date = Integer.valueOf(comparedDate.substring(8, 10));

		Calendar calendarCompareDate = Calendar.getInstance();
		calendarCompareDate.set(year, month - 1, date);

		if (calendarTarget.after(calendarCompareDate)) {
			return 1;
		} else {
			return 0;
		}
	}
	

	/**
	 * 
	 * 计算日期差
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static long TimeDiff(String date1, String date2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = sdf.parse(date1);
		Date d2 = sdf.parse(date2);
		long n = (d1.getTime() - d2.getTime()) / (60 * 1000);
		return n;
	}
	
	/**
	 * 
	 * 计算历史日期
	 * @param workDate
	 * @return
	 */
	public String getHistoryDate(String workDate) {

		int year = new Integer(workDate.substring(0, 4));
		int month = new Integer(workDate.substring(4, 6));
		int day = new Integer(workDate.substring(6, 8));
		Calendar cal = Calendar.getInstance();
		// 月的索引开始于0
		cal.set(year, month - 1, day);
		cal.add(Calendar.YEAR, -1);
		Date dt = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(dt);

	}
	
	/**
	 * 获取提示付款日期
	 * 
	 * @param docDate
	 * @return
	 * @throws ParseException
	 */
	public static String getPayDay(String docDate) throws ParseException {
		String result = docDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
				Locale.SIMPLIFIED_CHINESE);
		GregorianCalendar gc = new GregorianCalendar();
		Date dt = sdf.parse(result);
		gc.setTime(dt);
		int i = 1;
		int dayOfWeek = 0;
		while (true) {
			if (i == 10) {
				result = sdf.format(gc.getTime());
				break;
			}
			gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
			dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY) {
				i++;
			}
		}
		return result;
	}

	/**
	 * 
	 * 获取当前年
	 * @return
	 */
	public static int getCurrYear() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(ts);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 
	 * 获取当前月
	 * @return
	 */
	public static int getCurrMonth() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(ts);
		return c.get(Calendar.MONTH) + 1;
	}
	 /**
     * 获取当月第一天
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
	 * 
	 * 获取当前日
	 * @return
	 */
	public static int getCurrDay() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(ts);
		return c.get(Calendar.DATE);
	}
	
	public static String getTimeStemp() {
		return String.valueOf(new Date().getTime());
	}
	public static void main(String[] args) {
		System.out.println(DateUtil.getFirstDay());
	}
}
