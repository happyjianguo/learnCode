package cn.yufu.bak.dao;

import cn.yufu.bak.entity.StatDailyAccde;
import cn.yufu.bak.entity.StatDailyAccdeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("bak.StatDailyAccdeDao")
public interface StatDailyAccdeMapper {
   
	Integer countByExample(@Param("example")StatDailyAccdeExample example);

    List<StatDailyAccde> selectByExample(@Param("example")StatDailyAccdeExample example);

    List<StatDailyAccde> selectPageByExample(@Param("example")StatDailyAccdeExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);
    
    StatDailyAccde selectSumAvlBal(@Param("example")StatDailyAccdeExample example);
    
}