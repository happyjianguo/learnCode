package cn.yufu.posp.sysparam.domain.model;

/**
 * AreaCodeInfo entity. @author MyEclipse Persistence Tools
 */

public class AreaCodeInfo implements java.io.Serializable {

	// Fields

	private String areaCode;
	private String areaName;

	// Constructors

	/** default constructor */
	public AreaCodeInfo() {
	}

	/** full constructor */
	public AreaCodeInfo(String areaCode, String areaName) {
		this.areaCode = areaCode;
		this.areaName = areaName;
	}

	// Property accessors

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}