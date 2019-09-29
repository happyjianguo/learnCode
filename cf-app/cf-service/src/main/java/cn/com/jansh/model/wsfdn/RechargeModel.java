package cn.com.jansh.model.wsfdn;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class RechargeModel {
	
	@NotBlank
	@Length(min=1, max=20)
	private String phone;
	@NotBlank
	@Length(min=1, max=20)
	private String phone1;
	
	@NotBlank
	@Length(min=0, max=10)
	private String transno;
	private String ispno;
	@NotBlank
	@Length(min=1, max=20)
	private String productno;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getTransno() {
		return transno;
	}
	public void setTransno(String transno) {
		this.transno = transno;
	}
	public String getIspno() {
		return ispno;
	}
	public void setIspno(String ispno) {
		this.ispno = ispno;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	@Override
	public String toString() {
		return "RechargeModel [phone=" + phone + ", phone1=" + phone1 + ", transno=" + transno + ", ispno=" + ispno
				+ ", productno=" + productno + "]";
	}
}
