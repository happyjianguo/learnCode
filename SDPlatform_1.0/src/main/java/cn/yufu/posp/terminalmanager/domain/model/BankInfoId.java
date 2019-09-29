package cn.yufu.posp.terminalmanager.domain.model;

/**
 * BankInfoId entity. @author MyEclipse Persistence Tools
 */

public class BankInfoId implements java.io.Serializable {

	// Fields

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

	// Constructors

	/** default constructor */
	public BankInfoId() {
	}

	/** minimal constructor */
	public BankInfoId(String bankId, String hostId, String bankName,
			String admBankId) {
		this.bankId = bankId;
		this.hostId = hostId;
		this.bankName = bankName;
		this.admBankId = admBankId;
	}

	/** full constructor */
	public BankInfoId(String bankId, String hostId, String bankName,
			String bankType, String admBankId, String admHostId,
			String address, String postCode, String email, String fax,
			String telex, String authTel, String settleTel, String gmName,
			String gmTel, String mngName1, String mngTel1, String mngName2,
			String mngTel2, String mngName3, String mngTel3,
			String authMngName, String authMngTel, String settMngName,
			String settMngTel, String cardMngName, String cardMngTel,
			String nasMngName, String nasMngTel, String updateOper,
			String updateDate, String updateTime) {
		this.bankId = bankId;
		this.hostId = hostId;
		this.bankName = bankName;
		this.bankType = bankType;
		this.admBankId = admBankId;
		this.admHostId = admHostId;
		this.address = address;
		this.postCode = postCode;
		this.email = email;
		this.fax = fax;
		this.telex = telex;
		this.authTel = authTel;
		this.settleTel = settleTel;
		this.gmName = gmName;
		this.gmTel = gmTel;
		this.mngName1 = mngName1;
		this.mngTel1 = mngTel1;
		this.mngName2 = mngName2;
		this.mngTel2 = mngTel2;
		this.mngName3 = mngName3;
		this.mngTel3 = mngTel3;
		this.authMngName = authMngName;
		this.authMngTel = authMngTel;
		this.settMngName = settMngName;
		this.settMngTel = settMngTel;
		this.cardMngName = cardMngName;
		this.cardMngTel = cardMngTel;
		this.nasMngName = nasMngName;
		this.nasMngTel = nasMngTel;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getHostId() {
		return this.hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getAdmBankId() {
		return this.admBankId;
	}

	public void setAdmBankId(String admBankId) {
		this.admBankId = admBankId;
	}

	public String getAdmHostId() {
		return this.admHostId;
	}

	public void setAdmHostId(String admHostId) {
		this.admHostId = admHostId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelex() {
		return this.telex;
	}

	public void setTelex(String telex) {
		this.telex = telex;
	}

	public String getAuthTel() {
		return this.authTel;
	}

	public void setAuthTel(String authTel) {
		this.authTel = authTel;
	}

	public String getSettleTel() {
		return this.settleTel;
	}

	public void setSettleTel(String settleTel) {
		this.settleTel = settleTel;
	}

	public String getGmName() {
		return this.gmName;
	}

	public void setGmName(String gmName) {
		this.gmName = gmName;
	}

	public String getGmTel() {
		return this.gmTel;
	}

	public void setGmTel(String gmTel) {
		this.gmTel = gmTel;
	}

	public String getMngName1() {
		return this.mngName1;
	}

	public void setMngName1(String mngName1) {
		this.mngName1 = mngName1;
	}

	public String getMngTel1() {
		return this.mngTel1;
	}

	public void setMngTel1(String mngTel1) {
		this.mngTel1 = mngTel1;
	}

	public String getMngName2() {
		return this.mngName2;
	}

	public void setMngName2(String mngName2) {
		this.mngName2 = mngName2;
	}

	public String getMngTel2() {
		return this.mngTel2;
	}

	public void setMngTel2(String mngTel2) {
		this.mngTel2 = mngTel2;
	}

	public String getMngName3() {
		return this.mngName3;
	}

	public void setMngName3(String mngName3) {
		this.mngName3 = mngName3;
	}

	public String getMngTel3() {
		return this.mngTel3;
	}

	public void setMngTel3(String mngTel3) {
		this.mngTel3 = mngTel3;
	}

	public String getAuthMngName() {
		return this.authMngName;
	}

	public void setAuthMngName(String authMngName) {
		this.authMngName = authMngName;
	}

	public String getAuthMngTel() {
		return this.authMngTel;
	}

	public void setAuthMngTel(String authMngTel) {
		this.authMngTel = authMngTel;
	}

	public String getSettMngName() {
		return this.settMngName;
	}

	public void setSettMngName(String settMngName) {
		this.settMngName = settMngName;
	}

	public String getSettMngTel() {
		return this.settMngTel;
	}

	public void setSettMngTel(String settMngTel) {
		this.settMngTel = settMngTel;
	}

	public String getCardMngName() {
		return this.cardMngName;
	}

	public void setCardMngName(String cardMngName) {
		this.cardMngName = cardMngName;
	}

	public String getCardMngTel() {
		return this.cardMngTel;
	}

	public void setCardMngTel(String cardMngTel) {
		this.cardMngTel = cardMngTel;
	}

	public String getNasMngName() {
		return this.nasMngName;
	}

	public void setNasMngName(String nasMngName) {
		this.nasMngName = nasMngName;
	}

	public String getNasMngTel() {
		return this.nasMngTel;
	}

	public void setNasMngTel(String nasMngTel) {
		this.nasMngTel = nasMngTel;
	}

	public String getUpdateOper() {
		return this.updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BankInfoId))
			return false;
		BankInfoId castOther = (BankInfoId) other;

		return ((this.getBankId() == castOther.getBankId()) || (this
				.getBankId() != null
				&& castOther.getBankId() != null && this.getBankId().equals(
				castOther.getBankId())))
				&& ((this.getHostId() == castOther.getHostId()) || (this
						.getHostId() != null
						&& castOther.getHostId() != null && this.getHostId()
						.equals(castOther.getHostId())))
				&& ((this.getBankName() == castOther.getBankName()) || (this
						.getBankName() != null
						&& castOther.getBankName() != null && this
						.getBankName().equals(castOther.getBankName())))
				&& ((this.getBankType() == castOther.getBankType()) || (this
						.getBankType() != null
						&& castOther.getBankType() != null && this
						.getBankType().equals(castOther.getBankType())))
				&& ((this.getAdmBankId() == castOther.getAdmBankId()) || (this
						.getAdmBankId() != null
						&& castOther.getAdmBankId() != null && this
						.getAdmBankId().equals(castOther.getAdmBankId())))
				&& ((this.getAdmHostId() == castOther.getAdmHostId()) || (this
						.getAdmHostId() != null
						&& castOther.getAdmHostId() != null && this
						.getAdmHostId().equals(castOther.getAdmHostId())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null
						&& castOther.getAddress() != null && this.getAddress()
						.equals(castOther.getAddress())))
				&& ((this.getPostCode() == castOther.getPostCode()) || (this
						.getPostCode() != null
						&& castOther.getPostCode() != null && this
						.getPostCode().equals(castOther.getPostCode())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null
						&& castOther.getEmail() != null && this.getEmail()
						.equals(castOther.getEmail())))
				&& ((this.getFax() == castOther.getFax()) || (this.getFax() != null
						&& castOther.getFax() != null && this.getFax().equals(
						castOther.getFax())))
				&& ((this.getTelex() == castOther.getTelex()) || (this
						.getTelex() != null
						&& castOther.getTelex() != null && this.getTelex()
						.equals(castOther.getTelex())))
				&& ((this.getAuthTel() == castOther.getAuthTel()) || (this
						.getAuthTel() != null
						&& castOther.getAuthTel() != null && this.getAuthTel()
						.equals(castOther.getAuthTel())))
				&& ((this.getSettleTel() == castOther.getSettleTel()) || (this
						.getSettleTel() != null
						&& castOther.getSettleTel() != null && this
						.getSettleTel().equals(castOther.getSettleTel())))
				&& ((this.getGmName() == castOther.getGmName()) || (this
						.getGmName() != null
						&& castOther.getGmName() != null && this.getGmName()
						.equals(castOther.getGmName())))
				&& ((this.getGmTel() == castOther.getGmTel()) || (this
						.getGmTel() != null
						&& castOther.getGmTel() != null && this.getGmTel()
						.equals(castOther.getGmTel())))
				&& ((this.getMngName1() == castOther.getMngName1()) || (this
						.getMngName1() != null
						&& castOther.getMngName1() != null && this
						.getMngName1().equals(castOther.getMngName1())))
				&& ((this.getMngTel1() == castOther.getMngTel1()) || (this
						.getMngTel1() != null
						&& castOther.getMngTel1() != null && this.getMngTel1()
						.equals(castOther.getMngTel1())))
				&& ((this.getMngName2() == castOther.getMngName2()) || (this
						.getMngName2() != null
						&& castOther.getMngName2() != null && this
						.getMngName2().equals(castOther.getMngName2())))
				&& ((this.getMngTel2() == castOther.getMngTel2()) || (this
						.getMngTel2() != null
						&& castOther.getMngTel2() != null && this.getMngTel2()
						.equals(castOther.getMngTel2())))
				&& ((this.getMngName3() == castOther.getMngName3()) || (this
						.getMngName3() != null
						&& castOther.getMngName3() != null && this
						.getMngName3().equals(castOther.getMngName3())))
				&& ((this.getMngTel3() == castOther.getMngTel3()) || (this
						.getMngTel3() != null
						&& castOther.getMngTel3() != null && this.getMngTel3()
						.equals(castOther.getMngTel3())))
				&& ((this.getAuthMngName() == castOther.getAuthMngName()) || (this
						.getAuthMngName() != null
						&& castOther.getAuthMngName() != null && this
						.getAuthMngName().equals(castOther.getAuthMngName())))
				&& ((this.getAuthMngTel() == castOther.getAuthMngTel()) || (this
						.getAuthMngTel() != null
						&& castOther.getAuthMngTel() != null && this
						.getAuthMngTel().equals(castOther.getAuthMngTel())))
				&& ((this.getSettMngName() == castOther.getSettMngName()) || (this
						.getSettMngName() != null
						&& castOther.getSettMngName() != null && this
						.getSettMngName().equals(castOther.getSettMngName())))
				&& ((this.getSettMngTel() == castOther.getSettMngTel()) || (this
						.getSettMngTel() != null
						&& castOther.getSettMngTel() != null && this
						.getSettMngTel().equals(castOther.getSettMngTel())))
				&& ((this.getCardMngName() == castOther.getCardMngName()) || (this
						.getCardMngName() != null
						&& castOther.getCardMngName() != null && this
						.getCardMngName().equals(castOther.getCardMngName())))
				&& ((this.getCardMngTel() == castOther.getCardMngTel()) || (this
						.getCardMngTel() != null
						&& castOther.getCardMngTel() != null && this
						.getCardMngTel().equals(castOther.getCardMngTel())))
				&& ((this.getNasMngName() == castOther.getNasMngName()) || (this
						.getNasMngName() != null
						&& castOther.getNasMngName() != null && this
						.getNasMngName().equals(castOther.getNasMngName())))
				&& ((this.getNasMngTel() == castOther.getNasMngTel()) || (this
						.getNasMngTel() != null
						&& castOther.getNasMngTel() != null && this
						.getNasMngTel().equals(castOther.getNasMngTel())))
				&& ((this.getUpdateOper() == castOther.getUpdateOper()) || (this
						.getUpdateOper() != null
						&& castOther.getUpdateOper() != null && this
						.getUpdateOper().equals(castOther.getUpdateOper())))
				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this
						.getUpdateDate() != null
						&& castOther.getUpdateDate() != null && this
						.getUpdateDate().equals(castOther.getUpdateDate())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankId() == null ? 0 : this.getBankId().hashCode());
		result = 37 * result
				+ (getHostId() == null ? 0 : this.getHostId().hashCode());
		result = 37 * result
				+ (getBankName() == null ? 0 : this.getBankName().hashCode());
		result = 37 * result
				+ (getBankType() == null ? 0 : this.getBankType().hashCode());
		result = 37 * result
				+ (getAdmBankId() == null ? 0 : this.getAdmBankId().hashCode());
		result = 37 * result
				+ (getAdmHostId() == null ? 0 : this.getAdmHostId().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getPostCode() == null ? 0 : this.getPostCode().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result
				+ (getFax() == null ? 0 : this.getFax().hashCode());
		result = 37 * result
				+ (getTelex() == null ? 0 : this.getTelex().hashCode());
		result = 37 * result
				+ (getAuthTel() == null ? 0 : this.getAuthTel().hashCode());
		result = 37 * result
				+ (getSettleTel() == null ? 0 : this.getSettleTel().hashCode());
		result = 37 * result
				+ (getGmName() == null ? 0 : this.getGmName().hashCode());
		result = 37 * result
				+ (getGmTel() == null ? 0 : this.getGmTel().hashCode());
		result = 37 * result
				+ (getMngName1() == null ? 0 : this.getMngName1().hashCode());
		result = 37 * result
				+ (getMngTel1() == null ? 0 : this.getMngTel1().hashCode());
		result = 37 * result
				+ (getMngName2() == null ? 0 : this.getMngName2().hashCode());
		result = 37 * result
				+ (getMngTel2() == null ? 0 : this.getMngTel2().hashCode());
		result = 37 * result
				+ (getMngName3() == null ? 0 : this.getMngName3().hashCode());
		result = 37 * result
				+ (getMngTel3() == null ? 0 : this.getMngTel3().hashCode());
		result = 37
				* result
				+ (getAuthMngName() == null ? 0 : this.getAuthMngName()
						.hashCode());
		result = 37
				* result
				+ (getAuthMngTel() == null ? 0 : this.getAuthMngTel()
						.hashCode());
		result = 37
				* result
				+ (getSettMngName() == null ? 0 : this.getSettMngName()
						.hashCode());
		result = 37
				* result
				+ (getSettMngTel() == null ? 0 : this.getSettMngTel()
						.hashCode());
		result = 37
				* result
				+ (getCardMngName() == null ? 0 : this.getCardMngName()
						.hashCode());
		result = 37
				* result
				+ (getCardMngTel() == null ? 0 : this.getCardMngTel()
						.hashCode());
		result = 37
				* result
				+ (getNasMngName() == null ? 0 : this.getNasMngName()
						.hashCode());
		result = 37 * result
				+ (getNasMngTel() == null ? 0 : this.getNasMngTel().hashCode());
		result = 37
				* result
				+ (getUpdateOper() == null ? 0 : this.getUpdateOper()
						.hashCode());
		result = 37
				* result
				+ (getUpdateDate() == null ? 0 : this.getUpdateDate()
						.hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		return result;
	}

}