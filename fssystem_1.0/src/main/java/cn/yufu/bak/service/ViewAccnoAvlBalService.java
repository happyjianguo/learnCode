package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.ViewAccnoAvlBal;

public interface ViewAccnoAvlBalService {
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(ViewAccnoAvlBal queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ViewAccnoAvlBal> selectPageByExample(ViewAccnoAvlBal queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<ViewAccnoAvlBal> selectByExample(ViewAccnoAvlBal queryModel);

}
