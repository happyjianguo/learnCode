package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractTest entity provides the base persistence definition of the Test
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTest implements java.io.Serializable {

	// Fields

	private Long keyid;
	private String id;
	private String pid;
	private String name;
	private String url;

	// Constructors

	/** default constructor */
	public AbstractTest() {
	}

	/** minimal constructor */
	public AbstractTest(Long keyid) {
		this.keyid = keyid;
	}

	/** full constructor */
	public AbstractTest(Long keyid, String id, String pid, String name,
			String url) {
		this.keyid = keyid;
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.url = url;
	}

	// Property accessors

	public Long getKeyid() {
		return this.keyid;
	}

	public void setKeyid(Long keyid) {
		this.keyid = keyid;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}