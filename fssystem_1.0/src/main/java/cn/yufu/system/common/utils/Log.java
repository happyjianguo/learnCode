package cn.yufu.system.common.utils;

import cn.yufu.system.common.log.LogContext;
import cn.yufu.system.common.log.LogContextHolder;
import cn.yufu.system.common.log.LoggerWrapper;



/**
 * 功能		:	bss封装的日志类,所有的bss的代码请引用这个类
 * 用法示例	:	private static Log log = Log.getLog(Sample.class);
 *          	log.debug("my name is {}", "mengfp");
 * @author mengfp
 *
 */
public class Log {

	private LoggerWrapper log;
	private static final String defaultLogName = "com.linkage.bss.commons.util.Log";

	/**
	 * 构造函数
	 * @param name
	 */
	private Log(String name) {
		log = new LoggerWrapper(name);
	}

	/**
	 * 静态构造函数,根据name构造log,客户端主调用这个方法
	 * @param name
	 * @return
	 */
	public static Log getLog(String name) {
		String s = name;
		if (s == null)
			s = defaultLogName;
		return new Log(s);
	}

	/**
	 * 静态构造函数,根据name构造log,客户端主调用这个方法
	 * @param clazz
	 * @return
	 */
	public static Log getLog(Class clazz) {
		String s = defaultLogName;
		if (clazz != null)
			s = clazz.getName();
		return getLog(s);
	}

	/**
	 * 判断日志的记录模式
	 * @return 枚举类型LogModel,分别为RunTimeClose,RunTimeOpen,RunTimeClose
	 */
	private static LogModel getLogModel() {
		LogModel model = LogModel.RunTimeClose;
		LogContext logContext = LogContextHolder.getLogContext();
		if (logContext != null) {
			if (logContext.getLogSwitch()) {
				model = LogModel.RunTimeOpen;
			} else
				model = LogModel.RunTimeClose;
		} else {
			//日志模式为:开发模式
			model = LogModel.Develop;
		}
		return model;
	}

	/**
	 * 判断是否开日志,logModel为开发模式默认为开日志,由logback.xml去判断
	 * @param model
	 * @return
	 */
	private static boolean isLogOpen(LogModel model) {
		if (model == LogModel.RunTimeOpen || model == LogModel.Develop)
			return true;
		else
			return false;
	}

	/**
	 * 默认判断日志开关方法
	 * @return
	 */
	private static boolean isLogOpen() {
		return isLogOpen(getLogModel());
	}

	/**
	 * 增加日志中的员工号，线程号等信息
	 * @return
	 */
	private static String buildLogContextInfo() {
		long threadId = Thread.currentThread().getId();
		String staff = null;
		LogContext logContext = LogContextHolder.getLogContext();
		if (logContext != null) {
			staff = logContext.getStaff();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("BSS_LOGGER:threadId=");
		sb.append(threadId);
		sb.append(",staff=");
		sb.append(staff);
		sb.append(",info=");
		return sb.toString();
	}

	/**
	 * 功能	：记录debug日志
	 * 示例	：log.debug("My name is {}, i am {} years old", "zhaox", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void debug(String message, Object... args) {
		try{
			LogModel model = getLogModel();
			if (isLogOpen(model)) {
				if (model == LogModel.RunTimeOpen) {
					log.debug(buildLogContextInfo() + message, args);
				} else
					log.debug(message, args);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}

	}

	/**
	 * 功能	：记录info日志
	 * 示例	：log.info("My name is {}, i am {} years old", "zhaox", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void info(String message, Object... args) {
		try {
			LogModel model = getLogModel();
			if (isLogOpen(model)) {
				if (model == LogModel.RunTimeOpen) {
					log.info(buildLogContextInfo() + message, args);
				} else
					log.info(message, args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录warn日志
	 * 示例	：log.warn("My name is {}, i am {} years old", "zhaox", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void warn(String message, Object... args) {
		try {
			LogModel model = getLogModel();
			if (isLogOpen(model)) {
				if (model == LogModel.RunTimeOpen) {
					log.warn(buildLogContextInfo() + message, args);
				} else
					log.warn(message, args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录trace日志
	 * 示例	：log.trace("My name is {}, i am {} years old", "zhaox", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void trace(String message, Object... args) {
		try {
			LogModel model = getLogModel();
			if (isLogOpen(model)) {
				if (model == LogModel.RunTimeOpen) {
					log.trace(buildLogContextInfo() + message, args);
				} else
					log.trace(message, args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录error日志
	 * 示例	：log.error("My name is {}, i am {} years old", "zhaox", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void error(String message, Object... args) {
		try {
			LogModel model = getLogModel();
			//error级别不受运行时控制 if (isLogOpen(model)){
			if (model == LogModel.RunTimeOpen) {
				log.error(buildLogContextInfo() + message, args);
			} else
				log.error(message, args);
			//}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录error日志
	 * 示例	：log.error("My name is " + "zhaox", exception);
	 * 
	 * @param message	日志消息,
	 * @param e			异常
	 */
	public void error(String message, Throwable e) {
		try {
			LogModel model = getLogModel();
			//error级别不受运行时控制if (isLogOpen(model)){
			if (model == LogModel.RunTimeOpen) {
				log.error(buildLogContextInfo() + message, e);
			} else
				log.error(message, e);
			//}
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}
	/**
	 * 是否debug可用
	 * @return
	 */
	public boolean isDebugEnabled() {
		try {
			if (log == null)
				return false;

			return isLogOpen() && log.isDebugEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否error级别可用
	 * @return
	 */
	public boolean isErrorEnabled() {
		try {
			if (log == null)
				return false;
			//mengfp 2009-8-24 modify : error级别的日志不受运行时开关限制,默认由logback.xml的配置来决定是否打印
			//return isLogOpen() && _log.isErrorEnabled();
			return log.isErrorEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否info级别可用
	 * @return
	 */
	public boolean isInfoEnabled() {
		try {
			if (log == null)
				return false;
			return isLogOpen() && log.isInfoEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否trace级别可用
	 * @return
	 */
	public boolean isTraceEnabled() {
		try {
			if (log == null)
				return false;
			return isLogOpen() && log.isTraceEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否warn级别可用
	 * @return
	 */
	public boolean isWarnEnabled() {
		try {
			if (log == null)
				return false;
			return isLogOpen() && log.isWarnEnabled();
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 日志状态，分别为'运行时开启','运行时关闭','开发模式'
	 * @author mengfp
	 *
	 */
	private enum LogModel {
		RunTimeOpen, RunTimeClose, Develop
	}
}
