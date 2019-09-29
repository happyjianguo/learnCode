package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ClearMermccDetailBook;


public interface ClearMermccDetailBookService {

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearMermccDetailBook queryInfo(String ClearMermccDetailBookId);
	
	public List<ClearMermccDetailBook> queryList(ClearMermccDetailBook queryModel);
	
	public List<ClearMermccDetailBook> queryListSum(ClearMermccDetailBook queryModel);
	/**
	 * sum(t.tran_num) || '#' || sum(t.tran_amt) || '#' || sum(t.ref_num) || '#' ||sum(t.ref_amt) || '#' || sum(t.stl_amt) || '#' || sum(t.fee) as fee
	 * @param queryModel
	 * @return
	 */
//	public String getSumAmt(ClearRegardSalesBook queryModel);

	
}
