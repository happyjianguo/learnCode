/*
 * 创建日期 2006-1-19 guozl
 *
 * 
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package cn.yufu.core.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author guozl
 * 
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class DateUtil {
	static long current = System.currentTimeMillis();
	/**
	 * 通过系统时间获得唯一ID
	 * @return
	 */
	static public synchronized long getUniqueIDBySystemTime() {
		return current++;
	}
	/**
	 * 通过年、月、日获得星期几
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(int year, int month, int date) {
		month = month - 1;
		Calendar newCal = new GregorianCalendar();
		newCal.set(year, month, date);
		int weekday = newCal.get(Calendar.DAY_OF_WEEK);
		if (weekday == 1)
			weekday = 7;
		else
			weekday = weekday - 1;
		return weekday;
	}

	/**
	 * 通过年月日（2006-1-1）获得星期几
	 * @param dt
	 * @return
	 */
	public static int getDayOfWeek(String dt) {
		int day;
		Date dt2 = null;
		try {
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			df.setLenient(false); // this is important!
			dt2 = df.parse(dt.toString());

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid date");
		}
		if (dt2.getDay() == 0)
			day = 7;
		else
			day = dt2.getDay();
		return day;
	}

	

}