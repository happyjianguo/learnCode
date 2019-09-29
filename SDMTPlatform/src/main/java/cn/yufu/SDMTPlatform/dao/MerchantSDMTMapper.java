package cn.yufu.SDMTPlatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.MerchantSDMTExample;
import cn.yufu.SDMTPlatform.entity.SysDict;
@Repository("sdmtpf.MerchantSDMTDao")
public interface MerchantSDMTMapper {
    int countByExample(@Param("example") MerchantSDMTExample example);

    int deleteByExample(MerchantSDMTExample example);

    int deleteByPrimaryKey(String merchantId);

    int insert(MerchantSDMT record);

    int insertSelective(MerchantSDMT record);

    List<MerchantSDMT> selectByExample(@Param("example") MerchantSDMTExample example);

    MerchantSDMT selectByPrimaryKey(String merchantId);

    int updateByExampleSelective(@Param("record") MerchantSDMT record, @Param("example") MerchantSDMTExample example);

    int updateByExample(@Param("record") MerchantSDMT record, @Param("example") MerchantSDMTExample example);

    int updateByPrimaryKeySelective(MerchantSDMT record);

    int updateByPrimaryKey(MerchantSDMT record);
    
	List<MerchantSDMT> selectPageByExample(@Param("example") MerchantSDMTExample example, @Param("startResult") int startResult, @Param("endResult") int endResult);

	public List<SysDict> selectByType(String type);
}