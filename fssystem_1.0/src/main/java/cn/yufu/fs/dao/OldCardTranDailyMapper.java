package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.OldCardTranDaily;
import cn.yufu.fs.entity.OldCardTranDailyExample;

@Repository("fs.OldCardTranDailyDao")
public interface OldCardTranDailyMapper {
	
	Integer countByExample(@Param("example")OldCardTranDailyExample example);

    List<OldCardTranDaily> selectByExample(@Param("example")OldCardTranDailyExample example);
    
    List<OldCardTranDaily> selectPageByExample(@Param("example")OldCardTranDailyExample example, 
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    OldCardTranDaily selectByPrimaryKey(String id);

}