/**
 *包名:cn.yufu.fs.dao
 *描述:package cn.yufu.fs.dao;
 */
package cn.yufu.fs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yufu.fs.entity.FukaOrders;
import cn.yufu.fs.entity.OrdersConsumption;

/**
 * OrdersConsumptionMapper.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月15日
 * 描述:订单消费明细及汇总Mapper
 */
@Repository("fs.OrdersConsumptionDao")
public interface OrdersConsumptionMapper {

	//通过卡号查询信息
	public List<OrdersConsumption> queryList(@Param("cardlist") List<String> cardlist,@Param("startResult") int startResult, @Param("endResult") int endResult);

	//查询总数
	public int querycount(@Param("cardnumber") String cardnumber);

	//查询总数
	public FukaOrders getFukaorders(@Param("ordercode") String ordercode);

	//查询消费总金额
	public BigDecimal queryTotalList(@Param("cardlist") List<String> cardlist);

}
