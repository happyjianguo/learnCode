package cn.yufu.bak.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.bak.entity.ViewTLogRealTime;

@Repository("bak.ViewTLogRealTimeDao")
public interface ViewTLogRealTimeMapper {
   
	/**
	 *获取总记录数
	 */
	public Integer getCount(@Param("model")ViewTLogRealTime model); 
	
	/**
	 *获取满足查询条件的分页数据
	 */
	public List<ViewTLogRealTime> getPageList(@Param("model")ViewTLogRealTime model, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult); 
	
	/**
	 *获取满足查询条件的所有数据
	 */
	public List<ViewTLogRealTime> getAllList(@Param("model")ViewTLogRealTime model); 
	
	/**
	 *获取满足查询条件的合计值
	 */
	public String getSumAmt(@Param("model")ViewTLogRealTime model);
	
}