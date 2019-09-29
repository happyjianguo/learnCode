package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.ViewTLogRealTime;

public interface ViewTLogRealTimeService {
	
	/**
	 *获取总记录数
	 */
	public int queryCount(ViewTLogRealTime queryModel); 
	
	/**
	 *获取满足查询条件的分页数据
	 */
	public List<ViewTLogRealTime> getPageList(ViewTLogRealTime queryModel, int startResult, 
			int endResult); 
	
	/**
	 *获取满足查询条件的所有数据
	 */
	public List<ViewTLogRealTime> getAllList(ViewTLogRealTime queryModel); 
	
	/**
	 *获取满足查询条件的合计值
	 */
	public String getSumAmt(ViewTLogRealTime queryModel);
	
}
