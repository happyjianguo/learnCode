package cn.yufu.system.modules.cortexs.entity;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.DataEntity;
import cn.yufu.system.common.utils.excel.annotation.ExcelField;

public class CrdfeeSaleCkjf extends DataEntity<CrdfeeSaleCkjf>{
	private static final long serialVersionUID = 6139769765139436200L;

	private BigDecimal tlogId;		//tlogId

    private String pan;				//卡号

    private String iid;				//卡bin

    private BigDecimal amttxn04;	//04账户消费金额	

    private BigDecimal amttxn09;	//09账户消费金额

    private String datelocal;		//消费交易日期

    private String topCkjfId;		//财卡充值明细id

    private String flag;			//积分消费标识

    private String updattime;		//最近一次更新时间

    private String reserved1;

    private String reserved3;

    private String reserved2;
    
    private String begainTranDate;	//开始交易日期
    
    private String endTranDate;		//结束交易日期

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

    @ExcelField(title="购物返积分-消费金额(元)", align=2, sort=50)
    public BigDecimal getAmttxn04() {
        return amttxn04;
    }

    public void setAmttxn04(BigDecimal amttxn04) {
        this.amttxn04 = amttxn04;
    }

    @ExcelField(title="联名卡送积分-消费金额(元)", align=2, sort=60)
    public BigDecimal getAmttxn09() {
        return amttxn09;
    }

    public void setAmttxn09(BigDecimal amttxn09) {
        this.amttxn09 = amttxn09;
    }

    @ExcelField(title="消费交易日期", align=2, sort=100)
    public String getDatelocal() {
        return datelocal;
    }

    public void setDatelocal(String datelocal) {
        this.datelocal = datelocal == null ? null : datelocal.trim();
    }

    @ExcelField(title="财卡充值明细ID", align=2, sort=120)
    public String getTopCkjfId() {
        return topCkjfId;
    }

    public void setTopCkjfId(String topCkjfId) {
        this.topCkjfId = topCkjfId == null ? null : topCkjfId.trim();
    }

    @ExcelField(title="积分消费标识", align=2, sort=130)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    @ExcelField(title="最近更新时间", align=2, sort=200)
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