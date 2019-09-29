/**
 * IMUser.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月21日
 */
package cn.com.jansh.entity.system;

/**
 * 用户实体
 * @author Mr.wong
 * @version 1.0
 */
public class IMUserN {

	private String userid;//用户id
	
	private String orgid;//机构id
	
	private String username;//用户名
	
	private String passwd;//用户密码
	
	private String cname;//姓名
	
	private String mf;//性别
	
	private String pwderrnum;//密码错误次数
	
	private String phoneno;//电话号码
	
	private String email;//邮箱
	
	private String status;//状态
	
	private String createtime;//创建时间
	
	private String updatetime;//更新时间
	
	private String ousername ;//旧用户名
	
	private String[] roleid;
	
	private String rolename;
	
	private String sRoleId; 
	
	/**
	 * 
	 */
	public IMUserN() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPwderrnum() {
		return pwderrnum;
	}

	public void setPwderrnum(String pwderrnum) {
		this.pwderrnum = pwderrnum;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getOusername() {
		return ousername;
	}

	public void setOusername(String ousername) {
		this.ousername = ousername;
	}


	public String[] getRoleid() {
		return roleid;
	}

	public void setRoleid(String[] roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getsRoleId() {
		return sRoleId;
	}

	public void setsRoleId(String sRoleId) {
		this.sRoleId = sRoleId;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}
	
}
