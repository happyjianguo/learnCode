package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ClearMerStlBook;
import cn.yufu.fs.entity.ClearMerStlBookExample;
@Repository("fs.ClearMerStlBookDao")

public interface ClearMerStlBookMapper {
    int countByExample(@Param("example") ClearMerStlBookExample example);

    int deleteByExample(ClearMerStlBookExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClearMerStlBook record);

    int insertSelective(ClearMerStlBook record);

    List<ClearMerStlBook> selectByExample(@Param("example") ClearMerStlBookExample example);

    ClearMerStlBook selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClearMerStlBook record, @Param("example") ClearMerStlBookExample example);

    int updateByExample(@Param("record") ClearMerStlBook record, @Param("example") ClearMerStlBookExample example);

    int updateByPrimaryKeySelective(ClearMerStlBook record);

    int updateByPrimaryKey(ClearMerStlBook record);
    
    List<ClearMerStlBook> selectPageByExample(@Param("example") ClearMerStlBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    String getSumAmtByExample(@Param("example") ClearMerStlBookExample example);

    List<ClearMerStlBook> selectExcelByExample(@Param("example") ClearMerStlBookExample example);
    
    /************************************************************************/
    Integer queryCount(@Param("example") ClearMerStlBookExample example);
    
    List<ClearMerStlBook> selectPageList(@Param("example") ClearMerStlBookExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

    String getSumAmtTotal(@Param("example") ClearMerStlBookExample example);

    List<ClearMerStlBook> getExcelList(@Param("example") ClearMerStlBookExample example);
   
    /**
	 * 发送邮件更新相应的数据信息
	 * 
	 */
	void sendMailUpdate(ClearMerStlBook queryModel);
}