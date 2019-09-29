package cn.yufu.posp.sysparam.domain.model;


/**
 * TranModuleInf entity. @author MyEclipse Persistence Tools
 */

public class TranModuleInf implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6511585918411226955L;
	// Fields

	private String moduleId;
	private String tranType;
	private String voidTranType;
	private String voidOldTranType;
	private String msgId;
	private String serviceCode;
	private String typeCode;
	private String procCode;
	private String tranName;
	private String reqBitmap;
	private String respBitmap;
	private String revOrgProcCode;
	private String settFlag;
	private String writeLsFlag;
	private String checkMacFlag;
	private String flag;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getVoidTranType() {
		return voidTranType;
	}

	public void setVoidTranType(String voidTranType) {
		this.voidTranType = voidTranType;
	}

	public String getVoidOldTranType() {
		return voidOldTranType;
	}

	public void setVoidOldTranType(String voidOldTranType) {
		this.voidOldTranType = voidOldTranType;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getProcCode() {
		return procCode;
	}

	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}

	public String getTranName() {
		return tranName;
	}

	public void setTranName(String tranName) {
		this.tranName = tranName;
	}

	public String getReqBitmap() {
		return reqBitmap;
	}

	public void setReqBitmap(String reqBitmap) {
		this.reqBitmap = reqBitmap;
	}

	public String getRespBitmap() {
		return respBitmap;
	}

	public void setRespBitmap(String respBitmap) {
		this.respBitmap = respBitmap;
	}

	public String getRevOrgProcCode() {
		return revOrgProcCode;
	}

	public void setRevOrgProcCode(String revOrgProcCode) {
		this.revOrgProcCode = revOrgProcCode;
	}

	public String getSettFlag() {
		return settFlag;
	}

	public void setSettFlag(String settFlag) {
		this.settFlag = settFlag;
	}

	public String getWriteLsFlag() {
		return writeLsFlag;
	}

	public void setWriteLsFlag(String writeLsFlag) {
		this.writeLsFlag = writeLsFlag;
	}

	public String getCheckMacFlag() {
		return checkMacFlag;
	}

	public void setCheckMacFlag(String checkMacFlag) {
		this.checkMacFlag = checkMacFlag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}