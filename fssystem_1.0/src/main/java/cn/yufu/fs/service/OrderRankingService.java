package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.OrderRanking;

public interface OrderRankingService {
	
    Integer countByExample(OrderRanking orderRanking);
    
    List<OrderRanking> selectPageByExample(OrderRanking orderRanking,  
    		 int startResult,  int endResult);

    List<OrderRanking> selectByExample(OrderRanking orderRanking);

	
}
