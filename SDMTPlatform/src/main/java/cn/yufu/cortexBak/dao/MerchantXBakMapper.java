package cn.yufu.cortexBak.dao;

import cn.yufu.cortexBak.entity.MerchantXBak;
import cn.yufu.cortexBak.entity.MerchantXBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortexBak.MerchantXBakDao")
public interface MerchantXBakMapper {
    int countByExample(MerchantXBakExample example);

    int deleteByExample(MerchantXBakExample example);

    int insert(MerchantXBak record);

    int insertSelective(MerchantXBak record);

    List<MerchantXBak> selectByExample(MerchantXBakExample example);

    int updateByExampleSelective(@Param("record") MerchantXBak record, @Param("example") MerchantXBakExample example);

    int updateByExample(@Param("record") MerchantXBak record, @Param("example") MerchantXBakExample example);
}