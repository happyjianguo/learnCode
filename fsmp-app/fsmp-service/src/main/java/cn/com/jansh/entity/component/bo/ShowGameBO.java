/**
 * CloudgameparamEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月27日
 */
package cn.com.jansh.entity.component.bo;

/**
 * 游戏参数实体
 * @author duanmuyn
 * @version 1.0
 */
public class ShowGameBO {

	/** 游戏id */
    private String gameid;
    
    /** 游戏模板 */
    private String tempname;
    
    /** 游戏模板名称 */
    private String templatename;
    
    /** 游戏名称 */
    private String gamename;
    
    /** 游戏部署路径 */
    private String deploypath;
    
    /** 游戏类别 */
    private String playname;
    
    /** 预算金额 */
    private Integer amount;
    
	/** 已使用金额*/
    private Integer uamount;
    
    /** 渠道(微信-wx，应用-app)*/
    private String channel;
    
    /** 操作人 */
    private String operator;
    
    /** 开始时间 */
    private String begintime;
    
    /** 结束时间 */
    private String endtime;
    
    /** 活动状态 */
    private String status;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    /** 机构id */
    private String orgid;
    /** 公众号id */
    private String appid;
     
    /**
     * 获取游戏id
     * 
     * @return 游戏id
     */
    public String getGameid() {
        return this.gameid;
    }
     
    /**
   	 * @param gamename the gamename to set
   	 */
    public String getGamename() {
		return gamename;
	}

    /**
   	 * @return the gamename
   	 */
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	
	public String getPlayname() {
		return playname;
	}

	public void setPlayname(String playname) {
		this.playname = playname;
	}

	/**
   	 * @return the uamount
   	 */
   	public Integer getUamount() {
   		return uamount;
   	}

   	/**
   	 * @param uamount the uamount to set
   	 */
   	public void setUamount(Integer uamount) {
   		this.uamount = uamount;
   	}

   	/**
   	 * @return the channel
   	 */
   	public String getChannel() {
   		return channel;
   	}

   	/**
   	 * @param channel the channel to set
   	 */
   	public void setChannel(String channel) {
   		this.channel = channel;
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

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getDeploypath() {
		return deploypath;
	}

	public void setDeploypath(String deploypath) {
		this.deploypath = deploypath;
	}
	
}
