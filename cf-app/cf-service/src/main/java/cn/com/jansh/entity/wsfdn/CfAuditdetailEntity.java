package cn.com.jansh.entity.wsfdn;

public class CfAuditdetailEntity {
	 /** 版本号 */
    
    /** 审批id */
    private String id;
    
    /** 状态 */
    private String detail;
    
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
    
    /**
     * 获取状态
     * 
     * @return 状态
     */
    public String getDetail() {
        return this.detail;
    }
     
    /**
     * 设置状态
     * 
     * @param detail
     *          状态
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
