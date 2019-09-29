package cn.yufu.bak.entity;

import java.math.BigDecimal;

public class ViewAccnoAvlBal {
    private String cardbin;

    private String pan;

    private String statcode;

    private BigDecimal av01;

    private BigDecimal av02;

    private BigDecimal av04;

    private BigDecimal av09;

    private BigDecimal totalBal;
    
    private BigDecimal cardNum;
    
    private String accnoType;
    
    private BigDecimal avlThreshold;
    
    private String symbol;		//0--大于，1--大于等于，2--等于，3-小于等于，4--小于
    
    private boolean flag;		// true -- 限制卡BIN

    public String getCardbin() {
        return cardbin;
    }

    public void setCardbin(String cardbin) {
        this.cardbin = cardbin == null ? null : cardbin.trim();
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    public String getStatcode() {
        return statcode;
    }

    public void setStatcode(String statcode) {
        this.statcode = statcode == null ? null : statcode.trim();
    }

    public BigDecimal getAv01() {
        return av01;
    }

    public void setAv01(BigDecimal av01) {
        this.av01 = av01;
    }

    public BigDecimal getAv02() {
        return av02;
    }

    public void setAv02(BigDecimal av02) {
        this.av02 = av02;
    }

    public BigDecimal getAv04() {
        return av04;
    }

    public void setAv04(BigDecimal av04) {
        this.av04 = av04;
    }

    public BigDecimal getAv09() {
        return av09;
    }

    public void setAv09(BigDecimal av09) {
        this.av09 = av09;
    }

    public BigDecimal getTotalBal() {
        return totalBal;
    }

    public void setTotalBal(BigDecimal totalBal) {
        this.totalBal = totalBal;
    }

	public BigDecimal getCardNum() {
		return cardNum;
	}

	public void setCardNum(BigDecimal cardNum) {
		this.cardNum = cardNum;
	}

	public String getAccnoType() {
		return accnoType;
	}

	public void setAccnoType(String accnoType) {
		this.accnoType = accnoType;
	}

	public BigDecimal getAvlThreshold() {
		return avlThreshold;
	}

	public void setAvlThreshold(BigDecimal avlThreshold) {
		this.avlThreshold = avlThreshold;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
    
}