/**
 * PassWordModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月9日
 */
package cn.com.jansh.model.component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改密码model
 * @author Mr.wong
 * @version 1.0
 */
public class PassWordModel {

	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9_]+$")
	@Size(max=30,min=6)
	private String opwd;
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9_]+$")
	@Size(max=30,min=6)
	private String pwd1;
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9_]+$")
	@Size(max=30,min=6)
	private String pwd2;
	public String getOpwd() {
		return opwd;
	}
	public void setOpwd(String opwd) {
		this.opwd = opwd;
	}
	public String getPwd1() {
		return pwd1;
	}
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}
	public String getPwd2() {
		return pwd2;
	}
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
}
