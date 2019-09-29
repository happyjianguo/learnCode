/**
 * CloudrechargerecordEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月25日
 */
package cn.com.jansh.entity.recharge;

import java.math.BigDecimal;

/**
 * 充值记录流水表
 * @author xieliangliang
 * @version 1.0
 */
public class CloudrechargerecordEntity {
	
	 /** 充值记录ID */
    private String orderid;
    
    /** 用户ID */
    private String userid;
    
    /** 机构ID */
    private String orgid;
    
    /** 机构名称 */
    private String orgname;
    
    /** 充值状态:0-成功; */
    private String status;
    
    /** 平台币数量 */
    private BigDecimal currentmoney;
    
    /** 单笔充值id */
    private String rechargeid;
    
    /** 交易时间 */
    private String txntime;
    
    /** 流水号 */
    private String queryid;
    
    /** 支付方式:0-线下 */
    private String paytype;
    
    /** 机构码 */
    private String code;
    
    /** 交易金额 */
    private Integer price;
    
    /** 加签串 */
    private String sign;
    
    /** 消费交易应答码 */
    private String origrespcode;
    
    /** 查询交易应答码 */
    private String respcode;
    
    /**  */
    private String traceno;
    
    /** 更新时间，回调时间 */
    private String updatetime;
    
    /** 更新状态 */
    private String status1;
    
    /** 交易查询次数 */
    private String querynum;
    
    /**
     * 获取充值记录ID
     * 
     * @return 充值记录ID
     */
    public String getOrderid() {
        return this.orderid;
    }
     
    /**
     * 设置充值记录ID
     * 
     * @param orderid
     *          充值记录ID
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    /**
     * 获取用户ID
     * 
     * @return 用户ID
     */
    public String getUserid() {
        return this.userid;
    }
     
    /**
     * 设置用户ID
     * 
     * @param userid
     *          用户ID
     */
    public void setUserid(String userid) {
        this.userid = userid;
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
     * 获取充值状态:0-成功;
     * 
     * @return 充值状态
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置充值状态:0-成功;
     * 
     * @param status
     *          充值状态:0-成功;
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 获取平台币数量
     * 
     * @return 平台币数量
     */
    public BigDecimal getCurrentmoney() {
        return this.currentmoney;
    }
     
    /**
     * 设置平台币数量
     * 
     * @param currentmoney
     *          平台币数量
     */
    public void setCurrentmoney(BigDecimal currentmoney) {
        this.currentmoney = currentmoney;
    }
    
    /**
     * 获取单笔充值id
     * 
     * @return 单笔充值id
     */
    public String getRechargeid() {
        return this.rechargeid;
    }
     
    /**
     * 设置单笔充值id
     * 
     * @param rechargeid
     *          单笔充值id
     */
    public void setRechargeid(String rechargeid) {
        this.rechargeid = rechargeid;
    }
    
    /**
     * 获取交易时间
     * 
     * @return 交易时间
     */
    public String getTxntime() {
        return this.txntime;
    }
     
    /**
     * 设置交易时间
     * 
     * @param txntime
     *          交易时间
     */
    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }
    
    /**
     * 获取流水号
     * 
     * @return 流水号
     */
    public String getQueryid() {
        return this.queryid;
    }
     
    /**
     * 设置流水号
     * 
     * @param queryid
     *          流水号
     */
    public void setQueryid(String queryid) {
        this.queryid = queryid;
    }
    
    /**
     * 获取支付方式:0-线下
     * 
     * @return 支付方式
     */
    public String getPaytype() {
        return this.paytype;
    }
     
    /**
     * 设置支付方式:0-线下
     * 
     * @param paytype
     *          支付方式:0-线下
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    
    /**
     * 获取机构码
     * 
     * @return 机构码
     */
    public String getCode() {
        return this.code;
    }
     
    /**
     * 设置机构码
     * 
     * @param code
     *          机构码
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 获取交易金额
     * 
     * @return 交易金额
     */
    public Integer getPrice() {
        return this.price;
    }
     
    /**
     * 设置交易金额
     * 
     * @param price
     *          交易金额
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
    
    /**
     * 获取加签串
     * 
     * @return 加签串
     */
    public String getSign() {
        return this.sign;
    }
     
    /**
     * 设置加签串
     * 
     * @param sign
     *          加签串
     */
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    /**
     * 获取消费交易应答码
     * 
     * @return 消费交易应答码
     */
    public String getOrigrespcode() {
        return this.origrespcode;
    }
     
    /**
     * 设置消费交易应答码
     * 
     * @param origrespcode
     *          消费交易应答码
     */
    public void setOrigrespcode(String origrespcode) {
        this.origrespcode = origrespcode;
    }
    
    /**
     * 获取查询交易应答码
     * 
     * @return 查询交易应答码
     */
    public String getRespcode() {
        return this.respcode;
    }
     
    /**
     * 设置查询交易应答码
     * 
     * @param respcode
     *          查询交易应答码
     */
    public void setRespcode(String respcode) {
        this.respcode = respcode;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getTraceno() {
        return this.traceno;
    }
     
    /**
     * 设置
     * 
     * @param traceno
     *          
     */
    public void setTraceno(String traceno) {
        this.traceno = traceno;
    }
    
    /**
     * 获取更新时间，回调时间
     * 
     * @return 更新时间
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
     
    /**
     * 设置更新时间，回调时间
     * 
     * @param updatetime
     *          更新时间，回调时间
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    
    /**
     * 获取更新状态
     * 
     * @return 更新状态
     */
    public String getStatus1() {
        return this.status1;
    }
     
    /**
     * 设置更新状态
     * 
     * @param status1
     *          更新状态
     */
    public void setStatus1(String status1) {
        this.status1 = status1;
    }
    
    /**
     * 获取交易查询次数
     * 
     * @return 交易查询次数
     */
    public String getQuerynum() {
        return this.querynum;
    }
     
    /**
     * 设置交易查询次数
     * 
     * @param querynum
     *          交易查询次数
     */
    public void setQuerynum(String querynum) {
        this.querynum = querynum;
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
