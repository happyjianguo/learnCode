package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.EnckeyBak;
import cn.yufu.cortexBak.entity.EnckeyBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.EnckeyBakDao")
public interface EnckeyBakMapper {
    int countByExample(EnckeyBakExample example);

    int deleteByExample(EnckeyBakExample example);

    int insert(EnckeyBak record);

    int insertSelective(EnckeyBak record);

    List<EnckeyBak> selectByExample(EnckeyBakExample example);

    int updateByExampleSelective(@Param("record") EnckeyBak record, @Param("example") EnckeyBakExample example);

    int updateByExample(@Param("record") EnckeyBak record, @Param("example") EnckeyBakExample example);

	public EnckeyBak selectByTermcode(EnckeyBak enckey);
}