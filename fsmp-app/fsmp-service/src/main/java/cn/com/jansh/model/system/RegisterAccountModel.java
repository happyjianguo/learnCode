/**
 * RegisterAccountModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月20日
 */
package cn.com.jansh.model.system;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户注册model
 * @author Mr.wong
 * @version 1.0
 */
public class RegisterAccountModel {

	private String username;//用户名
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9_]+$")
	@Size(max=30,min=6)
	private String passwd;//密码
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9_]+$")
	@Size(max=30,min=6)
	private String passwdagain;//确认密码
	
	private String cname;//姓名
	@NotBlank
	@Size(max=2,min=1)
	private String mf;//性别
	@NotBlank
	@Pattern(regexp="^1[0-9]{10}$")
	private String phoneno;//手机号
	
	@Pattern(regexp="^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$")
	private String email; // 邮箱
	@NotBlank
	@Size(max=6,min=4)
	private String validcode;//验证码
	
	/**
	 * 
	 */
	public RegisterAccountModel() {
	}

	public RegisterAccountModel(String username, String passwd, String cname, String mf, String phoneno, String email,
			String validcode) {
		super();
		this.username = username;
		this.passwd = passwd;
		this.cname = cname;
		this.mf = mf;
		this.phoneno = phoneno;
		this.email = email;
		this.validcode = validcode;
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


	
	
	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
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

	
	public String getValidcode() {
		return validcode;
	}

	public void setValidcode(String validcode) {
		this.validcode = validcode;
	}
	
	public String getPasswdagain() {
		return passwdagain;
	}

	public void setPasswdagain(String passwdagain) {
		this.passwdagain = passwdagain;
	}

	@Override
	public String toString() {
		return "RegisterAccountModel [username=" + username + ", passwd=" + passwd + ", cname=" + cname + ", mf=" + mf
				+ ", phoneno=" + phoneno + ", email=" + email + ", validcode=" + validcode + "]";
	}

	
}
