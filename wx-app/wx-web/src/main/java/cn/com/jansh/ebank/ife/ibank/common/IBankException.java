/**
 * IBankException.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.ibank.common;

/**
 * 功能: IBank系统异常<br>
 * @author YangFan
 * @version 1.0
 */
public class IBankException extends Exception {

	private static final long serialVersionUID = 4434909341747702892L;

	public IBankException() {
		super();
	}

	public IBankException(String errorMsg) {
		super(errorMsg);
	}

	public IBankException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}
}
