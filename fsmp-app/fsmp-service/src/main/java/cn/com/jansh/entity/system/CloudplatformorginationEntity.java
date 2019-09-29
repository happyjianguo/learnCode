/******************************************************************
** 标    题：CloudplatformorginationEntity
** 创 建 者：Mr.wong
** 创建日期：2016-11-17 11:29:41
** 描    述：平台机构
******************************************************************/
package cn.com.jansh.entity.system;
/**
 * 平台机构(BLACKLIST)
 * @author Mr.wong
 * @version 1.0.0 2016-11-17
 */
public class CloudplatformorginationEntity {
    /** 机构ID */
    private String id;
    
    /** 父级机构ID */
    private String parentorgid;
    
    /** 机构名称 */
    private String orgname;
    
    /** 机构备注 */
    private String note;
    
    /** 机构状态 */
    private String orgstatus;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /** 删除标志位 0-已删除 1-未删除 */
    private String delflag;
    
    /**
     * 获取机构ID
     * 
     * @return 机构ID
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置机构ID
     * 
     * @param id
     *          机构ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取父级机构ID
     * 
     * @return 父级机构ID
     */
    public String getParentorgid() {
        return this.parentorgid;
    }
     
    /**
     * 设置父级机构ID
     * 
     * @param parentorgid
     *          父级机构ID
     */
    public void setParentorgid(String parentorgid) {
        this.parentorgid = parentorgid;
    }
    
    /**
     * 获取机构名称
     * 
     * @return 机构名称
     */
    public String getOrgname() {
        return this.orgname;
    }
     
    /**
     * 设置机构名称
     * 
     * @param orgname
     *          机构名称
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
    
    /**
     * 获取机构备注
     * 
     * @return 机构备注
     */
    public String getNote() {
        return this.note;
    }
     
    /**
     * 设置机构备注
     * 
     * @param note
     *          机构备注
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 获取机构状态
     * 
     * @return 机构状态
     */
    public String getOrgstatus() {
        return this.orgstatus;
    }
     
    /**
     * 设置机构状态
     * 
     * @param orgstatus
     *          机构状态
     */
    public void setOrgstatus(String orgstatus) {
        this.orgstatus = orgstatus;
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
     * 获取删除标志位 0-已删除 1-未删除
     * 
     * @return 删除标志位 0-已删除 1-未删除
     */
    public String getDelflag() {
        return this.delflag;
    }
     
    /**
     * 设置删除标志位 0-已删除 1-未删除
     * 
     * @param delflag
     *          删除标志位 0-已删除 1-未删除
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }
}