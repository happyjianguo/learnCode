package cn.yufu.cortex.service;

import java.util.List;
import java.util.Map;

import cn.yufu.cortex.entity.CortexAccType;
import cn.yufu.cortex.entity.CortexArea;
import cn.yufu.cortex.entity.CortexCrdProduct;
import cn.yufu.cortex.entity.CortexCrdStatus;
import cn.yufu.cortex.entity.CortexMerchantX;
import cn.yufu.cortex.entity.CortexMerchantXExample;
import cn.yufu.cortex.entity.CortexMrchAccX;
import cn.yufu.cortex.entity.CortexMrchAccXExample;
import cn.yufu.cortex.entity.CortexSysParameter;
import cn.yufu.cortex.entity.CortexTBankInfo;
import cn.yufu.cortex.entity.CortexTBankInfoExample;
import cn.yufu.cortex.entity.CortexTermposX;
import cn.yufu.cortex.entity.CortexViewAccdetStat;

public interface CortexService {

	public List<CortexMerchantX> getCortexMerchantXList(CortexMerchantXExample example);

	public List<CortexMrchAccX> getCortexMrchAccXList(CortexMrchAccXExample example);

	public List<CortexTermposX> getCortexTermposXList(String merNo,String termCode);

	public List<CortexSysParameter> getCortexSysParameterList(CortexSysParameter sysParamter);
	
	public List<CortexArea> getCortexAreaList(CortexArea cortexArea);
	
	public List<CortexTBankInfo> getCortexTBankInfoList(CortexTBankInfoExample example);
	
	public List<CortexCrdStatus> getCortexCrdStatusList(CortexCrdStatus example);
	
	public CortexMerchantX getCortexMerchantXByMerNo(String merNo);	
	/**
	 * 获取卡产品码表
	 * @return
	 */
	public Map<String,String> getCortexCrdProductMap();
	/**
	 * 根据参数类型获取参数码表
	 * @param paramType
	 * @return
	 */
	public Map<String, String> getSysParamMapByParamType(String paramType) ;
	
	public List<CortexSysParameter> getSysParamListByParamType(String paramType) ;
	
	public List<CortexCrdProduct> getCortexCrdProductList(CortexCrdProduct model);

	public List<CortexAccType> getCortexAccTypeList(CortexAccType model);
	
	public int queryCnt(CortexViewAccdetStat queryModel);

	public List<CortexViewAccdetStat> queryList(CortexViewAccdetStat queryModel, int startResult, int endResult);
	
	public List<CortexViewAccdetStat> queryList(CortexViewAccdetStat queryModel);

	public String getSumAmt(CortexViewAccdetStat queryModel);
	
	public String getSumAmtBak(CortexViewAccdetStat queryModel);
	
}
