package cn.yufu.bak.dao;

import cn.yufu.bak.entity.ViewAccnoAvlBal;
import cn.yufu.bak.entity.ViewAccnoAvlBalExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("bak.ViewAccnoAvlBalDao")
public interface ViewAccnoAvlBalMapper {
    Integer countByExample(@Param("example")ViewAccnoAvlBalExample example);

    List<ViewAccnoAvlBal> selectByExample(@Param("example")ViewAccnoAvlBalExample example);
    
    List<ViewAccnoAvlBal> selectPageByExample(@Param("example")ViewAccnoAvlBalExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);
    
}