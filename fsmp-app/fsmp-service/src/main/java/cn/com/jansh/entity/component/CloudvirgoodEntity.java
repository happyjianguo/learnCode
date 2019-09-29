/**
 * CloudvirgoodEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月21日
 */
package cn.com.jansh.entity.component;

/**
 * 订单系统-奖品实体
 * @author duanmuyn
 * @version 1.0
 */
public class CloudvirgoodEntity{

	/** 奖品ID */
    private String id;
    
    /**
     * 套餐编号
     */
    private String apid;
    
	/** 奖品名称 */
    private String prizename;
    
    /** 奖品类型 */
    private String prizestyle;
    
    /** 运营商 */
    private String carrier;
    
    /** 套餐面值 */
    private String combovalue;
    
    /** 奖品价格 */
    private int prizeprice;
    
    /** 面值单位 */
    private String pervalue;
    
    /** 图片地址 */
    private String imagepath;
    /**
     * 充值地区状态
     */
    private String urange;
	/**
     * 归属地
     */
    private String province;
    
    /** 状态 */
    private String status;
    
    /** 奖品备注 */
    private String note;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    
    /**
   	 * @return the apid
   	 */
   	public String getApid() {
   		return apid;
   	}
   	/**
   	 * @param apid the apid to set
   	 */
   	public void setApid(String apid) {
   		this.apid = apid;
   	}
    
    /**
	 * @return the urange
	 */
	public String getUrange() {
		return urange;
	}
	/**
	 * @param urange the urange to set
	 */
	public void setUrange(String urange) {
		this.urange = urange;
	}
    
    /**
     * 获取奖品ID
     * 
     * @return 奖品ID
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置奖品ID
     * 
     * @param id
     *          奖品ID
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
    /**
     * 获取奖品名称
     * 
     * @return 奖品名称
     */
    public String getPrizename() {
        return this.prizename;
    }
     
    /**
     * 设置奖品名称
     * 
     * @param prizename
     *          奖品名称
     */
    public void setPrizename(String prizename) {
        this.prizename = prizename;
    }
    
    /**
     * 获取奖品类型
     * 
     * @return 奖品类型
     */
    public String getPrizestyle() {
        return this.prizestyle;
    }
     
    /**
     * 设置奖品类型
     * 
     * @param prizestyle
     *          奖品类型
     */
    public void setPrizestyle(String prizestyle) {
        this.prizestyle = prizestyle;
    }
    
    /**
     * 获取运营商
     * 
     * @return 运营商
     */
    public String getCarrier() {
        return this.carrier;
    }
     
    /**
     * 设置运营商
     * 
     * @param carrier
     *          运营商
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    
    /**
     * 获取套餐面值
     * 
     * @return 套餐面值
     */
    public String getCombovalue() {
        return this.combovalue;
    }
     
    /**
     * 设置套餐面值
     * 
     * @param combovalue
     *          套餐面值
     */
    public void setCombovalue(String combovalue) {
        this.combovalue = combovalue;
    }
    
    
    /**
	 * @return the prizeprice
	 */
	public int getPrizeprice() {
		return prizeprice;
	}

	/**
	 * @param prizeprice the prizeprice to set
	 */
	public void setPrizeprice(int prizeprice) {
		this.prizeprice = prizeprice;
	}

	/**
     * 获取面值单位
     * 
     * @return 面值单位
     */
    public String getPervalue() {
        return this.pervalue;
    }
     
    /**
     * 设置面值单位
     * 
     * @param pervalue
     *          面值单位
     */
    public void setPervalue(String pervalue) {
        this.pervalue = pervalue;
    }
    
    /**
     * 获取图片地址
     * 
     * @return 图片地址
     */
    public String getImagepath() {
        return this.imagepath;
    }
     
    /**
     * 设置图片地址
     * 
     * @param imagepath
     *          图片地址
     */
    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
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
    
    /**
     * 获取奖品备注
     * 
     * @return 奖品备注
     */
    public String getNote() {
        return this.note;
    }
     
    /**
     * 设置奖品备注
     * 
     * @param note
     *          奖品备注
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public String getCreatetime() {
        return this.createtime;
    }
     
    /**
     * 设置创建时间
     * 
     * @param createtime
     *          创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    /**
     * 获取更新时间
     * 
     * @return 更新时间
     */
    public String getUpdatetime() {
        return this.updatetime;
    }
     
    /**
     * 设置更新时间
     * 
     * @param updatetime
     *          更新时间
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
