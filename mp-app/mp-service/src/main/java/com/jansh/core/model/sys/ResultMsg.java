package com.jansh.core.model.sys;

import javax.servlet.http.HttpServletRequest;

import com.jansh.core.constant.Operation;

public class ResultMsg {

	private String resultflag;
	/**
	 * 提示信息编码
	 */
	private String msgcode;
	/**
	 * 提示信息参数
	 */
	private String[] msgargs;

	private String msg;

	private HttpServletRequest request;

	public ResultMsg(String msgcode, String[] msgargs) {
		this.resultflag = "1";
		this.msgcode = msgcode;
		this.msgargs = msgargs;
	}

	public ResultMsg(boolean resultflag, String msgcode, String[] msgargs) {
		if (resultflag) {
			this.resultflag = "1";
		} else {
			this.resultflag = "0";
		}
		this.msgcode = msgcode;
		this.msgargs = msgargs;
	}

	public ResultMsg(Operation operation) {
		this.resultflag = "1";
		switch (operation) {
		case CREATE:
			this.msgcode = "result.success.create";
			break;
		case DELETE:
			this.msgcode = "result.success.delete";
			break;
		case UPDATE:
			this.msgcode = "result.success.update";
			break;
		case RETRIEVE:
			this.msgcode = "result.success.retrieve";
			break;
		case DEFULT:
			this.msgcode = "result.success.operation";
			break;
		default:
			this.msgcode = "result.success.operation";
			break;
		}
	}

	public ResultMsg() {
		this.resultflag = "1";
		this.msgcode = "result.success.operation";
	}

	public String getMsgcode() {
		return msgcode;
	}

	public String[] getMsgargs() {
		return msgargs;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultflag() {
		return resultflag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
