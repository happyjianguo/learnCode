/******************************************************************
** 标    题：CloudgameparamEntity
** 创 建 者：gll
** 创建日期：2016-12-01 11:42:50
** 描    述：活动参数表
******************************************************************/
package cn.com.jansh.entity.game;
/**
 * 活动参数表
 * 
 * @author gll
 * @version 1.0.0 2016-12-01
 */
public class CloudgameparamEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -7299307197061786396L;
    
    /** 游戏id */
    private String gameid;
    
    /** 游戏名称 */
    private String gamename;
    
    /** 游戏类型 与gameinit 主键关联 */
    private String gametype;
    
    /** 模板id */
    private String tempid;
    
    /** 预算金额 */
    private Integer amount;
    
    /** 已使用预算 */
    private Integer uamount;
    
    /** 机构id */
    private String orgid;
    
    /** 渠道(微信-wx，应用-app) 微信：0   应用 ：1 */
    private String channel;
    
    /** 公众号ID */
    private String appid;
    
    /** 开始时间 */
    private String begintime;
    
    /** 结束时间 */
    private String endtime;
    
    /** 0表示未发布，1表示发布，2表示活动已经结束 */
    private String status;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /** 操作人 */
    private String operator;
    
    public String getTempid() {
		return tempid;
	}

	public void setTempid(String tempid) {
		this.tempid = tempid;
	}

	/**
     * 获取游戏id
     * 
     * @return 游戏id
     */
    public String getGameid() {
        return this.gameid;
    }
     
    /**
     * 设置游戏id
     * 
     * @param gameid
     *          游戏id
     */
    public void setGameid(String gameid) {
        this.gameid = gameid;
    }
    
    /**
     * 获取游戏名称
     * 
     * @return 游戏名称
     */
    public String getGamename() {
        return this.gamename;
    }
     
    /**
     * 设置游戏名称
     * 
     * @param gamename
     *          游戏名称
     */
    public void setGamename(String gamename) {
        this.gamename = gamename;
    }
    
    /**
     * 获取游戏类型 与gameinit 主键关联
     * 
     * @return 游戏类型 与gameinit 主键关联
     */
    public String getGametype() {
        return this.gametype;
    }
     
    /**
     * 设置游戏类型 与gameinit 主键关联
     * 
     * @param gametype
     *          游戏类型 与gameinit 主键关联
     */
    public void setGametype(String gametype) {
        this.gametype = gametype;
    }
    
    /**
     * 获取预算金额
     * 
     * @return 预算金额
     */
    public Integer getAmount() {
        return this.amount;
    }
     
    /**
     * 设置预算金额
     * 
     * @param amount
     *          预算金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    /**
     * 获取已使用预算
     * 
     * @return 已使用预算
     */
    public Integer getUamount() {
        return this.uamount;
    }
     
    /**
     * 设置已使用预算
     * 
     * @param uamount
     *          已使用预算
     */
    public void setUamount(Integer uamount) {
        this.uamount = uamount;
    }
    
    /**
     * 获取机构id
     * 
     * @return 机构id
     */
    public String getOrgid() {
        return this.orgid;
    }
     
    /**
     * 设置机构id
     * 
     * @param orgid
     *          机构id
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    
    /**
     * 获取渠道(微信-wx，应用-app) 微信：0   应用 ：1
     * 
     * @return 渠道(微信-wx
     */
    public String getChannel() {
        return this.channel;
    }
     
    /**
     * 设置渠道(微信-wx，应用-app) 微信：0   应用 ：1
     * 
     * @param channel
     *          渠道(微信-wx，应用-app) 微信：0   应用 ：1
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    /**
     * 获取公众号ID
     * 
     * @return 公众号ID
     */
    public String getAppid() {
        return this.appid;
    }
     
    /**
     * 设置公众号ID
     * 
     * @param appid
     *          公众号ID
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }
    
    /**
     * 获取开始时间
     * 
     * @return 开始时间
     */
    public String getBegintime() {
        return this.begintime;
    }
     
    /**
     * 设置开始时间
     * 
     * @param begintime
     *          开始时间
     */
    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }
    
    /**
     * 获取结束时间
     * 
     * @return 结束时间
     */
    public String getEndtime() {
        return this.endtime;
    }
     
    /**
     * 设置结束时间
     * 
     * @param endtime
     *          结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    
    /**
     * 获取0表示未发布，1表示发布，2表示活动已经结束
     * 
     * @return 0表示未发布
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置0表示未发布，1表示发布，2表示活动已经结束
     * 
     * @param status
     *          0表示未发布，1表示发布，2表示活动已经结束
     */
    public void setStatus(String status) {
        this.status = status;
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

	@Override
	public String toString() {
		return "CloudgameparamEntity [gameid=" + gameid + ", gamename=" + gamename + ", gametype=" + gametype
				+ ", tempid=" + tempid + ", amount=" + amount + ", uamount=" + uamount + ", orgid=" + orgid
				+ ", channel=" + channel + ", appid=" + appid + ", begintime=" + begintime + ", endtime=" + endtime
				+ ", status=" + status + ", createtime=" + createtime + ", updatetime=" + updatetime + ", operator="
				+ operator + "]";
	}
}