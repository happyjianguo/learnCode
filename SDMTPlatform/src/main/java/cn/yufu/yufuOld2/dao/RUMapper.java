package cn.yufu.yufuOld2.dao;

import cn.yufu.yufuOld2.entity.RU;
import cn.yufu.yufuOld2.entity.RUExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("yufuOld2.RUDao")
public interface RUMapper {
    int countByExample(RUExample example);

    int deleteByExample(RUExample example);

    int deleteByPrimaryKey(String lruid);

    int insert(RU record);

    int insertSelective(RU record);

    List<RU> selectByExampleWithBLOBs(RUExample example);

    List<RU> selectByExample(@Param("example") RUExample example);

    RU selectByPrimaryKey(String lruid);

    int updateByExampleSelective(@Param("record") RU record, @Param("example") RUExample example);

    int updateByExampleWithBLOBs(@Param("record") RU record, @Param("example") RUExample example);

    int updateByExample(@Param("record") RU record, @Param("example") RUExample example);

    int updateByPrimaryKeySelective(RU record);

    int updateByPrimaryKeyWithBLOBs(RU record);

    int updateByPrimaryKey(RU record);
    
    String getMaxRuId();
}