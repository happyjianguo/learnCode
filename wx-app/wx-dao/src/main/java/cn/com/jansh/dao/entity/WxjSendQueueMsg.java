package cn.com.jansh.dao.entity;

public class WxjSendQueueMsg {

	private Integer autoId;
	private String docId;
	private String msgCd;
	private String insertTime;
	private String processFlag;
	private String msgType;
	public WxjSendQueueMsg() {
		super();
	}
	public WxjSendQueueMsg( String docId, String msgCd, String insertTime, String processFlag,
			String msgType) {
		super();
		this.docId = docId;
		this.msgCd = msgCd;
		this.insertTime = insertTime;
		this.processFlag = processFlag;
		this.msgType = msgType;
	}
	public Integer getAutoId() {
		return autoId;
	}
	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getMsgCd() {
		return msgCd;
	}
	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getProcessFlag() {
		return processFlag;
	}
	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	@Override
	public String toString() {
		return "WxjSendQueueMsg [autoId=" + autoId + ", docId=" + docId + ", msgCd=" + msgCd + ", insertTime="
				+ insertTime + ", processFlag=" + processFlag + ", msgType=" + msgType + "]";
	}
}
