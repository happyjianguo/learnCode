package cn.com.jansh.entity.risk;
/******************************************************************
** 标    题：Blacklist
** 创 建 者：bianj
** 创建日期：2016-10-18 16:13:40
** 描    述：黑名单表
******************************************************************/

/**
 * 黑名单表(BLACKLIST)
 * 
 * @author bianj
 * @version 1.0.0 2016-10-18
 */
public class BlacklistEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 8200275402696683681L;
    
    /** 黑名单id */
    private String blackid;
    
    /** 黑名单类型 */
    private String blacktype;
    
    /** 黑名单值 */
    private String blackvalue;
    
    /** 黑名单时间 */
    private String actionid;
    
    /** 黑名单时间 */
    private String playname;
    
    /** 状态：0-过期 ，1-生效 */
    private String status;
    
    /** 过期时间 */
    private String overtime;
    
    /** 说明  */
    private String remark;
    
    /**
     * 获取黑名单id
     * 
     * @return 黑名单id
     */
    public String getBlackid() {
        return this.blackid;
    }
     
    /**
     * 设置黑名单id
     * 
     * @param blackid
     *          黑名单id
     */
    public void setBlackid(String blackid) {
        this.blackid = blackid;
    }
    
    /**
     * 获取黑名单类型
     * 
     * @return 黑名单类型
     */
    public String getBlacktype() {
        return this.blacktype;
    }
     
    /**
     * 设置黑名单类型
     * 
     * @param blacktype
     *          黑名单类型
     */
    public void setBlacktype(String blacktype) {
        this.blacktype = blacktype;
    }
    
    /**
     * 获取黑名单值
     * 
     * @return 黑名单值
     */
    public String getBlackvalue() {
        return this.blackvalue;
    }
     
    /**
     * 设置黑名单值
     * 
     * @param blackvalue
     *          黑名单值
     */
    public void setBlackvalue(String blackvalue) {
        this.blackvalue = blackvalue;
    }
    
    /**
     * 获取黑名单时间
     * 
     * @return 黑名单时间
     */
    public String getActionid() {
        return this.actionid;
    }
     
    /**
     * 设置黑名单时间
     * 
     * @param actionid
     *          黑名单时间
     */
    public void setActionid(String actionid) {
        this.actionid = actionid;
    }
    
    /**
     * 获取状态：0-过期 ，1-生效
     * 
     * @return 状态：0-过期 
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态：0-过期 ，1-生效
     * 
     * @param status
     *          状态：0-过期 ，1-生效
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 获取过期时间
     * 
     * @return 过期时间
     */
    public String getOvertime() {
        return this.overtime;
    }
     
    /**
     * 设置过期时间
     * 
     * @param overtime
     *          过期时间
     */
    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getRemark() {
        return this.remark;
    }
     
    /**
     * 设置
     * 
     * @param remark
     *          
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getPlayname() {
		return playname;
	}

	public void setPlayname(String playname) {
		this.playname = playname;
	}
	@Override
	public String toString() {
		return "BlacklistEntity [blackid=" + blackid + ", blacktype=" + blacktype + ", blackvalue=" + blackvalue
				+ ", actionid=" + actionid + ", playname=" + playname + ", status=" + status + ", overtime=" + overtime
				+ ", remark=" + remark + "]";
	}
}
