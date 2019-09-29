package cn.yufu.core.common.log;

import org.apache.log4j.*;

import cn.yufu.core.common.util.*;

/**
 * ��־��¼
 * 
 * @author
 * @since
 */
public final class Log {
	static {
		// ��ʼ�� Log4J
		BasicConfigurator.configure();
		String propPath = Configuration.getInstance(StaticData.STATICDATAPROPPATH).getValue("log4jpropath");
		PropertyConfigurator.configure(propPath);
	}
	// ����Ĭ��LOG������
	static Category root = Category.getRoot();

	/**
	 * ��ȡLOG������
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
	 * ������־
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
		// ������־
		logger.log("", Priority.ERROR, msg, t);
	}
}
