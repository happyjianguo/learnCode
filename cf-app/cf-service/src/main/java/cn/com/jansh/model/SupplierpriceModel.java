package cn.com.jansh.model;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfProvinceEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;

public class SupplierpriceModel {
	
	/** 报价列表 */
	public List<CfSupplierpriceEntity> CfSupplierpriceList;
	
	/** 供应商 */
	public List<CfSupplierEntity> supplierList;
	
	/** 省份*/
	public List<CfProvinceEntity> provinceList;
	
	/** 报价ID */
    private String id;
    
    /** 套餐名称 */
    private String pname;
    
    /** 运营商编号 */
    private String ispno;
    
    /** 套餐类型 */
    private String ipstype;
    
    /** 省份 */
    private String pno;
    
    /** 面额 */
    private String size;
    
    /** 供应商编号 */
    private String sid;
    
    /** 供应商报价 */
    private String price;
    
    /** 开始时间 */
    private String begintime;
    
    /** 结束时间 */
    private String endtime;
    
    /** 状态 */
    private String status;
    /** cp订购编码 */
    private String cpordernos;
    
    /** 套餐类型*/
    private String company;
    
      
    public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCpordernos() {
		return cpordernos;
	}

	public void setCpordernos(String cpordernos) {
		this.cpordernos = cpordernos;
	}
    public List<CfProvinceEntity> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<CfProvinceEntity> provinceList) {
		this.provinceList = provinceList;
	}
    public List<CfSupplierpriceEntity> getCfSupplierpriceList() {
		return CfSupplierpriceList;
	}

	public void setCfSupplierpriceList(List<CfSupplierpriceEntity> cfSupplierpriceList) {
		CfSupplierpriceList = cfSupplierpriceList;
	}
    
	public List<CfSupplierEntity> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<CfSupplierEntity> supplierList) {
		this.supplierList = supplierList;
	}
    /**
     * 获取报价ID
     * 
     * @return 报价ID
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置报价ID
     * 
     * @param id
     *          报价ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取套餐名称
     * 
     * @return 套餐名称
     */
    public String getPname() {
        return this.pname;
    }
     
    /**
     * 设置套餐名称
     * 
     * @param pname
     *          套餐名称
     */
    public void setPname(String pname) {
        this.pname = pname;
    }
    
    /**
     * 获取运营商编号
     * 
     * @return 运营商编号
     */
    public String getIspno() {
        return this.ispno;
    }
     
    /**
     * 设置运营商编号
     * 
     * @param ispno
     *          运营商编号
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
     * 获取省份
     * 
     * @return 省份
     */
    public String getPno() {
        return this.pno;
    }
     
    /**
     * 设置省份
     * 
     * @param pno
     *          省份
     */
    public void setPno(String pno) {
        this.pno = pno;
    }
    
    /**
     * 获取面额
     * 
     * @return 面额
     */
    public String getSize() {
        return this.size;
    }
     
    /**
     * 设置面额
     * 
     * @param size
     *          面额
     */
    public void setSize(String size) {
        this.size = size;
    }
    
    /**
     * 获取供应商编号
     * 
     * @return 供应商编号
     */
    public String getSid() {
        return this.sid;
    }
     
    /**
     * 设置供应商编号
     * 
     * @param sid
     *          供应商编号
     */
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    /**
     * 获取供应商报价
     * 
     * @return 供应商报价
     */
    public String getPrice() {
        return this.price;
    }
     
    /**
     * 设置供应商报价
     * 
     * @param price
     *          供应商报价
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
    /**
     * 获取开始时间
     * 
     * @return 开始时间
     */
    public String getBegintime() {
        return this.begintime;
    }
     
    /**
     * 设置开始时间
     * 
     * @param begintime
     *          开始时间
     */
    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }
    
    /**
     * 获取结束时间
     * 
     * @return 结束时间
     */
    public String getEndtime() {
        return this.endtime;
    }
     
    /**
     * 设置结束时间
     * 
     * @param endtime
     *          结束时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
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
		return "SupplierpriceModel [CfSupplierpriceList=" + CfSupplierpriceList + ", supplierList=" + supplierList
				+ ", provinceList=" + provinceList + ", id=" + id + ", pname=" + pname + ", ispno=" + ispno
				+ ", ipstype=" + ipstype + ", pno=" + pno + ", size=" + size + ", sid=" + sid + ", price=" + price
				+ ", begintime=" + begintime + ", endtime=" + endtime + ", status=" + status + ", cpordernos="
				+ cpordernos + "]";
	}
    
}
