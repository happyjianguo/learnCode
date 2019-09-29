package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class CardTypeBook {
    private String cardTypeId;

    private BigDecimal feeOrder;  //分润比例

    private String comments;

    private String comments1;

    private String comments2;

    private String comments3;
    
    private String flag;

    public String getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId == null ? null : cardTypeId.trim();
    }

    public BigDecimal getFeeOrder() {
        return feeOrder;
    }

    public void setFeeOrder(BigDecimal feeOrder) {
        this.feeOrder = feeOrder;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getComments1() {
        return comments1;
    }

    public void setComments1(String comments1) {
        this.comments1 = comments1 == null ? null : comments1.trim();
    }

    public String getComments2() {
        return comments2;
    }

    public void setComments2(String comments2) {
        this.comments2 = comments2 == null ? null : comments2.trim();
    }

    public String getComments3() {
        return comments3;
    }

    public void setComments3(String comments3) {
        this.comments3 = comments3 == null ? null : comments3.trim();
    }

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}
    
}