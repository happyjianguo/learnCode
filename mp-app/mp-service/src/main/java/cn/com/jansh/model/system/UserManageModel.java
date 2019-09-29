/**
 * UserManageModel.java
 * 2016年2月23日 下午1:20:35
 * 
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 */
package cn.com.jansh.model.system;

import java.util.Arrays;
import java.util.List;

import com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.entity.system.IMRole;

/**
 * @author nie
 *
 */
public class UserManageModel {
	
	//查询条件
	private String userid;

	private String qusername;
	
	private String qcname;
	
	private String qroleid;
	
	private String username;
	
	private String cname;
	
	private String roleid;
	
	private String phoneno;
	
	private String email;
	
	private String[] roleids;
	
	private List<IMUser> userList;
	
	private List<IMRole> roleList;
	
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

	@Override
	public String toString() {
		return "UserManageModel [userid=" + userid + ", qusername=" + qusername + ", qcname=" + qcname + ", qroleid="
				+ qroleid + ", username=" + username + ", cname=" + cname + ", roleid=" + roleid + ", phoneno="
				+ phoneno + ", email=" + email + ", roleids=" + Arrays.toString(roleids) + ", userList=" + userList
				+ ", roleList=" + roleList + "]";
	}

}
