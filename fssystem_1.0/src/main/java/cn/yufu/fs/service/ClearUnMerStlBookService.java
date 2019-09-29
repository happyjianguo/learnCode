package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.ClearUnMerStlBook;


public interface ClearUnMerStlBookService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearUnMerStlBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearUnMerStlBook> queryList(ClearUnMerStlBook queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearUnMerStlBook queryInfo(String ClearUnMerStlBookId);
	
	/**
	 * 更新一条记录
	 * 
	 * @return
	 */
	public int UpdateClearUnMerStlBook(ClearUnMerStlBook record);
	
	/**
	 * 编辑一条记录
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> edit(ClearUnMerStlBook info);
	
	
	public List<ClearUnMerStlBook> queryList(ClearUnMerStlBook queryModel);
	/**
	 * sum(consum_num) || '#' || sum(consum_amt) || '#' || sum(tran_amt) || '#' ||sum(net_amt)  || '#' || sum(fee)  as fee
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearUnMerStlBook queryModel);
}
