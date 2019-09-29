package com.jansh.core.exception;

/**
 * 定制 errorCode
 * 
 * @author nie
 *
 */
public class DefaultErrorCode implements ErrorCode {

	private final String value;

	public DefaultErrorCode(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
