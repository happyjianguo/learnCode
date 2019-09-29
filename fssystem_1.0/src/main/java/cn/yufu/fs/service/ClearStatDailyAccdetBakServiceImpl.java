package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.ClearStatDailyAccdetBakMapper;
import cn.yufu.fs.entity.ClearStatDailyAccdetBak;

@Service("fs.ClearStatDailyAccdetBakService")
public class ClearStatDailyAccdetBakServiceImpl implements ClearStatDailyAccdetBakService {

	@Autowired
	@Qualifier("fs.ClearStatDailyAccdetBakDao")
	private ClearStatDailyAccdetBakMapper clearStatDailyAccdetBakDao;
	
	@Override
	public int queryCount(ClearStatDailyAccdetBak queryModel) {
		Integer queryCount = clearStatDailyAccdetBakDao.queryCount(queryModel);
		if (queryCount == null) {
			return 0;
		}
		return queryCount;
	}
	
	@Override
	public List<ClearStatDailyAccdetBak> selectPageList(ClearStatDailyAccdetBak queryModel, int startResult,
			int endResult) {
		Map<String , Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return clearStatDailyAccdetBakDao.selectPageList(map);
	}

	@Override
	public ClearStatDailyAccdetBak TotalCardSumAndBal(ClearStatDailyAccdetBak queryModel) {
		return clearStatDailyAccdetBakDao.TotalCardSumAndBal(queryModel);
	}

}
