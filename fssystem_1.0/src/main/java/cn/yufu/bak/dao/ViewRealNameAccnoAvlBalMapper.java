package cn.yufu.bak.dao;

import cn.yufu.bak.entity.ViewRealNameAccnoAvlBal;
import cn.yufu.bak.entity.ViewRealNameAccnoAvlBalExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("bak.ViewRealNameAccnoAvlBalDao")
public interface ViewRealNameAccnoAvlBalMapper {
    Integer countByExample(@Param("example")ViewRealNameAccnoAvlBalExample example);

    List<ViewRealNameAccnoAvlBal> selectByExample(@Param("example")ViewRealNameAccnoAvlBalExample example);

    List<ViewRealNameAccnoAvlBal> selectPageByExample(@Param("example")ViewRealNameAccnoAvlBalExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

}