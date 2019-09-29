package cn.com.jansh.model;

import org.hibernate.validator.constraints.NotBlank;

public class CardBindModel {

	private final String cardType = "1";
	
	private String openid;
	@NotBlank(message = "{E10006}")
	private String username;
	@NotBlank(message = "{E10008}")
	private String idno;
	@NotBlank(message = "{E10009}")
	private String cardno;
	@NotBlank(message = "{E10010}")
	private String mobile;
	private String nickname;
	private String msgcode;
	private String noticestatus;
	private String captcha;
	
	public String getCardType() {
		return cardType;
	}

	public String getNoticestatus() {
		return noticestatus;
	}
	public void setNoticestatus(String noticestatus) {
		this.noticestatus = noticestatus;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMsgcode() {
		return msgcode;
	}
	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String toString(){
		return "openid:"+openid + ",username:"+username+",idno:"+idno+",cardno:"+cardno+",mobile:"+mobile+".noticestatus:"+noticestatus;
	}
}
