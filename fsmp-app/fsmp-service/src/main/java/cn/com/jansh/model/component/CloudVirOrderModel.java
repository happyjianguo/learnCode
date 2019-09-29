/**
 * CloudVirOderModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.model.component;

/**
 * 虚拟订单model
 * @author Mr.wong
 * @version 1.0
 */
public class CloudVirOrderModel {

	//领奖流水记录
	private String awardid;
	//活动id
	private String gameid;
	//用户ID
	private String userid;
	//手机号
	private String mobile;
	//奖品ID
	private String prizeid;
	//创建时间
	private String createtime;
	//奖品类型
	private String prizetype;
	//奖品价格(未使用)
	private int prizeprice;
	//是否超出预算标识(未使用)
	private String budgetlimit;

    /** 奖品名称*/
    private String prizename;

	public CloudVirOrderModel() {
	}

	public String getAwardid() {
		return awardid;
	}

	public void setAwardid(String awardid) {
		this.awardid = awardid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPrizeid() {
		return prizeid;
	}

	public void setPrizeid(String prizeid) {
		this.prizeid = prizeid;
	}


	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getPrizetype() {
		return prizetype;
	}

	public void setPrizetype(String prizetype) {
		this.prizetype = prizetype;
	}

	public int getPrizeprice() {
		return prizeprice;
	}

	public void setPrizeprice(int prizeprice) {
		this.prizeprice = prizeprice;
	}

	public String getBudgetlimit() {
		return budgetlimit;
	}

	public void setBudgetlimit(String budgetlimit) {
		this.budgetlimit = budgetlimit;
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	
	public String getPrizename() {
		return prizename;
	}

	public void setPrizename(String prizename) {
		this.prizename = prizename;
	}

	@Override
	public String toString() {
		return "CloudVirOrderModel [awardid=" + awardid + ", gameid=" + gameid + ", userid=" + userid + ", mobile="
				+ mobile + ", prizeid=" + prizeid + ", createtime=" + createtime + ", prizetype=" + prizetype
				+ ", prizeprice=" + prizeprice + ", budgetlimit=" + budgetlimit + ", prizename=" + prizename + "]";
	}

	
}
