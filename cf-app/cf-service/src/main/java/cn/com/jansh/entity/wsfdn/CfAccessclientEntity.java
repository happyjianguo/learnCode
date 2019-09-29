package cn.com.jansh.entity.wsfdn;
/******************************************************************
** 标    题：CfAccessclientEntity
** 创 建 者：longlong
** 创建日期：2016-04-21 13:14:17
******************************************************************/

public class CfAccessclientEntity {

	/** 接入者ID */
	private String id;

	/** 接入者名称 */
	private String acname;

	/** 客户id */
	private String cid;
	
	private String cname; 

	/** 联系人姓名 */
	private String mname;

	/** 联系人电话 */
	private String phone;

	/** 联系人邮箱 */
	private String email;

	/** 秘钥 */
	private String ackey;

	/** 开始时间 */
	private String begintime;

	/** 结束时间 */
	private String endtime;

	/** 预算 */
	private String budget;

	/** 状态 */
	private String status;

	/** 累计消费 */
	private String cumulative;

	/** 回掉地址*/
	private String callbackurl;

	private String qid;
	private String qcid;
	private String qstatus;
	
	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取接入者ID
	 * 
	 * @return 接入者ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置接入者ID
	 * 
	 * @param id
	 *            接入者ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取接入者名称
	 * 
	 * @return 接入者名称
	 */
	public String getAcname() {
		return this.acname;
	}

	/**
	 * 设置接入者名称
	 * 
	 * @param acname
	 *            接入者名称
	 */
	public void setAcname(String acname) {
		this.acname = acname;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getCid() {
		return this.cid;
	}

	/**
	 * 设置
	 * 
	 * @param cid
	 * 
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	/**
	 * 获取联系人姓名
	 * 
	 * @return 联系人姓名
	 */
	public String getMname() {
		return this.mname;
	}

	/**
	 * 设置联系人姓名
	 * 
	 * @param mname
	 *            联系人姓名
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

	/**
	 * 获取秘钥
	 * 
	 * @return 秘钥
	 */
	public String getAckey() {
		return this.ackey;
	}

	/**
	 * 设置秘钥
	 * 
	 * @param ackey
	 *            秘钥
	 */
	public void setAckey(String ackey) {
		this.ackey = ackey;
	}

	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */
	public String getBegintime() {
		return this.begintime;
	}

	/**
	 * 设置开始时间
	 * 
	 * @param begintime
	 *            开始时间
	 */
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */
	public String getEndtime() {
		return this.endtime;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param endtime
	 *            结束时间
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * 获取预算
	 * 
	 * @return 预算
	 */
	public String getBudget() {
		return this.budget;
	}

	/**
	 * 设置预算
	 * 
	 * @param budget
	 *            预算
	 */
	public void setBudget(String budget) {
		this.budget = budget;
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
	 *            状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取累计消费
	 * 
	 * @return
	 */
	public String getCumulative() {
		return this.cumulative;
	}

	/**
	 * 设置累计消费
	 * 
	 * @param cumulative
	 * 
	 */
	public void setCumulative(String cumulative) {
		this.cumulative = cumulative;
	}
	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQcid() {
		return qcid;
	}

	public void setQcid(String qcid) {
		this.qcid = qcid;
	}

	public String getQstatus() {
		return qstatus;
	}

	public void setQstatus(String qstatus) {
		this.qstatus = qstatus;
	}

	@Override
	public String toString() {
		return "CfAccessclientEntity [id=" + id + ", acname=" + acname + ", cid=" + cid + ", cname=" + cname
				+ ", mname=" + mname + ", phone=" + phone + ", email=" + email + ", ackey=" + ackey + ", begintime="
				+ begintime + ", endtime=" + endtime + ", budget=" + budget + ", status=" + status + ", cumulative="
				+ cumulative + ", callbackurl=" + callbackurl + ", qid=" + qid + ", qcid=" + qcid + ", qstatus="
				+ qstatus + "]";
	}
}
