package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearUnMerStlBook;
import cn.yufu.fs.entity.ClearUnMerStlBookExample;
@Repository("fs.ClearUnMerStlBookDao")

public interface ClearUnMerStlBookMapper {
    int countByExample(@Param("example") ClearUnMerStlBookExample example);

    int deleteByExample(ClearUnMerStlBookExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClearUnMerStlBook record);

    int insertSelective(ClearUnMerStlBook record);

    List<ClearUnMerStlBook> selectByExample(@Param("example") ClearUnMerStlBookExample example);

    ClearUnMerStlBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClearUnMerStlBook record, @Param("example") ClearUnMerStlBookExample example);

    int updateByExample(@Param("record") ClearUnMerStlBook record, @Param("example") ClearUnMerStlBookExample example);

    int updateByPrimaryKeySelective(ClearUnMerStlBook record);

    int updateByPrimaryKey(ClearUnMerStlBook record);
    
    List<ClearUnMerStlBook> selectPageByExample(@Param("example") ClearUnMerStlBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    String getSumAmtByExample(@Param("example") ClearUnMerStlBookExample example);
}