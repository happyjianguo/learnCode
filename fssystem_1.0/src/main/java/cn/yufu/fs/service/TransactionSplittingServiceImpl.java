package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.TransactionSplittingMapper;
import cn.yufu.fs.entity.TransactionSplitting;
import cn.yufu.fs.entity.TransactionSplittingExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.TransactionSplittingService")
public class TransactionSplittingServiceImpl implements TransactionSplittingService {
	
	Log log = Log.getLog(TransactionSplittingServiceImpl.class);

	@Autowired
	@Qualifier("fs.TransactionSplittingDao")
	private TransactionSplittingMapper transactionSplittingDao;
	
	@Override
	public int queryCnt(TransactionSplitting queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer countByExample = transactionSplittingDao.countByExample(getExample(queryModel));
		if (countByExample == null) {
			return 0;
		}
		return countByExample;
	}

	@Override
	public List<TransactionSplitting> queryList(TransactionSplitting queryModel, int startResult, int endResult) {
		return transactionSplittingDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<TransactionSplitting> queryList(TransactionSplitting queryModel) {
		return transactionSplittingDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public TransactionSplitting getSumAmt(TransactionSplitting queryModel) {
		return transactionSplittingDao.getSumAmt(getExample(queryModel));
	}
	
	@Override
	public TransactionSplitting selectByPrimaryKey(String id) {
		return transactionSplittingDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private TransactionSplittingExample getExample(TransactionSplitting queryModel){
		TransactionSplittingExample example = new TransactionSplittingExample();
		TransactionSplittingExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getCardBin())) {
			criteria.andCardBinEqualTo(queryModel.getCardBin());
		}
		if (StringUtils.isNotBlank(queryModel.getCardBin2())) {
			criteria.andCardBinEqualTo(queryModel.getCardBin2());
		}
		if (StringUtils.isNotBlank(queryModel.getBegainTransactiondate())) {
			criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getBegainTransactiondate());
		}
		if (StringUtils.isNotBlank(queryModel.getEndTransactiondate())) {
			criteria.andTransactiondateLessThanOrEqualTo(queryModel.getEndTransactiondate());
		}
		example.setOrderByClause("TRANSACTIONDATE DESC, TRANSACTIONMONEY DESC ");
		return example;
	}

}
