package cn.yufu.cortex.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.cortex.entity.CortexViewAccdetStat;
import cn.yufu.cortex.entity.CortexViewAccdetStatExample;
@Repository("cortex.CortexViewAccdetStatDao")

public interface CortexViewAccdetStatMapper {
    int countByExample(@Param("example") CortexViewAccdetStatExample example);

    int deleteByExample(CortexViewAccdetStatExample example);

    int insert(CortexViewAccdetStat record);

    int insertSelective(CortexViewAccdetStat record);

    List<CortexViewAccdetStat> selectByExample(@Param("example") CortexViewAccdetStatExample example);

    int updateByExampleSelective(@Param("record") CortexViewAccdetStat record, @Param("example") CortexViewAccdetStatExample example);

    int updateByExample(@Param("record") CortexViewAccdetStat record, @Param("example") CortexViewAccdetStatExample example);
    
    List<CortexViewAccdetStat> selectPageByExample(@Param("example") CortexViewAccdetStatExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    String getSumAmtByExample(@Param("example") CortexViewAccdetStatExample example);
    
    String getSumAmtBakByExample(@Param("example") CortexViewAccdetStatExample example);
}