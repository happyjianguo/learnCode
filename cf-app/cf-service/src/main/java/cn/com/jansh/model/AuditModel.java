package cn.com.jansh.model;

public class AuditModel {
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
    
    /** 审批人*/
    private String auditer;
    
    /** 起草人*/
    private String draftman;
    
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

	private String opinion;
    
    private String 	servicename;
    
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

	
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getAuname() {
		return auname;
	}

	public void setAuname(String auname) {
		this.auname = auname;
	}

	public String getAutype() {
		return autype;
	}

	public void setAutype(String autype) {
		this.autype = autype;
	}

	public String getDetailkey() {
		return detailkey;
	}

	public void setDetailkey(String detailkey) {
		this.detailkey = detailkey;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
