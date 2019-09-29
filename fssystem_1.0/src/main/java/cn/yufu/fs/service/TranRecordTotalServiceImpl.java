package cn.yufu.fs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.TranRecordTotalMapper;
import cn.yufu.fs.entity.TranRecordTotal;
import cn.yufu.system.common.utils.Log;

@Service("fs.TranRecordTotalService")
public class TranRecordTotalServiceImpl implements TranRecordTotalService {
	Log log = Log.getLog(TranRecordTotalServiceImpl.class);
	
	@Autowired
	@Qualifier("fs.TranRecordTotalDao")
	private TranRecordTotalMapper tranRecordTotalDao;
	
	@Override
	public Integer queryCount(TranRecordTotal tranRecordTotal) {
		if (tranRecordTotal == null) return 0;
		Integer queryCount = tranRecordTotalDao.queryCount(tranRecordTotal);
		if (queryCount == null) return 0;
		return queryCount;
	}

	@Override
	public List<TranRecordTotal> getDataByMrch(TranRecordTotal tranRecordTotal, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", tranRecordTotal);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return tranRecordTotalDao.getDataByMrch(map);
	}

	@Override
	public List<TranRecordTotal> getDataByPan(TranRecordTotal tranRecordTotal, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", tranRecordTotal);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return tranRecordTotalDao.getDataByPan(map);
	}

	@Override
	public List<TranRecordTotal> getDataByTerm(TranRecordTotal tranRecordTotal, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", tranRecordTotal);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return tranRecordTotalDao.getDataByTerm(map);
	}

	@Override
	public List<TranRecordTotal> getDataByMrchAndTerm(TranRecordTotal tranRecordTotal, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", tranRecordTotal);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return tranRecordTotalDao.getDataByMrchAndTerm(map);
	}

	@Override
	public List<TranRecordTotal> getDataByCardType(TranRecordTotal tranRecordTotal, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", tranRecordTotal);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return tranRecordTotalDao.getDataByCardType(map);
	}

	@Override
	public List<TranRecordTotal> getExcelDataByMrch(TranRecordTotal tranRecordTotal) {
		return tranRecordTotalDao.getExcelDataByMrch(tranRecordTotal);
	}

	@Override
	public List<TranRecordTotal> getExcelDataByPan(TranRecordTotal tranRecordTotal) {
		return tranRecordTotalDao.getExcelDataByPan(tranRecordTotal);
	}

	@Override
	public List<TranRecordTotal> getExcelDataByTerm(TranRecordTotal tranRecordTotal) {
		return tranRecordTotalDao.getExcelDataByTerm(tranRecordTotal);
	}

	@Override
	public List<TranRecordTotal> getExcelDataByMrchAndTerm(TranRecordTotal tranRecordTotal) {
		return tranRecordTotalDao.getExcelDataByMrchAndTerm(tranRecordTotal);
	}

	@Override
	public List<TranRecordTotal> getExcelDataByCardType(TranRecordTotal tranRecordTotal) {
		return tranRecordTotalDao.getExcelDataByCardType(tranRecordTotal);
	}

	@Override
	public Integer queryTlogCount(TranRecordTotal tranRecordTotal) {
		if (null == tranRecordTotal) return 0;
		Integer queryCount = tranRecordTotalDao.queryTlogCount(tranRecordTotal);
		if (null == queryCount) return 0;
		return queryCount;
	}

	@Override
	public List<TranRecordTotal> queryTlogList(TranRecordTotal tranRecordTotal, int startResult, int endResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("queryModel", tranRecordTotal);
		map.put("startResult", startResult);
		map.put("endResult", endResult);
		return tranRecordTotalDao.queryTlogList(map);
	}

	@Override
	public List<TranRecordTotal> queryTlogExcel(TranRecordTotal tranRecordTotal) {
		return tranRecordTotalDao.queryTlogExcel(tranRecordTotal);
	}

	@Override
	public String getSumAmt(TranRecordTotal tranRecordTotal) {
		return tranRecordTotalDao.getSumAmt(tranRecordTotal);
	}

}
