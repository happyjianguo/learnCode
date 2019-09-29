package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.OldCrdinfo;

public interface OldCrdinfoService {
	
	/**
	 *获取一条数据
	 */
	public List<OldCrdinfo> getExcelData(OldCrdinfo queryModelint, int startResult, 
			int endResult);
	
	/**
	 *获取总记录数
	 */
	public int getCounts(OldCrdinfo queryModel); 
	
	/**
	 *获取满足查询条件的分页数据
	 */
	public List<OldCrdinfo> selectPageList(OldCrdinfo queryModel, int startResult, 
			int endResult);
	
	/**
	 *获取总记录数
	 */
	public int queryCount(OldCrdinfo queryModel); 
	
	/**
	 *获取总人数
	 */
	public int getPeopleCount(OldCrdinfo queryModel); 
	
	/**
	 *获取满足查询条件的分页数据
	 */
	public List<OldCrdinfo> getPageList(OldCrdinfo queryModel, int startResult, 
			int endResult); 
	
	/**
	 *获取满足查询条件的分页数据
	 */
	public List<OldCrdinfo> getAllList(OldCrdinfo queryModel); 
}
