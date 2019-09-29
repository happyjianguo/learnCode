package cn.yufu.posp.entity;

import java.math.BigDecimal;

public class EdcTerminal {
    private String merchantId;

    private String terminalId;

    private String terminalStat;

    private String edcType;

    private String edcDoc;

    private String printerType;

    private String pinpadType;

    private String softVer;

    private String downloadFlag;

    private BigDecimal downloadMode;

    private String setDate;

    private String setAddr;

    private String updateOper;

    private String updateDate;

    private String updateTime;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }

    public String getTerminalStat() {
        return terminalStat;
    }

    public void setTerminalStat(String terminalStat) {
        this.terminalStat = terminalStat == null ? null : terminalStat.trim();
    }

    public String getEdcType() {
        return edcType;
    }

    public void setEdcType(String edcType) {
        this.edcType = edcType == null ? null : edcType.trim();
    }

    public String getEdcDoc() {
        return edcDoc;
    }

    public void setEdcDoc(String edcDoc) {
        this.edcDoc = edcDoc == null ? null : edcDoc.trim();
    }

    public String getPrinterType() {
        return printerType;
    }

    public void setPrinterType(String printerType) {
        this.printerType = printerType == null ? null : printerType.trim();
    }

    public String getPinpadType() {
        return pinpadType;
    }

    public void setPinpadType(String pinpadType) {
        this.pinpadType = pinpadType == null ? null : pinpadType.trim();
    }

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer == null ? null : softVer.trim();
    }

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag == null ? null : downloadFlag.trim();
    }

    public BigDecimal getDownloadMode() {
        return downloadMode;
    }

    public void setDownloadMode(BigDecimal downloadMode) {
        this.downloadMode = downloadMode;
    }

    public String getSetDate() {
        return setDate;
    }

    public void setSetDate(String setDate) {
        this.setDate = setDate == null ? null : setDate.trim();
    }

    public String getSetAddr() {
        return setAddr;
    }

    public void setSetAddr(String setAddr) {
        this.setAddr = setAddr == null ? null : setAddr.trim();
    }

    public String getUpdateOper() {
        return updateOper;
    }

    public void setUpdateOper(String updateOper) {
        this.updateOper = updateOper == null ? null : updateOper.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}