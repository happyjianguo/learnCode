package cn.yufu.posp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.posp.entity.MerchantBase;
import cn.yufu.posp.entity.MerchantBaseExample;
import cn.yufu.posp.entity.PManager;
import cn.yufu.posp.entity.SysDict;
@Repository("posp.MerchantBaseDao")
public interface MerchantBaseMapper {
    int countByExample(MerchantBaseExample example);

    int deleteByExample(MerchantBaseExample example);

    int insert(MerchantBase record);

    int insertSelective(MerchantBase record);

    List<MerchantBase> selectByExample(@Param("example") MerchantBaseExample example);

    int updateByExampleSelective(@Param("record") MerchantBase record, @Param("example") MerchantBaseExample example);

    int updateByExample(@Param("record") MerchantBase record, @Param("example") MerchantBaseExample example);

	public List<SysDict> selectByType(@Param("type") String type);

	public List<PManager> findListByRoleId(PManager pManagerSM);

	public List<PManager> findListByRoleIdAndArea(PManager pManagerSM);

	public MerchantBase selectByMerchantId(MerchantBase merchantBase);

	public void updateSelective(MerchantBase merchantBase);
}