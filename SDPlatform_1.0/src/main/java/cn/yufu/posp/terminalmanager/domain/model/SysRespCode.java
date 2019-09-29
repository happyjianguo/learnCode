package cn.yufu.posp.terminalmanager.domain.model;

/**
 * SysRespCode entity. @author MyEclipse Persistence Tools
 */

public class SysRespCode implements java.io.Serializable {

	// Fields

	private String respCode;
	private String respCat;
	private String rcText;
	private String rcDetail;
	private String action;

	// Constructors

	/** default constructor */
	public SysRespCode() {
	}

	/** minimal constructor */
	public SysRespCode(String respCode) {
		this.respCode = respCode;
	}

	/** full constructor */
	public SysRespCode(String respCode, String respCat, String rcText, String rcDetail, String action) {
		this.respCode = respCode;
		this.respCat = respCat;
		this.rcText = rcText;
		this.rcDetail = rcDetail;
		this.action = action;
	}

	// Property accessors

	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespCat() {
		return this.respCat;
	}

	public void setRespCat(String respCat) {
		this.respCat = respCat;
	}

	public String getRcText() {
		return this.rcText;
	}

	public void setRcText(String rcText) {
		this.rcText = rcText;
	}

	public String getRcDetail() {
		return this.rcDetail;
	}

	public void setRcDetail(String rcDetail) {
		this.rcDetail = rcDetail;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}