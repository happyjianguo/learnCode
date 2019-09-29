package cn.com.jansh.service.scheduler;

import cn.com.jansh.core.exception.AppException;

public interface CfQueryOrderTaskService {

	/**
	 * 定时更新订单状态
	 * @throws AppException
	 */
	public void queryOrder() throws AppException;
}
