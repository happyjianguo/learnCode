package com.pay.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @TODO  ���ڲ���������
 * 
 * @author YP
 * @Date 2007-12-17  ����10:02:47
 * @Version 1.0
 */
public class DateUtils{
    private static final DateUtils dateUtils = new DateUtils();

    /**
     * 
     * @TODO ������ڹ��ò��������ʵ��
     * 
     * @return
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:27:51
     * LastModified
     * History:
     * </pre>
     */
    public static DateUtils getInstance() {
        return dateUtils;
    }

    /**
     * @TODO ��ʽ�����ڣ�����yyyy-MM-dd��ʽ���ַ���
     * 
     * @param date ��Ҫ��ʽ�������ڶ���
     * @return  yyyy-MM-dd��ʽ���ַ���
     * @author YP
     * @version 1.0
     * <pre>
     * Created on :2007-12-17 ����10:02:47
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
     * @TODO ���ݴ���ĸ�ʽ��ʽ�����ڶ��󣬲����ظ�ʽ������ַ���
     * 
     * @param date ��ʽ�������ڶ���
     * @param format ��ʽ���ĸ�ʽ
     * @return  ��ʽ������ַ�
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:15:47
     * LastModified
     * History:
     * </pre>
     */
    public String format(Date date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    /**
     * @TODO ���ݸ�ʽ��������ַ�����ʽ�������ڶ��󲢷���
     *
     * @param date Ҫ��ʽ�����ַ�������
     * @param format  ��ʽ���ĸ�ʽ
     * @return ��ʽ��������ڶ���
     * @throws ParseException  ��ʽ���쳣
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:33:32
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
     * @TODO ��õ�ǰ���� ��ʽΪyyyy-MM-dd
     *
     * @return ��ǰ���ڵ��ַ������ʽ yyyy-MM-dd
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:37:04
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
     * @TODO ��õ�ǰ���� ��ʽΪyyyyMMdd
     *
     * @return ��ǰ���ڵ��ַ������ʽ yyyyMMdd
     * @author Ԭ��
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:44:30
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
     * @TODO ��õ�ǰ����ʱ��
     *
     * @return  ��ǰ����ʱ����ַ������ʽ  ��ʽΪyyyy-MM-dd HH:mm:ss
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:40:16
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
     * @TODO ��õ�ǰ����ʱ��
     *
     * @return  ��ǰ����ʱ����ַ������ʽ  ��ʽΪyyyy-MM-dd HH:mm:ss.SSS
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:40:16
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
     * @TODO ��õ�ǰ����ʱ��
     *
     * @return  ��ǰ����ʱ����ַ������ʽ  ��ʽΪyyyyMMddHHmmss
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:40:16
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
     * @TODO ��õ�ǰ����ʱ��
     *
     * @return  ��ǰ����ʱ����ַ������ʽ  ��ʽΪyyyyMMddHH:mm:ss.xxx
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
     * @TODO ��õ�ǰʱ��
     *
     * @return  ��ǰʱ����ַ������ʽ  ��ʽΪHHmmss
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
     * @TODO ��������ַ����е����ڸ�ʽ��(���пո�ȡ�ո�ǰ�ĵ��ַ������и�ʽ��) ��ʽΪyyyy-MM-dd
     *
     * @param str Ҫ��ʽ��������
     * @return  ��ʽ�������������
     * @author Ԭ��
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����10:50:02
     * LastModified
     * History:
     * </pre>
     * @throws ParseException ��ʽ���쳣
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
     * @TODO �ж�����
     *
     * @param y ���
     * @return �Ƿ�Ϊ����
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����02:29:18
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
     * @TODO  ���������ֶ�����ȡ�·ݵ�����
     *
     * @param year  ���
     * @param month  �·�
     * @return  ��������
     * @author YP
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  ����03:07:04
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
     * @TODO ������������֮�����������
     *
     * @param date1 ���ڸ�ʽ(yyyy-MM-dd)
     * @param date2 ���ڸ�ʽ(yyyy-MM-dd)
     * @return (date1 - date2)���ĵ������� 
     * @author �Ʊ�
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
            sb.append("��");
            sb.append(date.substring(4, 6));
            sb.append("��");
            sb.append(date.substring(6, 8));
            sb.append("��");
        } else
            return date;

        return StringUtils.outerToInner(new String(sb));
    }


    /**
     * 
     * @TODO ��ȡ����
     *
     * @return  �·�
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
		lastDate.add(Calendar.MONTH, -1);// ��һ���£���Ϊ����
		str = sdf.format(lastDate.getTime());
		return str;
	}

    /**
     * 
     * @TODO ����������� ��ʽΪyyyy-MM-dd
     *
     * @return �������ڵ��ַ������ʽ yyyy-MM-dd
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
		lastDate.add(Calendar.DATE, -1);// ��һ��
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
			//lastDate.add(Calendar.DATE, -1);// ��һ��
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
     * @param datestr �����ַ���  
     * @param day  ���������Ϊ������ʾ֮��Ϊ������ʾ֮ǰ  
     * @return ָ�������ַ���n��֮ǰ����֮�������  
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
            throw new RuntimeException("����ת������");   
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
