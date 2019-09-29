package cn.yufu.posp.bookManager.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AnalyzeMerstlBook entity. @author MyEclipse Persistence Tools
 */

public class AnalyzeMerstlBook implements java.io.Serializable {

	// Fields

	private String id;
	private Date gentime;
	private String merno;
	private String stldate;
	private String stlstartdate;
	private String stlenddate;
	private BigDecimal status;
	private BigDecimal stlamt;
	private BigDecimal payoutfee;
	private BigDecimal payoutamt;
	private String payoutjnlno;
	private String bankbranchname;
	private String bankaccno;
	private String bankaccname;
	private String comments;
	private String bankaccphone;
	private BigDecimal stlnum;

	// Constructors

	/** default constructor */
	public AnalyzeMerstlBook() {
	}

	/** minimal constructor */
	public AnalyzeMerstlBook(String id, Date gentime, String merno, String stldate) {
		this.id = id;
		this.gentime = gentime;
		this.merno = merno;
		this.stldate = stldate;
	}

	/** full constructor */
	public AnalyzeMerstlBook(String id, Date gentime, String merno, String stldate, String stlstartdate, String stlenddate, BigDecimal status,
			BigDecimal stlamt, BigDecimal payoutfee, BigDecimal payoutamt, String payoutjnlno, String bankbranchname, String bankaccno, String bankaccname,
			String comments, String bankaccphone, BigDecimal stlnum) {
		this.id = id;
		this.gentime = gentime;
		this.merno = merno;
		this.stldate = stldate;
		this.stlstartdate = stlstartdate;
		this.stlenddate = stlenddate;
		this.status = status;
		this.stlamt = stlamt;
		this.payoutfee = payoutfee;
		this.payoutamt = payoutamt;
		this.payoutjnlno = payoutjnlno;
		this.bankbranchname = bankbranchname;
		this.bankaccno = bankaccno;
		this.bankaccname = bankaccname;
		this.comments = comments;
		this.bankaccphone = bankaccphone;
		this.stlnum = stlnum;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getGentime() {
		return this.gentime;
	}

	public void setGentime(Date gentime) {
		this.gentime = gentime;
	}

	public String getMerno() {
		return this.merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public String getStldate() {
		return this.stldate;
	}

	public void setStldate(String stldate) {
		this.stldate = stldate;
	}

	public String getStlstartdate() {
		return this.stlstartdate;
	}

	public void setStlstartdate(String stlstartdate) {
		this.stlstartdate = stlstartdate;
	}

	public String getStlenddate() {
		return this.stlenddate;
	}

	public void setStlenddate(String stlenddate) {
		this.stlenddate = stlenddate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStlamt() {
		return this.stlamt;
	}

	public void setStlamt(BigDecimal stlamt) {
		this.stlamt = stlamt;
	}

	public BigDecimal getPayoutfee() {
		return this.payoutfee;
	}

	public void setPayoutfee(BigDecimal payoutfee) {
		this.payoutfee = payoutfee;
	}

	public BigDecimal getPayoutamt() {
		return this.payoutamt;
	}

	public void setPayoutamt(BigDecimal payoutamt) {
		this.payoutamt = payoutamt;
	}

	public String getPayoutjnlno() {
		return this.payoutjnlno;
	}

	public void setPayoutjnlno(String payoutjnlno) {
		this.payoutjnlno = payoutjnlno;
	}

	public String getBankbranchname() {
		return this.bankbranchname;
	}

	public void setBankbranchname(String bankbranchname) {
		this.bankbranchname = bankbranchname;
	}

	public String getBankaccno() {
		return this.bankaccno;
	}

	public void setBankaccno(String bankaccno) {
		this.bankaccno = bankaccno;
	}

	public String getBankaccname() {
		return this.bankaccname;
	}

	public void setBankaccname(String bankaccname) {
		this.bankaccname = bankaccname;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getBankaccphone() {
		return this.bankaccphone;
	}

	public void setBankaccphone(String bankaccphone) {
		this.bankaccphone = bankaccphone;
	}

	public BigDecimal getStlnum() {
		return this.stlnum;
	}

	public void setStlnum(BigDecimal stlnum) {
		this.stlnum = stlnum;
	}

}