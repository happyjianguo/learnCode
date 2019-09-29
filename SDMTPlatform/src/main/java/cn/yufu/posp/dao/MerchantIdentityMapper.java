package cn.yufu.posp.dao;

import cn.yufu.posp.entity.MerchantIdentity;
import cn.yufu.posp.entity.MerchantIdentityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("posp.MerchantIdentityDao")
public interface MerchantIdentityMapper {
    int countByExample(MerchantIdentityExample example);

    int deleteByExample(MerchantIdentityExample example);

    int insert(MerchantIdentity record);

    int insertSelective(MerchantIdentity record);

    List<MerchantIdentity> selectByExample(MerchantIdentityExample example);

    int updateByExampleSelective(@Param("record") MerchantIdentity record, @Param("example") MerchantIdentityExample example);

    int updateByExample(@Param("record") MerchantIdentity record, @Param("example") MerchantIdentityExample example);

	cn.yufu.posp.entity.MerchantIdentity selectByMerchantId(MerchantIdentity merchantIdentity);

	public void updateSelective(MerchantIdentity merchantIdentity);
}