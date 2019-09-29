package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.ClearFeeBook;


public interface ClearFeeBookService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearFeeBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearFeeBook> queryList(ClearFeeBook queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearFeeBook queryInfo(String ClearFeeBookId);
	
	/**
	 * 更新一条记录
	 * 
	 * @return
	 */
	public int UpdateClearFeeBook(ClearFeeBook record);
	
	/**
	 * 编辑一条记录
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> edit(ClearFeeBook info);
	
	
	public List<ClearFeeBook> queryList(ClearFeeBook queryModel);
	/**
	 * sum(consum_num) || '#' || sum(consum_amt) || '#' || sum(tran_amt) || '#' ||sum(net_amt)  || '#' || sum(fee)  as fee
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearFeeBook queryModel);
}
