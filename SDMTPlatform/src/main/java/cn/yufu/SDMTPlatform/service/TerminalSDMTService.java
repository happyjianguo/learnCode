package cn.yufu.SDMTPlatform.service;

import java.util.List;
import java.util.Map;

import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMTKey;

public interface TerminalSDMTService {

	/**
	 * 查询清算报文总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(TerminalSDMT queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<TerminalSDMT> queryList(TerminalSDMT queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public TerminalSDMT queryInfo(TerminalSDMTKey TerminalId);
	
	/**
	 * 更新一条记录
	 * 
	 * @return
	 */
	public int UpdateTerminalSDMT(TerminalSDMT record);
	
	/**
	 * 添加一条记录
	 * 
	 * @param record
	 * @return
	 */
	public Map<String, Object> save(TerminalSDMT info);
	/**
	 * 编辑一条记录
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> edit(TerminalSDMT info);
	/**
	 * 判断该商户下终端号是否重复
	 * @param merchantId
	 * @param termCode
	 * @return	大于0重复
	 */
	public int checkTerminal(String merchantId,String termCode);
}
