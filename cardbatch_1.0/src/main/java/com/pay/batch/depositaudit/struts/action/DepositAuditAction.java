/**
 *包名:com.pay.batch.depositaudit.struts.action
 *描述:package com.pay.batch.depositaudit.struts.action;
 */
package com.pay.batch.depositaudit.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.batch.bflowlog.tool.CallSocket;
import com.pay.batch.depositaudit.bean.DepositBean;
import com.pay.batch.depositaudit.dao.DepositDao;
import com.pay.batch.depositaudit.struts.form.DepositForm;
//import com.pay.batch.deposit.bean.DepositBean;
//import com.pay.batch.deposit.dao.DepositDao;
//import com.pay.batch.deposit.struts.form.DepositForm;
import com.pay.merchant.bean.AreaBean;
import com.pay.merchant.dao.Merchantdao;
import com.pay.query.bean.CardProductBean;
import com.pay.query.dao.CommonDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.util.AxisClient;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.ExportExcel;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.RecordMethod;
import com.pay.util.StringUtils;
import com.pay.util.SystemConfig;

import cn.yufu.system.common.shiro.UserUtils;

/**
 * DepositAuditAction.java 版权所有(C) 2017 裕福控股有限公司 创建:gll 时间:2017年5月23日
 * 描述:批量充值审核模块
 */
public class DepositAuditAction extends BaseDispatchAction {
	// 日志
	private static final Logger logger = Logger.getLogger(DepositAuditAction.class);

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	public ActionForward getDepositList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 进入主页前，把查询条件设置为空
			DepositDao dao = new DepositDao();
			DepositForm depositForm = (DepositForm) form;
			String user_code = UserUtils.getLoginName();
			// 设置查询条件
			String batch_stat_query = request.getParameter("batch_stat_query");
			String pay_type_query = request.getParameter("pay_type_query");
			String sales_point_query = request.getParameter("sales_point_query");
			if (!"".equals(batch_stat_query) && batch_stat_query != null) {
				depositForm.setBatch_stat(batch_stat_query);
			}
			if (!"".equals(pay_type_query) && pay_type_query != null) {
				depositForm.setPay_type(pay_type_query);
			}
			if (!"".equals(sales_point_query) && sales_point_query != null) {
				depositForm.setSales_point(sales_point_query);
			}
			List<DepositBean> lst = null;
			String sumAmt = "0";
			String sumPanCount = "0";
			/*如果查询条件为空，则返回空*/
			/*String startdate = depositForm.getStartdate();
			String enddate = depositForm.getEnddate();*/
			String operator = depositForm.getOperator();
			String batchstat = depositForm.getBatch_stat();
			String crdproduct = depositForm.getCrdproduct();
			String startperiod = depositForm.getStart_period();
			String endperiod = depositForm.getEnd_period();
			SysParameterDao sysParameterDao=new SysParameterDao();
			//账期
			if((!StringUtils.isNotEmptyStr(startperiod)) || (!StringUtils.isNotEmptyStr(endperiod)) || (!StringUtils.isNotEmptyStr(operator)) || (!StringUtils.isNotEmptyStr(batchstat)) || (!StringUtils.isNotEmptyStr(crdproduct))){
				int cPage = 1;
				int count = 0;
				PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
				// 保存分页对象信息
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("sumAmt", sumAmt);
				request.setAttribute("sumPanCount", sumPanCount);
			}else{
				// 设置当前页码
				String dParams[] = getDisplayParams();
				int cPage = 1;
				if (request.getParameter(dParams[0]) != null) {
					cPage = Integer.parseInt(request.getParameter(dParams[0]));
				}
				int count = dao.getCount(depositForm, user_code);
				PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
				// 保存分页对象信息
				request.setAttribute("pageBean", pageBean);
				if (count > 0) {
					lst = dao.getDepositList(pageBean, depositForm, user_code);
					sumAmt = dao.getSumAmt(depositForm, user_code);
					sumPanCount = dao.getSumPanCount(depositForm);
				}
				request.setAttribute("sumAmt", sumAmt);
				request.setAttribute("sumPanCount", sumPanCount);

				if (lst != null && !lst.isEmpty()) {
					request.setAttribute("openCardList", lst);
				}
				if(lst!=null&&lst.size()>0){
					Map<String,String> consumpCategoryMap=sysParameterDao.getSysParamMap("PAY_TYPE");
					if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
						for(DepositBean oc:lst){
							if(oc.getPay_type()!=null){
								oc.setPay_type_desc(consumpCategoryMap.get(oc.getPay_type()));
							}
						}
					}
				}
			}
			
			// 枚举值:卡产品列表
			CommonDao comDao = new CommonDao();
			List<CardProductBean> cardProductList = comDao.getUserCrdproductList(user_code);
			request.setAttribute("cardProductList", cardProductList);
		} catch (Exception e) {
			logger.error("MerchantAction--getMerchantList--Exception:", e);
		}
		return mapping.findForward("showDepositAuditList");
	}

	public ActionForward prequeryDepositList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DepositForm opencardForm = (DepositForm) form;
		HttpSession session = request.getSession();
		new RecordMethod().sessionSet(session, DepositForm.class, opencardForm);
		return mapping.findForward("queryDepositList");

	}

	public ActionForward queryDepositList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DepositDao dao = new DepositDao();
		DepositForm opencardForm = (DepositForm) form;
		String user_code = UserUtils.getLoginName();
		// 构造分页对象
		int count = dao.getCount(opencardForm, user_code);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,
				ParamUtils.getIntParameter(request, "currentPage", 1));
		List<DepositBean> lst = dao.getDepositList(pageBean, opencardForm, user_code);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("depositList", lst);
		}
		request.setAttribute("queryflag", "1"); // 表示不是查询
		Merchantdao mchdao = new Merchantdao();
		// 获取所有一线城市
		List<AreaBean> provinList = mchdao.getCityByFid("0");
		// 获取二线城市
		List<AreaBean> city_noList = mchdao.getCityByFid("1");

		if (provinList != null && !provinList.isEmpty()) {
			request.setAttribute("provinList", provinList);
		}
		if (city_noList != null && !city_noList.isEmpty()) {
			request.setAttribute("city_noList", city_noList);
		}
		return mapping.findForward("showDepositAuditList");
	}

	public ActionForward showDepositInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		DepositDao dao = new DepositDao();
		DepositBean bean = dao.getDepositInfo(id);
		request.setAttribute("depositBean", bean);
		if (bean != null && bean.getPay_type() != null) {
			SysParameterDao sysParameterDao = new SysParameterDao();
			Map<String, String> consumpCategoryMap = sysParameterDao.getSysParamMap("PAY_TYPE");
			if (consumpCategoryMap != null && consumpCategoryMap.size() > 0) {
				bean.setPay_type_desc(consumpCategoryMap.get(bean.getPay_type()));
			}
		}
		return mapping.findForward("showDepositAuditInfo");
	}

	/**
	 * 通知类交易 充值通知、撤销
	 */
	public ActionForward Deposit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String id = ParamUtils.getParameter(request, "id");
		DepositDao dao = new DepositDao();
		int result = 0;
		String sktInfo = "";
		String wsInfo = "";
		String msgtype = "";
		DepositBean bean = dao.getDepositInfo(id);
		if (null == bean.getId() || "".equals(bean.getId()) || "null".equals(bean.getId())) {
			result = -2;
		} else {
			StringBuffer retmsg = new StringBuffer();
			try {
				result = fukaCheck(bean, retmsg);
			} catch (AxisFault e2) {
				e2.printStackTrace();
				result = -8;
			}
			wsInfo = retmsg.toString();
			if (result == 0) {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
				StringBuffer sendbuf = new StringBuffer();
				msgtype = request.getParameter("msgtype");
				// 组报文 0028,0051
				// 请求：消息类型|时间|流水号|开始卡号|结束卡号|张数|金额|父订单|子订单|账期|支付方式|支付方式详细信息|购卡单位名称|售卡点|地区ID|证件类型|证件号码|手机号|电话|地址|充值类型|是否到账|备注(支票号)|操作员|商户号|商户密码|MAC
				// 更新20150630：......|操作员78|交易币种|交易金额|交易汇率|清算币种|商户号42|商户密码81|MAC

				sendbuf.append(msgtype).append("|");// 消息类型
				sendbuf.append(sdf.format(new Date())).append("|");// 时间
				sendbuf.append(bean.getStan()).append("|");// 流水号
				sendbuf.append(bean.getPan_start()).append("|");// 开始卡号
				sendbuf.append(bean.getPan_end()).append("|");// 结束卡号
				sendbuf.append(bean.getPan_count()).append("|");// 张数
				sendbuf.append(bean.getAmt_each_crd()).append("|");// 金额
				sendbuf.append(bean.getFather_order()).append("|");// 父订单
				sendbuf.append(bean.getChildren_order()).append("|");// 子订单
				sendbuf.append(bean.getAcct_period()).append("|");// 账期
				sendbuf.append(bean.getPay_type()).append("|");// 支付方式
				sendbuf.append("|");// 支付方式详细信息
				sendbuf.append("|");// 购卡单位名称
				sendbuf.append("|");// 售卡点
				sendbuf.append(bean.getArea()).append("|");// 地区ID
				sendbuf.append(bean.getId_type()).append("|");// 证件类型
				sendbuf.append(bean.getId_number()).append("|");// 证件号码
				sendbuf.append(bean.getCell_phone()).append("|");// 手机号码
				sendbuf.append(bean.getPhone()).append("|");// 电话
				sendbuf.append("|");// 地址
				sendbuf.append(bean.getCashin_type()).append("|");// 充值类型
				sendbuf.append(bean.getIspaid()).append("|");// 是否到账
				sendbuf.append("|");// 备注
				sendbuf.append("|");// 操作员

				// sendbuf.append(bean.getCurtxn()).append("|");//交易币种
				// sendbuf.append(bean.getAmttxn()).append("|");//交易金额
				// sendbuf.append(bean.getRateset()).append("|");//交易汇率
				// sendbuf.append(bean.getCurrbill()).append("|");//清算币种

				sendbuf.append("111111111111115").append("|");// 固定商户
				sendbuf.append("222333").append("|");//
				sendbuf.append("").append("|");//

				System.out.println(sendbuf.toString());

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
						// 新增需求：添加四字节报文长度
						String sendbufToUTF8 = new String(sendbuf.toString().getBytes("gbk"), "utf-8");
						int bufLength = sendbufToUTF8.length();
						String bufLenStr = "";
						if (bufLength < 10) {
							bufLenStr = "000" + bufLength;
						} else if (bufLength >= 10 && bufLength < 100) {
							bufLenStr = "00" + bufLength;
						} else if (bufLength >= 100 && bufLength < 1000) {
							bufLenStr = "0" + bufLength;
						} else if (bufLength >= 1000 && bufLength < 10000) {
							bufLenStr = "" + bufLength;
						}
						sendbufToUTF8 = bufLenStr + sendbufToUTF8;

						System.out.println("Deposit sendbufToUTF8: " + sendbufToUTF8);
						result = callsocket.execMyShell(ip, port, sendbufToUTF8, recvbuf);

						// result = callsocket.execShell(ip, port,
						// new
						// String(sendbuf.toString().getBytes("gbk"),"utf-8"),
						// recvbuf);
						if (result == 0) {

							String[] rcvtmp = recvbuf.toString().split("\\|", -1);
							sktInfo = "[" + rcvtmp[3] + "]" + new String(rcvtmp[4].getBytes("gb18030"), "utf-8");
							System.out.println(sktInfo);
							// 充值通知(福卡充值通知接口)-CortexCardChargeNotice()
							// 请求：消息类型|时间|起始卡号|截止卡号|面值|张数|订单号|通知标志(0:确认充值,1:撤消充值)|操作员|MAC
							// 应答：消息类型|时间|交易码|交易返回信息|MAC
							if ("0".equals(rcvtmp[3])) {
								DateUtils du = new DateUtils();
								dao.updCashInBatchDescr(id, UserUtils.getUserName() + "," + du.getFullTime());
								StringBuilder wsbuf = new StringBuilder();
								wsbuf.append("0002|");
								wsbuf.append(sdf.format(new Date())).append("|");// 时间
								wsbuf.append(bean.getPan_start()).append("|");// 开始卡号
								wsbuf.append(bean.getPan_end()).append("|");// 结束卡号
								wsbuf.append(bean.getAmt_each_crd()).append("|");// 金额
								wsbuf.append(bean.getPan_count()).append("|");// 张数
								wsbuf.append(bean.getFather_order()).append("|");// 父订单
								if (msgtype.equals("0028")) {
									wsbuf.append("0").append("|");// 通知标志
								} else {
									wsbuf.append("1").append("|");// 通知标志
								}
								wsbuf.append(bean.getOperator()).append("|");//
								wsbuf.append("");//
								System.out.println("[WEBSERVICE SENDBUF]" + wsbuf.toString());
								String ws_ret = AxisClient.CltCall2("CortexCardChargeNotice",
										SystemConfig.getValue("sales_ws_url"), SystemConfig.getValue("sales_ws_ns"),
										wsbuf.toString());
								System.out.println("[WEBSERVICE RECVBUF]" + ws_ret);
								if (null == ws_ret || "".equals(ws_ret)) {
									result = -4;
								} else {
									String[] wsrcvtmp = ws_ret.toString().split("\\|", -1);
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
			}
		}
		String info = "";
		// String flushdo = "/bflowlog.do?method=preShowBFlowLogList";
		// String flushdo = "/deposit.do?method=getDepositList";
		String flushdo = "closewindow";

		if (UserUtils.getUserName() != null && !"".equals(UserUtils.getUserName())) {

			String info_tit = "";
			if (msgtype.equals("0028")) {
				info_tit = "充值通知";
			} else {
				info_tit = "充值撤销";
			}

			if (result >= 1) {
				info = info_tit + "验证成功,交易失败";
				request.setAttribute("result", "1");
			} else if (result == 0) {
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
			} else if (result == -8) {
				info = info_tit + "销售平台验证失败" + wsInfo;
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

	private int fukaCheck(DepositBean bean, StringBuffer retmsg) throws AxisFault {

		int result = 0;
		String wsInfo = "";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HHmmss");
		StringBuilder wsbuf = new StringBuilder();

		wsbuf.append("0001|");
		wsbuf.append(sdf.format(new Date())).append("|");// 时间
		wsbuf.append(bean.getPan_start()).append("|");// 开始卡号
		wsbuf.append(bean.getPan_end()).append("|");// 结束卡号
		wsbuf.append(bean.getAmt_each_crd()).append("|");// 金额
		wsbuf.append(bean.getPan_count()).append("|");// 张数
		wsbuf.append(bean.getFather_order()).append("|");// 父订单
		wsbuf.append(bean.getChildren_order()).append("|");// 子订单

		wsbuf.append("1").append("|");// 通知标志（0:开卡验证,1:充值验证）

		wsbuf.append(bean.getOperator()).append("|");//
		wsbuf.append("");//
		System.out.println("[WEBSERVICE SENDBUF FuKaOpenCheck]" + wsbuf.toString());
		String ws_ret = AxisClient.CltCall2("FuKaOpenCheck", SystemConfig.getValue("sales_ws_url"),
				SystemConfig.getValue("sales_ws_ns"), wsbuf.toString());
		System.out.println("[WEBSERVICE RECVBUF FuKaOpenCheck]" + ws_ret);
		if (null == ws_ret || "".equals(ws_ret)) {
			result = -8;
		} else {
			String[] wsrcvtmp = ws_ret.toString().split("\\|", -1);
			wsInfo = "[" + wsrcvtmp[2] + "]" + wsrcvtmp[3];
			retmsg.append(wsInfo);
			System.out.println(wsInfo);
			if ("0".equals(wsrcvtmp[2])) {
				result = 0;
			} else {
				result = -8;
			}

		}
		return result;
	}

	public ActionForward exportExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		DepositDao dao = new DepositDao();
		String user_code = UserUtils.getLoginName();
		DepositForm depositForm = (DepositForm) form;
		List<DepositBean> lst = dao.getDepositList(depositForm, user_code);
		for (int i = 0; i < lst.size(); i++) {
			DepositBean ocb_tmp = lst.get(i);

			if (ocb_tmp.getTxncode().equals("28")) {
				if (ocb_tmp.getBatch_stat().equals("02")) {
					ocb_tmp.setTxncode("充值冲正");
				} else {
					ocb_tmp.setTxncode("充值交易");
				}
			} else {
				ocb_tmp.setTxncode("充值调账");
			}

			if (ocb_tmp.getPay_type().equals("0"))
				ocb_tmp.setPay_type("在线支付");
			if (ocb_tmp.getPay_type().equals("1"))
				ocb_tmp.setPay_type("汇款");
			if (ocb_tmp.getPay_type().equals("2"))
				ocb_tmp.setPay_type("支票");
			if (ocb_tmp.getPay_type().equals("3"))
				ocb_tmp.setPay_type("现金");
			if (ocb_tmp.getPay_type().equals("4"))
				ocb_tmp.setPay_type("刷卡");
			if (ocb_tmp.getPay_type().equals("5"))
				ocb_tmp.setPay_type("综合（含支票）");
			if (ocb_tmp.getPay_type().equals("6"))
				ocb_tmp.setPay_type("综合（不含支票）");

			if (ocb_tmp.getBatch_stat().equals("00"))
				ocb_tmp.setBatch_stat("已处理");
			if (ocb_tmp.getBatch_stat().equals("01"))
				ocb_tmp.setBatch_stat("未处理");
			if (ocb_tmp.getBatch_stat().equals("02"))
				ocb_tmp.setBatch_stat("已冲正");
			if (ocb_tmp.getBatch_stat().equals("03"))
				ocb_tmp.setBatch_stat("已撤销");

			if (ocb_tmp.getCashin_type().equals("1"))
				ocb_tmp.setCashin_type("普通福卡充值");
			if (ocb_tmp.getCashin_type().equals("2"))
				ocb_tmp.setCashin_type("信用卡还款");
			if (ocb_tmp.getCashin_type().equals("3"))
				ocb_tmp.setCashin_type("积分计划充值");
			if (ocb_tmp.getCashin_type().equals("4"))
				ocb_tmp.setCashin_type("购卡返还积分充值");

			if (ocb_tmp.getCashin_type().equals("6"))
				ocb_tmp.setCashin_type("商旅卡首次充值");
			if (ocb_tmp.getCashin_type().equals("7"))
				ocb_tmp.setCashin_type("喜卡充值");

			if (ocb_tmp.getCashin_type().equals("9"))
				ocb_tmp.setCashin_type("中信积分充值");
			if (ocb_tmp.getCashin_type().equals("10"))
				ocb_tmp.setCashin_type("福卡积分充值");
			if (ocb_tmp.getCashin_type().equals("11"))
				ocb_tmp.setCashin_type("实名账户充值");
			if (ocb_tmp.getCashin_type().equals("12"))
				ocb_tmp.setCashin_type("购物返积分充值");

			// 删除多余字段值（因为excel映射获取javabean存在字段值）
			if (!"".equals(ocb_tmp.getDescr()) || ocb_tmp.getDescr() != null)
				ocb_tmp.setDescr("");
			lst.set(i, ocb_tmp);
		}
		String docsPath = request.getSession().getServletContext().getRealPath("docs");
		String fileName = "topup" + System.currentTimeMillis() + ".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		System.out.println("[download file path=]" + filePath);
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			ExportExcel<DepositBean> expexl = new ExportExcel<DepositBean>();

			String[] headers = { "ID", "交易类型", "充值类型", "订单时间", "流水号", "开始卡号", "结束卡号", "张数", "每张金额", "父订单号", "子订单号",
					"支付方式", "支付详细信息", "销售点", "地区", "操作员", "备注", "商户号", "是否到账", "订单状态", "账期", "审核员", "审核时间", "证件类型",
					"证件号码", "手机", "电话", "地址" };
			expexl.exportExcel("充值管理表", headers, lst, out, "yyyy-MM-dd");

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

	// 批量审核
	public ActionForward createBatchCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String msgtype = "";
		int result = 0;
		String sktInfo = "";
		String wsInfo = "";
//		String strId = "";
//		String[] tt = {};
		String id = "";
		int i = 0;
		String ip = "";
		String sport = "";

		// 提示信息
		String info = "";
		String info_string = "";
		String flushdo = "closewindow";
		String info_tit = "";
		String father_order = "";
		//得到数据
		DepositDao dao = new DepositDao();
		String user_code = UserUtils.getLoginName();
		DepositForm depositForm = (DepositForm) form;
		depositForm.setBatch_stat("01");//审批时只审批未处理的数据
		List<DepositBean> lst = dao.getDepositList(depositForm, user_code);
		// TODO 根据前业务查询数据格式
		if(null == lst || lst.size() ==0){
			info_string="没有需要审核的订单！";
		}else{
			for(i =0;i<lst.size();i++){
				DepositBean depositbean = lst.get(i);
				id = depositbean.getId();
				father_order = depositbean.getFather_order();
				DepositBean bean = dao.getDepositInfo(id);
				if (null == bean.getId() || "".equals(bean.getId())
						|| "null".equals(bean.getId())) {
					result = -2;
				} else {
					StringBuffer retmsg = new StringBuffer();
					try {
						result = fukaCheck(bean, retmsg);
					} catch (AxisFault e2) {
						e2.printStackTrace();
						result = -8;
					}
					wsInfo = retmsg.toString();
					if (result == 0) {
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
								"yyyyMMddHHmmss");
						StringBuffer sendbuf = new StringBuffer();
						msgtype = request.getParameter("msgtype");
						// 组报文 0028,0051
						// 请求：消息类型|时间|流水号|开始卡号|结束卡号|张数|金额|父订单|子订单|账期|支付方式|支付方式详细信息|购卡单位名称|售卡点|地区ID|证件类型|证件号码|手机号|电话|地址|充值类型|是否到账|备注(支票号)|操作员|商户号|商户密码|MAC
						//更新20150630：......|操作员78|交易币种|交易金额|交易汇率|清算币种|商户号42|商户密码81|MAC

						sendbuf.append(msgtype).append("|");// 消息类型
						sendbuf.append(sdf.format(new Date())).append("|");// 时间
						sendbuf.append(bean.getStan()).append("|");// 流水号
						sendbuf.append(bean.getPan_start()).append("|");// 开始卡号
						sendbuf.append(bean.getPan_end()).append("|");// 结束卡号
						sendbuf.append(bean.getPan_count()).append("|");// 张数
						sendbuf.append(bean.getAmt_each_crd()).append("|");// 金额
						sendbuf.append(bean.getFather_order()).append("|");// 父订单
						sendbuf.append(bean.getChildren_order()).append("|");// 子订单
						sendbuf.append(bean.getAcct_period()).append("|");// 账期
						sendbuf.append(bean.getPay_type()).append("|");// 支付方式
						sendbuf.append("|");// 支付方式详细信息
						sendbuf.append("|");// 购卡单位名称
						sendbuf.append("|");// 售卡点
						sendbuf.append(bean.getArea()).append("|");// 地区ID
						sendbuf.append(bean.getId_type()).append("|");// 证件类型
						sendbuf.append(bean.getId_number()).append("|");// 证件号码
						sendbuf.append(bean.getCell_phone()).append("|");// 手机号码
						sendbuf.append(bean.getPhone()).append("|");// 电话
						sendbuf.append("|");// 地址
						sendbuf.append(bean.getCashin_type()).append("|");// 充值类型
						sendbuf.append(bean.getIspaid()).append("|");// 是否到账
						sendbuf.append("|");// 备注
						sendbuf.append("|");// 操作员
						
//						sendbuf.append(bean.getCurtxn()).append("|");//交易币种
//						sendbuf.append(bean.getAmttxn()).append("|");//交易金额
//						sendbuf.append(bean.getRateset()).append("|");//交易汇率
//						sendbuf.append(bean.getCurrbill()).append("|");//清算币种						

						
						sendbuf.append("111111111111115").append("|");// 固定商户
						sendbuf.append("222333").append("|");//
						sendbuf.append("").append("|");//
						if (result == 0) {
							CallSocket callsocket = new CallSocket();
							ip = SystemConfig.getValue("socketip");
							sport = SystemConfig.getValue("socketport");
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
								System.out.println("Deposit sendbufToUTF8: "+sendbufToUTF8);
								result = callsocket.execMyShell(ip, port,sendbufToUTF8, recvbuf);
//								result = callsocket.execShell(ip, port,
//										new String(sendbuf.toString().getBytes("gbk"),"utf-8"), recvbuf);
								if (result == 0) {

									String[] rcvtmp = recvbuf.toString().split("\\|",
											-1);
									sktInfo = "["
											+ rcvtmp[3]
											+ "]"
											+ new String(rcvtmp[4].getBytes("gb18030"),
													"utf-8");
									System.out.println(sktInfo);
									// 充值通知(福卡充值通知接口)-CortexCardChargeNotice()
									// 请求：消息类型|时间|起始卡号|截止卡号|面值|张数|订单号|通知标志(0:确认充值,1:撤消充值)|操作员|MAC
									// 应答：消息类型|时间|交易码|交易返回信息|MAC
									if ("0".equals(rcvtmp[3])) {
										DateUtils du = new DateUtils();
										dao.updCashInBatchDescr(id,
												UserUtils.getUserName() + ","
														+ du.getFullTime());
										StringBuilder wsbuf = new StringBuilder();
										wsbuf.append("0002|");
										wsbuf.append(sdf.format(new Date()))
												.append("|");// 时间
										wsbuf.append(bean.getPan_start()).append("|");// 开始卡号
										wsbuf.append(bean.getPan_end()).append("|");// 结束卡号
										wsbuf.append(bean.getAmt_each_crd())
												.append("|");// 金额
										wsbuf.append(bean.getPan_count()).append("|");// 张数
										wsbuf.append(bean.getFather_order())
												.append("|");// 父订单
										if (msgtype.equals("0028")) {
											wsbuf.append("0").append("|");// 通知标志
										} else {
											wsbuf.append("1").append("|");// 通知标志
										}
										wsbuf.append(bean.getOperator()).append("|");//
										wsbuf.append("");//
										System.out.println("[WEBSERVICE SENDBUF]"
												+ wsbuf.toString());
										String ws_ret = AxisClient.CltCall2(
												"CortexCardChargeNotice",
												SystemConfig.getValue("sales_ws_url"),
												SystemConfig.getValue("sales_ws_ns"),
												wsbuf.toString());
										System.out.println("[WEBSERVICE RECVBUF]"
												+ ws_ret);
										if (null == ws_ret || "".equals(ws_ret)) {
											result = -4;
										} else {
											String[] wsrcvtmp = ws_ret.toString()
													.split("\\|", -1);
											wsInfo = "[" + wsrcvtmp[2] + "]"
													+ wsrcvtmp[3];
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
					}
				}

				if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
					if (msgtype.equals("0028")) {
						info_tit = "充值通知";
					} else {
						info_tit = "充值撤销";
					}
					
					if(result>=1){
						info = info_tit + "验证成功,交易失败";
					}else if (result == 0) {
						info = info_tit + sktInfo + wsInfo;
					} else if (result == -1) {
						info = info_tit + "发送失败";
					} else if (result == -2) {
						info = info_tit + "读取数据库记录失败";
					} else if (result == -3) {
						info = info_tit + "字符集转换失败";
					} else if (result == -4) {
						info = info_tit + "交易成功，但通知销售平台失败" + wsInfo;
					} else if (result == -8) {
						info = info_tit + "销售平台验证失败" + wsInfo;
					} else {
						info = "调用失败！";
					}
					info_string=info_string+"编号:"+id+"  订单号："+father_order+"  详情["+info+"]"+";";
				} 
			}
		}
		request.setAttribute("info", info_string.split(";"));
		request.setAttribute("flushdo", flushdo);
		// 传输查询条件
//		DepositForm depositForm = (DepositForm) form;
		depositForm.setBatch_stat("");//置空
		request.setAttribute("batch_stat_query", depositForm.getBatch_stat());
		request.setAttribute("pay_type_query", depositForm.getPay_type());
		request.setAttribute("sales_point_query", depositForm.getSales_point());

		return mapping.findForward("batchDepositAutitSubmitInfo");
	}
}
