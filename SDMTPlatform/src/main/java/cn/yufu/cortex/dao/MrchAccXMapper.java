package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.MrchAccX;
import cn.yufu.cortex.entity.MrchAccXExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.MrchAccXDao")
public interface MrchAccXMapper {
    int countByExample(MrchAccXExample example);

    int deleteByExample(MrchAccXExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MrchAccX record);

    int insertSelective(MrchAccX record);

    List<MrchAccX> selectByExample(MrchAccXExample example);

    MrchAccX selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MrchAccX record, @Param("example") MrchAccXExample example);

    int updateByExample(@Param("record") MrchAccX record, @Param("example") MrchAccXExample example);

    int updateByPrimaryKeySelective(MrchAccX record);

    int updateByPrimaryKey(MrchAccX record);

    String getCortexMrchAccXId();
}