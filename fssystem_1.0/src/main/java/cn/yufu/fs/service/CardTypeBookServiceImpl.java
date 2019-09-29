package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.CardTypeBookMapper;
import cn.yufu.fs.entity.CardTypeBook;
import cn.yufu.fs.entity.CardTypeBookExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.CardTypeBookService")
public class CardTypeBookServiceImpl implements CardTypeBookService {
	
	Log log = Log.getLog(CardTypeBookServiceImpl.class);

	@Autowired
	@Qualifier("fs.CardTypeBookDao")
	private CardTypeBookMapper CardTypeBookDao;
	
	@Override
	public int queryCnt(CardTypeBook queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = CardTypeBookDao.countByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<CardTypeBook> queryList(CardTypeBook queryModel, int startResult, int endResult) {
		return CardTypeBookDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<CardTypeBook> queryList(CardTypeBook queryModel) {
		return CardTypeBookDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public CardTypeBook selectByPrimaryKey(String cardTypeId) {
		return CardTypeBookDao.selectByPrimaryKey(cardTypeId);
	}
	
	@Override
	public Map<String, Object> save(CardTypeBook queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = CardTypeBookDao.insert(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加卡类型码表信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKey(CardTypeBook queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = CardTypeBookDao.updateByPrimaryKey(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡类型码表信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteByPrimaryKey(String cardTypeId) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = CardTypeBookDao.deleteByPrimaryKey(cardTypeId);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除卡类型码表信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private CardTypeBookExample getExample(CardTypeBook queryModel){
		CardTypeBookExample example = new CardTypeBookExample();
		CardTypeBookExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getCardTypeId())) {
			criteria.andCardTypeIdEqualTo(queryModel.getCardTypeId());
		}
		example.setOrderByClause("CARD_TYPE_ID DESC ");
		return example;
	}

}
