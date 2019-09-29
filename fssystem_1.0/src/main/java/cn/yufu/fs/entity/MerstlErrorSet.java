package cn.yufu.fs.entity;

public class MerstlErrorSet {
	
	private String id;
	
    private String errorCardNum;

    private String errorCardAmt;
    
    private String numFlag;
    
    private String amtFlag;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
    }

	public String getErrorCardNum() {
        return errorCardNum;
    }

    public void setErrorCardNum(String errorCardNum) {
        this.errorCardNum = errorCardNum == null ? null : errorCardNum.trim();
    }

    public String getErrorCardAmt() {
        return errorCardAmt;
    }

    public void setErrorCardAmt(String errorCardAmt) {
        this.errorCardAmt = errorCardAmt == null ? null : errorCardAmt.trim();
    }

	public String getNumFlag() {
		return numFlag;
	}

	public void setNumFlag(String numFlag) {
		this.numFlag = numFlag;
	}

	public String getAmtFlag() {
		return amtFlag;
	}

	public void setAmtFlag(String amtFlag) {
		this.amtFlag = amtFlag;
	}
    
}