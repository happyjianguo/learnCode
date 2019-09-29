package cn.yufu.bak.service;

import java.util.List;

public interface TermposXService {
	
	/**
	 * 根据商户号从备库获取商户费率
	 * @author Administrator
	 *
	 */
	public List<String> getTimezoneList(String merNo);
	
	/**
	 * 根据终端号号从备库获取终端位置
	 * @author Administrator
	 *
	 */
	public List<String> getTerminalLoc(String terminalNo);
	
	/**
	 * 根据终端号号从备库获取商户号
	 * @author Administrator
	 *
	 */
	public List<String> getMrchno(String terminalNo);
	
}
