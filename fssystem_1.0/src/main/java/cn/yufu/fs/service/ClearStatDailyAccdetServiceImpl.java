package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearStatDailyAccdetMapper;
import cn.yufu.fs.entity.ClearStatDailyAccdet;
import cn.yufu.fs.entity.ClearStatDailyAccdetExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearStatDailyAccdetService")
public class ClearStatDailyAccdetServiceImpl implements ClearStatDailyAccdetService {
	Log log = Log.getLog(ClearStatDailyAccdetServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearStatDailyAccdetDao")
	private ClearStatDailyAccdetMapper ClearStatDailyAccdetDao;

	public int queryCnt(ClearStatDailyAccdet queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearStatDailyAccdetDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClearStatDailyAccdetExample getExampleByModel(ClearStatDailyAccdet queryModel){
		ClearStatDailyAccdetExample example = new ClearStatDailyAccdetExample();
		ClearStatDailyAccdetExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getBegainDailyDate())){
			criteria.andDailyDateGreaterThanOrEqualTo(queryModel.getBegainDailyDate());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDailyDate())){
			criteria.andDailyDateLessThanOrEqualTo(queryModel.getEndDailyDate());
		}
		if (!StringUtil.isEmpty(queryModel.getCardBin()))
			criteria.andCardBinLike("%"+queryModel.getCardBin()+"%");
		if (!StringUtil.isEmpty(queryModel.getCrdproduct()))
			criteria.andCrdproductEqualTo(queryModel.getCrdproduct());
		
		if (!StringUtil.isEmpty(queryModel.getExelusiveCardFlag()))
				criteria.andExelusiveCardFlagEqualTo(queryModel.getExelusiveCardFlag());
		if (!StringUtil.isEmpty(queryModel.getStlNeedFlag()))
			criteria.andStlNeedFlagequalto(queryModel.getStlNeedFlag());
		
		if(!StringUtil.isEmpty(queryModel.getCardStatus())){
			criteria.andCardStatusequalto(queryModel.getCardStatus());
		}
		
		example.setOrderByClause(" GEN_DT DESC, CRDPRODUCT  ");
	
		return example;
	}
	
	public List<ClearStatDailyAccdet> queryList(ClearStatDailyAccdet queryModel, int startResult, int endResult) {
		return ClearStatDailyAccdetDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearStatDailyAccdet> queryList(ClearStatDailyAccdet queryModel) {
		return ClearStatDailyAccdetDao.selectByExample(this.getExampleByModel(queryModel));
	}	
			
	public String getSumAmt(ClearStatDailyAccdet queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearStatDailyAccdetDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
}
