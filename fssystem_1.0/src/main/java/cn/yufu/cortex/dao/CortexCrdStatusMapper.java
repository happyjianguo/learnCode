package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexCrdStatus;
import cn.yufu.cortex.entity.CortexCrdStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexCrdStatusDao")

public interface CortexCrdStatusMapper {
    int countByExample(@Param("example")CortexCrdStatusExample example);

    int deleteByExample(CortexCrdStatusExample example);

    int deleteByPrimaryKey(String statcode);

    int insert(CortexCrdStatus record);

    int insertSelective(CortexCrdStatus record);

    List<CortexCrdStatus> selectByExample(@Param("example") CortexCrdStatusExample example);

    CortexCrdStatus selectByPrimaryKey(String statcode);

    int updateByExampleSelective(@Param("record") CortexCrdStatus record, @Param("example") CortexCrdStatusExample example);

    int updateByExample(@Param("record") CortexCrdStatus record, @Param("example") CortexCrdStatusExample example);

    int updateByPrimaryKeySelective(CortexCrdStatus record);

    int updateByPrimaryKey(CortexCrdStatus record);
}