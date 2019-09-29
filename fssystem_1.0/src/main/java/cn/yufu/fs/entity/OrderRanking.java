package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class OrderRanking {
    private String batchId;	//批次号

    private String companyName;	//公司名称

    private String orderId;	//订单号

    private String cellPhone;	//手机号

    private String orderDate;	//订单日期

    private Long panAccount;  //每订单张数   购卡张数

    private BigDecimal amtEachCrd;  //每张卡的金额,订单总额

    private String merNo;

    private String cardNun;

    private BigDecimal tranAmt;	//消费总额

    private String freeField1;

    private String freeField2;

    private String freeField3;

    private String freeField4;

    private String freeField5;
    
	private String transactiondateStart;// 消费起始日期
	
	private String transactiondateEnd;// 消费截止日期
	
	private String orderSum;//订单数
	
	private String customCardSum;//消费的卡张数
	
	private String customSum;//消费笔数

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate == null ? null : orderDate.trim();
    }

    public Long getPanAccount() {
        return panAccount;
    }

    public void setPanAccount(Long panAccount) {
        this.panAccount = panAccount;
    }

    public BigDecimal getAmtEachCrd() {
        return amtEachCrd;
    }

    public void setAmtEachCrd(BigDecimal amtEachCrd) {
        this.amtEachCrd = amtEachCrd;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo == null ? null : merNo.trim();
    }

    public String getCardNun() {
        return cardNun;
    }

    public void setCardNun(String cardNun) {
        this.cardNun = cardNun == null ? null : cardNun.trim();
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
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

	public String getTransactiondateStart() {
		return transactiondateStart;
	}

	public void setTransactiondateStart(String transactiondateStart) {
		this.transactiondateStart = transactiondateStart;
	}

	public String getTransactiondateEnd() {
		return transactiondateEnd;
	}

	public void setTransactiondateEnd(String transactiondateEnd) {
		this.transactiondateEnd = transactiondateEnd;
	}

	public String getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(String orderSum) {
		this.orderSum = orderSum;
	}

	public String getCustomCardSum() {
		return customCardSum;
	}

	public void setCustomCardSum(String customCardSum) {
		this.customCardSum = customCardSum;
	}

	public String getCustomSum() {
		return customSum;
	}

	public void setCustomSum(String customSum) {
		this.customSum = customSum;
	}
    
}