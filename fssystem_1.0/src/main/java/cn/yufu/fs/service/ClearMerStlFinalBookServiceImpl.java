package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearMerStlFinalBookMapper;
import cn.yufu.fs.entity.ClearMerStlFinalBook;
import cn.yufu.fs.entity.ClearMerStlFinalBookExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;

@Service("fs.ClearMerStlFinalBookService")
public class ClearMerStlFinalBookServiceImpl implements ClearMerStlFinalBookService {
	Log log = Log.getLog(ClearMerStlFinalBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearMerStlFinalBookDao")
	private ClearMerStlFinalBookMapper ClearMerStlFinalBookDao;
	
	public int queryCnt(ClearMerStlFinalBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = ClearMerStlFinalBookDao.countByExample(getExampleByModel(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}
	
	@Override
	public int shareBenefitReportCount(ClearMerStlFinalBook queryModel){
		if(queryModel==null){
			return 0;
		}
		Integer integer = ClearMerStlFinalBookDao.shareBenefitReportCount(shareBenefitReportModel(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}
	
	private ClearMerStlFinalBookExample getExampleByModel(ClearMerStlFinalBook queryModel){
		ClearMerStlFinalBookExample example = new ClearMerStlFinalBookExample();
		ClearMerStlFinalBookExample.Criteria criteria = example.createCriteria();
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
	
	public List<ClearMerStlFinalBook> queryList(ClearMerStlFinalBook queryModel, int startResult, int endResult) {
		return ClearMerStlFinalBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}
	
	@Override
	public List<ClearMerStlFinalBook> shareBenefitReport(ClearMerStlFinalBook queryModel, int startResult, int endResult) {
		ClearMerStlFinalBookExample example = shareBenefitReportModel(queryModel);
		
		return ClearMerStlFinalBookDao.shareBenefitReport(example, startResult, endResult);
	}
	
	@Override
	public List<ClearMerStlFinalBook> shareBenefitReport(ClearMerStlFinalBook queryModel) {
		return ClearMerStlFinalBookDao.shareBenefitAllReport(this.shareBenefitReportModel(queryModel));
	}
	
	@Override
	public ClearMerStlFinalBook shareBenefitSum(ClearMerStlFinalBook queryModel) {
		return ClearMerStlFinalBookDao.shareBenefitSum(shareBenefitReportModel(queryModel));
	}

	private ClearMerStlFinalBookExample shareBenefitReportModel(ClearMerStlFinalBook queryModel) {
		ClearMerStlFinalBookExample example = new ClearMerStlFinalBookExample();
		ClearMerStlFinalBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo())) {
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		}
		if (!StringUtil.isEmpty(queryModel.getMerchantOrg())) {
			criteria.andMerchantOrgEqualTo(queryModel.getMerchantOrg());
		}
		if (!StringUtil.isEmpty(queryModel.getStartStlDate())) {
			criteria.andStartStlDateGreaterThanOrEqualTo(queryModel.getStartStlDate());
		}
		if (!StringUtil.isEmpty(queryModel.getEndStlDate())) {
			criteria.andEndStlDateLessThanOrEqualTo(queryModel.getEndStlDate());
		}
		criteria.andMerchantOrgIsNotNull();
		criteria.andMerchantOrgNotEqualTo(" ");
		return example;
	}

	public List<ClearMerStlFinalBook> queryList(ClearMerStlFinalBook queryModel) {
		return ClearMerStlFinalBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearMerStlFinalBook queryInfo(String ClearMerStlFinalBookId) {
		return ClearMerStlFinalBookDao.selectByPrimaryKey(ClearMerStlFinalBookId);
	}
	
	@Override
	public int UpdateClearMerStlFinalBook(ClearMerStlFinalBook record){
		return ClearMerStlFinalBookDao.updateByPrimaryKey(record);		
	}
	
	public Map<String, Object> edit(ClearMerStlFinalBook info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "差错流水信息保存成功。");
		ClearMerStlFinalBookDao.updateByPrimaryKeySelective(info);
		return map;
	}
	
	@Override
	public String getSumAmt(ClearMerStlFinalBook queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearMerStlFinalBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}
	
	@Override
	public int updatePayoutStatus(String flag,List<ClearMerStlFinalBook> list) {
		if(flag==null||"".equals(flag)||list==null||list.size()<=0){
			return -2;
		}else{
			int resultSize=0;
			for(ClearMerStlFinalBook fb:list){
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
	
	public List<ClearMerStlFinalBook> selectGroupSumByExample(ClearMerStlFinalBook queryModel) {
		ClearMerStlFinalBookExample example = new ClearMerStlFinalBookExample();
		ClearMerStlFinalBookExample.Criteria criteria = example.createCriteria();
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

	@Override
	public int crdfeeTotalCount(List<String> list,ClearMerStlFinalBook queryModel) {
		if(list==null || list.size() == 0){
			return 0;
		}
		Integer integer = ClearMerStlFinalBookDao.countByExample(crdfeeTotalExample(list,queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<ClearMerStlFinalBook> crdfeeTotalList(List<String> list,ClearMerStlFinalBook queryModel, int startResult, int endResult) {
		return ClearMerStlFinalBookDao.selectPageByExample(crdfeeTotalExample(list,queryModel), startResult, endResult);
	}

	@Override
	public List<ClearMerStlFinalBook> crdfeeTotalList(List<String> list,ClearMerStlFinalBook queryModel) {
		return ClearMerStlFinalBookDao.selectByExample(crdfeeTotalExample(list,queryModel));
	}
	
	private ClearMerStlFinalBookExample crdfeeTotalExample(List<String> list,ClearMerStlFinalBook queryModel) {
		ClearMerStlFinalBookExample example = new ClearMerStlFinalBookExample();
		ClearMerStlFinalBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getEndStlDate())) {
			criteria.andStlDateBetween(queryModel.getStartStlDate(), queryModel.getEndStlDate());
		}
		if (!StringUtil.isEmpty(queryModel.getStlDate()) 
				&& StringUtil.isEmpty(queryModel.getEndStlDate())) {
			criteria.andStlDateEqualTo(queryModel.getStlDate());
		}
		criteria.andMerNoIn(list);
		example.setOrderByClause("STL_DATE DESC,END_STL_DATE DESC,ROWID DESC");
		return example;
	}
	
}
