package cn.yufu.posp.common.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class DateTimeUtil
{
	public static Date getFirstDayOfMonth(Date d)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.set(Calendar.HOUR_OF_DAY,1);
		gc.set(Calendar.MINUTE,1);
		gc.set(Calendar.SECOND,1);
		gc.set(Calendar.DAY_OF_MONTH,1);
		return gc.getTime();
	}
	
	public static Date getLastDayOfMonth(Date d)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		GregorianCalendar tempGc = new GregorianCalendar();
		int month = gc.get(Calendar.MONTH);
		do
		{
			gc.add(Calendar.DAY_OF_MONTH,1);
		}while(gc.get(Calendar.MONTH)==month);
		gc.add(Calendar.DAY_OF_MONTH,-1);
		return gc.getTime();
	}
	
	public static Date getFirstDayOfNextMonth(Date d)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.MONTH,1);
		return getFirstDayOfMonth(gc.getTime());
	}
	
	public static int getWeekOfMonth(int year,int month)
	{
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getDaysOfMonth(int year,int month)
	{
		int daysOfMonth = 0;
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,1);
		do
		{
			calendar.add(Calendar.DAY_OF_MONTH,1);
			daysOfMonth++;
		}while(calendar.get(Calendar.MONTH)==month-1);
		return daysOfMonth;
	}
	
	
	
	public static void main(String[] args)
	{
		System.out.println(DateTimeUtil.getFirstDayOfMonth(new Date()));
		System.out.println(DateTimeUtil.getLastDayOfMonth(new Date()));
		System.out.println(DateTimeUtil.getFirstDayOfNextMonth(new Date()));
		System.out.println(DateTimeUtil.getWeekOfMonth(2004,7));
		System.out.println(DateTimeUtil.getDaysOfMonth(2006,2));
		System.out.println(new Date().getDate());
	}
}
