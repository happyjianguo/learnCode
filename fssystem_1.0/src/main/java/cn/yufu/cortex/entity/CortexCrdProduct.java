package cn.yufu.cortex.entity;

public class CortexCrdProduct {
    private Long id;

    private Long vernoCtx;

    private Long instId;

    private String crdproduct;

    private String descr;

    private Long crdformatId;

    private String affinity;

    private String aci;

    private String defTypeacc;

    private Integer prodnum;

    private Long prodseq;

    private Integer points1;

    private Integer points2;

    private String stmtLine1;

    private String stmtLine2;

    private String stmtLine3;

    private String pointsLine1;

    private String pointsLine2;

    private String overlimLine1;

    private String overlimLine2;

    private String latepayLine1;

    private String latepayLine2;

    private String overdue1Line1;

    private String overdue1Line2;

    private String overdue2Line1;

    private String overdue2Line2;

    private String overdue3Line1;

    private String overdue3Line2;

    private String denysvc;

    private Integer emvcrptgid;

    private Integer authchksid;

    private Long atclimit;

    private String arqcfailact;

    private String arqcfailrsp;

    private String emvscriptsvc;

    private Integer fallbacklimit;

    private String fallbackact;

    private String fallbackrsp;

    private Integer emvencsch;

    private Integer emviccver;

    private Integer emvoda;

    private Double amtrtiloc;

    private String currtiloc;

    private Double amtrtiint;

    private String currtiint;

    private Integer iccpinfmt;

    private Integer scriptset;

    private String denymcc;

    private Double maxload;

    private Double maxbal;

    private String riskProfCode;

    private String authParams;

    private String features;

    private String icckeydev;

    private Double preauthBlkLim;

    private Double preauthTotLim;

    private String catParams;

    private String options;

    private Integer preauthExpiry;

    private Long crdstatustranssetId;

    private Integer validity;

    private Integer minvalduriss;

    private Integer minvaldurdmg;

    private Integer rnwpstexpperiod;

    private Integer rnwpreexpperiod;

    private Integer rnwearlyperiod;

    private String usrdata1;

    private String usrdata2;

    private Long crdplstctypId;

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

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getCrdproduct() {
        return crdproduct;
    }

    public void setCrdproduct(String crdproduct) {
        this.crdproduct = crdproduct == null ? null : crdproduct.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public Long getCrdformatId() {
        return crdformatId;
    }

    public void setCrdformatId(Long crdformatId) {
        this.crdformatId = crdformatId;
    }

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity == null ? null : affinity.trim();
    }

    public String getAci() {
        return aci;
    }

    public void setAci(String aci) {
        this.aci = aci == null ? null : aci.trim();
    }

    public String getDefTypeacc() {
        return defTypeacc;
    }

    public void setDefTypeacc(String defTypeacc) {
        this.defTypeacc = defTypeacc == null ? null : defTypeacc.trim();
    }

    public Integer getProdnum() {
        return prodnum;
    }

    public void setProdnum(Integer prodnum) {
        this.prodnum = prodnum;
    }

    public Long getProdseq() {
        return prodseq;
    }

    public void setProdseq(Long prodseq) {
        this.prodseq = prodseq;
    }

    public Integer getPoints1() {
        return points1;
    }

    public void setPoints1(Integer points1) {
        this.points1 = points1;
    }

    public Integer getPoints2() {
        return points2;
    }

    public void setPoints2(Integer points2) {
        this.points2 = points2;
    }

    public String getStmtLine1() {
        return stmtLine1;
    }

    public void setStmtLine1(String stmtLine1) {
        this.stmtLine1 = stmtLine1 == null ? null : stmtLine1.trim();
    }

    public String getStmtLine2() {
        return stmtLine2;
    }

    public void setStmtLine2(String stmtLine2) {
        this.stmtLine2 = stmtLine2 == null ? null : stmtLine2.trim();
    }

    public String getStmtLine3() {
        return stmtLine3;
    }

    public void setStmtLine3(String stmtLine3) {
        this.stmtLine3 = stmtLine3 == null ? null : stmtLine3.trim();
    }

    public String getPointsLine1() {
        return pointsLine1;
    }

    public void setPointsLine1(String pointsLine1) {
        this.pointsLine1 = pointsLine1 == null ? null : pointsLine1.trim();
    }

    public String getPointsLine2() {
        return pointsLine2;
    }

    public void setPointsLine2(String pointsLine2) {
        this.pointsLine2 = pointsLine2 == null ? null : pointsLine2.trim();
    }

    public String getOverlimLine1() {
        return overlimLine1;
    }

    public void setOverlimLine1(String overlimLine1) {
        this.overlimLine1 = overlimLine1 == null ? null : overlimLine1.trim();
    }

    public String getOverlimLine2() {
        return overlimLine2;
    }

    public void setOverlimLine2(String overlimLine2) {
        this.overlimLine2 = overlimLine2 == null ? null : overlimLine2.trim();
    }

    public String getLatepayLine1() {
        return latepayLine1;
    }

    public void setLatepayLine1(String latepayLine1) {
        this.latepayLine1 = latepayLine1 == null ? null : latepayLine1.trim();
    }

    public String getLatepayLine2() {
        return latepayLine2;
    }

    public void setLatepayLine2(String latepayLine2) {
        this.latepayLine2 = latepayLine2 == null ? null : latepayLine2.trim();
    }

    public String getOverdue1Line1() {
        return overdue1Line1;
    }

    public void setOverdue1Line1(String overdue1Line1) {
        this.overdue1Line1 = overdue1Line1 == null ? null : overdue1Line1.trim();
    }

    public String getOverdue1Line2() {
        return overdue1Line2;
    }

    public void setOverdue1Line2(String overdue1Line2) {
        this.overdue1Line2 = overdue1Line2 == null ? null : overdue1Line2.trim();
    }

    public String getOverdue2Line1() {
        return overdue2Line1;
    }

    public void setOverdue2Line1(String overdue2Line1) {
        this.overdue2Line1 = overdue2Line1 == null ? null : overdue2Line1.trim();
    }

    public String getOverdue2Line2() {
        return overdue2Line2;
    }

    public void setOverdue2Line2(String overdue2Line2) {
        this.overdue2Line2 = overdue2Line2 == null ? null : overdue2Line2.trim();
    }

    public String getOverdue3Line1() {
        return overdue3Line1;
    }

    public void setOverdue3Line1(String overdue3Line1) {
        this.overdue3Line1 = overdue3Line1 == null ? null : overdue3Line1.trim();
    }

    public String getOverdue3Line2() {
        return overdue3Line2;
    }

    public void setOverdue3Line2(String overdue3Line2) {
        this.overdue3Line2 = overdue3Line2 == null ? null : overdue3Line2.trim();
    }

    public String getDenysvc() {
        return denysvc;
    }

    public void setDenysvc(String denysvc) {
        this.denysvc = denysvc == null ? null : denysvc.trim();
    }

    public Integer getEmvcrptgid() {
        return emvcrptgid;
    }

    public void setEmvcrptgid(Integer emvcrptgid) {
        this.emvcrptgid = emvcrptgid;
    }

    public Integer getAuthchksid() {
        return authchksid;
    }

    public void setAuthchksid(Integer authchksid) {
        this.authchksid = authchksid;
    }

    public Long getAtclimit() {
        return atclimit;
    }

    public void setAtclimit(Long atclimit) {
        this.atclimit = atclimit;
    }

    public String getArqcfailact() {
        return arqcfailact;
    }

    public void setArqcfailact(String arqcfailact) {
        this.arqcfailact = arqcfailact == null ? null : arqcfailact.trim();
    }

    public String getArqcfailrsp() {
        return arqcfailrsp;
    }

    public void setArqcfailrsp(String arqcfailrsp) {
        this.arqcfailrsp = arqcfailrsp == null ? null : arqcfailrsp.trim();
    }

    public String getEmvscriptsvc() {
        return emvscriptsvc;
    }

    public void setEmvscriptsvc(String emvscriptsvc) {
        this.emvscriptsvc = emvscriptsvc == null ? null : emvscriptsvc.trim();
    }

    public Integer getFallbacklimit() {
        return fallbacklimit;
    }

    public void setFallbacklimit(Integer fallbacklimit) {
        this.fallbacklimit = fallbacklimit;
    }

    public String getFallbackact() {
        return fallbackact;
    }

    public void setFallbackact(String fallbackact) {
        this.fallbackact = fallbackact == null ? null : fallbackact.trim();
    }

    public String getFallbackrsp() {
        return fallbackrsp;
    }

    public void setFallbackrsp(String fallbackrsp) {
        this.fallbackrsp = fallbackrsp == null ? null : fallbackrsp.trim();
    }

    public Integer getEmvencsch() {
        return emvencsch;
    }

    public void setEmvencsch(Integer emvencsch) {
        this.emvencsch = emvencsch;
    }

    public Integer getEmviccver() {
        return emviccver;
    }

    public void setEmviccver(Integer emviccver) {
        this.emviccver = emviccver;
    }

    public Integer getEmvoda() {
        return emvoda;
    }

    public void setEmvoda(Integer emvoda) {
        this.emvoda = emvoda;
    }

    public Double getAmtrtiloc() {
        return amtrtiloc;
    }

    public void setAmtrtiloc(Double amtrtiloc) {
        this.amtrtiloc = amtrtiloc;
    }

    public String getCurrtiloc() {
        return currtiloc;
    }

    public void setCurrtiloc(String currtiloc) {
        this.currtiloc = currtiloc == null ? null : currtiloc.trim();
    }

    public Double getAmtrtiint() {
        return amtrtiint;
    }

    public void setAmtrtiint(Double amtrtiint) {
        this.amtrtiint = amtrtiint;
    }

    public String getCurrtiint() {
        return currtiint;
    }

    public void setCurrtiint(String currtiint) {
        this.currtiint = currtiint == null ? null : currtiint.trim();
    }

    public Integer getIccpinfmt() {
        return iccpinfmt;
    }

    public void setIccpinfmt(Integer iccpinfmt) {
        this.iccpinfmt = iccpinfmt;
    }

    public Integer getScriptset() {
        return scriptset;
    }

    public void setScriptset(Integer scriptset) {
        this.scriptset = scriptset;
    }

    public String getDenymcc() {
        return denymcc;
    }

    public void setDenymcc(String denymcc) {
        this.denymcc = denymcc == null ? null : denymcc.trim();
    }

    public Double getMaxload() {
        return maxload;
    }

    public void setMaxload(Double maxload) {
        this.maxload = maxload;
    }

    public Double getMaxbal() {
        return maxbal;
    }

    public void setMaxbal(Double maxbal) {
        this.maxbal = maxbal;
    }

    public String getRiskProfCode() {
        return riskProfCode;
    }

    public void setRiskProfCode(String riskProfCode) {
        this.riskProfCode = riskProfCode == null ? null : riskProfCode.trim();
    }

    public String getAuthParams() {
        return authParams;
    }

    public void setAuthParams(String authParams) {
        this.authParams = authParams == null ? null : authParams.trim();
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }

    public String getIcckeydev() {
        return icckeydev;
    }

    public void setIcckeydev(String icckeydev) {
        this.icckeydev = icckeydev == null ? null : icckeydev.trim();
    }

    public Double getPreauthBlkLim() {
        return preauthBlkLim;
    }

    public void setPreauthBlkLim(Double preauthBlkLim) {
        this.preauthBlkLim = preauthBlkLim;
    }

    public Double getPreauthTotLim() {
        return preauthTotLim;
    }

    public void setPreauthTotLim(Double preauthTotLim) {
        this.preauthTotLim = preauthTotLim;
    }

    public String getCatParams() {
        return catParams;
    }

    public void setCatParams(String catParams) {
        this.catParams = catParams == null ? null : catParams.trim();
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

    public Integer getPreauthExpiry() {
        return preauthExpiry;
    }

    public void setPreauthExpiry(Integer preauthExpiry) {
        this.preauthExpiry = preauthExpiry;
    }

    public Long getCrdstatustranssetId() {
        return crdstatustranssetId;
    }

    public void setCrdstatustranssetId(Long crdstatustranssetId) {
        this.crdstatustranssetId = crdstatustranssetId;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public Integer getMinvalduriss() {
        return minvalduriss;
    }

    public void setMinvalduriss(Integer minvalduriss) {
        this.minvalduriss = minvalduriss;
    }

    public Integer getMinvaldurdmg() {
        return minvaldurdmg;
    }

    public void setMinvaldurdmg(Integer minvaldurdmg) {
        this.minvaldurdmg = minvaldurdmg;
    }

    public Integer getRnwpstexpperiod() {
        return rnwpstexpperiod;
    }

    public void setRnwpstexpperiod(Integer rnwpstexpperiod) {
        this.rnwpstexpperiod = rnwpstexpperiod;
    }

    public Integer getRnwpreexpperiod() {
        return rnwpreexpperiod;
    }

    public void setRnwpreexpperiod(Integer rnwpreexpperiod) {
        this.rnwpreexpperiod = rnwpreexpperiod;
    }

    public Integer getRnwearlyperiod() {
        return rnwearlyperiod;
    }

    public void setRnwearlyperiod(Integer rnwearlyperiod) {
        this.rnwearlyperiod = rnwearlyperiod;
    }

    public String getUsrdata1() {
        return usrdata1;
    }

    public void setUsrdata1(String usrdata1) {
        this.usrdata1 = usrdata1 == null ? null : usrdata1.trim();
    }

    public String getUsrdata2() {
        return usrdata2;
    }

    public void setUsrdata2(String usrdata2) {
        this.usrdata2 = usrdata2 == null ? null : usrdata2.trim();
    }

    public Long getCrdplstctypId() {
        return crdplstctypId;
    }

    public void setCrdplstctypId(Long crdplstctypId) {
        this.crdplstctypId = crdplstctypId;
    }
}