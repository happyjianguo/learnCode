package cn.yufu.core.common.exception;

public class ThtfException extends Exception {

	/**
	 * 异常描述
	 */
	private String msg;

	/**
	 * 不带参构造方法
	 * 
	 * @roseuid 43BC876B00B4
	 */
	public ThtfException() {
		super();
	}

	/**
	 * 带参构造方法 super(msg); this.msg = msg;
	 * 
	 * @param msg
	 *            String
	 */
	public ThtfException(String msg) {
		super(msg);
		this.msg = msg;
	}

	/**
	 * 异常描述
	 * 
	 * @return String
	 */
	@Override
	public String getMessage() {
		return msg;
	}
}
