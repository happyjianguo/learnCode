package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.OldCardTranDailyMapper;
import cn.yufu.fs.entity.OldCardTranDaily;
import cn.yufu.fs.entity.OldCardTranDailyExample;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.OldCardTranDailyService")
public class OldCardTranDailyServiceImpl implements OldCardTranDailyService {

	@Autowired
	@Qualifier("fs.OldCardTranDailyDao")
	private OldCardTranDailyMapper oldCardTranDailyDao;
	
	@Override
	public int queryCount(OldCardTranDaily queryModel) {
		Integer queryCount = oldCardTranDailyDao.countByExample(getExample(queryModel));
		if (queryCount == null) {
			return 0;
		}
		return queryCount;
	}
	
	@Override
	public List<OldCardTranDaily> selectPageByExample(OldCardTranDaily queryModel, 
			int startResult, int endResult) {
		return oldCardTranDailyDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}
	
	@Override
	public List<OldCardTranDaily> selectByExample(OldCardTranDaily queryModel) {
		return oldCardTranDailyDao.selectByExample(getExample(queryModel));
	}

	@Override
	public OldCardTranDaily selectByPrimaryKey(String id) {
		return oldCardTranDailyDao.selectByPrimaryKey(id);
	}
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private OldCardTranDailyExample getExample(OldCardTranDaily queryModel){
		OldCardTranDailyExample example = new OldCardTranDailyExample();
		OldCardTranDailyExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getId())) {
			criteria.andIdEqualTo(queryModel.getId());
		}
		if (StringUtils.isNotBlank(queryModel.getBegainDailyDate())) {
			criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getBegainDailyDate());
		}
		if (StringUtils.isNotBlank(queryModel.getEndDailyDate())) {
			criteria.andTransactiondateLessThanOrEqualTo(queryModel.getEndDailyDate());
		}
		example.setOrderByClause(" TRANSACTIONDATE DESC, TRANSACTIONMONEY_ACC DESC ");
		return example;
	}

}
