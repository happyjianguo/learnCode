package cn.yufu.bak.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.TransactionrecordsDailyMapper;
import cn.yufu.bak.entity.TransactionrecordsDaily;
import cn.yufu.bak.entity.TransactionrecordsDailyExample;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.StringUtil;
import cn.yufu.system.common.utils.StringUtils;

@Service("bak.TransactionrecordsDailyService")
public class TransactionrecordsDailyServiceImpl implements TransactionrecordsDailyService {
	
	@Autowired
	@Qualifier("bak.TransactionrecordsDailyDao")
	private TransactionrecordsDailyMapper transactionrecordsDailyDao;

	@Override
	public int queryCount(TransactionrecordsDaily queryModel) {
		Integer integer = transactionrecordsDailyDao.countByExample(getExampleByModel(queryModel));
		if(integer == null){
			return 0;
		}
		return integer;
	}

	@Override
	public List<TransactionrecordsDaily> selectPageList(TransactionrecordsDaily queryModel, int startResult,
			int endResult) {
		return transactionrecordsDailyDao.selectPageList(getExampleByModel(queryModel), startResult, endResult);
	}

	@Override
	public List<TransactionrecordsDaily> selectAllList(TransactionrecordsDaily queryModel) {
		return transactionrecordsDailyDao.selectByExample(getExampleByModel(queryModel));
	}
	
	@Override
	public TransactionrecordsDaily getNumAndAmt(TransactionrecordsDaily queryModel) {
		return transactionrecordsDailyDao.getNumAndAmt(getExampleByModel(queryModel));
	}
	
	private TransactionrecordsDailyExample getExampleByModel(TransactionrecordsDaily queryModel) {
		TransactionrecordsDailyExample example = new TransactionrecordsDailyExample();
		TransactionrecordsDailyExample.Criteria criteria = example.createCriteria();
		//需要过滤的商户号
		List<String> asList = new ArrayList<>();
		String config = Global.getConfig("merno");
		if(StringUtils.isNoneBlank(config)) {
			 String[] merchantNumberArray = config.split(",");
		     asList = Arrays.asList(merchantNumberArray);
        }
		if (asList != null && asList.size() > 0) {
			criteria.andMerchantnumberNotIn(asList);
		}
		if (queryModel != null) {
			//交易时间日期段
			if (!StringUtil.isEmpty(queryModel.getStartDt())){
				criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getStartDt());
			}
			if (!StringUtil.isEmpty(queryModel.getEndDt())){
				criteria.andTransactiondateLessThanOrEqualTo(queryModel.getEndDt());
			}		
		}
		example.setOrderByClause(" TRANSACTIONMONEY_SUM DESC, TRAN_NUM DESC ");
		return example;
	}

}
