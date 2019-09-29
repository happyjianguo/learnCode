package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearFeeBook;
import cn.yufu.fs.entity.ClearFeeBookExample;
@Repository("fs.ClearFeeBookDao")

public interface ClearFeeBookMapper {
    int countByExample(@Param("example") ClearFeeBookExample example);

    int deleteByExample(ClearFeeBookExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClearFeeBook record);

    int insertSelective(ClearFeeBook record);

    List<ClearFeeBook> selectByExample(@Param("example") ClearFeeBookExample example);

    ClearFeeBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClearFeeBook record, @Param("example") ClearFeeBookExample example);

    int updateByExample(@Param("record") ClearFeeBook record, @Param("example") ClearFeeBookExample example);

    int updateByPrimaryKeySelective(ClearFeeBook record);

    int updateByPrimaryKey(ClearFeeBook record);
    
    List<ClearFeeBook> selectPageByExample(@Param("example") ClearFeeBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    String getSumAmtByExample(@Param("example") ClearFeeBookExample example);
}