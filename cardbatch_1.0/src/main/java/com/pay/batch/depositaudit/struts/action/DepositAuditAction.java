/**
 *����:com.pay.batch.depositaudit.struts.action
 *����:package com.pay.batch.depositaudit.struts.action;
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
 * DepositAuditAction.java ��Ȩ����(C) 2017 ԣ���ع����޹�˾ ����:gll ʱ��:2017��5��23��
 * ����:������ֵ���ģ��
 */
public class DepositAuditAction extends BaseDispatchAction {
	// ��־
	private static final Logger logger = Logger.getLogger(DepositAuditAction.class);

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	public ActionForward getDepositList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// ������ҳǰ���Ѳ�ѯ��������Ϊ��
			DepositDao dao = new DepositDao();
			DepositForm depositForm = (DepositForm) form;
			String user_code = UserUtils.getLoginName();
			// ���ò�ѯ����
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
			/*�����ѯ����Ϊ�գ��򷵻ؿ�*/
			/*String startdate = depositForm.getStartdate();
			String enddate = depositForm.getEnddate();*/
			String operator = depositForm.getOperator();
			String batchstat = depositForm.getBatch_stat();
			String crdproduct = depositForm.getCrdproduct();
			String startperiod = depositForm.getStart_period();
			String endperiod = depositForm.getEnd_period();
			SysParameterDao sysParameterDao=new SysParameterDao();
			//����
			if((!StringUtils.isNotEmptyStr(startperiod)) || (!StringUtils.isNotEmptyStr(endperiod)) || (!StringUtils.isNotEmptyStr(operator)) || (!StringUtils.isNotEmptyStr(batchstat)) || (!StringUtils.isNotEmptyStr(crdproduct))){
				int cPage = 1;
				int count = 0;
				PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
				// �����ҳ������Ϣ
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("sumAmt", sumAmt);
				request.setAttribute("sumPanCount", sumPanCount);
			}else{
				// ���õ�ǰҳ��
				String dParams[] = getDisplayParams();
				int cPage = 1;
				if (request.getParameter(dParams[0]) != null) {
					cPage = Integer.parseInt(request.getParameter(dParams[0]));
				}
				int count = dao.getCount(depositForm, user_code);
				PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
				// �����ҳ������Ϣ
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
			
			// ö��ֵ:����Ʒ�б�
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
		// �����ҳ����
		int count = dao.getCount(opencardForm, user_code);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,
				ParamUtils.getIntParameter(request, "currentPage", 1));
		List<DepositBean> lst = dao.getDepositList(pageBean, opencardForm, user_code);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("depositList", lst);
		}
		request.setAttribute("queryflag", "1"); // ��ʾ���ǲ�ѯ
		Merchantdao mchdao = new Merchantdao();
		// ��ȡ����һ�߳���
		List<AreaBean> provinList = mchdao.getCityByFid("0");
		// ��ȡ���߳���
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
	 * ֪ͨ�ཻ�� ��ֵ֪ͨ������
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
				// �鱨�� 0028,0051
				// ������Ϣ����|ʱ��|��ˮ��|��ʼ����|��������|����|���|������|�Ӷ���|����|֧����ʽ|֧����ʽ��ϸ��Ϣ|������λ����|�ۿ���|����ID|֤������|֤������|�ֻ���|�绰|��ַ|��ֵ����|�Ƿ���|��ע(֧Ʊ��)|����Ա|�̻���|�̻�����|MAC
				// ����20150630��......|����Ա78|���ױ���|���׽��|���׻���|�������|�̻���42|�̻�����81|MAC

				sendbuf.append(msgtype).append("|");// ��Ϣ����
				sendbuf.append(sdf.format(new Date())).append("|");// ʱ��
				sendbuf.append(bean.getStan()).append("|");// ��ˮ��
				sendbuf.append(bean.getPan_start()).append("|");// ��ʼ����
				sendbuf.append(bean.getPan_end()).append("|");// ��������
				sendbuf.append(bean.getPan_count()).append("|");// ����
				sendbuf.append(bean.getAmt_each_crd()).append("|");// ���
				sendbuf.append(bean.getFather_order()).append("|");// ������
				sendbuf.append(bean.getChildren_order()).append("|");// �Ӷ���
				sendbuf.append(bean.getAcct_period()).append("|");// ����
				sendbuf.append(bean.getPay_type()).append("|");// ֧����ʽ
				sendbuf.append("|");// ֧����ʽ��ϸ��Ϣ
				sendbuf.append("|");// ������λ����
				sendbuf.append("|");// �ۿ���
				sendbuf.append(bean.getArea()).append("|");// ����ID
				sendbuf.append(bean.getId_type()).append("|");// ֤������
				sendbuf.append(bean.getId_number()).append("|");// ֤������
				sendbuf.append(bean.getCell_phone()).append("|");// �ֻ�����
				sendbuf.append(bean.getPhone()).append("|");// �绰
				sendbuf.append("|");// ��ַ
				sendbuf.append(bean.getCashin_type()).append("|");// ��ֵ����
				sendbuf.append(bean.getIspaid()).append("|");// �Ƿ���
				sendbuf.append("|");// ��ע
				sendbuf.append("|");// ����Ա

				// sendbuf.append(bean.getCurtxn()).append("|");//���ױ���
				// sendbuf.append(bean.getAmttxn()).append("|");//���׽��
				// sendbuf.append(bean.getRateset()).append("|");//���׻���
				// sendbuf.append(bean.getCurrbill()).append("|");//�������

				sendbuf.append("111111111111115").append("|");// �̶��̻�
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
						// ��������������ֽڱ��ĳ���
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
							// ��ֵ֪ͨ(������ֵ֪ͨ�ӿ�)-CortexCardChargeNotice()
							// ������Ϣ����|ʱ��|��ʼ����|��ֹ����|��ֵ|����|������|֪ͨ��־(0:ȷ�ϳ�ֵ,1:������ֵ)|����Ա|MAC
							// Ӧ����Ϣ����|ʱ��|������|���׷�����Ϣ|MAC
							if ("0".equals(rcvtmp[3])) {
								DateUtils du = new DateUtils();
								dao.updCashInBatchDescr(id, UserUtils.getUserName() + "," + du.getFullTime());
								StringBuilder wsbuf = new StringBuilder();
								wsbuf.append("0002|");
								wsbuf.append(sdf.format(new Date())).append("|");// ʱ��
								wsbuf.append(bean.getPan_start()).append("|");// ��ʼ����
								wsbuf.append(bean.getPan_end()).append("|");// ��������
								wsbuf.append(bean.getAmt_each_crd()).append("|");// ���
								wsbuf.append(bean.getPan_count()).append("|");// ����
								wsbuf.append(bean.getFather_order()).append("|");// ������
								if (msgtype.equals("0028")) {
									wsbuf.append("0").append("|");// ֪ͨ��־
								} else {
									wsbuf.append("1").append("|");// ֪ͨ��־
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
				info_tit = "��ֵ֪ͨ";
			} else {
				info_tit = "��ֵ����";
			}

			if (result >= 1) {
				info = info_tit + "��֤�ɹ�,����ʧ��";
				request.setAttribute("result", "1");
			} else if (result == 0) {
				info = info_tit + sktInfo + wsInfo;
				request.setAttribute("result", "0");
			} else if (result == -1) {
				info = info_tit + "����ʧ��";
				request.setAttribute("result", "1");
			} else if (result == -2) {
				info = info_tit + "��ȡ���ݿ��¼ʧ��";
				request.setAttribute("result", "1");
			} else if (result == -3) {
				info = info_tit + "�ַ���ת��ʧ��";
				request.setAttribute("result", "1");
			} else if (result == -4) {
				info = info_tit + "���׳ɹ�����֪ͨ����ƽ̨ʧ��" + wsInfo;
				request.setAttribute("result", "1");
			} else if (result == -8) {
				info = info_tit + "����ƽ̨��֤ʧ��" + wsInfo;
				request.setAttribute("result", "1");
			} else {
				info = "����ʧ�ܣ�";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", StringUtils.outerToInner(info));
			request.setAttribute("flushdo", flushdo);
		} else {
			request.setAttribute("info", "������ʱ�������µ�¼��");
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
		wsbuf.append(sdf.format(new Date())).append("|");// ʱ��
		wsbuf.append(bean.getPan_start()).append("|");// ��ʼ����
		wsbuf.append(bean.getPan_end()).append("|");// ��������
		wsbuf.append(bean.getAmt_each_crd()).append("|");// ���
		wsbuf.append(bean.getPan_count()).append("|");// ����
		wsbuf.append(bean.getFather_order()).append("|");// ������
		wsbuf.append(bean.getChildren_order()).append("|");// �Ӷ���

		wsbuf.append("1").append("|");// ֪ͨ��־��0:������֤,1:��ֵ��֤��

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
					ocb_tmp.setTxncode("��ֵ����");
				} else {
					ocb_tmp.setTxncode("��ֵ����");
				}
			} else {
				ocb_tmp.setTxncode("��ֵ����");
			}

			if (ocb_tmp.getPay_type().equals("0"))
				ocb_tmp.setPay_type("����֧��");
			if (ocb_tmp.getPay_type().equals("1"))
				ocb_tmp.setPay_type("���");
			if (ocb_tmp.getPay_type().equals("2"))
				ocb_tmp.setPay_type("֧Ʊ");
			if (ocb_tmp.getPay_type().equals("3"))
				ocb_tmp.setPay_type("�ֽ�");
			if (ocb_tmp.getPay_type().equals("4"))
				ocb_tmp.setPay_type("ˢ��");
			if (ocb_tmp.getPay_type().equals("5"))
				ocb_tmp.setPay_type("�ۺϣ���֧Ʊ��");
			if (ocb_tmp.getPay_type().equals("6"))
				ocb_tmp.setPay_type("�ۺϣ�����֧Ʊ��");

			if (ocb_tmp.getBatch_stat().equals("00"))
				ocb_tmp.setBatch_stat("�Ѵ���");
			if (ocb_tmp.getBatch_stat().equals("01"))
				ocb_tmp.setBatch_stat("δ����");
			if (ocb_tmp.getBatch_stat().equals("02"))
				ocb_tmp.setBatch_stat("�ѳ���");
			if (ocb_tmp.getBatch_stat().equals("03"))
				ocb_tmp.setBatch_stat("�ѳ���");

			if (ocb_tmp.getCashin_type().equals("1"))
				ocb_tmp.setCashin_type("��ͨ������ֵ");
			if (ocb_tmp.getCashin_type().equals("2"))
				ocb_tmp.setCashin_type("���ÿ�����");
			if (ocb_tmp.getCashin_type().equals("3"))
				ocb_tmp.setCashin_type("���ּƻ���ֵ");
			if (ocb_tmp.getCashin_type().equals("4"))
				ocb_tmp.setCashin_type("�����������ֳ�ֵ");

			if (ocb_tmp.getCashin_type().equals("6"))
				ocb_tmp.setCashin_type("���ÿ��״γ�ֵ");
			if (ocb_tmp.getCashin_type().equals("7"))
				ocb_tmp.setCashin_type("ϲ����ֵ");

			if (ocb_tmp.getCashin_type().equals("9"))
				ocb_tmp.setCashin_type("���Ż��ֳ�ֵ");
			if (ocb_tmp.getCashin_type().equals("10"))
				ocb_tmp.setCashin_type("�������ֳ�ֵ");
			if (ocb_tmp.getCashin_type().equals("11"))
				ocb_tmp.setCashin_type("ʵ���˻���ֵ");
			if (ocb_tmp.getCashin_type().equals("12"))
				ocb_tmp.setCashin_type("���ﷵ���ֳ�ֵ");

			// ɾ�������ֶ�ֵ����Ϊexcelӳ���ȡjavabean�����ֶ�ֵ��
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

			String[] headers = { "ID", "��������", "��ֵ����", "����ʱ��", "��ˮ��", "��ʼ����", "��������", "����", "ÿ�Ž��", "��������", "�Ӷ�����",
					"֧����ʽ", "֧����ϸ��Ϣ", "���۵�", "����", "����Ա", "��ע", "�̻���", "�Ƿ���", "����״̬", "����", "���Ա", "���ʱ��", "֤������",
					"֤������", "�ֻ�", "�绰", "��ַ" };
			expexl.exportExcel("��ֵ�����", headers, lst, out, "yyyy-MM-dd");

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

	// �������
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

		// ��ʾ��Ϣ
		String info = "";
		String info_string = "";
		String flushdo = "closewindow";
		String info_tit = "";
		String father_order = "";
		//�õ�����
		DepositDao dao = new DepositDao();
		String user_code = UserUtils.getLoginName();
		DepositForm depositForm = (DepositForm) form;
		depositForm.setBatch_stat("01");//����ʱֻ����δ���������
		List<DepositBean> lst = dao.getDepositList(depositForm, user_code);
		// TODO ����ǰҵ���ѯ���ݸ�ʽ
		if(null == lst || lst.size() ==0){
			info_string="û����Ҫ��˵Ķ�����";
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
						// �鱨�� 0028,0051
						// ������Ϣ����|ʱ��|��ˮ��|��ʼ����|��������|����|���|������|�Ӷ���|����|֧����ʽ|֧����ʽ��ϸ��Ϣ|������λ����|�ۿ���|����ID|֤������|֤������|�ֻ���|�绰|��ַ|��ֵ����|�Ƿ���|��ע(֧Ʊ��)|����Ա|�̻���|�̻�����|MAC
						//����20150630��......|����Ա78|���ױ���|���׽��|���׻���|�������|�̻���42|�̻�����81|MAC

						sendbuf.append(msgtype).append("|");// ��Ϣ����
						sendbuf.append(sdf.format(new Date())).append("|");// ʱ��
						sendbuf.append(bean.getStan()).append("|");// ��ˮ��
						sendbuf.append(bean.getPan_start()).append("|");// ��ʼ����
						sendbuf.append(bean.getPan_end()).append("|");// ��������
						sendbuf.append(bean.getPan_count()).append("|");// ����
						sendbuf.append(bean.getAmt_each_crd()).append("|");// ���
						sendbuf.append(bean.getFather_order()).append("|");// ������
						sendbuf.append(bean.getChildren_order()).append("|");// �Ӷ���
						sendbuf.append(bean.getAcct_period()).append("|");// ����
						sendbuf.append(bean.getPay_type()).append("|");// ֧����ʽ
						sendbuf.append("|");// ֧����ʽ��ϸ��Ϣ
						sendbuf.append("|");// ������λ����
						sendbuf.append("|");// �ۿ���
						sendbuf.append(bean.getArea()).append("|");// ����ID
						sendbuf.append(bean.getId_type()).append("|");// ֤������
						sendbuf.append(bean.getId_number()).append("|");// ֤������
						sendbuf.append(bean.getCell_phone()).append("|");// �ֻ�����
						sendbuf.append(bean.getPhone()).append("|");// �绰
						sendbuf.append("|");// ��ַ
						sendbuf.append(bean.getCashin_type()).append("|");// ��ֵ����
						sendbuf.append(bean.getIspaid()).append("|");// �Ƿ���
						sendbuf.append("|");// ��ע
						sendbuf.append("|");// ����Ա
						
//						sendbuf.append(bean.getCurtxn()).append("|");//���ױ���
//						sendbuf.append(bean.getAmttxn()).append("|");//���׽��
//						sendbuf.append(bean.getRateset()).append("|");//���׻���
//						sendbuf.append(bean.getCurrbill()).append("|");//�������						

						
						sendbuf.append("111111111111115").append("|");// �̶��̻�
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
								//��������������ֽڱ��ĳ���
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
									// ��ֵ֪ͨ(������ֵ֪ͨ�ӿ�)-CortexCardChargeNotice()
									// ������Ϣ����|ʱ��|��ʼ����|��ֹ����|��ֵ|����|������|֪ͨ��־(0:ȷ�ϳ�ֵ,1:������ֵ)|����Ա|MAC
									// Ӧ����Ϣ����|ʱ��|������|���׷�����Ϣ|MAC
									if ("0".equals(rcvtmp[3])) {
										DateUtils du = new DateUtils();
										dao.updCashInBatchDescr(id,
												UserUtils.getUserName() + ","
														+ du.getFullTime());
										StringBuilder wsbuf = new StringBuilder();
										wsbuf.append("0002|");
										wsbuf.append(sdf.format(new Date()))
												.append("|");// ʱ��
										wsbuf.append(bean.getPan_start()).append("|");// ��ʼ����
										wsbuf.append(bean.getPan_end()).append("|");// ��������
										wsbuf.append(bean.getAmt_each_crd())
												.append("|");// ���
										wsbuf.append(bean.getPan_count()).append("|");// ����
										wsbuf.append(bean.getFather_order())
												.append("|");// ������
										if (msgtype.equals("0028")) {
											wsbuf.append("0").append("|");// ֪ͨ��־
										} else {
											wsbuf.append("1").append("|");// ֪ͨ��־
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
						info_tit = "��ֵ֪ͨ";
					} else {
						info_tit = "��ֵ����";
					}
					
					if(result>=1){
						info = info_tit + "��֤�ɹ�,����ʧ��";
					}else if (result == 0) {
						info = info_tit + sktInfo + wsInfo;
					} else if (result == -1) {
						info = info_tit + "����ʧ��";
					} else if (result == -2) {
						info = info_tit + "��ȡ���ݿ��¼ʧ��";
					} else if (result == -3) {
						info = info_tit + "�ַ���ת��ʧ��";
					} else if (result == -4) {
						info = info_tit + "���׳ɹ�����֪ͨ����ƽ̨ʧ��" + wsInfo;
					} else if (result == -8) {
						info = info_tit + "����ƽ̨��֤ʧ��" + wsInfo;
					} else {
						info = "����ʧ�ܣ�";
					}
					info_string=info_string+"���:"+id+"  �����ţ�"+father_order+"  ����["+info+"]"+";";
				} 
			}
		}
		request.setAttribute("info", info_string.split(";"));
		request.setAttribute("flushdo", flushdo);
		// �����ѯ����
//		DepositForm depositForm = (DepositForm) form;
		depositForm.setBatch_stat("");//�ÿ�
		request.setAttribute("batch_stat_query", depositForm.getBatch_stat());
		request.setAttribute("pay_type_query", depositForm.getPay_type());
		request.setAttribute("sales_point_query", depositForm.getSales_point());

		return mapping.findForward("batchDepositAutitSubmitInfo");
	}
}
