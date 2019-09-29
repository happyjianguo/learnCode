package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.MerstlErrorSetMapper;
import cn.yufu.fs.entity.MerstlErrorSet;
import cn.yufu.fs.entity.MerstlErrorSetExample;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;

@Service("fs.MerstlErrorSetService")
public class MerstlErrorSetServiceImpl implements MerstlErrorSetService {
	
	Log log = Log.getLog(MerstlErrorSetServiceImpl.class);

	@Autowired
	@Qualifier("fs.MerstlErrorSetDao")
	private MerstlErrorSetMapper merstlErrorSetDao;
	
	@Override
	public int countByExample(MerstlErrorSet queryModel) {
		if(queryModel==null){
			return 0;
		}
		Integer integer = merstlErrorSetDao.countByExample(getExample(queryModel));
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<MerstlErrorSet> selectPageByExample(MerstlErrorSet queryModel, int startResult, int endResult) {
		return merstlErrorSetDao.selectPageByExample(getExample(queryModel), startResult, endResult);
	}

	@Override
	public List<MerstlErrorSet> selectByExample(MerstlErrorSet queryModel) {
		return merstlErrorSetDao.selectByExample(getExample(queryModel));
	}
	
	@Override
	public MerstlErrorSet selectByPrimaryKey(String id) {
		return merstlErrorSetDao.selectByPrimaryKey(id);
	}
	
	@Override
	public Map<String, Object> insertSelective(MerstlErrorSet queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = merstlErrorSetDao.insertSelective(queryModel);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "添加卡余额调整信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKey(MerstlErrorSet queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = merstlErrorSetDao.updateByPrimaryKey(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡余额调整信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}
	
	@Override
	public Map<String, Object> updateByPrimaryKeySelective(MerstlErrorSet queryModel) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int key = merstlErrorSetDao.updateByPrimaryKeySelective(queryModel);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "更新卡余额调整信息成功。");
		}else{
			throw new Exception();
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteByPrimaryKey(String id) throws Exception{
		Map<String, Object> map = new HashMap<>();
		int insert = merstlErrorSetDao.deleteByPrimaryKey(id);
		if (insert > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "删除卡余额调整信息成功。");
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
	private MerstlErrorSetExample getExample(MerstlErrorSet queryModel){
		MerstlErrorSetExample example = new MerstlErrorSetExample();
		MerstlErrorSetExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryModel.getId())) {
			criteria.andIdEqualTo(queryModel.getId());
		}
		return example;
	}

}
