package cn.yufu.posp.sysparam.domain.model;

/**
 * SysParam entity. @author MyEclipse Persistence Tools
 */

public class SysParam implements java.io.Serializable {

	// Fields

	private SysParamId id;

	// Constructors

	/** default constructor */
	public SysParam() {
	}

	/** full constructor */
	public SysParam(SysParamId id) {
		this.id = id;
	}

	// Property accessors

	public SysParamId getId() {
		return this.id;
	}

	public void setId(SysParamId id) {
		this.id = id;
	}

}