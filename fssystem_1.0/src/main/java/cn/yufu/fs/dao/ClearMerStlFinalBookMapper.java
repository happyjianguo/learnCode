package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearMerStlFinalBook;
import cn.yufu.fs.entity.ClearMerStlFinalBookExample;
@Repository("fs.ClearMerStlFinalBookDao")

public interface ClearMerStlFinalBookMapper {
    Integer countByExample(@Param("example") ClearMerStlFinalBookExample example);

    Integer deleteByExample(ClearMerStlFinalBookExample example);

    Integer deleteByPrimaryKey(String id);

    Integer insert(ClearMerStlFinalBook record);

    Integer insertSelective(ClearMerStlFinalBook record);

    List<ClearMerStlFinalBook> selectByExample(@Param("example") ClearMerStlFinalBookExample example);

    ClearMerStlFinalBook selectByPrimaryKey(String id);

    Integer updateByExampleSelective(@Param("record") ClearMerStlFinalBook record, @Param("example") ClearMerStlFinalBookExample example);

    Integer updateByExample(@Param("record") ClearMerStlFinalBook record, @Param("example") ClearMerStlFinalBookExample example);

    Integer updateByPrimaryKeySelective(ClearMerStlFinalBook record);

    Integer updateByPrimaryKey(ClearMerStlFinalBook record);
    
    List<ClearMerStlFinalBook> selectPageByExample(@Param("example") ClearMerStlFinalBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    String getSumAmtByExample(@Param("example") ClearMerStlFinalBookExample example);
    
    int updatePayoutStatusOneToThree(ClearMerStlFinalBook record);
    
    int updatePayoutStatusZeroToOne(ClearMerStlFinalBook record);
    
    int updatePayoutStatusTwoToEight(ClearMerStlFinalBook record); 
    
    List<ClearMerStlFinalBook> selectGroupSumByExample(@Param("example") ClearMerStlFinalBookExample example);
    
    Integer shareBenefitReportCount(@Param("example") ClearMerStlFinalBookExample example);
    
    List<ClearMerStlFinalBook> shareBenefitReport(@Param("example") ClearMerStlFinalBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    List<ClearMerStlFinalBook> shareBenefitAllReport(@Param("example") ClearMerStlFinalBookExample example);
    
    ClearMerStlFinalBook shareBenefitSum(@Param("example") ClearMerStlFinalBookExample example);

}