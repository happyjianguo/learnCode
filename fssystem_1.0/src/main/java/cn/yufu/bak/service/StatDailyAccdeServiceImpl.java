package cn.yufu.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.StatDailyAccdeMapper;
import cn.yufu.bak.entity.StatDailyAccde;
import cn.yufu.bak.entity.StatDailyAccdeExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;


@Service("bak.StatDailyAccdeService")
public class StatDailyAccdeServiceImpl implements StatDailyAccdeService {
	
	Log log = Log.getLog(StatDailyAccdeServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.StatDailyAccdeDao")
	private StatDailyAccdeMapper statDailyAccdeDao;

	@Override
	public int countByExample(StatDailyAccde queryModel) {
		if(null == queryModel) return 0;
		Integer count = statDailyAccdeDao.countByExample(getExample(queryModel));
		if (null == count) return 0;
		return count;
	}

	@Override
	public List<StatDailyAccde> selectPageByExample(StatDailyAccde queryModel, int startResult, int endResult) {
		return statDailyAccdeDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<StatDailyAccde> selectByExample(StatDailyAccde queryModel) {
		return statDailyAccdeDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public StatDailyAccde selectSumAvlBal(StatDailyAccde queryModel) {
		return statDailyAccdeDao.selectSumAvlBal(getExample(queryModel));
	}
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private StatDailyAccdeExample getExample(StatDailyAccde queryModel){
		StatDailyAccdeExample example = new StatDailyAccdeExample();
		StatDailyAccdeExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(queryModel.getStartDate())) {
			criteria.andDailyDateGreaterThanOrEqualTo(queryModel.getStartDate());
		}
		if (StringUtils.isNotBlank(queryModel.getEndDate())) {
			criteria.andDailyDateLessThanOrEqualTo(queryModel.getEndDate());
		}
		if (StringUtils.isNotBlank(queryModel.getCardBin())) {
			criteria.andCardBinEqualTo(queryModel.getCardBin());
		}
		if (StringUtils.isNotBlank(queryModel.getCrdproduct())) {
			criteria.andCrdproductEqualTo(queryModel.getCrdproduct());
		}
		criteria.andAbFlagEqualTo("0");
		example.setOrderByClause(" GEN_DT DESC ");
		return example;
	}

}
