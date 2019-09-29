package cn.yufu.yufuOld.service;

import cn.yufu.SDMTPlatform.entity.TerminalSDMT;

public interface YufuOldTerminalService {
	
	public void synchronousYufuOldTerminal(TerminalSDMT info);
	
	public int getTerminalListByTermCode(String termCode);
}
