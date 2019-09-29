package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.NumDescr;
import cn.yufu.cortex.entity.NumDescrExample;
import cn.yufu.cortex.entity.NumDescrKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.NumDescrDao")
public interface NumDescrMapper {
    int countByExample(NumDescrExample example);

    int deleteByExample(NumDescrExample example);

    int deleteByPrimaryKey(NumDescrKey key);

    int insert(NumDescr record);

    int insertSelective(NumDescr record);

    List<NumDescr> selectByExample(NumDescrExample example);

    NumDescr selectByPrimaryKey(NumDescrKey key);

    int updateByExampleSelective(@Param("record") NumDescr record, @Param("example") NumDescrExample example);

    int updateByExample(@Param("record") NumDescr record, @Param("example") NumDescrExample example);

    int updateByPrimaryKeySelective(NumDescr record);

    int updateByPrimaryKey(NumDescr record);
}