package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.CombineDate;
import cn.yufu.fs.entity.CombineDateExample;

@Repository("fs.CombineDateDao")
public interface CombineDateMapper {
	
	Integer countByExample(@Param("example")CombineDateExample example);
	
	Integer deleteByPrimaryKey(String id);

	Integer insert(CombineDate record);

	Integer insertSelective(CombineDate record);

    List<CombineDate> selectByExample(@Param("example")CombineDateExample example);
    
    List<CombineDate> selectPageByExample(@Param("example")CombineDateExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    CombineDate selectByPrimaryKey(String id);

    Integer updateByPrimaryKeySelective(CombineDate record);

    Integer updateByPrimaryKey(CombineDate record);
    
    CombineDate selectMaxEndDate(CombineDate record);
    
}