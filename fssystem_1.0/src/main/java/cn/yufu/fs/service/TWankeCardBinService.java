package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.TWankeCardBin;

public interface TWankeCardBinService {
	
	//获取卡ID 最大值+1
	public String getCardId();
	
	public TWankeCardBin selectByPrimaryKey(String cardId);
	
	//总数
	public int queryCount(TWankeCardBin queryModel);
	
	//分页查询
	public List<TWankeCardBin> selectPageList(TWankeCardBin queryModel,
			int startResult, int endResult);
	
	//导出查询
	public List<TWankeCardBin> selectByExample(TWankeCardBin queryModel);
	
	//新增
	public Map<String, Object> insertCardBin(TWankeCardBin queryModel);
	
	//更新
	public Map<String, Object> updateCardBin(TWankeCardBin queryModel);
	
	//删除
	public Map<String, Object> deleteByPrimaryKey(TWankeCardBin queryModel);
	
}
