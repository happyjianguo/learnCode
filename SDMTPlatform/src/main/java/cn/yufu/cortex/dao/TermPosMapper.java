package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.TermPos;
import cn.yufu.cortex.entity.TermPosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.TermPosDao")
public interface TermPosMapper {
    int countByExample(TermPosExample example);

    int deleteByExample(TermPosExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TermPos record);

    int insertSelective(TermPos record);

    List<TermPos> selectByExample(TermPosExample example);

    TermPos selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TermPos record, @Param("example") TermPosExample example);

    int updateByExample(@Param("record") TermPos record, @Param("example") TermPosExample example);

    int updateByPrimaryKeySelective(TermPos record);

    int updateByPrimaryKey(TermPos record);
    
    String getCortexTermPosId();
    
    Integer getTermNo(String merchantId);

	public TermPos selectByTermCode(TermPos termPos);
}