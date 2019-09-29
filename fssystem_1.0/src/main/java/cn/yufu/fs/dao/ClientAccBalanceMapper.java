package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClientAccBalance;
import cn.yufu.fs.entity.ClientAccBalanceExample;
@Repository("fs.ClientAccBalanceDao")

public interface ClientAccBalanceMapper {
    int countByExample(@Param("example") ClientAccBalanceExample example);

    int deleteByExample(ClientAccBalanceExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClientAccBalance record);

    int insertSelective(ClientAccBalance record);

    List<ClientAccBalance> selectByExample(@Param("example") ClientAccBalanceExample example);

    ClientAccBalance selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClientAccBalance record, @Param("example") ClientAccBalanceExample example);

    int updateByExample(@Param("record") ClientAccBalance record, @Param("example") ClientAccBalanceExample example);

    int updateByPrimaryKeySelective(ClientAccBalance record);

    int updateByPrimaryKey(ClientAccBalance record);
    
    List<ClientAccBalance> selectPageByExample(@Param("example") ClientAccBalanceExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    String getSumAmtByExample(@Param("example") ClientAccBalanceExample example);
    
}