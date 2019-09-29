package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ClearMermccBook;

public interface ClearMermccBookService {

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearMermccBook queryInfo(String ClearMermccBookId);
	
	public List<ClearMermccBook> queryList(ClearMermccBook queryModel);

	/**
	 * sum(t.tran_num) || '#' || sum(t.tran_amt) || '#' || sum(t.ref_num) || '#' ||sum(t.ref_amt) || '#' || sum(t.stl_amt) || '#' || sum(t.fee) as fee
	 * @param queryModel
	 * @return
	 */
//	public String getSumAmt(ClearMermccBook queryModel);
	
	public int getCount(ClearMermccBook queryModel);
	
	public List<ClearMermccBook> getTotalAmtPage(ClearMermccBook queryModel,
			int startResult, int endResult);
	
	public List<ClearMermccBook> getTotalAmt(ClearMermccBook queryModel);
	
	public ClearMermccBook getTotal(ClearMermccBook queryModel);
	
}
