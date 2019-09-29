package cn.yufu.bak.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.bak.entity.TransactionrecordsDaily;
import cn.yufu.bak.entity.TransactionrecordsDailyExample;

@Repository("bak.TransactionrecordsDailyDao")
public interface TransactionrecordsDailyMapper {
	
    Integer countByExample(@Param("example")TransactionrecordsDailyExample example);

    List<TransactionrecordsDaily> selectByExample(@Param("example")TransactionrecordsDailyExample example);

    List<TransactionrecordsDaily> selectPageList(@Param("example")TransactionrecordsDailyExample example,
    		@Param("startResult")int startResult, @Param("endResult")int endResult);
    
    TransactionrecordsDaily getNumAndAmt(@Param("example")TransactionrecordsDailyExample example);
    
}