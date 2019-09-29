package cn.yufu.fs.service;

import java.util.List;

import cn.yufu.fs.entity.TranRecordTotal;

public interface TranRecordTotalService {
	
	public Integer queryCount(TranRecordTotal tranRecordTotal);
	
	public List<TranRecordTotal> getDataByMrch(TranRecordTotal tranRecordTotal, 
			int startResult, int endResult);
	
	public List<TranRecordTotal> getDataByPan(TranRecordTotal tranRecordTotal, 
			int startResult, int endResult);
	
	public List<TranRecordTotal> getDataByTerm(TranRecordTotal tranRecordTotal, 
			int startResult, int endResult);
	
	public List<TranRecordTotal> getDataByMrchAndTerm(TranRecordTotal tranRecordTotal, 
			int startResult, int endResult);
	
	public List<TranRecordTotal> getDataByCardType(TranRecordTotal tranRecordTotal, 
			int startResult, int endResult);
	
	public List<TranRecordTotal> getExcelDataByMrch(TranRecordTotal tranRecordTotal);
	
	public List<TranRecordTotal> getExcelDataByPan(TranRecordTotal tranRecordTotal);
	
	public List<TranRecordTotal> getExcelDataByTerm(TranRecordTotal tranRecordTotal);
	
	public List<TranRecordTotal> getExcelDataByMrchAndTerm(TranRecordTotal tranRecordTotal);
	
	public List<TranRecordTotal> getExcelDataByCardType(TranRecordTotal tranRecordTotal);
	
	/**
	 * 非实时交易流水新老卡查询--总数
	 * */
	public Integer queryTlogCount(TranRecordTotal tranRecordTotal);
	
	/**
	 * 非实时交易流水新老卡查询--分页查询
	 * */
	public List<TranRecordTotal> queryTlogList(TranRecordTotal tranRecordTotal, int startResult, int endResult);
	
	/**
	 * 非实时交易流水新老卡查询--导出
	 * */
	public List<TranRecordTotal> queryTlogExcel(TranRecordTotal tranRecordTotal);

	/**
	 * 非实时交易流水新老卡查询--金额合计
	 * */
	public String getSumAmt(TranRecordTotal tranRecordTotal);

}
