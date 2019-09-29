/**
 * PaymentOptionController.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月25日
 */
package cn.com.jansh.controller.wechat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jansh.comm.util.DateUtil;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.annotation.SecurityRequest;
import com.jansh.core.constant.Operation;

import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.model.component.RechargeManageModel;
import cn.com.jansh.model.component.ShowRechargeModel;
import cn.com.jansh.service.component.CyInterfaceService;
import cn.com.jansh.service.system.IMUserService;
import cn.com.jansh.vo.DatatablesViewPage;
import cn.com.jansh.vo.JsonVO;

/**
 * 平台币购买
 * @author duanmuyn
 * @version 1.0
 */
@Controller
@RequestMapping("/currency")
public class CurrencyController {

	private static final Logger logger = LogManager.getLogger(CurrencyController.class);
	
	@Autowired
	public CyInterfaceService cyService;
	@Autowired
	private IMUserService userService;

	/**
	 * 跳转到初始化页面
	 * @return
	 */
	@RequestMapping("/init")
	public String payinit(){
		logger.info("支付页面初始化");
		return "fsmp/currency/recharge";
	}
	
	/**
	 * 发起银联请求并根据结果选择跳转页面
	 * @throws Exception
	 */
	@RequestMapping("/temp")
	@SecurityRequest(repeatRequest=true)
	@OperationLog(value=Operation.CREATE,key="300100")
	public String applyPay(String payno,HttpServletRequest request,HttpServletResponse resp) throws Exception{
		logger.info("开始支付");
		JsonVO result = cyService.pay(payno);
		logger.info(result);
		if(result.isSuccess()){
			/** 返回结果成功，跳转到支付页面 **/
			HttpSession session = request.getSession();
			session.setAttribute("ylpaydata", result.getData());
			return "redirect:/currency/pay";
		}else{
			/** 返回结果失败，跳转错误页面并将错误码和错误信息传递 **/
			@SuppressWarnings("unchecked")
			Map<String, String> data = (Map<String, String>) result.getData();
			request.setAttribute("errorCode",data.get("errorCode"));
			request.setAttribute("errorMsg",data.get("errorMsg"));
			return "error/apperror";
		}
	}
	
	/**
	 * 跳转到银联支付初始化页面
	 * @throws Exception
	 */
	@RequestMapping("/pay")
	public void payTemp(HttpServletRequest request,HttpServletResponse resp) throws IOException{
		HttpSession session = request.getSession();
		String result = (String) session.getAttribute("ylpaydata");
		logger.info("result : "+result);
		session.removeAttribute("ylpaydata");
		resp.setHeader("content-type","text/html;charset=UTF-8");
		resp.getWriter().write(result); 
	}
	
	/**
	 * 查询充值记录
	 * @param rechargeManageModel
	 * @return
	 */
	@RequestMapping("/record")
	@ResponseBody
	public DatatablesViewPage<ShowRechargeModel> showRechargeRecord(RechargeManageModel rechargeManageModel){
		logger.info("开始获取机构充值记录信息");
		/** 通过服务查询结果 **/
		RechargeManageModel rechargeModel = cyService.searchRecharge(rechargeManageModel);
		DatatablesViewPage<ShowRechargeModel> view = new DatatablesViewPage<ShowRechargeModel>();
		view.setiTotalDisplayRecords(rechargeModel.getCount());
		view.setiTotalRecords(rechargeModel.getCount());
		view.setAaData(rechargeModel.getShowRechargeModels());
		return view;
	}
	
	/**
	 * 初始化平台币充值记录页面
	 * @param rechargeManageModel
	 * @return
	 */
	@RequestMapping("/showrecord")
	public String showRecord(RechargeManageModel rechargeManageModel){
		logger.info("跳转到平台币充值记录页面");
		List<IMUserN> users = userService.queryUserInOrg();
		rechargeManageModel.setBeginTime(DateUtil.getFirstDayOfMonth());
		rechargeManageModel.setEndTime(DateUtil.getDate());
		rechargeManageModel.setUsers(users);
		return "fsmp/currency/record" ;
	}
	
}
