package cn.yufu.cortex.service;

import java.util.List;

import cn.yufu.cortex.entity.MerchantOrg;

public interface MerchantOrgService {
	
	public MerchantOrg selectByPrimaryKey(String orgId);
	
	//获取商户机构名称及ID集合
    public List<MerchantOrg> getOrgNameAndID();
}
