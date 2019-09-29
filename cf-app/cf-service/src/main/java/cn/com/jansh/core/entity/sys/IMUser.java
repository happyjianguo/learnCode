package cn.com.jansh.core.entity.sys;

public class IMUser {
	
    private String userid;
    private String username;
    private String passwd;
    private String cname;
   	private String pwderrnum;
    private String status;
    private String updatetime;
    private String createtime;
    private String phoneno;
    private String email;
    
	private String ousername;
	private String rolename;
    private String[] roleid;
    private String sRoleId;
    //机构代码
    private String organizationCode;
  
   
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
    
    
    public String getsRoleId() {
		return sRoleId;
	}
	public void setsRoleId(String sRoleId) {
		this.sRoleId = sRoleId;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    public String getOusername() {
		return ousername;
	}
	public void setOusername(String ousername) {
		this.ousername = ousername;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String[] getRoleid() {
		return roleid;
	}
	public void setRoleid(String[] roleid) {
		this.roleid = roleid;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
}
