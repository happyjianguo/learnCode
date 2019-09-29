package cn.yufu.yufuOld.service;

import java.util.List;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.yufuOld.entity.RU;

public interface RUService {

	/**
	 * 
	 * @param merchantId
	 * @return
	 */
	public List<RU> queryList(String lruid);

	public void synchronousYufuOldMerchant(MerchantSDMT info); 
}
