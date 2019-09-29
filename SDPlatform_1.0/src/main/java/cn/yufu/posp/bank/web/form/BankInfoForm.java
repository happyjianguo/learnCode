package cn.yufu.posp.bank.web.form;

import cn.yufu.core.web.form.BaseForm;

public class BankInfoForm extends BaseForm{
	
	private String bankId;
	private String hostId;
	private String bankName;
	private String bankType;
	private String admBankId;
	private String admHostId;
	private String address;
	private String postCode;
	private String email;
	private String fax;
	private String telex;
	private String authTel;
	private String settleTel;
	private String gmName;
	private String gmTel;
	private String mngName1;
	private String mngTel1;
	private String mngName2;
	private String mngTel2;
	private String mngName3;
	private String mngTel3;
	private String authMngName;
	private String authMngTel;
	private String settMngName;
	private String settMngTel;
	private String cardMngName;
	private String cardMngTel;
	private String nasMngName;
	private String nasMngTel;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	
	//银行类型名称
	private String bankTypeName;
	
	//查询条件
	private String queryBankId;
	private String queryHostId;
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getAdmBankId() {
		return admBankId;
	}
	public void setAdmBankId(String admBankId) {
		this.admBankId = admBankId;
	}
	public String getAdmHostId() {
		return admHostId;
	}
	public void setAdmHostId(String admHostId) {
		this.admHostId = admHostId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTelex() {
		return telex;
	}
	public void setTelex(String telex) {
		this.telex = telex;
	}
	public String getAuthTel() {
		return authTel;
	}
	public void setAuthTel(String authTel) {
		this.authTel = authTel;
	}
	public String getSettleTel() {
		return settleTel;
	}
	public void setSettleTel(String settleTel) {
		this.settleTel = settleTel;
	}
	public String getGmName() {
		return gmName;
	}
	public void setGmName(String gmName) {
		this.gmName = gmName;
	}
	public String getGmTel() {
		return gmTel;
	}
	public void setGmTel(String gmTel) {
		this.gmTel = gmTel;
	}
	public String getMngName1() {
		return mngName1;
	}
	public void setMngName1(String mngName1) {
		this.mngName1 = mngName1;
	}
	public String getMngTel1() {
		return mngTel1;
	}
	public void setMngTel1(String mngTel1) {
		this.mngTel1 = mngTel1;
	}
	public String getMngName2() {
		return mngName2;
	}
	public void setMngName2(String mngName2) {
		this.mngName2 = mngName2;
	}
	public String getMngTel2() {
		return mngTel2;
	}
	public void setMngTel2(String mngTel2) {
		this.mngTel2 = mngTel2;
	}
	public String getMngName3() {
		return mngName3;
	}
	public void setMngName3(String mngName3) {
		this.mngName3 = mngName3;
	}
	public String getMngTel3() {
		return mngTel3;
	}
	public void setMngTel3(String mngTel3) {
		this.mngTel3 = mngTel3;
	}
	public String getAuthMngName() {
		return authMngName;
	}
	public void setAuthMngName(String authMngName) {
		this.authMngName = authMngName;
	}
	public String getAuthMngTel() {
		return authMngTel;
	}
	public void setAuthMngTel(String authMngTel) {
		this.authMngTel = authMngTel;
	}
	public String getSettMngName() {
		return settMngName;
	}
	public void setSettMngName(String settMngName) {
		this.settMngName = settMngName;
	}
	public String getSettMngTel() {
		return settMngTel;
	}
	public void setSettMngTel(String settMngTel) {
		this.settMngTel = settMngTel;
	}
	public String getCardMngName() {
		return cardMngName;
	}
	public void setCardMngName(String cardMngName) {
		this.cardMngName = cardMngName;
	}
	public String getCardMngTel() {
		return cardMngTel;
	}
	public void setCardMngTel(String cardMngTel) {
		this.cardMngTel = cardMngTel;
	}
	public String getNasMngName() {
		return nasMngName;
	}
	public void setNasMngName(String nasMngName) {
		this.nasMngName = nasMngName;
	}
	public String getNasMngTel() {
		return nasMngTel;
	}
	public void setNasMngTel(String nasMngTel) {
		this.nasMngTel = nasMngTel;
	}
	public String getUpdateOper() {
		return updateOper;
	}
	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getBankTypeName() {
		return bankTypeName;
	}
	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}
	public String getQueryBankId() {
		return queryBankId;
	}
	public void setQueryBankId(String queryBankId) {
		this.queryBankId = queryBankId;
	}
	public String getQueryHostId() {
		return queryHostId;
	}
	public void setQueryHostId(String queryHostId) {
		this.queryHostId = queryHostId;
	}

	
	
}
