package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ClearRegardSalesDetBook;


public interface ClearRegardSalesDetBookService {

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearRegardSalesDetBook queryInfo(String ClearRegardSalesDetBookId);
	
	public List<ClearRegardSalesDetBook> queryList(ClearRegardSalesDetBook queryModel);
	
	public List<ClearRegardSalesDetBook> queryListSum(ClearRegardSalesDetBook queryModel);
	/**
	 * sum(t.tran_num) || '#' || sum(t.tran_amt) || '#' || sum(t.ref_num) || '#' ||sum(t.ref_amt) || '#' || sum(t.stl_amt) || '#' || sum(t.fee) as fee
	 * @param queryModel
	 * @return
	 */
//	public String getSumAmt(ClearRegardSalesBook queryModel);

	
}
