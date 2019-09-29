/**
 * SendJsonEntity.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:yangfan 2015-8-14
 */
package cn.com.jansh.model;

/**
 * 发送到的微信JSON实体类 
 * @author yangfan
 * @version 1.0.0
 */
public class SendJsonModel {

    private String touser="";  //用户OPENID
    private String template_id="";  //模板ID
    private String url="";  //跳转地址
    private String topcolor="";  //公共部分颜色
    private String first="";  //信息标题
    private String firstcolor="";  //信息标题颜色
    private String remark="";  //信息备注
    private String remarkcolor="";  //信息备注颜色
    private String time="";  //交易时间
    private String timecolor="";  //交易时间颜色
    private String type="";  //交易类型
    private String typecolor="";  //交易类型颜色
    private String number="";  //交易金额
    private String numbercolor="";  //交易金额颜色
    private String balance="";  //卡内余额
    private String balancecolor="";  //卡内余额颜色
    private String TEMPLATE_TITLE="";  //模板标题
    private String TEMPLATE_NO="";  //模板编号
    private String TEMPLATE_DOCID="";  //模板编号业务ID
    private String CARDNUM="";  //银行卡号
    private String CARDNAME="";  //银行卡名称
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String templateId) {
		template_id = templateId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getFirstcolor() {
		return firstcolor;
	}
	public void setFirstcolor(String firstcolor) {
		this.firstcolor = firstcolor;
	}
	public String getRemarkcolor() {
		return remarkcolor;
	}
	public void setRemarkcolor(String remarkcolor) {
		this.remarkcolor = remarkcolor;
	}
	public String getTimecolor() {
		return timecolor;
	}
	public void setTimecolor(String timecolor) {
		this.timecolor = timecolor;
	}
	public String getTypecolor() {
		return typecolor;
	}
	public void setTypecolor(String typecolor) {
		this.typecolor = typecolor;
	}
	public String getNumbercolor() {
		return numbercolor;
	}
	public void setNumbercolor(String numbercolor) {
		this.numbercolor = numbercolor;
	}
	public String getBalancecolor() {
		return balancecolor;
	}
	public void setBalancecolor(String balancecolor) {
		this.balancecolor = balancecolor;
	}
	public String getTEMPLATE_TITLE() {
		return TEMPLATE_TITLE;
	}
	public void setTEMPLATE_TITLE(String tEMPLATETITLE) {
		TEMPLATE_TITLE = tEMPLATETITLE;
	}
	public String getTEMPLATE_NO() {
		return TEMPLATE_NO;
	}
	public void setTEMPLATE_NO(String tEMPLATENO) {
		TEMPLATE_NO = tEMPLATENO;
	}
	public String getTEMPLATE_DOCID() {
		return TEMPLATE_DOCID;
	}
	public void setTEMPLATE_DOCID(String tEMPLATEDOCID) {
		TEMPLATE_DOCID = tEMPLATEDOCID;
	}
	public String getCARDNUM() {
		return CARDNUM;
	}
	public void setCARDNUM(String cARDNUM) {
		CARDNUM = cARDNUM;
	}
	public String getCARDNAME() {
		return CARDNAME;
	}
	public void setCARDNAME(String cARDNAME) {
		CARDNAME = cARDNAME;
	}
    
}
