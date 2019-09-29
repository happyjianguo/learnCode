package cn.yufu.posp.terminalmanager.domain.model;

/**
 * Banktype entity. @author MyEclipse Persistence Tools
 */

public class Banktype implements java.io.Serializable {

	// Fields

	private BanktypeId id;

	// Constructors

	/** default constructor */
	public Banktype() {
	}

	/** full constructor */
	public Banktype(BanktypeId id) {
		this.id = id;
	}

	// Property accessors

	public BanktypeId getId() {
		return this.id;
	}

	public void setId(BanktypeId id) {
		this.id = id;
	}

}