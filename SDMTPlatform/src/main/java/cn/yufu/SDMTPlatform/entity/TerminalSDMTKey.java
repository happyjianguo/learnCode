package cn.yufu.SDMTPlatform.entity;

public class TerminalSDMTKey {
    private String merchantId;

    private String termCode;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode == null ? null : termCode.trim();
    }
}