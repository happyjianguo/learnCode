package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.TermPosBak;
import cn.yufu.cortexBak.entity.TermPosBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.TermPosBakDao")
public interface TermPosBakMapper {
    int countByExample(TermPosBakExample example);

    int deleteByExample(TermPosBakExample example);

    int insert(TermPosBak record);

    int insertSelective(TermPosBak record);

    List<TermPosBak> selectByExample(TermPosBakExample example);

    int updateByExampleSelective(@Param("record") TermPosBak record, @Param("example") TermPosBakExample example);

    int updateByExample(@Param("record") TermPosBak record, @Param("example") TermPosBakExample example);

	public TermPosBak selectByTermcode(TermPosBak termPos);
}