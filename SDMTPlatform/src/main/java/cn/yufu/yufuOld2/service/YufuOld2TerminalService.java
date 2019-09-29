package cn.yufu.yufuOld2.service;

import cn.yufu.SDMTPlatform.entity.TerminalSDMT;

public interface YufuOld2TerminalService {
	
	public void synchronousYufuOldTerminal(TerminalSDMT info);
	
	public int getTerminalListByTermCode(String termCode);
}
