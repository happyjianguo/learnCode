package cn.yufu.cortex.service;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;

public interface CortexTerminalService {
	/**
	 * 
	 * @param merchant 中间库商户实体
	 * @param info	中间库终端实体
	 * @return
	 */
	public String synchronousCortexTerminal(MerchantSDMT merchant,
			TerminalSDMT info);

}
