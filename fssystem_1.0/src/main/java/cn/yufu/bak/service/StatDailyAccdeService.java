package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.StatDailyAccde;

public interface StatDailyAccdeService {
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(StatDailyAccde queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<StatDailyAccde> selectPageByExample(StatDailyAccde queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<StatDailyAccde> selectByExample(StatDailyAccde queryModel);
	
	
	/**
	 * 获取总金额
	 * @param queryModel
	 * @return
	 */
	public StatDailyAccde selectSumAvlBal(StatDailyAccde queryModel);

}
