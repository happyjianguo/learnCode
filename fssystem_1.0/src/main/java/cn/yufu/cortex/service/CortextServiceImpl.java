package cn.yufu.cortex.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.cortex.dao.CortexAccTypeMapper;
import cn.yufu.cortex.dao.CortexAreaMapper;
import cn.yufu.cortex.dao.CortexCrdProductMapper;
import cn.yufu.cortex.dao.CortexCrdStatusMapper;
import cn.yufu.cortex.dao.CortexMerchantXMapper;
import cn.yufu.cortex.dao.CortexMrchAccXMapper;
import cn.yufu.cortex.dao.CortexSysParameterMapper;
import cn.yufu.cortex.dao.CortexTBankInfoMapper;
import cn.yufu.cortex.dao.CortexTermposXMapper;
import cn.yufu.cortex.dao.CortexViewAccdetStatMapper;
import cn.yufu.cortex.entity.CortexAccType;
import cn.yufu.cortex.entity.CortexAccTypeExample;
import cn.yufu.cortex.entity.CortexArea;
import cn.yufu.cortex.entity.CortexAreaExample;
import cn.yufu.cortex.entity.CortexCrdProduct;
import cn.yufu.cortex.entity.CortexCrdProductExample;
import cn.yufu.cortex.entity.CortexCrdStatus;
import cn.yufu.cortex.entity.CortexCrdStatusExample;
import cn.yufu.cortex.entity.CortexMerchantX;
import cn.yufu.cortex.entity.CortexMerchantXExample;
import cn.yufu.cortex.entity.CortexMrchAccX;
import cn.yufu.cortex.entity.CortexMrchAccXExample;
import cn.yufu.cortex.entity.CortexSysParameter;
import cn.yufu.cortex.entity.CortexSysParameterExample;
import cn.yufu.cortex.entity.CortexTBankInfo;
import cn.yufu.cortex.entity.CortexTBankInfoExample;
import cn.yufu.cortex.entity.CortexTermposX;
import cn.yufu.cortex.entity.CortexTermposXExample;
import cn.yufu.cortex.entity.CortexViewAccdetStat;
import cn.yufu.cortex.entity.CortexViewAccdetStatExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;

@Service("cortex.CortexService")

public class CortextServiceImpl implements CortexService {
	Log log = Log.getLog(CortextServiceImpl.class);

	@Autowired
	@Qualifier("cortex.CortexMerchantXDao")
	private CortexMerchantXMapper CortexMerchantXDao;

	@Autowired
	@Qualifier("cortex.CortexMrchAccXDao")
	private CortexMrchAccXMapper CortexMrchAccXDao;
	
	@Autowired
	@Qualifier("cortex.CortexSysParameterDao")
	private CortexSysParameterMapper CortexSysParameterDao;
	
	@Autowired
	@Qualifier("cortex.CortexTBankInfoDao")
	private CortexTBankInfoMapper CortexTBankInfoDao;
	
	@Autowired
	@Qualifier("cortex.CortexTermposXDao")
	private CortexTermposXMapper CortexTermposXDao;
	
	@Autowired
	@Qualifier("cortex.CortexCrdProductDao")
	private CortexCrdProductMapper CortexCrdProductDao;	

	@Autowired
	@Qualifier("cortex.CortexAccTypeDao")
	private CortexAccTypeMapper CortexAccTypeDao;	

	@Autowired
	@Qualifier("cortex.CortexViewAccdetStatDao")
	private CortexViewAccdetStatMapper CortexViewAccdetStatDao;
	
	@Autowired
	@Qualifier("cortex.CortexCrdStatusDao")
	private CortexCrdStatusMapper CortexCrdStatusDao;

	@Autowired
	@Qualifier("cortex.CortexAreaDao")
	private CortexAreaMapper CortexAreaDao;
	
	@Override
	public List<CortexMerchantX> getCortexMerchantXList(
			CortexMerchantXExample example) {
		if (example!=null){
			List<CortexMerchantX> lst =this.CortexMerchantXDao.selectByExample(example);
			if(lst!=null&&lst.size()>0){
				return lst;
			}else{
				return null;
			}
		}else{
			return null;			
		}		
	}

	@Override
	public List<CortexMrchAccX> getCortexMrchAccXList(
			CortexMrchAccXExample example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CortexSysParameter> getCortexSysParameterList(CortexSysParameter sysParamter) {
		if(sysParamter==null){
			return null;
		}
		CortexSysParameterExample example=new CortexSysParameterExample();
		CortexSysParameterExample.Criteria criteria=example.createCriteria();
		if (!StringUtil.isEmpty(sysParamter.getParamType())){
			criteria.andParamTypeEqualTo(sysParamter.getParamType());
		}
		if (!StringUtil.isEmpty(sysParamter.getParamId())){
			criteria.andParamIdEqualTo(sysParamter.getParamId());
		}
		//启用标志   0：不启用 1：启用
		criteria.andEnableEqualTo("1");		
		example.setOrderByClause(" PARAM_TYPE,PARAM_ID ASC ");
		return this.CortexSysParameterDao.selectByExample(example);
	}

	@Override
	public List<CortexTBankInfo> getCortexTBankInfoList(
			CortexTBankInfoExample example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CortexTermposX> getCortexTermposXList(String merNo,String termCode) {
		List<CortexTermposX> lst=new ArrayList<CortexTermposX>();
		if (!StringUtil.isEmpty(merNo)){
			CortexTermposXExample example=new CortexTermposXExample();
			CortexTermposXExample.Criteria criteria=example.createCriteria();
			criteria.andMrchnoEqualTo(merNo);
			criteria.andTermStatEqualTo("0");
			if (!StringUtil.isEmpty(termCode)){
				criteria.andTermcodeLike("%"+termCode+"%");
			}				
			example.setOrderByClause(" termcode asc ");
			lst=this.CortexTermposXDao.selectByExample(example);			
		}		
		return lst;
	}

	@Override
	public CortexMerchantX getCortexMerchantXByMerNo(String merNo) {
		if (!StringUtil.isEmpty(merNo)){
			CortexMerchantXExample example=new CortexMerchantXExample();
			CortexMerchantXExample.Criteria criteria=example.createCriteria();			
			criteria.andMrchnoEqualTo(merNo);
			List<CortexMerchantX> lst =this.CortexMerchantXDao.selectByExample(example);
			if(lst!=null&&lst.size()>0){
				return lst.get(0);
			}else{
				return null;
			}
		}else{
			return null;			
		}		
	}

	@Override
	public Map<String, String> getCortexCrdProductMap() {
		Map<String, String> map = new HashMap<String, String>();	
		List<CortexCrdProduct> crdPrd=this.CortexCrdProductDao.selectByExample(null);
		if(crdPrd!=null&&crdPrd.size()>0){
			for(CortexCrdProduct crdProduct:crdPrd){
				map.put(crdProduct.getCrdproduct(), crdProduct.getDescr());
			}
		}
		return map;
	}

	@Override
	public Map<String, String> getSysParamMapByParamType(String paramType) {
		Map<String, String> map = new HashMap<String, String>();	
		CortexSysParameterExample example=new CortexSysParameterExample();
		CortexSysParameterExample.Criteria criteria=example.createCriteria();
		if (!StringUtil.isEmpty(paramType)){
			criteria.andParamTypeEqualTo(paramType);
		}
		//启用标志   0：不启用 1：启用
		criteria.andEnableEqualTo("1");		
		example.setOrderByClause(" PARAM_TYPE,PARAM_ID ASC ");		
		
		List<CortexSysParameter> crdPrd=this.CortexSysParameterDao.selectByExample(example);
		if(crdPrd!=null&&crdPrd.size()>0){
			for(CortexSysParameter sysParam:crdPrd){
				map.put(sysParam.getParamValue(), sysParam.getParamName());
			}
		}
		return map;
	}
	
	public List<CortexSysParameter> getSysParamListByParamType(String paramType) {
		CortexSysParameterExample example=new CortexSysParameterExample();
		CortexSysParameterExample.Criteria criteria=example.createCriteria();
		if (!StringUtil.isEmpty(paramType)){
			criteria.andParamTypeEqualTo(paramType);
		}
		//启用标志   0：不启用 1：启用
		criteria.andEnableEqualTo("1");		
		example.setOrderByClause(" PARAM_TYPE,PARAM_ID ASC ");	
		List<CortexSysParameter> crdPrd=this.CortexSysParameterDao.selectByExample(example);
		return crdPrd;
	}

	@Override
	public List<CortexCrdProduct> getCortexCrdProductList(CortexCrdProduct model) {
		CortexCrdProductExample example=new CortexCrdProductExample();
		if(model!=null){
			CortexCrdProductExample.Criteria criteria=example.createCriteria();
			if (!StringUtil.isEmpty(model.getUsrdata1())){
				criteria.andUsrdata1EqualTo(model.getUsrdata1());
			}
			if (!StringUtil.isEmpty(model.getUsrdata2())){
				criteria.andUsrdata2EqualTo(model.getUsrdata2());
			}		
			if (model.getId()!=null){
				criteria.andIdEqualTo(model.getId());
			}		
			if (!StringUtil.isEmpty(model.getCrdproduct())){
				criteria.andCrdproductEqualTo(model.getCrdproduct());
			}	
		}		
		return CortexCrdProductDao.selectByExample(example);
	}

	@Override
	public List<CortexAccType> getCortexAccTypeList(CortexAccType model) {
		CortexAccTypeExample example=new CortexAccTypeExample();
		if(model!=null){
			CortexAccTypeExample.Criteria criteria=example.createCriteria();
			if (model.getInstId()!=null){
				criteria.andInstIdEqualTo(model.getInstId());
			}
			if (!StringUtil.isEmpty(model.getTypecode())){
				criteria.andTypecodeEqualTo(model.getTypecode());
			}				
			if (!StringUtil.isEmpty(model.getCurrcode())){
				criteria.andCurrcodeEqualTo(model.getCurrcode());
			}	
		}		
		return this.CortexAccTypeDao.selectByExample(example);
	}

	@Override
	public List<CortexCrdStatus> getCortexCrdStatusList(
			CortexCrdStatus model) {
		CortexCrdStatusExample example=new CortexCrdStatusExample();
		if(model!=null){
			CortexCrdStatusExample.Criteria criteria=example.createCriteria();
			if (model.getStatcode()!=null){
				criteria.andStatcodeEqualTo(model.getStatcode());
			}
			if (!StringUtil.isEmpty(model.getSysdef())){
				criteria.andSysdefEqualTo(model.getSysdef());
			}				
			if (!StringUtil.isEmpty(model.getCanceled())){
				criteria.andCanceledEqualTo(model.getCanceled());
			}	
		}		
		return this.CortexCrdStatusDao.selectByExample(example);
	}

	public int queryCnt(CortexViewAccdetStat queryModel) {
		if(queryModel==null){
			return 0;
		}
		return CortexViewAccdetStatDao.countByExample(getExampleByModel(queryModel));
	}
	
	private CortexViewAccdetStatExample getExampleByModel(CortexViewAccdetStat queryModel){
		CortexViewAccdetStatExample example = new CortexViewAccdetStatExample();
		CortexViewAccdetStatExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getCardStatus()))
			criteria.andCardStatusEqualTo(queryModel.getCardStatus());
		if (!StringUtil.isEmpty(queryModel.getCardBin()))
			criteria.andCardBinLike("%"+queryModel.getCardBin()+"%");
		if (!StringUtil.isEmpty(queryModel.getCrdproduct()))
			criteria.andCrdproductEqualTo(queryModel.getCrdproduct());
		if (!StringUtil.isEmpty(queryModel.getIsExclusive()))
			criteria.andExelusiveCardFlagEqualTo(queryModel.getIsExclusive());
	if (!StringUtil.isEmpty(queryModel.getStlFlag()))
		criteria.andStlFlagequalto(queryModel.getStlFlag());
		example.setOrderByClause(" CARD_STATUS DESC, CRDPRODUCT  ");
	
		return example;
	}
	
	public List<CortexViewAccdetStat> queryList(CortexViewAccdetStat queryModel, int startResult, int endResult) {
		return CortexViewAccdetStatDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<CortexViewAccdetStat> queryList(CortexViewAccdetStat queryModel) {
		return CortexViewAccdetStatDao.selectByExample(this.getExampleByModel(queryModel));
	}	
			
	public String getSumAmt(CortexViewAccdetStat queryModel) {
		if(queryModel==null){
			return "";
		}
		return CortexViewAccdetStatDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
	@Override
	public String getSumAmtBak(CortexViewAccdetStat queryModel) {
		if(queryModel==null){
			return "";
		}
		return CortexViewAccdetStatDao.getSumAmtBakByExample(this.getExampleByModel(queryModel));
	}

	@Override
	public List<CortexArea> getCortexAreaList(CortexArea model){
		CortexAreaExample example=new CortexAreaExample();
		if(model!=null){
			CortexAreaExample.Criteria criteria=example.createCriteria();
			if (model.getFid()!=null){
				criteria.andFidEqualTo(model.getFid());
			}
			if (!StringUtil.isEmpty(model.getIsuse())){
				criteria.andIsuseEqualTo(model.getIsuse());
			}
			if (model.getId()!=null){
				criteria.andIdEqualTo(model.getId());
			}
			/*criteria.andIdBetween(1l, 10l);*/
		}
		
		example.setOrderByClause("ID ASC");
		return this.CortexAreaDao.selectByExample(example);
	}

}
