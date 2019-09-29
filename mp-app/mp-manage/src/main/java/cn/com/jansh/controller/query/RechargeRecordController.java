/**
 * RechargeRecordController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月2日
 */
package cn.com.jansh.controller.query;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.entity.recharge.CloudrechargerecordEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.query.CloudrechargerecordModel;
import cn.com.jansh.service.query.RechargeRecordService;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 充值记录查询控制类
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value="/rechargerecordq")
public class RechargeRecordController {
	
	private static final Logger logger = LogManager.getLogger(RechargeRecordController.class);
	
	@Autowired
	private CloudplatformorginationService cloudpfgService; //机构接口
	
	@Autowired
	private RechargeRecordService rechRecService; 
	
	
	/**
	 * 充值记录查询页面
	 */
	@RequestMapping(value="init")
	public String init(CloudrechargerecordModel rechgModel) {
		logger.info("充值记录查询");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		
		List<CloudrechargerecordEntity> rechargerecordList = rechRecService.queryAll(modelToEntity(rechgModel)); //获取所有审核记录数据
		
		rechgModel.setCloudpforgList(orgList); //机构下拉列表
		
		rechgModel.setCloudrechargerecordList(rechargerecordList);
		
		return "/query/rechargerecordlist";
	}
	
	/**
	 * model转Entity
	 */
	private CloudrechargerecordEntity modelToEntity(CloudrechargerecordModel rechargerecordModel){
		CloudrechargerecordEntity rechargerecordEntity = new CloudrechargerecordEntity();
		rechargerecordEntity.setOrgid(rechargerecordModel.getOrgid());
		rechargerecordEntity.setUserid(rechargerecordModel.getUserid());
		return rechargerecordEntity;
	}
}
