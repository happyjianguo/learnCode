package cn.com.jansh.model.wsfdn;

public class RechargeOrder {
	
	private String sysid;
	
	private String phone;
	
	private String transno;
	
	private String ispno;
	
	private String isptype;
	
	private String facevalue;

	private String province;
	
	//接入者订单号，对应充值表id
	private String cporder;
	
	public String getCporder() {
		return cporder;
	}

	public void setCporder(String cporder) {
		this.cporder = cporder;
	}

	public String getIsptype() {
		return isptype;
	}

	public void setIsptype(String isptype) {
		this.isptype = isptype;
	}

	public String getFacevalue() {
		return facevalue;
	}

	public void setFacevalue(String facevalue) {
		this.facevalue = facevalue;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

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

	
	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	@Override
	public String toString() {
		return "LiuliangOrder [sysid=" + sysid + ", phone=" + phone + ", transno=" + transno + ", ispno=" + ispno
				+ ", isptype=" + isptype + ", facevalue=" + facevalue + ", province=" + province + "]";
	}
}
