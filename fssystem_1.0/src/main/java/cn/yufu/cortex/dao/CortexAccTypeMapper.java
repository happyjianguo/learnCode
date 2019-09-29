package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexAccType;
import cn.yufu.cortex.entity.CortexAccTypeExample;
import cn.yufu.cortex.entity.CortexAccTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexAccTypeDao")
public interface CortexAccTypeMapper {
    int countByExample(@Param("example") CortexAccTypeExample example);

    int deleteByExample(CortexAccTypeExample example);

    int deleteByPrimaryKey(CortexAccTypeKey key);

    int insert(CortexAccType record);

    int insertSelective(CortexAccType record);

    List<CortexAccType> selectByExample(@Param("example") CortexAccTypeExample example);

    CortexAccType selectByPrimaryKey(CortexAccTypeKey key);

    int updateByExampleSelective(@Param("record") CortexAccType record, @Param("example") CortexAccTypeExample example);

    int updateByExample(@Param("record") CortexAccType record, @Param("example") CortexAccTypeExample example);

    int updateByPrimaryKeySelective(CortexAccType record);

    int updateByPrimaryKey(CortexAccType record);
}