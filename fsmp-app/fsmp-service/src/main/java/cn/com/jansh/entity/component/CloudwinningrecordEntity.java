package cn.com.jansh.entity.component;
/******************************************************************
** 标    题：BlacklistEntity
** 创建日期：2016-10-21 09:56:36
** 描    述：中奖记录表
******************************************************************/

/**
 * @version 1.0.0 2016-10-21
 */
public class CloudwinningrecordEntity {
    
    /** 中奖记录ID */
    private String id;
    
    /** 游戏中奖ID */
    private String awardid;
    
    /** OPENID */
    private String openid;
    
    /** 机构ID */
    private String orgid;
    
    /** 活动ID */
    private String gameid;
    
    /** 中奖手机号 */
    private String winnerphone;
    
    /** 发货状态 */
    private String sendstatus;
    
    /** 奖品ID */
    private String prizestyle;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /** 审核状态 */
    private String preliminarystatus;
    
    /** 初审人 */
    private String preliminaryper;
    
    /** 复审人 */
    private String reviewper;
    
    /** 奖品分类*/
    private String prizetype;

    /** 奖品名称*/
    private String prizename;
    
    /**
	 * @return the prizetype
	 */
	public String getPrizetype() {
		return prizetype;
	}

	/**
	 * @param prizetype the prizetype to set
	 */
	public void setPrizetype(String prizetype) {
		this.prizetype = prizetype;
	}

	/**
	 * @return the prizename
	 */
	public String getPrizename() {
		return prizename;
	}

	/**
	 * @param prizename the prizename to set
	 */
	public void setPrizename(String prizename) {
		this.prizename = prizename;
	}

	/**
     * 获取中奖记录ID
     * 
     * @return 中奖记录ID
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置中奖记录ID
     * 
     * @param id
     *          中奖记录ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取OPENID
     * 
     * @return OPENID
     */
    public String getOpenid() {
        return this.openid;
    }
     
    /**
     * 设置OPENID
     * 
     * @param openid
     *          OPENID
     */
    public void setOpenid(String openid) {
        this.openid = openid;
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
     * 获取活动ID
     * 
     * @return 活动ID
     */
    public String getGameid() {
        return this.gameid;
    }
     
    /**
     * 设置活动ID
     * 
     * @param gameid
     *          活动ID
     */
    public void setGameid(String gameid) {
        this.gameid = gameid;
    }
    
    /**
     * 获取中奖手机号
     * 
     * @return 中奖手机号
     */
    public String getWinnerphone() {
        return this.winnerphone;
    }
     
    /**
     * 设置中奖手机号
     * 
     * @param winnerphone
     *          中奖手机号
     */
    public void setWinnerphone(String winnerphone) {
        this.winnerphone = winnerphone;
    }
    
    /**
     * 获取发货状态
     * 
     * @return 发货状态
     */
    public String getSendstatus() {
        return this.sendstatus;
    }
     
    /**
     * 设置发货状态
     * 
     * @param sendstatus
     *          发货状态
     */
    public void setSendstatus(String sendstatus) {
        this.sendstatus = sendstatus;
    }
    
    /**
     * 获取奖品ID
     * 
     * @return 奖品ID
     */
    public String getPrizestyle() {
        return this.prizestyle;
    }
     
    /**
     * 设置奖品ID
     * 
     * @param prizestyle
     *          奖品ID
     */
    public void setPrizestyle(String prizestyle) {
        this.prizestyle = prizestyle;
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
     * 获取审核状态
     * 
     * @return 审核状态
     */
    public String getPreliminarystatus() {
        return this.preliminarystatus;
    }
     
    /**
     * 设置审核状态
     * 
     * @param preliminarystatus
     *          审核状态
     */
    public void setPreliminarystatus(String preliminarystatus) {
        this.preliminarystatus = preliminarystatus;
    }
    
    /**
     * 获取初审人
     * 
     * @return 初审人
     */
    public String getPreliminaryper() {
        return this.preliminaryper;
    }
     
    /**
     * 设置初审人
     * 
     * @param preliminaryper
     *          初审人
     */
    public void setPreliminaryper(String preliminaryper) {
        this.preliminaryper = preliminaryper;
    }
    
    /**
     * 获取复审人
     * 
     * @return 复审人
     */
    public String getReviewper() {
        return this.reviewper;
    }
     
    /**
     * 设置复审人
     * 
     * @param reviewper
     *          复审人
     */
    public void setReviewper(String reviewper) {
        this.reviewper = reviewper;
    }

	public String getAwardid() {
		return awardid;
	}

	public void setAwardid(String awardid) {
		this.awardid = awardid;
	}
    
    
}