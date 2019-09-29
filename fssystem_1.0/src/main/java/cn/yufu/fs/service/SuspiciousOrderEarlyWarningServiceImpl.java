package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.SuspiciousOrderEarlyWarningMapper;
import cn.yufu.fs.entity.SuspiciousOrderEarlyWarning;
import cn.yufu.fs.entity.SuspiciousOrderEarlyWarningExample;
import cn.yufu.system.common.utils.Log;

@Service("fs.SuspiciousOrderEarlyWarningService")
public class SuspiciousOrderEarlyWarningServiceImpl implements SuspiciousOrderEarlyWarningService {
	
	Log log = Log.getLog(SuspiciousOrderEarlyWarningServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.SuspiciousOrderEarlyWarningDao")
	private SuspiciousOrderEarlyWarningMapper suspiciousOrderEarlyWarningDao;
	
	@Override
	public int queryCnt(SuspiciousOrderEarlyWarning queryModel) {
		if(queryModel==null){
			return 0;
		}
		return suspiciousOrderEarlyWarningDao.countByExample(this.getExample(queryModel));
	}
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private SuspiciousOrderEarlyWarningExample getExample(SuspiciousOrderEarlyWarning queryModel){
		SuspiciousOrderEarlyWarningExample example = new SuspiciousOrderEarlyWarningExample();
		SuspiciousOrderEarlyWarningExample.Criteria criteria = example.createCriteria();
		if (queryModel.getEndDate() != null && queryModel.getEndDate().trim().length() != 0) {
			criteria.andSuspiciousDateLessThanOrEqualTo(queryModel.getEndDate());
		}
		if (queryModel.getBeginDate() != null && queryModel.getBeginDate().trim().length() != 0) {
			criteria.andSuspiciousDateGreaterThanOrEqualTo(queryModel.getBeginDate());
		}
		if (queryModel.getOrdercode() != null && queryModel.getOrdercode().trim().length() != 0) {
			criteria.andOrdercodeEqualTo(queryModel.getOrdercode());
		}
		if (queryModel.getPurchaseAmt() != null) {
			criteria.andPurchaseAmtGreaterThanOrEqualTo(queryModel.getPurchaseAmt());
		}
		if (queryModel.getSuspiciousDate() != null && queryModel.getSuspiciousDate().trim().length() != 0) {
			criteria.andSuspiciousDateLessThanOrEqualTo(queryModel.getSuspiciousDate());
		}
		if (queryModel.getProvisionsRate() != null) {
			criteria.andProvisionsRateGreaterThanOrEqualTo(queryModel.getProvisionsRate());
		}
		example.setOrderByClause(" SUSPICIOUS_DATE DESC, PURCHASE_DATE DESC ");
		return example;
	}

	@Override
	public List<SuspiciousOrderEarlyWarning> queryList(SuspiciousOrderEarlyWarning queryModel, int startResult,
			int endResult) {
		return suspiciousOrderEarlyWarningDao.selectPageByExample(this.getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<SuspiciousOrderEarlyWarning> queryList(SuspiciousOrderEarlyWarning queryModel) {
		return suspiciousOrderEarlyWarningDao.selectByExample(this.getExample(queryModel));
	}

}
