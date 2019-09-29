package cn.yufu.cortex.dao;

import cn.yufu.cortex.entity.CortexTBankInfo;
import cn.yufu.cortex.entity.CortexTBankInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("cortex.CortexTBankInfoDao")

public interface CortexTBankInfoMapper {
    int countByExample(CortexTBankInfoExample example);

    int deleteByExample(CortexTBankInfoExample example);

    int deleteByPrimaryKey(String bankCode);

    int insert(CortexTBankInfo record);

    int insertSelective(CortexTBankInfo record);

    List<CortexTBankInfo> selectByExample(@Param("example") CortexTBankInfoExample example);

    CortexTBankInfo selectByPrimaryKey(String bankCode);

    int updateByExampleSelective(@Param("record") CortexTBankInfo record, @Param("example") CortexTBankInfoExample example);

    int updateByExample(@Param("record") CortexTBankInfo record, @Param("example") CortexTBankInfoExample example);

    int updateByPrimaryKeySelective(CortexTBankInfo record);

    int updateByPrimaryKey(CortexTBankInfo record);
}