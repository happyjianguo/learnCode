package cn.com.jansh.entity.wsfdn;
/**
 * 单笔充值记录表(CF_Currbusilog)
 * 
 * @author longlong
 */
public class CfCurrbusilogEntity {
    
	
	/**  */
    private String bizid;
    
    /** 客户名称 */
    private String cname;
    
    /** 接入者名称 */
    private String acname;
    
    /**  */
    private String phone;
    
    /**  */
    private String ispno;
    
    /** 套餐类型 */
    private String ipstype;
    
    /**  */
    private String province;
    
    /**  */
    private String facevalue;
    
    /**  */
    private String orderid;
    
    /** CP订购编号 */
    private String cporderno;
    
    /**  */
    private String systransno;
    
    /** 操作结果编码 */
    private String responsecode;
    
    /** 供应商报价 */
    private String spprice;
    
    /** 供应商报价编号 */
    private String spno;
    
    /** 接入者报价 */
    private String apprice;
    
    /** 接入者报价编号· */
    private String apno;
    
    /**  */
    private String createtime;
    
    /**  */
    private String updatetime;
    
    
    /** 0:待发送;1:成功;2:失败;3:通讯异常;4:已受理 */
    private String status;
    
    
    /**  */
    private String begintime;
    
    /**  */
    private String endtime;
    /**客户id   */
    private String cid;
    
    /**接入者id   */
    private String acid;
    
    private String pname;
    
    /**
     * 要查询的时间
     */
    private String querytime;

    /** 版本号 */
    /** 成本额  */
    private String totalspprice;
    /** 销售额  */
    private String totalapprice;
    /** 利润  */
    private String profit;
    
	public String getQuerytime() {
		return querytime;
	}

	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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
    public String getBizid() {
        return this.bizid;
    }
     
    /**
     * 设置
     * 
     * @param bizid
     *          
     */
    public void setBizid(String bizid) {
        this.bizid = bizid;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getPhone() {
        return this.phone;
    }
     
    /**
     * 设置
     * 
     * @param phone
     *          
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取套餐类型
     * 
     * @return 套餐类型
     */
    public String getIpstype() {
        return this.ipstype;
    }
     
    /**
     * 设置套餐类型
     * 
     * @param ipstype
     *          套餐类型
     */
    public void setIpstype(String ipstype) {
        this.ipstype = ipstype;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getProvince() {
        return this.province;
    }
     
    /**
     * 设置
     * 
     * @param province
     *          
     */
    public void setProvince(String province) {
        this.province = province;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getFacevalue() {
        return this.facevalue;
    }
     
    /**
     * 设置
     * 
     * @param facevalue
     *          
     */
    public void setFacevalue(String facevalue) {
        this.facevalue = facevalue;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getOrderid() {
        return this.orderid;
    }
     
    /**
     * 设置
     * 
     * @param orderid
     *          
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    
    /**
     * 获取CP订购编号
     * 
     * @return CP订购编号
     */
    public String getCporderno() {
        return this.cporderno;
    }
     
    /**
     * 设置CP订购编号
     * 
     * @param cporderno
     *          CP订购编号
     */
    public void setCporderno(String cporderno) {
        this.cporderno = cporderno;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getSystransno() {
        return this.systransno;
    }
     
    /**
     * 设置
     * 
     * @param systransno
     *          
     */
    public void setSystransno(String systransno) {
        this.systransno = systransno;
    }
    
    /**
     * 获取操作结果编码
     * 
     * @return 操作结果编码
     */
    public String getResponsecode() {
        return this.responsecode;
    }
     
    /**
     * 设置操作结果编码
     * 
     * @param responsecode
     *          操作结果编码
     */
    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }
    
    /**
     * 获取供应商报价
     * 
     * @return 供应商报价
     */
    public String getSpprice() {
        return this.spprice;
    }
     
    /**
     * 设置供应商报价
     * 
     * @param spprice
     *          供应商报价
     */
    public void setSpprice(String spprice) {
        this.spprice = spprice;
    }
    
    /**
     * 获取供应商报价编号
     * 
     * @return 供应商报价编号
     */
    public String getSpno() {
        return this.spno;
    }
     
    /**
     * 设置供应商报价编号
     * 
     * @param spno
     *          供应商报价编号
     */
    public void setSpno(String spno) {
        this.spno = spno;
    }
    
    /**
     * 获取接入者报价
     * 
     * @return 接入者报价
     */
    public String getApprice() {
        return this.apprice;
    }
     
    /**
     * 设置接入者报价
     * 
     * @param apprice
     *          接入者报价
     */
    public void setApprice(String apprice) {
        this.apprice = apprice;
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
    
    /**
     * 获取0:待发送;1:成功;2:失败;3:通讯异常;4:已受理
     * 
     * @return 0:待发送;1:成功;2:失败;3:通讯异常;4:已受理
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置0:待发送;1:成功;2:失败;3:通讯异常;4:已受理
     * 
     * @param status
     *          0:待发送;1:成功;2:失败;3:通讯异常;4:已受理
     */
    public void setStatus(String status) {
        this.status = status;
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
    
}