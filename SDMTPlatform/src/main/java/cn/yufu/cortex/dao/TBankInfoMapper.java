package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.TBankInfo;
import cn.yufu.cortex.entity.TBankInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.TBankInfoDao")
public interface TBankInfoMapper {
    int countByExample(TBankInfoExample example);

    int deleteByExample(TBankInfoExample example);

    int deleteByPrimaryKey(String bankCode);

    int insert(TBankInfo record);

    int insertSelective(TBankInfo record);

    List<TBankInfo> selectByExample(TBankInfoExample example);

    TBankInfo selectByPrimaryKey(String bankCode);

    int updateByExampleSelective(@Param("record") TBankInfo record, @Param("example") TBankInfoExample example);

    int updateByExample(@Param("record") TBankInfo record, @Param("example") TBankInfoExample example);

    int updateByPrimaryKeySelective(TBankInfo record);

    int updateByPrimaryKey(TBankInfo record);
}