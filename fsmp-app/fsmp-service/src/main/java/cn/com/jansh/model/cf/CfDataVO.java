/**
 * CfDataVO.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月20日
 */
package cn.com.jansh.model.cf;

import java.util.ArrayList;

/**
 * 话费流量充值平台-接入者商品信息xml映射实体
 * @author duanmuyn
 * @version 1.0
 */
public class CfDataVO {

	private String errorCode;
	
	private String errorMsg;
	
	private String tokUrl;
	
	private ArrayList<RecMenu> data;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the tokUrl
	 */
	public String getTokUrl() {
		return tokUrl;
	}

	/**
	 * @param tokUrl the tokUrl to set
	 */
	public void setTokUrl(String tokUrl) {
		this.tokUrl = tokUrl;
	}

	/**
	 * @return the data
	 */
	public ArrayList<RecMenu> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ArrayList<RecMenu> data) {
		this.data = data;
	}

}
