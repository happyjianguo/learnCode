package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.MerchantX;
import cn.yufu.cortex.entity.MerchantXExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.MerchantXDao")
public interface MerchantXMapper {
    int countByExample(MerchantXExample example);

    int deleteByExample(MerchantXExample example);

    int insert(MerchantX record);

    int insertSelective(MerchantX record);

    List<MerchantX> selectByExample(@Param("example") MerchantXExample example);

    int updateByExampleSelective(@Param("record") MerchantX record, @Param("example") MerchantXExample example);

    int updateByExample(@Param("record") MerchantX record, @Param("example") MerchantXExample example);

//	public MerchantX selectByMerchantId(MerchantX merchantX);
}