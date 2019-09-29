/**
 * WinningRecordController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年11月4日
 */
package cn.com.jansh.controller.review;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;
import com.jansh.core.web.servlet.ViewObject;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.constant.ContextCode;
import cn.com.jansh.controller.query.WinningrecordController;
import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.recharge.CloudwinningrecordEntity;
import cn.com.jansh.entity.system.CloudplatformorginationEntity;
import cn.com.jansh.model.query.CloudwinningrecordModel;
import cn.com.jansh.service.game.GameInitService;
import cn.com.jansh.service.query.WinningrecordService;
import cn.com.jansh.service.system.CloudplatformorginationService;

/**
 * 活动中奖订单数据审核
 * @author xieliangliang
 * @version 1.0
 */
@Controller
@RequestMapping(value="winningrecord")
public class WinningRecordController {
	
	private static final Logger logger = LogManager.getLogger(WinningrecordController.class);
	
	@Autowired
	private CloudplatformorginationService cloudpfgService; //机构接口
	
	@Autowired
	private WinningrecordService winningrecordService;
	
	@Autowired
	private GameInitService gameInitService; //活动的接口
	
	/**
	 * 数据初始化
	 */
	@RequestMapping(value="init")
	public String init(CloudwinningrecordModel winningrecordModel) {
		logger.info("订单记录查询");
		
		List<CloudplatformorginationEntity> orgList = cloudpfgService.queryAllDate(); //获取机构的下拉列表值
		winningrecordModel.setPreliminarystatus(ContextCode.PRELIMINARY_STATUS_WAIT.value()); //初始化需要审核状态的数据
		List<CloudwinningrecordEntity> cloudwinningrecordList = winningrecordService.queryAll(modelToEntity(winningrecordModel)); //获取所有审核记录数据
		
		//活动状态-订单获取全部活动
		List<String> status = new ArrayList<>();
		status.add(ContextCode.ACTIVITY_STATUS_0.value());
		status.add(ContextCode.ACTIVITY_STATUS_1.value());
		status.add(ContextCode.ACTIVITY_STATUS_2.value());
		status.add(ContextCode.ACTIVITY_STATUS_3.value());
		List<CloudGameInitEntity> gameNameList = gameInitService.getGameNameAndCode(status);
		winningrecordModel.setGameCodeAndNameList(gameNameList); //活动的下拉列表
		
		winningrecordModel.setCloudpforgList(orgList); //机构下拉列表
		
		winningrecordModel.setCloudwinningrecordList(cloudwinningrecordList);
		
		return "/review/winningrecord";
	}
	
	/**
	 * 审核页面初始化
	 */
	@RequestMapping(value="reviewinit")
	@ExceptionHandle("/registerreview/init")
	public String reviewinit(CloudwinningrecordModel winningrecordModel,Model model) {
		logger.info("订单审核页面初始化");
		winningrecordModel = entityToModel(winningrecordService.queryByUserId(winningrecordModel.getId()));
		model.addAttribute(winningrecordModel);
		return "/review/winningrecordop";
	}
	
	/**
	 * 审核操作
	 */
	@RequestMapping(value = "/reviewsb")
	@ExceptionHandle("/registerreview/init")
	@OperationResult(value=Operation.UPDATE)
	@OperationLog(key = "button.reviewed")
	@SecurityRequest(repeatRequest=true)
	public String reviewsb(CloudwinningrecordModel winningrecordModel) {
		logger.info("审核订单充值信息：{}",winningrecordModel);
		winningrecordModel.setPreliminaryper(AppUtil.getUserDetail().getUsername()); //审核人
		winningrecordModel.setUpdatetime(DateUtil.getDateTimestamp()); //审核日期

		winningrecordService.updateStatus(modelToEntity(winningrecordModel));
		
		//spring标签需要将model清空
		winningrecordModel.setOpenid("");
		winningrecordModel.setWinnerphone("");
		winningrecordModel.setOrgid("");
		winningrecordModel.setPreliminarystatus("");
		
		return init(winningrecordModel);
	}	
	
	/**
	 * 批量审核操作
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/ajax/batchUpdate")
	@OperationResult(value=Operation.UPDATE)
	@OperationLog(key = "button.batchreviewed")
	@ResponseBody
	private ViewObject batchUpdate(CloudwinningrecordEntity entity) {
		logger.info("数据初始化");

		//将传递的idss集合进行处理，转换为集合并存储到参数实体集合中
		String idss = entity.getIdss();
	    String[] arr = idss.split(",");
	    List<String> list = java.util.Arrays.asList(arr);
		
		List<CloudwinningrecordEntity> parList = new ArrayList<CloudwinningrecordEntity>();
		CloudwinningrecordEntity parEntity = null;
		for(int i =0;i<list.size();i++) {
			parEntity = new CloudwinningrecordEntity();
			parEntity.setId(list.get(i));
			parEntity.setPreliminarystatus(entity.getPreliminarystatus());
			parEntity.setReviewdes(entity.getReviewdes());
			parEntity.setPreliminaryper(AppUtil.getUserDetail().getUsername()); 
			parEntity.setUpdatetime(DateUtil.getDateTimestamp());
			
			parList.add(parEntity);
		}
		winningrecordService.batchUpdate(parList); //批量更新
		return new ViewObject();
	}
	
	/**
	 * 实体转换MODEL
	 * @param queryByUserId
	 * @return
	 */
	private CloudwinningrecordModel entityToModel(CloudwinningrecordEntity entity) {
		CloudwinningrecordModel model = new CloudwinningrecordModel();
		model.setId(entity.getId());
		
		return model;
	}

	/**
	 * MODEL转换实体
	 * @param winningrecordModel
	 * @return
	 */
	private CloudwinningrecordEntity modelToEntity(CloudwinningrecordModel winningrecordModel) {
		CloudwinningrecordEntity entity = new CloudwinningrecordEntity();
		entity.setId(winningrecordModel.getId());
		entity.setOrgid(winningrecordModel.getOrgid());
		entity.setPreliminarystatus(winningrecordModel.getPreliminarystatus());
		entity.setOpenid(winningrecordModel.getOpenid());
		entity.setWinnerphone(winningrecordModel.getWinnerphone());
		entity.setUpdatetime(winningrecordModel.getUpdatetime());
		entity.setPreliminaryper(winningrecordModel.getPreliminaryper());
		entity.setReviewdes(winningrecordModel.getReviewdes());
		entity.setGameid(winningrecordModel.getGameid());
		return entity;
	}
}
