/******************************************************************
** 标    题：GametemplateEntity
** 创 建 者：Mr.wong
** 创建日期：2016-12-22 10:13:22
** 描    述：游戏模板实体
******************************************************************/
package cn.com.jansh.entity.component;
/**
 * 游戏模板实体
 * @author Mr.wong
 * @version 1.0.0 2016-12-22
 */
public class GametemplateEntity {
    
    /** 模板ID */
    private String tempid;
    
    /** 模板地址名称 */
    private String tempname;
    /** 模板名称 */
    private String templatename;
    
    /** 使用次数 */
    private Integer usetimes;
    
    /** 游戏编号 */
    private String gameid;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /** 状态：0-未生效，1-已生效 */
    private String tempstatus;
    
    /**
     * 获取模板ID
     * 
     * @return 模板ID
     */
    public String getTempid() {
        return this.tempid;
    }
     
    /**
     * 设置模板ID
     * 
     * @param tempid
     *          模板ID
     */
    public void setTempid(String tempid) {
        this.tempid = tempid;
    }
    
    /**
     * 获取模板名称
     * 
     * @return 模板名称
     */
    public String getTempname() {
        return this.tempname;
    }
     
    /**
     * 设置模板名称
     * 
     * @param tempname
     *          模板名称
     */
    public void setTempname(String tempname) {
        this.tempname = tempname;
    }
    
    /**
     * 获取使用次数
     * 
     * @return 使用次数
     */
    public Integer getUsetimes() {
        return this.usetimes;
    }
     
    /**
     * 设置使用次数
     * 
     * @param usetimes
     *          使用次数
     */
    public void setUsetimes(Integer usetimes) {
        this.usetimes = usetimes;
    }
    
    /**
     * 获取游戏编号
     * 
     * @return 游戏编号
     */
    public String getGameid() {
        return this.gameid;
    }
     
    /**
     * 设置游戏编号
     * 
     * @param gameid
     *          游戏编号
     */
    public void setGameid(String gameid) {
        this.gameid = gameid;
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
     * 获取状态：0-未生效，1-已生效
     * 
     * @return 状态：0-未生效
     */
    public String getTempstatus() {
        return this.tempstatus;
    }
     
    /**
     * 设置状态：0-未生效，1-已生效
     * 
     * @param tempstatus
     *          状态：0-未生效，1-已生效
     */
    public void setTempstatus(String tempstatus) {
        this.tempstatus = tempstatus;
    }

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
    
}