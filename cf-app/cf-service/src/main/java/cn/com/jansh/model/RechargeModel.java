package cn.com.jansh.model;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.entity.wsfdn.CfProvinceEntity;

public class RechargeModel {
    
	/** 接入者集合*/
	private List<CfAccessclientEntity> priceList;
	
	/** 省份*/
	public List<CfProvinceEntity> provinceList;
	
	/** 客户表*/
	private List<CfCustomerEntity> cfCustomerList;

	/**客户名 */
    private String cname;
    /**接入者名称 */
    private String acname;
    /**省份名称 */
    private String pname;
	 public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	/** 充值id */
    private String id;
    
    /** 运营商 */
    private String ispno;
    
    /** 套餐类型 */
    private String isptype;
    
    /** 面值 */
    private String facevalue;
    
    /** 手机号 */
    private String phone;
    
    /** 流水号 */
    private String bizid;
    
    /** 发送状态 */
    private String status;
    
    /** 省份 */
    private String province;
    
    /** 客户 */
    private String cid;
    
    /** 接入者 */
    private String acid;
          
    /**
     * 获取充值id
     * 
     * @return 充值id
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置充值id
     * 
     * @param id
     *          充值id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取运营商
     * 
     * @return 运营商
     */
    public String getIspno() {
        return this.ispno;
    }
     
    /**
     * 设置运营商
     * 
     * @param ispno
     *          运营商
     */
    public void setIspno(String ispno) {
        this.ispno = ispno;
    }
    
    /**
     * 获取套餐类型
     * 
     * @return 套餐类型
     */
    public String getIsptype() {
        return this.isptype;
    }
     
    /**
     * 设置套餐类型
     * 
     * @param isptype
     *          套餐类型
     */
    public void setIsptype(String isptype) {
        this.isptype = isptype;
    }
    
    /**
     * 获取面值
     * 
     * @return 面值
     */
    public String getFacevalue() {
        return this.facevalue;
    }
     
    /**
     * 设置面值
     * 
     * @param facevalue
     *          面值
     */
    public void setFacevalue(String facevalue) {
        this.facevalue = facevalue;
    }
    
    /**
     * 获取手机号
     * 
     * @return 手机号
     */
    public String getPhone() {
        return this.phone;
    }
     
    /**
     * 设置手机号
     * 
     * @param phone
     *          手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * 获取流水号
     * 
     * @return 流水号
     */
    public String getBizid() {
        return this.bizid;
    }
     
    /**
     * 设置流水号
     * 
     * @param bizid
     *          流水号
     */
    public void setBizid(String bizid) {
        this.bizid = bizid;
    }
    
    /**
     * 获取发送状态
     * 
     * @return 发送状态
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置发送状态
     * 
     * @param status
     *          发送状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * 获取省份
     * 
     * @return 省份
     */
    public String getProvince() {
        return this.province;
    }
     
    /**
     * 设置省份
     * 
     * @param province
     *          省份
     */
    public void setProvince(String province) {
        this.province = province;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getCid() {
        return this.cid;
    }
     
    /**
     * 设置
     * 
     * @param cid
     *          
     */
    public void setCid(String cid) {
        this.cid = cid;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getAcid() {
        return this.acid;
    }
     
    /**
     * 设置
     * 
     * @param acid
     *          
     */
    public void setAcid(String acid) {
        this.acid = acid;
    }
	
    public List<CfCustomerEntity> getCfCustomerList() {
		return cfCustomerList;
	}

	public void setCfCustomerList(List<CfCustomerEntity> cfCustomerList) {
		this.cfCustomerList = cfCustomerList;
	}
    
	public List<CfAccessclientEntity> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<CfAccessclientEntity> priceList) {
		this.priceList = priceList;
	}

	public List<CfProvinceEntity> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<CfProvinceEntity> provinceList) {
		this.provinceList = provinceList;
	}

	@Override
	public String toString() {
		return "RechargeModel [priceList=" + priceList + ", provinceList=" + provinceList + ", cfCustomerList="
				+ cfCustomerList + ", cname=" + cname + ", acname=" + acname + ", pname=" + pname + ", id=" + id
				+ ", ispno=" + ispno + ", isptype=" + isptype + ", facevalue=" + facevalue + ", phone=" + phone
				+ ", bizid=" + bizid + ", status=" + status + ", province=" + province + ", cid=" + cid + ", acid="
				+ acid + "]";
	}
}
