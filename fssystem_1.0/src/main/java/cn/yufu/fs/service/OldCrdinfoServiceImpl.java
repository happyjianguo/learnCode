package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.OldCrdinfoMapper;
import cn.yufu.fs.entity.OldCrdinfo;

@Service("bak.OldCrdinfoService")
public class OldCrdinfoServiceImpl implements OldCrdinfoService {
	
	@Autowired
	@Qualifier("bak.OldCrdinfoDao")
	private OldCrdinfoMapper oldCrdinfoDao;
	
	@Override
	public int getCounts(OldCrdinfo queryModel) {
		Integer counts = oldCrdinfoDao.getCounts(queryModel);
		if (null == counts) {
			counts = 0;
		}
		return counts;
	}

	@Override
	public List<OldCrdinfo> selectPageList(OldCrdinfo queryModel, int startResult, int endResult) {
		Map<String, Object> fatherMap = new HashMap<>();
		fatherMap.put("queryModel", queryModel);
		fatherMap.put("startResult", startResult);
		fatherMap.put("endResult", endResult);
		return oldCrdinfoDao.selectPageList(fatherMap);
	}

	@Override
	public List<OldCrdinfo> getExcelData(OldCrdinfo queryModel, int startResult, int endResult) {
		Map<String, Object> fatherMap = new HashMap<>();
		fatherMap.put("queryModel", queryModel);
		fatherMap.put("startResult", startResult);
		fatherMap.put("endResult", endResult);
		return oldCrdinfoDao.getExcelData(fatherMap);
	}

	@Override
	public int queryCount(OldCrdinfo queryModel) {
		Integer counts = oldCrdinfoDao.queryCount(queryModel);
		if (counts == null) {
			return 0;
		}
		return counts;
	}

	@Override
	public List<OldCrdinfo> getPageList(OldCrdinfo queryModel, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return oldCrdinfoDao.getPageList(map);
	}

	@Override
	public List<OldCrdinfo> getAllList(OldCrdinfo queryModel) {
		return oldCrdinfoDao.getAllList(queryModel);
	}

	@Override
	public int getPeopleCount(OldCrdinfo queryModel) {
		Integer counts = oldCrdinfoDao.getPeopleCount(queryModel);
		if (counts == null) {
			return 0;
		}
		return counts;
	}
	
}
