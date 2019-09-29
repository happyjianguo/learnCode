/**
 * RegisterReviewModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月18日
 */
package cn.com.jansh.model.review;

import java.util.List;

import cn.com.jansh.entity.review.FsmUserEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;

/**
 * 用户注册使用MODEL
 * @author xieliangliang
 * @version 1.0
 */
public class RegisterReviewModel {
	
	  /** 用户id */
    private String userid;
    
    /** 机构代码 */
    private String orgid;
    
    /** 用户登录名 */
    private String username;
    
    /** 用户登录名-参数 */
    private String usernames;
    
    /** 密码 */
    private String passwd;
    
    /** 用户姓名 */
    private String cname;
    
    /** 密码错误次数 */
    private String pwderrnum;
    
    /** 状态;0-失效,1-正常,2-冻结,3待审核，4审核失败 */
    private String status;
    
    /** 更新时间YYYYMMDDHH24MISS */
    private String updatetime;
    
    /** 创建时间YYYYMMDDHH24MISS */
    private String createtime;
    
    /** 邮箱 */
    private String email;
    
    /** 手机号 */
    private String phoneno;
    
    /**  */
    private String mf;
    
    /** 用户表 */
    private String reviewper;
    
    /** 审核描述 */
    private String reviewdes;
    
    private List<FsmUserEntity> fsmUserList;
    
    /** 机构列表  */
    private List<CloudplatformorginationEntity> cloudpforgList;
    
    /** 修改机构标识*/
    private String mflg;
    
    
    /**
     * 获取用户id
     * 
     * @return 用户id
     */
    public String getUserid() {
        return this.userid;
    }
     
    /**
     * 设置用户id
     * 
     * @param userid
     *          用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    /**
     * 获取机构代码
     * 
     * @return 机构代码
     */
    public String getOrgid() {
        return this.orgid;
    }
     
    /**
     * 设置机构代码
     * 
     * @param orgid
     *          机构代码
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    
    /**
     * 获取用户登录名
     * 
     * @return 用户登录名
     */
    public String getUsername() {
        return this.username;
    }
     
    /**
     * 设置用户登录名
     * 
     * @param username
     *          用户登录名
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 获取密码
     * 
     * @return 密码
     */
    public String getPasswd() {
        return this.passwd;
    }
     
    /**
     * 设置密码
     * 
     * @param passwd
     *          密码
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    /**
     * 获取用户姓名
     * 
     * @return 用户姓名
     */
    public String getCname() {
        return this.cname;
    }
     
    /**
     * 设置用户姓名
     * 
     * @param cname
     *          用户姓名
     */
    public void setCname(String cname) {
        this.cname = cname;
    }
    
    /**
     * 获取密码错误次数
     * 
     * @return 密码错误次数
     */
    public String getPwderrnum() {
        return this.pwderrnum;
    }
     
    /**
     * 设置密码错误次数
     * 
     * @param pwderrnum
     *          密码错误次数
     */
    public void setPwderrnum(String pwderrnum) {
        this.pwderrnum = pwderrnum;
    }
    
    /**
     * 获取状态;0-失效,1-正常,2-冻结,3待审核，4审核失败
     * 
     * @return 状态;0-失效,1-正常,2-冻结,3待审核
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态;0-失效,1-正常,2-冻结,3待审核，4审核失败
     * 
     * @param status
     *          状态;0-失效,1-正常,2-冻结,3待审核，4审核失败
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 获取更新时间YYYYMMDDHH24MISS
     * 
     * @return 更新时间YYYYMMDDHH24MISS
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
     
    /**
     * 设置更新时间YYYYMMDDHH24MISS
     * 
     * @param updatetime
     *          更新时间YYYYMMDDHH24MISS
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    
    /**
     * 获取创建时间YYYYMMDDHH24MISS
     * 
     * @return 创建时间YYYYMMDDHH24MISS
     */
    public String getCreatetime() {
        return this.createtime;
    }
     
    /**
     * 设置创建时间YYYYMMDDHH24MISS
     * 
     * @param createtime
     *          创建时间YYYYMMDDHH24MISS
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    /**
     * 获取邮箱
     * 
     * @return 邮箱
     */
    public String getEmail() {
        return this.email;
    }
     
    /**
     * 设置邮箱
     * 
     * @param email
     *          邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getMf() {
        return this.mf;
    }
     
    /**
     * 获取手机号
     * 
     * @return 手机号
     */
	public String getPhoneno() {
		return phoneno;
	}

	 /**
     * 设置手机号
     * 
     * @param telephone
     *          手机号
     */
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	/**
     * 设置
     * 
     * @param mf
     *          
     */
    public void setMf(String mf) {
        this.mf = mf;
    }
    
    /**
     * 获取用户表
     * 
     * @return 用户表
     */
    public String getReviewper() {
        return this.reviewper;
    }
     
    /**
     * 设置用户表
     * 
     * @param reviewper
     *          用户表
     */
    public void setReviewper(String reviewper) {
        this.reviewper = reviewper;
    }
    
    /**
     * 获取审核意见
     * 
     * @return 
     */
    public String getReviewdes() {
        return this.reviewdes;
    }
     
    /**
     * 设置审核意见
     * 
     * @param reviewdes
     *          
     */
    public void setReviewdes(String reviewdes) {
        this.reviewdes = reviewdes;
    }

	/**
	 * @return the fsmUserList
	 */
	public List<FsmUserEntity> getFsmUserList() {
		return fsmUserList;
	}

	/**
	 * @param fsmUserList the fsmUserList to set
	 */
	public void setFsmUserList(List<FsmUserEntity> fsmUserList) {
		this.fsmUserList = fsmUserList;
	}

	/**
	 * @return the usernames
	 */
	public String getUsernames() {
		return usernames;
	}

	/**
	 * @param usernames the usernames to set
	 */
	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}

	/**
	 * @return the cloudpforgList
	 */
	public List<CloudplatformorginationEntity> getCloudpforgList() {
		return cloudpforgList;
	}

	/**
	 * @param cloudpforgList the cloudpforgList to set
	 */
	public void setCloudpforgList(List<CloudplatformorginationEntity> cloudpforgList) {
		this.cloudpforgList = cloudpforgList;
	}

	/**
	 * @return the mflg
	 */
	public String getMflg() {
		return mflg;
	}

	/**
	 * @param mflg the mflg to set
	 */
	public void setMflg(String mflg) {
		this.mflg = mflg;
	}
	
}
