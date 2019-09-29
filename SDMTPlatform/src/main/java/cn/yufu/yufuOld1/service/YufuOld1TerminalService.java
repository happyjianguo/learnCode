package cn.yufu.yufuOld1.service;

import cn.yufu.SDMTPlatform.entity.TerminalSDMT;

public interface YufuOld1TerminalService {
	
	public void synchronousYufuOld1Terminal(TerminalSDMT info);
	
	public int getTerminalListByTermCode(String termCode);
}
