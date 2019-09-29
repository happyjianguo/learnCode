package cn.yufu.posp.service;

import java.util.List;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.posp.entity.MerchantBase;

public interface MerchantBaseService {

	/**
	 * 
	 * @param merchantId
	 * @return
	 */
	public List<MerchantBase> queryList(String merchantId);

	public void synchronousPospMerchant(MerchantSDMT info);

}
