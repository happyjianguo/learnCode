package cn.com.jansh.core.web.servlet;

/**
 * 返回对象视图，如json、xml等
 * 
 * @author nie
 *
 */
public class ViewObject<T> {

	private String errorCode;

	private String errorMsg;

	private String tokUrl;

	private T data;

	public ViewObject() {
		this.errorCode = "000000";
	}

	public ViewObject(T data) {
		this.errorCode = "000000";
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getTokUrl() {
		return tokUrl;
	}

	public void setTokUrl(String tokUrl) {
		this.tokUrl = tokUrl;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ViewObject [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", tokUrl=" + tokUrl + ", data="
				+ data + "]";
	}
}
