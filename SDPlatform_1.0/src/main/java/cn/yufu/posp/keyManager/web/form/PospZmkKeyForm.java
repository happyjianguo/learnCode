package cn.yufu.posp.keyManager.web.form;

import java.math.BigDecimal;

import cn.yufu.core.web.form.BaseForm;

/**
 * PospZmkKey entity. @author MyEclipse Persistence Tools
 */

public class PospZmkKeyForm extends BaseForm  {

	// Fields

	private String moduleId;
	private String masterKey;
	private String pinKey;
	private String macKey;
	private String keyIndex;
	private String zmkmasterKey;
	private String lmkpinKey;
	private String lmkmacKey;

	// Property accessors

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getMasterKey() {
		return this.masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getPinKey() {
		return this.pinKey;
	}

	public void setPinKey(String pinKey) {
		this.pinKey = pinKey;
	}

	public String getMacKey() {
		return this.macKey;
	}

	public void setMacKey(String macKey) {
		this.macKey = macKey;
	}

	public String getKeyIndex() {
		return this.keyIndex;
	}

	public void setKeyIndex(String keyIndex) {
		this.keyIndex = keyIndex;
	}

	public String getZmkmasterKey() {
		return this.zmkmasterKey;
	}

	public void setZmkmasterKey(String zmkmasterKey) {
		this.zmkmasterKey = zmkmasterKey;
	}

	public String getLmkpinKey() {
		return this.lmkpinKey;
	}

	public void setLmkpinKey(String lmkpinKey) {
		this.lmkpinKey = lmkpinKey;
	}

	public String getLmkmacKey() {
		return this.lmkmacKey;
	}

	public void setLmkmacKey(String lmkmacKey) {
		this.lmkmacKey = lmkmacKey;
	}

}