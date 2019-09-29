package cn.yufu.fs.dao;

import cn.yufu.fs.entity.OldCrdinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("bak.OldCrdinfoDao")
public interface OldCrdinfoMapper {
    
	public Integer getCounts(OldCrdinfo queryModel); 
	
	public List<OldCrdinfo> getExcelData(Map<String, Object> map);
	
	public List<OldCrdinfo> selectPageList(Map<String, Object> map);
	
	public Integer queryCount(OldCrdinfo queryModel); 
	
	public List<OldCrdinfo> getPageList(Map<String, Object> map); 
	
	public List<OldCrdinfo> getAllList(OldCrdinfo queryModel); 
	
	public Integer getPeopleCount(OldCrdinfo queryModel);
	
}