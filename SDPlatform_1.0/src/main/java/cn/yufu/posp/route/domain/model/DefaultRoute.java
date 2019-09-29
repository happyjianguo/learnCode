package cn.yufu.posp.route.domain.model;

/**
 * DefaultRoute entity. @author MyEclipse Persistence Tools
 */

public class DefaultRoute implements java.io.Serializable {

	// Fields

	private DefaultRouteId id;

	// Constructors

	/** default constructor */
	public DefaultRoute() {
	}

	/** full constructor */
	public DefaultRoute(DefaultRouteId id) {
		this.id = id;
	}

	// Property accessors

	public DefaultRouteId getId() {
		return this.id;
	}

	public void setId(DefaultRouteId id) {
		this.id = id;
	}

}