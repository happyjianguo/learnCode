package cn.yufu.fs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.TranRecordTotal;

/**
 * TranRecordTotalMapper.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年01月26日
 * 描述:交易统计Dao
 */
@Repository("fs.TranRecordTotalDao")
public interface TranRecordTotalMapper {

	public Integer queryCount(TranRecordTotal tranRecordTotal);
	
	public List<TranRecordTotal> getDataByMrch(Map<String, Object> map);
	
	public List<TranRecordTotal> getDataByPan(Map<String, Object> map);
	
	public List<TranRecordTotal> getDataByTerm(Map<String, Object> map);
	
	public List<TranRecordTotal> getDataByMrchAndTerm(Map<String, Object> map);
	
	public List<TranRecordTotal> getDataByCardType(Map<String, Object> map);
	
	
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
	public List<TranRecordTotal> queryTlogList(Map<String, Object> map);
	
	/**
	 * 非实时交易流水新老卡查询--导出
	 * */
	public List<TranRecordTotal> queryTlogExcel(TranRecordTotal tranRecordTotal);
	
	/**
	 * 非实时交易流水新老卡查询--金额合计
	 * */
	public String getSumAmt(TranRecordTotal tranRecordTotal);
}
