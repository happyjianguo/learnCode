package cn.yufu.bak.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.NewCrdinfoMapper;
import cn.yufu.bak.entity.NewCrdinfo;

@Service("bak.NewCrdinfoService")
public class NewCrdinfoServiceImpl implements NewCrdinfoService {
	
	@Autowired
	@Qualifier("bak.NewCrdinfoDao")
	private NewCrdinfoMapper newCrdinfoDao;
	
	@Override
	public List<NewCrdinfo> getExcelData(NewCrdinfo queryModel, int startResult, 
			int endResult) {
		Map<String, Object> fatherMap = new HashMap<>();
		fatherMap.put("queryModel", queryModel);
		fatherMap.put("startResult", startResult);
		fatherMap.put("endResult", endResult);
		return newCrdinfoDao.getExcelData(fatherMap);
	}
	
	@Override
	public int getCounts(NewCrdinfo queryModel) {
		Integer counts = newCrdinfoDao.getCounts(queryModel);
		if (null == counts) {
			counts = 0;
		}
		return counts;
	}
	
	@Override
	public List<NewCrdinfo> selectPageList(NewCrdinfo queryModel, int startResult, int endResult) {
		Map<String, Object> fatherMap = new HashMap<>();
		fatherMap.put("queryModel", queryModel);
		fatherMap.put("startResult", startResult);
		fatherMap.put("endResult", endResult);
		return newCrdinfoDao.selectPageList(fatherMap);
	}

	@Override
	public int queryCount(NewCrdinfo queryModel) {
		Integer counts = newCrdinfoDao.queryCount(queryModel);
		if (counts == null) {
			return 0;
		}
		return counts;
	}

	@Override
	public List<NewCrdinfo> getPageList(NewCrdinfo queryModel, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return newCrdinfoDao.getPageList(map);
	}

	@Override
	public List<NewCrdinfo> getAllList(NewCrdinfo queryModel) {
		return newCrdinfoDao.getAllList(queryModel);
	}

	@Override
	public int getPeopleCount(NewCrdinfo queryModel) {
		Integer counts = newCrdinfoDao.getPeopleCount(queryModel);
		if (counts == null) {
			return 0;
		}
		return counts;
	}

}
