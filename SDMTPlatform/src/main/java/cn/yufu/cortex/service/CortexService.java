package cn.yufu.cortex.service;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;

public interface CortexService {
	/**
	 * 
	 * @param info
	 * @return	seqMerchantId|SeqMrchAccXId
	 */
	public String synchronousCortexMerchant(MerchantSDMT info);

}
