package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.MrchClass;
import cn.yufu.cortex.entity.MrchClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.MrchClassDao")
public interface MrchClassMapper {
    int countByExample(MrchClassExample example);

    int deleteByExample(MrchClassExample example);

    int insert(MrchClass record);

    int insertSelective(MrchClass record);

    List<MrchClass> selectByExample(MrchClassExample example);

    int updateByExampleSelective(@Param("record") MrchClass record, @Param("example") MrchClassExample example);

    int updateByExample(@Param("record") MrchClass record, @Param("example") MrchClassExample example);
}