package cn.yufu.fs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearStatDailyAccdetBak;

@Repository("fs.ClearStatDailyAccdetBakDao")
public interface ClearStatDailyAccdetBakMapper {
	
	public Integer queryCount(ClearStatDailyAccdetBak queryModel);
	
	public List<ClearStatDailyAccdetBak> selectPageList(Map<String, Object> map);
	
	public ClearStatDailyAccdetBak TotalCardSumAndBal(ClearStatDailyAccdetBak queryModel);
	
}