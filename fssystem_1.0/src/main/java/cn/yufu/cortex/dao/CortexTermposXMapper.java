package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexTermposX;
import cn.yufu.cortex.entity.CortexTermposXExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexTermposXDao")

public interface CortexTermposXMapper {
    int countByExample(CortexTermposXExample example);

    int deleteByExample(CortexTermposXExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CortexTermposX record);

    int insertSelective(CortexTermposX record);

    List<CortexTermposX> selectByExample(@Param("example") CortexTermposXExample example);

    CortexTermposX selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CortexTermposX record, @Param("example") CortexTermposXExample example);

    int updateByExample(@Param("record") CortexTermposX record, @Param("example") CortexTermposXExample example);

    int updateByPrimaryKeySelective(CortexTermposX record);

    int updateByPrimaryKey(CortexTermposX record);
}