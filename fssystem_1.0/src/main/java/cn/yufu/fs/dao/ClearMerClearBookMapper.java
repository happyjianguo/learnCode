package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearMerClearBook;
import cn.yufu.fs.entity.ClearMerClearBookExample;

@Repository("fs.ClearMerClearBookDao")
public interface ClearMerClearBookMapper {
    int countByExample(@Param("example") ClearMerClearBookExample example);

    int deleteByExample(ClearMerClearBookExample example);

    int insert(ClearMerClearBook record);

    int insertSelective(ClearMerClearBook record);

    List<ClearMerClearBook> selectByExample(@Param("example") ClearMerClearBookExample example);

    int updateByExampleSelective(@Param("record") ClearMerClearBook record, @Param("example") ClearMerClearBookExample example);

    int updateByExample(@Param("record") ClearMerClearBook record, @Param("example") ClearMerClearBookExample example);
    
    List<ClearMerClearBook> selectPageByExample(@Param("example") ClearMerClearBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    String getSumAmtByExample(@Param("example") ClearMerClearBookExample example);    
    
    List<ClearMerClearBook> selectPaiMingByExample(@Param("example") ClearMerClearBookExample example);
    
    List<ClearMerClearBook> selectPaiMingPageByExample(@Param("example") ClearMerClearBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    Integer countPaiMingByExample(@Param("example") ClearMerClearBookExample example);

}