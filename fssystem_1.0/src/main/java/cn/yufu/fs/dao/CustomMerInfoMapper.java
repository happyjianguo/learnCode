package cn.yufu.fs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.CustomMerInfo;
import cn.yufu.fs.entity.CustomMerInfoExample;

@Repository("fs.CustomMerInfoDao")
public interface CustomMerInfoMapper {
	
	Integer countByExample(@Param("example")CustomMerInfoExample example);

    Integer deleteByPrimay(String batchId);

    Integer insert(CustomMerInfo record);

    List<CustomMerInfo> selectByExample(@Param("example")CustomMerInfoExample example);
    
    List<CustomMerInfo> selectPageByExample(@Param("example")CustomMerInfoExample example,  
    		@Param("startResult") int startResult, @Param("endResult") int endResult);

    Integer updateByPrimay(CustomMerInfo model);
    
    CustomMerInfo selectByPrimay(String batchId);
}