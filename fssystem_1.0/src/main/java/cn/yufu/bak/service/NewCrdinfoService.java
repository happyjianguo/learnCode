package cn.yufu.bak.service;

import java.util.List;

import cn.yufu.bak.entity.NewCrdinfo;

public interface NewCrdinfoService {
	

	/**
	 *获取一页数据
	 */
	public List<NewCrdinfo> getExcelData(NewCrdinfo queryModel, int startResult, 
			int endResult);  
	
	/**
	 *获取总记录数
	 */
	public int getCounts(NewCrdinfo queryModel); 
	
	/**
	 *获取总人数
	 */
	public int getPeopleCount(NewCrdinfo queryModel); 
	
	/**
	 * 获取分页ID
	 */
	public List<NewCrdinfo> selectPageList(NewCrdinfo queryModel, int startResult, 
			int endResult); 
	
	/**
	 *获取总记录数
	 */
	public int queryCount(NewCrdinfo queryModel); 
	
	/**
	 *获取满足查询条件的分页数据
	 */
	public List<NewCrdinfo> getPageList(NewCrdinfo queryModel, int startResult, 
			int endResult); 
	
	/**
	 *获取满足查询条件的分页数据
	 */
	public List<NewCrdinfo> getAllList(NewCrdinfo queryModel); 
}
