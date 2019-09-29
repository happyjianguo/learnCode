package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ClearMerClearBook;


public interface ClearMerClearBookService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearMerClearBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearMerClearBook> queryList(ClearMerClearBook queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearMerClearBook queryInfo(String ClearMerClearBookId);
	
	public List<ClearMerClearBook> queryList(ClearMerClearBook queryModel);
	/**
	 * sum(t.tran_num) || '#' || sum(t.tran_amt) || '#' || sum(t.ref_num) || '#' ||sum(t.ref_amt) || '#' || sum(t.stl_amt) || '#' || sum(t.fee) as fee
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearMerClearBook queryModel);
	
	public List<ClearMerClearBook> selectPaiMingByExample(ClearMerClearBook queryModel);
	
	public List<ClearMerClearBook> selectPaiMingPageByExample(ClearMerClearBook queryModel, int startResult, int endResult);

	public int countPaiMingByExample(ClearMerClearBook queryModel) ;
	
	public List<ClearMerClearBook> selectPaiMingTotal(ClearMerClearBook queryModel);
	
	public List<ClearMerClearBook> selectPaiMingPageTotal(ClearMerClearBook queryModel, int startResult, int endResult);

	public int countPaiMingTotal(ClearMerClearBook queryModel) ;
	
}
