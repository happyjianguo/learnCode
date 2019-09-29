/**
 * PubsSysBase.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月11日
 */
package cn.com.jansh.dao.entity;
/**
 * 系统参数
 * 
 *@author Mr.wong
 *
 */
public class PubsSysBase {

	//自动Id
	private String autoId;
	//参数名
	private String sysBaseId;
	//参数值
	private String sysBaseValue;
	//参数描述
	private String sysBaseMemo;

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
