package cn.com.jansh.entity.wsfdn;

/**
 * 供应商
 * @author duanmuyn
 */
public class CfSupplierEntity {

	/** 供应商ID */
    private String id;
    
    /** 供应商名称 */
    private String sname;
    
    /** 联系人名称 */
    private String mname;
    
    /** 联系人电话 */
    private String phone;
    
    /** 联系人邮箱 */
    private String email;
    
    /**
     * 获取供应商ID
     * 
     * @return 供应商ID
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置供应商ID
     * 
     * @param id
     *          供应商ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取供应商名称
     * 
     * @return 供应商名称
     */
    public String getSname() {
        return this.sname;
    }
     
    /**
     * 设置供应商名称
     * 
     * @param sname
     *          供应商名称
     */
    public void setSname(String sname) {
        this.sname = sname;
    }
    
    /**
     * 获取联系人名称
     * 
     * @return 联系人名称
     */
    public String getMname() {
        return this.mname;
    }
     
    /**
     * 设置联系人名称
     * 
     * @param mname
     *          联系人名称
     */
    public void setMname(String mname) {
        this.mname = mname;
    }
    
    /**
     * 获取联系人电话
     * 
     * @return 联系人电话
     */
    public String getPhone() {
        return this.phone;
    }
     
    /**
     * 设置联系人电话
     * 
     * @param phone
     *          联系人电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * 获取联系人邮箱
     * 
     * @return 联系人邮箱
     */
    public String getEmail() {
        return this.email;
    }
     
    /**
     * 设置联系人邮箱
     * 
     * @param email
     *          联系人邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
	public String toString() {
		return "CfSupplierEntity [id=" + id + ", sname=" + sname + ", mname=" + mname + ", phone=" + phone + ", email="
				+ email + "]";
	}
}