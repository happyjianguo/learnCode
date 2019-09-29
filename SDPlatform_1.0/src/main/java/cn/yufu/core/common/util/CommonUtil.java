/*
 * @(#)CommonUtil.java
 * ���ഴ����2014��8��29�� 
 */
package cn.yufu.core.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * �������ȵĹ���������.
 * 
 * @version 0.5
 * @author mengfanpeng
 */

public class CommonUtil {

	// private static final Log LOG = Log.getLog(CommonUtil.class);
	// �������͵�����
	private static final int DATE_TYPE_ITEM = 4;
	// ʱ�����͵�����
	private static final int TIME_TYPE_ITEM = 16;

	/**
	 * ����CLOBת��STRING����
	 * 
	 * @param clob
	 *            CLOB�����ݶ���
	 * @return CLOB��ȡת������ַ�������
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String clobToString(oracle.sql.CLOB clob) throws SQLException, IOException {
		String reString = "";
		// �õ���
		Reader is = clob.getCharacterStream();
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		// ִ��ѭ�����ַ���ȫ��ȡ����ֵ��StringBuffer��StringBufferת��STRING
		while (s != null) {
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		return reString;
	}

	/**
	 * �ṩ����ԣ���ȷ�ĳ������㡣�����������������ʱ����scale����ָ �����ȣ��Ժ�������������롣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @param scale
	 *            ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
	 * @return ������������
	 */
	public static String div(String v1, String v2, int scale) {
		if (v1 == null || v1.trim().length() == 0) {
			return "0";
		}
		if (scale < 0) {
			// LOG.error("�������ȱ������0!");
			return "0";
		}
		BigDecimal b1 = new BigDecimal(v1.trim());
		BigDecimal b2 = new BigDecimal(v2.trim());
		return (b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP)).toString().trim();
	}

	/**
	 * ��ȡָ������(���ֽڳ��Ȼ�ȡ)���ַ���
	 * 
	 * @param src
	 *            Դ�ַ���
	 * @param length
	 *            ����
	 * @return
	 */
	public static String getSubStr(String src, int length) {
		if (src.isEmpty()) {
			return null;
		}
		if (src.getBytes().length > length) {
			byte[] b = src.getBytes();
			byte[] s = new byte[length];
			for (int i = 0; i < length; i++) {
				s[i] = b[i];
			}
			return new String(s);
		} else {
			return src;
		}
	}

	/**
	 * ��ȡ�쳣��Ϣ����
	 * 
	 * @param e
	 *            �쳣����
	 * @param length
	 *            ָ������
	 * @return �����쳣��Ϣ����
	 */
	public static String getExceptionString(Exception e, int length) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		e.printStackTrace(ps);
		String msg = os.toString();
		if (msg.length() > length) {
			msg = getSubStr(msg, length);
		}
		return msg;
	}

	/**
	 * ��ȡ�쳣��Ϣ����
	 * 
	 * @param e
	 *            �쳣����
	 * @return �����쳣��Ϣ����
	 */
	public static String getExceptionString(Exception e) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		e.printStackTrace(ps);
		String msg = os.toString();
		return msg;
	}

	// /**
	// * ���쳣�����װ��ǰ̨չʾ���쳣DTO����
	// * @param e �쳣����
	// * @return �����쳣��ϢDto
	// */
	// public static ExceptionDto getExceptionDto(Exception e) {
	// ExceptionDto dto = new ExceptionDto();
	// dto.setFlag(true);
	// //ϵͳ�ڲ��쳣
	// if (e instanceof BssRuntimeException) {
	// BssRuntimeException be = (BssRuntimeException) e;
	// dto.setExceptionCode(be.getResult().getCode());
	// dto.setExceptionMsg(be.getMessage());
	// dto.setExceptionStack(getExceptionString(be));
	// } else {
	// dto.setExceptionCode(-9999);
	// dto.setExceptionMsg("δ֪�쳣" + e.getMessage() == null ? "" :
	// e.getMessage());
	// dto.setExceptionStack(getExceptionString(e));
	// }
	// return dto;
	// }

	public static String formatDate(Date date, String formatStr) {
		SimpleDateFormat dateformat = new SimpleDateFormat(formatStr);
		String dateStr = dateformat.format(date);
		return dateStr;
	}

	/**
	 * ���ڴ���ǰ̨ͨ��GET�ύ������������⣬��һ������ת��
	 * 
	 * @param originalString
	 *            Դ�ַ���
	 * @return ����ת������ַ���
	 */
	public static String changeEncode(String originalString) {
		try {
			return new String(originalString.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ����List����
	 * 
	 * @param list
	 * @return
	 */
	public static List copyList(List list) {
		try {
			List listBak = new ArrayList();
			for (Object obj : list) {
				listBak.add(BeanUtils.cloneBean(obj));
			}
			return listBak;
		} catch (Exception e) {
			// LOG.error("���󿽱��쳣:" + e.getMessage());
			// throw new SmoException(ErrorDomain.ERROR_COPY_DATA_CODE_1058,
			// "���ݿ����쳣:" + e.getMessage());
		}
		return null;
	}

	/**
	 * ����2�������һ�����ԣ�����������������,Ŀ�����ļ����౻��ʼ��Ϊ�ռ��� add by lsw
	 * 
	 * @param newObj
	 * @param srcObj
	 * @throws IllegalAccessException
	 *             , InvocationTargetException, NoSuchMethodException
	 * 
	 */
	public static void copyProperties(Object newObj, Object srcObj) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		if (newObj == null || srcObj == null) {
			return;
		}
		Field[] fields = srcObj.getClass().getDeclaredFields();
		String name = null;
		for (int i = 0; i < fields.length; i++) {
			name = fields[i].getName();
			// ���˵���������

			if (!(fields[i].getType().isAssignableFrom(Set.class) || fields[i].getType().isAssignableFrom(Collection.class))) {

				PropertyUtils.setSimpleProperty(newObj, name, PropertyUtils.getSimpleProperty(srcObj, name));

			} else {
				PropertyUtils.setSimpleProperty(newObj, name, new HashSet());
			}

		}

	}

	/**
	 * ��ȿ���2������,������������ add by lsw
	 * 
	 * @param srcObj
	 * @throws IOException
	 *             , ClassNotFoundException
	 * 
	 */
	public static Object copyObject(Object srcObj) throws IOException, ClassNotFoundException {
		// ���ö������л�����
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(srcObj);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		return in.readObject();

	}

	/**
	 * ��ȿ�������,������������,���ؿ������� add by lsw
	 * 
	 * @param srcObj
	 * @param count
	 *            ��������
	 * @throws IOException
	 *             , ClassNotFoundException
	 * 
	 */
	public static Object[] copyObject(Object srcObj, int count) throws IOException, ClassNotFoundException {
		// ���ö������л�����
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(srcObj);
		ByteArrayInputStream byteIn = null;
		ObjectInputStream in = null;
		Object[] retObj = new Object[count];
		for (int i = 0; i < count; i++) {
			byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			in = new ObjectInputStream(byteIn);
			retObj[i] = in.readObject();
		}
		return retObj;
	}

	/**
	 * �ж��ַ������Ƿ�ֻ��������
	 * 
	 * @param �ַ���
	 * @return true-ֻ��������,false-�����κ����
	 */
	public static boolean isContainsNumberOnly(String str) {
		boolean result = true;
		if (!StringUtils.isEmpty(str)) {
			for (int i = 0; i < str.length(); i++) {
				if (!Character.isDigit(str.charAt(i))) {
					result = false;
				}
			}
		} else {
			result = false;
		}
		return result;
	}
}
