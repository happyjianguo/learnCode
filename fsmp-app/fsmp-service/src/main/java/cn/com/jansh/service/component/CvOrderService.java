/**
 * CVirOrderService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月2日
 */
package cn.com.jansh.service.component;

import org.springframework.http.ResponseEntity;

import cn.com.jansh.model.component.OrderManageModel;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;

/**
 * 话费流量充值订单服务
 * @author Mr.wong
 * @version 1.0
 */
public interface CvOrderService {

	/**
	 * 保存话费流量订单
	 * @return 
	 * 
	 */
	public JsonVO saveOrder(String cloudVirOrderModelJson , String sign);
	/**
	 * 展示所有的订单
	 * @return 
	 * 
	 */
	public OrderManageModel showAllData(OrderManageModel manageModel);
	/**
	 * 导出报表
	 * @return 
	 * 
	 */
	public ResponseEntity<byte[]> exportData(OrderManageModel orderManageModel) throws Exception;
	/**
	 * 批量补充
	 * @return 
	 * 
	 */
	public OrderManageModel batchRecharge(OrderManageModel orderManageModel)throws Exception;
	/**
	 * 单个补充
	 * @throws Exception 
	 * 
	 */
	public AjaxObj recharge(String id) throws Exception;
	
	
}
