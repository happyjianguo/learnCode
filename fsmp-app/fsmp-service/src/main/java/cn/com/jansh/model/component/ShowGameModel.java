/**
 * CloudgameparamEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月27日
 */
package cn.com.jansh.model.component;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import cn.com.jansh.entity.component.CloudgameinitEntity;
import cn.com.jansh.entity.component.GametemplateEntity;
import cn.com.jansh.entity.wechat.AuthAccount;

/**
 * 游戏参数实体
 * @author duanmuyn
 * @version 1.0
 */
public class ShowGameModel {

	/** 游戏id */
    private String gameid;
    
    /** 游戏模板 */
    private String tempname;
    
    /** 游戏模板名称 */
    private String templatename;
    
    /** 游戏部署路径 */
    private String deploypath;
    
    /** 游戏名称 */
	@NotBlank
	@Size(max=120,min=1)
    private String gamename;
    
    /** 游戏类别 */
	@NotBlank
	@Size(max=20)
    private String playname;
	
	/** 游戏模板  */
	@NotBlank
	@Size(max=20)
	private String tempid;
	
	/** 指定游戏 */
	private String directId;
    
    /** 预算金额 */
    private Integer amount;
    
	/** 已使用金额*/
    private Integer uamount;
    
    /** 渠道(微信-wx，应用-app)*/
    @NotBlank
	@Size(max=2,min=1)
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
	@Size(max=20)
    private String appid;
    
    /** 公众号集合 */
    private AuthAccount wechat;
    
    /** 游戏集合 */
	private List<CloudgameinitEntity> cloudgames;
	
	/** 游戏模板集合*/
	private List<GametemplateEntity> gameTemps;
     
    /**
     * 获取游戏id
     * 
     * @return 游戏id
     */
    public String getGameid() {
        return this.gameid;
    }
    
	
    public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}


	public String getPlayname() {
		return playname;
	}


	public void setPlayname(String playname) {
		this.playname = playname;
	}

	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
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
    
    public String getTempid() {
		return tempid;
	}
	public void setTempid(String tempid) {
		this.tempid = tempid;
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
	public List<CloudgameinitEntity> getCloudgames() {
		return cloudgames;
	}
	public void setCloudgames(List<CloudgameinitEntity> cloudgames) {
		this.cloudgames = cloudgames;
	}
	public String getDirectId() {
		return directId;
	}
	public void setDirectId(String directId) {
		this.directId = directId;
	}
	public List<GametemplateEntity> getGameTemps() {
		return gameTemps;
	}
	public void setGameTemps(List<GametemplateEntity> gameTemps) {
		this.gameTemps = gameTemps;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public AuthAccount getWechat() {
		return wechat;
	}
	public void setWechat(AuthAccount wechat) {
		this.wechat = wechat;
	}

	public String getDeploypath() {
		return deploypath;
	}

	public void setDeploypath(String deploypath) {
		this.deploypath = deploypath;
	}
	
}
