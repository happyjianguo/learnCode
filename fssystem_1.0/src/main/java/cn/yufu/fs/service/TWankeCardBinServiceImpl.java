package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.TWankeCardBinMapper;
import cn.yufu.fs.entity.TWankeCardBin;
import cn.yufu.fs.entity.TWankeCardBinExample;
import cn.yufu.fs.entity.TWankeCardBinExample.Criteria;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.StringUtils;

/**
 * 机构/卡BIN分润Service
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月25日
 * 描述:万科卡BIN Service
 * */
@Service("fs.TWankeCardBinService")
public class TWankeCardBinServiceImpl implements TWankeCardBinService {

	@Autowired
	@Qualifier("fs.TWankeCardBinDao")
	private TWankeCardBinMapper wankeCardBinDao;
	
	@Override
	public String getCardId() {
		return wankeCardBinDao.getCardId();
	}
	
	@Override
	public TWankeCardBin selectByPrimaryKey(String cardId) {
		return wankeCardBinDao.selectByPrimaryKey(cardId);
	}
	
	@Override
	public int queryCount(TWankeCardBin queryModel) {
		Integer integer = wankeCardBinDao.countByExample(getExampleByModel(queryModel));
		if(integer == null){
			return 0;
		}
		return integer;
	}

	@Override
	public List<TWankeCardBin> selectPageList(TWankeCardBin queryModel, int startResult, int endResult) {
		return wankeCardBinDao.selectPageList(getExampleByModel(queryModel), startResult, endResult);
	}

	@Override
	public List<TWankeCardBin> selectByExample(TWankeCardBin queryModel) {
		return wankeCardBinDao.selectByExample(getExampleByModel(queryModel));
	}
	
	@Override
	public Map<String, Object> insertCardBin(TWankeCardBin queryModel) {
		Map<String, Object> map = new HashMap<>();
		int key = wankeCardBinDao.insertCardBin(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加万科卡BIN信息成功。");
		}
		return map;
	}

	@Override
	public Map<String, Object> updateCardBin(TWankeCardBin queryModel) {
		Map<String, Object> map = new HashMap<>();
		int key = wankeCardBinDao.updateCardBin(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新万科卡BIN信息成功。");
		}
		return map;
	}
	
	@Override
	public Map<String, Object> deleteByPrimaryKey(TWankeCardBin info) {
		Map<String, Object> map = new HashMap<>();
		int key = wankeCardBinDao.deleteByPrimaryKey(info);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除万科卡BIN信息成功。");
		}
		return map;
	}

	private TWankeCardBinExample getExampleByModel(TWankeCardBin queryModel) {
		TWankeCardBinExample example = new TWankeCardBinExample();
		Criteria criteria = example.createCriteria();
		if (null != queryModel) {
			if (StringUtils.isNotEmpty(queryModel.getCardId())) {
				criteria.andCardIdEqualTo(queryModel.getCardId());
			}
			if (StringUtils.isNotEmpty(queryModel.getCardBin())) {
				criteria.andCardBinEqualTo(queryModel.getCardBin());
			}
		}
		example.setOrderByClause(" CARD_ID DESC ");
		return example;
	}

}
