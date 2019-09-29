package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.TransactionRecordsHisT;
import cn.yufu.fs.entity.TransactionRecordsHisTExample;

@Repository("fs.TransactionRecordsHisTDao")
public interface TransactionRecordsHisTMapper {

    Integer countByExample(@Param("example")TransactionRecordsHisTExample example);

    List<TransactionRecordsHisT> selectByExample(@Param("example")TransactionRecordsHisTExample example);

    List<TransactionRecordsHisT> selectPageList(@Param("example")TransactionRecordsHisTExample example,
    		@Param("startResult")int startResult, @Param("endResult")int endResult);
    
}