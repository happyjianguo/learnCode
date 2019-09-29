package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.WankeCridetOnAccountBookMapper;
import cn.yufu.fs.entity.WankeCridetOnAccountBook;
import cn.yufu.fs.entity.WankeCridetOnAccountBookExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.WankeCridetOnAccountBookService")
public class WankeCridetOnAccountBookServiceImpl implements WankeCridetOnAccountBookService {
	Log log = Log.getLog(WankeCridetOnAccountBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.WankeCridetOnAccountBookDao")
	private WankeCridetOnAccountBookMapper WankeCridetOnAccountBookDao;

	public int queryCnt(WankeCridetOnAccountBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		return WankeCridetOnAccountBookDao.countByExample(getExampleByModel(queryModel));
	}
	
	private WankeCridetOnAccountBookExample getExampleByModel(WankeCridetOnAccountBook queryModel){
		WankeCridetOnAccountBookExample example = new WankeCridetOnAccountBookExample();
		WankeCridetOnAccountBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		if (!StringUtil.isEmpty(queryModel.getPan()))
			criteria.andPanEqualTo(queryModel.getPan());
		if(null!=queryModel.getDebtAmt()){
			//已还清
			if("0".equals(queryModel.getDebtAmt().toString())){
				criteria.andDebtAmtEqualTo(queryModel.getDebtAmt());
			}
			//未还清
			if(!"0".equals(queryModel.getDebtAmt().toString())){
				criteria.andDebtAmtGreaterThan(BigDecimal.ZERO);
			}
		}
			
			
		
			
		
		
//		//交易时间日期段
//		if (!StringUtil.isEmpty(queryModel.getStartDt())){
//			criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getStartDt());
//		}
//		if (!StringUtil.isEmpty(queryModel.getEndDt())){
//			criteria.andTransactiondateLessThanOrEqualTo(queryModel.getEndDt());
//		}
//		
//		if (!StringUtil.isEmpty(queryModel.getIid()))
//			criteria.andIidLike("%"+queryModel.getIid()+"%");
		
		example.setOrderByClause("MER_NO");
	
		return example;
	}
	
	public List<WankeCridetOnAccountBook> queryList(WankeCridetOnAccountBook queryModel, int startResult, int endResult) {
		return WankeCridetOnAccountBookDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<WankeCridetOnAccountBook> queryList(WankeCridetOnAccountBook queryModel) {
		return WankeCridetOnAccountBookDao.selectByExample(this.getExampleByModel(queryModel));
	}	
			
//	public String getSumAmt(WankeCridetOnAccountBook queryModel) {
//		if(queryModel==null){
//			return "";
//		}
//		return WankeCridetOnAccountBookDao.getSumAmtByExample(this.getExampleByModel(queryModel));
//	}
	
	public WankeCridetOnAccountBook queryInfo(String WankeCridetOnAccountBookId) {
		WankeCridetOnAccountBookExample example = new WankeCridetOnAccountBookExample();
		WankeCridetOnAccountBookExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(WankeCridetOnAccountBookId))
			criteria.andIdEqualTo(WankeCridetOnAccountBookId);
		
		List<WankeCridetOnAccountBook> list=WankeCridetOnAccountBookDao.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	
}
