/**
 * PayResponse.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月26日
 */
package cn.com.jansh.model.component;

/**
 * 银联支付应答参数
 * @author Mr.wong
 * @version 1.0
 */
public class PayResponseModel {

	//订单号
	private String orderId;
	//交易查询流水号
	private String queryId;
	//系统跟踪号
	private String traceNo;
	//应答码
	private String respCode;
	//应答信息
	private String respMsg;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	@Override
	public String toString() {
		return "PayResponse [orderId=" + orderId + ", queryId=" + queryId + ", traceNo=" + traceNo + ", respCode="
				+ respCode + ", respMsg=" + respMsg + "]";
	}
}
