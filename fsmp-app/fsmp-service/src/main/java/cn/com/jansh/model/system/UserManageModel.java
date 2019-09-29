/**
 * UserManageModel.java
 * 2016年2月23日 下午1:20:35
 * 
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 */
package cn.com.jansh.model.system;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.jansh.core.entity.sys.IMUser;

import cn.com.jansh.entity.system.IMRole;

/**
 * @author nie
 *
 */
public class UserManageModel {
	
	//查询条件
	@NotBlank
	@Size(max=20)
	private String userid;
	
	private String qusername;
	
	private String mf;
	
	private String qcname;
	
	@NotBlank
	@Size(max=6,min=4)
	private String validecode;
	
	private String qroleid;
	
	private String username;
	@NotBlank
	@Size(max=30,min=2)
	private String cname;
	
	private String roleid;
	@NotBlank
	@Pattern(regexp="^1[0-9]{10}$")
	private String phoneno;
	@NotBlank
	@Pattern(regexp="^([A-Za-z0-9]-*[.]*)+@([A-Za-z0-9]-?)+([.][A-Za-z0-9]{2,})+$")
	private String email;
	
	private String[] roleids;
	
	private List<IMUser> userList;
	
	private List<IMRole> roleList;
	
	private MultipartFile myfiles;
	
	private MultipartFile mylicense;
	
	public String[] getRoleids() {
		return roleids;
	}

	public void setRoleids(String[] roleids) {
		this.roleids = roleids;
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

	public List<IMUser> getUserList() {
		return userList;
	}

	public void setUserList(List<IMUser> userList) {
		this.userList = userList;
	}

	public String getUsername() {
		return username;
	}

	public String getCname() {
		return cname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public List<IMRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<IMRole> roleList) {
		this.roleList = roleList;
	}

	public String getQusername() {
		return qusername;
	}

	public String getQcname() {
		return qcname;
	}

	public String getQroleid() {
		return qroleid;
	}

	public void setQusername(String qusername) {
		this.qusername = qusername;
	}

	public void setQcname(String qcname) {
		this.qcname = qcname;
	}

	public void setQroleid(String qroleid) {
		this.qroleid = qroleid;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public MultipartFile getMyfiles() {
		return myfiles;
	}

	public void setMyfiles(MultipartFile myfiles) {
		this.myfiles = myfiles;
	}

	public MultipartFile getMylicense() {
		return mylicense;
	}

	public void setMylicense(MultipartFile mylicense) {
		this.mylicense = mylicense;
	}
	
	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}
	
	public String getValidecode() {
		return validecode;
	}

	public void setValidecode(String validecode) {
		this.validecode = validecode;
	}

	@Override
	public String toString() {
		return "UserManageModel [userid=" + userid + ", qusername=" + qusername + ", qcname=" + qcname + ", qroleid="
				+ qroleid + ", username=" + username + ", cname=" + cname + ", roleid=" + roleid + ", phoneno="
				+ phoneno + ", email=" + email + ", roleids=" + Arrays.toString(roleids) + ", userList=" + userList
				+ ", roleList=" + roleList + "]";
	}

}
