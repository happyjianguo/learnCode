package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.MrchClassBak;
import cn.yufu.cortexBak.entity.MrchClassBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.MrchClassBakDao")
public interface MrchClassBakMapper {
    int countByExample(MrchClassBakExample example);

    int deleteByExample(MrchClassBakExample example);

    int insert(MrchClassBak record);

    int insertSelective(MrchClassBak record);

    List<MrchClassBak> selectByExample(MrchClassBakExample example);

    int updateByExampleSelective(@Param("record") MrchClassBak record, @Param("example") MrchClassBakExample example);

    int updateByExample(@Param("record") MrchClassBak record, @Param("example") MrchClassBakExample example);
}