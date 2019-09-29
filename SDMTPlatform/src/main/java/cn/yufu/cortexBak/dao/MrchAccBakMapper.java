package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.MrchAccBak;
import cn.yufu.cortexBak.entity.MrchAccBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.MrchAccBakDao")
public interface MrchAccBakMapper {
    int countByExample(MrchAccBakExample example);

    int deleteByExample(MrchAccBakExample example);

    int insert(MrchAccBak record);

    int insertSelective(MrchAccBak record);

    List<MrchAccBak> selectByExample(MrchAccBakExample example);

    int updateByExampleSelective(@Param("record") MrchAccBak record, @Param("example") MrchAccBakExample example);

    int updateByExample(@Param("record") MrchAccBak record, @Param("example") MrchAccBakExample example);
}