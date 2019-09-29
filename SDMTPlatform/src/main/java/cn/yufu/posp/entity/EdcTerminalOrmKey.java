package cn.yufu.posp.entity;

import java.math.BigDecimal;

public class EdcTerminalOrmKey {
    private String merchantId;

    private BigDecimal moduleId;

    private String terminalId;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public BigDecimal getModuleId() {
        return moduleId;
    }

    public void setModuleId(BigDecimal moduleId) {
        this.moduleId = moduleId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }
}