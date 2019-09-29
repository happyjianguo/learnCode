package cn.yufu.cortexBak.service;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;

public interface CortexBakTerminalService {
	/**
	 * 
	 * @param merchant 中间库商户实体
	 * @param info	中间库终端实体
	 * @param seqTermPosIdAndTermNoAndseqEnckeyId	
	 */
	public void synchronousCortexBakTerminal(MerchantSDMT merchant,TerminalSDMT info,String seqTermPosIdAndTermNoAndseqEnckeyId);
	
	public int getTerminalListByTermCode(String termCode);
}
