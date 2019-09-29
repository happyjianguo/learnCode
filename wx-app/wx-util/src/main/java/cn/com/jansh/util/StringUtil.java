package cn.com.jansh.util;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtil {

	private final static char[] charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/**
	 * 获得32位随机数UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
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
	 * 将62进制转换成10进制数
	 * 
	 * @param number
	 * @return
	 */
	public static String base62to10(String number) {
		int decimal = 0;
		int base = 62;
		int keisu = 0;
		int cnt = 0;

		byte ident[] = number.getBytes();
		for (int i = ident.length - 1; i >= 0; i--) {
			int num = 0;
			if (ident[i] > 48 && ident[i] <= 57) {
				num = ident[i] - 48;
			} else if (ident[i] >= 65 && ident[i] <= 90) {
				num = ident[i] - 65 + 10;
			} else if (ident[i] >= 97 && ident[i] <= 122) {
				num = ident[i] - 97 + 10 + 26;
			}
			keisu = (int) java.lang.Math.pow((double) base, (double) cnt);
			decimal += num * keisu;
			cnt++;
		}
		return String.format("%08d", decimal);
	}
}
