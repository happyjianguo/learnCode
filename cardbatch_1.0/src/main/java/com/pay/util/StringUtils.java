package com.pay.util;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @TODO 字符串操作公用类
 * 
 * @author YP
 * @created on 2007-12-19 下午01:24:01
 * @version 1.0
 */
public class StringUtils {
	public static void main(String[] args){
		System.out.println("----------------工具类测试方法----------------");
		System.out.println(isNotEmptyStr(""));;
		System.out.println(isNotEmptyStr(null));;
	}
	private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
	private static final char[] AMP_ENCODE = "&amp;".toCharArray();
	private static final char[] LT_ENCODE = "&lt;".toCharArray();
	private static final int fillchar = '=';
	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";
	private static String HanDigiStr[] = new String[] { "零", "壹", "贰", "叁",
			"肆", "伍", "陆", "柒", "捌", "玖" };
	private static String HanDiviStr[] = new String[] { "", "拾", "佰", "仟", "万",
			"拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾",
			"佰", "仟", "万", "拾", "佰", "仟" };

	/**
	 * 
	 * @TODO 将内码(ISO8859-1)转换为外码(GB2312)
	 * 
	 * @param unicodeString
	 *            要进行转码的字符串
	 * @return 转码后的字符串
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午01:24:28
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String innerToOuter(String unicodeString) {
		if (unicodeString == null)
			return null;
		if (unicodeString.trim().length() == 0)
			return unicodeString;

		// 取得外码参数
		String extcharset = SystemConfig.getValue("extcharset");
		if (extcharset == null)
			extcharset = "GB2312";

		// 取得内码参数
		String intcharset = SystemConfig.getValue("intcharset");
		if (intcharset == null)
			intcharset = "ISO8859-1";

		String returnStr = "";
		try {
			if (extcharset.intern() != intcharset.intern()) {
				returnStr = new String(unicodeString.getBytes(intcharset),
						extcharset);
			} else {
				returnStr = unicodeString;
			}
		} catch (Exception e) {
		}
		return returnStr;
	}

	/**
	 * 
	 * @TODO 将外码转换为内码
	 * 
	 * @param str
	 *            要转码的字符串
	 * @return 转码后的字符串
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午01:29:16
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String outerToInner(String str) {
		if (str == null)
			return null;
		if (str.trim().length() == 0)
			return str;

		// 取得外码参数
		String extcharset = SystemConfig.getValue("extcharset");
		if (extcharset == null)
			extcharset = "GB2312";

		// 取得内码参数
		String intcharset = SystemConfig.getValue("intcharset");
		if (intcharset == null)
			intcharset = "ISO8859-1";

		String returnStr = "";
		try {
			if (extcharset.intern() != intcharset.intern()) {
				returnStr = new String(str.getBytes(extcharset), intcharset);
			} else {
				returnStr = str;
			}
		} catch (Exception e) {
		}

		return returnStr;
	}

	/**
	 * Replaces all instances of oldString with newString in line.
	 * 
	 * @param line
	 *            the String to search to perform replacements on
	 * @param oldString
	 *            the String that should be replaced by newString
	 * @param newString
	 *            the String that will replace all instances of oldString
	 * 
	 * @return a String will all instances of oldString replaced by newString
	 */
	public static final String replace(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 
	 * @TODO base64加密
	 * 
	 * @param data
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:Jan 19, 2008  5:13:37 PM
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String encodeBase64(String data) {
		return encodeBase64(data.getBytes());
	}

	/**
	 * 
	 * @TODO base64加密
	 * 
	 * @param data
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:Jan 19, 2008  5:13:37 PM
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String encodeBase64(byte[] data) {
		String returnStr = new BASE64Encoder().encode(data);

		return returnStr;
	}

	/**
	 * 
	 * @TODO 解密
	 * 
	 * @param data
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:Jan 19, 2008  5:13:32 PM
	 * LastModified
	 * History:
	 * </pre>
	 * @throws IOException
	 */
	public static String decodeBase64(String data) throws IOException {
		if (data == null)
			return null;
		byte[] returnStr = new BASE64Decoder().decodeBuffer(data);

		return new String(returnStr);
	}

	/**
	 * \u00B3\u00FD\u00C8\u00A5\u00A1\u00C1\u00D6\u00A1¤\u00FB\u00B4\u00AE
	 * \u00D6\u00D0\u00CC\u00D8\u00CA\u00E2
	 * \u00A1\u00C1\u00D6\u00A1¤\u00FB\u00B2\u00A2
	 * \u00BD\u00AB\u00D2\u00D4\u00CC\u00D8\u00CA\u00E2
	 * \u00A1\u00C1\u00D6\u00A1¤\u00FB
	 * \u00B8\u00F4\u00B6\u00CF\u00B5\u00C4\u00A1\u00C1\u00D3
	 * \u00A1\u00C1\u00D6\u00A1
	 * ¤\u00FB\u00B4\u00AE\u00A1¤\u00D6\u00A1\u00C1¨\u00A6
	 * \u00B4\u00E6\u00A1¤\u00C5.
	 * 
	 * @param text
	 *            a String of text to convert into an array of words
	 * @return text broken up into an array of words.
	 */
	public static final String[] splittoArray(String text) {
		if (text == null || text.length() == 0) {
			return new String[0];
		}
		ArrayList wordList = new ArrayList();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(text);
		int start = 0;
		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
				.next()) {
			String tmp = text.substring(start, end).trim();
			tmp = replace(tmp, "~", "");
			tmp = replace(tmp, "!", "");
			tmp = replace(tmp, "@", "");
			tmp = replace(tmp, "#", "");
			tmp = replace(tmp, "$", "");
			tmp = replace(tmp, "%", "");
			tmp = replace(tmp, "^", "");
			tmp = replace(tmp, "&", "");
			tmp = replace(tmp, "*", "");
			tmp = replace(tmp, "(", "");
			tmp = replace(tmp, ")", "");
			tmp = replace(tmp, "_", "");
			tmp = replace(tmp, "+", "");
			tmp = replace(tmp, "|", "");
			tmp = replace(tmp, "{", "");
			tmp = replace(tmp, "}", "");
			tmp = replace(tmp, "[", "");
			tmp = replace(tmp, "]", "");
			if (tmp.length() > 0) {
				wordList.add(tmp);
			}
		}
		return (String[]) wordList.toArray(new String[wordList.size()]);
	}

	/**
	 * \u00D3\u00C3\u00A1\u00C1\u00D6\u00A1¤\u00FB\u00B4\u00AE
	 * \u00D6\u00D0\u00B5\u00C4\u00C4
	 * \u00B3\u00B8\u00F6\u00CC\u00D8\u00CA\u00E2\u00A1
	 * \u00C1\u00D6\u00A1¤\u00FB\u00A1
	 * \u00C1\u00A1\u00C2\u00CE\u00AA\u00A1¤\u00D6\u00B8
	 * \u00EE\u00A1¤\u00FB,\u00BD
	 * \u00AB\u00A1\u00C1\u00D6\u00A1¤\u00FB\u00B4\u00AE
	 * \u00A1¤\u00D6\u00B3\u00C9\u00C8\u00F4
	 * \u00B8\u00C9\u00B8\u00F6\u00A1\u00C1\u00D6
	 * \u00A1¤\u00FB\u00B4\u00AE\u00CA\u00FD\u00A1\u00C1¨\u00A6\u00A1\u00A3
	 * 
	 * @param text
	 *            a String of text to convert into an array of words
	 * @param str
	 *            \u00A1\u00C1\u00D6\u00A1¤\u00FB\u00B4\u00AE
	 *            \u00D6\u00D0\u00B5\u00C4\u00C4
	 *            \u00B3\u00B8\u00F6\u00CC\u00D8\u00CA\u00E2
	 *            \u00A1\u00C1\u00D6\u00A1¤\u00FB.
	 * @return text broken up into an array of words.
	 */
	public static final String[] splittoArrayByStr(String text, String str) {
		if (text == null || text.length() == 0) {
			return new String[0];
		}
		ArrayList wordList = new ArrayList();
		if (text.indexOf(str) < 0) {
			wordList.add(text);
		} else {
			BreakIterator boundary = BreakIterator.getWordInstance();
			boundary.setText(text);
			int start = 0;
			for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
					.next()) {
				String tmp = text.substring(start, end).trim();
				tmp = replace(tmp, str, "");
				if (tmp.length() > 0) {
					wordList.add(tmp);
				}
			}
		}
		return (String[]) wordList.toArray(new String[wordList.size()]);
	}

	/**
	 * Pseudo-random number generator object for use with randomString(). The
	 * Random class is not considered to be cryptographically secure, so only
	 * use these random Strings for low to medium security applications.
	 */
	private static Random randGen = new Random();

	/**
	 * Array of numbers and letters of mixed case. Numbers appear in the list
	 * twice so that there is a more equal chance that a number will be picked.
	 * We can use the array to get a random number or letter by picking a random
	 * array index.
	 */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
			+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	/**
	 * Returns a random String of numbers and letters (lower and upper case) of
	 * the specified length. The method uses the Random class that is built-in
	 * to Java which is suitable for low to medium grade security uses. This
	 * means that the output is only pseudo random, i.e., each number is
	 * mathematically generated so is not truly random.
	 * <p>
	 * 
	 * The specified length must be at least one. If not, the method will return
	 * null.
	 * 
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String chopAtWord(String string, int length) {
		if (string == null) {
			return string;
		}

		char[] charArray = string.toCharArray();
		int sLength = string.length();
		if (length < sLength) {
			sLength = length;
		}

		// First check if there is a newline character before length; if so,
		// chop word there.
		for (int i = 0; i < sLength - 1; i++) {
			// Windows
			if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
				return string.substring(0, i + 1);
			}
			// Unix
			else if (charArray[i] == '\n') {
				return string.substring(0, i);
			}
		}
		// Also check boundary case of Unix newline
		if (charArray[sLength - 1] == '\n') {
			return string.substring(0, sLength - 1);
		}

		// Done checking for newline, now see if the total string is less than
		// the specified chop point.
		if (string.length() < length) {
			return string;
		}

		// No newline, so chop at the first whitespace.
		for (int i = length - 1; i > 0; i--) {
			if (charArray[i] == ' ') {
				return string.substring(0, i).trim();
			}
		}

		// Did not find word boundary so return original String chopped at
		// specified length.
		return string.substring(0, length);
	}

	/**
	 * Escapes all necessary characters in the String so that it can be used in
	 * an XML doc.
	 * 
	 * @param string
	 *            the string to escape.
	 * @return the string with appropriate characters escaped.
	 */
	public static final String escapeForXML(String string) {
		if (string == null) {
			return null;
		}
		char ch;
		int i = 0;
		int last = 0;
		char[] input = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (; i < len; i++) {
			ch = input[i];
			if (ch > '>') {
				continue;
			} else if (ch == '<') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
			} else if (ch == '&') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(AMP_ENCODE);
			} else if (ch == '"') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(QUOTE_ENCODE);
			}
		}
		if (last == 0) {
			return string;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/**
	 * Unescapes the String by converting XML escape sequences back into normal
	 * characters.
	 * 
	 * @param string
	 *            the string to unescape.
	 * @return the string with appropriate characters unescaped.
	 */
	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	/**
	 * get the count of character[',',';'] int long String.
	 * 
	 * @param str
	 *            :long str
	 * @return the count of character[',',';']
	 *         \u00B5\u00C3\u00B5\u00BD\u00D3\u00C3
	 *         \u00A1¤\u00FB\u00BA\u00C5\u00A3
	 *         \u00A1§,;\u00A3\u00A9\u00BD\u00AB\u00A1
	 *         \u00C1\u00D6\u00A1¤\u00FB\u00B4
	 *         \u00AE\u00B8\u00F4\u00BF\u00AA\u00B5\u00C4
	 *         \u00A1\u00C1\u00D3\u00A1
	 *         \u00C1\u00D6\u00A1¤\u00FB\u00B4\u00AE\u00B5\u00C4
	 *         \u00B8\u00F6\u00CA\u00FD
	 */
	public static final int getCharacterCount(String str) {
		int cnt = 0, i = 0, len = 0;
		String charStr = "";

		if (str == null)
			return 0;
		str = str.trim();
		len = str.length();
		if (len == 0)
			return 0;
		for (i = 0; i < len; i++) {
			charStr = str.substring(i, i + 1);
			if ((charStr.compareTo(",") == 0) | (charStr.compareTo(";") == 0))
				cnt++;
		}
		if ((charStr.compareTo(",") != 0) & (charStr.compareTo(";") != 0))
			cnt++;
		return cnt;
	}

	/**
	 * get the value of the n th of the long String which split by
	 * character[',',';']
	 * 
	 * @param str
	 *            :long String
	 * @param n
	 *            :then n th item
	 * @return this value
	 *         \u00C8\u00A1\u00B5\u00C3\u00B5\u00DAn\u00B8\u00F6\u00B6
	 *         \u00CF\u00BE
	 *         \u00E4\u00A1\u00C1\u00D6\u00A1¤\u00FB\u00D3\u00EB\u00B5\u00DAn
	 *         +1\u00B8\u00F6\u00B6\u00CF\u00BE\u00E4\u00A1\u00C1\u00D6\u00A1
	 *         ¤\u00FB\u00D6
	 *         \u00AE\u00BC\u00E4\u00B5\u00C4\u00A1\u00C1\u00D6\u00A1
	 *         ¤\u00FB\u00B4\u00AE\u00A1\u00A3
	 */
	public static final String getCharacterItem(String str, int n) {
		int cnt = 0, i = 0, len = 0, loop = 0;
		String charStr = "", rtn = "";
		cnt = getCharacterCount(str);
		if ((cnt == 0) | (n >= cnt))
			return rtn;
		str = str.trim();
		len = str.length();
		for (i = 0; i < len; i++) {
			charStr = str.substring(i, i + 1);
			if ((charStr.compareTo(",") == 0) | (charStr.compareTo(";") == 0))
				loop++;
			else if (loop == n)
				rtn += charStr;
		}
		return rtn;
	}

	/**
	 * \u00A1\u00C1\u00AA\u00BB\u00BB\u00B8\u00F1\u00CA\u00BD\u00BD\u00AB
	 * \u00CA\u00FD
	 * \u00BE\u00DD\u00BF\u00E2\u00B8\u00F1\u00CA\u00BD\u00A1\u00C1\u00AA
	 * \u00BB\u00AFHTML\u00B8\u00F1\u00CA\u00BD
	 * 
	 * @param str
	 * @return
	 */
	public static final String getOutPut(String str) {
		if (str == null)
			return "";
		str = str.trim();
		int i = str.length();
		if (i == 0) {
			return "";
		} else {
			str = replace(str, "\n", "<br>");
			str = replace(str, "&", "&amp;");
			str = replace(str, "'", "&apos;");
			str = replace(str, " ", "&nbsp;");
			str = replace(str, "\"", "&quot;");
			str = replace(str, "<", "&lt;");
			str = replace(str, ">", "&gt;");
			return str;
		}
	}

	/**
	 * \u00D3\u00C3\u00A1\u00C1\u00D6\u00A1¤\u00FB
	 * '\u00BD\u00AB\u00A1\u00E3¨\u00B9\u00BA\u00AC","\u00B5\u00C4\u00A1\u00C1\u00D6\u00A1¤\u00FB\u00A1¤\u00D6\u00BF\u00AA
	 * .
	 * 
	 * @param str
	 * @return String
	 */

	public static final String addSingleQuotation(String arrayStr) {
		String rtn = "";
		if (arrayStr.equals("")) {
			rtn = "''";
		} else {
			String[] strArray = arrayStr.split(",");
			for (int i = 0; i < strArray.length; i++) {
				rtn += ",'" + strArray[i] + "'";
			}
			rtn = rtn.substring(1);
		}
		return rtn;
	}

	/**
	 * 
	 * @TODO 左补空格
	 * 
	 * @param sLStr
	 *            补全字符串
	 * @param iLen
	 *            补全长度
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午01:38:27
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String lSpace(String sLStr, int iLen) {
		if (sLStr == null)
			sLStr = "";

		String sRtn = "";

		for (int i = 0; i < (iLen - sLStr.length()); i++)
			sRtn += " ";
		return sRtn + sLStr;
	}

	/**
	 * 
	 * @TODO 右补空格
	 * 
	 * @param sRStr
	 *            补全字符串
	 * @param iLen
	 *            补全长度
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午01:38:58
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String rSpace(String sRStr, int iLen) {
		if (sRStr == null)
			sRStr = "";

		String sRtn = "";

		for (int i = 0; i < (iLen - sRStr.length()); i++)
			sRtn += " ";
		return sRStr + sRtn;
	}

	/**
	 * 
	 * @TODO 右补两个空格
	 * 
	 * @param sRStr
	 *            补全字符串
	 * @param iLen
	 *            补全长度
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午01:39:58
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String rTwoSpace(String sRStr, int iLen) {
		if (sRStr == null)
			sRStr = "";

		String sRtn = "";

		for (int i = 0; i < (iLen - sRStr.length()); i++)
			sRtn += "  ";

		return sRStr + sRtn;
	}

	/**
	 * 
	 * @TODO 左补零
	 * 
	 * @param sLStr
	 *            补全字符串
	 * @param iLen
	 *            补全长度
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午02:27:01
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String lZero(String sLStr, int iLen) {
		if (sLStr == null)
			sLStr = "";

		String sRtn = "";

		for (int i = 0; i < (iLen - sLStr.length()); i++)
			sRtn += "0";
		return sRtn + sLStr;
	}

	/**
	 * 
	 * @TODO 右补零 　*
	 * @param sRStr
	 *            　 补全字符串
	 * @param iLen
	 *            　补全长度
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午02:27:35
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String rZero(String sRStr, int iLen) {
		if (sRStr == null)
			sRStr = "";

		String sRtn = "";

		for (int i = 0; i < (iLen - sRStr.length()); i++)
			sRtn += "0";
		return sRStr + sRtn;
	}

	/**
	 * 
	 * @TODO 16进制BCD转为字符串
	 * 
	 * @param strIn
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午03:01:19
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String HexToByte(String strIn) {
		byte[] arrB = strIn.getBytes();

		byte[] by = new byte[strIn.length() / 2];
		for (int i = 0; i < strIn.length() / 2; i++) {
			String swStr = new String(arrB, i * 2, 2);
			by[i] = (byte) (Integer.parseInt(swStr, 16));
		}
		return new String(by);
	}

	private static String PositiveIntegerToHanStr(String NumStr) { // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
		String RMBStr = "";
		boolean lastzero = false;
		boolean hasvalue = false; // 亿、万进位前有数值标记
		int len, n;
		len = NumStr.length();
		if (len > 15)
			return "数值过大!";
		for (int i = len - 1; i >= 0; i--) {
			if (NumStr.charAt(len - i - 1) == ' ')
				continue;
			n = NumStr.charAt(len - i - 1) - '0';
			if (n < 0 || n > 9)
				return "输入含非数字字符!";

			if (n != 0) {
				if (lastzero)
					RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
				// 除了亿万前的零不带到后面
				// if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) ) //
				// 如十进位前有零也不发壹音用此行
				if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
					RMBStr += HanDigiStr[n];
				RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
				hasvalue = true; // 置万进位前有值标记

			} else {
				if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
					RMBStr += HanDiviStr[i]; // “亿”或“万”
			}
			if (i % 8 == 0)
				hasvalue = false; // 万进位前有值标记逢亿复位
			lastzero = (n == 0) && (i % 4 != 0);
		}

		if (RMBStr.length() == 0)
			return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
		return RMBStr;
	}

	/**
	 * 
	 * @TODO 大写金额转换
	 * 
	 * @param strVal
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  下午03:33:51
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String DigitalConversion(String strVal) {
		double val = Double.parseDouble(strVal);
		String SignStr = "";
		String TailStr = "";
		long fraction, integer;
		int jiao, fen;

		if (val < 0) {
			val = -val;
			SignStr = "负";
		}
		if (val > 99999999999999.999 || val < -99999999999999.999)
			return "数值位数过大!";
		// 四舍五入到分
		long temp = Math.round(val * 100);
		integer = temp / 100;
		fraction = temp % 100;
		jiao = (int) fraction / 10;
		fen = (int) fraction % 10;
		if (jiao == 0 && fen == 0) {
			TailStr = "整";
		} else {
			TailStr = HanDigiStr[jiao];
			if (jiao != 0)
				TailStr += "角";
			if (integer == 0 && jiao == 0) // 零元后不写零几分
				TailStr = "";
			if (fen != 0)
				TailStr += HanDigiStr[fen] + "分";
		}

		return SignStr + PositiveIntegerToHanStr(String.valueOf(integer)) + "元"
				+ TailStr;
	}

	/**
	 * 
	 * @TODO 判断传入对象为非空字符串对象
	 * 
	 * @param str
	 * @return
	 * @author YP
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-24  上午12:08:54
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static boolean isNotEmptyStr(Object str) {
		if (str != null && str instanceof String
				&& str.toString().trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	// public static String formatStrToSql(Object[] obj) {
	// if (obj == null)
	// return "";
	// String returnStr = "";
	// StringBuffer bf = new StringBuffer();
	// for (int i = 0; i < obj.length; i++) {
	// bf.append("'");
	// bf.append(obj[i]);
	// bf.append("',");
	// }
	// returnStr = bf.toString();
	//
	// if (returnStr.lastIndexOf(",") > 0)
	// returnStr = returnStr.substring(0, returnStr.lastIndexOf(","));
	//
	// return returnStr;
	// }

	public static String formatStrToSql(Object[] obj) {
		if (obj == null)
			return "";
		String returnStr = "";
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < obj.length; i++) {
			bf.append("?,");
		}
		returnStr = bf.toString();

		if (returnStr.lastIndexOf(",") > 0)
			returnStr = returnStr.substring(0, returnStr.lastIndexOf(","));

		return returnStr;
	}

	public static String trimDbData(String data) {
		if (data == null)
			return "";
		else
			return data.trim();
	}

	public static String[] splitStr(String str, String split, boolean trimFlag) {
		if (str == null)
			return null;
		str = str.replaceAll(split, " " + split + " ");
		StringTokenizer st = new StringTokenizer(str, split, false);
		String[] strArray = new String[st.countTokens()];
		for (int i = 0; st.hasMoreElements(); i++) {
			if (trimFlag)
				strArray[i] = trimDbData(st.nextToken());
			else
				strArray[i] = st.nextToken();
		}
		return strArray;
	}

	//
	// public static String[] splitStr1(String str, String split) {
	// if (str == null)
	// return null;
	// str = str.replaceAll(split, " " + split + " ");
	// String tmp = str;
	// while (str.indexOf("\\\\") != -1) {
	// tmp = str.substring(0, str.indexOf("\\\\")) + "\\"
	// + str.substring(str.indexOf("\\\\") + 2);
	// str = str.substring(0, str.indexOf("\\\\")) +
	// str.substring(str.indexOf("\\\\") + 2);
	// }
	// str = tmp;
	// String[] strArray = str.split(split);
	// // StringTokenizer st = new StringTokenizer(str, split, false);
	// // String[] strArray = new String[st.countTokens()];
	// // for (int i = 0; st.hasMoreElements(); i++) {
	// // if (trimFlag)
	// // strArray[i] = trimDbData(st.nextToken());
	// // else
	// // strArray[i] = st.nextToken();
	// // }
	// return strArray;
	// }

	public static String[] splitStr(String str, String split) {
		return splitStr(str, split, false);
	}

	/**
	 * 
	 * @TODO 将位图中的某一位替换成其他字符
	 * 
	 * @param sStr
	 *            要转码的字符串，rChar 要替换成的字符，iIndx 索引号
	 * @return 转码后的字符串
	 * @author zl
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2009-12-29
	 * LastModified
	 * History:
	 * </pre>
	 */
	public static String getBitStr(String sStr, String rChar, int iIndx) {
		String sSource = "";
		for (int i = 0; i < sStr.length(); i++) {
			if (i == iIndx) {
				sSource += rChar;
				continue;
			}
			sSource += sStr.charAt(i);
		}
		return sSource;
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}
}
