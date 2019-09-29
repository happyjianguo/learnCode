package cn.com.jansh.model.component;

/**
 * 用户充值记录model
 * @author Mr.wong
 * @version 1.0
 */
public class ShowRechargeModel {

	/** 交易订单号 */
	private String orderid;
	/** 用户名 */
	private String username;
	/** 用户昵称 */
	private String cname;
	/** 交易时间 */
	private String txntime;
	/** 交易状态 */
	private String status;
	/** 交易金额 */
	private String price;
	/** 购买平台币数量 */
	private String currentmoney;

	public ShowRechargeModel() {
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTxntime() {
		return txntime;
	}

	public void setTxntime(String txntime) {
		this.txntime = txntime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrentmoney() {
		return currentmoney;
	}

	public void setCurrentmoney(String currentmoney) {
		this.currentmoney = currentmoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
