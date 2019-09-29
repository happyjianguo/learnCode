package cn.com.jansh.entity.component;

import java.math.BigDecimal;

public class CfolTradeQueryEntity {

	/** 交易订单号 */
	private String orderid;
	/** 用户id */
	private String userid;
	/** 机构id */
	private String orgid;

	/** 单笔充值id */
	private String rechargeid;
	/** 交易时间 */
	private String txntime;
	/** 交易状态 */
	private String status;
	/** 流水号 */
	private String queryid;
	/** 支付方式 */
	private String paytype;
	/** 机构码 */
	private String code;
	/** 交易金额 */
	private BigDecimal price;
	
	private BigDecimal currentmoney;
	/** 加签串 */
	private String sign;
	/** 消费交易应答码 */
	private String origrespcode;
	/** 查询交易应答码 */
	private String respcode;
	/** 系统跟踪号 */
	private String traceno;
	/** 更新时间-交易完成回调时间 */
	private String updatetime;
	/** 更新时判断状态 */
	private String status1;
	/** 交易查询次数 */
	private String querynum;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
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

	public String getRechargeid() {
		return rechargeid;
	}

	public void setRechargeid(String rechargeid) {
		this.rechargeid = rechargeid;
	}

	public String getQueryid() {
		return queryid;
	}

	public void setQueryid(String queryid) {
		this.queryid = queryid;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getCurrentmoney() {
		return currentmoney;
	}

	public void setCurrentmoney(BigDecimal currentmoney) {
		this.currentmoney = currentmoney;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrigrespcode() {
		return origrespcode;
	}

	public void setOrigrespcode(String origrespcode) {
		this.origrespcode = origrespcode;
	}

	public String getRespcode() {
		return respcode;
	}

	public void setRespcode(String respcode) {
		this.respcode = respcode;
	}

	public String getTraceno() {
		return traceno;
	}

	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getQuerynum() {
		return querynum;
	}

	public void setQuerynum(String querynum) {
		this.querynum = querynum;
	}
	@Override
	public String toString() {
		return "CfolTradeQueryEntity [orderid=" + orderid + ", userid=" + userid + ", orgid=" + orgid + ", rechargeid="
				+ rechargeid + ", txntime=" + txntime + ", status=" + status + ", queryid=" + queryid + ", paytype="
				+ paytype + ", code=" + code + ", price=" + price + ", currentmoney=" + currentmoney + ", sign=" + sign
				+ ", origrespcode=" + origrespcode + ", respcode=" + respcode + ", traceno=" + traceno + ", updatetime="
				+ updatetime + ", status1=" + status1 + ", querynum=" + querynum + "]";
	}
}
