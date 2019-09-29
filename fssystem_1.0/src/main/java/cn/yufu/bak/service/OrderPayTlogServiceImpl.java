package cn.yufu.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.bak.dao.OrderPayTlogMapper;
import cn.yufu.bak.entity.OrderPayTlog;
import cn.yufu.system.common.utils.Log;

@Service("bak.OrderPayTlogService")
public class OrderPayTlogServiceImpl implements OrderPayTlogService {
	
	Log log = Log.getLog(OrderPayTlogServiceImpl.class);
	
	@Autowired
	@Qualifier("bak.OrderPayTlogDao")
	private OrderPayTlogMapper orderPayTlogDao;

	@Override
	public int countByExample(OrderPayTlog queryModel) {
		if(null == queryModel) return 0;
		Integer count = orderPayTlogDao.countByExample(queryModel);
		if (null == count) return 0;
		return count;
	}

	@Override
	public List<OrderPayTlog> selectPageByExample(OrderPayTlog queryModel, int startResult, int endResult) {
		return orderPayTlogDao.selectPageByExample(queryModel, startResult, endResult);
	}

	@Override
	public List<OrderPayTlog> selectByExample(OrderPayTlog queryModel) {
		return orderPayTlogDao.selectByExample(queryModel);
	}
	
}
