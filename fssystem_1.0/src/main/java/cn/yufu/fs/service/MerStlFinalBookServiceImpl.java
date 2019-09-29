package cn.yufu.fs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.MerStlFinalBookMapper;
import cn.yufu.fs.entity.ClearMerStlFinalBook;
import cn.yufu.fs.entity.ClearMerStlFinalBookExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.MerStlFinalBookService")
public class MerStlFinalBookServiceImpl implements MerStlFinalBookService {
	Log log = Log.getLog(MerStlFinalBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.MerStlFinalBookDao")
	private MerStlFinalBookMapper merStlFinalBookDao;
	
	public int queryCnt(ClearMerStlFinalBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = merStlFinalBookDao.countByExample(getExampleByModel(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}
	
	private ClearMerStlFinalBookExample getExampleByModel(ClearMerStlFinalBook queryModel){
		ClearMerStlFinalBookExample example = new ClearMerStlFinalBookExample();
		ClearMerStlFinalBookExample.Criteria criteria = example.createCriteria();
		//需要过滤的商户号
		List<String> asList = new ArrayList<>();
		String config = Global.getConfig("merno");
		if(StringUtils.isNoneBlank(config)) {
			 String[] merchantNumberArray = config.split(",");
		     asList = Arrays.asList(merchantNumberArray);
        }
		if (asList != null && asList.size() > 0) {
			criteria.andMerNoNotIn(asList);
		}
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
		
		//添加线上线下区分标志
		criteria.andFlagOnline(queryModel.getFlagOnline());
		
		example.setOrderByClause(" PROVINCE_CODE ASC,CITY_CODE ASC,MER_NO DESC,START_STL_DATE DESC,END_STL_DATE DESC,CARD_TYPE DESC,GEN_DT DESC,ROWID DESC");
	
		return example;
	}
	
	public List<ClearMerStlFinalBook> queryList(ClearMerStlFinalBook queryModel, int startResult, int endResult) {
		return merStlFinalBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearMerStlFinalBook> queryList(ClearMerStlFinalBook queryModel) {
		return merStlFinalBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearMerStlFinalBook queryInfo(String ClearMerStlFinalBookId) {
		return merStlFinalBookDao.selectByPrimaryKey(ClearMerStlFinalBookId);
	}
	
	public int UpdateClearMerStlFinalBook(ClearMerStlFinalBook record){
		return merStlFinalBookDao.updateByPrimaryKey(record);		
	}
	
	public Map<String, Object> edit(ClearMerStlFinalBook info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
		map.put(SysConst.RESULT_MSG, "差错流水信息保存成功。");
		merStlFinalBookDao.updateByPrimaryKeySelective(info);
		return map;
	}
		
	public String getSumAmt(ClearMerStlFinalBook queryModel) {
		if(queryModel==null){
			return "";
		}
		return merStlFinalBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}

	public int updatePayoutStatus(String flag,List<ClearMerStlFinalBook> list) {
		if(flag==null||"".equals(flag)||list==null||list.size()<=0){
			return -2;
		}else{
			int resultSize=0;
			for(ClearMerStlFinalBook fb:list){
				if("0".equals(flag)){
					resultSize=resultSize+merStlFinalBookDao.updatePayoutStatusZeroToOne(fb);
				}else if("1".equals(flag)){
					resultSize=resultSize+merStlFinalBookDao.updatePayoutStatusOneToThree(fb);	
				}else{
					resultSize=resultSize+merStlFinalBookDao.updatePayoutStatusTwoToEight(fb);					
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
		
		//添加线上线下区分标志
		criteria.andFlagOnline(queryModel.getFlagOnline());
		
		example.setOrderByClause(" province_code asc,city_code asc");	
		
		return merStlFinalBookDao.selectGroupSumByExample(example);
	}
	
}
