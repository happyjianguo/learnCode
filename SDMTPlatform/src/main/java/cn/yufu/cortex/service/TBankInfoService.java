package cn.yufu.cortex.service;

import java.util.List;

import cn.yufu.cortex.entity.TBankInfo;

public interface TBankInfoService {

	public List<TBankInfo> queryList(String bankCode) ;

}
