package cn.yufu.bak.dao;

import cn.yufu.bak.entity.NewCrdinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("bak.NewCrdinfoDao")
public interface NewCrdinfoMapper {
    
	public Integer getCounts(NewCrdinfo queryModel); 
	
	public List<NewCrdinfo> getExcelData(Map<String, Object> map);
	
	public List<Long> selectPageIds(Map<String, Object> map); 
	
	public List<NewCrdinfo> selectPageList(Map<String, Object> map);
	
	public Integer queryCount(NewCrdinfo queryModel); 
	
	public List<NewCrdinfo> getPageList(Map<String, Object> map); 
	
	public List<NewCrdinfo> getAllList(NewCrdinfo queryModel); 
	
	public Integer getPeopleCount(NewCrdinfo queryModel);
	
}