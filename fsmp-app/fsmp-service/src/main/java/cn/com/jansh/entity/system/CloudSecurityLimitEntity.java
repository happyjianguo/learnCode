/**
 * Tellimit.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.entity.system;

/**
 * 短信发送频率限制
 * @author Mr.wong
 * @version 1.0
 */
public class CloudSecurityLimitEntity {

	//手机号码
	private String reportno;
	//更新时间
	private String updatetime;
	
	public String getReportno() {
		return reportno;
	}
	public void setReportno(String reportno) {
		this.reportno = reportno;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
}
