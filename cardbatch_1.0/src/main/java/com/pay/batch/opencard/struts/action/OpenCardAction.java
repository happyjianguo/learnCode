package com.pay.batch.opencard.struts.action;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
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
import com.pay.batch.opencard.bean.OpenCardBean;
import com.pay.batch.opencard.dao.OpenCardDao;
import com.pay.batch.opencard.struts.form.OpenCardForm;
import com.pay.merchant.bean.AreaBean;
import com.pay.merchant.dao.Merchantdao;
import com.pay.query.bean.CardProductBean;
import com.pay.query.dao.CommonDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.bean.SysParameterBean;
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

public class OpenCardAction extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(OpenCardAction.class);

	public static final String FILE_SEPARATOR = System.getProperties()
			.getProperty("file.separator");
	
	public ActionForward getOpenCardList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
	        // 进入主页前，把查询条件设置为空
			OpenCardDao dao = new OpenCardDao();
			OpenCardForm opencardForm = (OpenCardForm) form;			// 获得商户信息列表所有记录
			String user_code=UserUtils.getLoginName();	
			
			//设置查询条件
			String batch_stat_query = request.getParameter("batch_stat_query");
			String pay_type_query = request.getParameter("pay_type_query");
			String sales_point_query = request.getParameter("sales_point_query");
			if(!"".equals(batch_stat_query)&&batch_stat_query!=null){
				opencardForm.setBatch_stat(batch_stat_query);
			}
			if(!"".equals(pay_type_query)&&pay_type_query!=null){
				opencardForm.setPay_type(pay_type_query);
			}
			if(!"".equals(sales_point_query)&&sales_point_query!=null){
				opencardForm.setSales_point(sales_point_query);
			}
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = dao.getCount(opencardForm,user_code);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List<OpenCardBean> lst = null;
			String sumAmt="0";
			String sumPanCount="0";
			if(count>0){
//				lst = dao.getOpenCardList(pageBean, opencardForm,user_code);
				lst = dao.getOpenCardquerySysParameterList(pageBean, opencardForm,user_code);
				sumAmt=dao.getSumAmt(opencardForm,user_code);
				sumPanCount=dao.getSumPanCount(opencardForm);
			}
			request.setAttribute("sumAmt", sumAmt);	
			request.setAttribute("sumPanCount", sumPanCount);
			
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
			
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("PAY_TYPE");
			if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
				request.setAttribute("pay_typeList", consump_categoryList);
			}
			List<SysParameterBean> consump_category_salesList=sysParameterDao.getSysParameterList("SALES_POINT");
			if(consump_category_salesList!=null&&!consump_category_salesList.isEmpty()){
				request.setAttribute("sales_pointList", consump_category_salesList);
			}
			if(lst!=null&&lst.size()>0){
				Map<String,String> consumpCategoryMap=sysParameterDao.getSysParamMap("PAY_TYPE");
				if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
					for(OpenCardBean oc:lst){
						if(oc.getPay_type()!=null){
							oc.setPay_type_desc(consumpCategoryMap.get(oc.getPay_type()));
						}
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("MerchantAction--getMerchantList--Exception:", e);
		}
		return mapping.findForward("showOpenCardList");
	}

	public ActionForward prequeryOpenCardList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		OpenCardForm opencardForm = (OpenCardForm) form;		
		HttpSession session = request.getSession();
		new RecordMethod()
				.sessionSet(session, OpenCardForm.class, opencardForm);	
		return mapping.findForward("queryOpenCardList");

	}

	public ActionForward queryOpenCardList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		OpenCardDao dao = new OpenCardDao();
		HttpSession session = request.getSession();
		OpenCardForm opencardForm = (OpenCardForm) form;
		String user_code=UserUtils.getLoginName();	
        // 设置当前页码
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = dao.getCount(opencardForm,user_code);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);

		List<OpenCardBean> lst = dao.getOpenCardList(pageBean, opencardForm,user_code);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("openCardList", lst);
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
		new RecordMethod().sessionRemove(session, OpenCardBean.class);
		return mapping.findForward("showOpenCardList");
	}

	public ActionForward showOpenCardInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		OpenCardDao dao = new OpenCardDao();
		OpenCardBean bean = dao.getOpenCardInfoquerySysParameter(id);
//		OpenCardBean bean = dao.getOpenCardInfo(id);
		request.setAttribute("opencardBean", bean);
		
		if(bean!=null&&bean.getPay_type()!=null){
			SysParameterDao sysParameterDao=new SysParameterDao();
			Map<String,String> consumpCategoryMap=sysParameterDao.getSysParamMap("PAY_TYPE");
			if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
				bean.setPay_type_desc(consumpCategoryMap.get(bean.getPay_type()));
			}
		}
		return mapping.findForward("showOpenCardInfo");
	}

	/**
	 * 通知类交易 开卡通知
	 * */
	public ActionForward OpenCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		OpenCardDao dao = new OpenCardDao();
		String msgtype = "";
		int result = 0;
		String sktInfo = "";
		String wsInfo = "";
		
		OpenCardBean bean = dao.getOpenCardInfo(id);
		if (null == bean.getId() || "".equals(bean.getId())
				|| "null".equals(bean.getId())) {
			result = -2;
		} else if (null == bean.getSales_point() || "".equals(bean.getSales_point())
				|| "null".equals(bean.getSales_point())) {
			//不能被审核售卡点不存在
			result = -11;
		}else{
			String salepoint = bean.getSales_point().substring(0,bean.getSales_point().indexOf(","));
			SysParameterDao SysParameterDao = new SysParameterDao();
			SysParameterBean sysParameterBean = SysParameterDao.getSysParameterByValue(salepoint);
			if(null == sysParameterBean){
				//不能被审核售卡点不存在
				result = -11;
			}else{
				if(!("1".equals(sysParameterBean.getIs_enablement()))){
					//不能审核售卡点被限制
					result = -12;
				}else{
					//ok
					StringBuffer retmsg = new StringBuffer();
					try {
						result = fukaCheck(bean, retmsg);
					} catch (AxisFault e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						result = -8;
					}
					wsInfo = retmsg.toString();
					if (result == 0) {
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
								"yyyyMMddHHmmss");
						StringBuilder sendbuf = new StringBuilder();
						msgtype = request.getParameter("msgtype");
						// 组报文 0014,0038   与销售系统进行信息交流
						// 请求：消息类型|时间|流水号|开始卡号|结束卡号|张数|金额|父订单|子订单|账期？|支付方式|支付方式详细信息|购卡单位名称|售卡点|地区ID|是否开卡标志|备注(支票号)|操作员|商户号|商户密码|MAC
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
						sendbuf.append(bean.getIsopen_flag().trim()).append("|");// 是否开卡标志
						sendbuf.append("|");// 备注
						sendbuf.append("|");//操作员
						
//						sendbuf.append(bean.getCurtxn()).append("|");//交易币种
//						sendbuf.append(bean.getAmttxn()).append("|");//交易金额
//						sendbuf.append(bean.getRateset()).append("|");//交易汇率
//						sendbuf.append(bean.getCurrbill()).append("|");//清算币种						
						
						sendbuf.append("111111111111115").append("|");// 固定商户
						sendbuf.append("222333").append("|");//
						sendbuf.append("").append("|");//		
						System.out.println(sendbuf.toString()+" ");		
						
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
								//---中文传递数据不进行中文转码问题解决
								String sendbufToUTF8=new String(sendbuf.toString().getBytes("utf-8"));
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
								System.out.println("opencard sendbufToUTF8: "+sendbufToUTF8);

//								result = callsocket.execShell(ip, port,new String(sendbuf.toString().getBytes("gbk"),"utf-8"),
//										recvbuf);
								//	result = callsocket.execShell(ip, port,sendbufToUTF8, recvbuf);
									//---中文传递数据不进行中文转码问题解决
								result = execMyShell(ip, port,sendbufToUTF8, recvbuf);
								if (result == 0) {
									
									String[] rcvtmp = recvbuf.toString().split("\\|", -1);
									sktInfo = "[" + rcvtmp[3]
											+ "]"
											+ new String(
													rcvtmp[4]
															.getBytes("gb18030"), "utf-8");
									System.out.println(sktInfo);
									// 开卡通知(福卡开卡通知接口)-CortexOpenCardNotice()
									// 请求：消息类型|时间|起始卡号|截止卡号|面值|张数|订单号|通知标志(0: 开卡,1:撤消开卡)|操作员|MAC
									// 应答：消息类型|时间|交易码|交易返回信息|MAC
									if ("0".equals(rcvtmp[3])) {
										DateUtils du = new DateUtils();
										dao.updOpenCrdBatchDescr(id, UserUtils.getUserName()+","+du.getFullTime());
										StringBuilder wsbuf = new StringBuilder();
										wsbuf.append("0001|");
										wsbuf.append(sdf.format(new Date())).append("|");// 时间
										wsbuf.append(bean.getPan_start()).append("|");// 开始卡号
										wsbuf.append(bean.getPan_end()).append("|");// 结束卡号
										wsbuf.append(bean.getAmt_each_crd()).append("|");// 金额
										wsbuf.append(bean.getPan_count()).append("|");// 张数
										wsbuf.append(bean.getFather_order()).append("|");// 父订单
										if (msgtype.equals("0014")) {
											wsbuf.append("0").append("|");// 通知标志
										} else {
											wsbuf.append("1").append("|");// 通知标志
										}
										wsbuf.append(bean.getOperator()).append("|");//
										wsbuf.append("");//
										System.out.println("[WEBSERVICE SENDBUF]"
												+ wsbuf.toString());
										String ws_ret = AxisClient.CltCall2(
												"CortexOpenCardNotice",
												SystemConfig.getValue("sales_ws_url"),
												SystemConfig.getValue("sales_ws_ns"),
												wsbuf.toString());
										System.out.println("[WEBSERVICE RECVBUF]" + ws_ret);
										if (null == ws_ret || "".equals(ws_ret)) {
											result = -4;
										} else {
											String[] wsrcvtmp = ws_ret.toString().split("\\|", -1);
											wsInfo = "[" + wsrcvtmp[2]
													+ "]"
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
								// TODO Auto-generated catch block
								e.printStackTrace();
								result = -3;
							}
						}
					}
				}
			}
		}
		String info = "";
		// String flushdo = "/bflowlog.do?method=preShowBFlowLogList";
		//String flushdo = "/opencard.do?method=getOpenCardList";
		String flushdo = "closewindow";

		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			String info_tit = "";
			if (msgtype.equals("0014")) {
				info_tit = "开卡通知";
			} else {
				info_tit = "开卡撤销";
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
			} else if (result == -8) {
				info = info_tit + "销售平台验证失败" + wsInfo;
				request.setAttribute("result", "1");
			} else if (result == -11) {
				info = info_tit + "售卡点不存在，不能审核";
				request.setAttribute("result", "1");
			} else if (result == -12) {
				info = info_tit + "售卡点被限制，不能审核";
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
	
	private int fukaCheck(OpenCardBean bean, StringBuffer retmsg) throws AxisFault {
		int result = 0;
		String wsInfo = "";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");
		StringBuilder wsbuf = new StringBuilder();
		
		wsbuf.append("0001|");
		wsbuf.append(sdf.format(new Date())).append("|");// 时间
		wsbuf.append(bean.getPan_start()).append("|");// 开始卡号
		wsbuf.append(bean.getPan_end()).append("|");// 结束卡号
		wsbuf.append(bean.getAmt_each_crd()).append("|");// 金额
		wsbuf.append(bean.getPan_count()).append("|");// 张数
		wsbuf.append(bean.getFather_order()).append("|");// 父订单
		wsbuf.append(bean.getChildren_order()).append("|");// 子订单
		
		wsbuf.append("0").append("|");// 通知标志（0:开卡验证,1:充值验证）
		
		wsbuf.append(bean.getOperator()).append("|");//
		wsbuf.append("");//
		System.out.println("[WEBSERVICE SENDBUF FuKaOpenCheck]"
				+ wsbuf.toString());
		String ws_ret = AxisClient.CltCall2(
				"FuKaOpenCheck",
				SystemConfig.getValue("sales_ws_url"),
				SystemConfig.getValue("sales_ws_ns"),
				wsbuf.toString());
		System.out.println("[WEBSERVICE RECVBUF FuKaOpenCheck]" + ws_ret);
		if (null == ws_ret || "".equals(ws_ret)) {
			result = -8;
		} else {
			String[] wsrcvtmp = ws_ret.toString().split("\\|", -1);
			wsInfo = "[" + wsrcvtmp[2]
					+ "]"
					+ wsrcvtmp[3];
			retmsg.append(wsInfo);
			if ("0".equals(wsrcvtmp[2])) {
				result = 0;
			} else {
				result = -8;
			}
				
		}
		return result;
	}
	
	public int execMyShell(String ip, int port, String sendbuf,
			StringBuilder recvbuf) {
		PrintWriter os = null;
		BufferedReader is = null;
		Socket socket = null;
		try {
			// 发出客户请求
			socket = new Socket(ip, port);
			// 由系统标准输入设备构造BufferedReader对象
			//---中文传递数据不进行中文转码问题解决
			os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			//new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"gb2312"),true);
			// 由Socket对象得到输出流，并构造PrintWriter对象
			//is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			is = new BufferedReader(new InputStreamReader(socket.getInputStream(),"gb2312"));
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象

			int sendlen = sendbuf.length();
			// String lenhexstr = Integer.toHexString(sendlen);
			// int lenhex = lenhexstr.length();
			// for (int i = 4; i > lenhex; i--) {
			// lenhexstr = "0" + lenhexstr;
			// }
			// byte lenhexb[] = ASCII_To_BCD(lenhexstr.getBytes(), 4);
			// sendbuf=new String(lenhexb)+sendbuf;
			os.print(sendbuf);
			// 刷新输出流，使Server马上收到该字符串
			os.flush();
			char[] cbuf = new char[128];
			// 从Server读入一字符串
			int nbyte = is.read(cbuf);
			System.out.println(cbuf);
			// if (nbyte != 2 || !"00".equals(String.valueOf(cbuf, 0, nbyte))) {
			// return -2;
			// }

			recvbuf.append(cbuf);

			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				is.close(); // 关闭Socket输入流
			if (socket != null)
				socket.close(); // 关闭Socket
			os = null;
			is = null;
			socket = null;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (os != null)
				os.close(); // 关闭Socket输出流
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket输入流
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 关闭Socket
			os = null;
			is = null;
			socket = null;

		}
		return 0;

	}
	
	public ActionForward exportExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		OpenCardDao dao = new OpenCardDao();
		OpenCardForm opencardForm = (OpenCardForm) form;
		String user_code=UserUtils.getLoginName();		
		List<OpenCardBean> lst = dao.getOpenCardList(opencardForm,user_code);
		for (int i=0; i < lst.size(); i++) {
			OpenCardBean ocb_tmp = lst.get(i);
			
			if (ocb_tmp.getTxncode().equals("28")) {
				if (ocb_tmp.getBatch_stat().equals("02")) {
					ocb_tmp.setTxncode("退单交易");
				} else {
					ocb_tmp.setTxncode("开卡交易");
				}
			} else {
				ocb_tmp.setTxncode("退卡交易");
			}
			
			if (ocb_tmp.getPay_type().equals("0")) ocb_tmp.setPay_type("在线支付");
			if (ocb_tmp.getPay_type().equals("1")) ocb_tmp.setPay_type("汇款");
			if (ocb_tmp.getPay_type().equals("2")) ocb_tmp.setPay_type("支票");
			if (ocb_tmp.getPay_type().equals("3")) ocb_tmp.setPay_type("现金");
			if (ocb_tmp.getPay_type().equals("4")) ocb_tmp.setPay_type("刷卡");
			if (ocb_tmp.getPay_type().equals("5")) ocb_tmp.setPay_type("综合（含支票）");
			if (ocb_tmp.getPay_type().equals("6")) ocb_tmp.setPay_type("综合（不含支票）");
			
			if (ocb_tmp.getBatch_stat().equals("00")) ocb_tmp.setBatch_stat("已处理");
			if (ocb_tmp.getBatch_stat().equals("01")) ocb_tmp.setBatch_stat("未处理");
			if (ocb_tmp.getBatch_stat().equals("02")) ocb_tmp.setBatch_stat("已退卡");
			if (ocb_tmp.getBatch_stat().equals("03")) ocb_tmp.setBatch_stat("已撤销");
			//删除多余字段值（因为excel映射获取javabean存在字段值）
			if (!"".equals(ocb_tmp.getDescr())||ocb_tmp.getDescr()!=null) ocb_tmp.setDescr("");
			lst.set(i, ocb_tmp);
		}
		String docsPath = request.getSession().getServletContext()
				.getRealPath("docs");
		String fileName = "opencardbatch"+System.currentTimeMillis()+".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		System.out.println("[download file path=]"+filePath);
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			ExportExcel<OpenCardBean> expexl = new ExportExcel<OpenCardBean>();
			
			String[] headers = { "ID","交易类型","订单时间","流水号","开始卡号","结束卡号","张数","每张金额","父订单号","子订单号","支付方式","支付详细信息","购卡单位名称","销售点","地区","操作员","备注","商户号","是否开卡","订单状态","账期","审核员","审核时间"};
			expexl.exportExcel("开卡管理表", headers, lst, out, "yyyy-MM-dd");
			
			expexl.download(filePath, response);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		return null;
	}
	//批量审核
	public ActionForward createBatchCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
			String msgtype = "";
			int result = 0;
			String sktInfo = "";
			String wsInfo = "";
			String strId = "";
			String[] tt ={};
			String id="";
			int i = 0;
			String ip ="";
			String sport="";
			
			//提示信息
			String info = "";
			String info_string = "";
			String flushdo = "closewindow";
			String info_tit = "";
			String father_order= "";
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = keyStr.split("\\|");
			for (i = 0; i < InfoIdStrs.length; i++) {
				strId = InfoIdStrs[i];
				tt = strId.split("#");
				id=tt[0].trim();
				father_order=tt[1].trim();							
				// 得到Dao
				OpenCardDao dao = new OpenCardDao();
				OpenCardBean bean = dao.getOpenCardInfo(id);
				if (null == bean.getId() || "".equals(bean.getId())
						|| "null".equals(bean.getId())) {
					result = -2;
				} if (null == bean.getSales_point() || "".equals(bean.getSales_point())
						|| "null".equals(bean.getSales_point())) {
					//不能被审核售卡点不存在
					result = -11;
				}else{
					String salepoint = bean.getSales_point().substring(0,bean.getSales_point().indexOf(","));
					SysParameterDao SysParameterDao = new SysParameterDao();
					SysParameterBean sysParameterBean = SysParameterDao.getSysParameterByValue(salepoint);
					if(null == sysParameterBean){
						//不能被审核售卡点不存在
						result = -11;
					}else{
						if(!("1".equals(sysParameterBean.getIs_enablement()))){
							//不能审核售卡点被限制
							result = -12;
						}else{
							//ok

							StringBuffer retmsg = new StringBuffer();
							try {
								result = fukaCheck(bean, retmsg);
							} catch (AxisFault e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
								result = -8;
							}
							wsInfo = retmsg.toString();
							if (result == 0) {
								java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
										"yyyyMMddHHmmss");
								StringBuilder sendbuf = new StringBuilder();
								msgtype = request.getParameter("msgtype");
								// 组报文 0014,0038   与销售系统进行信息交流
								// 请求：消息类型|时间|流水号|开始卡号|结束卡号|张数|金额|父订单|子订单|账期？|支付方式|支付方式详细信息|购卡单位名称|售卡点|地区ID|是否开卡标志|备注(支票号)|操作员|商户号|商户密码|MAC
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
								sendbuf.append(bean.getIsopen_flag().trim()).append("|");// 是否开卡标志
								sendbuf.append("|");// 备注
								sendbuf.append("|");//操作员
								
//								sendbuf.append(bean.getCurtxn()).append("|");//交易币种
//								sendbuf.append(bean.getAmttxn()).append("|");//交易金额
//								sendbuf.append(bean.getRateset()).append("|");//交易汇率
//								sendbuf.append(bean.getCurrbill()).append("|");//清算币种						
								
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
										//---中文传递数据不进行中文转码问题解决
										String sendbufToUTF8=new String(sendbuf.toString().getBytes("utf-8"));
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
										System.out.println("opencard sendbufToUTF8: "+sendbufToUTF8);

//										result = callsocket.execShell(ip, port,new String(sendbuf.toString().getBytes("gbk"),"utf-8"),
//												recvbuf);
										result = execMyShell(ip, port,sendbufToUTF8, recvbuf);
										if (result == 0) {
											
											String[] rcvtmp = recvbuf.toString().split("\\|", -1);
											sktInfo = "[" + rcvtmp[3]
													+ "]"
													+ new String(
															rcvtmp[4]
																	.getBytes("gb18030"), "utf-8");
											System.out.println(sktInfo);
											// 开卡通知(福卡开卡通知接口)-CortexOpenCardNotice()
											// 请求：消息类型|时间|起始卡号|截止卡号|面值|张数|订单号|通知标志(0: 开卡,1:撤消开卡)|操作员|MAC
											// 应答：消息类型|时间|交易码|交易返回信息|MAC
											if ("0".equals(rcvtmp[3])) {
												DateUtils du = new DateUtils();
												dao.updOpenCrdBatchDescr(id, UserUtils.getUserName()+","+du.getFullTime());
												StringBuilder wsbuf = new StringBuilder();
												wsbuf.append("0001|");
												wsbuf.append(sdf.format(new Date())).append("|");// 时间
												wsbuf.append(bean.getPan_start()).append("|");// 开始卡号
												wsbuf.append(bean.getPan_end()).append("|");// 结束卡号
												wsbuf.append(bean.getAmt_each_crd()).append("|");// 金额
												wsbuf.append(bean.getPan_count()).append("|");// 张数
												wsbuf.append(bean.getFather_order()).append("|");// 父订单
												if (msgtype.equals("0014")) {
													wsbuf.append("0").append("|");// 通知标志
												} else {
													wsbuf.append("1").append("|");// 通知标志
												}
												wsbuf.append(bean.getOperator()).append("|");//
												wsbuf.append("");//
												System.out.println("[WEBSERVICE SENDBUF]"
														+ wsbuf.toString());
												String ws_ret = AxisClient.CltCall2(
														"CortexOpenCardNotice",
														SystemConfig.getValue("sales_ws_url"),
														SystemConfig.getValue("sales_ws_ns"),
														wsbuf.toString());
												System.out.println("[WEBSERVICE RECVBUF]" + ws_ret);
												if (null == ws_ret || "".equals(ws_ret)) {
													result = -4;
												} else {
													String[] wsrcvtmp = ws_ret.toString().split("\\|", -1);
													wsInfo = "[" + wsrcvtmp[2]
															+ "]"
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
										// TODO Auto-generated catch block
										e.printStackTrace();
										result = -3;
									}
								}
							}
						}
					}
				}
				if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
					if (msgtype.equals("0014")) {
						info_tit = "开卡通知";
					} else {
						info_tit = "开卡撤销";
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
					} else if (result == -11) {
						info = info_tit + "售卡点不存在，不能审核";
						request.setAttribute("result", "1");
					} else if (result == -12) {
						info = info_tit + "售卡点被限制，不能审核";
						request.setAttribute("result", "1");
					} else {
						info = "调用失败！";
					}
					info_string=info_string+"编号:"+id+"  订单号："+father_order+"  详情["+info+"]"+";";
				} 
				
				
			}//循环截止处
				request.setAttribute("info", info_string.split(";"));
				request.setAttribute("flushdo", flushdo);
				//传输查询条件
				OpenCardForm opencardForm = (OpenCardForm) form;
				request.setAttribute("batch_stat_query", opencardForm.getBatch_stat());
				request.setAttribute("pay_type_query", opencardForm.getPay_type());
				request.setAttribute("sales_point_query", opencardForm.getSales_point());
				
				return mapping.findForward("batchOpenCardSubmitInfo");
			
		}
}		
