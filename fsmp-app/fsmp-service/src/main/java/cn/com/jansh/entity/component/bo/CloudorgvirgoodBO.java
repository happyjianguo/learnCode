/******************************************************************
** 标    题：CloudorgvirgoodModel
** 创 建 者：Mr.wong
** 创建日期：2016-11-07 16:36:32
** 描    述：黑名单表
******************************************************************/
package cn.com.jansh.entity.component.bo;
/**
 * 机构套餐BO(CloudorgvirgoodModel)
 * @author Mr.wong
 * @version 1.0.0 2016-11-07
 */
public class CloudorgvirgoodBO {
    
    /** id */
    private String id;
    
    /** 机构编号 */
    private String orgid;
    
    /** 游戏id */
    private String gameid;
    
    /** 套餐id */
    private String prizestyle;
    
    /** 套餐名称 */
    private String prizename;
    
    /** 创建时间 */
    private String createtime;
    
    /**
     * 获取id
     * 
     * @return id
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置id
     * 
     * @param id
     *          id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取机构编号
     * 
     * @return 机构编号
     */
    public String getOrgid() {
        return this.orgid;
    }
     
    /**
     * 设置机构编号
     * 
     * @param orgid
     *          机构编号
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    
    /**
     * 获取游戏id
     * 
     * @return 游戏id
     */
    public String getGameid() {
        return this.gameid;
    }
     
    /**
     * 设置游戏id
     * 
     * @param gameid
     *          游戏id
     */
    public void setGameid(String gameid) {
        this.gameid = gameid;
    }
    
    /**
     * 获取套餐id
     * 
     * @return 套餐id
     */
    public String getPrizestyle() {
        return this.prizestyle;
    }
     
    /**
     * 设置套餐id
     * 
     * @param prizestyle
     *          套餐id
     */
    public void setPrizestyle(String prizestyle) {
        this.prizestyle = prizestyle;
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

	public String getPrizename() {
		return prizename;
	}

	public void setPrizename(String prizename) {
		this.prizename = prizename;
	}
    
    
}