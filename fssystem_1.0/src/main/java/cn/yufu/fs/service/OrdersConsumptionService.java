/**
 *包名:cn.yufu.fs.service
 *描述:package cn.yufu.fs.service;
 */
package cn.yufu.fs.service;

import java.math.BigDecimal;
import java.util.List;

import cn.yufu.fs.entity.FukaOrders;
import cn.yufu.fs.entity.OrdersConsumption;

/**
 * OrdersConsumptionService.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月15日
 * 描述:订单消费明细及汇总接口
 */
public interface OrdersConsumptionService {

	//获取分页总数
	public int queryCnt(List<String> cardlist);

	//查询数据
	public List<OrdersConsumption> queryList(List<String> cardlist, int startResult, int endResult);
	
	//查询FukaOrders
	public FukaOrders getFukaorders(String ordercode);
	
	//查询消费金额
	public BigDecimal queryTotalList(List<String> cardlist);

}
