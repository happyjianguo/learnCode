/**
 * RechargeAuditController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月21日
 */
package cn.com.jansh.controller.review;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.constant.ContextCode;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.comm.util.DateUtil;
import com.jansh.core.web.util.AppUtil;
import cn.com.jansh.entity.recharge.CloudaccountsurplusrevEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.recharge.CloudaccountsurplusrevModel;
import cn.com.jansh.service.recharge.AccountSurplusrevService;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 平台币充值审核
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/rechargeaudit")
public class RechargeAuditController {
	
private static final Logger logger = LogManager.getLogger(RechargeAuditController.class);	
	
	@Autowired
	private AccountSurplusrevService accSurService; //审核表接口
	
	@Autowired
	private CloudplatformorginationService cloudpfgService; //机构接口
	
	/**
	 * 充值页面初始化
	 */
	@RequestMapping(value = "/init")
	public String rechargeinit(CloudaccountsurplusrevModel cloudaccsupMolde) {
		logger.info("人工充值始化页面");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		
		cloudaccsupMolde.setRstatus(ContextCode.RECHARGE_RSTATUS_AUDIT.value()); //查询所有待审核状态的数据
		List<CloudaccountsurplusrevEntity> accountpList = accSurService.queryAll(modelToEntity(cloudaccsupMolde)); //获取所有审核记录数据
		
		cloudaccsupMolde.setCloudpforgList(orgList); //机构下拉列表
		
		cloudaccsupMolde.setAccountsurpList(accountpList); //充值审核记录
		
		return "/review/rechargeaudit";
	}
	
	
	/**
	 * 初始化人工充值审核页面
	 */
	@RequestMapping(value = "/rechargeinit")
	@ExceptionHandle("/rechargeaudit/init")
	public String rechargeinit(CloudaccountsurplusrevModel cloudaccountModel,Model model) {
		logger.info("人工充值审核页面初始化");
		cloudaccountModel = entityToModel(accSurService.queryById(cloudaccountModel.getId()));
		model.addAttribute(cloudaccountModel);
		return "/review/rechargeauditop";
	}
	
	/**
	 * 审核人工充值。
	 * 1、审核通过后更新原有的审核列表状态；
	 * 2、查询余额表是否存在记录：
	 *    2.1：存在记录，则将金额相加在更新到数据库中
	 *    2.2：不存在记录，则根据表结构新增记录到数据库中
	 * 
	 */
	@RequestMapping(value = "/rechargesb")
	@ExceptionHandle("/rechargeaudit/init")
	@OperationResult(value=Operation.UPDATE)
	@OperationLog(key = "button.reviewed")
	@SecurityRequest(repeatRequest=true)
	public String rechargesb(CloudaccountsurplusrevModel cloudaccsupMolde){
		logger.info("人工充值审核处理:{}",cloudaccsupMolde);
		//根据审核状态进行不同的处理
		String status = cloudaccsupMolde.getRstatus(); //审核状态
		
		cloudaccsupMolde.setAuditor(AppUtil.getUserDetail().getUsername()); //审核人
		cloudaccsupMolde.setUpdatetime(DateUtil.getDateTimestamp()); //审核日期
		
		if(ContextCode.RECHARGE_RSTATUS_FAILE.value().equals(status)) {
			logger.info("人工充值审核不通过处理");
			//审核不通过的处理-直接更新数据
			accSurService.updateByOrgid(modelToEntity(cloudaccsupMolde));
			
		}else if(ContextCode.RECHARGE_RSTATUS_SUCCES.value().equals(status)) {
			logger.info("人工充值审核通过处理");
			//数据库多表更新，事务控制
			accSurService.recharge(cloudaccsupMolde);
		}
		
		cloudaccsupMolde.setOrgid("");
		return rechargeinit(cloudaccsupMolde);
	}
	
	/**
	 * Entity转model
	 */
	public CloudaccountsurplusrevModel entityToModel(CloudaccountsurplusrevEntity cloudaccountEntity) {
		CloudaccountsurplusrevModel cloudaccountModel = new CloudaccountsurplusrevModel();
		cloudaccountModel.setId(cloudaccountEntity.getId());
		cloudaccountModel.setOrgid(cloudaccountEntity.getOrgid());
		cloudaccountModel.setOrgname(cloudaccountEntity.getOrgname());
		cloudaccountModel.setAmount(cloudaccountEntity.getAmount());
		BigDecimal num1 = new BigDecimal("1000");
		cloudaccountModel.setMoney(cloudaccountEntity.getAmount().divide(num1));
		return cloudaccountModel;
	}
	
	/**
	 * model转Entity
	 */
	private CloudaccountsurplusrevEntity modelToEntity(CloudaccountsurplusrevModel accountsurplusrevModel){
		CloudaccountsurplusrevEntity accountsurpEntity = new CloudaccountsurplusrevEntity();
		accountsurpEntity.setId(accountsurplusrevModel.getId());
		accountsurpEntity.setOrgid(accountsurplusrevModel.getOrgid());
		accountsurpEntity.setRstatus(accountsurplusrevModel.getRstatus());
		accountsurpEntity.setAudresult(accountsurplusrevModel.getAudresult());
		accountsurpEntity.setAuditor(accountsurplusrevModel.getAuditor());
		accountsurpEntity.setUpdatetime(accountsurplusrevModel.getUpdatetime());
		return accountsurpEntity;
	}

}
