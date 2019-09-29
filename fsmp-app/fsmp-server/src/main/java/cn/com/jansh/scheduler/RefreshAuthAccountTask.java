/**
 * RefreshAuthAccountTask.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2017年1月10日
 */
package cn.com.jansh.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.core.scheduler.AbstractScheduledTask;

import cn.com.jansh.service.scheduler.RefreshAuthAccountService;

/**
 * 刷新公众号信息定时任务
 * @author Mr.wong
 * @version 1.0
 */
public class RefreshAuthAccountTask extends AbstractScheduledTask {

	@Autowired
	private RefreshAuthAccountService refreshService;
	
	@Override
	public void exec() {
		refreshService.action();

	}

}
