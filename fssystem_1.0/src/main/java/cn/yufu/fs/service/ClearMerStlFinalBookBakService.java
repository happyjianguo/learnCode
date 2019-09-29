package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ClearMerStlFinalBookBak;


public interface ClearMerStlFinalBookBakService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearMerStlFinalBookBak queryModel);
	
	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearMerStlFinalBookBak> queryList(ClearMerStlFinalBookBak queryModel, int startResult, int endResult);
	
	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearMerStlFinalBookBak queryInfo(String ClearMerStlFinalBookId);
	
	
	public List<ClearMerStlFinalBookBak> queryList(ClearMerStlFinalBookBak queryModel);

	/**
	 * sum(consum_num) || '#' || sum(consum_amt) || '#' || sum(tran_amt) || '#' ||sum(net_amt)  || '#' || sum(fee)  as fee
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearMerStlFinalBookBak queryModel);
	
	public int updatePayoutStatus(String flag,List<ClearMerStlFinalBookBak> list) ;
	
	public List<ClearMerStlFinalBookBak> selectGroupSumByExample(ClearMerStlFinalBookBak queryModel);
	
}
