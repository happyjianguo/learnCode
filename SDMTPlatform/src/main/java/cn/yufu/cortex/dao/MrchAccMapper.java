package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.MrchAcc;
import cn.yufu.cortex.entity.MrchAccExample;
import cn.yufu.cortex.entity.MrchAccKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.MrchAccDao")
public interface MrchAccMapper {
    int countByExample(MrchAccExample example);

    int deleteByExample(MrchAccExample example);

    int deleteByPrimaryKey(MrchAccKey key);

    int insert(MrchAcc record);

    int insertSelective(MrchAcc record);

    List<MrchAcc> selectByExample(MrchAccExample example);

    MrchAcc selectByPrimaryKey(MrchAccKey key);

    int updateByExampleSelective(@Param("record") MrchAcc record, @Param("example") MrchAccExample example);

    int updateByExample(@Param("record") MrchAcc record, @Param("example") MrchAccExample example);

    int updateByPrimaryKeySelective(MrchAcc record);

    int updateByPrimaryKey(MrchAcc record);
}