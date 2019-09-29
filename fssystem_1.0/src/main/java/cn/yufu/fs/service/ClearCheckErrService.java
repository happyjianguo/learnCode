package cn.yufu.fs.service;

import java.util.List;
import java.util.Map;

import cn.yufu.fs.entity.ClearCheckErr;


public interface ClearCheckErrService {

	/**
	 * 查询总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(ClearCheckErr queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<ClearCheckErr> queryList(ClearCheckErr queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public ClearCheckErr queryInfo(String ClearCheckErrId);
	
	/**
	 * 更新一条记录
	 * 
	 * @return
	 */
	public int UpdateClearCheckErr(ClearCheckErr record);
	
	/**
	 * 添加一条记录
	 * 
	 * @param record
	 * @return
	 */
	public Map<String, Object> save(ClearCheckErr info);
	/**
	 * 编辑一条记录
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> edit(ClearCheckErr info);
	
	public int checkClearCheckErrId(String ClearCheckErrId);
	
	public List<ClearCheckErr> queryList(ClearCheckErr queryModel);
	/**
	 * 获取合计金额（交易金额和手续费：sum(coretran_amt)||'|'||sum(fee) as fee）
	 * @param queryModel
	 * @return
	 */
	public String getSumAmt(ClearCheckErr queryModel);
}
