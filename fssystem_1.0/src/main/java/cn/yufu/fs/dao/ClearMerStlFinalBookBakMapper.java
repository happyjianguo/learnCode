package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearMerStlFinalBookBak;
import cn.yufu.fs.entity.ClearMerStlFinalBookBakExample;
@Repository("fs.ClearMerStlFinalBookBakDao")

public interface ClearMerStlFinalBookBakMapper {
    Integer countByExample(@Param("example") ClearMerStlFinalBookBakExample example);

    List<ClearMerStlFinalBookBak> selectByExample(@Param("example") ClearMerStlFinalBookBakExample example);

    List<ClearMerStlFinalBookBak> selectPageByExample(@Param("example") ClearMerStlFinalBookBakExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    ClearMerStlFinalBookBak selectByPrimaryKey(String id);
    
    String getSumAmtByExample(@Param("example") ClearMerStlFinalBookBakExample example);
    
    int updatePayoutStatusOneToThree(ClearMerStlFinalBookBak record);
    
    int updatePayoutStatusZeroToOne(ClearMerStlFinalBookBak record);
    
    int updatePayoutStatusTwoToEight(ClearMerStlFinalBookBak record); 
   
    List<ClearMerStlFinalBookBak> selectGroupSumByExample(@Param("example") ClearMerStlFinalBookBakExample example);
    
}