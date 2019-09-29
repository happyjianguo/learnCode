package cn.yufu.fs.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.TransactionRecordsHisTMapper;
import cn.yufu.fs.entity.TransactionRecordsHisT;
import cn.yufu.fs.entity.TransactionRecordsHisTExample;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.TransactionRecordsHisTService")
public class TransactionRecordsHisTServiceImpl implements TransactionRecordsHisTService {
	
	Log log = Log.getLog(TransactionRecordsHisTServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.TransactionRecordsHisTDao")
	private TransactionRecordsHisTMapper transactionRecordsHisTMapper;

	@Override
	public int queryCount(TransactionRecordsHisT queryModel) {
		Integer integer = transactionRecordsHisTMapper.countByExample(getExampleByModel(queryModel));
		if(integer == null){
			return 0;
		}
		return integer;
	}

	@Override
	public List<TransactionRecordsHisT> selectPageList(TransactionRecordsHisT queryModel, int startResult,
			int endResult) {
		return transactionRecordsHisTMapper.selectPageList(getExampleByModel(queryModel), startResult, endResult);
	}

	@Override
	public List<TransactionRecordsHisT> selectAllList(TransactionRecordsHisT queryModel) {
		return transactionRecordsHisTMapper.selectByExample(getExampleByModel(queryModel));
	}
	
	private TransactionRecordsHisTExample getExampleByModel(TransactionRecordsHisT queryModel){
		TransactionRecordsHisTExample example = new TransactionRecordsHisTExample();
		TransactionRecordsHisTExample.Criteria criteria = example.createCriteria();
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
			//商户号
			if (StringUtils.isNotBlank(queryModel.getMerchantnumber())) {
				criteria.andMerchantnumberEqualTo(queryModel.getMerchantnumber());
			}
			//终端号
			if (StringUtils.isNotBlank(queryModel.getTerminalnumber())) {
				criteria.andTerminalnumberEqualTo(queryModel.getTerminalnumber());
			}
			//卡号
			if (StringUtils.isNotBlank(queryModel.getCardnumber())) {
				criteria.andCardnumberEqualTo(queryModel.getCardnumber());
			}
			//交易类型
			if (StringUtils.isNotBlank(queryModel.getTransactiontype())) {
				criteria.andTransactiontypeEqualTo(queryModel.getTransactiontype());
			}
			//参考号
			if (StringUtils.isNotBlank(queryModel.getReferencenumber())) {
				criteria.andReferencenumberEqualTo(queryModel.getReferencenumber());
			}
			//起始日期
			if (StringUtils.isNotBlank(queryModel.getTransactiondateStart())) {
				criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getTransactiondateStart());
			}
			//截止日期
			if (StringUtils.isNotBlank(queryModel.getTransactiondateEnd())) {
				criteria.andTransactiondateLessThanOrEqualTo(queryModel.getTransactiondateEnd());
			}
		}
		example.setOrderByClause(" TRANSACTIONDATE DESC, TRANSACTIONTIME DESC ");
		return example;
	}
}
