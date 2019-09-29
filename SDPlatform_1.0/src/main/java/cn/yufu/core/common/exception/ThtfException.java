package cn.yufu.core.common.exception;

public class ThtfException extends Exception {

	/**
	 * �쳣����
	 */
	private String msg;

	/**
	 * �����ι��췽��
	 * 
	 * @roseuid 43BC876B00B4
	 */
	public ThtfException() {
		super();
	}

	/**
	 * ���ι��췽�� super(msg); this.msg = msg;
	 * 
	 * @param msg
	 *            String
	 */
	public ThtfException(String msg) {
		super(msg);
		this.msg = msg;
	}

	/**
	 * �쳣����
	 * 
	 * @return String
	 */
	@Override
	public String getMessage() {
		return msg;
	}
}
