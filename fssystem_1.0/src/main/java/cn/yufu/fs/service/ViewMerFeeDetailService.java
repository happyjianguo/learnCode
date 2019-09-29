package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ViewMerFeeDetail;


public interface ViewMerFeeDetailService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ViewMerFeeDetail queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ViewMerFeeDetail> queryList(ViewMerFeeDetail queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ViewMerFeeDetail queryInfo(String ViewMerFeeDetailId);
	
	public List<ViewMerFeeDetail> queryList(ViewMerFeeDetail queryModel);

	public String getSumAmt(String id);
}
