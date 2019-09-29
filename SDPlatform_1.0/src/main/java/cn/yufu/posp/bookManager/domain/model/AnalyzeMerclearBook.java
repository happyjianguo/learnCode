package cn.yufu.posp.bookManager.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AnalyzeMerclearBook entity. @author MyEclipse Persistence Tools
 */

public class AnalyzeMerclearBook implements java.io.Serializable {

	// Fields

	private String id;
	private Date gentime;
	private String trandate;
	private String merno;
	private BigDecimal trannum;
	private BigDecimal tranamt;
	private BigDecimal refundnum;
	private BigDecimal refundamt;
	private BigDecimal tranfee;
	private BigDecimal clearamt;
	private String stlbookid;
	private String stldate;
	private BigDecimal stlflag;
	private BigDecimal stlamt;
	private String feebookid;
	private String feestldate;
	private BigDecimal feestlflag;
	private BigDecimal feestlamt;
	private String comments;

	// Constructors

	/** default constructor */
	public AnalyzeMerclearBook() {
	}

	/** minimal constructor */
	public AnalyzeMerclearBook(String id, Date gentime, String trandate, String merno) {
		this.id = id;
		this.gentime = gentime;
		this.trandate = trandate;
		this.merno = merno;
	}

	/** full constructor */
	public AnalyzeMerclearBook(String id, Date gentime, String trandate, String merno, BigDecimal trannum, BigDecimal tranamt, BigDecimal refundnum,
			BigDecimal refundamt, BigDecimal tranfee, BigDecimal clearamt, String stlbookid, String stldate, BigDecimal stlflag, BigDecimal stlamt,
			String feebookid, String feestldate, BigDecimal feestlflag, BigDecimal feestlamt, String comments) {
		this.id = id;
		this.gentime = gentime;
		this.trandate = trandate;
		this.merno = merno;
		this.trannum = trannum;
		this.tranamt = tranamt;
		this.refundnum = refundnum;
		this.refundamt = refundamt;
		this.tranfee = tranfee;
		this.clearamt = clearamt;
		this.stlbookid = stlbookid;
		this.stldate = stldate;
		this.stlflag = stlflag;
		this.stlamt = stlamt;
		this.feebookid = feebookid;
		this.feestldate = feestldate;
		this.feestlflag = feestlflag;
		this.feestlamt = feestlamt;
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

	public String getTrandate() {
		return this.trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}

	public String getMerno() {
		return this.merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
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

	public BigDecimal getRefundnum() {
		return this.refundnum;
	}

	public void setRefundnum(BigDecimal refundnum) {
		this.refundnum = refundnum;
	}

	public BigDecimal getRefundamt() {
		return this.refundamt;
	}

	public void setRefundamt(BigDecimal refundamt) {
		this.refundamt = refundamt;
	}

	public BigDecimal getTranfee() {
		return this.tranfee;
	}

	public void setTranfee(BigDecimal tranfee) {
		this.tranfee = tranfee;
	}

	public BigDecimal getClearamt() {
		return this.clearamt;
	}

	public void setClearamt(BigDecimal clearamt) {
		this.clearamt = clearamt;
	}

	public String getStlbookid() {
		return this.stlbookid;
	}

	public void setStlbookid(String stlbookid) {
		this.stlbookid = stlbookid;
	}

	public String getStldate() {
		return this.stldate;
	}

	public void setStldate(String stldate) {
		this.stldate = stldate;
	}

	public BigDecimal getStlflag() {
		return this.stlflag;
	}

	public void setStlflag(BigDecimal stlflag) {
		this.stlflag = stlflag;
	}

	public BigDecimal getStlamt() {
		return this.stlamt;
	}

	public void setStlamt(BigDecimal stlamt) {
		this.stlamt = stlamt;
	}

	public String getFeebookid() {
		return this.feebookid;
	}

	public void setFeebookid(String feebookid) {
		this.feebookid = feebookid;
	}

	public String getFeestldate() {
		return this.feestldate;
	}

	public void setFeestldate(String feestldate) {
		this.feestldate = feestldate;
	}

	public BigDecimal getFeestlflag() {
		return this.feestlflag;
	}

	public void setFeestlflag(BigDecimal feestlflag) {
		this.feestlflag = feestlflag;
	}

	public BigDecimal getFeestlamt() {
		return this.feestlamt;
	}

	public void setFeestlamt(BigDecimal feestlamt) {
		this.feestlamt = feestlamt;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}