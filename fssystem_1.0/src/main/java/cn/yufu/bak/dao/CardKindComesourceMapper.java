package cn.yufu.bak.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.bak.entity.CardKindComesource;
import cn.yufu.bak.entity.CardKindComesourceExample;

@Repository("bak.CardKindComesourceDao")
public interface CardKindComesourceMapper {
	
Integer countByExample(@Param("example")CardKindComesourceExample example);
	
    Integer deleteByPrimaryKey(String cardnumber);

    Integer insert(CardKindComesource record);

    Integer insertSelective(CardKindComesource record);

    List<CardKindComesource> selectByExample(@Param("example")CardKindComesourceExample example);
    
    List<CardKindComesource> selectPageByExample(@Param("example")CardKindComesourceExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    CardKindComesource selectByPrimaryKey(String cardnumber);

    Integer updateByPrimaryKeySelective(CardKindComesource record);

    Integer updateByPrimaryKey(CardKindComesource record);
    
    String getMaxId();
}