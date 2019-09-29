package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.ViewTranRecordOrg;

public interface ViewTranRecordOrgService {
	
	//总数
	public int queryCount(ViewTranRecordOrg queryModel);
	
	//分页查询
	public List<ViewTranRecordOrg> selectPageList(ViewTranRecordOrg queryModel,
			int startResult, int endResult);
	
	//导出查询
	public List<ViewTranRecordOrg> selectByExample(ViewTranRecordOrg queryModel);
	
	//获取金额合计
	public ViewTranRecordOrg getSumAmt(ViewTranRecordOrg queryModel);
	
}
