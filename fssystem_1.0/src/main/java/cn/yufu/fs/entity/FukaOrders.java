package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FukaOrders {
    private String ordercode;//订单号

    private String adminName;//售卡员用户名

    private String adminRealname;//售卡员姓名

    private BigDecimal pointid;//售卡网点编号

    private String pointName;//售卡网点名称

    private BigDecimal companyId;//分公司id

    private String companyName;//分公司名称

    private BigDecimal cardtotalprice;//售卡总金额

    private Date outcardverifytime;//售卡时间

    private String provisions;//备付金

    private BigDecimal provisionsRate;//备付金和购卡比例

    private String freeField3;

    private String freeField4;

    private String freeField5;

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminRealname() {
        return adminRealname;
    }

    public void setAdminRealname(String adminRealname) {
        this.adminRealname = adminRealname == null ? null : adminRealname.trim();
    }

    public BigDecimal getPointid() {
        return pointid;
    }

    public void setPointid(BigDecimal pointid) {
        this.pointid = pointid;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public BigDecimal getCompanyId() {
        return companyId;
    }

    public void setCompanyId(BigDecimal companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public BigDecimal getCardtotalprice() {
        return cardtotalprice;
    }

    public void setCardtotalprice(BigDecimal cardtotalprice) {
        this.cardtotalprice = cardtotalprice;
    }

    public Date getOutcardverifytime() {
		return outcardverifytime;
	}

	public void setOutcardverifytime(Date outcardverifytime) {
		this.outcardverifytime = outcardverifytime;
	}

	public String getProvisions() {
        return provisions;
    }

    public void setProvisions(String provisions) {
        this.provisions = provisions == null ? null : provisions.trim();
    }

    public BigDecimal getProvisionsRate() {
        return provisionsRate;
    }

    public void setProvisionsRate(BigDecimal provisionsRate) {
        this.provisionsRate = provisionsRate;
    }

    public String getFreeField3() {
        return freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3 == null ? null : freeField3.trim();
    }

    public String getFreeField4() {
        return freeField4;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4 == null ? null : freeField4.trim();
    }

    public String getFreeField5() {
        return freeField5;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5 == null ? null : freeField5.trim();
    }
}