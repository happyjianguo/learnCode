package cn.yufu.yufuOld2.service;

import java.util.List;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.yufuOld2.entity.RU;

public interface RU2Service {

	/**
	 * 
	 * @param merchantId
	 * @return
	 */
	public List<RU> queryList(String lruid);

	public void synchronousYufuOldMerchant(MerchantSDMT info); 
}
