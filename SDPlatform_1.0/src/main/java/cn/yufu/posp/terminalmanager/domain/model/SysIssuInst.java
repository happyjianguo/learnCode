package cn.yufu.posp.terminalmanager.domain.model;

import java.math.BigDecimal;

/**
 * SysIssuInst entity. @author MyEclipse Persistence Tools
 */

public class SysIssuInst implements java.io.Serializable {

	// Fields

	private String issuId;
	private String issuName;
	private BigDecimal statFlag;

	// Constructors

	/** default constructor */
	public SysIssuInst() {
	}

	/** full constructor */
	public SysIssuInst(String issuId, String issuName, BigDecimal statFlag) {
		this.issuId = issuId;
		this.issuName = issuName;
		this.statFlag = statFlag;
	}

	// Property accessors

	public String getIssuId() {
		return this.issuId;
	}

	public void setIssuId(String issuId) {
		this.issuId = issuId;
	}

	public String getIssuName() {
		return this.issuName;
	}

	public void setIssuName(String issuName) {
		this.issuName = issuName;
	}

	public BigDecimal getStatFlag() {
		return this.statFlag;
	}

	public void setStatFlag(BigDecimal statFlag) {
		this.statFlag = statFlag;
	}

}