package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.MrchAccXBak;
import cn.yufu.cortexBak.entity.MrchAccXBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.MrchAccXBakDao")
public interface MrchAccXBakMapper {
    int countByExample(MrchAccXBakExample example);

    int deleteByExample(MrchAccXBakExample example);

    int insert(MrchAccXBak record);

    int insertSelective(MrchAccXBak record);

    List<MrchAccXBak> selectByExample(MrchAccXBakExample example);

    int updateByExampleSelective(@Param("record") MrchAccXBak record, @Param("example") MrchAccXBakExample example);

    int updateByExample(@Param("record") MrchAccXBak record, @Param("example") MrchAccXBakExample example);
}