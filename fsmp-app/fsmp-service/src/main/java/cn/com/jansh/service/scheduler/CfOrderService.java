/**
 * CfOrderInterface.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.service.scheduler;

/**
 * 定时充值
 * @author duanmuyn
 * @version 1.0
 */
public interface CfOrderService {
	
	/**
	 * 直冲
	 */
	public void createOrder();
	
	/**
	 * 订单查询
	 */
	public void queryOrder();
}
