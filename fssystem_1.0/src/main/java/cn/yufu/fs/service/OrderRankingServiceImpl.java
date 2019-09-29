package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.OrderRankingMapper;
import cn.yufu.fs.entity.OrderRanking;
import cn.yufu.fs.entity.OrderRankingExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.OrderRankingService")
public class OrderRankingServiceImpl implements OrderRankingService {
	
	Log log = Log.getLog(OrderRankingServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.OrderRankingDao")
	private OrderRankingMapper orderRankingDao;
	
	@Override
	public Integer countByExample(OrderRanking orderRanking) {
		if(orderRanking==null){
			return 0;
		}
		Integer integer = orderRankingDao.countByExample(getExample(orderRanking));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<OrderRanking> selectPageByExample(OrderRanking orderRanking, int startResult, int endResult) {
		return orderRankingDao.selectPageByExample(getExample(orderRanking), startResult, endResult);
	}

	@Override
	public List<OrderRanking> selectByExample(OrderRanking orderRanking) {
		return orderRankingDao.selectByExample(getExample(orderRanking));
	}
	
	private OrderRankingExample getExample(OrderRanking orderRanking){
		OrderRankingExample example = new OrderRankingExample();
		OrderRankingExample.Criteria criteria = example.createCriteria();
		if(orderRanking != null){
			if (StringUtils.isNotEmpty(orderRanking.getBatchId())) {
				criteria.andBatchIdEqualTo(orderRanking.getBatchId());
			}
			if (StringUtils.isNotEmpty(orderRanking.getMerNo())) {
				criteria.andMerNoEqualTo(orderRanking.getMerNo());
			}
			if (StringUtils.isNotBlank(orderRanking.getCompanyName())) {
				criteria.andCompanyNameLike(orderRanking.getCompanyName());
			}
			//消费时间-->改成订单日期
			if (StringUtils.isNotBlank(orderRanking.getTransactiondateStart())) {
				criteria.andOrderDateGreaterThanOrEqualTo(orderRanking.getTransactiondateStart());
			}
			if (StringUtils.isNotBlank(orderRanking.getTransactiondateEnd())) {
				criteria.andOrderDateLessThanOrEqualTo(orderRanking.getTransactiondateEnd());
			}
		}
		example.setOrderByClause(" AMT_EACH_CRD DESC ");
		return example;
	}

}
