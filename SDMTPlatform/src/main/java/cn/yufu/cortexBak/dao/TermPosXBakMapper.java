package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.TermPosXBak;
import cn.yufu.cortexBak.entity.TermPosXBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.TermPosXBakDao")
public interface TermPosXBakMapper {
    int countByExample(TermPosXBakExample example);

    int deleteByExample(TermPosXBakExample example);

    int insert(TermPosXBak record);

    int insertSelective(TermPosXBak record);

    List<TermPosXBak> selectByExample(TermPosXBakExample example);

    int updateByExampleSelective(@Param("record") TermPosXBak record, @Param("example") TermPosXBakExample example);

    int updateByExample(@Param("record") TermPosXBak record, @Param("example") TermPosXBakExample example);

	public TermPosXBak selectByTermcode(TermPosXBak termPosX);
}