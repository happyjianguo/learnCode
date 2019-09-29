package cn.yufu.posp.entity;

public class MerchantRefund {
    private String merchantId;

    private Double refundLimit;

    private String refundCheck;

    private String updateOper;

    private String updateDate;

    private String updateTime;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public Double getRefundLimit() {
        return refundLimit;
    }

    public void setRefundLimit(Double refundLimit) {
        this.refundLimit = refundLimit;
    }

    public String getRefundCheck() {
        return refundCheck;
    }

    public void setRefundCheck(String refundCheck) {
        this.refundCheck = refundCheck == null ? null : refundCheck.trim();
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