package cn.yufu.posp.common.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;


public class DateTimeUtil
{
    /**
     *将当前时间，转换成格式为"yyyy-MM-dd HH:mm:ss"的字符串
     * Return current datetime string.
     * @return current datetime, pattern: "yyyy-MM-dd HH:mm:ss".
     */
    public static String getDateTime()
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dt = sdf.format(new Date());
      return dt;
    }

    /**
     * 按照给定的格式，返回当前事件的字符串.
     * @param pattern String   制订的格式字串，格式应符合SimpleDateFormat支持的内容
     * @return String
     */
    public static String getDateTime(String pattern)
    {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      String dt = sdf.format(new Date());
      return dt;
    }


    /**
     * 按照给定的日期，默认转换成格式 "yyyy-MM-dd" 的字符串.
     * @param date Date
     * @param dateFormat String
     * @return String
     */
    public static String shortFmt(Date date, String dateFormat)
    {
      if (null == dateFormat || "".equals(dateFormat.trim()))
      {
        dateFormat = "yyyy-MM-dd";
      }
      String strDate = "";
      if (date != null)
      {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        strDate = sdf.format(date);
      }
      return strDate;
    }


    /**
     * 当前时间默认转换成格式 "yyyy-MM-dd HH:mm:ss" 的字符串.
     * @param date Timestamp
     * @param dateFormat String
     * @return String
     */

    public static String shortFmt(java.sql.Timestamp date, String dateFormat)
    {
      if (null == dateFormat || "".equals(dateFormat.trim()))
      {
        dateFormat = "yyyy-MM-dd HH:mm:ss";
      }
      String strDate = "";
      if (date != null)
      {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        strDate = sdf.format(date);
      }
      return strDate;
    }

    /**
     * 将传递的字符串转换成Date值.
     * @param param String  格式是: "yyyy-MM-dd HH:mm"、“yyyy-MM-dd”.
     * @return Date
     */
    public static Date parse(String param)
    {
      Date date = null;

      if (param == null)
        return null;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
      try
      {
        date = sdf.parse(param);
      }
      catch (ParseException ex)
      {
        try
        {
          date = sdf2.parse(param);
        }
        catch (ParseException ex2)
        {

        }
      }
      finally
      {
        if (date != null)
        {
          return date;
        }
        else
        {
          return null;
        }
      }
    }

    /**
     * 将传递的字符串转换成Timestamp值.
     * @param param String  格式是: "yyyy-MM-dd HH:mm"、“yyyy-MM-dd”.
     * @return Timestamp
     */
    public static Timestamp parseTimestamp(String param)
    {
      Date date = null;
      Timestamp time = null;
      if (param == null)
        return null;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try
      {
        date = sdf.parse(param);
      }
      catch (ParseException ex)
      {
        try
        {
          date = sdf2.parse(param);
        }
        catch (ParseException ex2)
        {
            
        }
      }
      finally
      {
        if (date != null)
        {
          return new Timestamp(date.getTime());
        }
        else
        {
          return null;
        }
      }
    }
    /**
     * 将传递的字符串转换成Timestamp值.
     * @param param String  格式是: "yyyy-MM-dd HH:mm"、“yyyy-MM-dd”.
     * @return Timestamp
     */
    public static Timestamp parseTimestamp2(String param)
    {
      Date date = null;
      Timestamp time = null;
      if (param == null)
        return null;
      
      SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try
      {
        date = sdf3.parse(param);
      }
      catch (ParseException ex)
      {
      }
      finally
      {
        if (date != null)
        {
          return new Timestamp(date.getTime());
        }
        else
        {
          return null;
        }
      }
    }

    /**
     * * 比较两个给定的Date值的大小.
     * @param date1 Date
     * @param date2 Date
     * @return int 0 date1等于date2 ;1 date1>date2;-1 date1<date2
     */

    public static int compareDatetime(Date date1, Date date2)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	String str1 = sdf.format(date1);
    	String str2 = sdf.format(date2);
    	
    	date1 = parse(str1);
    	date2 = parse(str2);
    	
      int compareResult = 0;

      if (date1 == null || date2 == null)
        return -2;

      long lngTime1 = date1.getTime();
      long lngTme2 = date2.getTime();

      if (lngTime1 == lngTme2)
        return 0;
      else if (lngTime1 > lngTme2)
        return 1;
      else
        return -1;
    }

    /**
        检查时间格式
     * @param time String
     * @param dateFormat String 默认为"yyyy-MM-dd hh:mm:ss"
     * @return boolean
     */
    public static boolean CheckTime(String time, String dateFormat)
    {
      if (null == dateFormat || "".equals(dateFormat.trim()))
      {
        dateFormat = "yyyy-MM-dd hh:mm:ss";
      }
      SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
      try
      {
        sdf.parse(time);
        return true;
      }
      catch (Exception e)
      {
        return false;
      }
    }
	//得到一个月的第一天
	public static Date getFirstDayOfMonth(Date d)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.set(Calendar.HOUR_OF_DAY,0);
		gc.set(Calendar.MINUTE,0);
		gc.set(Calendar.SECOND,0);
		gc.set(Calendar.DAY_OF_MONTH,1);
		
		return gc.getTime();
	}
	
	//得到一个月的最后一天
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
	
	//得到下个月的第一天
	public static Date getFirstDayOfNextMonth(Date d)
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.MONTH,1);
		
		return getFirstDayOfMonth(gc.getTime());
	}
	
	//得到一个月的第一天是周几
	public static int getWeekOfMonth(int year,int month)
	{
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,1);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	//得到一个月的天数
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
	
	//得到当天0点0分0时
	public static  Date getCurrentDayBegin(Date d)
	{	
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(d);
		gCalendar.set(Calendar.HOUR_OF_DAY,0);
		gCalendar.set(Calendar.MINUTE,0);
		gCalendar.set(Calendar.SECOND,0);
		
		return gCalendar.getTime();
	}
	
	//得到下一天的0点0分0时
	public static Date getNextDayBegin(Date d)
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(d);
		gCalendar.set(Calendar.HOUR_OF_DAY,0);
		gCalendar.set(Calendar.MINUTE,0);
		gCalendar.set(Calendar.SECOND,0);
		gCalendar.add(Calendar.DAY_OF_MONTH,1);
		
		return gCalendar.getTime();
	}
    
   //得到当前年第一天的日期
    public static Date getFirstDayOfYear()
    {
        Date date = new Date();
        Date da = parse((date.getYear() + 1900) + "-01-01");
        return da;
    }
    
    
    /**
     * 根据日期得到当前周的第一天的０点０分０秒
     */
    public static Date getCurrentWeekFirstDay(Date d)
    {
    	//得到当前天
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(d);
    	gc.set(Calendar.HOUR_OF_DAY,0);
		gc.set(Calendar.MINUTE,0);
		gc.set(Calendar.SECOND,0);
		
		//得到当前天是本周中的第几天，周日是第一天，周一是第二天
		int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
		
		int addDay = 2 - dayOfWeek;
		if(addDay == 1)
		{
			addDay = -6;
		}
		
		gc.add(Calendar.DAY_OF_MONTH,addDay);
		
    	return gc.getTime();
    }
    
	
    /**
     * 根据日期得到当前周的最后一天的０点０分０秒
     */
    public static Date getCurrentWeekLastDay(Date d)
    {
    	Date date = null;
    	
    	Date firstDay = getCurrentWeekFirstDay(d);
    	
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(firstDay);
    	gc.add(Calendar.DAY_OF_MONTH,6);
    	
    	date = gc.getTime();
    	
    	return date;
    	
    }
    
    /**
     * 根据日期得到下一周的第一天的０点０分０秒
     */
    public static Date getNextWeekFirstDay(Date d)
    {
    	Date date = null;
    	
    	Date firstDay = getCurrentWeekFirstDay(d);
    	
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(firstDay);
    	gc.add(Calendar.DAY_OF_MONTH,7);
    	
    	date = gc.getTime();
    	
    	return date;
    	
    }
    
    /**
     * 根据日期得到七天后的日期的第一天的０点０分０秒
     */
    public static Date getNextWeek(Date d)
    {
        Date date = null;
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        gc.add(Calendar.DAY_OF_MONTH,7);
        
        date = gc.getTime();
        
        return date;
        
    }
    /**
     * 根据日期得到'i'天后的日期的０点０分０秒
     */
    public static Date getNextDay(int i,Date d)
    {
        Date date = null;
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        gc.add(Calendar.DAY_OF_MONTH,i);
        
        date = gc.getTime();
        
        return date;
        
    }
    /**
     * 根据日期得到下下周的第一天的０点０分０秒
     */
    public static Date getNextNextWeekFirstDay(Date d)
    {
        Date date = null;
        
        Date firstDay = getCurrentWeekFirstDay(d);
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(firstDay);
        gc.add(Calendar.DAY_OF_MONTH,14);
        
        date = gc.getTime();
        
        return date;
        
    }
	
    //得到季度的第一天
    public static String getFirstDayOfQuarter(int y,int m,int d)
    {
        GregorianCalendar gc = new GregorianCalendar(y,m,d,0,0,0);
        String date = shortFmt(gc.getTime(),"yyyy-MM-dd HH:mm:ss");
        return date;
    }
    
//  得到一个月的第一天
    public static String getFirstDayOfMonth(int y,int m,int d)
    {
        GregorianCalendar gc = new GregorianCalendar(y,m,d,0,0,0);
        String date = shortFmt(gc.getTime(),"yyyy-MM-dd HH:mm:ss");
        return date;
    }
    
   
    
    //得到下个月的第一天
    public static String getFirstDayOfNextMonth(int y,int m,int d)
    {
        GregorianCalendar gc = new GregorianCalendar(y,m,d,0,0,0);
        String date = shortFmt(gc.getTime(),"yyyy-MM-dd HH:mm:ss");
        return date;
    }
    public static Date getDelayDayOfTime(Date newDate,int days){
    	Calendar gc = Calendar.getInstance();
    	gc.setTime(newDate);
    	gc.add(Calendar.DATE,days);    	
    	return gc.getTime();
    }
    
    /***/
    public static Date getMonth(Date newDate,int month){
    	Calendar gc = Calendar.getInstance();
    	gc.setTime(newDate);
    	gc.add(Calendar.MONTH,month);    	
    	return gc.getTime();
    }
    
    //得到"xxxx年xx月xx日"的 中国日期  形式"
    public static String getChineseDate(Date d)
    {
    	 String s4="";
		try {
			String s=DateTimeUtil.shortFmt(d,"");
			 
			 String s1=s.substring(0,4)+"年";
			 
			 String s2 = s.substring(5,7)+"月";
			 if (s2.substring(0,1).equals("0"))
				 s2=s2.substring(1);
			 String s3=s.substring(8,10)+"日";
			 if (s3.substring(0,1).equals("0"))
				 s3=s3.substring(1);
			 s4=s1+s2+s3;
			 
		} catch (RuntimeException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    	 
    	 return s4;
    }
    //得到"xxxx年xx月"的 中国日期  形式"
    public static String getChineseMonth(Date d)
    {
    	 String s4="";
		try {
			String s=DateTimeUtil.shortFmt(d,"");
			 
			 String s1=s.substring(0,4)+"年";
			 
			 String s2 = s.substring(5,7)+"月";
			 if (s2.substring(0,1).equals("0"))
				 s2=s2.substring(1);
			 s4=s1+s2;
			 
		} catch (RuntimeException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    	 
    	 return s4;
    } 
	public static void main(String[] args)
	{
		/*System.out.println(DateTimeUtil.getFirstDayOfMonth(new Date()));
		System.out.println(DateTimeUtil.getLastDayOfMonth(new Date()));
		System.out.println(DateTimeUtil.getFirstDayOfNextMonth(new Date()));
		System.out.println(DateTimeUtil.getWeekOfMonth(2004,7));
		System.out.println(DateTimeUtil.getDaysOfMonth(2006,2));
		System.out.println(new Date().getDate());*/
		
		/*GregorianCalendar gc = new GregorianCalendar(2006,4-1,16);
		System.out.println(getCurrentWeekFirstDay(gc.getTime()));*/
		
		Date date = null;
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
	      try
	      {
	    	  date = sdf.parse("20080806");
	      }
	      catch (ParseException ex)
	      {
	    	  
	      }
	      
	      
	     Date date2 = getMonth(date,11);
        
        System.out.println("--------date2--------" + shortFmt(date2,"yyMM"));
        
      /*  java.sql.Timestamp time = parseTimestamp2("2006-5-10 17:38:39");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
        
        
        
	}
	
}
