package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.MrchMtd;
import cn.yufu.cortex.entity.MrchMtdExample;
import cn.yufu.cortex.entity.MrchMtdKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.MrchMtdDao")
public interface MrchMtdMapper {
    int countByExample(MrchMtdExample example);

    int deleteByExample(MrchMtdExample example);

    int deleteByPrimaryKey(MrchMtdKey key);

    int insert(MrchMtd record);

    int insertSelective(MrchMtd record);

    List<MrchMtd> selectByExample(MrchMtdExample example);

    MrchMtd selectByPrimaryKey(MrchMtdKey key);

    int updateByExampleSelective(@Param("record") MrchMtd record, @Param("example") MrchMtdExample example);

    int updateByExample(@Param("record") MrchMtd record, @Param("example") MrchMtdExample example);

    int updateByPrimaryKeySelective(MrchMtd record);

    int updateByPrimaryKey(MrchMtd record);
}