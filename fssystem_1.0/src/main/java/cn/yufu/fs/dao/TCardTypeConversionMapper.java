package cn.yufu.fs.dao;

import cn.yufu.fs.entity.TCardTypeConversion;
import cn.yufu.fs.entity.TCardTypeConversionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ViewTranRecordOrgMapper.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月25日
 * 描述:卡类型Dao
 */
@Repository("fs.TCardTypeConversionDao")
public interface TCardTypeConversionMapper {
	
	TCardTypeConversion get(Integer cardtype);
	
    Integer countByExample(@Param("example")TCardTypeConversionExample example);
    
    List<TCardTypeConversion> selectByExample(@Param("example")TCardTypeConversionExample example);
    
	List<TCardTypeConversion> selectPageList(@Param("example")TCardTypeConversionExample example,
    		@Param("startResult")int startResult, @Param("endResult")int endResult);
	
	//新增
	int insertCardType(TCardTypeConversion queryModel);
	
	//更新
	int updateCardType(TCardTypeConversion queryModel);
	
	//删除
	int deleteByPrimaryKey(TCardTypeConversion info);
}