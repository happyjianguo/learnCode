/*
 * @(#)CommonUtil.java
 * 本类创建于2014年8月29日 
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
 * 订单调度的公共工具类.
 * 
 * @version 0.5
 * @author mengfanpeng
 */

public class CommonUtil {

	// private static final Log LOG = Log.getLog(CommonUtil.class);
	// 日期类型的属性
	private static final int DATE_TYPE_ITEM = 4;
	// 时间类型的属性
	private static final int TIME_TYPE_ITEM = 16;

	/**
	 * 将字CLOB转成STRING类型
	 * 
	 * @param clob
	 *            CLOB的数据对象
	 * @return CLOB读取转化后的字符串对象
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String clobToString(oracle.sql.CLOB clob) throws SQLException, IOException {
		String reString = "";
		// 得到流
		Reader is = clob.getCharacterStream();
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
		while (s != null) {
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		return reString;
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static String div(String v1, String v2, int scale) {
		if (v1 == null || v1.trim().length() == 0) {
			return "0";
		}
		if (scale < 0) {
			// LOG.error("除法精度必须大于0!");
			return "0";
		}
		BigDecimal b1 = new BigDecimal(v1.trim());
		BigDecimal b2 = new BigDecimal(v2.trim());
		return (b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP)).toString().trim();
	}

	/**
	 * 获取指定长度(按字节长度获取)的字符串
	 * 
	 * @param src
	 *            源字符串
	 * @param length
	 *            长度
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
	 * 获取异常信息内容
	 * 
	 * @param e
	 *            异常对象
	 * @param length
	 *            指定长度
	 * @return 返回异常信息内容
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
	 * 获取异常信息内容
	 * 
	 * @param e
	 *            异常对象
	 * @return 返回异常信息内容
	 */
	public static String getExceptionString(Exception e) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		e.printStackTrace(ps);
		String msg = os.toString();
		return msg;
	}

	// /**
	// * 将异常对象封装成前台展示的异常DTO对象
	// * @param e 异常对象
	// * @return 返回异常信息Dto
	// */
	// public static ExceptionDto getExceptionDto(Exception e) {
	// ExceptionDto dto = new ExceptionDto();
	// dto.setFlag(true);
	// //系统内部异常
	// if (e instanceof BssRuntimeException) {
	// BssRuntimeException be = (BssRuntimeException) e;
	// dto.setExceptionCode(be.getResult().getCode());
	// dto.setExceptionMsg(be.getMessage());
	// dto.setExceptionStack(getExceptionString(be));
	// } else {
	// dto.setExceptionCode(-9999);
	// dto.setExceptionMsg("未知异常" + e.getMessage() == null ? "" :
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
	 * 用于处理前台通过GET提交中文乱码的问题，做一个编码转换
	 * 
	 * @param originalString
	 *            源字符串
	 * @return 编码转换后的字符串
	 */
	public static String changeEncode(String originalString) {
		try {
			return new String(originalString.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 复制List对象
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
			// LOG.error("对象拷贝异常:" + e.getMessage());
			// throw new SmoException(ErrorDomain.ERROR_COPY_DATA_CODE_1058,
			// "数据拷贝异常:" + e.getMessage());
		}
		return null;
	}

	/**
	 * 拷贝2个对象的一般属性，不包括集合类属性,目标对象的集合类被初始化为空集合 add by lsw
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
			// 过滤掉集合属性

			if (!(fields[i].getType().isAssignableFrom(Set.class) || fields[i].getType().isAssignableFrom(Collection.class))) {

				PropertyUtils.setSimpleProperty(newObj, name, PropertyUtils.getSimpleProperty(srcObj, name));

			} else {
				PropertyUtils.setSimpleProperty(newObj, name, new HashSet());
			}

		}

	}

	/**
	 * 深度拷贝2个对象,包扩关联对象 add by lsw
	 * 
	 * @param srcObj
	 * @throws IOException
	 *             , ClassNotFoundException
	 * 
	 */
	public static Object copyObject(Object srcObj) throws IOException, ClassNotFoundException {
		// 利用对象序列化技术
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(srcObj);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		return in.readObject();

	}

	/**
	 * 深度拷贝对象,包扩关联对象,返回拷贝数组 add by lsw
	 * 
	 * @param srcObj
	 * @param count
	 *            拷贝个数
	 * @throws IOException
	 *             , ClassNotFoundException
	 * 
	 */
	public static Object[] copyObject(Object srcObj, int count) throws IOException, ClassNotFoundException {
		// 利用对象序列化技术
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
	 * 判断字符串中是否只包含数字
	 * 
	 * @param 字符串
	 * @return true-只包含数字,false-其他任何情况
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
