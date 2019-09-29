package cn.yufu.bak.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.bak.entity.Transactionrecords;
import cn.yufu.bak.entity.TransactionrecordsExample;

@Repository("bak.TransactionrecordsDao")
public interface TransactionrecordsMapper {
	
    Integer countByExample(@Param("example")TransactionrecordsExample example);

    List<Transactionrecords> selectByExample(@Param("example")TransactionrecordsExample example);

    List<Transactionrecords> selectPageList(@Param("example")TransactionrecordsExample example,
    		@Param("startResult")int startResult, @Param("endResult")int endResult);
    
    Transactionrecords selectByPrimaryKey(Integer id);

}