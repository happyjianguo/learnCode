package cn.com.jansh.entity.wsfdn;
/******************************************************************
** 标    题：CfAccessclientEntity
** 创 建 者：dmyn
** 创建日期：2016-04-26 17:11:28
** 描    述：批量充值表
******************************************************************/
public class CfBatchrechargeEntity {

	/** 充值ID */
    private String id;
    
    /** 运营商 */
    private String ispno;
    
    /** 套餐类型 */
    private String isptype;
    
    /** 面值*/
    private String facevalue;
    
    /** 手机号 */
    private String phone;
    
    /** 批量充值编号 */
    private String batchid;
    
    /** 批次名称 */
    private String batchname;
    
    /** 流水id */
    private String bizid;
    
    /** 发送状态  */
    private String status;
    
    /** 接入者id */
    private String apno;
    
    private String acname;
    
    /** 客户id  */
    private String cid;
    
    private String cname;
    
    /**
     * 获取充值ID
     * 
     * @return 充值ID
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置充值ID
     * 
     * @param id
     *          充值ID
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
     * 获取批量充值编号
     * 
     * @return 批量充值编号
     */
    public String getBatchid() {
        return this.batchid;
    }
     
    /**
     * 设置批量充值编号
     * 
     * @param batchid
     *          批量充值编号
     */
    public void setBatchid(String batchid) {
        this.batchid = batchid;
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
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置
     * 
     * @param status
     *          
     */
    public void setStatus(String status) {
        this.status = status;
    }

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getApno() {
		return apno;
	}

	public void setApno(String apno) {
		this.apno = apno;
	}

	public String getAcname() {
		return acname;
	}

	public void setAcname(String acname) {
		this.acname = acname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "CfBatchrechargeEntity [id=" + id + ", ispno=" + ispno + ", isptype=" + isptype + ", facevalue="
				+ facevalue + ", phone=" + phone + ", batchid=" + batchid + ", batchname=" + batchname + ", bizid="
				+ bizid + ", status=" + status + ", apno=" + apno + ", acname=" + acname + ", cid=" + cid + ", cname="
				+ cname + "]";
	}
    
   
	
}