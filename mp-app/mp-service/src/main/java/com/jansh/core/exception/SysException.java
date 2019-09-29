package com.jansh.core.exception;

/**
 * 系统异常类
 * 
 * @author nie
 *
 */
public class SysException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5320954084025832052L;

	/**
	 * 错误码
	 */
	private String errCode = "error";

	/**
	 * 点击跳转地址
	 */
	private String clickUrl;

	/**
	 * 返回地址的modelbean
	 */
	private Object obj;

	/**
	 * 
	 * @param errorCode
	 *            错误码
	 */
	public SysException(ErrorCode errorCode) {
		this.errCode = errorCode.value();
	}

	/**
	 * 
	 * @param errorCode
	 *            错误码
	 * @param backUrl
	 *            返回地址
	 * @param obj
	 *            modelbean
	 */
	public SysException(ErrorCode errorCode, Object obj) {
		this.errCode = errorCode.value();
		this.obj = obj;
	}

	public SysException(ErrorCode errorCode, Throwable rootCause) {
		super(rootCause);
		this.errCode = errorCode.value();
	}

	public SysException(ErrorCode errorCode, Object obj, Throwable rootCause) {
		super(rootCause);
		this.errCode = errorCode.value();
		this.obj = obj;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
