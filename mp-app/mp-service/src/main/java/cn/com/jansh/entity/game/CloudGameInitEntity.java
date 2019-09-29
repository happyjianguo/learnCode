/**
 * CloudGameInitEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月9日
 */
package cn.com.jansh.entity.game;

/**
 * 游戏活动参数初始化实体类
 * @author xieliangliang
 * @version 1.0
 */
public class CloudGameInitEntity {
	
	/** 游戏id */
    private String id;
    
    /** 序号 */
    private Integer indexnum;
    
    /** 渠道 */
    private String channel;
    
    /** 游戏名称 */
    private String playname;
    
    /** 部署路径 */
    private String deploypath;
    
    /** 状态（0--未上线，1--上线，2--下线，3--推荐） */
    private String status;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /** 操作人 */
    private String operator;
    
    /** app活动数据接口地址 */
    private String appactionurl;
    
    /** 微信活动数据接口地址 */
    private String wxactionurl;
    
    /** app活动获奖数据接口地址 */
    private String apprewardurl;
    
    /** 微信活动获奖数据接口地址 */
    private String wxrewardurl;

	public String getAppactionurl() {
		return appactionurl;
	}

	public void setAppactionurl(String appactionurl) {
		this.appactionurl = appactionurl;
	}

	public String getWxactionurl() {
		return wxactionurl;
	}

	public void setWxactionurl(String wxactionurl) {
		this.wxactionurl = wxactionurl;
	}

	public String getApprewardurl() {
		return apprewardurl;
	}

	public void setApprewardurl(String apprewardurl) {
		this.apprewardurl = apprewardurl;
	}

	public String getWxrewardurl() {
		return wxrewardurl;
	}

	public void setWxrewardurl(String wxrewardurl) {
		this.wxrewardurl = wxrewardurl;
	}

	/**
     * 获取游戏id
     * 
     * @return 游戏id
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置游戏id
     * 
     * @param id
     *          游戏id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取序号
     * 
     * @return 序号
     */
    public Integer getIndexnum() {
        return this.indexnum;
    }
     
    /**
     * 设置序号
     * 
     * @param indexnum
     *          序号
     */
    public void setIndexnum(Integer indexnum) {
        this.indexnum = indexnum;
    }
    
    /**
     * 获取渠道
     * 
     * @return 渠道
     */
    public String getChannel() {
        return this.channel;
    }
     
    /**
     * 设置渠道
     * 
     * @param channel
     *          渠道
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    /**
     * 获取游戏名称
     * 
     * @return 游戏名称
     */
    public String getPlayname() {
        return this.playname;
    }
     
    /**
     * 设置游戏名称
     * 
     * @param playname
     *          游戏名称
     */
    public void setPlayname(String playname) {
        this.playname = playname;
    }
    
    /**
     * 获取部署路径
     * 
     * @return 部署路径
     */
    public String getDeploypath() {
        return this.deploypath;
    }
     
    /**
     * 设置部署路径
     * 
     * @param deploypath
     *          部署路径
     */
    public void setDeploypath(String deploypath) {
        this.deploypath = deploypath;
    }
    
    /**
     * 获取状态（0--未上线，1--上线，2--下线，3--推荐）
     * 
     * @return 状态（0--未上线
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态（0--未上线，1--上线，2--下线，3--推荐）
     * 
     * @param status
     *          状态（0--未上线，1--上线，2--下线，3--推荐）
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
		return "CloudGameInitEntity [id=" + id + ", indexnum=" + indexnum + ", channel=" + channel + ", playname="
				+ playname + ", deploypath=" + deploypath + ", status=" + status + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", operator=" + operator + ", appactionurl=" + appactionurl
				+ ", wxactionurl=" + wxactionurl + ", apprewardurl=" + apprewardurl + ", wxrewardurl=" + wxrewardurl
				+ "]";
	}
}
