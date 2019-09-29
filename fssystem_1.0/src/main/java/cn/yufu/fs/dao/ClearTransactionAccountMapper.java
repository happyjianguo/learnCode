package cn.yufu.fs.dao;


import cn.yufu.fs.entity.ClearTransactionAccount;
import cn.yufu.fs.entity.ClearTransactionAccountExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("fs.ClearTransactionAccountDao")
public interface ClearTransactionAccountMapper {
    int countByExample(@Param("example") ClearTransactionAccountExample example);

//    int deleteByExample(ClearTransactionAccountExample example);
//
//    int insert(ClearTransactionAccount record);
//
//    int insertSelective(ClearTransactionAccount record);

    List<ClearTransactionAccount> selectByExample(@Param("example") ClearTransactionAccountExample example);

    int updateByExampleSelective(@Param("record") ClearTransactionAccount record, @Param("example") ClearTransactionAccountExample example);

    int updateByExample(@Param("record") ClearTransactionAccount record, @Param("example") ClearTransactionAccountExample example);
    
    List<ClearTransactionAccount> selectPageByExample(@Param("example") ClearTransactionAccountExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

}