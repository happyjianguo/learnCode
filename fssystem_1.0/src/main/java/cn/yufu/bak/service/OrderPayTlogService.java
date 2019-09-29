package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.OrderPayTlog;

public interface OrderPayTlogService {
	
	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int countByExample(OrderPayTlog queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<OrderPayTlog> selectPageByExample(OrderPayTlog queryModel, int startResult, int endResult);
	
	/**
	 * @param queryModel
	 * @return
	 */
	public List<OrderPayTlog> selectByExample(OrderPayTlog queryModel);

}
