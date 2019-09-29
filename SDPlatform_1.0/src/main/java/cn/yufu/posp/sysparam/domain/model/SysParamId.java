package cn.yufu.posp.sysparam.domain.model;

import java.math.BigDecimal;

/**
 * SysParamId entity. @author MyEclipse Persistence Tools
 */

public class SysParamId implements java.io.Serializable {

	// Fields

	private String bankId;
	private String hostId;
	private String admBankId;
	private String admHostId;
	private String bankType;
	private BigDecimal hostLsNo;
	private BigDecimal hisLsDay;
	private String backupLdate;
	private BigDecimal backupInterval;
	private BigDecimal curLsDay;
	private String autoChgFlag;
	private BigDecimal autoSafDay;
	private BigDecimal endDay;
	private String ifNoSaf;
	private BigDecimal safInterval;
	private String creChkFlag;
	private String logicDate;
	private String ifRepAuth;
	private Double authAmt;
	private BigDecimal dayAuthCnt;
	private Double dayAuthAmt;
	private String ifOffline;
	private Double adjustRate;
	private Double confirmRate;
	private String updateOper;
	private String updateDate;
	private String updateTime;

	// Constructors

	/** default constructor */
	public SysParamId() {
	}

	/** minimal constructor */
	public SysParamId(String bankId, String hostId, String admBankId,
			String admHostId, String bankType, BigDecimal hostLsNo,
			BigDecimal hisLsDay, String backupLdate, BigDecimal backupInterval,
			BigDecimal curLsDay, String logicDate, String ifRepAuth) {
		this.bankId = bankId;
		this.hostId = hostId;
		this.admBankId = admBankId;
		this.admHostId = admHostId;
		this.bankType = bankType;
		this.hostLsNo = hostLsNo;
		this.hisLsDay = hisLsDay;
		this.backupLdate = backupLdate;
		this.backupInterval = backupInterval;
		this.curLsDay = curLsDay;
		this.logicDate = logicDate;
		this.ifRepAuth = ifRepAuth;
	}

	/** full constructor */
	public SysParamId(String bankId, String hostId, String admBankId,
			String admHostId, String bankType, BigDecimal hostLsNo,
			BigDecimal hisLsDay, String backupLdate, BigDecimal backupInterval,
			BigDecimal curLsDay, String autoChgFlag, BigDecimal autoSafDay,
			BigDecimal endDay, String ifNoSaf, BigDecimal safInterval,
			String creChkFlag, String logicDate, String ifRepAuth,
			Double authAmt, BigDecimal dayAuthCnt, Double dayAuthAmt,
			String ifOffline, Double adjustRate, Double confirmRate,
			String updateOper, String updateDate, String updateTime) {
		this.bankId = bankId;
		this.hostId = hostId;
		this.admBankId = admBankId;
		this.admHostId = admHostId;
		this.bankType = bankType;
		this.hostLsNo = hostLsNo;
		this.hisLsDay = hisLsDay;
		this.backupLdate = backupLdate;
		this.backupInterval = backupInterval;
		this.curLsDay = curLsDay;
		this.autoChgFlag = autoChgFlag;
		this.autoSafDay = autoSafDay;
		this.endDay = endDay;
		this.ifNoSaf = ifNoSaf;
		this.safInterval = safInterval;
		this.creChkFlag = creChkFlag;
		this.logicDate = logicDate;
		this.ifRepAuth = ifRepAuth;
		this.authAmt = authAmt;
		this.dayAuthCnt = dayAuthCnt;
		this.dayAuthAmt = dayAuthAmt;
		this.ifOffline = ifOffline;
		this.adjustRate = adjustRate;
		this.confirmRate = confirmRate;
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

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public BigDecimal getHostLsNo() {
		return this.hostLsNo;
	}

	public void setHostLsNo(BigDecimal hostLsNo) {
		this.hostLsNo = hostLsNo;
	}

	public BigDecimal getHisLsDay() {
		return this.hisLsDay;
	}

	public void setHisLsDay(BigDecimal hisLsDay) {
		this.hisLsDay = hisLsDay;
	}

	public String getBackupLdate() {
		return this.backupLdate;
	}

	public void setBackupLdate(String backupLdate) {
		this.backupLdate = backupLdate;
	}

	public BigDecimal getBackupInterval() {
		return this.backupInterval;
	}

	public void setBackupInterval(BigDecimal backupInterval) {
		this.backupInterval = backupInterval;
	}

	public BigDecimal getCurLsDay() {
		return this.curLsDay;
	}

	public void setCurLsDay(BigDecimal curLsDay) {
		this.curLsDay = curLsDay;
	}

	public String getAutoChgFlag() {
		return this.autoChgFlag;
	}

	public void setAutoChgFlag(String autoChgFlag) {
		this.autoChgFlag = autoChgFlag;
	}

	public BigDecimal getAutoSafDay() {
		return this.autoSafDay;
	}

	public void setAutoSafDay(BigDecimal autoSafDay) {
		this.autoSafDay = autoSafDay;
	}

	public BigDecimal getEndDay() {
		return this.endDay;
	}

	public void setEndDay(BigDecimal endDay) {
		this.endDay = endDay;
	}

	public String getIfNoSaf() {
		return this.ifNoSaf;
	}

	public void setIfNoSaf(String ifNoSaf) {
		this.ifNoSaf = ifNoSaf;
	}

	public BigDecimal getSafInterval() {
		return this.safInterval;
	}

	public void setSafInterval(BigDecimal safInterval) {
		this.safInterval = safInterval;
	}

	public String getCreChkFlag() {
		return this.creChkFlag;
	}

	public void setCreChkFlag(String creChkFlag) {
		this.creChkFlag = creChkFlag;
	}

	public String getLogicDate() {
		return this.logicDate;
	}

	public void setLogicDate(String logicDate) {
		this.logicDate = logicDate;
	}

	public String getIfRepAuth() {
		return this.ifRepAuth;
	}

	public void setIfRepAuth(String ifRepAuth) {
		this.ifRepAuth = ifRepAuth;
	}

	public Double getAuthAmt() {
		return this.authAmt;
	}

	public void setAuthAmt(Double authAmt) {
		this.authAmt = authAmt;
	}

	public BigDecimal getDayAuthCnt() {
		return this.dayAuthCnt;
	}

	public void setDayAuthCnt(BigDecimal dayAuthCnt) {
		this.dayAuthCnt = dayAuthCnt;
	}

	public Double getDayAuthAmt() {
		return this.dayAuthAmt;
	}

	public void setDayAuthAmt(Double dayAuthAmt) {
		this.dayAuthAmt = dayAuthAmt;
	}

	public String getIfOffline() {
		return this.ifOffline;
	}

	public void setIfOffline(String ifOffline) {
		this.ifOffline = ifOffline;
	}

	public Double getAdjustRate() {
		return this.adjustRate;
	}

	public void setAdjustRate(Double adjustRate) {
		this.adjustRate = adjustRate;
	}

	public Double getConfirmRate() {
		return this.confirmRate;
	}

	public void setConfirmRate(Double confirmRate) {
		this.confirmRate = confirmRate;
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
		if (!(other instanceof SysParamId))
			return false;
		SysParamId castOther = (SysParamId) other;

		return ((this.getBankId() == castOther.getBankId()) || (this
				.getBankId() != null
				&& castOther.getBankId() != null && this.getBankId().equals(
				castOther.getBankId())))
				&& ((this.getHostId() == castOther.getHostId()) || (this
						.getHostId() != null
						&& castOther.getHostId() != null && this.getHostId()
						.equals(castOther.getHostId())))
				&& ((this.getAdmBankId() == castOther.getAdmBankId()) || (this
						.getAdmBankId() != null
						&& castOther.getAdmBankId() != null && this
						.getAdmBankId().equals(castOther.getAdmBankId())))
				&& ((this.getAdmHostId() == castOther.getAdmHostId()) || (this
						.getAdmHostId() != null
						&& castOther.getAdmHostId() != null && this
						.getAdmHostId().equals(castOther.getAdmHostId())))
				&& ((this.getBankType() == castOther.getBankType()) || (this
						.getBankType() != null
						&& castOther.getBankType() != null && this
						.getBankType().equals(castOther.getBankType())))
				&& ((this.getHostLsNo() == castOther.getHostLsNo()) || (this
						.getHostLsNo() != null
						&& castOther.getHostLsNo() != null && this
						.getHostLsNo().equals(castOther.getHostLsNo())))
				&& ((this.getHisLsDay() == castOther.getHisLsDay()) || (this
						.getHisLsDay() != null
						&& castOther.getHisLsDay() != null && this
						.getHisLsDay().equals(castOther.getHisLsDay())))
				&& ((this.getBackupLdate() == castOther.getBackupLdate()) || (this
						.getBackupLdate() != null
						&& castOther.getBackupLdate() != null && this
						.getBackupLdate().equals(castOther.getBackupLdate())))
				&& ((this.getBackupInterval() == castOther.getBackupInterval()) || (this
						.getBackupInterval() != null
						&& castOther.getBackupInterval() != null && this
						.getBackupInterval().equals(
								castOther.getBackupInterval())))
				&& ((this.getCurLsDay() == castOther.getCurLsDay()) || (this
						.getCurLsDay() != null
						&& castOther.getCurLsDay() != null && this
						.getCurLsDay().equals(castOther.getCurLsDay())))
				&& ((this.getAutoChgFlag() == castOther.getAutoChgFlag()) || (this
						.getAutoChgFlag() != null
						&& castOther.getAutoChgFlag() != null && this
						.getAutoChgFlag().equals(castOther.getAutoChgFlag())))
				&& ((this.getAutoSafDay() == castOther.getAutoSafDay()) || (this
						.getAutoSafDay() != null
						&& castOther.getAutoSafDay() != null && this
						.getAutoSafDay().equals(castOther.getAutoSafDay())))
				&& ((this.getEndDay() == castOther.getEndDay()) || (this
						.getEndDay() != null
						&& castOther.getEndDay() != null && this.getEndDay()
						.equals(castOther.getEndDay())))
				&& ((this.getIfNoSaf() == castOther.getIfNoSaf()) || (this
						.getIfNoSaf() != null
						&& castOther.getIfNoSaf() != null && this.getIfNoSaf()
						.equals(castOther.getIfNoSaf())))
				&& ((this.getSafInterval() == castOther.getSafInterval()) || (this
						.getSafInterval() != null
						&& castOther.getSafInterval() != null && this
						.getSafInterval().equals(castOther.getSafInterval())))
				&& ((this.getCreChkFlag() == castOther.getCreChkFlag()) || (this
						.getCreChkFlag() != null
						&& castOther.getCreChkFlag() != null && this
						.getCreChkFlag().equals(castOther.getCreChkFlag())))
				&& ((this.getLogicDate() == castOther.getLogicDate()) || (this
						.getLogicDate() != null
						&& castOther.getLogicDate() != null && this
						.getLogicDate().equals(castOther.getLogicDate())))
				&& ((this.getIfRepAuth() == castOther.getIfRepAuth()) || (this
						.getIfRepAuth() != null
						&& castOther.getIfRepAuth() != null && this
						.getIfRepAuth().equals(castOther.getIfRepAuth())))
				&& ((this.getAuthAmt() == castOther.getAuthAmt()) || (this
						.getAuthAmt() != null
						&& castOther.getAuthAmt() != null && this.getAuthAmt()
						.equals(castOther.getAuthAmt())))
				&& ((this.getDayAuthCnt() == castOther.getDayAuthCnt()) || (this
						.getDayAuthCnt() != null
						&& castOther.getDayAuthCnt() != null && this
						.getDayAuthCnt().equals(castOther.getDayAuthCnt())))
				&& ((this.getDayAuthAmt() == castOther.getDayAuthAmt()) || (this
						.getDayAuthAmt() != null
						&& castOther.getDayAuthAmt() != null && this
						.getDayAuthAmt().equals(castOther.getDayAuthAmt())))
				&& ((this.getIfOffline() == castOther.getIfOffline()) || (this
						.getIfOffline() != null
						&& castOther.getIfOffline() != null && this
						.getIfOffline().equals(castOther.getIfOffline())))
				&& ((this.getAdjustRate() == castOther.getAdjustRate()) || (this
						.getAdjustRate() != null
						&& castOther.getAdjustRate() != null && this
						.getAdjustRate().equals(castOther.getAdjustRate())))
				&& ((this.getConfirmRate() == castOther.getConfirmRate()) || (this
						.getConfirmRate() != null
						&& castOther.getConfirmRate() != null && this
						.getConfirmRate().equals(castOther.getConfirmRate())))
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
				+ (getAdmBankId() == null ? 0 : this.getAdmBankId().hashCode());
		result = 37 * result
				+ (getAdmHostId() == null ? 0 : this.getAdmHostId().hashCode());
		result = 37 * result
				+ (getBankType() == null ? 0 : this.getBankType().hashCode());
		result = 37 * result
				+ (getHostLsNo() == null ? 0 : this.getHostLsNo().hashCode());
		result = 37 * result
				+ (getHisLsDay() == null ? 0 : this.getHisLsDay().hashCode());
		result = 37
				* result
				+ (getBackupLdate() == null ? 0 : this.getBackupLdate()
						.hashCode());
		result = 37
				* result
				+ (getBackupInterval() == null ? 0 : this.getBackupInterval()
						.hashCode());
		result = 37 * result
				+ (getCurLsDay() == null ? 0 : this.getCurLsDay().hashCode());
		result = 37
				* result
				+ (getAutoChgFlag() == null ? 0 : this.getAutoChgFlag()
						.hashCode());
		result = 37
				* result
				+ (getAutoSafDay() == null ? 0 : this.getAutoSafDay()
						.hashCode());
		result = 37 * result
				+ (getEndDay() == null ? 0 : this.getEndDay().hashCode());
		result = 37 * result
				+ (getIfNoSaf() == null ? 0 : this.getIfNoSaf().hashCode());
		result = 37
				* result
				+ (getSafInterval() == null ? 0 : this.getSafInterval()
						.hashCode());
		result = 37
				* result
				+ (getCreChkFlag() == null ? 0 : this.getCreChkFlag()
						.hashCode());
		result = 37 * result
				+ (getLogicDate() == null ? 0 : this.getLogicDate().hashCode());
		result = 37 * result
				+ (getIfRepAuth() == null ? 0 : this.getIfRepAuth().hashCode());
		result = 37 * result
				+ (getAuthAmt() == null ? 0 : this.getAuthAmt().hashCode());
		result = 37
				* result
				+ (getDayAuthCnt() == null ? 0 : this.getDayAuthCnt()
						.hashCode());
		result = 37
				* result
				+ (getDayAuthAmt() == null ? 0 : this.getDayAuthAmt()
						.hashCode());
		result = 37 * result
				+ (getIfOffline() == null ? 0 : this.getIfOffline().hashCode());
		result = 37
				* result
				+ (getAdjustRate() == null ? 0 : this.getAdjustRate()
						.hashCode());
		result = 37
				* result
				+ (getConfirmRate() == null ? 0 : this.getConfirmRate()
						.hashCode());
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