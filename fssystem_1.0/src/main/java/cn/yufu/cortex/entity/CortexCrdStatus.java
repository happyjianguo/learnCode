package cn.yufu.cortex.entity;

public class CortexCrdStatus {
    private String statcode;

    private Long vernoCtx;

    private String sysdef;

    private String descr;

    private String actioncode;

    private String rspcode;

    private String canceled;

    public String getStatcode() {
        return statcode;
    }

    public void setStatcode(String statcode) {
        this.statcode = statcode == null ? null : statcode.trim();
    }

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public String getSysdef() {
        return sysdef;
    }

    public void setSysdef(String sysdef) {
        this.sysdef = sysdef == null ? null : sysdef.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getActioncode() {
        return actioncode;
    }

    public void setActioncode(String actioncode) {
        this.actioncode = actioncode == null ? null : actioncode.trim();
    }

    public String getRspcode() {
        return rspcode;
    }

    public void setRspcode(String rspcode) {
        this.rspcode = rspcode == null ? null : rspcode.trim();
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled == null ? null : canceled.trim();
    }
}