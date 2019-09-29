package cn.com.jansh.entity.wsfdn;
/**
 * 接入者报价
 * @author duanmuyn
 */
public class CfAccesspriceEntity {
    /** 接入者报价编号 */
    private String apid;
    
    /** 接入者ID */
    private String acid;
    
    /** 报价 */
    private String price;
    
    /**  */
    private String ispno;
    
    /**  */
    private String ipstype;
    
    /**  */
    private String province;
    
    /**  */
    private String facevalue;
    
    /** 状态 */
    private String status;
    
    /** 运营商*/
    private String acname;
    
	/** 省份*/
    private String pname;
    
    public String getAcname() {
		return acname;
	}

	public void setAcname(String acname) {
		this.acname = acname;
	}
    public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
     * 获取接入者报价编号
     * 
     * @return 接入者报价编号
     */
    public String getApid() {
        return this.apid;
    }
     
    /**
     * 设置接入者报价编号
     * 
     * @param apid
     *          接入者报价编号
     */
    public void setApid(String apid) {
        this.apid = apid;
    }
    
    /**
     * 获取接入者ID
     * 
     * @return 接入者ID
     */
    public String getAcid() {
        return this.acid;
    }
     
    /**
     * 设置接入者ID
     * 
     * @param acid
     *          接入者ID
     */
    public void setAcid(String acid) {
        this.acid = acid;
    }
    
    /**
     * 获取报价
     * 
     * @return 报价
     */
    public String getPrice() {
        return this.price;
    }
     
    /**
     * 设置报价
     * 
     * @param price
     *          报价
     */
    public void setPrice(String price) {
        this.price = price;
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
     * 获取
     * 
     * @return 
     */
    public String getIpstype() {
        return this.ipstype;
    }
     
    /**
     * 设置
     * 
     * @param ipstype
     *          
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
     * 获取状态
     * 
     * @return 状态
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态
     * 
     * @param status
     *          状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "CfAccesspriceEntity [apid=" + apid + ", acid=" + acid + ", price=" + price + ", ispno=" + ispno
				+ ", ipstype=" + ipstype + ", province=" + province + ", facevalue=" + facevalue + ", status=" + status
				+ ", acname=" + acname + ", pname=" + pname + "]";
	}
    
}