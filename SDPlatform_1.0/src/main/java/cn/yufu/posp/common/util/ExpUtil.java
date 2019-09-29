package cn.yufu.posp.common.util;

import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ExpUtil {
	private static Logger log = Logger.getLogger("ExpUtil");

	public static String trsMessage(Throwable ex) {
		String message = getTrsMessage(ex);
		if ((message == null) || (message.trim().equals("")))
			return ex.getLocalizedMessage() == null ? "" : ex.getLocalizedMessage();
		return message + "¡£\n" + (ex.getLocalizedMessage() == null ? "" : ex.getLocalizedMessage());
	}

	public static String getTrsMessage(Throwable ex) {
		if (ex == null) {
			return null;
		}
		if ((ex instanceof SQLException)) {
			return getSQLExpMsg(ex);
		}
		return getOtherExpMsg(ex);
	}

	public static String outputException(Throwable e) {
		StackTraceElement[] stackTraceElement = e.getStackTrace();
		StringBuffer output = new StringBuffer();

		output.append("\n" + e.getClass().getName() + " : " + e.getLocalizedMessage() + "\n");
		for (int i = 0; i < stackTraceElement.length; i++) {
			output.append("\tat " + stackTraceElement[i].toString() + "\n");
		}
		return output.toString();
	}

	public static String outputExceptionToJsp(Throwable e) {
		String expStr = outputException(e);
		return expStr.replace("\n", "<br>").replace(" ", "&nbsp;").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
	}

	private static String getOtherExpMsg(Throwable ex) {
		String key = "exp." + ex.getClass().getSimpleName();
//		String message = ResConfig.getProperty(key);
//		return message;
		return key;
	}

	private static String getSQLExpMsg(Throwable ex) {
		SQLException e = (SQLException) ex;
		String key = "";
		String dbType = getDbType(e);

		if (dbType.equals("oracle")) {
			int code = e.getErrorCode();
			for (int i = 0; i < 5 - code; i++) {
				code = 0 + code;
			}
			key = "exp.SQLException." + dbType + "." + "ORA-" + code;
		}

		//String message = ResConfig.getProperty(key);
		//return message;
		return key;
	}

	private static String getDbType(SQLException e) {
		String message = e.getLocalizedMessage();

		if (message.indexOf("ORA-") != -1)
			return "oracle";
		return "oracle";
	}
}