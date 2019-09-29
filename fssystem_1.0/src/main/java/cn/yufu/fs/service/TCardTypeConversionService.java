package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.TCardTypeConversion;

public interface TCardTypeConversionService {
	
	public TCardTypeConversion get(Integer cardtype);
	
	//总数
	public int queryCount(TCardTypeConversion queryModel);
	
	//分页查询
	public List<TCardTypeConversion> selectPageList(TCardTypeConversion queryModel,
			int startResult, int endResult);
	
	//导出查询
	public List<TCardTypeConversion> selectByExample(TCardTypeConversion queryModel);
	
	//新增
	public Map<String, Object> insertCardType(TCardTypeConversion queryModel);
	
	//更新
	public Map<String, Object> updateCardType(TCardTypeConversion queryModel);
	
	//删除
	public Map<String, Object> deleteByPrimaryKey(TCardTypeConversion queryModel);
	
}
