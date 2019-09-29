import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.yufu.system.common.utils.DateUtil;

/**
 *包名:
 *描述:
 */
/**
 * test.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年6月23日
 * 描述:TODO
 */
public class test {
	public static String getFormatTimeString(String date) throws ParseException {
		String pattern = "yyyyMMdd";
		SimpleDateFormat sDateFormat = DateUtil.getDateFormat(pattern);
		Date dates = DateUtil.getDateFromString(date, "yyyy-MM-dd HH:mm:ss");
		// 单实例,SimpleDateFormat非线程安全
		synchronized (sDateFormat) {
			return sDateFormat.format(dates);
		}
	}
	public static void main(String[] args) throws ParseException{
		System.out.println(getFormatTimeString("2017-06-13 00:00:00"));
	}
}
