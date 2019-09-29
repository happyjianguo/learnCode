/**
 * ImNoticeTaskService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月23日
 */
package cn.com.jansh.service.scheduler;

/**
 * 公告定时任务Service
 * @author gll
 * @version 1.0
 */
public interface ImNoticeTaskService {

	/**
	 * 公告定时任务，定时刷新公告状态。
	 */
	public void updateNoticeByendtime();

}
