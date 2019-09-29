/**
 * DocSet.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-15
 */
package cn.com.jansh.ebank.ife.ibank.entity;

import java.io.Serializable;

/**
 * 核心交易请求数据汇总
 * @author YangFan
 * @version 1.0
 */
public class DocSet implements Serializable {
	
	private static final long serialVersionUID = 2095749672581921899L;
	
	private String tallyId;//交易模板ID
	private String businessId;//业务流水ID
	private String msgCd;//业务编号
	private String hostSeqNo;//核心流水号
	private String hostCode;//主机返回代码
	private String hostMsg;//主机返回信息
	

	private String TranTyp;//交易类型
	private String SRFlg;//往来标志
	private String ChkIoFlg;//是否检查磁道
	private String ChkNmFlg;//是否检查户名
	private String ChkIDFlg;//是否检查证件标志
	private String DrAcc;//借方账号/卡号
	private String DrAccNm;//借方户名
	private String DrCertNo;//借方证件号码
	private String CrAcc;//贷方账号/卡号
	private String CrAccNm;//贷方户名
	private String CrCertNo;//贷方证件号码
	
	private String AccTyp;//账户类型
	private String AccNo;//账号/卡号
	private String AccNm;//户名
	private String PhoneNm;//手机号码
	
	private String BeginDate;//起始日期
	private String EndDate;//终止日期
	private String FindDate;//查询日期
	
	
	
	
	
	public String getTallyId() {
		return tallyId;
	}
	public void setTallyId(String tallyId) {
		this.tallyId = tallyId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getMsgCd() {
		return msgCd;
	}
	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}
	public String getHostSeqNo() {
		return hostSeqNo;
	}
	public void setHostSeqNo(String hostSeqNo) {
		this.hostSeqNo = hostSeqNo;
	}
	public String getHostCode() {
		return hostCode;
	}
	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}
	public String getHostMsg() {
		return hostMsg;
	}
	public void setHostMsg(String hostMsg) {
		this.hostMsg = hostMsg;
	}
	public String getTranTyp() {
		return TranTyp;
	}
	public void setTranTyp(String tranTyp) {
		TranTyp = tranTyp;
	}
	public String getSRFlg() {
		return SRFlg;
	}
	public void setSRFlg(String flg) {
		SRFlg = flg;
	}
	public String getChkIoFlg() {
		return ChkIoFlg;
	}
	public void setChkIoFlg(String chkIoFlg) {
		ChkIoFlg = chkIoFlg;
	}
	public String getChkNmFlg() {
		return ChkNmFlg;
	}
	public void setChkNmFlg(String chkNmFlg) {
		ChkNmFlg = chkNmFlg;
	}
	public String getChkIDFlg() {
		return ChkIDFlg;
	}
	public void setChkIDFlg(String chkIDFlg) {
		ChkIDFlg = chkIDFlg;
	}
	public String getDrAcc() {
		return DrAcc;
	}
	public void setDrAcc(String drAcc) {
		DrAcc = drAcc;
	}
	public String getDrAccNm() {
		return DrAccNm;
	}
	public void setDrAccNm(String drAccNm) {
		DrAccNm = drAccNm;
	}
	public String getDrCertNo() {
		return DrCertNo;
	}
	public void setDrCertNo(String drCertNo) {
		DrCertNo = drCertNo;
	}
	public String getCrAcc() {
		return CrAcc;
	}
	public void setCrAcc(String crAcc) {
		CrAcc = crAcc;
	}
	public String getCrAccNm() {
		return CrAccNm;
	}
	public void setCrAccNm(String crAccNm) {
		CrAccNm = crAccNm;
	}
	public String getCrCertNo() {
		return CrCertNo;
	}
	public void setCrCertNo(String crCertNo) {
		CrCertNo = crCertNo;
	}
	public String getAccTyp() {
		return AccTyp;
	}
	public void setAccTyp(String accTyp) {
		AccTyp = accTyp;
	}
	public String getAccNo() {
		return AccNo;
	}
	public void setAccNo(String accNo) {
		AccNo = accNo;
	}
	public String getAccNm() {
		return AccNm;
	}
	public void setAccNm(String accNm) {
		AccNm = accNm;
	}
	public String getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getFindDate() {
		return FindDate;
	}
	public void setFindDate(String findDate) {
		FindDate = findDate;
	}
	public String getPhoneNm() {
		return PhoneNm;
	}
	public void setPhoneNm(String phoneNm) {
		PhoneNm = phoneNm;
	}
	
}
