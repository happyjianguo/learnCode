package cn.yufu.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.ViewTLogRealTimeMapper;
import cn.yufu.bak.entity.ViewTLogRealTime;
import cn.yufu.system.common.utils.Log;


@Service("bak.ViewTLogRealTimeService")
public class ViewTLogRealTimeServiceImpl implements ViewTLogRealTimeService {
	
	Log log = Log.getLog(ViewTLogRealTimeServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.ViewTLogRealTimeDao")
	private ViewTLogRealTimeMapper viewTLogRealTimeDao;

	@Override
	public int queryCount(ViewTLogRealTime model) {
		if (model == null) return 0;
		Integer queryCount = viewTLogRealTimeDao.getCount(model);
		if (queryCount == null) queryCount = 0;
		return queryCount;
	}

	@Override
	public List<ViewTLogRealTime> getPageList(ViewTLogRealTime model, int startResult, int endResult) {
		return viewTLogRealTimeDao.getPageList(model,startResult, endResult);
	}

	@Override
	public List<ViewTLogRealTime> getAllList(ViewTLogRealTime model) {
		return viewTLogRealTimeDao.getAllList(model);
	}

	@Override
	public String getSumAmt(ViewTLogRealTime queryModel) {
		return viewTLogRealTimeDao.getSumAmt(queryModel);
	}

}
