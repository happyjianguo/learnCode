package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.TCardTypeConversionMapper;
import cn.yufu.fs.entity.TCardTypeConversion;
import cn.yufu.fs.entity.TCardTypeConversionExample;
import cn.yufu.fs.entity.TCardTypeConversionExample.Criteria;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.StringUtils;

/**
 * 机构/卡BIN分润Service
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月25日
 * 描述:卡类型Service
 * */
@Service("fs.TCardTypeConversionService")
public class TCardTypeConversionServiceImpl implements TCardTypeConversionService {

	@Autowired
	@Qualifier("fs.TCardTypeConversionDao")
	private TCardTypeConversionMapper cardTypeConversionDao;
	
	@Override
	public TCardTypeConversion get(Integer cardtype) {
		return cardTypeConversionDao.get(cardtype);
	}
	
	@Override
	public int queryCount(TCardTypeConversion queryModel) {
		Integer integer = cardTypeConversionDao.countByExample(getExampleByModel(queryModel));
		if(integer == null){
			return 0;
		}
		return integer;
	}

	@Override
	public List<TCardTypeConversion> selectPageList(TCardTypeConversion queryModel, int startResult, int endResult) {
		return cardTypeConversionDao.selectPageList(getExampleByModel(queryModel), startResult, endResult);
	}

	@Override
	public List<TCardTypeConversion> selectByExample(TCardTypeConversion queryModel) {
		return cardTypeConversionDao.selectByExample(getExampleByModel(queryModel));
	}
	
	@Override
	public Map<String, Object> insertCardType(TCardTypeConversion queryModel) {
		Map<String, Object> map = new HashMap<>();
		int key = cardTypeConversionDao.insertCardType(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加卡类型信息成功。");
		}
		return map;
	}

	@Override
	public Map<String, Object> updateCardType(TCardTypeConversion queryModel) {
		Map<String, Object> map = new HashMap<>();
		int key = cardTypeConversionDao.updateCardType(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡类型信息成功。");
		}
		return map;
	}
	
	@Override
	public Map<String, Object> deleteByPrimaryKey(TCardTypeConversion info) {
		Map<String, Object> map = new HashMap<>();
		int key = cardTypeConversionDao.deleteByPrimaryKey(info);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除卡类型信息成功。");
		}
		return map;
	}

	private TCardTypeConversionExample getExampleByModel(TCardTypeConversion queryModel) {
		TCardTypeConversionExample example = new TCardTypeConversionExample();
		Criteria criteria = example.createCriteria();
		if (null != queryModel) {
			if (null != queryModel.getCardtypeId()) {
				criteria.andCardtypeIdEqualTo(queryModel.getCardtypeId());
			}
			if (null != queryModel.getCardtype()) {
				criteria.andCardtypeEqualTo(queryModel.getCardtype());
			}
			if (StringUtils.isNotEmpty(queryModel.getCardtypeIdName())) {
				criteria.andCardtypeIdNameLike("%" + queryModel.getCardtypeIdName() + "%");
			}
			if (StringUtils.isNotEmpty(queryModel.getCardtypename())) {
				criteria.andCardtypenameLike("%" + queryModel.getCardtypename() + "%");
			}
		}
		example.setOrderByClause(" CARDTYPE DESC ");
		return example;
	}

}
