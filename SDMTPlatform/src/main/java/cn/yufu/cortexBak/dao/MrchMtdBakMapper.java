package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.MrchMtdBak;
import cn.yufu.cortexBak.entity.MrchMtdBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.MrchMtdBakDao")
public interface MrchMtdBakMapper {
    int countByExample(MrchMtdBakExample example);

    int deleteByExample(MrchMtdBakExample example);

    int insert(MrchMtdBak record);

    int insertSelective(MrchMtdBak record);

    List<MrchMtdBak> selectByExample(MrchMtdBakExample example);

    int updateByExampleSelective(@Param("record") MrchMtdBak record, @Param("example") MrchMtdBakExample example);

    int updateByExample(@Param("record") MrchMtdBak record, @Param("example") MrchMtdBakExample example);
}