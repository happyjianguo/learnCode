/**
 * CloudplatformorginationModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月19日
 */
package cn.com.jansh.model.system;

import java.util.List;

import cn.com.jansh.entity.system.CloudplatformorginationEntity;

/**
 * 机构Model
 * @author gll
 * @version 1.0
 */
public class CloudplatformorginationModel {

	 /** 机构ID */
    private String id;
    
    /** 父级机构ID */
    private String parentorgid;
    
    /** 分行机构id*/
    private String branchid;
    
    /** 父级机构Name */
    private String parentorgname;
    
    /** 机构名称 */
    private String orgname;
    
    /** 机构备注 */
    private String note;
    
    /** 机构状态 */
    private String orgstatus;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /***查询条件***/
    /** 机构名称*/
    private String queryorgname;
    /** 父机构ID*/
    private String queryIsparentorgid;
    /** 支行机构ID*/
    private String queryparentorgid;
    /** 机构状态*/
    private String queryorgstatus;
    /** 机构管理-查询下拉列表*/
    private List<CloudplatformorginationEntity> cloudplatformorginationList;
    /**
     * 获取机构ID
     * 
     * @return 机构ID
     */
    public String getId() {
        return this.id;
    }
     
    public String getQueryIsparentorgid() {
		return queryIsparentorgid;
	}

	public void setQueryIsparentorgid(String queryIsparentorgid) {
		this.queryIsparentorgid = queryIsparentorgid;
	}

	/**
     * 设置机构ID
     * 
     * @param id
     *          机构ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取父级机构ID
     * 
     * @return 父级机构ID
     */
    public String getParentorgid() {
        return this.parentorgid;
    }
     
    /**
     * 设置父级机构ID
     * 
     * @param parentorgid
     *          父级机构ID
     */
    public void setParentorgid(String parentorgid) {
        this.parentorgid = parentorgid;
    }
    
    /**
     * 获取机构名称
     * 
     * @return 机构名称
     */
    public String getOrgname() {
        return this.orgname;
    }
     
    /**
     * 设置机构名称
     * 
     * @param orgname
     *          机构名称
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
    
    /**
     * 获取机构备注
     * 
     * @return 机构备注
     */
    public String getNote() {
        return this.note;
    }
     
    /**
     * 设置机构备注
     * 
     * @param note
     *          机构备注
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 获取机构状态
     * 
     * @return 机构状态
     */
    public String getOrgstatus() {
        return this.orgstatus;
    }
     
    /**
     * 设置机构状态
     * 
     * @param orgstatus
     *          机构状态
     */
    public void setOrgstatus(String orgstatus) {
        this.orgstatus = orgstatus;
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

	public String getQueryorgname() {
		return queryorgname;
	}

	public String getBranchid() {
		return branchid;
	}

	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}

	public void setQueryorgname(String queryorgname) {
		this.queryorgname = queryorgname;
	}

	public String getQueryparentorgid() {
		return queryparentorgid;
	}

	public void setQueryparentorgid(String queryparentorgid) {
		this.queryparentorgid = queryparentorgid;
	}

	public String getQueryorgstatus() {
		return queryorgstatus;
	}

	public void setQueryorgstatus(String queryorgstatus) {
		this.queryorgstatus = queryorgstatus;
	}

	public List<CloudplatformorginationEntity> getCloudplatformorginationList() {
		return cloudplatformorginationList;
	}

	public void setCloudplatformorginationList(List<CloudplatformorginationEntity> cloudplatformorginationList) {
		this.cloudplatformorginationList = cloudplatformorginationList;
	}

	public String getParentorgname() {
		return parentorgname;
	}

	public void setParentorgname(String parentorgname) {
		this.parentorgname = parentorgname;
	}

	@Override
	public String toString() {
		return "CloudplatformorginationModel [id=" + id + ", parentorgid=" + parentorgid + ", branchid=" + branchid
				+ ", parentorgname=" + parentorgname + ", orgname=" + orgname + ", note=" + note + ", orgstatus="
				+ orgstatus + ", createtime=" + createtime + ", updatetime=" + updatetime + ", queryorgname="
				+ queryorgname + ", queryIsparentorgid=" + queryIsparentorgid + ", queryparentorgid=" + queryparentorgid
				+ ", queryorgstatus=" + queryorgstatus + ", cloudplatformorginationList=" + cloudplatformorginationList
				+ "]";
	}
}
