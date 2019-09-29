package cn.com.jansh.entity.wsfdn;
/**
 * 接入者表(CF_Ofbill)
 * @author bianj
 * @version 1.0.0 2016-04-20
 */
public class CfOfbillEntity {
    /** CP流水号 */
    private String cpno;
    
    /** SP订单号 */
    private String spno;
    
    /** 商品编号 */
    private String num;
    
    /** 商品数量 */
    private String size;
    
    /** 充值账号 */
    private String phone;
    /** 平台状态 */
    private String platstatus;
    /** 欧飞状态 */
    private String ofstatus;
    /** 接入者名称 */
    private String acname;
    
    /** 订单金额 */
    private String oprice;
    
    /** 订单时间 */
    private String otime;
    
    /** 订单状态 */
    private String status;
    
    /** 开始时间  */
    private String starttime;
    
	/** 结束时间  */
    private String endtime;
    

    public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
     * 获取CP流水号
     * 
     * @return CP流水号
     */
    public String getCpno() {
        return this.cpno;
    }
     
    /**
     * 设置CP流水号
     * 
     * @param cpno
     *          CP流水号
     */
    public void setCpno(String cpno) {
        this.cpno = cpno;
    }
    
    /**
     * 获取SP订单号
     * 
     * @return SP订单号
     */
    public String getSpno() {
        return this.spno;
    }
     
    /**
     * 设置SP订单号
     * 
     * @param spno
     *          SP订单号
     */
    public void setSpno(String spno) {
        this.spno = spno;
    }
    
    /**
     * 获取商品编号
     * 
     * @return 商品编号
     */
    public String getNum() {
        return this.num;
    }
     
    /**
     * 设置商品编号
     * 
     * @param num
     *          商品编号
     */
    public void setNum(String num) {
        this.num = num;
    }
    
    /**
     * 获取商品数量
     * 
     * @return 商品数量
     */
    public String getSize() {
        return this.size;
    }
     
    /**
     * 设置商品数量
     * 
     * @param size
     *          商品数量
     */
    public void setSize(String size) {
        this.size = size;
    }
    
    /**
     * 获取充值账号
     * 
     * @return 充值账号
     */
    public String getPhone() {
        return this.phone;
    }
     
    /**
     * 设置充值账号
     * 
     * @param phone
     *          充值账号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * 获取订单金额
     * 
     * @return 订单金额
     */
    public String getOprice() {
        return this.oprice;
    }
     
    /**
     * 设置订单金额
     * 
     * @param oprice
     *          订单金额
     */
    public void setOprice(String oprice) {
        this.oprice = oprice;
    }
    
    /**
     * 获取订单时间
     * 
     * @return 订单时间
     */
    public String getOtime() {
        return this.otime;
    }
     
    /**
     * 设置订单时间
     * 
     * @param otime
     *          订单时间
     */
    public void setOtime(String otime) {
        this.otime = otime;
    }
    
    /**
     * 获取订单状态
     * 
     * @return 订单状态
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置订单状态
     * 
     * @param status
     *          订单状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

	public String getPlatstatus() {
		return platstatus;
	}

	public void setPlatstatus(String platstatus) {
		this.platstatus = platstatus;
	}

	public String getOfstatus() {
		return ofstatus;
	}

	public void setOfstatus(String ofstatus) {
		this.ofstatus = ofstatus;
	}

	public String getAcname() {
		return acname;
	}

	public void setAcname(String acname) {
		this.acname = acname;
	}
    
    
}