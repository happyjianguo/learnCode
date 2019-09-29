package cn.yufu.SDMTPlatform.commons.log;

import java.io.Serializable;

/**
 * 保存日志的上下文信息，如员工号、日志开关等
 * 
 * @author mengfp
 *
 */
public class LogContext implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1824909131431581344L;
	private String id;
	private boolean logSwitch;

	public LogContext(String id, boolean logSwitch) {
		this.id = id;
		this.logSwitch = logSwitch;
	}

	public String getStaff() {
		return id;
	}

	public void setStaff(String id) {
		this.id = id;
	}

	/**
	 * 日志开关
	 * 
	 * @return true表示该员工开启了日志,false表示关闭日志
	 */
	public boolean getLogSwitch() {
		return logSwitch;
	}

	public void setLogSwitch(boolean logSwitch) {
		this.logSwitch = logSwitch;
	}

}
