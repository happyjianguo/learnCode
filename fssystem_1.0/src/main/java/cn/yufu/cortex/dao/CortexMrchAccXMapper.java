package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexMrchAccX;
import cn.yufu.cortex.entity.CortexMrchAccXExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexMrchAccXDao")

public interface CortexMrchAccXMapper {
    int countByExample(CortexMrchAccXExample example);

    int deleteByExample(CortexMrchAccXExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CortexMrchAccX record);

    int insertSelective(CortexMrchAccX record);

    List<CortexMrchAccX> selectByExample(@Param("example") CortexMrchAccXExample example);

    CortexMrchAccX selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CortexMrchAccX record, @Param("example") CortexMrchAccXExample example);

    int updateByExample(@Param("record") CortexMrchAccX record, @Param("example") CortexMrchAccXExample example);

    int updateByPrimaryKeySelective(CortexMrchAccX record);

    int updateByPrimaryKey(CortexMrchAccX record);
}