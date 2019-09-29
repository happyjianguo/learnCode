package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ClearStatDailyAccdet;


public interface ClearStatDailyAccdetService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearStatDailyAccdet queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearStatDailyAccdet> queryList(ClearStatDailyAccdet queryModel, int startResult, int endResult);
	
	public List<ClearStatDailyAccdet> queryList(ClearStatDailyAccdet queryModel);
	/**
	 * sum(t.tran_num) || '#' || sum(t.tran_amt) || '#' || sum(t.ref_num) || '#' ||sum(t.ref_amt) || '#' || sum(t.stl_amt) || '#' || sum(t.fee) as fee
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearStatDailyAccdet queryModel);
}
