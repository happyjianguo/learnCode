package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearStatDailyAccdet;
import cn.yufu.fs.entity.ClearStatDailyAccdetExample;
@Repository("fs.ClearStatDailyAccdetDao")

public interface ClearStatDailyAccdetMapper {
    int countByExample(@Param("example") ClearStatDailyAccdetExample example);

    int deleteByExample(ClearStatDailyAccdetExample example);

    int insert(ClearStatDailyAccdet record);

    int insertSelective(ClearStatDailyAccdet record);

    List<ClearStatDailyAccdet> selectByExample(@Param("example") ClearStatDailyAccdetExample example);

    int updateByExampleSelective(@Param("record") ClearStatDailyAccdet record, @Param("example") ClearStatDailyAccdetExample example);

    int updateByExample(@Param("record") ClearStatDailyAccdet record, @Param("example") ClearStatDailyAccdetExample example);
    
    List<ClearStatDailyAccdet> selectPageByExample(@Param("example") ClearStatDailyAccdetExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    String getSumAmtByExample(@Param("example") ClearStatDailyAccdetExample example);

}