package com.pay.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @TODO  日期操作公用类
 * 
 * @author YP
 * @Date 2007-12-17  上午10:02:47
 * @Version 1.0
 */
public class DateUtils{
    private static final DateUtils dateUtils = new DateUtils();

    /**
     * 
     * @TODO 获得日期公用操作类对象实例
     * 
     * @return
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:27:51
     * LastModified
     * History:
     * </pre>
     */
    public static DateUtils getInstance() {
        return dateUtils;
    }

    /**
     * @TODO 格式化日期，返回yyyy-MM-dd格式的字符串
     * 
     * @param date 需要格式化的日期对象
     * @return  yyyy-MM-dd格式的字符串
     * @author YP
     * @version 1.0
     * <pre>
     * Created on :2007-12-17 上午10:02:47
     * LastModified:
     * History:
     * </pre>
     */
    public String format(Date date) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat fmt = new SimpleDateFormat(format);

        return fmt.format(date);
    }

    /**
     * @TODO 根据传入的格式格式化日期对象，并返回格式化后的字符串
     * 
     * @param date 格式化的日期对象
     * @param format 格式化的格式
     * @return  格式化后的字符
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:15:47
     * LastModified
     * History:
     * </pre>
     */
    public String format(Date date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    /**
     * @TODO 根据格式将传入的字符串格式化成日期对象并返回
     *
     * @param date 要格式化的字符串日期
     * @param format  格式化的格式
     * @return 格式化后的日期对象
     * @throws ParseException  格式化异常
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:33:32
     * LastModified
     * History:
     * </pre>
     */
    public Date parse(String date, String format) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat(format);

        return fmt.parse(date);
    }

    /**
     * 
     * @TODO 获得当前日期 格式为yyyy-MM-dd
     *
     * @return 当前日期的字符串表达式 yyyy-MM-dd
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:37:04
     * LastModified
     * History:
     * </pre>
     */
    public String getToday() {
        String result = "";
        Date date = new Date();
        result = this.format(date);
        return result;
    }

    /**
     * 
     * @TODO 获得当前日期 格式为yyyyMMdd
     *
     * @return 当前日期的字符串表达式 yyyyMMdd
     * @author 袁鹏
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:44:30
     * LastModified
     * History:
     * </pre>
     */
    public String getTodayNoSeparate() {
        String result = "";
        Date date = new Date();

        result = this.format(date, "yyyyMMdd");

        return result;   
    }

    /**
     * 
     * @TODO 获得当前日期时间
     *
     * @return  当前日期时间的字符串表达式  格式为yyyy-MM-dd HH:mm:ss
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:40:16
     * LastModified
     * History:
     * </pre>
     */
    public String getTime() {
        String result = "";
        Date date = new Date();

        result = this.format(date, "yyyy-MM-dd HH:mm:ss");

        return result;
    }

    /**
     * 
     * @TODO 获得当前日期时间
     *
     * @return  当前日期时间的字符串表达式  格式为yyyy-MM-dd HH:mm:ss.SSS
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:40:16
     * LastModified
     * History:
     * </pre>
     */
    public String getLogTime() {
        String result = "";
        Date date = new Date();

        result = this.format(date, "yyyy-MM-dd HH:mm:ss.SSS");

        return result;
    }

    /**
     * 
     * @TODO 获得当前日期时间
     *
     * @return  当前日期时间的字符串表达式  格式为yyyyMMddHHmmss
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:40:16
     * LastModified
     * History:
     * </pre>
     */
    public String getTimeNoSeparate() {
        String result = "";
        Date date = new Date();

        result = this.format(date, "yyyyMMddHHmmss");

        return result;
    }
    
    /**
     * 
     * @TODO 获得当前日期时间
     *
     * @return  当前日期时间的字符串表达式  格式为yyyyMMddHH:mm:ss.xxx
     * @author liuxj
     * @version 1.0
     * <pre>
     * Created on:2009-3-30
     * LastModified
     * History:
     * </pre>
     */
    public String getFullTime() {
    	String result = "";
    	Date date = new Date();
    	
    	result = this.format(date, "yyyyMMddHHmmss");
    	
    	return result;
    }
    
    /**
     * 
     * @TODO 获得当前时间
     *
     * @return  当前时间的字符串表达式  格式为HHmmss
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-10-15
     * LastModified
     * History:
     * </pre>
     */
    public String getTimeOnlyNoSeparate() {
    	String result = "";
    	Date date = new Date();
    	
    	result = this.format(date, "HHmmss");
    	
    	return result;
    }

    /**
     * 
     * @TODO 将传入的字符串中的日期格式化(如有空格取空格前的的字符串进行格式化) 格式为yyyy-MM-dd
     *
     * @param str 要格式化的日期
     * @return  格式化后的日期类型
     * @author 袁鹏
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  上午10:50:02
     * LastModified
     * History:
     * </pre>
     * @throws ParseException 格式化异常
     */
    public Date format(String str) throws ParseException {
        if (str == null)
            return null;
        Date result = null;

        str += " ";
        int endStr = str.indexOf(" ");
        String dateString = str.substring(0, endStr);
        result = this.parse(dateString, "yyyy-MM-dd");

        return result;
    }

    /**
     * 
     * @TODO 判断闰年
     *
     * @param y 年份
     * @return 是否为闰年
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  下午02:29:18
     * LastModified
     * History:
     * </pre>
     */
    public static boolean pugYear(int y) {
        boolean re = false;

        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
            re = true;
        } else {
            re = false;
        }
        return re;
    }

    /**
     * 
     * @TODO  根据年月字段来获取月份的天数
     *
     * @param year  年份
     * @param month  月份
     * @return  该月天数
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  下午03:07:04
     * LastModified
     * History:
     * </pre>
     */
    public static int GetMonDays(String year, String month) {
        if (year == null || month == null) {
            return -1;
        }

        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        boolean flag = DateUtils.pugYear(yearInt);

        int mondays = 30;
        if (monthInt == 1 || monthInt == 3 || monthInt == 5 || monthInt == 7 || monthInt == 8
                || monthInt == 10 || monthInt == 12) {
            mondays = 31;
        } else if (monthInt == 2) {
            if (flag) {
                mondays = 28;
            } else {
                mondays = 29;
            }
        } else {
            return -1;
        }

        return mondays;
    }

    /**
     * 
     * @TODO 计算两个日期之间相隔的天数
     *
     * @param date1 日期格式(yyyy-MM-dd)
     * @param date2 日期格式(yyyy-MM-dd)
     * @return (date1 - date2)所的到的天数 
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-26  13:48:54
     * LastModified
     * History:
     * </pre>
     */
    public long compareTwoDate(String date1, String date2) {
        long days = 0;
        Date dateOne;
        try {
            dateOne = DateUtils.getInstance().format(date1);
            Date dateTwo = DateUtils.getInstance().format(date2);
            long time1 = dateOne.getTime();
            long time2 = dateTwo.getTime();
            days = ((time1 - time2) / 1000 / 60 / 60 / 24);
        } catch (ParseException e) {
        }
        return days;
    }

    public String format8To10(String date) {
        StringBuffer sb = new StringBuffer();
        if (date.length() == 8) {
            sb.append(date.substring(0, 4));
            sb.append("年");
            sb.append(date.substring(4, 6));
            sb.append("月");
            sb.append(date.substring(6, 8));
            sb.append("日");
        } else
            return date;

        return StringUtils.outerToInner(new String(sb));
    }


    /**
     * 
     * @TODO 获取上月
     *
     * @return  月份
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-12-01
     * LastModified
     * History:
     * </pre>
     */
	public String getPreviousMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为上月
		str = sdf.format(lastDate.getTime());
		return str;
	}

    /**
     * 
     * @TODO 获得昨日日期 格式为yyyy-MM-dd
     *
     * @return 昨日日期的字符串表达式 yyyy-MM-dd
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-12-10
     * LastModified
     * History:
     * </pre>
     */
    public String getYestoday() {
    	String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.DATE, -1);// 减一天
		str = sdf.format(lastDate.getTime());
		return str;
    }
    
    public String getYestoday(String time) {
    	String str = "";
    	SimpleDateFormat sdf = null;
    	try {
    		if(time.matches("\\d{4}-\\d{2}-\\d{2}"))
    			sdf = new SimpleDateFormat("yyyy-MM-dd");
    		if(time.matches("\\d{8}"))
    			sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar lastDate = Calendar.getInstance();
			lastDate.setTime(sdf.parse(time));
			lastDate.add(Calendar.DAY_OF_MONTH, -1);
			//lastDate.add(Calendar.DATE, -1);// 减一天
			str = sdf.format(lastDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
    }
    
    
    public String getBeforeAfterDateStr(String datestr, int day){
    	SimpleDateFormat sdf = null;
    		if(datestr.matches("\\d{4}-\\d{2}-\\d{2}"))
    			sdf = new SimpleDateFormat("yyyy-MM-dd");
    		if(datestr.matches("\\d{8}"))
    			sdf = new SimpleDateFormat("yyyyMMdd");
    		String time = sdf.format(getBeforeAfterDate(datestr,day));
    		return time;
    }

    /**  
     *   
     * @param datestr 日期字符串  
     * @param day  相对天数，为正数表示之后，为负数表示之前  
     * @return 指定日期字符串n天之前或者之后的日期  
     */  
    public Date getBeforeAfterDate(String datestr, int day) {
    	SimpleDateFormat df  = null;
    	if(datestr.matches("\\d{4}-\\d{2}-\\d{2}"))
    		df = new SimpleDateFormat("yyyy-MM-dd");  
    	if(datestr.matches("\\d{8}"))
			df = new SimpleDateFormat("yyyyMMdd");
        java.sql.Date olddate = null;   
        try {   
            df.setLenient(false);   
            olddate = new java.sql.Date(df.parse(datestr).getTime());   
        } catch (ParseException e) {   
            throw new RuntimeException("日期转换错误");   
        }   
        Calendar cal = new GregorianCalendar();   
        cal.setTime(olddate);   
        int Year = cal.get(Calendar.YEAR);   
        int Month = cal.get(Calendar.MONTH);   
        int Day = cal.get(Calendar.DAY_OF_MONTH);   
  
        int NewDay = Day + day;   
  
        cal.set(Calendar.YEAR, Year);   
        cal.set(Calendar.MONTH, Month);   
        cal.set(Calendar.DAY_OF_MONTH, NewDay);   
  
        return new java.sql.Date(cal.getTimeInMillis());   
    }   

    public static void main(String[] args)throws Exception  {
    	
    	String s = "2012-03-01";
    	System.out.println(new DateUtils().getYestoday(s));
    	
    	/*DateUtils du = new DateUtils();
    	System.out.println(du.getYestoday()+"|"+du.compareTwoDate("1983-01-10","2009-01-10")+"|"+du.compareTwoDate("2003-11-01","2009-04-23")
    			+"|"+du.getBeforeAfterDate("1983-01-10", 10000));
    	
    	Calendar c=Calendar.getInstance();
    	Date d=du.format("2009-12-31");
    	System.out.println(d.getTime());
    	c.setTime(d);
    	c.add(Calendar.DAY_OF_WEEK, 1);
    	System.out.println(du.format(c.getTime()));
    	String s="2009-12-31";
    	if(s.indexOf("-")!=-1){
    		s=s.replaceAll("-", "");
    	}
    	System.out.println();*/
    	
    	
    	
    }
}
