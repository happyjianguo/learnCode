package cn.yufu.cortexBak.entity;

public class MrchClassBak {
    private Long instId;

    private String mrchno;

    private String classify;

    private String fmrchno;

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getMrchno() {
        return mrchno;
    }

    public void setMrchno(String mrchno) {
        this.mrchno = mrchno == null ? null : mrchno.trim();
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public String getFmrchno() {
        return fmrchno;
    }

    public void setFmrchno(String fmrchno) {
        this.fmrchno = fmrchno == null ? null : fmrchno.trim();
    }
}