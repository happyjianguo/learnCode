package cn.yufu.bak.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.TransactionrecordsMapper;
import cn.yufu.bak.entity.Transactionrecords;
import cn.yufu.bak.entity.TransactionrecordsExample;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("bak.TransactionrecordsService")
public class TransactionrecordsServiceImpl implements TransactionrecordsService {
	
	Log log = Log.getLog(TransactionrecordsServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.TransactionrecordsDao")
	private TransactionrecordsMapper transactionrecordsDao;

	@Override
	public int queryCount(Transactionrecords queryModel) {
		Integer integer = transactionrecordsDao.countByExample(getExampleByModel(queryModel));
		if(integer == null){
			return 0;
		}
		return integer;
	}

	@Override
	public List<Transactionrecords> selectPageList(Transactionrecords queryModel, 
			int startResult, int endResult) {
		return transactionrecordsDao.selectPageList(getExampleByModel(queryModel), startResult, endResult);
	}

	@Override
	public List<Transactionrecords> selectAllList(Transactionrecords queryModel) {
		return transactionrecordsDao.selectByExample(getExampleByModel(queryModel));
	}
	
	private TransactionrecordsExample getExampleByModel(Transactionrecords queryModel){
		TransactionrecordsExample example = new TransactionrecordsExample();
		TransactionrecordsExample.Criteria criteria = example.createCriteria();
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
			if (queryModel.getReferencenumber() != null) {
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
