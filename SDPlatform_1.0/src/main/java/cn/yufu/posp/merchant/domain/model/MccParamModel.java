package cn.yufu.posp.merchant.domain.model;

/**
 * MccParamId entity. @author MyEclipse Persistence Tools
 */

public class MccParamModel implements java.io.Serializable {

	// Fields

	private String mcc;//商户类型编号
	private String mccName;//商户类型名称
	private String mccEname;//类型英文名称
	private String mccType;//所属类型
	
	//查询条件
	private String queryMcc;
	private String queryMccName;

	// Constructors

	public String getQueryMccName() {
		return queryMccName;
	}

	public void setQueryMccName(String queryMccName) {
		this.queryMccName = queryMccName;
	}

	public String getQueryMcc() {
		return queryMcc;
	}

	public void setQueryMcc(String queryMcc) {
		this.queryMcc = queryMcc;
	}

	/** default constructor */
	public MccParamModel() {
	}

	/** minimal constructor */
	public MccParamModel(String mcc, String mccName, String mccType) {
		this.mcc = mcc;
		this.mccName = mccName;
		this.mccType = mccType;
	}

	/** full constructor */
	public MccParamModel(String mcc, String mccName, String mccEname,
			String mccType) {
		this.mcc = mcc;
		this.mccName = mccName;
		this.mccEname = mccEname;
		this.mccType = mccType;
	}

	// Property accessors

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMccName() {
		return this.mccName;
	}

	public void setMccName(String mccName) {
		this.mccName = mccName;
	}

	public String getMccEname() {
		return this.mccEname;
	}

	public void setMccEname(String mccEname) {
		this.mccEname = mccEname;
	}

	public String getMccType() {
		return this.mccType;
	}

	public void setMccType(String mccType) {
		this.mccType = mccType;
	}

	

}