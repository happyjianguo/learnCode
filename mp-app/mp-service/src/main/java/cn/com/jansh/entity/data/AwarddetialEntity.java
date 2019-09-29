package cn.com.jansh.entity.data;

public class AwarddetialEntity {
	private String prizeparamid; //奖品设置id
	private String awardid;//领奖流水记录
	private String userid;//用户ID
	private String mobile;//手机号
	private String prizeid;//奖品ID
	private String prizename;//奖品名称
	private String prizestatus;//中奖状态
	private String whetherwinning;//中奖记录充值状态 o 表示未充值 1表示充值成功 2表示充值失败
	private String createtime;//创建时间
	private String prizetype;//奖品类型
	private String prizeprice;//奖品价格
	
	public String getPrizeparamid() {
		return prizeparamid;
	}
	public void setPrizeparamid(String prizeparamid) {
		this.prizeparamid = prizeparamid;
	}
	public String getPrizeprice() {
		return prizeprice;
	}
	public void setPrizeprice(String prizeprice) {
		this.prizeprice = prizeprice;
	}
	public String getPrizetype() {
		return prizetype;
	}
	public void setPrizetype(String prizetype) {
		this.prizetype = prizetype;
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
	public String getPrizename() {
		return prizename;
	}
	public void setPrizename(String prizename) {
		this.prizename = prizename;
	}
	public String getPrizestatus() {
		return prizestatus;
	}
	public void setPrizestatus(String prizestatus) {
		this.prizestatus = prizestatus;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getWhetherwinning() {
		return whetherwinning;
	}
	public void setWhetherwinning(String whetherwinning) {
		this.whetherwinning = whetherwinning;
	}
	
}
