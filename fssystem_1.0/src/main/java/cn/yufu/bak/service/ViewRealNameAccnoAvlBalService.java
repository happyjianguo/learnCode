package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.ViewRealNameAccnoAvlBal;

public interface ViewRealNameAccnoAvlBalService {
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(ViewRealNameAccnoAvlBal queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ViewRealNameAccnoAvlBal> selectPageByExample(ViewRealNameAccnoAvlBal queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<ViewRealNameAccnoAvlBal> selectByExample(ViewRealNameAccnoAvlBal queryModel);

}
