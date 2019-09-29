package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.MerchantOrg;
import cn.yufu.cortex.entity.MerchantOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("cortex.MerchantOrgDao")
public interface MerchantOrgMapper {
    int countByExample(@Param("example")MerchantOrgExample example);

    List<MerchantOrg> selectByExample(@Param("example")MerchantOrgExample example);

    MerchantOrg selectByPrimaryKey(String orgId);
    
    //获取商户机构名称及ID集合
    List<MerchantOrg> getOrgNameAndID();

}