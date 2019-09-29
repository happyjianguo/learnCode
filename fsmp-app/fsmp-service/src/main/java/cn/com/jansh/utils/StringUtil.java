package cn.com.jansh.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	/**
	 * 62进制字符串
	 */
	private final static char[] charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

/////
    // 把字符串中html标记所用的六个特殊字符替换回正常字符：&amp;quot;（&quot;） &amp;#039;（&#039;）
    // &amp;lt;（&lt;） &amp;gt;（&gt;） &amp;amp;（&amp;） 。
    // TODO
    // param sSource 要替换的字符串。
    // return 返回替换后的字符串。
    ///
   public static String htmlDecode(String sSource) {
       String sTemp = sSource;
       sTemp = replace(sTemp, "&quot;", "\"");
       sTemp = replace(sTemp, "&#039;", "'");
       sTemp = replace(sTemp, "&lt;", "<");
       sTemp = replace(sTemp, "&gt;", ">");
       sTemp = replace(sTemp, "&amp;", "&");
       return sTemp;
   }
	/////
	 // 在字符串中替换html标记所用的六个特殊字符：& \ " ' &lt; &gt;
	 //
	 // param sSource 要替换的字符串。
	 // return 返回替换后的字符串。
	 ///
	public static String htmlEncode(String sSource) {
		 String sTemp = sSource;
		 sTemp = replace(sTemp, "&", "&amp;");
		 sTemp = replace(sTemp, "\"", "&quot;");
		 sTemp = replace(sTemp, "'", "&#039;");
		 sTemp = replace(sTemp, "<", "&lt;");
		 sTemp = replace(sTemp, ">", "&gt;");
		 return sTemp;
	}
/////
   // 在字符串中进行查找替换，给出替换后的字符串.
   //
   // 如果是null 传入将返回不替换而返回原字符串.
   // 例子：
   // <pre>
   // StringUtil.replace(null, //, //)        = null
   // StringUtil.replace("", //, //)          = ""
   // StringUtil.replace("aba", null, null) = "aba"
   // StringUtil.replace("aba", null, null) = "aba"
   // StringUtil.replace("aba", "a", null)  = "aba"
   // StringUtil.replace("aba", "a", "")    = "aba"
   // StringUtil.replace("aba", "a", "z")   = "zbz"
   // </pre>
   //
   // param text  text to search and replace in, may be null
   // param repl  the String to search for, may be null
   // param with  the String to replace with, may be null
   // return the text with any replacements processed,
   //  <code>null</code> if null String input
   ///
  public static String replace(String text, String repl, String with) {
      return StringUtils.replace(text,repl,with);
  }
	
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

	public static void main(String[] args) {
		System.out.println(randomCharNum(20));
	}
}
