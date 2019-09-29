package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.CustomMerInfo;

public interface CustomMerInfoService {
	
	public int countByExample(CustomMerInfo queryModel);
	
	public List<CustomMerInfo> selectByExample(CustomMerInfo queryModel);
    
	public List<CustomMerInfo> selectPageByExample(CustomMerInfo queryModel,  
    		int startResult, int endResult);

	public CustomMerInfo selectByPrimay(String batchId);
	
	public int insert(CustomMerInfo record);
	
	public int deleteByPrimay(String batchId);

	public int updateByPrimay(CustomMerInfo model);
	
}
