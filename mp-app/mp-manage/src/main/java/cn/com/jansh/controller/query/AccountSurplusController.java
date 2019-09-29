/**
 * AccountSurplusController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月1日
 */
package cn.com.jansh.controller.query;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.entity.recharge.CloudaccountsurplusEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.recharge.AccountSurplusModel;
import cn.com.jansh.service.recharge.CloudaccountsurplusService;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 账户余额查询
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value="/accountbalance")
public class AccountSurplusController {
	
	private static final Logger logger = LogManager.getLogger(AccountSurplusController.class);

	@Autowired
	private CloudaccountsurplusService cloudaccountsurplusService; //账户余额接口
	
	@Autowired
	private CloudplatformorginationService cloudpfgService; //机构接口
	
	/**
	 * 页面初始化
	 */
	@RequestMapping(value = "/init")
	public String init(AccountSurplusModel accountSurplusModel) {
		logger.info("人工充值始化页面");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		
		List<CloudaccountsurplusEntity> accountpList = cloudaccountsurplusService.queryAll(modelToEntity(accountSurplusModel)); //获取所有审核记录数据
		
		accountSurplusModel.setCloudpforgList(orgList); //机构下拉列表
		
		accountSurplusModel.setAccountBalanceList(accountpList); //充值审核记录
		
		return "/query/accountsurpluslist";
	}
	
	/**
	 * model转Entity
	 */
	private CloudaccountsurplusEntity modelToEntity(AccountSurplusModel accountsurplusrevModel){
		CloudaccountsurplusEntity accountsurpEntity = new CloudaccountsurplusEntity();
		accountsurpEntity.setOrgid(accountsurplusrevModel.getOrgid());
		return accountsurpEntity;
	}
	
}
