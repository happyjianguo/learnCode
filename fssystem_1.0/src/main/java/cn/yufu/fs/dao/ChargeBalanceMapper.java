package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ChargeBalance;
import cn.yufu.fs.entity.ChargeBalanceExample;

@Repository("fs.ChargeBalanceDao")
public interface ChargeBalanceMapper {
	
	String getSequenceId();
    
	Integer countByExample(@Param("example")ChargeBalanceExample example);

	Integer deleteByPrimaryKey(String id);
	
	Integer cancelByPrimaryKey(String id);

	Integer insert(ChargeBalance record);

    List<ChargeBalance> selectByExample(@Param("example")ChargeBalanceExample example);
    
    List<ChargeBalance> selectPageByExample(@Param("example")ChargeBalanceExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    ChargeBalance selectByPrimaryKey(String id);

    Integer updateByExampleSelective(@Param("record") ChargeBalance record, @Param("example") ChargeBalanceExample example);

    Integer updateByExample(@Param("record") ChargeBalance record, @Param("example") ChargeBalanceExample example);

    Integer updateByPrimaryKeySelective(ChargeBalance record);

    Integer updateByPrimaryKey(ChargeBalance record);
}