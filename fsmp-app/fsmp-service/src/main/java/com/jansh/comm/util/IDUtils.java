package com.jansh.comm.util;

/**
 * 各种id生成策略
 */
public class IDUtils {

	/**
	 * 根据当前时间+5位随机字符串
	 * 
	 * @return
	 */
	public static String getTimeRandon() {
		long time = DateUtil.getClockMillis();
		return StringUtil.base10to62(time) + StringUtil.randomCharNum(5);
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.base10to62(999999999999999999L));
		System.out.println(StringUtil.base62to10("1Bs0emTbBk7"));
		// long a = Long.valueOf(StringUtil.base62to10("PwdeRWF"));
		// System.out.println(new Date(25344100232553L));;
	}

}
