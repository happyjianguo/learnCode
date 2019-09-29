/**
 *包名:cn.yufu.fs.web
 *描述:package cn.yufu.fs.web;
 */
package cn.yufu.fs.web;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Strings;

import cn.yufu.cortex.service.CortexopencrdbatchService;
import cn.yufu.fs.entity.FukaOrders;
import cn.yufu.fs.entity.OrdersConsumption;
import cn.yufu.fs.service.OrdersConsumptionService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;

/**
 * OrdersConsumptionController.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年5月15日
 * 描述:订单消费明细及汇总
 */
@Controller
@RequestMapping(value="/OrdersConsumption")
public class OrdersConsumptionController {
	
	//日志
	Log log = Log.getLog(OrdersConsumptionController.class);
	
	//订单消费金额明细及汇总service
	@Autowired
	@Qualifier("fs.OrdersConsumptionService")
	private OrdersConsumptionService OrdersConsumptionService;
	
	//根据订单号查询卡号、查询备付金余额
	@Autowired
	@Qualifier("cortex.CortexopencrdbatchService")
	private CortexopencrdbatchService CortexopencrdbatchService;
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @returnClearMerStlBook
	 */
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, OrdersConsumption queryModel) {

		log.debug("订单消费金额明细页面跳转", queryModel);
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/OrdersConsumption/OrdersConsumptionList";
	}
	
	/**
	 * 分页查询明细
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, OrdersConsumption queryModel) {
		/**
		 * 流程
		 * 1、根据订单号在主库中查出订单号对应卡号
		 * 2、根据卡号在财务结算库T_TRANSACTION_RECORDS_HIS表中查出下列字段
		 * 		卡号、商户号、商户名称、消费金额、消费日期、消费时间
		 * 		2.2在主库中MERCHANT_X中  商户号：MRCHNO；商户名：MRCHT_NAME
		 * 3、根据订单号在财务结算库FUKA_ORDERS表中查出
		 * 		订单号、购卡时间、购卡总额、备付金剩余总金额
		 * 4、根据消费金额算出消费总金额
		 */
		//得到订单号
		String ordercode = queryModel.getOrdercode().trim();
		/*****************************************************1*****************************************************/
		/**
		 * 1、根据订单号在主库中查出订单号对应卡号
		 */
		List<String> Cardlist = CortexopencrdbatchService.getCard(ordercode);
		String pageBar="";
		List<OrdersConsumption> list = new LinkedList<OrdersConsumption>();
		//合计
		String ordercodeno="";//订单号
		String ordertime="";//购卡时间
		String ordertotalmoney="0";//购卡金额
		String xiaofeimoney="0";	//消费总金额
		String cover_s="0";//备付金剩余金额
		/**
		 * 判断list是否为空，如果为空返回null，反之继续执行
		 */
		if(null == Cardlist || Cardlist.size() ==0 ){
			//返回空
		}else{
			//获取分页总数
			int count = OrdersConsumptionService.queryCnt(Cardlist);
			/**
			 * 如果count等于0则返回空
			 */
			if(count==0){
				//返回空
			}else{
				Page page = new Page(curPage, pageSize, count);
				curPage = page.getCurPage();
				page.style1();
				String url = req.getRequestURI().toString();
				Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
				pageBar = page.createPageBar(url, param);
				int startResult = (curPage - 1) * pageSize;
				int endResult = curPage * pageSize;
				/*****************************************************2*****************************************************/
				/**
				 * 2、根据卡号在财务结算库T_TRANSACTION_RECORDS_HIS表中查出下列字段
				 * 		卡号、商户号、商户名称、消费金额、消费日期、消费时间
				 */
				list = OrdersConsumptionService.queryList(Cardlist, startResult, endResult);
				//通过商户号查询商户名称
				for(int i=0;i<list.size();i++){
					String merchantnumber=list.get(i).getMerchantnumber();
					list.get(i).setMerchantname(CortexopencrdbatchService.getMerchantName(merchantnumber));//添加商户名称
					list.get(i).setOrdercode(ordercode);//添加订单号
				}
				/*****************************************************4*****************************************************/	
				/**
				 * 4、根据消费金额算出消费总金额
				 */
				xiaofeimoney=OrdersConsumptionService.queryTotalList(Cardlist).toString();	//消费总金额
			}
		}
		/*****************************************************3*****************************************************/
		/**
		 * 3、根据订单号在财务结算库FUKA_ORDERS表中查出
		 * 		订单号、购卡时间、购卡总额、备付金剩余总金额
		 */
		//为合计赋值
		FukaOrders fukaOrders  = OrdersConsumptionService.getFukaorders(ordercode);
		if(null != fukaOrders){
			ordercodeno = ordercode; //订单号
			ordertime=DateUtil.getFormatTimeString(fukaOrders.getOutcardverifytime(),"yyyy-MM-dd HH:mm:ss");//购卡时间
			//ordertime=fukaOrders.getOutcardverifytime().toString();//购卡时间
			ordertotalmoney=fukaOrders.getCardtotalprice().toString();//购卡金额
		}
		//得到备付金余额
		String cover = CortexopencrdbatchService.getCover(ordercode);
		if(!Strings.isNullOrEmpty(cover)){
			ordercodeno = ordercode; //订单号
			//cover_s=fukaOrders.getProvisions();//备付金剩余金额
			cover_s=cover;//备付金剩余金额
		}
		model.addAttribute("OrdersConsumptionList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		model.addAttribute("ordercodeno", ordercodeno);
		model.addAttribute("ordertime", ordertime);
		model.addAttribute("ordertotalmoney", ordertotalmoney);
		model.addAttribute("xiaofeimoney", xiaofeimoney);
		model.addAttribute("cover", cover_s);
		return "modules/fs/OrdersConsumption/OrdersConsumptionList";
	}
}
