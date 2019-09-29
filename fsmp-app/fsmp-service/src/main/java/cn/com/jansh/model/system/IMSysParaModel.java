/**
 * IMSysParaModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月17日
 */
package cn.com.jansh.model.system;

/**
 * 系统参数model
 * @author Mr.wong
 * @version 1.0
 */
public class IMSysParaModel {
	
	private String autoId;
	
	private String sysBaseId;
	
	private String sysBaseValue;
	
	private String sysBaseMemo;
	
	public IMSysParaModel() {
	}

	public IMSysParaModel(String sysBaseId, String sysBaseValue) {
		super();
		this.sysBaseId = sysBaseId;
		this.sysBaseValue = sysBaseValue;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getSysBaseId() {
		return sysBaseId;
	}

	public void setSysBaseId(String sysBaseId) {
		this.sysBaseId = sysBaseId;
	}

	public String getSysBaseValue() {
		return sysBaseValue;
	}

	public void setSysBaseValue(String sysBaseValue) {
		this.sysBaseValue = sysBaseValue;
	}

	public String getSysBaseMemo() {
		return sysBaseMemo;
	}

	public void setSysBaseMemo(String sysBaseMemo) {
		this.sysBaseMemo = sysBaseMemo;
	}
	
	
}
