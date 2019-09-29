package cn.yufu.posp.route.domain.model;

/**
 * CardRoute entity. @author MyEclipse Persistence Tools
 */

public class CardRoute implements java.io.Serializable {

	// Fields

	private CardRouteId id;

	// Constructors

	/** default constructor */
	public CardRoute() {
	}

	/** full constructor */
	public CardRoute(CardRouteId id) {
		this.id = id;
	}

	// Property accessors

	public CardRouteId getId() {
		return this.id;
	}

	public void setId(CardRouteId id) {
		this.id = id;
	}

}