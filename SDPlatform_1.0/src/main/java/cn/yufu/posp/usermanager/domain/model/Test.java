package cn.yufu.posp.usermanager.domain.model;

/**
 * Test entity. @author MyEclipse Persistence Tools
 */
public class Test extends AbstractTest implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Test() {
	}

	/** minimal constructor */
	public Test(Long keyid) {
		super(keyid);
	}

	/** full constructor */
	public Test(Long keyid, String id, String pid, String name, String url) {
		super(keyid, id, pid, name, url);
	}

}
