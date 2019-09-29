package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearMerStlFinalBookBakMapper;
import cn.yufu.fs.entity.ClearMerStlFinalBookBak;
import cn.yufu.fs.entity.ClearMerStlFinalBookBakExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;

@Service("fs.ClearMerStlFinalBookBakService")
public class ClearMerStlFinalBookBakServiceImpl implements ClearMerStlFinalBookBakService {
	Log log = Log.getLog(ClearMerStlFinalBookBakServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearMerStlFinalBookBakDao")
	private ClearMerStlFinalBookBakMapper ClearMerStlFinalBookDao;
	
	public int queryCnt(ClearMerStlFinalBookBak queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = ClearMerStlFinalBookDao.countByExample(getExampleByModel(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}
	
	private ClearMerStlFinalBookBakExample getExampleByModel(ClearMerStlFinalBookBak queryModel){
		ClearMerStlFinalBookBakExample example = new ClearMerStlFinalBookBakExample();
		ClearMerStlFinalBookBakExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getGenDt())){
			criteria.andGenDtEqualTo(queryModel.getGenDt());
		}
		//打款日期
		if (!StringUtil.isEmpty(queryModel.getPayoutDate())){
			criteria.andPayoutDateEqualTo(queryModel.getPayoutDate());
		}
		if (!StringUtil.isEmpty(queryModel.getPayoutJunl())){
			criteria.andPayoutJunlLike("%"+queryModel.getPayoutJunl()+"%");
		}
		if (!StringUtil.isEmpty(queryModel.getSeqNo())){
			criteria.andSeqNoLike("%"+queryModel.getSeqNo()+"%");
		}	
		//结算日期时间段--开始日期-结束时间段
		if(!StringUtil.isEmpty(queryModel.getStlDate())&&StringUtil.isEmpty(queryModel.getStlDate1())){
			criteria.andStlDateEqualTo(queryModel.getStlDate());
		}else if(StringUtil.isEmpty(queryModel.getStlDate())&&!StringUtil.isEmpty(queryModel.getStlDate1())){//结束时间段
			criteria.andStlDateLessThanOrEqualTo(queryModel.getStlDate1());
		}else if (!StringUtil.isEmpty(queryModel.getStlDate())&&!StringUtil.isEmpty(queryModel.getStlDate1())){
			criteria.andStlDateBetween(queryModel.getStlDate(), queryModel.getStlDate1());
		}
		
		//打款状态
		if (!StringUtil.isEmpty(queryModel.getPayoutStatus())){
			criteria.andPayoutStatusEqualTo(queryModel.getPayoutStatus());
		}		
		if (queryModel.getProvinceCode()!=null){
			criteria.andProvinceCodeEqualTo(queryModel.getProvinceCode());
		}		
		if (queryModel.getCityCode()!=null){
			criteria.andCityCodeEqualTo(queryModel.getCityCode());
		}	
		if (!StringUtil.isEmpty(queryModel.getBjFlag())){
			criteria.andBjFlagEqualTo(queryModel.getBjFlag());
		}		
		if (!StringUtil.isEmpty(queryModel.getCardType())){
			criteria.andCardTypeEqualTo(queryModel.getCardType());
		}			
		if (!StringUtil.isEmpty(queryModel.getExelusiveCardFlag())){
			criteria.andExelusiveCardFlagEqualTo(queryModel.getExelusiveCardFlag());
		}
		if (!StringUtil.isEmpty(queryModel.getStlNeedFlag())){
			criteria.andStlNeedFlagEqualTo(queryModel.getStlNeedFlag());
		}
		//结算员
		if (!StringUtil.isEmpty(queryModel.getSettlementPerson())){
			criteria.andSettlementPersonLike("%" + queryModel.getSettlementPerson() + "%");
		}
		
		//添加线上线下区分标志
		criteria.andFlagOnline(queryModel.getFlagOnline());
		
		example.setOrderByClause(" PROVINCE_CODE ASC,CITY_CODE ASC,MER_NO DESC,START_STL_DATE DESC,END_STL_DATE DESC,CARD_TYPE DESC,GEN_DT DESC,ROWID DESC");
	
		return example;
	}
	
	public List<ClearMerStlFinalBookBak> queryList(ClearMerStlFinalBookBak queryModel, int startResult, int endResult) {
		return ClearMerStlFinalBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}
	
	public List<ClearMerStlFinalBookBak> queryList(ClearMerStlFinalBookBak queryModel) {
		return ClearMerStlFinalBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearMerStlFinalBookBak queryInfo(String ClearMerStlFinalBookId) {
		return ClearMerStlFinalBookDao.selectByPrimaryKey(ClearMerStlFinalBookId);
	}
	
	@Override
	public String getSumAmt(ClearMerStlFinalBookBak queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearMerStlFinalBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
	@Override
	public int updatePayoutStatus(String flag,List<ClearMerStlFinalBookBak> list) {
		if(flag==null||"".equals(flag)||list==null||list.size()<=0){
			return -2;
		}else{
			int resultSize=0;
			for(ClearMerStlFinalBookBak fb:list){
				if("0".equals(flag)){
					resultSize=resultSize+ClearMerStlFinalBookDao.updatePayoutStatusZeroToOne(fb);
				}else if("1".equals(flag)){
					resultSize=resultSize+ClearMerStlFinalBookDao.updatePayoutStatusOneToThree(fb);	
				}else{
					resultSize=resultSize+ClearMerStlFinalBookDao.updatePayoutStatusTwoToEight(fb);					
				}
			}
			return resultSize;
		}
		
	}
	
	public List<ClearMerStlFinalBookBak> selectGroupSumByExample(ClearMerStlFinalBookBak queryModel) {
		ClearMerStlFinalBookBakExample example = new ClearMerStlFinalBookBakExample();
		ClearMerStlFinalBookBakExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getGenDt())){
			criteria.andGenDtEqualTo(queryModel.getGenDt());
		}
		//打款日期
		if (!StringUtil.isEmpty(queryModel.getPayoutDate())){
			criteria.andPayoutDateEqualTo(queryModel.getPayoutDate());
		}
		if (!StringUtil.isEmpty(queryModel.getPayoutJunl())){
			criteria.andPayoutJunlLike("%"+queryModel.getPayoutJunl()+"%");
		}
		if (!StringUtil.isEmpty(queryModel.getSeqNo())){
			criteria.andSeqNoLike("%"+queryModel.getSeqNo()+"%");
		}	
		//结算日期时间段--开始日期-结束时间段
		if(!StringUtil.isEmpty(queryModel.getStlDate())&&StringUtil.isEmpty(queryModel.getStlDate1())){
			criteria.andStlDateEqualTo(queryModel.getStlDate());
		}else if(StringUtil.isEmpty(queryModel.getStlDate())&&!StringUtil.isEmpty(queryModel.getStlDate1())){//结束时间段
			criteria.andStlDateLessThanOrEqualTo(queryModel.getStlDate1());
		}else if (!StringUtil.isEmpty(queryModel.getStlDate())&&!StringUtil.isEmpty(queryModel.getStlDate1())){
			criteria.andStlDateBetween(queryModel.getStlDate(), queryModel.getStlDate1());
		}
		
		//打款状态
		if (!StringUtil.isEmpty(queryModel.getPayoutStatus())){
			criteria.andPayoutStatusEqualTo(queryModel.getPayoutStatus());
		}		
		if (queryModel.getProvinceCode()!=null){
			criteria.andProvinceCodeEqualTo(queryModel.getProvinceCode());
		}		
		if (queryModel.getCityCode()!=null){
			criteria.andCityCodeEqualTo(queryModel.getCityCode());
		}	
		if (!StringUtil.isEmpty(queryModel.getBjFlag())){
			criteria.andBjFlagEqualTo(queryModel.getBjFlag());
		}		
		if (!StringUtil.isEmpty(queryModel.getCardType())){
			criteria.andCardTypeEqualTo(queryModel.getCardType());
		}			
		if (!StringUtil.isEmpty(queryModel.getExelusiveCardFlag())){
			criteria.andExelusiveCardFlagEqualTo(queryModel.getExelusiveCardFlag());
		}
		if (!StringUtil.isEmpty(queryModel.getStlNeedFlag())){
			criteria.andStlNeedFlagEqualTo(queryModel.getStlNeedFlag());
		}
		//结算员
		if (!StringUtil.isEmpty(queryModel.getSettlementPerson())){
			criteria.andSettlementPersonLike("%" + queryModel.getSettlementPerson() + "%");
		}
		//添加线上线下区分标志
		criteria.andFlagOnline(queryModel.getFlagOnline());
		
		example.setOrderByClause(" province_code asc,city_code asc");	
		
		return ClearMerStlFinalBookDao.selectGroupSumByExample(example);
	}

}
