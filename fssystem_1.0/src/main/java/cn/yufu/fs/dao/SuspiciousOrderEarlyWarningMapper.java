package cn.yufu.fs.dao;

import cn.yufu.fs.entity.SuspiciousOrderEarlyWarning;
import cn.yufu.fs.entity.SuspiciousOrderEarlyWarningExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("fs.SuspiciousOrderEarlyWarningDao")
public interface SuspiciousOrderEarlyWarningMapper {
	
    int countByExample(@Param("example") SuspiciousOrderEarlyWarningExample example);

    int deleteByExample(@Param("example") SuspiciousOrderEarlyWarningExample example);

    int deleteByPrimaryKey(String ordercode);

    int insert(@Param("record") SuspiciousOrderEarlyWarning record);

    int insertSelective(@Param("record") SuspiciousOrderEarlyWarning record);

    List<SuspiciousOrderEarlyWarning> selectByExample(@Param("example") SuspiciousOrderEarlyWarningExample example);

    List<SuspiciousOrderEarlyWarning> selectPageByExample(@Param("example") SuspiciousOrderEarlyWarningExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);
    
    SuspiciousOrderEarlyWarning selectByPrimaryKey(String ordercode);

    int updateByExampleSelective(@Param("record") SuspiciousOrderEarlyWarning record, @Param("example") SuspiciousOrderEarlyWarningExample example);

    int updateByExample(@Param("record") SuspiciousOrderEarlyWarning record, @Param("example") SuspiciousOrderEarlyWarningExample example);

    int updateByPrimaryKeySelective(SuspiciousOrderEarlyWarning record);

    int updateByPrimaryKey(SuspiciousOrderEarlyWarning record);
}