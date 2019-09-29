/**
 * CPublicUtils.java
 * 版权所有(C) 2010 北京坚石诚信科技公司
 * 创建:yangfan 2010-11-06
 */
package cn.com.jansh.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 字符串工具类
 * 
 * @author yangfan
 * @version 1.0.0
 */
public class CPublicUtils {
	private static String hexString = "0123456789ABCDEF";
	private static String CHAR_ENCODE = "utf8";

	/**
	 * 字符串的替换
	 * 
	 * @param str
	 *            原完整内容
	 * @param substr
	 *            替换后字符串
	 * @param restr
	 *            替换前字符串
	 * @return 替换后的完整内容
	 */
	public static String replace(String str, String substr, String restr) {
		String returnstr = "";
		if (str == null || substr == null || restr == null) {
			return null;
		}
		returnstr = str.replace(restr, substr);

		return dealNull(returnstr);
	}

	/**
	 * @param str
	 *            源字符串转换成字节数组
	 * @return
	 */
	public static byte[] StringToByte(String str) {
		return StringToByte(str, CHAR_ENCODE);
	}

	/**
	 * 字符串转换成源字节数组
	 * 
	 * @param str
	 *            源字符串
	 * @param charEncode
	 *            字符集
	 * @return
	 */
	public static byte[] StringToByte(String str, String charEncode) {
		byte[] destObj = null;
		try {
			if (null == str || str.trim().equals("")) {
				destObj = new byte[0];
				return destObj;
			} else {
				destObj = str.getBytes(charEncode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return destObj;
	}

	/**
	 * 将字符串拆成数组
	 * 
	 * @param sourceStr
	 * @param sign
	 * @return
	 */
	public static String[] stringToArray(String sourceStr, String sign) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(sourceStr, sign);
		while (st.hasMoreTokens()) {
			String everyStr = st.nextToken();
			if (!"".equals(everyStr)) {
				list.add(everyStr);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 计算字符串长度
	 * 
	 * @param str
	 *            待计算长度的字符串
	 * @return 一个字母或数字按一个字符计算<br>
	 *         一个汉字的长度按二个字符计算<br>
	 *         如果给定的字符串为空,返回字符串长为0<br>
	 */
	public static int strlen(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}
		int len = 0;
		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len++;
			} else {
				if (Character.isLetter(c)) {
					// 中文
					len += 2;
				} else {
					// 符号或控制字符
					len++;
				}
			}
		}
		return len;
	}

	/**
	 * 
	 * 十进制转十六进制
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/**
	 * 
	 * 十六进制转十进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	/**
	 * 
	 * string格式化
	 * 
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unused")
	private String FormatStringHexToString(String src) {
		String temp = CPublicUtils.encode(src);
		int i = temp.lastIndexOf("3F");
		if (i != -1) {
			if ((i + 2) == temp.length()) {
				temp = temp.substring(0, temp.length() - 2);
			}
		}
		return CPublicUtils.decode(temp);
	}

	/**
	 * 字符串转换:半角转换为全角
	 * 
	 * @param str
	 *            :待转换的字符串
	 * @return String :转换后的字符串
	 */
	public static String halfConversionFull(String str) {
		String ret = "";
		for (int i = 0; i < str.length(); i++) {
			int j = str.charAt(i);
			if (0 <= j && j < 256) {

				int temp = j + 65248;
				if (temp > 0)
					ret += (char) temp;
				else
					ret += (char) j;
			} else
				ret += (char) j;
		}
		return ret;
	}

	/**
	 * 是否有特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static boolean IsHasTSString(String str) throws Exception {
		// 特殊字符
		String regEx = "[ :'\";%$\\[\\]\\{\\}&|!^]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 处理空字符串null为""
	 * 
	 * @param str 被处理的字符串
	 */
	public static String dealNull(String str) {
		String returnstr = null;
		if (str == null) {
			returnstr = "";
		} else {
			returnstr = str;
		}
		return returnstr;
	}

	/**
	 * 增加换行符号<br>
	 */
	public static String addBr(String txt) {
		if (txt != null) {
			txt = replace(txt, "\n", "<br>");
		}
		return txt;
	}

	/**
	 * 删除换行符号
	 */
	public static String delBr(String txt) {
		if (txt != null) {
			txt = replace(txt, "<br>", "");
		}
		return txt;
	}

	/**
	 * 增加转义字符
	 */
	public static String addSlashes(String txt) {
		if (txt != null) {
			txt = replace(txt, "\'", "\\'");
		}
		return txt;
	}

	/**
	 * 删除转义字符
	 */
	public static String stripslashes(String txt) {
		if (txt != null) {
			txt = replace(txt, "\'", "'");
		}
		return txt;
	}

	/**
	 * 
	 * 去除后缀
	 * 
	 * @param txt
	 * @return
	 */
	public static String stripslashdel(String txt) {
		String result = "";
		if (txt != null) {
			String[] array = txt.split("\\.");
			result = array[0];
		}
		return result;
	}

	/**
	 * 获取指定位数的随机数字字符串
	 * 
	 * @param count
	 * @return
	 */
	public static String getRandomNumStr(int count) {
		return RandomStringUtils.random(count, new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' });
	}

	/**
	 * 字符串特殊字符替换处理类。 指定特殊符号进行字符串切割，并将传入的参数按照字符串中特殊符号的顺序进行替换。
	 * 
	 * @param msg
	 *            带有符号的字符串(必输字段，若传空值则直接返回error)
	 * @param sign
	 *            指定切割的符号(必输字段，若传空值则直接返回error)
	 * @param arg...
	 *            不确定数量参数，根据需要替换的个数进行传值(需要与替换的符号个数相匹配，否则直接返回error)
	 * @return 需要判断返回值，若返回error则说明传入的值不正确。
	 */
	public static String vauleReplaceCharString(String msg, String sign, String... arg) {
		String result = "";
		// 若传入的参数或者切割符号为空则直接返回处理错误
		if ((null == msg || "".equals(msg)) || (null == sign || "".equals(sign))) {
			result = "error";
			return result;
		}
		// 判断是否以特殊字符结尾
		int msgLength = msg.length();
		String lastCharacter = msg.substring(msgLength - 1, msgLength); // 获取最后一个字符串
		StringBuffer lastMsg = new StringBuffer();// 最终返回的字符串
		if (sign.equals(lastCharacter)) {
			int argLength = arg.length; // 替换参数的个数

			String[] msgSp = msg.split(sign); // 根据指定的符号进行切割
			int msgSpCount = msgSp.length; // 获取字符串中#号的个数

			// 传入的参数若与字符串中的特殊符号个数不匹配则返回错误
			if (argLength != msgSpCount) {
				result = "error";
				return result;
			}
			for (int i = 0; i < argLength; i++) {
				lastMsg.append(msgSp[i] + arg[i]);
			}
		} else {
			// 最后一位不是以切割字符串结尾，则说明后面最后一行数据时不完整的，需要继续追加
			int argLength = arg.length;
			String[] msgSp = msg.split(sign);
			int msgSpCount = msgSp.length - 1; // 获取字符串中#号的个数

			// 传入的参数若与字符串中的特殊符号个数不匹配则返回错误
			if (argLength != msgSpCount) {
				result = "error";
				return result;
			}
			for (int i = 0; i < argLength; i++) {
				lastMsg.append(msgSp[i] + arg[i]);
			}
			lastMsg.append(msgSp[argLength]);
		}

		result = lastMsg.toString();

		return result;
	}

	/**
	 * 根据规则的特殊符号进行回车换行显示
	 * 
	 * @param str
	 *            已拼接好的字符串
	 * @param sign
	 *            字符串中含有的字符，以本字符进行数据换行显示
	 * @param enter
	 *            换行符，非必输字段，若不输则不进行换行处理，\n换行处理，\n\n则回车换行处理
	 * @return 需要判断返回值，若返回值为error则说明传入的参数为空
	 */
	public static String enterCharString(String str, String sign, String enter) {
		String result = "";
		// 传入的字符串或者切割字符为空则直接返回错误
		if ((null == str || "".equals(str)) || (null == sign || "".equals(sign))) {
			result = "error";
			return result;
		}

		StringBuffer lastMsg = new StringBuffer();// 存储最后拼接好变量

		String[] strSp = str.split(sign); // 根据指定的符号进行切割

		int msgSpCount = strSp.length; // 获取截取后的字符串长度

		for (int i = 0; i < msgSpCount; i++) {
			lastMsg.append(strSp[i] + enter);
		}

		result = lastMsg.toString(); // 生成最后返回在字符串

		return result;
	}
}
