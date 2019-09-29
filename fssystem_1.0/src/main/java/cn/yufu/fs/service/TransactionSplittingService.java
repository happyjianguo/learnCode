package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.TransactionSplitting;

public interface TransactionSplittingService {
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(TransactionSplitting queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<TransactionSplitting> queryList(TransactionSplitting queryModel, int startResult, int endResult);
	
	/**
	 * 报表导出
	 * 
	 * @param queryModel
	 * @return
	 */
	public List<TransactionSplitting> queryList(TransactionSplitting queryModel);
	
	/**
	 * 合计金额
	 * 
	 * @param queryModel
	 * @return
	 */
	public TransactionSplitting getSumAmt(TransactionSplitting queryModel);
	
	/**
	 * 主键查询
	 * 
	 * @param cardTypeId
	 * @return
	 */
	public TransactionSplitting selectByPrimaryKey(String id);
	
}
