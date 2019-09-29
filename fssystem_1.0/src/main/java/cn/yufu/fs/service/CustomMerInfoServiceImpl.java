package cn.yufu.fs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.CustomMerInfoMapper;
import cn.yufu.fs.entity.CustomMerInfo;
import cn.yufu.fs.entity.CustomMerInfoExample;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.CustomMerInfoService")
public class CustomMerInfoServiceImpl implements CustomMerInfoService {
	
	Log log = Log.getLog(CustomMerInfoServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.CustomMerInfoDao")
	private CustomMerInfoMapper customMerInfoDao;

	@Override
	public int countByExample(CustomMerInfo queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = customMerInfoDao.countByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<CustomMerInfo> selectByExample(CustomMerInfo queryModel) {
		return customMerInfoDao.selectByExample(getExample(queryModel));
	}

	@Override
	public List<CustomMerInfo> selectPageByExample(CustomMerInfo queryModel, int startResult, int endResult) {
		return customMerInfoDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}
	
	@Override
	public CustomMerInfo selectByPrimay(String batchId) {
		return customMerInfoDao.selectByPrimay(batchId);
	}
	
	@Override
	public int insert(CustomMerInfo record) {
		if (record != null) {
			record.setBatchId(System.currentTimeMillis() + "");
			if (StringUtils.isNotBlank(record.getMerNo())) {
				record.setChoiceFlag("1");
			}
			if (StringUtils.isNotBlank(record.getCompanyName())) {
				record.setChoiceFlag("2");
			}
		}
		return customMerInfoDao.insert(record);
	}

	@Override
	public int deleteByPrimay(String batchId) {
		return customMerInfoDao.deleteByPrimay(batchId);
	}

	@Override
	public int updateByPrimay(CustomMerInfo record) {
		if (record != null) {
			if (StringUtils.isNotBlank(record.getMerNo())) {
				record.setChoiceFlag("1");
			}
			if (StringUtils.isNotBlank(record.getCompanyName())) {
				record.setChoiceFlag("2");
			}
		}
		return customMerInfoDao.updateByPrimay(record);
	}
	
	private CustomMerInfoExample getExample(CustomMerInfo queryModel) {
		CustomMerInfoExample example = new CustomMerInfoExample();
		CustomMerInfoExample.Criteria criteria = example.createCriteria();
		if (queryModel != null) {
			if (StringUtils.isNotEmpty(queryModel.getBatchId())) {
				criteria.andBatchIdEqualTo(queryModel.getBatchId());
			}
			if (StringUtils.isNotEmpty(queryModel.getOrderDate())) {
				criteria.andOrderDateEqualTo(queryModel.getOrderDate());
			}
			if (StringUtils.isNotEmpty(queryModel.getFlag())) {
				criteria.andFlagEqualTo(queryModel.getFlag());
			}
		}
		example.setOrderByClause(" DEAL_DATE DESC, BATCH_ID DESC ");
		return example;
	}

}
