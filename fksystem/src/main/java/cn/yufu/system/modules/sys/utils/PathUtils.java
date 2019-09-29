package cn.yufu.system.modules.sys.utils;

import java.net.URL;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathUtils {
	private static Logger log = LoggerFactory.getLogger(PathUtils.class);

	/**
	 *
	 * 获取class 所在的jar包所在目录下的resources目录的绝对路径
	 *
	 * 未经作者同意，请勿修改此文件代码
	 *
	 *
	 * @author hankai
	 *         20141203
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getResoucesPath(Class c) {
		URL url = c.getProtectionDomain().getCodeSource().getLocation();

		String filePath = getPathFromUrl(url);

		return filePath + "resources/";
	}

	/**
	 * 从lib里的jar包里获取resourse的绝对路径
	 *
	 * @param c
	 * @return
	 */
	public static String getResoucesPathFromLib(Class c) {
		URL url = c.getProtectionDomain().getCodeSource().getLocation();
		String r = "";
		String filePath = getPathFromUrl(url);
		String lib = "lib";
		if (filePath.contains(lib)) {
			r = filePath.substring(0, filePath.length() - (lib.length() + 1));
		}

		return r + "resources/";
	}

	/**
	 * 处理开发环境和部署环境路径不一样的问题
	 *
	 * @param url
	 * @return
	 */
	private static String getPathFromUrl(URL url) {
		String filePath = null;
		try {
			filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
		} catch (Exception e) {
			log.error("转换路径编码异常", e);
		}

		log.debug("原始路径："+ filePath);
		if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
			// 截取路径中的jar包名
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		} else
		if (filePath.endsWith("classes/")) {
			int i = filePath.lastIndexOf("classes/");
			filePath = filePath.substring(0, i);
		}

		log.debug("处理后的路径：" + filePath);
		return filePath;

	}

	/**
	 *
	 * 获取class 所在的jar包所在目录绝对路径
	 *
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getCurrentPath(Class c) {
		URL url = c.getProtectionDomain().getCodeSource().getLocation();

		String filePath = getPathFromUrl(url);

		return filePath;
	}
}
