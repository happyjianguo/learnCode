package cn.yufu.posp.bookManager.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AnalyzeCupcheckBook entity. @author MyEclipse Persistence Tools
 */

public class AnalyzeCupcheckBook implements java.io.Serializable {

	// Fields

	private String bookid;
	private Date gentasktime;
	private String filedate;
	private String comafilename;
	private String errafilename;
	private String lfefilename;
	private Date genfiletime;
	private BigDecimal status;
	private String checkdate;
	private Date chkstarttime;
	private Date chkendtime;
	private Double comatotleamt;
	private BigDecimal comatotalcnt;
	private Double systotalamt;
	private BigDecimal systotalcnt;
	private Double erratotleamt;
	private BigDecimal erratotalcnt;
	private Double lfetotleamt;
	private BigDecimal lfetotalcnt;
	private String comments;

	// Constructors

	/** default constructor */
	public AnalyzeCupcheckBook() {
	}

	/** minimal constructor */
	public AnalyzeCupcheckBook(String bookid, Date gentasktime, String filedate, String comafilename, String errafilename, String lfefilename,
			BigDecimal status) {
		this.bookid = bookid;
		this.gentasktime = gentasktime;
		this.filedate = filedate;
		this.comafilename = comafilename;
		this.errafilename = errafilename;
		this.lfefilename = lfefilename;
		this.status = status;
	}

	/** full constructor */
	public AnalyzeCupcheckBook(String bookid, Date gentasktime, String filedate, String comafilename, String errafilename, String lfefilename,
			Date genfiletime, BigDecimal status, String checkdate, Date chkstarttime, Date chkendtime, Double comatotleamt,
			BigDecimal comatotalcnt, Double systotalamt, BigDecimal systotalcnt, Double erratotleamt, BigDecimal erratotalcnt, Double lfetotleamt,
			BigDecimal lfetotalcnt, String comments) {
		this.bookid = bookid;
		this.gentasktime = gentasktime;
		this.filedate = filedate;
		this.comafilename = comafilename;
		this.errafilename = errafilename;
		this.lfefilename = lfefilename;
		this.genfiletime = genfiletime;
		this.status = status;
		this.checkdate = checkdate;
		this.chkstarttime = chkstarttime;
		this.chkendtime = chkendtime;
		this.comatotleamt = comatotleamt;
		this.comatotalcnt = comatotalcnt;
		this.systotalamt = systotalamt;
		this.systotalcnt = systotalcnt;
		this.erratotleamt = erratotleamt;
		this.erratotalcnt = erratotalcnt;
		this.lfetotleamt = lfetotleamt;
		this.lfetotalcnt = lfetotalcnt;
		this.comments = comments;
	}

	// Property accessors

	public String getBookid() {
		return this.bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public Date getGentasktime() {
		return this.gentasktime;
	}

	public void setGentasktime(Date gentasktime) {
		this.gentasktime = gentasktime;
	}

	public String getFiledate() {
		return this.filedate;
	}

	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}

	public String getComafilename() {
		return this.comafilename;
	}

	public void setComafilename(String comafilename) {
		this.comafilename = comafilename;
	}

	public String getErrafilename() {
		return this.errafilename;
	}

	public void setErrafilename(String errafilename) {
		this.errafilename = errafilename;
	}

	public String getLfefilename() {
		return this.lfefilename;
	}

	public void setLfefilename(String lfefilename) {
		this.lfefilename = lfefilename;
	}

	public Date getGenfiletime() {
		return this.genfiletime;
	}

	public void setGenfiletime(Date genfiletime) {
		this.genfiletime = genfiletime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getCheckdate() {
		return this.checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public Date getChkstarttime() {
		return this.chkstarttime;
	}

	public void setChkstarttime(Date chkstarttime) {
		this.chkstarttime = chkstarttime;
	}

	public Date getChkendtime() {
		return this.chkendtime;
	}

	public void setChkendtime(Date chkendtime) {
		this.chkendtime = chkendtime;
	}

	public Double getComatotleamt() {
		return this.comatotleamt;
	}

	public void setComatotleamt(Double comatotleamt) {
		this.comatotleamt = comatotleamt;
	}

	public BigDecimal getComatotalcnt() {
		return this.comatotalcnt;
	}

	public void setComatotalcnt(BigDecimal comatotalcnt) {
		this.comatotalcnt = comatotalcnt;
	}

	public Double getSystotalamt() {
		return this.systotalamt;
	}

	public void setSystotalamt(Double systotalamt) {
		this.systotalamt = systotalamt;
	}

	public BigDecimal getSystotalcnt() {
		return this.systotalcnt;
	}

	public void setSystotalcnt(BigDecimal systotalcnt) {
		this.systotalcnt = systotalcnt;
	}

	public Double getErratotleamt() {
		return this.erratotleamt;
	}

	public void setErratotleamt(Double erratotleamt) {
		this.erratotleamt = erratotleamt;
	}

	public BigDecimal getErratotalcnt() {
		return this.erratotalcnt;
	}

	public void setErratotalcnt(BigDecimal erratotalcnt) {
		this.erratotalcnt = erratotalcnt;
	}

	public Double getLfetotleamt() {
		return this.lfetotleamt;
	}

	public void setLfetotleamt(Double lfetotleamt) {
		this.lfetotleamt = lfetotleamt;
	}

	public BigDecimal getLfetotalcnt() {
		return this.lfetotalcnt;
	}

	public void setLfetotalcnt(BigDecimal lfetotalcnt) {
		this.lfetotalcnt = lfetotalcnt;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}