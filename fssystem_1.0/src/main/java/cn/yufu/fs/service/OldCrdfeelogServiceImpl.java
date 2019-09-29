package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.OldCrdfeelogMapper;
import cn.yufu.fs.entity.OldCrdfeelog;
import cn.yufu.system.common.SysConst;

@Service("fs.OldCrdfeelogService")
public class OldCrdfeelogServiceImpl implements OldCrdfeelogService {
	
	@Autowired
	@Qualifier("fs.OldCrdfeelogDao")
	private OldCrdfeelogMapper crdfeelogDao;

	@Override
	public int queryCnt(OldCrdfeelog queryModel) {
		Integer integer = crdfeelogDao.countByExample(queryModel);
		if (integer == null) {
			return 0;
		}
		return integer;
	}

	@Override
	public List<OldCrdfeelog> queryList(OldCrdfeelog queryModel, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", queryModel);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return crdfeelogDao.selectPageByExample(map);
	}

	@Override
	public List<OldCrdfeelog> queryList(OldCrdfeelog queryModel) {
		return crdfeelogDao.selectByExample(queryModel);
	}

	@Override
	public List<OldCrdfeelog> selectByPrimaryKey(OldCrdfeelog queryModel) {
		return crdfeelogDao.selectByPrimaryKey(queryModel);
	}

	@Override
	public BigDecimal getFeeSum(OldCrdfeelog queryModel) {
		return crdfeelogDao.getFeeSum(queryModel);
	}

	@Override
	public Map<String, Object> refund(OldCrdfeelog crdfeelog) {
		Map<String, Object> map = new HashMap<>();
		int key = crdfeelogDao.refund(crdfeelog);
		if (key > 0) {
			map.put(SysConst.RESULT, SysConst.RESULT_SUCCESS);
			map.put(SysConst.RESULT_MSG, "退款成功。");
		}
		return map;
	}

}
