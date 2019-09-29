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
	        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
			OpenCardDao dao = new OpenCardDao();
			OpenCardForm opencardForm = (OpenCardForm) form;			// ����̻���Ϣ�б����м�¼
			String user_code=UserUtils.getLoginName();	
			
			//���ò�ѯ����
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
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = dao.getCount(opencardForm,user_code);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			// �����ҳ������Ϣ
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
			//��ȡ����һ�߳���
			List<AreaBean> provinList = mchdao.getCityByFid("0");
			//��ȡ���߳���
			List<AreaBean> city_noList = mchdao.getCityByFid("1");
			
			if (provinList != null && !provinList.isEmpty()) {
				request.setAttribute("provinList", provinList);
			}
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}		
			//ö��ֵ:����Ʒ�б�
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
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = dao.getCount(opencardForm,user_code);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);

		List<OpenCardBean> lst = dao.getOpenCardList(pageBean, opencardForm,user_code);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("openCardList", lst);
		}
		request.setAttribute("queryflag", "1"); // ��ʾ���ǲ�ѯ
		Merchantdao mchdao = new Merchantdao();
		//��ȡ����һ�߳���
		List<AreaBean> provinList = mchdao.getCityByFid("0");
		//��ȡ���߳���
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
	 * ֪ͨ�ཻ�� ����֪ͨ
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
			//���ܱ�����ۿ��㲻����
			result = -11;
		}else{
			String salepoint = bean.getSales_point().substring(0,bean.getSales_point().indexOf(","));
			SysParameterDao SysParameterDao = new SysParameterDao();
			SysParameterBean sysParameterBean = SysParameterDao.getSysParameterByValue(salepoint);
			if(null == sysParameterBean){
				//���ܱ�����ۿ��㲻����
				result = -11;
			}else{
				if(!("1".equals(sysParameterBean.getIs_enablement()))){
					//��������ۿ��㱻����
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
						// �鱨�� 0014,0038   ������ϵͳ������Ϣ����
						// ������Ϣ����|ʱ��|��ˮ��|��ʼ����|��������|����|���|������|�Ӷ���|���ڣ�|֧����ʽ|֧����ʽ��ϸ��Ϣ|������λ����|�ۿ���|����ID|�Ƿ񿪿���־|��ע(֧Ʊ��)|����Ա|�̻���|�̻�����|MAC
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
						sendbuf.append(bean.getIsopen_flag().trim()).append("|");// �Ƿ񿪿���־
						sendbuf.append("|");// ��ע
						sendbuf.append("|");//����Ա
						
//						sendbuf.append(bean.getCurtxn()).append("|");//���ױ���
//						sendbuf.append(bean.getAmttxn()).append("|");//���׽��
//						sendbuf.append(bean.getRateset()).append("|");//���׻���
//						sendbuf.append(bean.getCurrbill()).append("|");//�������						
						
						sendbuf.append("111111111111115").append("|");// �̶��̻�
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
								//---���Ĵ������ݲ���������ת��������
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
									//---���Ĵ������ݲ���������ת��������
								result = execMyShell(ip, port,sendbufToUTF8, recvbuf);
								if (result == 0) {
									
									String[] rcvtmp = recvbuf.toString().split("\\|", -1);
									sktInfo = "[" + rcvtmp[3]
											+ "]"
											+ new String(
													rcvtmp[4]
															.getBytes("gb18030"), "utf-8");
									System.out.println(sktInfo);
									// ����֪ͨ(��������֪ͨ�ӿ�)-CortexOpenCardNotice()
									// ������Ϣ����|ʱ��|��ʼ����|��ֹ����|��ֵ|����|������|֪ͨ��־(0: ����,1:��������)|����Ա|MAC
									// Ӧ����Ϣ����|ʱ��|������|���׷�����Ϣ|MAC
									if ("0".equals(rcvtmp[3])) {
										DateUtils du = new DateUtils();
										dao.updOpenCrdBatchDescr(id, UserUtils.getUserName()+","+du.getFullTime());
										StringBuilder wsbuf = new StringBuilder();
										wsbuf.append("0001|");
										wsbuf.append(sdf.format(new Date())).append("|");// ʱ��
										wsbuf.append(bean.getPan_start()).append("|");// ��ʼ����
										wsbuf.append(bean.getPan_end()).append("|");// ��������
										wsbuf.append(bean.getAmt_each_crd()).append("|");// ���
										wsbuf.append(bean.getPan_count()).append("|");// ����
										wsbuf.append(bean.getFather_order()).append("|");// ������
										if (msgtype.equals("0014")) {
											wsbuf.append("0").append("|");// ֪ͨ��־
										} else {
											wsbuf.append("1").append("|");// ֪ͨ��־
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
				info_tit = "����֪ͨ";
			} else {
				info_tit = "��������";
			}
			if(result>=1){
				info = info_tit + "��֤�ɹ�,����ʧ��";
				request.setAttribute("result", "1");
			}else if (result == 0) {
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
			} else if (result == -11) {
				info = info_tit + "�ۿ��㲻���ڣ��������";
				request.setAttribute("result", "1");
			} else if (result == -12) {
				info = info_tit + "�ۿ��㱻���ƣ��������";
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
	
	private int fukaCheck(OpenCardBean bean, StringBuffer retmsg) throws AxisFault {
		int result = 0;
		String wsInfo = "";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");
		StringBuilder wsbuf = new StringBuilder();
		
		wsbuf.append("0001|");
		wsbuf.append(sdf.format(new Date())).append("|");// ʱ��
		wsbuf.append(bean.getPan_start()).append("|");// ��ʼ����
		wsbuf.append(bean.getPan_end()).append("|");// ��������
		wsbuf.append(bean.getAmt_each_crd()).append("|");// ���
		wsbuf.append(bean.getPan_count()).append("|");// ����
		wsbuf.append(bean.getFather_order()).append("|");// ������
		wsbuf.append(bean.getChildren_order()).append("|");// �Ӷ���
		
		wsbuf.append("0").append("|");// ֪ͨ��־��0:������֤,1:��ֵ��֤��
		
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
			// �����ͻ�����
			socket = new Socket(ip, port);
			// ��ϵͳ��׼�����豸����BufferedReader����
			//---���Ĵ������ݲ���������ת��������
			os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			//new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"gb2312"),true);
			// ��Socket����õ��������������PrintWriter����
			//is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			is = new BufferedReader(new InputStreamReader(socket.getInputStream(),"gb2312"));
			// ��Socket����õ�����������������Ӧ��BufferedReader����

			int sendlen = sendbuf.length();
			// String lenhexstr = Integer.toHexString(sendlen);
			// int lenhex = lenhexstr.length();
			// for (int i = 4; i > lenhex; i--) {
			// lenhexstr = "0" + lenhexstr;
			// }
			// byte lenhexb[] = ASCII_To_BCD(lenhexstr.getBytes(), 4);
			// sendbuf=new String(lenhexb)+sendbuf;
			os.print(sendbuf);
			// ˢ���������ʹServer�����յ����ַ���
			os.flush();
			char[] cbuf = new char[128];
			// ��Server����һ�ַ���
			int nbyte = is.read(cbuf);
			System.out.println(cbuf);
			// if (nbyte != 2 || !"00".equals(String.valueOf(cbuf, 0, nbyte))) {
			// return -2;
			// }

			recvbuf.append(cbuf);

			if (os != null)
				os.close(); // �ر�Socket�����
			if (is != null)
				is.close(); // �ر�Socket������
			if (socket != null)
				socket.close(); // �ر�Socket
			os = null;
			is = null;
			socket = null;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (os != null)
				os.close(); // �ر�Socket�����
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // �ر�Socket������
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // �ر�Socket
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
					ocb_tmp.setTxncode("�˵�����");
				} else {
					ocb_tmp.setTxncode("��������");
				}
			} else {
				ocb_tmp.setTxncode("�˿�����");
			}
			
			if (ocb_tmp.getPay_type().equals("0")) ocb_tmp.setPay_type("����֧��");
			if (ocb_tmp.getPay_type().equals("1")) ocb_tmp.setPay_type("���");
			if (ocb_tmp.getPay_type().equals("2")) ocb_tmp.setPay_type("֧Ʊ");
			if (ocb_tmp.getPay_type().equals("3")) ocb_tmp.setPay_type("�ֽ�");
			if (ocb_tmp.getPay_type().equals("4")) ocb_tmp.setPay_type("ˢ��");
			if (ocb_tmp.getPay_type().equals("5")) ocb_tmp.setPay_type("�ۺϣ���֧Ʊ��");
			if (ocb_tmp.getPay_type().equals("6")) ocb_tmp.setPay_type("�ۺϣ�����֧Ʊ��");
			
			if (ocb_tmp.getBatch_stat().equals("00")) ocb_tmp.setBatch_stat("�Ѵ���");
			if (ocb_tmp.getBatch_stat().equals("01")) ocb_tmp.setBatch_stat("δ����");
			if (ocb_tmp.getBatch_stat().equals("02")) ocb_tmp.setBatch_stat("���˿�");
			if (ocb_tmp.getBatch_stat().equals("03")) ocb_tmp.setBatch_stat("�ѳ���");
			//ɾ�������ֶ�ֵ����Ϊexcelӳ���ȡjavabean�����ֶ�ֵ��
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
			
			String[] headers = { "ID","��������","����ʱ��","��ˮ��","��ʼ����","��������","����","ÿ�Ž��","��������","�Ӷ�����","֧����ʽ","֧����ϸ��Ϣ","������λ����","���۵�","����","����Ա","��ע","�̻���","�Ƿ񿪿�","����״̬","����","���Ա","���ʱ��"};
			expexl.exportExcel("���������", headers, lst, out, "yyyy-MM-dd");
			
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
	//�������
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
			
			//��ʾ��Ϣ
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
				// �õ�Dao
				OpenCardDao dao = new OpenCardDao();
				OpenCardBean bean = dao.getOpenCardInfo(id);
				if (null == bean.getId() || "".equals(bean.getId())
						|| "null".equals(bean.getId())) {
					result = -2;
				} if (null == bean.getSales_point() || "".equals(bean.getSales_point())
						|| "null".equals(bean.getSales_point())) {
					//���ܱ�����ۿ��㲻����
					result = -11;
				}else{
					String salepoint = bean.getSales_point().substring(0,bean.getSales_point().indexOf(","));
					SysParameterDao SysParameterDao = new SysParameterDao();
					SysParameterBean sysParameterBean = SysParameterDao.getSysParameterByValue(salepoint);
					if(null == sysParameterBean){
						//���ܱ�����ۿ��㲻����
						result = -11;
					}else{
						if(!("1".equals(sysParameterBean.getIs_enablement()))){
							//��������ۿ��㱻����
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
								// �鱨�� 0014,0038   ������ϵͳ������Ϣ����
								// ������Ϣ����|ʱ��|��ˮ��|��ʼ����|��������|����|���|������|�Ӷ���|���ڣ�|֧����ʽ|֧����ʽ��ϸ��Ϣ|������λ����|�ۿ���|����ID|�Ƿ񿪿���־|��ע(֧Ʊ��)|����Ա|�̻���|�̻�����|MAC
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
								sendbuf.append(bean.getIsopen_flag().trim()).append("|");// �Ƿ񿪿���־
								sendbuf.append("|");// ��ע
								sendbuf.append("|");//����Ա
								
//								sendbuf.append(bean.getCurtxn()).append("|");//���ױ���
//								sendbuf.append(bean.getAmttxn()).append("|");//���׽��
//								sendbuf.append(bean.getRateset()).append("|");//���׻���
//								sendbuf.append(bean.getCurrbill()).append("|");//�������						
								
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
										//---���Ĵ������ݲ���������ת��������
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
											// ����֪ͨ(��������֪ͨ�ӿ�)-CortexOpenCardNotice()
											// ������Ϣ����|ʱ��|��ʼ����|��ֹ����|��ֵ|����|������|֪ͨ��־(0: ����,1:��������)|����Ա|MAC
											// Ӧ����Ϣ����|ʱ��|������|���׷�����Ϣ|MAC
											if ("0".equals(rcvtmp[3])) {
												DateUtils du = new DateUtils();
												dao.updOpenCrdBatchDescr(id, UserUtils.getUserName()+","+du.getFullTime());
												StringBuilder wsbuf = new StringBuilder();
												wsbuf.append("0001|");
												wsbuf.append(sdf.format(new Date())).append("|");// ʱ��
												wsbuf.append(bean.getPan_start()).append("|");// ��ʼ����
												wsbuf.append(bean.getPan_end()).append("|");// ��������
												wsbuf.append(bean.getAmt_each_crd()).append("|");// ���
												wsbuf.append(bean.getPan_count()).append("|");// ����
												wsbuf.append(bean.getFather_order()).append("|");// ������
												if (msgtype.equals("0014")) {
													wsbuf.append("0").append("|");// ֪ͨ��־
												} else {
													wsbuf.append("1").append("|");// ֪ͨ��־
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
						info_tit = "����֪ͨ";
					} else {
						info_tit = "��������";
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
					} else if (result == -11) {
						info = info_tit + "�ۿ��㲻���ڣ��������";
						request.setAttribute("result", "1");
					} else if (result == -12) {
						info = info_tit + "�ۿ��㱻���ƣ��������";
						request.setAttribute("result", "1");
					} else {
						info = "����ʧ�ܣ�";
					}
					info_string=info_string+"���:"+id+"  �����ţ�"+father_order+"  ����["+info+"]"+";";
				} 
				
				
			}//ѭ����ֹ��
				request.setAttribute("info", info_string.split(";"));
				request.setAttribute("flushdo", flushdo);
				//�����ѯ����
				OpenCardForm opencardForm = (OpenCardForm) form;
				request.setAttribute("batch_stat_query", opencardForm.getBatch_stat());
				request.setAttribute("pay_type_query", opencardForm.getPay_type());
				request.setAttribute("sales_point_query", opencardForm.getSales_point());
				
				return mapping.findForward("batchOpenCardSubmitInfo");
			
		}
}		
