package cn.yufu.fs.dao;

import cn.yufu.fs.entity.FkStlDayEndStep;
import cn.yufu.fs.entity.FkStlDayEndStepExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("fs.FkStlDayEndStepDao")

public interface FkStlDayEndStepMapper {
    int countByExample(@Param("example") FkStlDayEndStepExample example);

    int deleteByExample(FkStlDayEndStepExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(FkStlDayEndStep record);

    int insertSelective(FkStlDayEndStep record);

    List<FkStlDayEndStep> selectByExample(@Param("example") FkStlDayEndStepExample example);

    FkStlDayEndStep selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") FkStlDayEndStep record, @Param("example") FkStlDayEndStepExample example);

    int updateByExample(@Param("record") FkStlDayEndStep record, @Param("example") FkStlDayEndStepExample example);

    int updateByPrimaryKeySelective(FkStlDayEndStep record);

    int updateByPrimaryKey(FkStlDayEndStep record);
}