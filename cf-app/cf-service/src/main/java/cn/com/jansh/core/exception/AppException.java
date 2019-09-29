package cn.com.jansh.core.exception;

/**
 * 应用异常类
 * 
 * @author nie
 *
 */
public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5320954084025832052L;

	/**
	 * 错误码
	 */
	private String errCode = "error";

	/**
	 * 跳转地址
	 */
	private String toUrl;

	/**
	 * 返回地址的modelbean
	 */
	private Object obj;

	/**
	 * 
	 * @param errorCode
	 *            错误码
	 */
	public AppException(ErrorCode errorCode) {
		this.errCode = errorCode.value();
	}
	
	/**
	 * 
	 * @param errorCode
	 *            错误码
	 * @param toUrl
	 *            跳转地址
	 */
	public AppException(ErrorCode errorCode, String toUrl) {
		this.errCode = errorCode.value();
		this.toUrl = toUrl;
	}

	/**
	 * 
	 * @param errorCode
	 *            错误码
	 * @param obj
	 *            modelbean
	 */
	public AppException(ErrorCode errorCode, Object obj) {
		this.errCode = errorCode.value();
		this.obj = obj;
	}

	public AppException(ErrorCode errorCode, Throwable rootCause) {
		super(rootCause);
		this.errCode = errorCode.value();
	}

	public AppException(ErrorCode errorCode, Object obj, Throwable rootCause) {
		super(rootCause);
		this.errCode = errorCode.value();
		this.obj = obj;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
