/**
 * ITradeCommException.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.ibank.common;

/**
 * 功能: 通讯异常类<br>
 * @author YangFan
 * @version 1.0
 */
public class ITradeCommException extends Exception {

	private static final long serialVersionUID = 8731015071861491693L;

	public ITradeCommException() {
		super();
	}

	public ITradeCommException(String errorMsg) {
		super(errorMsg);
	}

	public ITradeCommException(String errorMsg, Throwable t) {
		super(errorMsg, t);
	}
}
