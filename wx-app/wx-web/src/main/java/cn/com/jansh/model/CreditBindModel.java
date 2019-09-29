package cn.com.jansh.model;

import org.hibernate.validator.constraints.NotBlank;

public class CreditBindModel {
	
	private final String cardType = "2";
	
	private String openid;
	@NotBlank(message = "{E10006}")
	private String creusername;
	@NotBlank(message = "{E10008}")
	private String creidno;
	@NotBlank(message = "{E10009}")
	private String crecardno;
	@NotBlank(message = "{E10010}")
	private String cremobile;
	private String crenickname;
	private String cremsgcode;
	private String crenoticestatus;
	private String crecaptcha;
	
	public String getCardType() {
		return cardType;
	}


	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getCreusername() {
		return creusername;
	}

	public void setCreusername(String creusername) {
		this.creusername = creusername;
	}

	public String getCreidno() {
		return creidno;
	}

	public void setCreidno(String creidno) {
		this.creidno = creidno;
	}

	public String getCrecardno() {
		return crecardno;
	}

	public void setCrecardno(String crecardno) {
		this.crecardno = crecardno;
	}

	public String getCremobile() {
		return cremobile;
	}

	public void setCremobile(String cremobile) {
		this.cremobile = cremobile;
	}

	public String getCrenickname() {
		return crenickname;
	}

	public void setCrenickname(String crenickname) {
		this.crenickname = crenickname;
	}

	public String getCremsgcode() {
		return cremsgcode;
	}

	public void setCremsgcode(String cremsgcode) {
		this.cremsgcode = cremsgcode;
	}

	public String getCrenoticestatus() {
		return crenoticestatus;
	}

	public void setCrenoticestatus(String crenoticestatus) {
		this.crenoticestatus = crenoticestatus;
	}

	public String getCrecaptcha() {
		return crecaptcha;
	}

	public void setCrecaptcha(String crecaptcha) {
		this.crecaptcha = crecaptcha;
	}
	
	
}
