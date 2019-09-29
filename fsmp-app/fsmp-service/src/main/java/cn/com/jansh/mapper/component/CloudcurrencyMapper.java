/**
 * CloudCFMapper.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月27日
 */
package cn.com.jansh.mapper.component;

import java.util.List;
import java.util.Map;

import cn.com.jansh.entity.component.CfolTradeQueryEntity;
import cn.com.jansh.entity.component.bo.ShowRechargeBO;

/**
 * 平台币充值信息处理接口
 * @author Mr.wong
 * @version 1.0
 */
public interface CloudcurrencyMapper {

	/**
	 *插入充值信息
	 */
	public void insert(CfolTradeQueryEntity tradeQueryEntity  );
	/**
	 *更新充值信息
	 */
	public void update(CfolTradeQueryEntity tradeQueryEntity  );
	/**
	 *获取充值记录
	 */
	public CfolTradeQueryEntity selectByOrderid(String orderid);
	/**
	 *获取机构全部充值记录
	 */
	public List<CfolTradeQueryEntity> selectByOrgid(String orgid);

	/**
	 * 根据查询条件获取充值记录信息
	 * @param map
	 * @return
	 */
	public List<CfolTradeQueryEntity> select(Map<String,Object> map);
	/**
	 * 根据查询条件获取充值记录
	 */
	public List<ShowRechargeBO> searchRecharge(Map<String, Object> map);
	/**
	 * 根据查询条件获取充值条数
	 */
	public int searchRechargeCount(Map<String, Object> map);
}
