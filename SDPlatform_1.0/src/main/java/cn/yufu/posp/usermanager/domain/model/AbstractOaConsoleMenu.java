package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractOaConsoleMenu entity provides the base persistence definition of the
 * OaConsoleMenu entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaConsoleMenu implements java.io.Serializable {

	// Fields

	private OaConsoleMenuId id;

	// Constructors

	/** default constructor */
	public AbstractOaConsoleMenu() {
	}

	/** full constructor */
	public AbstractOaConsoleMenu(OaConsoleMenuId id) {
		this.id = id;
	}

	// Property accessors

	public OaConsoleMenuId getId() {
		return this.id;
	}

	public void setId(OaConsoleMenuId id) {
		this.id = id;
	}

}