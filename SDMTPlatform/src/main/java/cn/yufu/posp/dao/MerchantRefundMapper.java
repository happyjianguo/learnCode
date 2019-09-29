package cn.yufu.posp.dao;

import cn.yufu.posp.entity.MerchantRefund;
import cn.yufu.posp.entity.MerchantRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("posp.MerchantRefundDao")
public interface MerchantRefundMapper {
    int countByExample(MerchantRefundExample example);

    int deleteByExample(MerchantRefundExample example);

    int insert(MerchantRefund record);

    int insertSelective(MerchantRefund record);

    List<MerchantRefund> selectByExample(MerchantRefundExample example);

    int updateByExampleSelective(@Param("record") MerchantRefund record, @Param("example") MerchantRefundExample example);

    int updateByExample(@Param("record") MerchantRefund record, @Param("example") MerchantRefundExample example);

	cn.yufu.posp.entity.MerchantRefund selectByMerchantId(MerchantRefund merchantRefund);

	public void updateSelective(MerchantRefund merchantRefund);
}