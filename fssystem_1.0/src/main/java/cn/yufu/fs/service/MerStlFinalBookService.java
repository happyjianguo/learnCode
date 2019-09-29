package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.ClearMerStlFinalBook;


public interface MerStlFinalBookService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearMerStlFinalBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearMerStlFinalBook> queryList(ClearMerStlFinalBook queryModel, int startResult, int endResult);
	
	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearMerStlFinalBook queryInfo(String ClearMerStlFinalBookId);
	
	/**
	 * 更新一条记录
	 * 
	 * @return
	 */
	public int UpdateClearMerStlFinalBook(ClearMerStlFinalBook record);
	
	/**
	 * 编辑一条记录
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> edit(ClearMerStlFinalBook info);
	
	
	public List<ClearMerStlFinalBook> queryList(ClearMerStlFinalBook queryModel);
	
	/**
	 * sum(consum_num) || '#' || sum(consum_amt) || '#' || sum(tran_amt) || '#' ||sum(net_amt)  || '#' || sum(fee)  as fee
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearMerStlFinalBook queryModel);
	
	public int updatePayoutStatus(String flag,List<ClearMerStlFinalBook> list) ;
	
	public List<ClearMerStlFinalBook> selectGroupSumByExample(ClearMerStlFinalBook queryModel);
	
}
