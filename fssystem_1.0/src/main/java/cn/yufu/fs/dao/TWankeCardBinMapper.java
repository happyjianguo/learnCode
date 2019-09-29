package cn.yufu.fs.dao;

import cn.yufu.fs.entity.TWankeCardBin;
import cn.yufu.fs.entity.TWankeCardBinExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ViewTranRecordOrgMapper.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月25日
 * 描述:万科卡BIN Dao
 */
@Repository("fs.TWankeCardBinDao")
public interface TWankeCardBinMapper {
	
	String getCardId();

    TWankeCardBin selectByPrimaryKey(String cardId);
	
    Integer countByExample(@Param("example")TWankeCardBinExample example);
    
    List<TWankeCardBin> selectByExample(@Param("example")TWankeCardBinExample example);
    
	List<TWankeCardBin> selectPageList(@Param("example")TWankeCardBinExample example,
    		@Param("startResult")int startResult, @Param("endResult")int endResult);
    
	//新增
	int insertCardBin(TWankeCardBin record);
	
	//更新
	int updateCardBin(TWankeCardBin record);
	
	//删除
	int deleteByPrimaryKey(TWankeCardBin record);
}