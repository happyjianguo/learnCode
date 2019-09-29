package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

public class Crdopen extends DataEntity<Crdopen>{
	private static final long serialVersionUID = 1L;

	private String openId;

    private String operator;

    private String indentNumber;

    private String createTime;

    private String updateTime;

    private String reserved;

    private String reserved1;

    private String reserved2;
    
    private String panStart;

    private String panEnd;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getIndentNumber() {
        return indentNumber;
    }

    public void setIndentNumber(String indentNumber) {
        this.indentNumber = indentNumber == null ? null : indentNumber.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved == null ? null : reserved.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }
    
    public String getPanStart() {
        return panStart;
    }

    public void setPanStart(String panStart) {
        this.panStart = panStart == null ? null : panStart.trim();
    }

    public String getPanEnd() {
        return panEnd;
    }

    public void setPanEnd(String panEnd) {
        this.panEnd = panEnd == null ? null : panEnd.trim();
    }
}