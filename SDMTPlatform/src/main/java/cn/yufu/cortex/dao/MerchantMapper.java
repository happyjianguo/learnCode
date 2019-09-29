package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.Merchant;
import cn.yufu.cortex.entity.MerchantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.MerchantDao")
public interface MerchantMapper {
    int countByExample(MerchantExample example);

    int deleteByExample(MerchantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    List<Merchant> selectByExampleWithBLOBs(MerchantExample example);

    List<Merchant> selectByExample(@Param("example") MerchantExample example);

    Merchant selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Merchant record, @Param("example") MerchantExample example);

    int updateByExampleWithBLOBs(@Param("record") Merchant record, @Param("example") MerchantExample example);

    int updateByExample(@Param("record") Merchant record, @Param("example") MerchantExample example);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKeyWithBLOBs(Merchant record);

    int updateByPrimaryKey(Merchant record);
    
    String getCortexMerchantId();
}