package cn.com.jansh.model;

import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.NotBlank;

public class AcctDetailModel {
	
	@NotBlank(message = "{E10015}")
	private String startDate;//开始日期
	@NotBlank(message = "{E10016}")
	private String endDate;//结束日期
	
	private String openid;
	private Map<String, String> cardnos;
	private String cardno;
	
	private List<Map<String, String>> acctDetailList;
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public Map<String, String> getCardnos() {
		return cardnos;
	}
	public void setCardnos(Map<String, String> cardnos) {
		this.cardnos = cardnos;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<Map<String, String>> getAcctDetailList() {
		return acctDetailList;
	}
	public void setAcctDetailList(List<Map<String, String>> acctDetailList) {
		this.acctDetailList = acctDetailList;
	}
}
