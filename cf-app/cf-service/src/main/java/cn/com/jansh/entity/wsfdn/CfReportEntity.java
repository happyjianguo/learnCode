package cn.com.jansh.entity.wsfdn;

public class CfReportEntity {
	


	/**
     * 要查询的时间
     */
    private String querytime;

    /** 客户名称 */
    private String cname;
    
    /** 接入者名称 */
    private String acname;
    /** 运营商类型 */
    private String ispno;
	/** 成本额  */
    private String totalspprice;
    /** 销售额  */
    private String totalapprice;
    /** 利润  */
    private String profit;  
    
    /** 接入者报价编号· */
    private String apno;
    
    /**  */
    private String createtime;
    
    /**  */
    private String updatetime;    
    
    /**  */
    private String begintime;
    
    /**  */
    private String endtime;
    /**客户id   */
    private String cid;
    
    /**接入者id   */
    private String acid;
    private String id;
    private String status;

	public String getQuerytime() {
		return querytime;
	}

	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAcid() {
		return acid;
	}

	public void setAcid(String acid) {
		this.acid = acid;
	}


	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getAcname() {
		return acname;
	}

	public void setAcname(String acname) {
		this.acname = acname;
	}

    /**
     * 获取
     * 
     * @return 
     */
    public String getIspno() {
        return this.ispno;
    }
     
    /**
     * 设置
     * 
     * @param ispno
     *          
     */
    public void setIspno(String ispno) {
        this.ispno = ispno;
    }
    
    /**
     * 获取接入者报价编号·
     * 
     * @return 接入者报价编号·
     */
    public String getApno() {
        return this.apno;
    }
     
    /**
     * 设置接入者报价编号·
     * 
     * @param apno
     *          接入者报价编号·
     */
    public void setApno(String apno) {
        this.apno = apno;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getCreatetime() {
        return this.createtime;
    }
     
    /**
     * 设置
     * 
     * @param createtime
     *          
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
     
    /**
     * 设置
     * 
     * @param updatetime
     *          
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    
	public String getTotalspprice() {
		return totalspprice;
	}

	public void setTotalspprice(String totalspprice) {
		this.totalspprice = totalspprice;
	}

	public String getTotalapprice() {
		return totalapprice;
	}

	public void setTotalapprice(String totalapprice) {
		this.totalapprice = totalapprice;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
