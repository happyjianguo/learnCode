package cn.yufu.posp.route.domain.model;

/**
 * BankcardRoute entity. @author MyEclipse Persistence Tools
 */

public class BankcardRoute implements java.io.Serializable {

	// Fields

	private BankcardRouteId id;

	// Constructors

	/** default constructor */
	public BankcardRoute() {
	}

	/** full constructor */
	public BankcardRoute(BankcardRouteId id) {
		this.id = id;
	}

	// Property accessors

	public BankcardRouteId getId() {
		return this.id;
	}

	public void setId(BankcardRouteId id) {
		this.id = id;
	}

}