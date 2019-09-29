package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.Enckey;
import cn.yufu.cortex.entity.EnckeyExample;
import cn.yufu.cortex.entity.MerchantXRecord;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.EnckeyDao")
public interface EnckeyMapper {
    int countByExample(EnckeyExample example);

    int deleteByExample(EnckeyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Enckey record);

    int insertSelective(Enckey record);

    List<Enckey> selectByExample(@Param("example") EnckeyExample example);

    Enckey selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Enckey record, @Param("example") EnckeyExample example);

    int updateByExample(@Param("record") Enckey record, @Param("example") EnckeyExample example);

    int updateByPrimaryKeySelective(Enckey record);

    int updateByPrimaryKey(Enckey record);
    
    String getCortexEnckeyId();

	public Enckey selectByTermCode(Enckey enckey);

	public void saveEditMerchantLog(MerchantXRecord merchantXRecord);
}