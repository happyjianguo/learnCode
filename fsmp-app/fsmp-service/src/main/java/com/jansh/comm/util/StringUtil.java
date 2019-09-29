package com.jansh.comm.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	/**
	 * 62进制字符串
	 */
	private final static char[] charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
			.toCharArray();

	/**
	 * 去除字符串两端空格，如果为参数null，则返回空字符串
	 * 
	 * <pre>
	 * StringUtil.trimToEmpty(null)          = ""
	 * StringUtil.trimToEmpty("")            = ""
	 * StringUtil.trimToEmpty("     ")       = ""
	 * StringUtil.trimToEmpty("abc")         = "abc"
	 * StringUtil.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String trimToEmpty(String str) {
		return str == null ? "" : str.trim();
	}

	/**
	 * 生成随机[a-z]字符串，包含大小写
	 */
	public static String randomAlphabetic(int length) {
		// 生成随机[a-z]字符串，包含大小写
		return RandomStringUtils.randomAlphabetic(length);
	}

	/**
	 * 生成随机数字字符串
	 */
	public static String randomNumeric(int length) {
		// 生成随机数字字符串
		return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * 生成指定长度的字母和数字的随机组合字符串
	 */
	public static String randomCharNum(int length) {
		// 生成指定长度的字母和数字的随机组合字符串
		return RandomStringUtils.randomAlphanumeric(length);
	}

	/**
	 * String转int，如果字符串为空，则返回-1
	 * 
	 * @param val
	 * @return
	 */
	public static int parseInt(String val) {
		if (StringUtils.isBlank(val)) {
			return -1;
		}
		return Integer.parseInt(val);
	}

	/**
	 * 将10进制转化为62进制字符串
	 * 
	 * @param number
	 * @return
	 */
	public static String base10to62(long number) {
		Long rest = number;
		StringBuilder result = new StringBuilder(0);
		while (rest != 0) {
			result.insert(0, charSet[new Long((rest - (rest / 62) * 62)).intValue()]);
			rest = rest / 62;
		}
		return result.toString();
	}

	/**
	 * 将10进制字符串转化为62进制字符串， 无long类型的最大限制，但效率较低
	 * 
	 * @param number
	 * @return
	 */
//	public static String base10to62(String number) {
//		// Long rest = number;
//		BigDecimal rest = new BigDecimal(number);
//		BigDecimal zero = new BigDecimal(0);
//		BigDecimal jinzhi = new BigDecimal(62);
//		StringBuilder result = new StringBuilder(0);
//
//		while (rest.compareTo(zero) > 0) {
//			System.out.println(rest);
////			BigDecimal a = rest.divide(jinzhi, 0).multiply(jinzhi);
//			BigDecimal b[] = rest.divideAndRemainder(jinzhi);
//			result.insert(0, charSet[(rest.subtract(b[0].multiply(jinzhi)).intValue())]);
//			rest = b[1];
//		}
//		return result.toString();
//	}

	/**
	 * 将62进制转换成10进制数
	 * 
	 * @param ident62
	 * @return
	 */
	public static String base62to10(String ident62) {
		BigDecimal decimal = new BigDecimal(0);
		BigDecimal base = new BigDecimal(62);
		BigDecimal keisu = new BigDecimal(0);
		int cnt = 0;
		byte ident[] = ident62.getBytes();
		for (int i = ident.length - 1; i >= 0; i--) {
			int num = 0;
			if (ident[i] > 48 && ident[i] <= 57) {
				// 0-9
				num = ident[i] - 48;
			} else if (ident[i] >= 65 && ident[i] <= 90) {
				// A-Z
				num = ident[i] - 65 + 10;
			} else if (ident[i] >= 97 && ident[i] <= 122) {
				// a-z
				num = ident[i] - 97 + 10 + 26;
			}
			keisu = base.pow(cnt);
			BigDecimal da = keisu.multiply(new BigDecimal(num));
			decimal = decimal.add(da);
			cnt++;
		}
		return decimal.toString();
	}

	public static void main(String[] args) {
		System.out.println(randomCharNum(20));
	}
}
