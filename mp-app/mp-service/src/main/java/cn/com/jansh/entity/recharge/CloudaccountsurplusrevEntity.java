/**
 * CloudaccountsurplusrevEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月19日
 */
package cn.com.jansh.entity.recharge;

import java.math.BigDecimal;

/**
 * 人工充值审核表ENTITY
 * @author xieliangliang
 * @version 1.0
 */
public class CloudaccountsurplusrevEntity {
	
	 /** 序号 */
    private String id;
    
    /** 机构ID */
    private String orgid;
    
    /** 机构名称 */
    private String orgname;
    
    /** 充值金额 */
    private BigDecimal amount;
    
    /** 操作人 */
    private String operator;
    
    /** 审核状态   0-审核通过;1-审核不通过;2-待审核 */
    private String rstatus;
    
    /** 审核人 */
    private String auditor;
    
    /** 审核意见 */
    private String audresult;
    
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
     * 获取充值金额
     * 
     * @return 充值金额
     */
    public BigDecimal getAmount() {
        return this.amount;
    }
     
    /**
     * 设置充值金额
     * 
     * @param amount
     *          充值金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    /**
     * 获取操作人
     * 
     * @return 操作人
     */
    public String getOperator() {
        return this.operator;
    }
     
    /**
     * 设置操作人
     * 
     * @param operator
     *          操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    /**
     * 获取审核状态   0-审核通过;1-审核不通过;2-待审核
     * 
     * @return 审核状态   0-审核通过;1-审核不通过;2-待审核
     */
    public String getRstatus() {
        return this.rstatus;
    }
     
    /**
     * 设置审核状态   0-审核通过;1-审核不通过;2-待审核
     * 
     * @param rstatus
     *          审核状态   0-审核通过;1-审核不通过;2-待审核
     */
    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
    }
    
    /**
     * 获取审核人
     * 
     * @return 审核人
     */
    public String getAuditor() {
        return this.auditor;
    }
     
    /**
     * 设置审核人
     * 
     * @param auditor
     *          审核人
     */
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
    
    /**
     * 获取审核意见
     * 
     * @return 审核意见
     */
    public String getAudresult() {
        return this.audresult;
    }
     
    /**
     * 设置审核意见
     * 
     * @param audresult
     *          审核意见
     */
    public void setAudresult(String audresult) {
        this.audresult = audresult;
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
