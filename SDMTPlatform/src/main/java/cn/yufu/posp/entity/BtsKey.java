package cn.yufu.posp.entity;

import java.math.BigDecimal;

public class BtsKey {
    private String merchantId;

    private String terminalId;

    private String operNo;

    private String operPasswd;

    private String masterKey;

    private String pinKey;

    private String macKey;

    private String posKey;

    private String logonKey;

    private String settleFlag;

    private BigDecimal batchNo;

    private String tmkmasterKey;

    private String lmkTdk;

    private String tmkTdk;

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

    public String getOperNo() {
        return operNo;
    }

    public void setOperNo(String operNo) {
        this.operNo = operNo == null ? null : operNo.trim();
    }

    public String getOperPasswd() {
        return operPasswd;
    }

    public void setOperPasswd(String operPasswd) {
        this.operPasswd = operPasswd == null ? null : operPasswd.trim();
    }

    public String getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey == null ? null : masterKey.trim();
    }

    public String getPinKey() {
        return pinKey;
    }

    public void setPinKey(String pinKey) {
        this.pinKey = pinKey == null ? null : pinKey.trim();
    }

    public String getMacKey() {
        return macKey;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey == null ? null : macKey.trim();
    }

    public String getPosKey() {
        return posKey;
    }

    public void setPosKey(String posKey) {
        this.posKey = posKey == null ? null : posKey.trim();
    }

    public String getLogonKey() {
        return logonKey;
    }

    public void setLogonKey(String logonKey) {
        this.logonKey = logonKey == null ? null : logonKey.trim();
    }

    public String getSettleFlag() {
        return settleFlag;
    }

    public void setSettleFlag(String settleFlag) {
        this.settleFlag = settleFlag == null ? null : settleFlag.trim();
    }

    public BigDecimal getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(BigDecimal batchNo) {
        this.batchNo = batchNo;
    }

    public String getTmkmasterKey() {
        return tmkmasterKey;
    }

    public void setTmkmasterKey(String tmkmasterKey) {
        this.tmkmasterKey = tmkmasterKey == null ? null : tmkmasterKey.trim();
    }

    public String getLmkTdk() {
        return lmkTdk;
    }

    public void setLmkTdk(String lmkTdk) {
        this.lmkTdk = lmkTdk == null ? null : lmkTdk.trim();
    }

    public String getTmkTdk() {
        return tmkTdk;
    }

    public void setTmkTdk(String tmkTdk) {
        this.tmkTdk = tmkTdk == null ? null : tmkTdk.trim();
    }
}