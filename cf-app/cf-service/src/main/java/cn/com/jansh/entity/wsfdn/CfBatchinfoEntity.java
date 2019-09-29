package cn.com.jansh.entity.wsfdn;
/******************************************************************
** 标    题：CfBatchinfoEntity
** 创 建 者：dmyn
** 创建日期：2016-04-26 17:11:28
** 描    述：批量充值明细表
******************************************************************/
public class CfBatchinfoEntity {

	/** 批次编号 */
    private String batchid;
    
    /** 批次名称 */
    private String batchname;
    
    /** 接入者编号 */
    private String apno;
    
    /** 状态 */
    private String status;
    
    
    private String userid;
    
    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
     * 获取批次编号
     * 
     * @return 批次编号
     */
    public String getBatchid() {
        return this.batchid;
    }
     
    /**
     * 设置批次编号
     * 
     * @param batchid
     *          批次编号
     */
    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }
    
    /**
     * 获取批次名称
     * 
     * @return 批次名称
     */
    public String getBatchname() {
        return this.batchname;
    }
     
    /**
     * 设置批次名称
     * 
     * @param batchname
     *          批次名称
     */
    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }
    
    /**
     * 获取接入者编号
     * 
     * @return 接入者编号
     */
    public String getApno() {
        return this.apno;
    }
     
    /**
     * 设置接入者编号
     * 
     * @param apno
     *          接入者编号
     */
    public void setApno(String apno) {
        this.apno = apno;
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
		return "CfBatchinfoEntity [batchid=" + batchid + ", batchname=" + batchname + ", apno=" + apno + ", status="
				+ status + "]";
	}
}