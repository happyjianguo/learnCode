package cn.yufu.fs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.TransactionSplitting;
import cn.yufu.fs.entity.TransactionSplittingExample;

@Repository("fs.TransactionSplittingDao")
public interface TransactionSplittingMapper {
    Integer countByExample(@Param("example")TransactionSplittingExample example);

    List<TransactionSplitting> selectByExample(@Param("example")TransactionSplittingExample example);
    
    TransactionSplitting getSumAmt(@Param("example")TransactionSplittingExample example);

    List<TransactionSplitting> selectPageByExample(@Param("example") TransactionSplittingExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    TransactionSplitting selectByPrimaryKey(String id);
    
    Integer countByExampleNoDate(@Param("example")TransactionSplittingExample example);

    List<TransactionSplitting> selectByExampleNoDate(@Param("example")TransactionSplittingExample example);

    List<TransactionSplitting> selectPageByExampleNoDate(@Param("example") TransactionSplittingExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
}