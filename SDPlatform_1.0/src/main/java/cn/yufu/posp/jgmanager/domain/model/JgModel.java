package cn.yufu.posp.jgmanager.domain.model;

/**
 * Jg entity. @author MyEclipse Persistence Tools
 */

public class JgModel implements java.io.Serializable {

	// Fields

	private String jgId;
	private String jgName;
	private String jgSm;
	private String jgDz;
	private String jgTel;
	private String jgCz;
	private String jgLxr;
	private String jgYb;

	// Constructors

	/** default constructor */
	public JgModel() {
	}

	/** minimal constructor */
	public JgModel(String jgId) {
		this.jgId = jgId;
	}

	/** full constructor */
	public JgModel(String jgId, String jgName, String jgSm, String jgDz, String jgTel,
			String jgCz, String jgLxr, String jgYb) {
		this.jgId = jgId;
		this.jgName = jgName;
		this.jgSm = jgSm;
		this.jgDz = jgDz;
		this.jgTel = jgTel;
		this.jgCz = jgCz;
		this.jgLxr = jgLxr;
		this.jgYb = jgYb;
	}

	// Property accessors

	public String getJgId() {
		return this.jgId;
	}

	public void setJgId(String jgId) {
		this.jgId = jgId;
	}

	public String getJgName() {
		return this.jgName;
	}

	public void setJgName(String jgName) {
		this.jgName = jgName;
	}

	public String getJgSm() {
		return this.jgSm;
	}

	public void setJgSm(String jgSm) {
		this.jgSm = jgSm;
	}

	public String getJgDz() {
		return this.jgDz;
	}

	public void setJgDz(String jgDz) {
		this.jgDz = jgDz;
	}

	public String getJgTel() {
		return this.jgTel;
	}

	public void setJgTel(String jgTel) {
		this.jgTel = jgTel;
	}

	public String getJgCz() {
		return this.jgCz;
	}

	public void setJgCz(String jgCz) {
		this.jgCz = jgCz;
	}

	public String getJgLxr() {
		return this.jgLxr;
	}

	public void setJgLxr(String jgLxr) {
		this.jgLxr = jgLxr;
	}

	public String getJgYb() {
		return this.jgYb;
	}

	public void setJgYb(String jgYb) {
		this.jgYb = jgYb;
	}

}