package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.MerchantTranRecord;

public interface MerchantTranRecordService {
	
	//获取分页总数
	public int queryCnt(MerchantTranRecord merchantTranRecord);

	//查询数据
	public List<MerchantTranRecord> queryList(MerchantTranRecord merchantTranRecord, int startResult, int endResult);
	
	public List<MerchantTranRecord> getExcelData(MerchantTranRecord merchantTranRecord);
}
