package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.OrderRanking;
import cn.yufu.fs.entity.OrderRankingExample;

@Repository("fs.OrderRankingDao")
public interface OrderRankingMapper {
	
    Integer countByExample(@Param("example")OrderRankingExample example);
    
    List<OrderRanking> selectPageByExample(@Param("example")OrderRankingExample example,  
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    List<OrderRanking> selectByExample(@Param("example")OrderRankingExample example);

}