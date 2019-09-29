package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractOaConsoleMenuOptRelation entity provides the base persistence
 * definition of the OaConsoleMenuOptRelation entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractOaConsoleMenuOptRelation implements
		java.io.Serializable {

	// Fields

	private OaConsoleMenuOptRelationId id;

	// Constructors

	/** default constructor */
	public AbstractOaConsoleMenuOptRelation() {
	}

	/** full constructor */
	public AbstractOaConsoleMenuOptRelation(OaConsoleMenuOptRelationId id) {
		this.id = id;
	}

	// Property accessors

	public OaConsoleMenuOptRelationId getId() {
		return this.id;
	}

	public void setId(OaConsoleMenuOptRelationId id) {
		this.id = id;
	}

}