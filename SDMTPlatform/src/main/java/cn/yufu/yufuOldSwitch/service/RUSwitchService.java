package cn.yufu.yufuOldSwitch.service;

import java.util.List;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.yufuOldSwitch.entity.RU;

public interface RUSwitchService {

	public void synchronousYufuOldSwitchMerchant(MerchantSDMT info); 
	public List<RU> queryList(String lruid);

}
