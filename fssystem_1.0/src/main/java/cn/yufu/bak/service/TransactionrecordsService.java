package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.Transactionrecords;

public interface TransactionrecordsService {
	
	//总数
	public int queryCount(Transactionrecords queryModel);
	
	//分页查询
	public List<Transactionrecords> selectPageList(Transactionrecords queryModel,
			int startResult, int endResult);
	
	//导出查询
	public List<Transactionrecords> selectAllList(Transactionrecords queryModel);
	
}
