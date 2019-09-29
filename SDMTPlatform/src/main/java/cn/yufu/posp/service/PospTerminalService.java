package cn.yufu.posp.service;

import cn.yufu.SDMTPlatform.entity.TerminalSDMT;

public interface PospTerminalService {

	/**
	 * 
	 * @param info
	 */
	public void synchronousPospTerminal(TerminalSDMT info);
	/**
	 * 收单系统与老福卡系统映射表
	 * @param info
	 */
	public void synchronousEdcTerminalOrm(TerminalSDMT info);
	/**
	 * 收单系统与FIS系统映射表
	 * @param info
	 */
	public void synchronousEdcNewfkterminalOrm(TerminalSDMT info);

}
