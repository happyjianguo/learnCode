package cn.yufu.cortex.entity;

public class TermPos {
    private Long id;

    private Long vernoCtx;

    private Integer typeid;

    private String termcode;

    private Integer testflag;

    private Integer statusid;

    private String currcode;

    private Integer termno;

    private String location;

    private String conaccno;

    private String concur;

    private String poschic;

    private String poschac;

    private String poscrc;

    private String posoe;

    private String poscdoc;

    private String postoc;

    private String pospcc;

    private String timezone;

    private String catParams;

    private Long merchantId;

    private String termtype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode == null ? null : termcode.trim();
    }

    public Integer getTestflag() {
        return testflag;
    }

    public void setTestflag(Integer testflag) {
        this.testflag = testflag;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getCurrcode() {
        return currcode;
    }

    public void setCurrcode(String currcode) {
        this.currcode = currcode == null ? null : currcode.trim();
    }

    public Integer getTermno() {
        return termno;
    }

    public void setTermno(Integer termno) {
        this.termno = termno;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getConaccno() {
        return conaccno;
    }

    public void setConaccno(String conaccno) {
        this.conaccno = conaccno == null ? null : conaccno.trim();
    }

    public String getConcur() {
        return concur;
    }

    public void setConcur(String concur) {
        this.concur = concur == null ? null : concur.trim();
    }

    public String getPoschic() {
        return poschic;
    }

    public void setPoschic(String poschic) {
        this.poschic = poschic == null ? null : poschic.trim();
    }

    public String getPoschac() {
        return poschac;
    }

    public void setPoschac(String poschac) {
        this.poschac = poschac == null ? null : poschac.trim();
    }

    public String getPoscrc() {
        return poscrc;
    }

    public void setPoscrc(String poscrc) {
        this.poscrc = poscrc == null ? null : poscrc.trim();
    }

    public String getPosoe() {
        return posoe;
    }

    public void setPosoe(String posoe) {
        this.posoe = posoe == null ? null : posoe.trim();
    }

    public String getPoscdoc() {
        return poscdoc;
    }

    public void setPoscdoc(String poscdoc) {
        this.poscdoc = poscdoc == null ? null : poscdoc.trim();
    }

    public String getPostoc() {
        return postoc;
    }

    public void setPostoc(String postoc) {
        this.postoc = postoc == null ? null : postoc.trim();
    }

    public String getPospcc() {
        return pospcc;
    }

    public void setPospcc(String pospcc) {
        this.pospcc = pospcc == null ? null : pospcc.trim();
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone == null ? null : timezone.trim();
    }

    public String getCatParams() {
        return catParams;
    }

    public void setCatParams(String catParams) {
        this.catParams = catParams == null ? null : catParams.trim();
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getTermtype() {
        return termtype;
    }

    public void setTermtype(String termtype) {
        this.termtype = termtype == null ? null : termtype.trim();
    }
}