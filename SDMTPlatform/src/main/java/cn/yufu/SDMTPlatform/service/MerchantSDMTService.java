package cn.yufu.SDMTPlatform.service;

import java.util.List;
import java.util.Map;

import cn.yufu.SDMTPlatform.entity.MerchantSDMT;
import cn.yufu.SDMTPlatform.entity.TerminalSDMT;
import cn.yufu.cortex.entity.MerchantXRecord;

public interface MerchantSDMTService {

	/**
	 * 查询清算报文总数
	 * 
	 * @param queryModel
	 * @return
	 */
	public int queryCnt(MerchantSDMT queryModel);

	/**
	 * 分页查询信息
	 * 
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return
	 */
	public List<MerchantSDMT> queryList(MerchantSDMT queryModel, int startResult, int endResult);

	/**
	 * 查询一条信息
	 * 
	 * @param param
	 * @return
	 */
	public MerchantSDMT queryInfo(String merchantId);
	
	/**
	 * 更新一条记录
	 * 
	 * @return
	 */
	public int UpdateMerchantSDMT(MerchantSDMT record);
	
	/**
	 * 添加一条记录
	 * 
	 * @param record
	 * @return
	 */
	public Map<String, Object> save(MerchantSDMT info, MerchantXRecord merchantXRecord,boolean b);
	/**
	 * 添加一条记录
	 * 商户+终端
	 * 
	 * @param record
	 * @return
	 */
	public Map<String, Object> saveMerchantAndTermial(MerchantSDMT info, MerchantXRecord merchantXRecord,boolean b,TerminalSDMT infos);
	/**
	 * 编辑一条记录
	 * 
	 * @param info
	 * @return
	 */
	public Map<String, Object> edit(MerchantSDMT info);
	/**
	 * 编辑一条商户扩展记录
	 * 
	 * @param info
	 * @param merchantXRecord 
	 * @return
	 */
	public Map<String, Object> extendEdit(MerchantSDMT info, MerchantXRecord merchantXRecord);
	
	public int checkMerchantId(String merchantId);
}
