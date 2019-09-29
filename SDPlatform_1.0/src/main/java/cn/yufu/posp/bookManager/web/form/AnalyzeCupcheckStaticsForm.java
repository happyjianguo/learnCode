package cn.yufu.posp.bookManager.web.form;

import java.math.BigDecimal;

import cn.yufu.core.web.form.BaseForm;

/**
 * AnalyzeCupcheckStatics entity. @author MyEclipse Persistence Tools
 */

public class AnalyzeCupcheckStaticsForm extends BaseForm {

	// Fields

	private String bookid;
	private String filedate;
	private String checkDate;
	private BigDecimal sysCurCheckTotalNum;
	private BigDecimal sysCurCheckTotalAmt;
	private BigDecimal cupCurCheckTotalNum;
	private BigDecimal cupCurCheckTotalAmt;
	private BigDecimal curCheckOkNum;
	private BigDecimal curCheckOkAmt;
	private BigDecimal curCheckErrNum;
	private BigDecimal curCheckErrAmt;
	private BigDecimal curCheckMoerNum;
	private BigDecimal curCheckMoreAmt;
	private BigDecimal curCheckLessNum;
	private BigDecimal curCheckLessAmt;
	private BigDecimal sysCurCheckNotyetChkNum;
	private BigDecimal sysCurCheckNotyetChkAmt;
	private BigDecimal curCheckAddchkNum;
	private BigDecimal curCheckAddchkAmt;

	// Constructors

	/** default constructor */
	public AnalyzeCupcheckStaticsForm() {
	}

	/** minimal constructor */
	public AnalyzeCupcheckStaticsForm(String bookid, String filedate) {
		this.bookid = bookid;
		this.filedate = filedate;
	}

	/** full constructor */
	public AnalyzeCupcheckStaticsForm(String bookid, String filedate, String checkDate, BigDecimal sysCurCheckTotalNum, BigDecimal sysCurCheckTotalAmt,
			BigDecimal cupCurCheckTotalNum, BigDecimal cupCurCheckTotalAmt, BigDecimal curCheckOkNum, BigDecimal curCheckOkAmt, BigDecimal curCheckErrNum,
			BigDecimal curCheckErrAmt, BigDecimal curCheckMoerNum, BigDecimal curCheckMoreAmt, BigDecimal curCheckLessNum, BigDecimal curCheckLessAmt,
			BigDecimal sysCurCheckNotyetChkNum, BigDecimal sysCurCheckNotyetChkAmt, BigDecimal curCheckAddchkNum, BigDecimal curCheckAddchkAmt) {
		this.bookid = bookid;
		this.filedate = filedate;
		this.checkDate = checkDate;
		this.sysCurCheckTotalNum = sysCurCheckTotalNum;
		this.sysCurCheckTotalAmt = sysCurCheckTotalAmt;
		this.cupCurCheckTotalNum = cupCurCheckTotalNum;
		this.cupCurCheckTotalAmt = cupCurCheckTotalAmt;
		this.curCheckOkNum = curCheckOkNum;
		this.curCheckOkAmt = curCheckOkAmt;
		this.curCheckErrNum = curCheckErrNum;
		this.curCheckErrAmt = curCheckErrAmt;
		this.curCheckMoerNum = curCheckMoerNum;
		this.curCheckMoreAmt = curCheckMoreAmt;
		this.curCheckLessNum = curCheckLessNum;
		this.curCheckLessAmt = curCheckLessAmt;
		this.sysCurCheckNotyetChkNum = sysCurCheckNotyetChkNum;
		this.sysCurCheckNotyetChkAmt = sysCurCheckNotyetChkAmt;
		this.curCheckAddchkNum = curCheckAddchkNum;
		this.curCheckAddchkAmt = curCheckAddchkAmt;
	}

	// Property accessors

	public String getBookid() {
		return this.bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getFiledate() {
		return this.filedate;
	}

	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}

	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public BigDecimal getSysCurCheckTotalNum() {
		return this.sysCurCheckTotalNum;
	}

	public void setSysCurCheckTotalNum(BigDecimal sysCurCheckTotalNum) {
		this.sysCurCheckTotalNum = sysCurCheckTotalNum;
	}

	public BigDecimal getSysCurCheckTotalAmt() {
		return this.sysCurCheckTotalAmt;
	}

	public void setSysCurCheckTotalAmt(BigDecimal sysCurCheckTotalAmt) {
		this.sysCurCheckTotalAmt = sysCurCheckTotalAmt;
	}

	public BigDecimal getCupCurCheckTotalNum() {
		return this.cupCurCheckTotalNum;
	}

	public void setCupCurCheckTotalNum(BigDecimal cupCurCheckTotalNum) {
		this.cupCurCheckTotalNum = cupCurCheckTotalNum;
	}

	public BigDecimal getCupCurCheckTotalAmt() {
		return this.cupCurCheckTotalAmt;
	}

	public void setCupCurCheckTotalAmt(BigDecimal cupCurCheckTotalAmt) {
		this.cupCurCheckTotalAmt = cupCurCheckTotalAmt;
	}

	public BigDecimal getCurCheckOkNum() {
		return this.curCheckOkNum;
	}

	public void setCurCheckOkNum(BigDecimal curCheckOkNum) {
		this.curCheckOkNum = curCheckOkNum;
	}

	public BigDecimal getCurCheckOkAmt() {
		return this.curCheckOkAmt;
	}

	public void setCurCheckOkAmt(BigDecimal curCheckOkAmt) {
		this.curCheckOkAmt = curCheckOkAmt;
	}

	public BigDecimal getCurCheckErrNum() {
		return this.curCheckErrNum;
	}

	public void setCurCheckErrNum(BigDecimal curCheckErrNum) {
		this.curCheckErrNum = curCheckErrNum;
	}

	public BigDecimal getCurCheckErrAmt() {
		return this.curCheckErrAmt;
	}

	public void setCurCheckErrAmt(BigDecimal curCheckErrAmt) {
		this.curCheckErrAmt = curCheckErrAmt;
	}

	public BigDecimal getCurCheckMoerNum() {
		return this.curCheckMoerNum;
	}

	public void setCurCheckMoerNum(BigDecimal curCheckMoerNum) {
		this.curCheckMoerNum = curCheckMoerNum;
	}

	public BigDecimal getCurCheckMoreAmt() {
		return this.curCheckMoreAmt;
	}

	public void setCurCheckMoreAmt(BigDecimal curCheckMoreAmt) {
		this.curCheckMoreAmt = curCheckMoreAmt;
	}

	public BigDecimal getCurCheckLessNum() {
		return this.curCheckLessNum;
	}

	public void setCurCheckLessNum(BigDecimal curCheckLessNum) {
		this.curCheckLessNum = curCheckLessNum;
	}

	public BigDecimal getCurCheckLessAmt() {
		return this.curCheckLessAmt;
	}

	public void setCurCheckLessAmt(BigDecimal curCheckLessAmt) {
		this.curCheckLessAmt = curCheckLessAmt;
	}

	public BigDecimal getSysCurCheckNotyetChkNum() {
		return this.sysCurCheckNotyetChkNum;
	}

	public void setSysCurCheckNotyetChkNum(BigDecimal sysCurCheckNotyetChkNum) {
		this.sysCurCheckNotyetChkNum = sysCurCheckNotyetChkNum;
	}

	public BigDecimal getSysCurCheckNotyetChkAmt() {
		return this.sysCurCheckNotyetChkAmt;
	}

	public void setSysCurCheckNotyetChkAmt(BigDecimal sysCurCheckNotyetChkAmt) {
		this.sysCurCheckNotyetChkAmt = sysCurCheckNotyetChkAmt;
	}

	public BigDecimal getCurCheckAddchkNum() {
		return this.curCheckAddchkNum;
	}

	public void setCurCheckAddchkNum(BigDecimal curCheckAddchkNum) {
		this.curCheckAddchkNum = curCheckAddchkNum;
	}

	public BigDecimal getCurCheckAddchkAmt() {
		return this.curCheckAddchkAmt;
	}

	public void setCurCheckAddchkAmt(BigDecimal curCheckAddchkAmt) {
		this.curCheckAddchkAmt = curCheckAddchkAmt;
	}

}