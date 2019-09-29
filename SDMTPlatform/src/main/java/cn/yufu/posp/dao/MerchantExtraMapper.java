package cn.yufu.posp.dao;

import cn.yufu.posp.entity.MerchantExtra;
import cn.yufu.posp.entity.MerchantExtraExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("posp.MerchantExtraDao")
public interface MerchantExtraMapper {
    int countByExample(MerchantExtraExample example);

    int deleteByExample(MerchantExtraExample example);

    int insert(MerchantExtra record);

    int insertSelective(MerchantExtra record);

    List<MerchantExtra> selectByExample(MerchantExtraExample example);

    int updateByExampleSelective(@Param("record") MerchantExtra record, @Param("example") MerchantExtraExample example);

    int updateByExample(@Param("record") MerchantExtra record, @Param("example") MerchantExtraExample example);

	public MerchantExtra selectByMerchantId(MerchantExtra merchantExtra);

	public void updateSelective(MerchantExtra merchantExtra);
}