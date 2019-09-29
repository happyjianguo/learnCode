package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.CombineDateMapper;
import cn.yufu.fs.entity.CombineDate;
import cn.yufu.fs.entity.CombineDateExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.CombineDateService")
public class CombineDateServiceImpl implements CombineDateService {
	
	Log log = Log.getLog(CombineDateServiceImpl.class);

	@Autowired
	@Qualifier("fs.CombineDateDao")
	private CombineDateMapper combineDateDao;
	
	@Override
	public int queryCnt(CombineDate queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = combineDateDao.countByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<CombineDate> queryList(CombineDate queryModel, int startResult, int endResult) {
		return combineDateDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<CombineDate> queryList(CombineDate queryModel) {
		return combineDateDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public CombineDate selectByPrimaryKey(String id) {
		return combineDateDao.selectByPrimaryKey(id);
	}
	
	@Override
	public Map<String, Object> save(CombineDate queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = combineDateDao.insertSelective(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加节假日结算合并信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKey(CombineDate queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = combineDateDao.updateByPrimaryKey(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新节假日结算合并信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKeySelective(CombineDate queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = combineDateDao.updateByPrimaryKeySelective(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新节假日结算合并信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteByPrimaryKey(String id) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = combineDateDao.deleteByPrimaryKey(id);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除节假日结算合并信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public CombineDate selectMaxEndDate(CombineDate queryModel) {
		return combineDateDao.selectMaxEndDate(queryModel);
	}
	
	/**
	 * 封装查询条件
	 * @param queryModel
	 * @return
	 */
	private CombineDateExample getExample(CombineDate queryModel){
		CombineDateExample example = new CombineDateExample();
		CombineDateExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getId())) {
			criteria.andIdEqualTo(queryModel.getId());
		}
		if (StringUtils.isNotBlank(queryModel.getStartDate())) {
			criteria.andStartDateGreaterThanOrEqualTo(queryModel.getStartDate());
		}
		if (StringUtils.isNotBlank(queryModel.getEndDate())) {
			criteria.andEndDateLessThanOrEqualTo(queryModel.getEndDate());
		}
		if (StringUtils.isNotBlank(queryModel.getDoneFlag())) {
			criteria.andDoneFlagEqualTo(queryModel.getDoneFlag());
		}
		if (StringUtils.isNotBlank(queryModel.getDelFlag())) {
			criteria.andDelFlagEqualTo(queryModel.getDelFlag());
		} else {
			criteria.andDelFlagEqualTo("0");	//默认只显示正常
		}
		example.setOrderByClause(" END_DATE DESC, UPDATE_DATE DESC ");
		return example;
	}

}
