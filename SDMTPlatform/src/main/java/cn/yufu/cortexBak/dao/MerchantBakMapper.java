package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.MerchantBak;
import cn.yufu.cortexBak.entity.MerchantBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.MerchantBakDao")
public interface MerchantBakMapper {
    int countByExample(MerchantBakExample example);

    int deleteByExample(MerchantBakExample example);

    int insert(MerchantBak record);

    int insertSelective(MerchantBak record);

    List<MerchantBak> selectByExample(@Param("example") MerchantBakExample example);

    int updateByExampleSelective(@Param("record") MerchantBak record, @Param("example") MerchantBakExample example);

    int updateByExample(@Param("record") MerchantBak record, @Param("example") MerchantBakExample example);
}