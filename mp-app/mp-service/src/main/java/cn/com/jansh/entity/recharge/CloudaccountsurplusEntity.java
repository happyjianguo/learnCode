/**
 * CloudaccountsurplusEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月24日
 */
package cn.com.jansh.entity.recharge;

import java.math.BigDecimal;

/**
 * 账户余额表
 * @author xieliangliang
 * @version 1.0
 */
public class CloudaccountsurplusEntity {

	/** 序号 */
    private String id;
    
    /** 机构ID */
    private String orgid;
    
    /** 机构名称 */
    private String orgname;
    
    /** 当前平台币 */
    private BigDecimal currentmoney;
    
    /** 充值累计平台币 */
    private BigDecimal totalmoney;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /**
     * 获取序号
     * 
     * @return 序号
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置序号
     * 
     * @param id
     *          序号
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取机构ID
     * 
     * @return 机构ID
     */
    public String getOrgid() {
        return this.orgid;
    }
     
    /**
     * 设置机构ID
     * 
     * @param orgid
     *          机构ID
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    
    /**
     * 获取当前平台币
     * 
     * @return 当前平台币
     */
    public BigDecimal getCurrentmoney() {
        return this.currentmoney;
    }
     
    /**
     * 设置当前平台币
     * 
     * @param currentmoney
     *          当前平台币
     */
    public void setCurrentmoney(BigDecimal currentmoney) {
        this.currentmoney = currentmoney;
    }
    
    /**
     * 获取充值累计平台币
     * 
     * @return 充值累计平台币
     */
    public BigDecimal getTotalmoney() {
        return this.totalmoney;
    }
     
    /**
     * 设置充值累计平台币
     * 
     * @param totalmoney
     *          充值累计平台币
     */
    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }
    
    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public String getCreatetime() {
        return this.createtime;
    }
     
    /**
     * 设置创建时间
     * 
     * @param createtime
     *          创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    /**
     * 获取更新时间
     * 
     * @return 更新时间
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
     
    /**
     * 设置更新时间
     * 
     * @param updatetime
     *          更新时间
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

	/**
	 * @return the orgname
	 */
	public String getOrgname() {
		return orgname;
	}

	/**
	 * @param orgname the orgname to set
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
    
}
