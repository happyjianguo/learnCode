package cn.yufu.core.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author guozl
 * 
 *         TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class StringUtil {
	public static final String ENCODING = "UTF8";

	/**
	 * ͨ��String���InputStream
	 * 
	 * @param str
	 * @return
	 */
	public static InputStream fromString(String str) {
		byte[] bytes = null;
		try {
			bytes = str.getBytes(ENCODING);
		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return new ByteArrayInputStream(bytes);
	}

	/**
	 * ���/�����ַ���(s)��ƥ���ַ���(sToMatch)�Ĳ��� ע������sƥ��sToMatch��ÿһ���ַ�
	 * 
	 * @param s
	 * @param sToMatch
	 * @param isToKeep
	 * @return
	 */
	public static String stringCleanUp(String s, String sToMatch, boolean isToKeep) {
		final int size = s.length();
		StringBuffer buf = new StringBuffer(size);
		if (!isToKeep) {
			for (int i = 0; i < size; i++) {
				if (sToMatch.indexOf(s.charAt(i)) == -1) {
					buf.append(s.charAt(i));
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (sToMatch.indexOf(s.charAt(i)) != -1) {
					buf.append(s.charAt(i));
				}
			}
		}
		return buf.toString();
	}

	/**
	 * ͨ��mask�趨string��ʽ ��string��200902 ��mask��**06-**-**��return: 2006-09-02��
	 * 
	 * @param string
	 * @param mask
	 * @return
	 */
	public static String formatString(String string, String mask) {
		try {
			javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter(mask);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(string);
		} catch (Exception e) {
			return string;
		}
	}

	public static String stringToHTMLString(String string) {
		StringBuffer sb = new StringBuffer(string.length());
		// true if last char was blank
		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == ' ') {
				// blank gets extra work,
				// this solves the problem you get if you replace all
				// blanks with &nbsp;, if you do that you loss
				// word breaking
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					sb.append("&nbsp;");
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;
				//
				// HTML Special Chars
				if (c == '"')
					sb.append("&quot;");
				else if (c == '&')
					sb.append("&amp;");
				else if (c == '<')
					sb.append("&lt;");
				else if (c == '>')
					sb.append("&gt;");
				else if (c == '\n')
					// Handle Newline
					sb.append("&lt;br/&gt;");
				else {
					int ci = 0xffff & c;
					if (ci < 160)
						// nothing special only 7 Bit
						sb.append(c);
					else {
						// Not 7 Bit use the unicode system
						sb.append("&#");
						sb.append(new Integer(ci).toString());
						sb.append(';');
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * �ж��ַ���value�Ƿ�Ϊ��.����Ϊ��ʱ����true
	 * 
	 * @param value
	 * @return
	 */
	public static boolean notNull(String value) {
		return null != value && !"".equals(value.trim());
	}

	/**
	 * �ж���������ֵ�Ƿ�Ϊ��,�ڴ˵Ŀհ������ַ�""��null ��������valueΪ��.����true.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		return !notNull(value);
	}
}