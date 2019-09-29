package cn.yufu.bak.dao;

import cn.yufu.bak.entity.Merchant;
import cn.yufu.bak.entity.MerchantExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("bak.MerchantDao")
public interface MerchantMapper {
    int countByExample(@Param("example") MerchantExample example);

    int deleteByExample(@Param("example") MerchantExample example);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    List<Merchant> selectByExample(@Param("example") MerchantExample example);

    int updateByExampleSelective(@Param("record") Merchant record, @Param("example") MerchantExample example);

    int updateByExample(@Param("record") Merchant record, @Param("example") MerchantExample example);
    
    List<Integer> getMrchstat(String mrchno);
}