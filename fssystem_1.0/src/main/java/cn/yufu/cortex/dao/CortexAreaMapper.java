package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexArea;
import cn.yufu.cortex.entity.CortexAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexAreaDao")

public interface CortexAreaMapper {
    int countByExample(@Param("example") CortexAreaExample example);

    int deleteByExample(CortexAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CortexArea record);

    int insertSelective(CortexArea record);

    List<CortexArea> selectByExample(@Param("example") CortexAreaExample example);

    CortexArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CortexArea record, @Param("example") CortexAreaExample example);

    int updateByExample(@Param("record") CortexArea record, @Param("example") CortexAreaExample example);

    int updateByPrimaryKeySelective(CortexArea record);

    int updateByPrimaryKey(CortexArea record);
}