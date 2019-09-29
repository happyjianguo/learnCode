package cn.com.jansh.entity.wsfdn;
/******************************************************************
** 标    题：CfAccessclientEntity
** 创 建 者：bianj
** 创建日期：2016-04-20 13:14:17
** 描    述：客户表
 * @author gll
******************************************************************/
public class CfCustomerEntity {

	/** 客户ID */
    private String id;
    
    /** 客户名称 */
    private String cname;
    
    /** 联系人名称 */
    private String mname;
    
    /** 联系人电话 */
    private String phone;
    
    /** 联系人邮箱 */
    private String email;
    private String qid;
    private String qmname;
    /**
     * 获取客户ID
     * 
     * @return 客户ID
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置客户ID
     * 
     * @param id
     *          客户ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取客户名称
     * 
     * @return 客户名称
     */
    public String getCname() {
        return this.cname;
    }
     
    /**
     * 设置客户名称
     * 
     * @param cname
     *          客户名称
     */
    public void setCname(String cname) {
        this.cname = cname;
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
    
    public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQmname() {
		return qmname;
	}

	public void setQmname(String qmname) {
		this.qmname = qmname;
	}

	@Override
	public String toString() {
		return "CfCustomerEntity [id=" + id + ", cname=" + cname + ", mname=" + mname + ", phone=" + phone + ", email="
				+ email + ", qid=" + qid + ", qmname=" + qmname + "]";
	}
}