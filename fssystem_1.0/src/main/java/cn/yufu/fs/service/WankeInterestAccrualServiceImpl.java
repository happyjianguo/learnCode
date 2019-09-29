package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.WankeInterestAccrualMapper;
import cn.yufu.fs.entity.WankeInterestAccrual;

@Service("fs.WankeInterestAccrualService")
public class WankeInterestAccrualServiceImpl implements WankeInterestAccrualService {
	
	@Autowired
	@Qualifier("fs.WankeInterestAccrualDao")
	private WankeInterestAccrualMapper wankeInterestAccrualDao;
	
	@Override
	public int countByExample(WankeInterestAccrual queryModel) {
		if (queryModel == null) {
			return 0;
		}
		Integer integer = wankeInterestAccrualDao.countByExample(queryModel);
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<WankeInterestAccrual> selectPageByExample(WankeInterestAccrual queryModel, int startResult,
			int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return wankeInterestAccrualDao.selectPageByExample(map);
	}

	@Override
	public List<WankeInterestAccrual> selectByExample(WankeInterestAccrual queryModel) {
		return wankeInterestAccrualDao.selectByExample(queryModel);
	}

	@Override
	public WankeInterestAccrual selectByPrimaryKey(String id) {
		return wankeInterestAccrualDao.selectByPrimaryKey(id);
	}

}
