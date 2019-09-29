/**
 * WinningrecordController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月3日
 */
package cn.com.jansh.controller.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.recharge.CloudwinningrecordEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.query.CloudwinningrecordModel;
import cn.com.jansh.service.game.GameInitService;
import cn.com.jansh.service.query.WinningrecordService;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 订单记录查询控制类
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value="/winningrecordq")
public class WinningrecordController {
	
	private static final Logger logger = LogManager.getLogger(WinningrecordController.class);
	
	@Autowired
	private CloudplatformorginationService cloudpfgService; //机构接口
	
	@Autowired
	private WinningrecordService winningrecordService;
	
	@Autowired
	private GameInitService gameInitService; //活动的接口
	
	/**
	 * 查询数据
	 */
	@RequestMapping(value="init")
	public String init(CloudwinningrecordModel winningrecordModel) {
		logger.info("订单记录查询");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		
		List<CloudwinningrecordEntity> cloudwinningrecordList = winningrecordService.queryAll(modelToEntity(winningrecordModel)); //获取所有审核记录数据
		
		winningrecordModel.setCloudpforgList(orgList); //机构下拉列表
		
		//活动状态-订单获取全部活动
		List<String> status = new ArrayList<>();
		status.add(ContextCode.ACTIVITY_STATUS_0.value());
		status.add(ContextCode.ACTIVITY_STATUS_1.value());
		status.add(ContextCode.ACTIVITY_STATUS_2.value());
		status.add(ContextCode.ACTIVITY_STATUS_3.value());
		List<CloudGameInitEntity> gameNameList = gameInitService.getGameNameAndCode(status);
		winningrecordModel.setGameCodeAndNameList(gameNameList); //活动的下拉列表
		
		winningrecordModel.setCloudwinningrecordList(cloudwinningrecordList);
		
		return "/query/winningrecordlist";
	}

	/**
	 * MODEL转换实体
	 * @param winningrecordModel
	 * @return
	 */
	private CloudwinningrecordEntity modelToEntity(CloudwinningrecordModel winningrecordModel) {
		CloudwinningrecordEntity entity = new CloudwinningrecordEntity();
		entity.setOrgid(winningrecordModel.getOrgid());
		entity.setOpenid(winningrecordModel.getOpenid());
		entity.setPreliminarystatus(winningrecordModel.getPreliminarystatus());
		entity.setWinnerphone(winningrecordModel.getWinnerphone());
		entity.setGameid(winningrecordModel.getGameid());
		return entity;
	}
}
