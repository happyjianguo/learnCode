package cn.yufu.core.common.util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

import cn.yufu.core.common.log.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * 
 * ��ȡ.properties�ļ�.
 * 
 * @version
 * @author
 */

public class Configuration {
	private Properties properties = new Properties();
	private final static Configuration cfg = null;

	/**
	 *װ�������ļ�
	 * 
	 * @param path
	 *            String
	 */

	private Configuration(String path) {
		InputStream is = null;
		try {
			is = getClass().getClassLoader().getResourceAsStream(path);
			properties.load(is);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				Log.log(exception, "", null);
			}
		}
	}

	/**
	 * ������
	 * 
	 * @param path
	 *            String
	 * @return Configuration
	 */
	public static Configuration getInstance(String path) {
		Configuration configuration = new Configuration(path);
		return configuration;
	}

	/**
	 * �õ�һ����ֵ
	 * 
	 * @param key
	 *            String
	 * @return String
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	/**
	 * �޸�properties�ļ�
	 * 
	 * @param path
	 *            String
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public static void setProperties(String path, String key, String value) {
		Properties prop = new Properties();
		FileOutputStream fos;
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(path));
			prop.load(fis);// װ��
			prop.setProperty(key, value);// ����
			fos = new FileOutputStream(new File(path));
			prop.store(fos, null);
		} catch (IOException e) {
			e.printStackTrace();
			Log.log(e, "", null);
		}
	}

	public static void main(String[] args) {
		setProperties("c:/test.properties", "aaa", "AAAA");
	}
}
