package cn.com.jansh.dao.entity;

public class WxdErrMsg {

	private Integer autoId;
	private String errorName;
	private String errorCode;
	private String errorDes;
	private String insertTime;
	private String updateTime;
	
	public Integer getAutoId() {
		return autoId;
	}
	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDes() {
		return errorDes;
	}
	public void setErrorDes(String errorDes) {
		this.errorDes = errorDes;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "WxdErrMsg [autoId=" + autoId + ", errorName=" + errorName + ", errorCode=" + errorCode + ", errorDes="
				+ errorDes + ", insertTime=" + insertTime + ", updateTime=" + updateTime + "]";
	}
}
