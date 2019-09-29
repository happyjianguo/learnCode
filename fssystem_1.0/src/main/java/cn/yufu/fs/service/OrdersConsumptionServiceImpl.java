/**
 *包名:cn.yufu.fs.service
 *描述:package cn.yufu.fs.service;
 */
package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.yufu.fs.dao.OrdersConsumptionMapper;
import cn.yufu.fs.entity.FukaOrders;
import cn.yufu.fs.entity.OrdersConsumption;
import cn.yufu.system.common.utils.Log;

/**
 * OrdersConsumptionServiceImpl.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月15日
 * 描述:订单消费明细及汇总接口Impl
 */
@Service("fs.OrdersConsumptionService")
public class OrdersConsumptionServiceImpl implements OrdersConsumptionService {

	//日志
	Log log = Log.getLog(OrdersConsumptionServiceImpl.class);

	//
	@Autowired
	@Qualifier("fs.OrdersConsumptionDao")
	private OrdersConsumptionMapper OrdersConsumptionDao;
	@Override
	public int queryCnt(List<String> cardlist) {
		log.debug("查询总数",cardlist);
		if(null==cardlist){
			return 0;
		}
		int total=0;
		for(int i=0;i<cardlist.size();i++){
			int sum = OrdersConsumptionDao.querycount(cardlist.get(i));
			total = sum + total;
		}
		return total;
	}

	@Override
	public List<OrdersConsumption> queryList(List<String> cardlist, int startResult, int endResult) {
		log.debug("查询数据",cardlist,startResult,endResult);
		List<OrdersConsumption> list = new LinkedList<OrdersConsumption>();
		list = OrdersConsumptionDao.queryList(cardlist, startResult,  endResult);
		return list;
	}

	@Override
	public FukaOrders getFukaorders(String ordercode) {
		log.debug("查询FukaOrders",ordercode);
		return OrdersConsumptionDao.getFukaorders(ordercode);
	}

	@Override
	public BigDecimal queryTotalList(List<String> cardlist) {
		log.debug("查询消费金额",cardlist);
		if(null == cardlist || cardlist.size() ==0 ){
			return new BigDecimal("0");
		}else{
			return OrdersConsumptionDao.queryTotalList(cardlist);
		}
	}
}
