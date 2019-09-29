package cn.yufu.posp.bookManager.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AnalyzeMerfeeBook entity. @author MyEclipse Persistence Tools
 */

public class AnalyzeMerfeeBook implements java.io.Serializable {

	// Fields

	private String id;
	private Date gentime;
	private String merno;
	private String bookdate;
	private String cyclestartdate;
	private String cycleenddate;
	private BigDecimal status;
	private BigDecimal trannum;
	private BigDecimal tranamt;
	private BigDecimal fee;
	private Date audtime;
	private String audoper;
	private String audremark;
	private String collectvoucher;
	private String collectdate;
	private String bankbranchname;
	private String bankaccno;
	private String bankaccname;
	private String comments;

	// Constructors

	/** default constructor */
	public AnalyzeMerfeeBook() {
	}

	/** minimal constructor */
	public AnalyzeMerfeeBook(String id, Date gentime, String merno, String bookdate, String cyclestartdate, String cycleenddate) {
		this.id = id;
		this.gentime = gentime;
		this.merno = merno;
		this.bookdate = bookdate;
		this.cyclestartdate = cyclestartdate;
		this.cycleenddate = cycleenddate;
	}

	/** full constructor */
	public AnalyzeMerfeeBook(String id, Date gentime, String merno, String bookdate, String cyclestartdate, String cycleenddate, BigDecimal status,
			BigDecimal trannum, BigDecimal tranamt, BigDecimal fee, Date audtime, String audoper, String audremark, String collectvoucher,
			String collectdate, String bankbranchname, String bankaccno, String bankaccname, String comments) {
		this.id = id;
		this.gentime = gentime;
		this.merno = merno;
		this.bookdate = bookdate;
		this.cyclestartdate = cyclestartdate;
		this.cycleenddate = cycleenddate;
		this.status = status;
		this.trannum = trannum;
		this.tranamt = tranamt;
		this.fee = fee;
		this.audtime = audtime;
		this.audoper = audoper;
		this.audremark = audremark;
		this.collectvoucher = collectvoucher;
		this.collectdate = collectdate;
		this.bankbranchname = bankbranchname;
		this.bankaccno = bankaccno;
		this.bankaccname = bankaccname;
		this.comments = comments;
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

	public String getBookdate() {
		return this.bookdate;
	}

	public void setBookdate(String bookdate) {
		this.bookdate = bookdate;
	}

	public String getCyclestartdate() {
		return this.cyclestartdate;
	}

	public void setCyclestartdate(String cyclestartdate) {
		this.cyclestartdate = cyclestartdate;
	}

	public String getCycleenddate() {
		return this.cycleenddate;
	}

	public void setCycleenddate(String cycleenddate) {
		this.cycleenddate = cycleenddate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTrannum() {
		return this.trannum;
	}

	public void setTrannum(BigDecimal trannum) {
		this.trannum = trannum;
	}

	public BigDecimal getTranamt() {
		return this.tranamt;
	}

	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Date getAudtime() {
		return this.audtime;
	}

	public void setAudtime(Date audtime) {
		this.audtime = audtime;
	}

	public String getAudoper() {
		return this.audoper;
	}

	public void setAudoper(String audoper) {
		this.audoper = audoper;
	}

	public String getAudremark() {
		return this.audremark;
	}

	public void setAudremark(String audremark) {
		this.audremark = audremark;
	}

	public String getCollectvoucher() {
		return this.collectvoucher;
	}

	public void setCollectvoucher(String collectvoucher) {
		this.collectvoucher = collectvoucher;
	}

	public String getCollectdate() {
		return this.collectdate;
	}

	public void setCollectdate(String collectdate) {
		this.collectdate = collectdate;
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

}