package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ViewTranRecordOrgMapper;
import cn.yufu.fs.entity.ViewTranRecordOrg;
import cn.yufu.fs.entity.ViewTranRecordOrgExample;
import cn.yufu.fs.entity.ViewTranRecordOrgExample.Criteria;
import cn.yufu.system.common.utils.StringUtils;

/**
 * 机构/卡BIN分润Service
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月07日
 * 描述:机构/卡BIN分润Service
 * */
@Service("fs.ViewTranRecordOrgService")
public class ViewTranRecordOrgServiceImpl implements ViewTranRecordOrgService {

	@Autowired
	@Qualifier("fs.ViewTranRecordOrgDao")
	private ViewTranRecordOrgMapper viewTranRecordOrgDao;
	
	@Override
	public int queryCount(ViewTranRecordOrg queryModel) {
		Integer integer = viewTranRecordOrgDao.countByExample(getExampleByModel(queryModel));
		if(integer == null){
			return 0;
		}
		return integer;
	}

	@Override
	public List<ViewTranRecordOrg> selectPageList(ViewTranRecordOrg queryModel, int startResult, int endResult) {
		return viewTranRecordOrgDao.selectPageList(getExampleByModel(queryModel), startResult, endResult);
	}

	@Override
	public List<ViewTranRecordOrg> selectByExample(ViewTranRecordOrg queryModel) {
		return viewTranRecordOrgDao.selectByExample(getExampleByModel(queryModel));
	}
	
	@Override
	public ViewTranRecordOrg getSumAmt(ViewTranRecordOrg queryModel) {
		return viewTranRecordOrgDao.getSumAmt(getExampleByModel(queryModel));
	}
	
	private ViewTranRecordOrgExample getExampleByModel(ViewTranRecordOrg queryModel) {
		ViewTranRecordOrgExample example = new ViewTranRecordOrgExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(queryModel.getMerchantnumber())) {
			criteria.andMerchantnumberEqualTo(queryModel.getMerchantnumber());
		}
		if (StringUtils.isNotEmpty(queryModel.getMrchtName())) {
			criteria.andMrchtNameLike("%" + queryModel.getMrchtName() + "%");
		}
		if (StringUtils.isNotEmpty(queryModel.getOrgName())) {
			criteria.andOrgNameLike("%" + queryModel.getOrgName() + "%");
		}
		if (StringUtils.isNotEmpty(queryModel.getOrgBin())) {
			criteria.andOrgBinEqualTo(queryModel.getOrgBin());
		}
		if (StringUtils.isNotEmpty(queryModel.getTransactiondateStart())) {
			criteria.andTransactiondateGreaterThanOrEqualTo(queryModel.getTransactiondateStart());
		}
		if (StringUtils.isNotEmpty(queryModel.getTransactiondateEnd())) {
			criteria.andTransactiondateLessThanOrEqualTo(queryModel.getTransactiondateEnd());
		}
		return example;
	}

}
