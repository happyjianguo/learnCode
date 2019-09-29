package cn.yufu.bak.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.bak.entity.ViewTLogMrchno;
import cn.yufu.bak.entity.ViewTLogMrchnoExample;

@Repository("bak.ViewTLogMrchnoDao")
public interface ViewTLogMrchnoMapper {
    
	Integer countByExample(@Param("example")ViewTLogMrchnoExample example);

    List<ViewTLogMrchno> selectByExample(@Param("example")ViewTLogMrchnoExample example);

    ViewTLogMrchno selectByPrimaryKey(BigDecimal id);
    
    List<ViewTLogMrchno> selectPageByExample(@Param("example")ViewTLogMrchnoExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);
}