package cn.yufu.core.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 读取.properties文件.
 * 
 * @version
 * @author
 */
public class ConfigurationUtil {
	private Properties properties;

	private final static ConfigurationUtil cfg = null;

	private ConfigurationUtil(String path) {
		properties = new Properties();
		InputStream is = null;
		try {
			// is = getClass().getResourceAsStream( path );
			is = new FileInputStream(path);
			properties.load(is);
		} catch (Exception exception) {
			System.out.println("Can't read the properties file. ");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 * 事例化
	 * 
	 * @return Configuration
	 */
	public static ConfigurationUtil getInstance(String path) {
		ConfigurationUtil configuration = new ConfigurationUtil(path);
		return configuration;
	}

	/**
	 * 得到一个键值
	 * 
	 * @param key
	 * @return value
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
