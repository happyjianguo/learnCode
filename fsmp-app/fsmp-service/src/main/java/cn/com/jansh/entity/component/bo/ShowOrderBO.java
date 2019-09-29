package cn.com.jansh.entity.component.bo;

/**
 * 用户充值记录BO
 * @author Mr.wong
 * @version 1.0
 */
public class ShowOrderBO {

	 /** 中奖记录ID */
    private String id;
    
    /** OPENID */
    private String openid;
    
    /** 机构ID */
    private String orgid;
    
    /** 活动名称 */
    private String gamename;
    
    /** 中奖手机号 */
    private String winnerphone;
    
    /** 发货状态 */
    private String sendstatus;
    
    /** 奖品名称 */
    private String prizestylename;
    
    /** 创建时间 */
    private String createtime;
    
    /** 更新时间 */
    private String updatetime;
    
    /** 审核状态 */
    private String preliminarystatus;
    
    /** 初审人 */
    private String preliminaryper;
    
    /** 复审人 */
    private String reviewper;
    
    /** 奖品分类*/
    private String prizetype;

    /** 奖品名称*/
    private String prizename;

	public ShowOrderBO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getWinnerphone() {
		return winnerphone;
	}

	public void setWinnerphone(String winnerphone) {
		this.winnerphone = winnerphone;
	}

	public String getSendstatus() {
		return sendstatus;
	}

	public void setSendstatus(String sendstatus) {
		this.sendstatus = sendstatus;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getPreliminarystatus() {
		return preliminarystatus;
	}

	public void setPreliminarystatus(String preliminarystatus) {
		this.preliminarystatus = preliminarystatus;
	}

	public String getPreliminaryper() {
		return preliminaryper;
	}

	public void setPreliminaryper(String preliminaryper) {
		this.preliminaryper = preliminaryper;
	}

	public String getReviewper() {
		return reviewper;
	}

	public void setReviewper(String reviewper) {
		this.reviewper = reviewper;
	}

	public String getPrizetype() {
		return prizetype;
	}

	public void setPrizetype(String prizetype) {
		this.prizetype = prizetype;
	}

	public String getPrizename() {
		return prizename;
	}

	public void setPrizename(String prizename) {
		this.prizename = prizename;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public String getPrizestylename() {
		return prizestylename;
	}

	public void setPrizestylename(String prizestylename) {
		this.prizestylename = prizestylename;
	}
	
}
