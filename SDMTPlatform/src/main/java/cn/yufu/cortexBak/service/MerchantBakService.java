package cn.yufu.cortexBak.service;

import java.util.List;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.cortexBak.entity.MerchantBak;

public interface MerchantBakService {

	/**
	 * 
	 * @param mrchNo
	 * @return
	 */
	public List<MerchantBak> queryList(String mrchNo);

	
	public void synchronousCortexBakMerchant(MerchantSDMT info,String seqMerchantIdAndSeqMrchAccXId);
}
