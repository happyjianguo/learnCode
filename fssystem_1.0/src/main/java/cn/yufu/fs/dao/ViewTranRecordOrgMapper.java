package cn.yufu.fs.dao;

import cn.yufu.fs.entity.ViewTranRecordOrg;
import cn.yufu.fs.entity.ViewTranRecordOrgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ViewTranRecordOrgMapper.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月07日
 * 描述:机构/卡BIN分润Dao
 */
@Repository("fs.ViewTranRecordOrgDao")
public interface ViewTranRecordOrgMapper {
	
	Integer countByExample(@Param("example")ViewTranRecordOrgExample example);

	List<ViewTranRecordOrg> selectPageList(@Param("example")ViewTranRecordOrgExample example,
    		@Param("startResult")int startResult, @Param("endResult")int endResult);
	
    List<ViewTranRecordOrg> selectByExample(@Param("example")ViewTranRecordOrgExample example);

    List<ViewTranRecordOrg> selectByMrchno(String merchantnumber);
    
    ViewTranRecordOrg getSumAmt(@Param("example")ViewTranRecordOrgExample example);

}