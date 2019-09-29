package cn.yufu.bak.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.CardKindComesourceMapper;
import cn.yufu.bak.entity.CardKindComesource;
import cn.yufu.bak.entity.CardKindComesourceExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;


@Service("bak.CardKindComesourceService")
public class CardKindComesourceServiceImpl implements CardKindComesourceService {
	
	Log log = Log.getLog(CardKindComesourceServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.CardKindComesourceDao")
	private CardKindComesourceMapper cardKindComesourceDao;

	@Override
	public int queryCnt(CardKindComesource queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = cardKindComesourceDao.countByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<CardKindComesource> queryList(CardKindComesource queryModel, int startResult, int endResult) {
		return cardKindComesourceDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<CardKindComesource> queryList(CardKindComesource queryModel) {
		return cardKindComesourceDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public CardKindComesource selectByPrimaryKey(String cardnumber) {
		return cardKindComesourceDao.selectByPrimaryKey(cardnumber);
	}
	
	@Override
	public String getMaxId() {
		return cardKindComesourceDao.getMaxId();
	}
	
	@Override
	public Map<String, Object> save(CardKindComesource queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = cardKindComesourceDao.insertSelective(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加卡类型信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKey(CardKindComesource queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = cardKindComesourceDao.updateByPrimaryKey(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡类型来源信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKeySelective(CardKindComesource queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = cardKindComesourceDao.updateByPrimaryKeySelective(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡类型来源信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteByPrimaryKey(String cardnumber) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = cardKindComesourceDao.deleteByPrimaryKey(cardnumber);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除卡类型来源信息成功。");
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
	private CardKindComesourceExample getExample(CardKindComesource queryModel){
		CardKindComesourceExample example = new CardKindComesourceExample();
		CardKindComesourceExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getCardnumber())) {
			criteria.andCardnumberEqualTo(queryModel.getCardnumber());
		}
		if (StringUtils.isNotBlank(queryModel.getOldKindid())) {
			criteria.andOldKindidEqualTo(queryModel.getOldKindid());
		}
		if (StringUtils.isNotBlank(queryModel.getOldKindidLike())) {
			criteria.andOldKindidLikeLike("%" + queryModel.getOldKindidLike() + "%");
		}
		if (StringUtils.isNotBlank(queryModel.getDataBaseType())) {
			criteria.andDataBaseTypeEqualTo(queryModel.getDataBaseType());
		}
		example.setOrderByClause(" ID DESC, CARDNUMBER DESC ");
		return example;
	}

}
