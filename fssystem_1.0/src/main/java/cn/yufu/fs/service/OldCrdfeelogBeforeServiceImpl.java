package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.OldCrdfeelogBeforeMapper;
import cn.yufu.fs.entity.OldCrdfeelogBefore;

@Service("fs.OldCrdfeelogBeforeService")
public class OldCrdfeelogBeforeServiceImpl implements OldCrdfeelogBeforeService {
	
	@Autowired
	@Qualifier("fs.OldCrdfeelogBeforeDao")
	private OldCrdfeelogBeforeMapper crdfeelogBeforeDao;

	@Override
	public int queryCnt(OldCrdfeelogBefore queryModel) {
		Integer integer = crdfeelogBeforeDao.countByExample(queryModel);
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<OldCrdfeelogBefore> queryList(OldCrdfeelogBefore queryModel, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return crdfeelogBeforeDao.selectPageByExample(map);
	}

	@Override
	public List<OldCrdfeelogBefore> queryList(OldCrdfeelogBefore queryModel) {
		return crdfeelogBeforeDao.selectByExample(queryModel);
	}

	@Override
	public OldCrdfeelogBefore selectByPrimaryKey(String cardTypeId) {
		return null;
	}

	@Override
	public BigDecimal getFeeSum(OldCrdfeelogBefore queryModel) {
		return crdfeelogBeforeDao.getFeeSum(queryModel);
	}

}
