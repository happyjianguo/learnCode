package cn.yufu.core.common.util;

import java.util.Date;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

/**
 * �ַ�����
 */
public class Util {
	/**
	 * ��һ�� String ��Ϊ�����������
	 * 
	 * @param source
	 *            byte[]
	 * @param search
	 *            String
	 * @param start
	 *            int
	 * @return int
	 */
	public static int byteIndexOf(byte[] source, String search, int start) {
		String s = new String(source);
		int i = s.indexOf(search, start);
		return i;
	}

	/**
	 * �����ַ���ת��Ϊ�ֽ�������ֽ�����ĳ���
	 * 
	 * @param s
	 *            String
	 * @return int
	 * @roseuid 43BCC4660256
	 */
	public static int bytesLen(String s) {
		return s.getBytes().length;
	}

	/**
	 * ������ڸ�ʽ
	 * 
	 * @param date
	 *            String
	 * @param dateFormat
	 *            String Ĭ��Ϊ"yyyy-MM-dd"
	 * @return boolean
	 */
	public static boolean CheckDate(String date, String dateFormat) {
		if (null == dateFormat || "".equals(dateFormat.trim())) {
			dateFormat = "yyyy-MM-dd";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			sdf.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * ���ַ����еĻس������С��ո�ȷ���ȥ��
	 * 
	 * @param s
	 *            String
	 * @return String
	 * @roseuid 43BCC50C0359
	 */
	public static String CheckString(String s) {
		if (s == null) {
			return "";
		} else {
			String retStr = replace(s, "\n", "");
			retStr = replace(retStr, "\r", "");
			retStr = replace(retStr, " ", "");
			return retStr;
		}
	}

	/**
	 * ȡ���Ӵ���ĸ���г��ֵĸ���
	 * 
	 * @param str
	 *            String
	 * @param sign
	 *            String
	 * @return int
	 * @roseuid 43BCC5E0034A
	 */
	public static int getCount(String str, String sign) {
		if (sign != "\r") {
			str = replace(str, "\r", "");
		}
		if (str == null) {
			return 0;
		}
		StringTokenizer s = new StringTokenizer(str, sign);
		return s.countTokens();
	}

	/**
	 * ����תΪ�ַ�
	 * 
	 * @param date
	 *            Date
	 * @param dateFormat
	 *            String Ĭ��Ϊ"yyyy-MM-dd"
	 * @return String
	 */
	public static String dateToString(Date date, String dateFormat) {
		if (null == dateFormat || "".equals(dateFormat.trim())) {
			dateFormat = "yyyy-MM-dd";
		}

		if (date == null) {
			return "";
		} else {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
			return sdf.format(date);
		}

	}

	/**
	 * �ַ����滻
	 * 
	 * @param line
	 *            String
	 * @param oldString
	 *            String
	 * @param newString
	 *            String
	 * @return String
	 * @roseuid 43BCC6F9014F
	 */
	public static String replace(String line, String oldString, String newString) {
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
	 * //ֱ�ӷ���һ��byte������Ӷ�
	 * 
	 * @param source
	 *            byte[]
	 * @param from
	 *            int
	 * @param end
	 *            int
	 * @return byte[]
	 * @roseuid 43BCC82902D3
	 */
	public static byte[] subbytes(byte[] source, int from, int end) {
		byte[] buffer = new byte[end - from + 1];
		for (int i = from; i <= end; i++) {
			buffer[i - from] = source[i];
		}
		return buffer;

	}

	/**
	 * Сд����ת��Ϊ��д����
	 * 
	 * @param s
	 *            String �����ַ�������ʽ�Ǵ�ת�������֣����ֵΪ99999999��
	 * @return String ��ǧ�Űپ�ʮ�����ǧ�Űپ�ʮ��
	 */
	public static String numberTohzsz(String s) {
		if (s == null || s.length() < 1) {
			return null;
		}
		String shz = "��һ�����������߰˾�ʮ";
		String ws = "ǧ,��,ʮ,��,ǧ,��,ʮ";
		String[] wsArray = ws.split(",");
		String sren = "";
		String stmp = null;
		int ipos;
		for (int i = 0; i < s.length(); i++) {
			stmp = s.substring(i, i + 1);
			if ((stmp.compareTo("0") >= 0) && (stmp.compareTo("9") <= 0)) {
				ipos = Integer.parseInt(stmp);
				stmp = shz.substring(ipos, ipos + 1);
			}
			sren += stmp;
			int j = wsArray.length - s.length() + 1 + i;
			if (j < wsArray.length) {
				stmp = wsArray[j].trim();
				sren += stmp;
			}
		}
		if (sren.length() > 1) {
			for (int i = sren.length(); i > 0; i--) {
				if (sren.endsWith("��")) {
					sren = sren.substring(0, sren.length() - 1);
				}
				if (sren.endsWith("��ʮ")) {
					sren = sren.substring(0, sren.length() - 2);
				}
				if (sren.endsWith("���")) {
					sren = sren.substring(0, sren.length() - 2);
				}
				if (sren.endsWith("��ǧ")) {
					sren = sren.substring(0, sren.length() - 2);
				}
				if (sren.endsWith("����")) {
					sren = sren.substring(0, sren.length() - 2) + "��";
				}
				if (sren.endsWith("��ʮ��")) {
					sren = sren.substring(0, sren.length() - 3) + "��";
				}
				if (sren.endsWith("�����")) {
					sren = sren.substring(0, sren.length() - 3) + "��";
				}
			}
		}

		return sren;
	}

	/**
	 * �ַ���ת boolean
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean stringToBoolean(String str) {
		if ("true".equals(str))
			return true;
		return false;
	}

	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean stringIsEmpty(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		return false;
	}

	/**
	 * ����
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		System.out.println(stringIsEmpty(null));
	}

}
