package cn.yufu.core.common.util;

import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * 用于实现时间类型与字符串之间的转换；
 * @version
 * @author
 */
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
   * * 比较两个给定的Date值的大小.
   * @param date1 Date
   * @param date2 Date
   * @return int 0 date1等于date2 ;1 date1>date2;-1 date1<date2
   */

  public static int compareDatetime(Date date1, Date date2)
  {
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


  public static void main(String[] args)
  {
    String dat = "1900-1-1 00:00:00";
    String dat2 = "2100-1-1 00:00:00";
    String dat3 = "2003-05-09";
    System.out.println(DateTimeUtil.shortFmt(new Date(), null));
  }
}
