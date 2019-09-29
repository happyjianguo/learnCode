/**
 * NoticeManageModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月18日
 */
package cn.com.jansh.model.system;

/**
 * 公告model
 * @author gll
 * @version 1.0
 */
public class NoticeManageModel {
	 /** 公告id */
    private String noticeid;
    
    /** 公告名 */
    private String noticename;
    
    /** 公告值 */
    private String noticevalue;
    
    /** 状态：0-未发布，1-已发布，其他数字-未知状态 */
    private String status;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /** 开始时间 */
    private String begintime;
    
    /** 结束时间 */
    private String endtime;
    
    /**
     * 获取公告id
     * 
     * @return 公告id
     */
    public String getNoticeid() {
        return this.noticeid;
    }
     
    /**
     * 设置公告id
     * 
     * @param noticeid
     *          公告id
     */
    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid;
    }
    
    /**
     * 获取公告名
     * 
     * @return 公告名
     */
    public String getNoticename() {
        return this.noticename;
    }
     
    /**
     * 设置公告名
     * 
     * @param noticename
     *          公告名
     */
    public void setNoticename(String noticename) {
        this.noticename = noticename;
    }
    
    /**
     * 获取公告值
     * 
     * @return 公告值
     */
    public String getNoticevalue() {
        return this.noticevalue;
    }
     
    /**
     * 设置公告值
     * 
     * @param noticevalue
     *          公告值
     */
    public void setNoticevalue(String noticevalue) {
        this.noticevalue = noticevalue;
    }
    
    /**
     * 获取状态：0-未发布，1-已发布，其他数字-未知状态
     * 
     * @return 状态：0-未发布
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态：0-未发布，1-已发布，其他数字-未知状态
     * 
     * @param status
     *          状态：0-未发布，1-已发布，其他数字-未知状态
     */
    public void setStatus(String status) {
        this.status = status;
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
    @Override
	public String toString() {
		return "NoticeEntity [noticeid=" + noticeid + ", noticename=" + noticename + ", noticevalue=" + noticevalue
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + ", begintime=" + begintime
				+ ", endtime=" + endtime + ", status=" + status + "]";
	}
}
