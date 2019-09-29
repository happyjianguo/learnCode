package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.TransactionRecordsHisT;

public interface TransactionRecordsHisTService {
	
	//总数
	public int queryCount(TransactionRecordsHisT queryModel);
	
	//分页查询
	public List<TransactionRecordsHisT> selectPageList(TransactionRecordsHisT queryModel,
			int startResult, int endResult);
	
	//导出查询
	public List<TransactionRecordsHisT> selectAllList(TransactionRecordsHisT queryModel);
	
}
