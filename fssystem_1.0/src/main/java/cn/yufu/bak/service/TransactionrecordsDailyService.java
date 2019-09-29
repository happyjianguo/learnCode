package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.TransactionrecordsDaily;

public interface TransactionrecordsDailyService {
	
	//总数
	public int queryCount(TransactionrecordsDaily queryModel);
	
	//分页查询
	public List<TransactionrecordsDaily> selectPageList(TransactionrecordsDaily queryModel,
			int startResult, int endResult);
	
	//导出查询
	public List<TransactionrecordsDaily> selectAllList(TransactionrecordsDaily queryModel);
	
	//获取总笔数，总交易金额
	public TransactionrecordsDaily getNumAndAmt(TransactionrecordsDaily queryModel);
		
}
