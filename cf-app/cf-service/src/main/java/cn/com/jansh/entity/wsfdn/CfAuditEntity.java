package cn.com.jansh.entity.wsfdn;

/**
 * 审批表
 * @author duanmuyn
 *
 */
public class CfAuditEntity {
	/** 版本号 */
    
    /** 审批id */
    private String id;
    
    /** 审批名称 */
    private String auname;
    
    /** 审批类型 */
    private String autype;
    
    /** 明细主键 */
    private String detailkey;
    
    /** 手机号 */
    private String createtime;
    
    /** 审批时间 */
    private String updatetime;
    
    /** 状态 */
    private String status;
    
    private String auditer;
    
    private String draftman;
    
    
    private String opinion;
    
    private String 	servicename;
    
    public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}

	public String getDraftman() {
		return draftman;
	}

	public void setDraftman(String draftman) {
		this.draftman = draftman;
	}

	/**
     * 获取审批id
     * 
     * @return 审批id
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置审批id
     * 
     * @param id
     *          审批id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    
    public String getAuname() {
		return auname;
	}

	public void setAuname(String auname) {
		this.auname = auname;
	}

	/**
     * 获取审批类型
     * 
     * @return 审批类型
     */
    public String getAutype() {
        return this.autype;
    }
     
    /**
     * 设置审批类型
     * 
     * @param autype
     *          审批类型
     */
    public void setAutype(String autype) {
        this.autype = autype;
    }
    
    /**
     * 获取明细主键
     * 
     * @return 明细主键
     */
    public String getDetailkey() {
        return this.detailkey;
    }
     
    /**
     * 设置明细主键
     * 
     * @param detailkey
     *          明细主键
     */
    public void setDetailkey(String detailkey) {
        this.detailkey = detailkey;
    }
    
    /**
     * 获取手机号
     * 
     * @return 手机号
     */
    public String getCreatetime() {
        return this.createtime;
    }
     
    /**
     * 设置手机号
     * 
     * @param createtime
     *          手机号
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    /**
     * 获取审批时间
     * 
     * @return 审批时间
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
     
    /**
     * 设置审批时间
     * 
     * @param updatetime
     *          审批时间
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    
    /**
     * 获取状态
     * 
     * @return 状态
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态
     * 
     * @param status
     *          状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
