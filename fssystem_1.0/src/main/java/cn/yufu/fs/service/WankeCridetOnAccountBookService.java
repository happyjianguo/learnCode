package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.WankeCridetOnAccountBook;


public interface WankeCridetOnAccountBookService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(WankeCridetOnAccountBook queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<WankeCridetOnAccountBook> queryList(WankeCridetOnAccountBook queryModel, int startResult, int endResult);
	
	public List<WankeCridetOnAccountBook> queryList(WankeCridetOnAccountBook queryModel);
	/**
	 * sum(t.tran_num) || '#' || sum(t.tran_amt) || '#' || sum(t.ref_num) || '#' ||sum(t.ref_amt) || '#' || sum(t.stl_amt) || '#' || sum(t.fee) as fee
	 * @param queryModel
	 * @return
	 */
	//public String getSumAmt(WankeCridetOnAccountBook queryModel);
	
	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public WankeCridetOnAccountBook queryInfo(String WankeCridetOnAccountBookId);
	
}
