package cn.yufu.posp.keyManager.domain.model;

import java.math.BigDecimal;

/**
 * PospZmkKey entity. @author MyEclipse Persistence Tools
 */

public class PospZmkKey implements java.io.Serializable {

	// Fields

	private String moduleId;
	private String masterKey;
	private String pinKey;
	private String macKey;
	private String keyIndex;
	private String zmkmasterKey;
	private String lmkpinKey;
	private String lmkmacKey;

	// Constructors

	/** default constructor */
	public PospZmkKey() {
	}

	/** full constructor */
	public PospZmkKey(String moduleId, String masterKey, String pinKey, String macKey, String keyIndex, String zmkmasterKey, String lmkpinKey,
			String lmkmacKey) {
		this.moduleId = moduleId;
		this.masterKey = masterKey;
		this.pinKey = pinKey;
		this.macKey = macKey;
		this.keyIndex = keyIndex;
		this.zmkmasterKey = zmkmasterKey;
		this.lmkpinKey = lmkpinKey;
		this.lmkmacKey = lmkmacKey;
	}

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PospZmkKey))
			return false;
		PospZmkKey castOther = (PospZmkKey) other;

		return ((this.getModuleId() == castOther.getModuleId()) || (this.getModuleId() != null && castOther.getModuleId() != null && this.getModuleId().equals(
				castOther.getModuleId())))
				&& ((this.getMasterKey() == castOther.getMasterKey()) || (this.getMasterKey() != null && castOther.getMasterKey() != null && this
						.getMasterKey().equals(castOther.getMasterKey())))
				&& ((this.getPinKey() == castOther.getPinKey()) || (this.getPinKey() != null && castOther.getPinKey() != null && this.getPinKey().equals(
						castOther.getPinKey())))
				&& ((this.getMacKey() == castOther.getMacKey()) || (this.getMacKey() != null && castOther.getMacKey() != null && this.getMacKey().equals(
						castOther.getMacKey())))
				&& ((this.getKeyIndex() == castOther.getKeyIndex()) || (this.getKeyIndex() != null && castOther.getKeyIndex() != null && this.getKeyIndex()
						.equals(castOther.getKeyIndex())))
				&& ((this.getZmkmasterKey() == castOther.getZmkmasterKey()) || (this.getZmkmasterKey() != null && castOther.getZmkmasterKey() != null && this
						.getZmkmasterKey().equals(castOther.getZmkmasterKey())))
				&& ((this.getLmkpinKey() == castOther.getLmkpinKey()) || (this.getLmkpinKey() != null && castOther.getLmkpinKey() != null && this
						.getLmkpinKey().equals(castOther.getLmkpinKey())))
				&& ((this.getLmkmacKey() == castOther.getLmkmacKey()) || (this.getLmkmacKey() != null && castOther.getLmkmacKey() != null && this
						.getLmkmacKey().equals(castOther.getLmkmacKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result + (getMasterKey() == null ? 0 : this.getMasterKey().hashCode());
		result = 37 * result + (getPinKey() == null ? 0 : this.getPinKey().hashCode());
		result = 37 * result + (getMacKey() == null ? 0 : this.getMacKey().hashCode());
		result = 37 * result + (getKeyIndex() == null ? 0 : this.getKeyIndex().hashCode());
		result = 37 * result + (getZmkmasterKey() == null ? 0 : this.getZmkmasterKey().hashCode());
		result = 37 * result + (getLmkpinKey() == null ? 0 : this.getLmkpinKey().hashCode());
		result = 37 * result + (getLmkmacKey() == null ? 0 : this.getLmkmacKey().hashCode());
		return result;
	}

}