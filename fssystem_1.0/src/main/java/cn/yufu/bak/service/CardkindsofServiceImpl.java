package cn.yufu.bak.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.CardkindsofMapper;
import cn.yufu.bak.entity.Cardkindsof;
import cn.yufu.bak.entity.CardkindsofExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;


@Service("bak.CardkindsofService")
public class CardkindsofServiceImpl implements CardkindsofService {
	
	Log log = Log.getLog(CardkindsofServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.CardkindsofDao")
	private CardkindsofMapper cardkindsofDao;

	@Override
	public List<Cardkindsof> getCardTypeList(Cardkindsof cardkindsof) {
		if (null == cardkindsof) cardkindsof = new Cardkindsof();
		return cardkindsofDao.getCardTypeList(cardkindsof);
	}

	@Override
	public List<Cardkindsof> getDicCardTypeList(Cardkindsof cardkindsof) {
		if (null == cardkindsof) cardkindsof = new Cardkindsof();
		return cardkindsofDao.getDicCardTypeList(cardkindsof);
	}
	
	@Override
	public int queryCnt(Cardkindsof queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = cardkindsofDao.countByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<Cardkindsof> queryList(Cardkindsof queryModel, int startResult, int endResult) {
		return cardkindsofDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<Cardkindsof> queryList(Cardkindsof queryModel) {
		return cardkindsofDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public Cardkindsof selectByPrimaryKey(String cardnumber) {
		return cardkindsofDao.selectByPrimaryKey(cardnumber);
	}
	
	@Override
	public String getMaXCardNum() {
		return cardkindsofDao.getMaXCardNum();
	}
	
	@Override
	public Map<String, Object> save(Cardkindsof queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = cardkindsofDao.insertSelective(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加卡类型信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKey(Cardkindsof queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = cardkindsofDao.updateByPrimaryKey(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡类型信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKeySelective(Cardkindsof queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = cardkindsofDao.updateByPrimaryKeySelective(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡类型信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteByPrimaryKey(String cardnumber) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = cardkindsofDao.deleteByPrimaryKey(cardnumber);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除卡类型信息成功。");
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
	private CardkindsofExample getExample(Cardkindsof queryModel){
		CardkindsofExample example = new CardkindsofExample();
		CardkindsofExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getCardnumber())) {
			criteria.andCardnumberEqualTo(queryModel.getCardnumber());
		}
		if (StringUtils.isNotBlank(queryModel.getCardkindname())) {
			criteria.andCardkindnameEqualTo(queryModel.getCardkindname());
		}
		if (StringUtils.isNotBlank(queryModel.getIsExclusive())) {
			criteria.andIsExclusiveEqualTo(queryModel.getIsExclusive());
		}
		if (StringUtils.isNotBlank(queryModel.getStlFlag())) {
			criteria.andStlFlagEqualTo(queryModel.getStlFlag());
		}
		example.setOrderByClause(" CARDNUMBER DESC ");
		return example;
	}

}
