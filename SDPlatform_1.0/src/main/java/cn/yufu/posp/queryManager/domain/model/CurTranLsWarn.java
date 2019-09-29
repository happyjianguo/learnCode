package cn.yufu.posp.queryManager.domain.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * CurTranLsWarn entity. @author MyEclipse Persistence Tools
 */

public class CurTranLsWarn implements java.io.Serializable {

	// Fields

	private String id;
	private String batno;
	private String cardtype;
	private String cardno;
	private String merno;
	private String mername;
	private String mcctype;
	private String termno;
	private BigDecimal tranceno;
	private String respcode;
	private String rulecode;
	private String ruledesc;
	private String actionstatus;
	private Date cteatetime;
	private BigDecimal tranamt;
	private Date localtime;

	// Constructors

	/** default constructor */
	public CurTranLsWarn() {
	}

	/** minimal constructor */
	public CurTranLsWarn(String id) {
		this.id = id;
	}

	/** full constructor */
	public CurTranLsWarn(String id, String batno, String cardtype,
			String cardno, String merno, String mername, String mcctype,
			String termno, BigDecimal tranceno, String respcode,
			String rulecode, String ruledesc, String actionstatus,
			Date cteatetime, BigDecimal tranamt, Date localtime) {
		this.id = id;
		this.batno = batno;
		this.cardtype = cardtype;
		this.cardno = cardno;
		this.merno = merno;
		this.mername = mername;
		this.mcctype = mcctype;
		this.termno = termno;
		this.tranceno = tranceno;
		this.respcode = respcode;
		this.rulecode = rulecode;
		this.ruledesc = ruledesc;
		this.actionstatus = actionstatus;
		this.cteatetime = cteatetime;
		this.tranamt = tranamt;
		this.localtime = localtime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatno() {
		return this.batno;
	}

	public void setBatno(String batno) {
		this.batno = batno;
	}

	public String getCardtype() {
		return this.cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getMerno() {
		return this.merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public String getMername() {
		return this.mername;
	}

	public void setMername(String mername) {
		this.mername = mername;
	}

	public String getMcctype() {
		return this.mcctype;
	}

	public void setMcctype(String mcctype) {
		this.mcctype = mcctype;
	}

	public String getTermno() {
		return this.termno;
	}

	public void setTermno(String termno) {
		this.termno = termno;
	}

	public BigDecimal getTranceno() {
		return this.tranceno;
	}

	public void setTranceno(BigDecimal tranceno) {
		this.tranceno = tranceno;
	}

	public String getRespcode() {
		return this.respcode;
	}

	public void setRespcode(String respcode) {
		this.respcode = respcode;
	}

	public String getRulecode() {
		return this.rulecode;
	}

	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}

	public String getRuledesc() {
		return this.ruledesc;
	}

	public void setRuledesc(String ruledesc) {
		this.ruledesc = ruledesc;
	}

	public String getActionstatus() {
		return this.actionstatus;
	}

	public void setActionstatus(String actionstatus) {
		this.actionstatus = actionstatus;
	}

	public Date getCteatetime() {
		return this.cteatetime;
	}

	public void setCteatetime(Date cteatetime) {
		this.cteatetime = cteatetime;
	}

	public BigDecimal getTranamt() {
		return this.tranamt;
	}

	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}

	public Date getLocaltime() {
		return this.localtime;
	}

	public void setLocaltime(Date localtime) {
		this.localtime = localtime;
	}

}