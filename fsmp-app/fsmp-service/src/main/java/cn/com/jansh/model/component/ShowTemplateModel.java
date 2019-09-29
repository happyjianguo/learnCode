/******************************************************************
** 标    题：GametemplateEntity
** 创 建 者：Mr.wong
** 创建日期：2016-12-22 10:13:22
** 描    述：游戏模板实体
******************************************************************/
package cn.com.jansh.model.component;
/**
 * 游戏模板实体
 * @author Mr.wong
 * @version 1.0.0 2016-12-22
 */
public class ShowTemplateModel {
    
    /** 模板ID */
    private String tempid;
    
    /** 模板地址名称 */
    private String tempname;
    
    /** 模板名称 */
    private String templatename;
    
    /** 模板图片地址*/
    private String pictrueurl;
    
    /** 部署路径 */
    private String deploypath;
    
    /** 游戏编号 */
    private String gameid;
    
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

	public String getDeploypath() {
		return deploypath;
	}

	public void setDeploypath(String deploypath) {
		this.deploypath = deploypath;
	}

	public String getPictrueurl() {
		return pictrueurl;
	}

	public void setPictrueurl(String pictrueurl) {
		this.pictrueurl = pictrueurl;
	}
	
	
}