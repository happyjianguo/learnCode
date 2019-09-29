/**
 * CforderScheduler.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.core.scheduler.AbstractScheduledTask;

import cn.com.jansh.service.scheduler.CfOrderService;

/**
 * 定时任务-订单充值
 * @author duanmuyn
 * @version 1.0
 */
public class CfOrderTask extends AbstractScheduledTask{

	@Autowired
	private CfOrderService cfOrderService;
	
	@Override
	public void exec() {
		cfOrderService.createOrder();
	}
	
}
