package cn.com.jansh.service.scheduler;

import cn.com.jansh.core.web.servlet.ViewObject;

public interface RechargeInterService {
	
	/**
	 * 直冲订购接口
	 * @param janshAuth
	 * @param msg
	 * @return
	 */
	public ViewObject billorder(String janshAuth, String msg);
	
	/**
	 * 订单状态查询
	 * @param janshAuth
	 * @param msg
	 * @return
	 */
	public ViewObject queryorder(String janshAuth,String msg);
	
}
