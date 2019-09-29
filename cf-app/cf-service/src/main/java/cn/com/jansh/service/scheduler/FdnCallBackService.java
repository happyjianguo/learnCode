package cn.com.jansh.service.scheduler;

import java.util.Map;

import cn.com.jansh.model.wsfdn.WsfdnOrdercb;

public interface FdnCallBackService {
	/**
	 * 流量订购（回调刷状态）
	 * 
	 * @return
	 */
	public void wsOrdercb(WsfdnOrdercb wsfdnOrdercb) ;
	
	/**
	 * 话费订购（回调刷状态）欧飞
	 * 
	 * @return
	 */
	public void ofOrdercb(Map<String,String> map) ;
}
