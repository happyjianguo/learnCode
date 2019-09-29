package cn.yufu.system.modules.sqlserver.entity;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.DataEntity;
import cn.yufu.system.common.utils.excel.annotation.ExcelField;

/**
 * 老福卡管理费退款功能
 * @author ZQK
 * @version 2018-06-25
 */
public class ReMemberCardSale extends DataEntity<ReMemberCardSale>{
	private static final long serialVersionUID = 1L;

	private BigDecimal primayKey;			//自增
	
	private String LRUID;				//商户号

    private String sSerialNum;			//终端号

    private String aCardnum;			//卡号
	
	private BigDecimal aSaleInegral;	//退款金额

    private String oldaRegDate;			//原交易日期

    private String oldaRegTime;			//原交易时间

    private BigDecimal lOrdNum;			//原批次号

    private BigDecimal lInvNum;			//原流水号
    
    private BigDecimal refOldid;			//原参考号
    
    private Integer bstate;				//状态  0 未退款；1已退款；2退款失败
    
    private String aRegDate;			//插入日期

    private String aRegTime;			//插入时间
    
    private String retime;			//退款时间
    
    private String reMsg;			//失败原因
    
    private String oracleID;			//ID
    
    private String begainTranDate;		//原交易开始时间
    
    private String endTranDate;			//原交易结束时间
    
    private String begainARegDate;		//创建开始时间
    
    private String endARegDate;			//创建结束时间

    public BigDecimal getPrimayKey() {
		return primayKey;
	}

	public void setPrimayKey(BigDecimal primayKey) {
		this.primayKey = primayKey;
	}

	@ExcelField(title="退款商户号", align=2, sort=10)	
	public String getLRUID() {
		return LRUID;
	}

	public void setLRUID(String lRUID) {
		LRUID = lRUID;
	}

	@ExcelField(title="退款终端号", align=2, sort=20)	
	public String getsSerialNum() {
		return sSerialNum;
	}

	public void setsSerialNum(String sSerialNum) {
		this.sSerialNum = sSerialNum;
	}

	@ExcelField(title="退款卡号", align=2, sort=30)	
	public String getaCardnum() {
		return aCardnum;
	}

	public void setaCardnum(String aCardnum) {
		this.aCardnum = aCardnum;
	}

	@ExcelField(title="退款金额", align=2, sort=40)	
	public BigDecimal getaSaleInegral() {
		return aSaleInegral;
	}

	public void setaSaleInegral(BigDecimal aSaleInegral) {
		this.aSaleInegral = aSaleInegral;
	}

	@ExcelField(title="原交易日期", align=2, sort=70)	
	public String getOldaRegDate() {
		return oldaRegDate;
	}

	public void setOldaRegDate(String oldaRegDate) {
		this.oldaRegDate = oldaRegDate;
	}

	@ExcelField(title="原交易时间", align=2, sort=80)	
	public String getOldaRegTime() {
		return oldaRegTime;
	}

	public void setOldaRegTime(String oldaRegTime) {
		this.oldaRegTime = oldaRegTime;
	}

	@ExcelField(title="原批次号", align=2, sort=90)	
	public BigDecimal getlOrdNum() {
		return lOrdNum;
	}

	public void setlOrdNum(BigDecimal lOrdNum) {
		this.lOrdNum = lOrdNum;
	}

	@ExcelField(title="原流水号", align=2, sort=100)	
	public BigDecimal getlInvNum() {
		return lInvNum;
	}

	public void setlInvNum(BigDecimal lInvNum) {
		this.lInvNum = lInvNum;
	}

	@ExcelField(title="原参考号", align=2, sort=110)	
	public BigDecimal getRefOldid() {
		return refOldid;
	}

	public void setRefOldid(BigDecimal refOldid) {
		this.refOldid = refOldid;
	}
	
	@ExcelField(title="退款时间", align=2, sort=120)	
	public String getRetime() {
		return retime;
	}

	public void setRetime(String retime) {
		this.retime = retime;
	}

	@ExcelField(title="退款状态", align=2, sort=140, dictType="REFUND_STATE")	
	public Integer getBstate() {
		return bstate;
	}

	public void setBstate(Integer bstate) {
		this.bstate = bstate;
	}
	
	@ExcelField(title="状态描述", align=2, sort=160)	
	public String getReMsg() {
		return reMsg;
	}

	public void setReMsg(String reMsg) {
		this.reMsg = reMsg;
	}

	@ExcelField(title="创建日期", align=2, sort=200)	
	public String getaRegDate() {
		return aRegDate;
	}

	public void setaRegDate(String aRegDate) {
		this.aRegDate = aRegDate;
	}

	@ExcelField(title="创建时间", align=2, sort=210)	
	public String getaRegTime() {
		return aRegTime;
	}

	public void setaRegTime(String aRegTime) {
		this.aRegTime = aRegTime;
	}

	public String getOracleID() {
		return oracleID;
	}

	public void setOracleID(String oracleID) {
		this.oracleID = oracleID;
	}

	public String getBegainTranDate() {
		return begainTranDate;
	}

	public void setBegainTranDate(String begainTranDate) {
		this.begainTranDate = begainTranDate;
	}

	public String getEndTranDate() {
		return endTranDate;
	}

	public void setEndTranDate(String endTranDate) {
		this.endTranDate = endTranDate;
	}

	public String getBegainARegDate() {
		return begainARegDate;
	}

	public void setBegainARegDate(String begainARegDate) {
		this.begainARegDate = begainARegDate;
	}

	public String getEndARegDate() {
		return endARegDate;
	}

	public void setEndARegDate(String endARegDate) {
		this.endARegDate = endARegDate;
	}

}