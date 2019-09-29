package cn.yufu.bak.dao;

import cn.yufu.bak.entity.OrderPayTlog;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("bak.OrderPayTlogDao")
public interface OrderPayTlogMapper {
	
    Integer countByExample(@Param("model")OrderPayTlog model);

    List<OrderPayTlog> selectByExample(@Param("model")OrderPayTlog model);
    
    List<OrderPayTlog> selectPageByExample(@Param("model")OrderPayTlog model, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);
    
}