package cn.yufu.posp.entity;

import java.math.BigDecimal;

public class EdcNewfkterminalOrm extends EdcNewfkterminalOrmKey {
    private String bankId;

    private String bankMerchantId;

    private String bankTerminalId;

    private BigDecimal sysTrace;

    private BigDecimal bankTrace;

    private BigDecimal pinFmt;

    private BigDecimal encMethod;

    private BigDecimal macFlag;

    private BigDecimal batchNo;

    private String pik;

    private String mak;

    private String tmk;

    private String pikTmk;

    private String makTmk;

    private String keyIndex;

    private BigDecimal settStatus;

    private BigDecimal logonStatus;

    private BigDecimal flag;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getBankMerchantId() {
        return bankMerchantId;
    }

    public void setBankMerchantId(String bankMerchantId) {
        this.bankMerchantId = bankMerchantId == null ? null : bankMerchantId.trim();
    }

    public String getBankTerminalId() {
        return bankTerminalId;
    }

    public void setBankTerminalId(String bankTerminalId) {
        this.bankTerminalId = bankTerminalId == null ? null : bankTerminalId.trim();
    }

    public BigDecimal getSysTrace() {
        return sysTrace;
    }

    public void setSysTrace(BigDecimal sysTrace) {
        this.sysTrace = sysTrace;
    }

    public BigDecimal getBankTrace() {
        return bankTrace;
    }

    public void setBankTrace(BigDecimal bankTrace) {
        this.bankTrace = bankTrace;
    }

    public BigDecimal getPinFmt() {
        return pinFmt;
    }

    public void setPinFmt(BigDecimal pinFmt) {
        this.pinFmt = pinFmt;
    }

    public BigDecimal getEncMethod() {
        return encMethod;
    }

    public void setEncMethod(BigDecimal encMethod) {
        this.encMethod = encMethod;
    }

    public BigDecimal getMacFlag() {
        return macFlag;
    }

    public void setMacFlag(BigDecimal macFlag) {
        this.macFlag = macFlag;
    }

    public BigDecimal getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(BigDecimal batchNo) {
        this.batchNo = batchNo;
    }

    public String getPik() {
        return pik;
    }

    public void setPik(String pik) {
        this.pik = pik == null ? null : pik.trim();
    }

    public String getMak() {
        return mak;
    }

    public void setMak(String mak) {
        this.mak = mak == null ? null : mak.trim();
    }

    public String getTmk() {
        return tmk;
    }

    public void setTmk(String tmk) {
        this.tmk = tmk == null ? null : tmk.trim();
    }

    public String getPikTmk() {
        return pikTmk;
    }

    public void setPikTmk(String pikTmk) {
        this.pikTmk = pikTmk == null ? null : pikTmk.trim();
    }

    public String getMakTmk() {
        return makTmk;
    }

    public void setMakTmk(String makTmk) {
        this.makTmk = makTmk == null ? null : makTmk.trim();
    }

    public String getKeyIndex() {
        return keyIndex;
    }

    public void setKeyIndex(String keyIndex) {
        this.keyIndex = keyIndex == null ? null : keyIndex.trim();
    }

    public BigDecimal getSettStatus() {
        return settStatus;
    }

    public void setSettStatus(BigDecimal settStatus) {
        this.settStatus = settStatus;
    }

    public BigDecimal getLogonStatus() {
        return logonStatus;
    }

    public void setLogonStatus(BigDecimal logonStatus) {
        this.logonStatus = logonStatus;
    }

    public BigDecimal getFlag() {
        return flag;
    }

    public void setFlag(BigDecimal flag) {
        this.flag = flag;
    }
}