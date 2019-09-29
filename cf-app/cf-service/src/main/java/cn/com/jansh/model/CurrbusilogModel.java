package cn.com.jansh.model;

import java.util.List;

import cn.com.jansh.entity.wsfdn.CfAccessclientEntity;
import cn.com.jansh.entity.wsfdn.CfCurrbusilogEntity;
import cn.com.jansh.entity.wsfdn.CfCustomerEntity;
import cn.com.jansh.entity.wsfdn.CfProvinceEntity;
import cn.com.jansh.entity.wsfdn.CfSupplierpriceEntity;

/**
 * 流水model
 * 
 * @author longlong
 */
public class CurrbusilogModel {
	@Override
	public String toString() {
		return "CurrbusilogModel [bizid=" + bizid + ", start=" + start + ", count=" + count + ", length=" + length
				+ ", phone=" + phone + ", cid=" + cid + ", cname=" + cname + ", acid=" + acid + ", acname=" + acname
				+ ", ispno=" + ispno + ", ipstype=" + ipstype + ", province=" + province + ", facevalue=" + facevalue
				+ ", orderid=" + orderid + ", cporderno=" + cporderno + ", systransno=" + systransno + ", responsecode="
				+ responsecode + ", spprice=" + spprice + ", spno=" + spno + ", apprice=" + apprice + ", apno=" + apno
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + ", begintime=" + begintime
				+ ", endtime=" + endtime + ", status=" + status + ", pname=" + pname + ", cfCustomerList="
				+ cfCustomerList + ", accessclientList=" + accessclientList + ", provinceList=" + provinceList
				+ ", supplierpriceList=" + supplierpriceList + ", currbusiloglist=" + currbusiloglist + "]";
	}

	/**  */
    private String bizid;
    /** 当前页 */
    private String start;
    /** 总页数 */
    private String count;
    /** 每页显示数*/
    private String length;
    /**  */
    private String phone;
    /**客户id   */
    private String cid;
	private String cname;
	/** 接入者id*/
	private String acid;
	private String acname;
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
    /**  
     * 创建更新时间
     * */
    private String createtime;
    private String updatetime;
    /**
     * 开始结束时间
     */
    private String begintime;
    private String endtime;
    /** 0:待发送;1:成功;2:失败;3:通讯异常;4:已受理 */
    private String status;
    /** 省份名称 */
    private String pname;
    /**
	 * 客户列表
	 */
	private List<CfCustomerEntity> cfCustomerList;
	/**
	 * 接入者列表
	 */
	private List<CfAccessclientEntity> accessclientList;
	/**
	 * 省份列表
	 */
	private List<CfProvinceEntity> provinceList;
	/**
	 * 供应商列表
	 */
	private List<CfSupplierpriceEntity> supplierpriceList;
	/**
	 * 流水列表
	 */
	private List<CfCurrbusilogEntity> currbusiloglist;
	
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getAcid() {
		return acid;
	}

	public void setAcid(String acid) {
		this.acid = acid;
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

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

	public List<CfCurrbusilogEntity> getCurrbusiloglist() {
		return currbusiloglist;
	}

	public void setCurrbusiloglist(List<CfCurrbusilogEntity> currbusiloglist) {
		this.currbusiloglist = currbusiloglist;
	}

	public List<CfSupplierpriceEntity> getSupplierpriceList() {
		return supplierpriceList;
	}

	public void setSupplierpriceList(List<CfSupplierpriceEntity> supplierpriceList) {
		this.supplierpriceList = supplierpriceList;
	}

	public List<CfProvinceEntity> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<CfProvinceEntity> provinceList) {
		this.provinceList = provinceList;
	}


	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
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

	public List<CfCustomerEntity> getCfCustomerList() {
		return cfCustomerList;
	}

	public void setCfCustomerList(List<CfCustomerEntity> cfCustomerList) {
		this.cfCustomerList = cfCustomerList;
	}

	public List<CfAccessclientEntity> getAccessclientList() {
		return accessclientList;
	}

	public void setAccessclientList(List<CfAccessclientEntity> accessclientList) {
		this.accessclientList = accessclientList;
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
    
}