/**
 * BlackListTaskService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月26日
 */
package cn.com.jansh.service.scheduler;

/**
 * 黑名单定时任务Service
 * @author gll
 * @version 1.0
 */
public interface BlackListTaskService {

	/**
	 * 根据过期时间定时刷新黑名单状态
	 */
	public void updateBlackListByovertime();

}
