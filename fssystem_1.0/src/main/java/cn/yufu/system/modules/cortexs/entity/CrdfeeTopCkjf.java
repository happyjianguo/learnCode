package cn.yufu.system.modules.cortexs.entity;

import java.math.BigDecimal;

import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.persistence.DataEntity;
import cn.yufu.system.common.utils.excel.annotation.ExcelField;

public class CrdfeeTopCkjf extends DataEntity<CrdfeeTopCkjf>{
	private static final long serialVersionUID = 4176853575866540264L;

	private String primayKey;
	
	private BigDecimal tlogId;		//tlogId

    private String pan;				//卡号

    private String iid;				//卡bin

    private String acctype;			//账户类型 04购卡返积分   09联名卡送积分

    private BigDecimal amttxn;		//充值金额

    private String datelocal;		//充值交易日期

    private String usemonth;		//有效期月份

    private String expdate;			//过期日期

    private BigDecimal alreadyamt;	//已消费总金额

    private BigDecimal expireamt;	//过期积分

    private BigDecimal lastamt;		//剩余总金额

    private String flag;			//0存在剩余积分  不存在剩余积分

    private String updattime;		//最近一次更新时间

    private String reserved1;

    private String reserved3;

    private String reserved2;
    
    private String begainTranDate;	//开始交易日期
    
    private String endTranDate;		//结束交易日期

    @ExcelField(title="积分充值表ID", align=2, sort=10)	
    public String getPrimayKey() {
		return primayKey;
	}

	public void setPrimayKey(String primayKey) {
		this.primayKey = primayKey;
	}

	@ExcelField(title="TLOG_ID", align=2, sort=20)	
    public BigDecimal getTlogId() {
        return tlogId;
    }

    public void setTlogId(BigDecimal tlogId) {
        this.tlogId = tlogId;
    }

    @ExcelField(title="卡号", align=2, sort=30)	
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    @ExcelField(title="卡BIN", align=2, sort=40)	
    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }

    @ExcelField(title="账户类型", align=2, sort=50, dictType="CRDFEE_TYPE")	
    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype == null ? null : acctype.trim();
    }

    @ExcelField(title="充值金额", align=2, sort=60)	
    public BigDecimal getAmttxn() {
        return amttxn;
    }

    public void setAmttxn(BigDecimal amttxn) {
        this.amttxn = amttxn;
    }

    @ExcelField(title="充值交易日期", align=2, sort=70)	
    public String getDatelocal() {
        return datelocal;
    }

    public void setDatelocal(String datelocal) {
        this.datelocal = datelocal == null ? null : datelocal.trim();
    }

    @ExcelField(title="有效期(月数)", align=2, sort=80)	
    public String getUsemonth() {
        return usemonth;
    }

    public void setUsemonth(String usemonth) {
        this.usemonth = usemonth == null ? null : usemonth.trim();
    }
    
    @ExcelField(title="已消费总金额", align=2, sort=100)	
    public BigDecimal getAlreadyamt() {
        return alreadyamt;
    }

    public void setAlreadyamt(BigDecimal alreadyamt) {
        this.alreadyamt = alreadyamt;
    }
    
    @ExcelField(title="剩余总金额", align=2, sort=110)	
    public BigDecimal getLastamt() {
        return lastamt;
    }

    public void setLastamt(BigDecimal lastamt) {
        this.lastamt = lastamt;
    }
    
    @ExcelField(title="过期日期", align=2, sort=120)	
    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate == null ? null : expdate.trim();
    }

    @ExcelField(title="过期积分", align=2, sort=150)	
    public BigDecimal getExpireamt() {
        return expireamt;
    }

    public void setExpireamt(BigDecimal expireamt) {
        this.expireamt = expireamt;
    }

    @ExcelField(title="是否存在剩余积分", align=2, sort=160, getDescById=Global.IS_EXISTS)	
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    @ExcelField(title="最后更新时间", align=2, sort=333)	
    public String getUpdattime() {
        return updattime;
    }

    public void setUpdattime(String updattime) {
        this.updattime = updattime == null ? null : updattime.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
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
    
}