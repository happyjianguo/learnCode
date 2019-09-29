/**
 * SingleActionVO.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月1日
 */
package cn.com.jansh.vo;

/**
 * 单个活动数据VO
 * @author gll
 * @version 1.0
 */
public class SingleActionVO {
	//开始日期
	private String startDate;
	//结束日期
	private String endDate;
	//活动 gameid
	private String gameid;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	@Override
	public String toString() {
		return "SingleActionVO [startDate=" + startDate + ", endDate=" + endDate + ", gameid=" + gameid + "]";
	}
}
