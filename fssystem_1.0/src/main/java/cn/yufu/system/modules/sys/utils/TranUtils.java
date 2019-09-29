package cn.yufu.system.modules.sys.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yufu.cortex.service.CortexService;
import cn.yufu.system.common.utils.CacheUtils;
import cn.yufu.system.common.utils.SpringContextHolder;
import cn.yufu.system.modules.cortexs.dao.TTranTypeDao;
import cn.yufu.system.modules.cortexs.entity.TTranType;

/**
 * 与交易相关的缓存
 * 
 * */
@SuppressWarnings("unchecked")
public class TranUtils {
	
	private static TTranTypeDao tranTypeDao = SpringContextHolder.getBean(TTranTypeDao.class);
	private static CortexService cortexService = (CortexService)SpringContextHolder.getBean("cortex.CortexService");
	
	public static final String CACHE_TRAN_TYPE = "tranTypeCache";
	public static final String CACHE_TRAN_TYPE_LIST = "tranTypeList";
	public static final String CACHE_TRAN_TYPE_MAP = "tranTypeDescMap";
	
	public static final String CACHE_TRAN_TYPE_CORTEX = "tranTypeCortexCache";
	public static final String CACHE_TRAN_TYPE_CORTEX_MAP = "tranTypeCortexDescMap";
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		CacheUtils.removeAll(CACHE_TRAN_TYPE);
		CacheUtils.removeAll(CACHE_TRAN_TYPE_CORTEX);
	}
	
	/**
	 * 返回交易类型对应的描述
	 * 
	 * */
	public static String getTranTypeDesc(String txncode, String subTxncode, 
			String fncode){
		Map<String, String> tranTypeDescMap = (Map<String, String>)CacheUtils.get(CACHE_TRAN_TYPE, CACHE_TRAN_TYPE_MAP);
		if (null == tranTypeDescMap || tranTypeDescMap.isEmpty()) {
			tranTypeDescMap = new HashMap<String, String>();
			List<TTranType> findList = (List<TTranType>)CacheUtils.get(CACHE_TRAN_TYPE, CACHE_TRAN_TYPE_LIST);
			if(null == findList || findList.isEmpty()){
				findList = getTranTypeList();
			}
			String type = "";
			for (TTranType tTranType : findList) {
				type = tTranType.getTxncode() + tTranType.getSubTxncode() + tTranType.getFncode() + "";
				tranTypeDescMap.put(type, tTranType.getTranTypeDesc());
			}
			CacheUtils.put(CACHE_TRAN_TYPE, CACHE_TRAN_TYPE_MAP, tranTypeDescMap);
		}
		String type = txncode + subTxncode + fncode;
		String result = "";
		if (null != tranTypeDescMap && tranTypeDescMap.size() > 0) {
			result = tranTypeDescMap.get(type);
		}
		return result;
	}
	
	public static List<TTranType> getTranTypeList(){
		List<TTranType> findList = (List<TTranType>)CacheUtils.get(CACHE_TRAN_TYPE, CACHE_TRAN_TYPE_LIST);
		if(null == findList || findList.isEmpty()){
			findList = tranTypeDao.findList(new TTranType());
			CacheUtils.put(CACHE_TRAN_TYPE, CACHE_TRAN_TYPE_LIST, findList);
		}
		return findList;
	}
	
	/**
	 * CORTEX -- 码表 来源于 X平台 参数管理配置 SYS_PARAMETER表
	 * 返回交易类型对应的描述
	 * 
	 * */
	public static String getTranTypeCortexDesc(String tranType){
		Map<String, String> tranTypeDescMap = (Map<String, String>)CacheUtils.get(CACHE_TRAN_TYPE_CORTEX, CACHE_TRAN_TYPE_CORTEX_MAP);
		if (null == tranTypeDescMap || tranTypeDescMap.isEmpty()) {
			tranTypeDescMap = cortexService.getSysParamMapByParamType("FS_TRAN_TYPE");		
			CacheUtils.put(CACHE_TRAN_TYPE_CORTEX, CACHE_TRAN_TYPE_CORTEX_MAP, tranTypeDescMap);
		}
		String result = "";
		if (null != tranTypeDescMap && tranTypeDescMap.size() > 0) {
			result = tranTypeDescMap.get(tranType);
		}
		return result;
	}
	
}
