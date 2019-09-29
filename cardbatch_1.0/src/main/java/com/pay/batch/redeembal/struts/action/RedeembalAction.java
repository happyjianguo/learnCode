package com.pay.batch.redeembal.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.batch.bflowlog.tool.CallSocket;
import com.pay.batch.redeembal.bean.RedeembalBean;
import com.pay.batch.redeembal.dao.RedeembalDao;
import com.pay.batch.redeembal.struts.form.RedeembalForm;
import com.pay.merchant.bean.AreaBean;
import com.pay.merchant.dao.Merchantdao;
import com.pay.query.bean.CardProductBean;
import com.pay.query.dao.CommonDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.AxisClient;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.ExportExcel;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.RecordMethod;
import com.pay.util.StringUtils;
import com.pay.util.SystemConfig;

public class RedeembalAction extends BaseDispatchAction {
	private static final Logger logger = Logger
			.getLogger(RedeembalAction.class);

	public static final String FILE_SEPARATOR = System.getProperties()
			.getProperty("file.separator");
	
	public ActionForward getRedeembalList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
	        // 进入主页前，把查询条件设置为空
			RedeembalDao dao = new RedeembalDao();
			RedeembalForm redeembalForm = (RedeembalForm) form;
			String user_code=UserUtils.getLoginName();	   
			
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = dao.getCount(redeembalForm,user_code);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List<RedeembalBean> lst = null;
			String sumAmt="0";
			if(count>0){
				sumAmt=dao.getSumAmt(redeembalForm,user_code);
				lst = dao.getRedeembalList(pageBean, redeembalForm,user_code);
			}
			request.setAttribute("sumAmt", sumAmt);
			if (lst != null && !lst.isEmpty()) {
				request.setAttribute("openCardList", lst);
			}
			Merchantdao mchdao = new Merchantdao();
			//获取所有一线城市
			List<AreaBean> provinList = mchdao.getCityByFid("0");
			//获取二线城市
			List<AreaBean> city_noList = mchdao.getCityByFid("1");
			
			if (provinList != null && !provinList.isEmpty()) {
				request.setAttribute("provinList", provinList);
			}
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
			//枚举值:卡产品列表
			CommonDao comDao = new CommonDao();
			//CardProductBean cardProductBean = new CardProductBean();
			//List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
			List<CardProductBean> cardProductList = comDao.getUserCrdproductList(user_code);
			request.setAttribute("cardProductList", cardProductList);				
		} catch (Exception e) {
			logger.error("MerchantAction--getMerchantList--Exception:", e);
		}
		return mapping.findForward("showRedeembalList");
	}

	public ActionForward prequeryRedeembalList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RedeembalForm redeembalForm = (RedeembalForm) form;
		HttpSession session = request.getSession();
		new RecordMethod().sessionSet(session, RedeembalForm.class,
				redeembalForm);
		return mapping.findForward("queryRedeembalList");

	}

	public ActionForward queryRedeembalList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RedeembalDao dao = new RedeembalDao();
		RedeembalForm redeembalForm = (RedeembalForm) form;
		String user_code=UserUtils.getLoginName();		
		// 构造分页对象
		int count = dao.getCount(redeembalForm,user_code);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List<RedeembalBean> lst = dao.getRedeembalList(pageBean, redeembalForm,user_code);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("redeembalList", lst);
		}
		request.setAttribute("queryflag", "1"); // 表示不是查询
		Merchantdao mchdao = new Merchantdao();
		//获取所有一线城市
		List<AreaBean> provinList = mchdao.getCityByFid("0");
		//获取二线城市
		List<AreaBean> city_noList = mchdao.getCityByFid("1");
		
		if (provinList != null && !provinList.isEmpty()) {
			request.setAttribute("provinList", provinList);
		}
		if (city_noList != null && !city_noList.isEmpty()) {
			request.setAttribute("city_noList", city_noList);
		}
		return mapping.findForward("showRedeembalList");
	}

	public ActionForward showRedeembalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		RedeembalDao dao = new RedeembalDao();
		RedeembalBean bean = dao.getRedeembalInfo(id);
		request.setAttribute("redeembalBean", bean);
		return mapping.findForward("showRedeembalInfo");
	}

	public ActionForward Redeembal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		RedeembalDao dao = new RedeembalDao();
		int result = 0;
		RedeembalBean bean = dao.getRedeembalInfo(id);
		if (null == bean.getId() || "".equals(bean.getId())
				|| "null".equals(bean.getId())) {
			result = -2;
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");
		StringBuffer sendbuf = new StringBuffer();
		String msgtype = request.getParameter("msgtype");
		// 组报文 msgtype=0052|0053
		// 请求：消息类型1|时间116|流水号11|赎回卡号2|赎回金额4|父订单号71|子订单（0）72|账期（YYYYMMDD）158|售卡点76|地区ID77|证件类型83|证件号码84|
		// 手机号85|电话86|赎回类型92（0和1实名账户
		// 2卡账户，3全部赎回）|银行名称94|支行名称95|银行卡号151|银行开户姓名135|银行卡持卡人身份证号133|
		// 利息金额96|手续费155|是否到账89|备注79|操作员78|商户号42|商户密码81|MAC
		//更新20150630：......|操作员78|交易币种|交易金额|交易汇率|清算币种|商户号42|商户密码81|MAC

		sendbuf.append(msgtype).append("|");// 消息类型
		sendbuf.append(sdf.format(new Date())).append("|");// 时间
		sendbuf.append(bean.getStan()).append("|");// 流水号
		sendbuf.append(bean.getPan()).append("|");// 卡号
		sendbuf.append(bean.getAmt()).append("|");// 金额
		sendbuf.append(bean.getFather_order()).append("|");// 父订单
		sendbuf.append(bean.getChildren_order()).append("|");// 子订单
		sendbuf.append(bean.getAcct_period()).append("|");// 账期
		sendbuf.append("|");// 售卡点
		sendbuf.append(bean.getArea()).append("|");// 地区ID
		sendbuf.append(bean.getId_type()).append("|");// 证件类型
		sendbuf.append(bean.getId_number()).append("|");// 证件号码
		sendbuf.append(bean.getCell_phone()).append("|");// 手机号码
		sendbuf.append(bean.getPhone()).append("|");// 电话
		sendbuf.append(bean.getRb_type()).append("|");// 赎回类型
		sendbuf.append("|");// 银行名称
		sendbuf.append("|");// 支行名称
		sendbuf.append(bean.getBank_pan()).append("|");// 银行卡号
		sendbuf.append("|");// 银行开户姓名
		sendbuf.append(bean.getCard_acceptor_id()).append("|");// 银行卡持卡人身份证号
		sendbuf.append(bean.getInterest()).append("|");// 利息
		sendbuf.append(bean.getFee()).append("|");// 手续费
		sendbuf.append(bean.getIspaid()).append("|");// 是否到账
		sendbuf.append("|");// 备注
		sendbuf.append("|");// 操作员
		
//		sendbuf.append(bean.getCurtxn()).append("|");//交易币种
//		sendbuf.append(bean.getAmttxn()).append("|");//交易金额
//		sendbuf.append(bean.getRateset()).append("|");//交易汇率
//		sendbuf.append(bean.getCurrbill()).append("|");//清算币种						

		
		sendbuf.append("111111111111115").append("|");// 固定商户
		sendbuf.append("222333").append("|");//
		sendbuf.append("").append("|");//

		System.out.println(sendbuf.toString());

		String sktInfo = "";
		String wsInfo = "";
		if (result == 0) {
			CallSocket callsocket = new CallSocket();
			String ip = SystemConfig.getValue("socketip");
			String sport = SystemConfig.getValue("socketport");
			int port = 0;
			try {
				port = Integer.parseInt(sport);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				StringBuilder recvbuf = new StringBuilder();
				//新增需求：添加四字节报文长度
				String sendbufToUTF8=new String(sendbuf.toString().getBytes("gbk"),"utf-8");
				int bufLength=sendbufToUTF8.length();
				String bufLenStr="";
				if(bufLength<10){
					bufLenStr="000"+bufLength;							
				}else if(bufLength>=10&&bufLength<100){
					bufLenStr="00"+bufLength;
				}else if(bufLength>=100&&bufLength<1000){
					bufLenStr="0"+bufLength;
				}else if(bufLength>=1000&&bufLength<10000){
					bufLenStr=""+bufLength;
				}
				sendbufToUTF8=bufLenStr+sendbufToUTF8;
				System.out.println("Redeembal sendbufToUTF8: "+sendbufToUTF8);

				result = callsocket.execShell(ip, port,sendbufToUTF8, recvbuf);
				if (result == 0) {

					String[] rcvtmp = recvbuf.toString().split("\\|", -1);
					sktInfo = "["
							+ rcvtmp[3]
							+ "]"
							+ new String(rcvtmp[4].getBytes("gb18030"), "utf-8");
					System.out.println(sktInfo);
					// 赎回通知接口(福卡赎回通知接口)-CortexCardRecoveryNotice()
					// 消息类型|时间|订单号|卡号|标志(0撤消赎回，1确认打款)|操作员|MAC
					// 应答：消息类型|时间|交易码|交易返回信息|MAC

					if ("0".equals(rcvtmp[3])) {
						DateUtils du = new DateUtils();
						dao.updRedeembalDescr(
								id,
								UserUtils.getUserName() + ","
										+ du.getFullTime());
						StringBuilder wsbuf = new StringBuilder();
						wsbuf.append("0003|");
						wsbuf.append(sdf.format(new Date())).append("|");// 时间
						wsbuf.append(bean.getFather_order()).append("|");// 父订单
						wsbuf.append(bean.getPan()).append("|");// 卡号

						if (msgtype.equals("0053")) {
							wsbuf.append("0").append("|");// 通知标志
						} else {
							wsbuf.append("1").append("|");// 通知标志
						}
						wsbuf.append(bean.getOperator()).append("|");//
						wsbuf.append("");//
						System.out.println("[WEBSERVICE SENDBUF]"
								+ wsbuf.toString());
						String ws_ret = AxisClient.CltCall2(
								"CortexCardRecoveryNotice",
								SystemConfig.getValue("sales_ws_url"),
								SystemConfig.getValue("sales_ws_ns"),
								wsbuf.toString());
						System.out.println("[WEBSERVICE RECVBUF]" + ws_ret);
						if (null == ws_ret || "".equals(ws_ret)) {
							result = -4;
						} else {
							String[] wsrcvtmp = ws_ret.toString().split("\\|",
									-1);
							wsInfo = "[" + wsrcvtmp[2] + "]" + wsrcvtmp[3];
							System.out.println(wsInfo);
							if ("0".equals(wsrcvtmp[2])) {
								result = 0;
							} else {
								result = -4;
							}

						}
					}
				}
			} catch (AxisFault e1) {
				e1.printStackTrace();
				result = -4;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				result = -3;
			}
		}

		String info = "";
		// String flushdo = "/bflowlog.do?method=preShowBFlowLogList";
		//String flushdo = "/redeembal.do?method=getRedeembalList";
		String flushdo = "closewindow";

		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {

			String info_tit = "";
			if (msgtype.equals("0052")) {
				info_tit = "赎回通知";
			} else {
				info_tit = "赎回撤销";
			}

			if(result>=1){
				info = info_tit + "验证成功,交易失败";
				request.setAttribute("result", "1");
			}else if (result == 0) {
				info = info_tit + sktInfo + wsInfo;
				request.setAttribute("result", "0");
			} else if (result == -1) {
				info = info_tit + "发送失败";
				request.setAttribute("result", "1");
			} else if (result == -2) {
				info = info_tit + "读取数据库记录失败";
				request.setAttribute("result", "1");
			} else if (result == -3) {
				info = info_tit + "字符集转换失败";
				request.setAttribute("result", "1");
			} else if (result == -4) {
				info = info_tit + "交易成功，但通知销售平台失败" + wsInfo;
				request.setAttribute("result", "1");
			} else {
				info = "调用失败！";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "操作超时，请重新登录！");
			request.setAttribute("flushdo", flushdo);
			request.setAttribute("result", "1");
		}
		return mapping.findForward("submitinfo");

	}
	
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RedeembalDao dao = new RedeembalDao();
		String user_code=UserUtils.getLoginName();		
		RedeembalForm redeembalForm = (RedeembalForm) form;
		List<RedeembalBean> lst = dao.getRedeembalList(redeembalForm,user_code);
		for (int i = 0; i < lst.size(); i++) {
			RedeembalBean ocb_tmp = lst.get(i);

			if (ocb_tmp.getTxncode().equals("00")) {
				if (ocb_tmp.getBatch_stat().equals("00")) {
					ocb_tmp.setTxncode("赎回交易");
				} 
				if (ocb_tmp.getBatch_stat().equals("01")) {
					ocb_tmp.setTxncode("赎回通知");
				} 
				if (ocb_tmp.getBatch_stat().equals("03")) {
					ocb_tmp.setTxncode("赎回撤销");
				} 
			} 

			if (ocb_tmp.getBatch_stat().equals("00"))
				ocb_tmp.setBatch_stat("已处理");
			if (ocb_tmp.getBatch_stat().equals("01"))
				ocb_tmp.setBatch_stat("未处理");
			if (ocb_tmp.getBatch_stat().equals("03"))
				ocb_tmp.setBatch_stat("已撤销");
			
			if (ocb_tmp.getRb_type().equals("1")) ocb_tmp.setRb_type("实名赎回");
			if (ocb_tmp.getRb_type().equals("2")) ocb_tmp.setRb_type("卡余额赎回");
			if (ocb_tmp.getRb_type().equals("3")) ocb_tmp.setRb_type("合并赎回");
			if (ocb_tmp.getRb_type().equals("4")) ocb_tmp.setRb_type("利息赎回");
			if (ocb_tmp.getRb_type().equals("5")) ocb_tmp.setRb_type("购卡返积分");
			if (ocb_tmp.getRb_type().equals("6")) ocb_tmp.setRb_type("黄金赎回");
			if (ocb_tmp.getRb_type().equals("7")) ocb_tmp.setRb_type("基金赎回");
			if (ocb_tmp.getRb_type().equals("8")) ocb_tmp.setRb_type("基金利息赎回");
			if (ocb_tmp.getRb_type().equals("9")) ocb_tmp.setRb_type("购物返积分赎回");
			if (ocb_tmp.getRb_type().equals("10")) ocb_tmp.setRb_type("联名卡送积分赎回");

			
			lst.set(i, ocb_tmp);
		}
		String docsPath = request.getSession().getServletContext()
				.getRealPath("docs");
		String fileName = "redeembal"+System.currentTimeMillis()+".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		System.out.println("[download file path=]" + filePath);
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			ExportExcel<RedeembalBean> expexl = new ExportExcel<RedeembalBean>();

			String[] headers = { "ID", "交易类型","赎回类型", "订单时间", "流水号", "卡号",
					"金额", "父订单号", "子订单号", "销售点", "地区", "操作员", "备注", "商户号", "是否到账", "订单状态", 
					"账期", "审核员", "审核时间","证件类型","证件号码","手机","电话","银行名称","支行名称","银行卡号","银行开户户名","银行卡持卡人身份证号","利息","手续费","卡账户","实名账户","利息账户","购卡返积分账户","黄金账户","基金账户","基金利息账户","积分账户","联名卡送积分"};
			expexl.exportExcel("赎回管理表", headers, lst, out, "yyyy-MM-dd");

			expexl.download(filePath, response);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
