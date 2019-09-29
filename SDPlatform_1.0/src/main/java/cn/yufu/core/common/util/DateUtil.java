/*
 * �������� 2006-1-19 guozl
 *
 * 
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
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
 * TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class DateUtil {
	static long current = System.currentTimeMillis();
	/**
	 * ͨ��ϵͳʱ����ΨһID
	 * @return
	 */
	static public synchronized long getUniqueIDBySystemTime() {
		return current++;
	}
	/**
	 * ͨ���ꡢ�¡��ջ�����ڼ�
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
	 * ͨ�������գ�2006-1-1��������ڼ�
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