package cn.yufu.core.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author mengfp
 * @version 2014-08-21
 */
public class ConstManagerImpl implements ConstManager {

	private static Map constMap = null;

	// private static Log logger = Log.getLog(ConstManagerImpl.class);

	private static String DEFAULT_CONFIG_FILE = "constConfig.properties";

	private static String CONFIG_FILE = null;

	private static boolean PARAM_VALID = true;

	private static String PRE_PARAM = "CONST_";

	private static ConstManager manager;

	public ConstManagerImpl() {
	}

	public static ConstManager getInstance() {
		if (manager == null) {
			manager = new ConstManagerImpl();
		}
		return manager;
	}

	/**
	 * ��keyȡֵ
	 */
	public Object getConst(Object key) {
		if (constMap == null) {
			constMap = loadConst();
			if (constMap == null) {
				// logger.error("�����ļ������ڻ��ȡʧ�ܣ�");
				throw new RuntimeException("�����ļ������ڻ��ȡʧ�ܣ�");
			}
			if (constMap.size() == 0) {
				// logger.error("�����ļ������������ݣ�");
				throw new RuntimeException("�����ļ������������ݣ�");
			}
		}
		if (constMap.containsKey(key)) {
			return constMap.get(key);
		} else {
			// logger.error("�����ļ���û��" + key + "���������");
			return null;
		}
	}

	public Map getConstMap() {
		if (constMap == null) {
			constMap = loadConst();
		}
		return constMap;
	}

	/**
	 * ��ʼ����������,�����˳���ǰ׺��У�顣
	 */
	private Map loadConst() {
		String filePath;
		if (CONFIG_FILE != null) {
			filePath = CONFIG_FILE;
		} else {
			filePath = DEFAULT_CONFIG_FILE;
		}
		// InputStream input = ClassLoader.getSystemResourceAsStream(filePath);
		InputStream input = manager.getClass().getClassLoader().getResourceAsStream(filePath);
		Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			// logger.error(e.toString());
			return null;
		} catch (Exception e) {
			// logger.error(e.toString());
			return null;
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				// logger.error(e.toString());
			}
		}

		Map map = new HashMap();
		Enumeration enum1 = prop.keys();
		while (enum1.hasMoreElements()) {
			try {
				String id = enum1.nextElement().toString();
				map.put(id, new String(prop.getProperty(id).getBytes("ISO-8859-1"), "GBK"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				// logger.error("�ַ�����֧��", e1);
			}
		}

		if (PARAM_VALID) {
			if (!validateConst(map)) {
				// logger.error("��������û����[" + PRE_PARAM + "]��ͷ");
				throw new RuntimeException("��������û����[" + PRE_PARAM + "]��ͷ");
			}
		}
		return map;
	}

	private boolean validateConst(Map map) {
		Set keys = map.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			if (!key.substring(0, PRE_PARAM.length()).equals(PRE_PARAM)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return
	 */
	public String getCONFIG_FILE() {
		return CONFIG_FILE;
	}

	/**
	 * @param string
	 */
	public void setCONFIG_FILE(String string) {
		CONFIG_FILE = string;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bss.crm.comanager.common.ConstManager#getString(java.lang.Object)
	 */
	public String getString(Object key) {
		Object obj = getConst(key);
		return obj == null ? null : obj.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bss.crm.comanager.common.ConstManager#getInteger(java.lang.Object)
	 */
	public Integer getInteger(Object key) {
		try {
			return Integer.valueOf(getString(key));
		} catch (Exception e) {
			// logger.error("ȡ����ʱ����KEY=" + key + "\n" + e.toString());
			throw new RuntimeException("ȡ����ʱ����KEY=" + key + "\n" + e.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bss.crm.comanager.common.ConstManager#getInt(java.lang.Object)
	 */
	public int getInt(Object key) {
		return getInteger(key).intValue();
	}

}
