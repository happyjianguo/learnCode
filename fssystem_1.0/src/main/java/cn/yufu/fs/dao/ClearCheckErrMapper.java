package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearCheckErr;
import cn.yufu.fs.entity.ClearCheckErrExample;
@Repository("fs.ClearCheckErrDao")
public interface ClearCheckErrMapper {
    int countByExample(@Param("example") ClearCheckErrExample example);

    int deleteByExample(ClearCheckErrExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClearCheckErr record);

    int insertSelective(ClearCheckErr record);

    List<ClearCheckErr> selectByExample(@Param("example") ClearCheckErrExample example);

    ClearCheckErr selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClearCheckErr record, @Param("example") ClearCheckErrExample example);

    int updateByExample(@Param("record") ClearCheckErr record, @Param("example") ClearCheckErrExample example);

    int updateByPrimaryKeySelective(ClearCheckErr record);

    int updateByPrimaryKey(ClearCheckErr record);

    List<ClearCheckErr> selectPageByExample(@Param("example") ClearCheckErrExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    String getKeyId();
    
    String getSumAmtByExample(@Param("example") ClearCheckErrExample example);
}