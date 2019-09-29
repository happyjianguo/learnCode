/**
 *包名:com.pay.batch.depositundo.struts.action
 *描述:package com.pay.batch.depositundo.struts.action;
 */
package com.pay.batch.depositundo.struts.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.batch.bflowlog.tool.CallSocket;
import com.pay.batch.depositundo.bean.DepositUndoBean;
import com.pay.batch.depositundo.dao.DepositUndoDao;
import com.pay.batch.depositundo.struts.form.DepositUndoForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.util.AxisClient;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;
import com.pay.util.SystemConfig;

import cn.yufu.system.common.shiro.UserUtils;

/**
 * DepositUndoAction.java 版权所有(C) 2017 裕福控股有限公司 创建:gll 时间:2017年9月4日 
 * 描述:批量充值撤销模块
 */
public class DepositUndoAction extends BaseDispatchAction {
	// 日志
	private static final Logger logger = Logger.getLogger(DepositUndoAction.class);

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	public ActionForward getDepositUndoList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("批量充值撤销查询start");
		try {

	        // 进入主页前，把查询条件设置为空
			DepositUndoDao dao = new DepositUndoDao();
			DepositUndoForm depositUndoForm = (DepositUndoForm) form;
			String user_code=UserUtils.getLoginName();
			
			List<DepositUndoBean> lst = null;
			String sumAmt="0";
			String sumPanCount="0";
			
			String father_order = depositUndoForm.getReserved1();
			if(!StringUtils.isNotEmptyStr(father_order)){
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
				int count = dao.getCount(depositUndoForm,user_code);
				PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
				// 保存分页对象信息
				request.setAttribute("pageBean", pageBean);
				
				if(count>0){
					lst = dao.getDepositList(pageBean, depositUndoForm,user_code);
					sumAmt=dao.getSumAmt(depositUndoForm,user_code);
					sumPanCount=dao.getSumPanCount(depositUndoForm);
				}
				request.setAttribute("sumAmt", sumAmt);	 
				request.setAttribute("sumPanCount", sumPanCount);
				
				if (lst != null && !lst.isEmpty()) {
					request.setAttribute("openCardList", lst);
				}
			
				SysParameterDao sysParameterDao=new SysParameterDao();
				if(lst!=null&&lst.size()>0){
					Map<String,String> consumpCategoryMap=sysParameterDao.getSysParamMap("PAY_TYPE");
					if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
						for(DepositUndoBean oc:lst){
							if(oc.getPay_type()!=null){
								oc.setPay_type_desc(consumpCategoryMap.get(oc.getPay_type()));
							}
						}
					}
				}			
			}
	        
		} catch (Exception e) {
			logger.error("DepositUndoAction--getDepositUndoList--Exception:", e);
		}
		return mapping.findForward("showDepositUndoList");
	}
	
	public ActionForward showDepositUndoInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		DepositUndoDao dao = new DepositUndoDao();
		DepositUndoBean bean = dao.getDepositInfo(id);
		request.setAttribute("depositBean", bean);
		if(bean!=null&&bean.getPay_type()!=null){
			SysParameterDao sysParameterDao=new SysParameterDao();
			Map<String,String> consumpCategoryMap=sysParameterDao.getSysParamMap("PAY_TYPE");
			if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
				bean.setPay_type_desc(consumpCategoryMap.get(bean.getPay_type()));
			}
		}
		return mapping.findForward("showDepositUndoInfo");
	}
	
	/**
	 * 批量充值撤销页面跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward batchDepositUndo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("批量充值撤销页面跳转");
		DepositUndoBean bean = new DepositUndoBean();
		String reserved1 = ParamUtils.getParameter(request, "id");
		if(reserved1 != null && reserved1.length() > 0){
			bean.setId(reserved1.trim());
		}
		request.setAttribute("depositBean", bean);
		return mapping.findForward("batchDepositCancel");
	}
	
	/**
	 * System.out.println("批量充值撤销后台处理");
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward Deposit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("batchDeposit start");
		DepositUndoDao dao = new DepositUndoDao();
		int result = 0;
		String sktInfo = "";
		String wsInfo = "";
		String msgtype = "";
		
		String id = ParamUtils.getParameter(request, "id");
		String cardnum = ParamUtils.getParameter(request, "cardnum");
		String tmoney = ParamUtils.getParameter(request, "tmoney");

		String user_code = UserUtils.getLoginName();
		DepositUndoForm depositUndoForm = new DepositUndoForm();
		depositUndoForm.setReserved1(id);
		int count = dao.getCount(depositUndoForm,user_code);
		int cardnumi = 0;
		try {
			cardnumi = Integer.parseInt(cardnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String sumAmt=dao.getSumAmt(depositUndoForm,user_code);//总金额
//		int a = new BigDecimal(sumAmt).compareTo(new BigDecimal(tmoney));
		//判断卡张数是否相同
		if(cardnumi > 0 && cardnumi == count){
		//加上总金额是否相等校验
//		if(cardnumi > 0 && cardnumi == count && a == 0){
			// 组报文 0028,0069
			// 请求：消息类型|时间|流水号|开始卡号|结束卡号|张数|金额|父订单|子订单|账期|支付方式|支付方式详细信息|购卡单位名称|售卡点|地区ID|证件类型|证件号码|手机号|电话|地址|充值类型|是否到账|备注(支票号)|操作员|商户号|商户密码|批次号|MAC
			//更新20150630：......|操作员78|交易币种|交易金额|交易汇率|清算币种|商户号42|商户密码81|MAC
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer sendbuf = new StringBuffer();
			msgtype = ParamUtils.getParameter(request, "msgtype");
			
			sendbuf.append(msgtype).append("|");// 消息类型
			sendbuf.append(sdf.format(new Date())).append("|");// 时间
			sendbuf.append("").append("|");// 流水号
			sendbuf.append("").append("|");// 开始卡号
			sendbuf.append("").append("|");// 结束卡号
			sendbuf.append(cardnum).append("|");// 张数
			sendbuf.append(tmoney).append("|");// 金额
			sendbuf.append("").append("|");// 父订单
			sendbuf.append("").append("|");// 子订单
			sendbuf.append("").append("|");// 账期
			sendbuf.append("").append("|");// 支付方式
			sendbuf.append("").append("|");// 支付方式详细信息
			sendbuf.append("").append("|");// 购卡单位名称
			sendbuf.append("").append("|");// 售卡点
			sendbuf.append("").append("|");// 地区ID
			sendbuf.append("").append("|");// 证件类型
			sendbuf.append("").append("|");// 证件号码
			sendbuf.append("").append("|");// 手机号码
			sendbuf.append("").append("|");// 电话
			sendbuf.append("").append("|");// 地址
			sendbuf.append("").append("|");// 充值类型
			sendbuf.append("").append("|");// 是否到账
			sendbuf.append("").append("|");// 备注
			sendbuf.append("").append("|");// 操作员
			sendbuf.append("111111111111115").append("|");// 固定商户
			sendbuf.append("222333").append("|");//商户密码
			sendbuf.append(id).append("|");// 批次号
			sendbuf.append("").append("|");//
			
			System.out.println(sendbuf.toString());
			
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
				
				System.out.println("BatchDeposit sendbufToUTF8: "+sendbufToUTF8);
				result = callsocket.execMyShell(ip, port,sendbufToUTF8, recvbuf);
				
				if (result == 0) {

					String[] rcvtmp = recvbuf.toString().split("\\|",
							-1);
					sktInfo = "["
							+ rcvtmp[3]
							+ "]"
							+ new String(rcvtmp[4].getBytes("gb18030"),
									"utf-8");
					System.out.println(sktInfo);
					// 充值通知(福卡充值通知接口)-CortexBatchCardChargeNotice ()
//					发送:消息类型|时间|总金额|张数|批次号|通知标志(0: 确认充值,1:撤消充值)|操作员|MAC
//					返回:消息类型|时间|交易码|交易返回信息|MAC
					if ("0".equals(rcvtmp[3])) {
						DateUtils du = new DateUtils();
						dao.updCashInBatchDescr(id,
								UserUtils.getUserName() + ","
										+ du.getFullTime());
						StringBuilder wsbuf = new StringBuilder();
						msgtype = ParamUtils.getParameter(request, "msgtype");
						
						wsbuf.append("0069|");
						wsbuf.append(sdf.format(new Date())).append("|");// 时间
						wsbuf.append(tmoney).append("|");// 金额
						wsbuf.append(cardnum).append("|");// 张数
						wsbuf.append(id).append("|");// 批次号
						if (msgtype.equals("0069")) {
							wsbuf.append("1").append("|");// 通知标志
						} else {
							wsbuf.append("0").append("|");// 通知标志
						}
						wsbuf.append(UserUtils.getLoginName()).append("|");//
//						wsbuf.append(UserUtils.getUserName()).append("|");//
						wsbuf.append("");//
						System.out.println("[WEBSERVICE SENDBUF]"
								+ wsbuf.toString());
						String ws_ret = AxisClient.CltCall2(
								"CortexBatchCardChargeNotice",
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
			}catch(AxisFault e1) {
				e1.printStackTrace();
				result = -4;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = -3;
			}
			
			System.out.println("batchDeposit results:pcid"+id);
			String info = "";
			//String flushdo = "/deposit.do?method=getDepositList";
			String flushdo = "closewindow";

			if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
				String info_tit = "";
				if (msgtype.equals("0069")) {
					info_tit = "批量充值撤销";
				} else {
//					info_tit = "批量充值撤销";
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
			
			/*Boolean b = false;
			if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
				String info_tit = msgtype.equals("0069") ? "批量充值撤销" : "";
				if(result>=1){
					info = info_tit + "验证成功,交易失败";
				}else if (result == 0) {
					info = info_tit + sktInfo + wsInfo;
					b = true;
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
				request.setAttribute("info", StringUtils.outerToInner(info));
			} else {
				request.setAttribute("info", "操作超时，请重新登录！");
			}
			String flagk = b ? "0":"1";
			request.setAttribute("result", flagk);
			request.setAttribute("flushdo", flushdo);*/
		}else{
			request.setAttribute("info", "卡张数不匹配请重新输入！");
			request.setAttribute("flushdo", "closewindow");
			request.setAttribute("result", "1");
		}
		return mapping.findForward("submitinfo");
		
	}
}
