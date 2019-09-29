package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexCrdProduct;
import cn.yufu.cortex.entity.CortexCrdProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexCrdProductDao")

public interface CortexCrdProductMapper {
    int countByExample(CortexCrdProductExample example);

    int deleteByExample(CortexCrdProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CortexCrdProduct record);

    int insertSelective(CortexCrdProduct record);

    List<CortexCrdProduct> selectByExample(@Param("example") CortexCrdProductExample example);

    CortexCrdProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CortexCrdProduct record, @Param("example") CortexCrdProductExample example);

    int updateByExample(@Param("record") CortexCrdProduct record, @Param("example") CortexCrdProductExample example);

    int updateByPrimaryKeySelective(CortexCrdProduct record);

    int updateByPrimaryKey(CortexCrdProduct record);
}