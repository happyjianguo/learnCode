package cn.com.jansh.model;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfCustomerEntity;

public class CustomerModel {

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
	/**页面客户ID */
	private String qid;
	private String qmname;
	/**客户列表-给查询条件下拉菜单用*/
	private List<CfCustomerEntity> customerlistselect;	

	/** 客户列表  */
	private List<CfCustomerEntity> customerlist;

	public List<CfCustomerEntity> getCustomerlist() {
		return customerlist;
	}

	public void setCustomerlist(List<CfCustomerEntity> customerlist) {
		this.customerlist = customerlist;
	}

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
	 *            客户ID
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
	 *            客户名称
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
	 *            联系人名称
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
	 *            联系人电话
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
	 *            联系人邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public List<CfCustomerEntity> getCustomerlistselect() {
		return customerlistselect;
	}

	public void setCustomerlistselect(List<CfCustomerEntity> customerlistselect) {
		this.customerlistselect = customerlistselect;
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
		return "CustomerModel [id=" + id + ", cname=" + cname + ", mname=" + mname + ", phone=" + phone + ", email="
				+ email + ", qid=" + qid + ", qmname=" + qmname + ", customerlistselect="
				+ customerlistselect + ", customerlist=" + customerlist + "]";
	}

}
