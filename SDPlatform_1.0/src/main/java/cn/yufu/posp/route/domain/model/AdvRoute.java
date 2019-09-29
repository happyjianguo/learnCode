package cn.yufu.posp.route.domain.model;

/**
 * AdvRoute entity. @author MyEclipse Persistence Tools
 */

public class AdvRoute implements java.io.Serializable {

	// Fields

	private AdvRouteId id;

	// Constructors

	/** default constructor */
	public AdvRoute() {
	}

	/** full constructor */
	public AdvRoute(AdvRouteId id) {
		this.id = id;
	}

	// Property accessors

	public AdvRouteId getId() {
		return this.id;
	}

	public void setId(AdvRouteId id) {
		this.id = id;
	}

}