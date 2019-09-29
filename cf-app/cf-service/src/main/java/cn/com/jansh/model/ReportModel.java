package cn.com.jansh.model;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;

public class ReportModel {
	 /**客户id   */
    private String cid;
	/**客户名称  */
	private String cname;
	/** 接入者id */
	private String acid;
	/** 接入者名称 */
	private String acname;
	/** 运营商类型  */
    private String ispno;
    /** 成本额  */
    private String totalspprice;
    /** 销售额  */
    private String totalapprice;
    /** 利润  */
    private String profit;
    /** 当前页 */
    private String start;
    /** 总页数 */
    private String count;
    /** 每页显示数*/
    private String length;   
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	/**
     * 要查询的时间
     */
    private String querytime;
    /**
     * 开始结束时间
     */
    private String begintime;
    private String endtime;
    
    
    /**  
     * 创建更新时间
     * */
    private String createtime;
    private String updatetime;
    
    /**
	 * 客户列表
	 */
	private List<CfCustomerEntity> cfCustomerList;
	/**
	 * 接入者列表
	 */
	private List<CfAccessclientEntity> accessclientList;
	/**
	 * 流水列表
	 */
	private List<CfCurrbusilogEntity> currbusiloglist;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getAcname() {
		return acname;
	}
	public void setAcname(String acname) {
		this.acname = acname;
	}
	public String getIspno() {
		return ispno;
	}
	public void setIspno(String ispno) {
		this.ispno = ispno;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	
	public String getTotalspprice() {
		return totalspprice;
	}
	public void setTotalspprice(String totalspprice) {
		this.totalspprice = totalspprice;
	}
	public String getTotalapprice() {
		return totalapprice;
	}
	public void setTotalapprice(String totalapprice) {
		this.totalapprice = totalapprice;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getQuerytime() {
		return querytime;
	}
	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}
	public List<CfCustomerEntity> getCfCustomerList() {
		return cfCustomerList;
	}
	public void setCfCustomerList(List<CfCustomerEntity> cfCustomerList) {
		this.cfCustomerList = cfCustomerList;
	}
	public List<CfAccessclientEntity> getAccessclientList() {
		return accessclientList;
	}
	public void setAccessclientList(List<CfAccessclientEntity> accessclientList) {
		this.accessclientList = accessclientList;
	}
	public List<CfCurrbusilogEntity> getCurrbusiloglist() {
		return currbusiloglist;
	}
	public void setCurrbusiloglist(List<CfCurrbusilogEntity> currbusiloglist) {
		this.currbusiloglist = currbusiloglist;
	}
	@Override
	public String toString() {
		return "ReportModel [cid=" + cid + ", cname=" + cname + ", acid=" + acid + ", acname=" + acname + ", ispno="
				+ ispno + ", totalspprice=" + totalspprice
				+ ", totalapprice=" + totalapprice + ", profit=" + profit + ", start=" + start + ", count=" + count
				+ ", length=" + length + ",  querytime=" + querytime
				+ ", begintime=" + begintime + ", endtime=" + endtime + ", createtime=" + createtime + ", updatetime="
				+ updatetime + ", cfCustomerList=" + cfCustomerList + ", accessclientList=" + accessclientList
				+ ", currbusiloglist=" + currbusiloglist + "]";
	}      
}
