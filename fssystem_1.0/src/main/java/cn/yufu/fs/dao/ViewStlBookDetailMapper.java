package cn.yufu.fs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.fs.entity.ViewStlBookDetailExample;

@Repository("fs.ViewStlBookDetailDao")
public interface ViewStlBookDetailMapper {
    int countByExample(@Param("example") ViewStlBookDetailExample example);

    int deleteByExample(ViewStlBookDetailExample example);

    int insert(ViewStlBookDetail record);

    int insertSelective(ViewStlBookDetail record);

    List<ViewStlBookDetail> selectByExample(@Param("example") ViewStlBookDetailExample example);

    int updateByExampleSelective(@Param("record") ViewStlBookDetail record, @Param("example") ViewStlBookDetailExample example);

    int updateByExample(@Param("record") ViewStlBookDetail record, @Param("example") ViewStlBookDetailExample example);
    
    List<ViewStlBookDetail> selectPageByExample(@Param("example") ViewStlBookDetailExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
   
    String getSumAmtByExample(String id);
    
    List<ViewStlBookDetail> getList(@Param("example") ViewStlBookDetailExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    String getSumAmtTotal(String id);

}