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
 * 读取.properties文件.
 * 
 * @version
 * @author
 */

public class Configuration {
	private Properties properties = new Properties();
	private final static Configuration cfg = null;

	/**
	 *装载属性文件
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
	 * 事例化
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
	 * 得到一个键值
	 * 
	 * @param key
	 *            String
	 * @return String
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 修改properties文件
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
			prop.load(fis);// 装载
			prop.setProperty(key, value);// 设置
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
