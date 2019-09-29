package cn.yufu.posp.cardtype.domain.model;

/**
 * Cardtype entity. @author MyEclipse Persistence Tools
 */

public class Cardtype1 implements java.io.Serializable {

	// Fields

	private CardtypeId1 id;

	// Constructors

	/** default constructor */
	public Cardtype1() {
	}

	/** full constructor */
	public Cardtype1(CardtypeId1 id) {
		this.id = id;
	}

	// Property accessors

	public CardtypeId1 getId() {
		return this.id;
	}

	public void setId(CardtypeId1 id) {
		this.id = id;
	}

}