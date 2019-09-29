package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class FukaSalepoint {
	private String showname; //显示名称，格式=分公司编码：分公司名-售卡网点编码：售卡网点名
	
    private BigDecimal pointId;

    private BigDecimal cid;

    private String pointcode;

    private String pointname;

    private String freeField1;

    private String freeField2;

    private String freeField3;

    private String freeField4;

    private String freeField5;

    public BigDecimal getPointId() {
        return pointId;
    }

    public void setPointId(BigDecimal pointId) {
        this.pointId = pointId;
    }

    public BigDecimal getCid() {
        return cid;
    }

    public void setCid(BigDecimal cid) {
        this.cid = cid;
    }

    public String getPointcode() {
        return pointcode;
    }

    public void setPointcode(String pointcode) {
        this.pointcode = pointcode == null ? null : pointcode.trim();
    }

    public String getPointname() {
        return pointname;
    }

    public void setPointname(String pointname) {
        this.pointname = pointname == null ? null : pointname.trim();
    }

    public String getFreeField1() {
        return freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1 == null ? null : freeField1.trim();
    }

    public String getFreeField2() {
        return freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2 == null ? null : freeField2.trim();
    }

    public String getFreeField3() {
        return freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3 == null ? null : freeField3.trim();
    }

    public String getFreeField4() {
        return freeField4;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4 == null ? null : freeField4.trim();
    }

    public String getFreeField5() {
        return freeField5;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5 == null ? null : freeField5.trim();
    }

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}
}