package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ViewStlBookDetail;


public interface ViewStlBookDetailService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ViewStlBookDetail queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ViewStlBookDetail> queryList(ViewStlBookDetail queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ViewStlBookDetail queryInfo(String ViewStlBookDetailId);
	
	public List<ViewStlBookDetail> queryList(ViewStlBookDetail queryModel);

	public String getSumAmt(String id);
	
	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ViewStlBookDetail> getList(ViewStlBookDetail queryModel, int startResult, int endResult);

	public String getSumAmtTotal(String id);
}
