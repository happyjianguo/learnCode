package cn.yufu.yufuOld1.service;

import java.util.List;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.yufuOld1.entity.RU;

public interface RU1Service {

	/**
	 * 
	 * @param merchantId
	 * @return
	 */
	public List<RU> queryList(String lruid);

	public void synchronousYufuOldMerchant(MerchantSDMT info); 
}
