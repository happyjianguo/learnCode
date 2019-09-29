package cn.yufu.fs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.CardTypeBook;
import cn.yufu.fs.entity.CardTypeBookExample;

@Repository("fs.CardTypeBookDao")
public interface CardTypeBookMapper {
	
	Integer countByExample(@Param("example")CardTypeBookExample example);

	Integer deleteByExample(@Param("example")CardTypeBookExample example);

	Integer deleteByPrimaryKey(String cardTypeId);

	Integer insert(CardTypeBook record);

    List<CardTypeBook> selectByExample(CardTypeBookExample example);
    
    List<CardTypeBook> selectPageByExample(@Param("example")CardTypeBookExample example,  @Param("startResult") int startResult, @Param("endResult") int endResult);

    CardTypeBook selectByPrimaryKey(String cardTypeId);

    Integer updateByPrimaryKey(CardTypeBook record);
}