package cn.yufu.yufuOldSwitch.service;

import cn.yufu.SDMTPlatform.entity.TerminalSDMT;

public interface YufuOldSwitchTerminalService {
	
	public void synchronousYufuOldSwitchTerminal(TerminalSDMT info);
	
	public int getTerminalListByTermCode(String termCode);
}
