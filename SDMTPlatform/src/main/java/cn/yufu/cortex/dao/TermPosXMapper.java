package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.TermPosX;
import cn.yufu.cortex.entity.TermPosXExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.TermPosXDao")
public interface TermPosXMapper {
    int countByExample(TermPosXExample example);

    int deleteByExample(TermPosXExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TermPosX record);

    int insertSelective(TermPosX record);

    List<TermPosX> selectByExample(TermPosXExample example);

    TermPosX selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TermPosX record, @Param("example") TermPosXExample example);

    int updateByExample(@Param("record") TermPosX record, @Param("example") TermPosXExample example);

    int updateByPrimaryKeySelective(TermPosX record);

    int updateByPrimaryKey(TermPosX record);

	public TermPosX selectByTermPos(TermPosX termPosX);
}