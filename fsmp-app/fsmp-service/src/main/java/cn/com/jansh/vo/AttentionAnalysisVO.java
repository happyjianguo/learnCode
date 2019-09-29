/**
 * AttentionAnalysisVO.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:zhuhujie 2015-8-26
 */
package cn.com.jansh.vo;

/**
 * 关注用户分析参数提交
 * @author panc
 * @version 1.0
 */
public class AttentionAnalysisVO {
	//开始日期
	private String startDate;
	// 结束日期
	private String endDate;
	//公众号appid
	private String appid;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
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
	@Override
	public String toString() {
		return "AttentionAnalysisVO [startDate=" + startDate + ", endDate=" + endDate + ", appid=" + appid + "]";
	}
}
