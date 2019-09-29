/**
 * OrderManageController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月4日
 */
package cn.com.jansh.controller.wechat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.OperationResult;
import com.jansh.core.constant.Operation;

import cn.com.jansh.entity.component.CloudgameparamEntity;
import cn.com.jansh.model.DatatablesViewPage;
import cn.com.jansh.model.component.CloudorgvirgoodModel;
import cn.com.jansh.model.component.OrderManageModel;
import cn.com.jansh.model.component.ShowOrderModel;
import cn.com.jansh.service.component.CfInterfaceService;
import cn.com.jansh.service.component.CvOrderService;
import cn.com.jansh.service.market.ClgameparamService;
import cn.com.jansh.vo.AjaxObj;

/**
 * 充值订单管理controller
 * @author Mr.wong
 * @version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderManageController {

	private static final Logger logger = LogManager.getLogger(OrderManageController.class);
	
	@Autowired
	private CvOrderService orderService;
	
	@Autowired
	private ClgameparamService gameService;
	
	@Autowired
	private CfInterfaceService cfService;
	
	/**
	 * 话费流量订单初始化界面
	 * @param orderManageModel
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/init")
	@OperationLog(value=Operation.CREATE,key="330300")
	public String  init(HttpServletRequest request ,OrderManageModel orderManageModel) throws Exception{
		logger.info("进入话费流量订单管理界面");
		if(StringUtils.isNotBlank(orderManageModel.getBeginTime())){
			logger.info("开始批量充值{}", orderManageModel.toString());
			OrderManageModel orderModel = orderService.batchRecharge(orderManageModel);
			orderManageModel.setQueryCount(orderModel.getQueryCount());
			orderManageModel.setChargeCount(orderManageModel.getChargeCount());
			orderManageModel.setValidateCount(orderManageModel.getValidateCount());
			orderManageModel.setRechargeFlag(true);
		}
		orderManageModel.setBeginTime(com.jansh.comm.util.DateUtil.getFirstDayOfMonth());
		orderManageModel.setEndTime(com.jansh.comm.util.DateUtil.getDate());
		List<CloudorgvirgoodModel> vrList = cfService.queryVrGoods(null);
		List<CloudgameparamEntity> gpList = gameService.getalldata();
		orderManageModel.setGpList(gpList);
		orderManageModel.setVrList(vrList);
		
		return "fsmp/recharge/record";
	}
	
	/**
	 * 话费流量订单记录列表
	 * @param request
	 * @param orderManageModel
	 * @return
	 */
	@RequestMapping("/record")
	@ResponseBody
	public DatatablesViewPage<ShowOrderModel>  showOrderRecord(HttpServletRequest request ,OrderManageModel orderManageModel ){
		logger.info("开始获取订单记录，获取条件是: "+orderManageModel.toString());
		/** 在服务中查询数据 **/
		OrderManageModel showAllData = orderService.showAllData(orderManageModel);
		DatatablesViewPage<ShowOrderModel> datatablesViewPage = new DatatablesViewPage<>();
		datatablesViewPage.setAaData(showAllData.getShowOrderModels());
		datatablesViewPage.setiTotalRecords(showAllData.getCount());
		datatablesViewPage.setiTotalDisplayRecords(showAllData.getCount());
		return datatablesViewPage;
	}
	
	/**
	 * 下载话费流量订单excel列表
	 * @param request
	 * @param orderManageModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	@ExceptionHandle("/order/init")
	@OperationResult(value=Operation.CREATE,msgCode="330200")
	@OperationLog(value=Operation.CREATE,key="330100")
	public ResponseEntity<byte[]> exceldownload(HttpServletRequest request,OrderManageModel orderManageModel)
			throws Exception {
		logger.info("开始下载报表{}", orderManageModel.toString());
		ResponseEntity<byte[]> exportData = orderService.exportData(orderManageModel);
		return exportData;
	}
	/**
	 * 单个补充
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/charge")
	@ResponseBody
	public AjaxObj recharge(String id) throws Exception{
		logger.info("进入单个补充controller");
		AjaxObj ajaxObj = orderService.recharge(id);
		return ajaxObj;
	}
	
	
}
