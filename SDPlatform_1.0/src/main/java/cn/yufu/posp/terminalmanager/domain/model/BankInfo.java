package cn.yufu.posp.terminalmanager.domain.model;

/**
 * BankInfo entity. @author MyEclipse Persistence Tools
 */

public class BankInfo implements java.io.Serializable {

	// Fields

	private BankInfoId id;

	// Constructors

	/** default constructor */
	public BankInfo() {
	}

	/** full constructor */
	public BankInfo(BankInfoId id) {
		this.id = id;
	}

	// Property accessors

	public BankInfoId getId() {
		return this.id;
	}

	public void setId(BankInfoId id) {
		this.id = id;
	}

}