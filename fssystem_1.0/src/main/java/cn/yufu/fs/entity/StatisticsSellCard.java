/**
 *包名:cn.yufu.fs.entity
 *描述:package cn.yufu.fs.entity;
 */
package cn.yufu.fs.entity;

import java.math.BigDecimal;

/**
 * StatisticsSellCard.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月15日
 * 描述:网点售卡统计
 */
public class StatisticsSellCard {
	
	private String stlDate;

    private String startStlDate;

    private String endStlDate;
    
    private String ordercode;//订单号

    private String adminName;//售卡员用户名

    private String adminRealname;//售卡员姓名

    private BigDecimal pointid;//售卡网点编号

    private String pointName;//售卡网点名称
    
    private BigDecimal cardtotalprice;//售卡总金额

    private String outcardverifytime;//售卡时间

    private String provisions;//备付金

    private BigDecimal companyId;//分公司id

    private String companyName;//分公司名称

    private BigDecimal provisionsRate;//备付金和购卡比例

    private String freeField3;

    private String freeField4;

    private String freeField5;

	public String getStlDate() {
		return stlDate;
	}

	public void setStlDate(String stlDate) {
		this.stlDate = stlDate;
	}

	public String getStartStlDate() {
		return startStlDate;
	}

	public void setStartStlDate(String startStlDate) {
		this.startStlDate = startStlDate;
	}

	public String getEndStlDate() {
		return endStlDate;
	}

	public void setEndStlDate(String endStlDate) {
		this.endStlDate = endStlDate;
	}

	@Override
	public String toString() {
		return "StatisticsSellCard [stlDate=" + stlDate + ", startStlDate=" + startStlDate + ", endStlDate="
				+ endStlDate + ", ordercode=" + ordercode + ", adminName=" + adminName + ", adminRealname="
				+ adminRealname + ", pointid=" + pointid + ", pointName=" + pointName + ", companyId=" + companyId
				+ ", companyName=" + companyName + ", cardtotalprice=" + cardtotalprice + ", outcardverifytime="
				+ outcardverifytime + ", provisions=" + provisions + ", provisionsRate=" + provisionsRate
				+ ", freeField3=" + freeField3 + ", freeField4=" + freeField4 + ", freeField5=" + freeField5 + "]";
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminRealname() {
		return adminRealname;
	}

	public void setAdminRealname(String adminRealname) {
		this.adminRealname = adminRealname;
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
		this.pointName = pointName;
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
		this.companyName = companyName;
	}

	public BigDecimal getCardtotalprice() {
		return cardtotalprice;
	}

	public void setCardtotalprice(BigDecimal cardtotalprice) {
		this.cardtotalprice = cardtotalprice;
	}

	public String getOutcardverifytime() {
		return outcardverifytime;
	}

	public void setOutcardverifytime(String outcardverifytime) {
		this.outcardverifytime = outcardverifytime;
	}

	public String getProvisions() {
		return provisions;
	}

	public void setProvisions(String provisions) {
		this.provisions = provisions;
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
		this.freeField3 = freeField3;
	}

	public String getFreeField4() {
		return freeField4;
	}

	public void setFreeField4(String freeField4) {
		this.freeField4 = freeField4;
	}

	public String getFreeField5() {
		return freeField5;
	}

	public void setFreeField5(String freeField5) {
		this.freeField5 = freeField5;
	}
}
