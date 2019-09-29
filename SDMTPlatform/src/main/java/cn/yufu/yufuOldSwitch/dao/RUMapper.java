package cn.yufu.yufuOldSwitch.dao;

import cn.yufu.yufuOldSwitch.entity.RU;
import cn.yufu.yufuOldSwitch.entity.RUExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("yufuOldSwitch.RUDao")
public interface RUMapper {
    int countByExample(RUExample example);

    int deleteByExample(RUExample example);

    int deleteByPrimaryKey(String lruid);

    int insert(RU record);

    int insertSelective(RU record);

    List<RU> selectByExampleWithBLOBs(RUExample example);

    List<RU> selectByExample(RUExample example);

    RU selectByPrimaryKey(String lruid);

    int updateByExampleSelective(@Param("record") RU record, @Param("example") RUExample example);

    int updateByExampleWithBLOBs(@Param("record") RU record, @Param("example") RUExample example);

    int updateByExample(@Param("record") RU record, @Param("example") RUExample example);

    int updateByPrimaryKeySelective(RU record);

    int updateByPrimaryKeyWithBLOBs(RU record);

    int updateByPrimaryKey(RU record);
}