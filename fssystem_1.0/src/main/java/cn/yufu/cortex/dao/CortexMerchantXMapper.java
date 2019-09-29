package cn.yufu.cortex.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.cortex.entity.CortexMerchantX;
import cn.yufu.cortex.entity.CortexMerchantXExample;
@Repository("cortex.CortexMerchantXDao")

public interface CortexMerchantXMapper {
    int countByExample(CortexMerchantXExample example);

    int deleteByExample(CortexMerchantXExample example);

    int insert(CortexMerchantX record);

    int insertSelective(CortexMerchantX record);

    List<CortexMerchantX> selectByExample(@Param("example") CortexMerchantXExample example);

    int updateByExampleSelective(@Param("record") CortexMerchantX record, @Param("example") CortexMerchantXExample example);

    int updateByExample(@Param("record") CortexMerchantX record, @Param("example") CortexMerchantXExample example);
}