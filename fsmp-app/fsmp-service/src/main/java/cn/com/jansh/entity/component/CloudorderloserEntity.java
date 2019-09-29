/******************************************************************
** 标    题：CloudorderloserEntity
** 创建日期：2016-12-22 10:13:22
** 描    述：失败订单重发中间表
******************************************************************/
package cn.com.jansh.entity.component;
/**
 * 失败订单重发中间表
 * @author Mr.wong
 * @version 1.0.0 2016-12-22
 */
public class CloudorderloserEntity {
    
    /** 序号id */
    private String id;
    
    /** 失败订单id */
    private String parentid;
    
    /** 创建时间 */
    private String createtime;
    
    /**
     * 获取序号id
     * 
     * @return 序号id
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置序号id
     * 
     * @param id
     *          序号id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取失败订单id
     * 
     * @return 失败订单id
     */
    public String getParentid() {
        return this.parentid;
    }
     
    /**
     * 设置失败订单id
     * 
     * @param parentid
     *          失败订单id
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
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
}