package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearTransactionAccountMapper;
import cn.yufu.fs.entity.ClearTransactionAccount;
import cn.yufu.fs.entity.ClearTransactionAccountExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtil;
@Service("fs.ClearTransactionAccountService")
public class ClearTransactionAccountServiceImpl implements ClearTransactionAccountService {
	Log log = Log.getLog(ClearTransactionAccountServiceImpl.class);

	@Autowired
	@Qualifier("fs.ClearTransactionAccountDao")
	private ClearTransactionAccountMapper ClearTransactionAccountDao;

	public int queryCnt(ClearTransactionAccount queryModel) {
		if(queryModel==null){
			return 0;
		}
		return ClearTransactionAccountDao.countByExample(getExampleByModel(queryModel));
	}
	
	private ClearTransactionAccountExample getExampleByModel(ClearTransactionAccount queryModel){
		ClearTransactionAccountExample example = new ClearTransactionAccountExample();
		ClearTransactionAccountExample.Criteria criteria = example.createCriteria();
		if (!StringUtil.isEmpty(queryModel.getMerNo()))
			criteria.andMerNoEqualTo(queryModel.getMerNo());
		if (!StringUtil.isEmpty(queryModel.getMerName()))
			criteria.andMerNameLike("%"+queryModel.getMerName()+"%");
		//交易时间日期段
		if (!StringUtil.isEmpty(queryModel.getStartDt())){
			criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getStartDt());
		}
		if (!StringUtil.isEmpty(queryModel.getEndDt())){
			criteria.andTransactiondateLessThanOrEqualTo(queryModel.getEndDt());
		}
		
		if (!StringUtil.isEmpty(queryModel.getIid()))
			criteria.andIidLike("%"+queryModel.getIid()+"%");
		
		example.setOrderByClause("MER_NO, TRANSACTIONDATE");
	
		return example;
	}
	
	public List<ClearTransactionAccount> queryList(ClearTransactionAccount queryModel, int startResult, int endResult) {
		return ClearTransactionAccountDao.selectPageByExample(this.getExampleByModel(queryModel), startResult, endResult);
	}

	public List<ClearTransactionAccount> queryList(ClearTransactionAccount queryModel) {
		return ClearTransactionAccountDao.selectByExample(this.getExampleByModel(queryModel));
	}	
			
//	public String getSumAmt(ClearTransactionAccount queryModel) {
//		if(queryModel==null){
//			return "";
//		}
//		return ClearTransactionAccountDao.getSumAmtByExample(this.getExampleByModel(queryModel));
//	}
	
}
