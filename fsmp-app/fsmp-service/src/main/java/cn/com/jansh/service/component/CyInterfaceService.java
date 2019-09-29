/**
 * CyInterfaceService.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月26日
 */
package cn.com.jansh.service.component;

import cn.com.jansh.entity.component.CloudATSurplusEntitiy;
import cn.com.jansh.model.component.RechargeManageModel;
import cn.com.jansh.vo.AjaxObj;
import cn.com.jansh.vo.JsonVO;

/**
 * 平台币购买接口
 * @author Mr.wong
 * @version 1.0
 */
public interface CyInterfaceService {

	/**
	 * 支付接口
	 */
	public JsonVO pay(String payno);
	/**
	 * 支付回调接口
	 * @throws Exception
	 */
	public void payBack(String reqBody) throws Exception;
	/**
	 * 显示账户余额
	 */
	public CloudATSurplusEntitiy getAccountSurplus();
	/**
	 * 获取机构账户充值记录
	 */
	public AjaxObj getRechargeRecord();
	/**
	 * 获取机构账户充值记录
	 * @throws Exception
	 */
	public  RechargeManageModel searchRecharge(RechargeManageModel rechargeManageModel);
	/**
	 * 银联支付交易结果查询
	 * 
	 */
	public void tradeCheck();
}
