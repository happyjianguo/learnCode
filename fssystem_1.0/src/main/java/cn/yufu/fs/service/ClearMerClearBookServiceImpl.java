package cn.yufu.fs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearMerClearBookMapper;
import cn.yufu.fs.entity.ClearMerClearBook;
import cn.yufu.fs.entity.ClearMerClearBookExample;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
import cn.yufu.system.common.utils.StringUtils;
@Service("fs.ClearMerClearBookService")
public class ClearMerClearBookServiceImpl implements ClearMerClearBookService {
	Log log = Log.getLog(ClearMerClearBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearMerClearBookDao")
	private ClearMerClearBookMapper ClearMerClearBookDao;

	public int queryCnt(ClearMerClearBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearMerClearBookDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClearMerClearBookExample getExampleByModel(ClearMerClearBook queryModel){
		ClearMerClearBookExample example = new ClearMerClearBookExample();
		ClearMerClearBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		if (!StringUtil.isEmpty(queryModel.getFmrchNo()))
			criteria.andFmrchNoEqualTo(queryModel.getFmrchNo());
		if (!StringUtil.isEmpty(queryModel.getFmrchName()))
			criteria.andFmrchNameLike("%"+queryModel.getFmrchName()+"%");	
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}		
		if (!StringUtil.isEmpty(queryModel.getStlFlag())){
			criteria.andStlFlagEqualTo(queryModel.getStlFlag());
		}		
		
		example.setOrderByClause(" GEN_DT DESC,MER_NO,TRAN_DATE DESC ");
	
		return example;
	}
	
	private ClearMerClearBookExample getExamplePaiMing(ClearMerClearBook queryModel){
		ClearMerClearBookExample example = new ClearMerClearBookExample();
		ClearMerClearBookExample.Criteria criteria = example.createCriteria();
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
		if (!StringUtil.isEmpty(queryModel.getFmrchNo()))
			criteria.andFmrchNoEqualTo(queryModel.getFmrchNo());
		if (!StringUtil.isEmpty(queryModel.getFmrchName()))
			criteria.andFmrchNameLike("%"+queryModel.getFmrchName()+"%");	
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}		
		if (!StringUtil.isEmpty(queryModel.getStlFlag())){
			criteria.andStlFlagEqualTo(queryModel.getStlFlag());
		}		
		
		example.setOrderByClause(" GEN_DT DESC,MER_NO,TRAN_DATE DESC ");
	
		return example;
	}
	
	public List<ClearMerClearBook> queryList(ClearMerClearBook queryModel, int startResult, int endResult) {
		return ClearMerClearBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearMerClearBook> queryList(ClearMerClearBook queryModel) {
		return ClearMerClearBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
	
	public ClearMerClearBook queryInfo(String ClearMerClearBookId) {
		ClearMerClearBookExample example = new ClearMerClearBookExample();
		ClearMerClearBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(ClearMerClearBookId))
			criteria.andSrcIdEqualTo(ClearMerClearBookId);
		
		List<ClearMerClearBook> list=ClearMerClearBookDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
		
	public String getSumAmt(ClearMerClearBook queryModel) {
		if(queryModel==null){
			return "";
		}
		return ClearMerClearBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
	}

	public List<ClearMerClearBook> selectPaiMingByExample(ClearMerClearBook queryModel) {
		return ClearMerClearBookDao.selectPaiMingByExample(this.getExamplePaiMing(queryModel));
	}	
	
	public List<ClearMerClearBook> selectPaiMingPageByExample(ClearMerClearBook queryModel, int startResult, int endResult) {
		return ClearMerClearBookDao.selectPaiMingPageByExample(this.getExamplePaiMing(queryModel),startResult,endResult);
	}

	public int countPaiMingByExample(ClearMerClearBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearMerClearBookDao.countPaiMingByExample(getExamplePaiMing(queryModel));
	}
	

	@Override
	public List<ClearMerClearBook> selectPaiMingTotal(ClearMerClearBook queryModel) {
		return ClearMerClearBookDao.selectPaiMingByExample(this.getExample(queryModel));
	}	

	@Override
	public List<ClearMerClearBook> selectPaiMingPageTotal(ClearMerClearBook queryModel, int startResult,
			int endResult) {
		return ClearMerClearBookDao.selectPaiMingPageByExample(this.getExample(queryModel),startResult,endResult);
	}

	@Override
	public int countPaiMingTotal(ClearMerClearBook queryModel) {
		Integer integer = ClearMerClearBookDao.countPaiMingByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}
	
	private ClearMerClearBookExample getExample(ClearMerClearBook queryModel){
		ClearMerClearBookExample example = new ClearMerClearBookExample();
		ClearMerClearBookExample.Criteria criteria = example.createCriteria();
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
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTranDateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTranDateLessThanOrEqualTo(queryModel.getEndDt());
		}		
		if (!StringUtil.isEmpty(queryModel.getStlFlag())){
			criteria.andStlFlagEqualTo(queryModel.getStlFlag());
		}
		
		example.setOrderByClause(" GEN_DT DESC,MER_NO,TRAN_DATE DESC ");
	
		return example;
	}
	
}
