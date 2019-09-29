package cn.yufu.posp.terminalmanager.domain.model;

/**
 * Cardtype entity. @author MyEclipse Persistence Tools
 */

public class Cardtype implements java.io.Serializable {

	// Fields

	private CardtypeId id;

	// Constructors

	/** default constructor */
	public Cardtype() {
	}

	/** full constructor */
	public Cardtype(CardtypeId id) {
		this.id = id;
	}

	// Property accessors

	public CardtypeId getId() {
		return this.id;
	}

	public void setId(CardtypeId id) {
		this.id = id;
	}

}