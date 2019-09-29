package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ViewMerFeeDetail;
import cn.yufu.fs.entity.ViewMerFeeDetailExample;
@Repository("fs.ViewMerFeeDetailDao")

public interface ViewMerFeeDetailMapper {
    int countByExample(@Param("example") ViewMerFeeDetailExample example);

    int deleteByExample(ViewMerFeeDetailExample example);

    int insert(ViewMerFeeDetail record);

    int insertSelective(ViewMerFeeDetail record);

    List<ViewMerFeeDetail> selectByExample(@Param("example") ViewMerFeeDetailExample example);

    int updateByExampleSelective(@Param("record") ViewMerFeeDetail record, @Param("example") ViewMerFeeDetailExample example);

    int updateByExample(@Param("record") ViewMerFeeDetail record, @Param("example") ViewMerFeeDetailExample example);
    
    List<ViewMerFeeDetail> selectPageByExample(@Param("example") ViewMerFeeDetailExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    String getSumAmtByExample(String id);

}