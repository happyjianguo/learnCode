package cn.yufu.core.common.log;

import org.apache.log4j.*;

import cn.yufu.core.common.util.*;

/**
 * 日志记录
 * 
 * @author
 * @since
 */
public final class Log {
	static {
		// 初始化 Log4J
		BasicConfigurator.configure();
		String propPath = Configuration.getInstance(StaticData.STATICDATAPROPPATH).getValue("log4jpropath");
		PropertyConfigurator.configure(propPath);
	}
	// 设置默认LOG事例化
	static Category root = Category.getRoot();

	/**
	 * 获取LOG事例化
	 * 
	 * @param module
	 *            String
	 * @return Category
	 */
	public static Category getLogger(String module) {
		if (module != null && module.length() > 0) {
			return Category.getInstance(module);
		} else {
			return root;
		}
	}

	/**
	 * 创建日志
	 * 
	 * @param t
	 *            Throwable
	 * @param msg
	 *            String
	 * @param module
	 *            String
	 */
	public static void log(Throwable t, String msg, String module) {
		Debug.println(" in Log !!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Category logger = getLogger(module);
		// 创建日志
		logger.log("", Priority.ERROR, msg, t);
	}
}
