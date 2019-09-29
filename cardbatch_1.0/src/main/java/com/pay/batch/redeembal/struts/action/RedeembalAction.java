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
	        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
			RedeembalDao dao = new RedeembalDao();
			RedeembalForm redeembalForm = (RedeembalForm) form;
			String user_code=UserUtils.getLoginName();	   
			
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = dao.getCount(redeembalForm,user_code);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			// �����ҳ������Ϣ
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
		// �����ҳ����
		int count = dao.getCount(redeembalForm,user_code);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List<RedeembalBean> lst = dao.getRedeembalList(pageBean, redeembalForm,user_code);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("redeembalList", lst);
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
		// �鱨�� msgtype=0052|0053
		// ������Ϣ����1|ʱ��116|��ˮ��11|��ؿ���2|��ؽ��4|��������71|�Ӷ�����0��72|���ڣ�YYYYMMDD��158|�ۿ���76|����ID77|֤������83|֤������84|
		// �ֻ���85|�绰86|�������92��0��1ʵ���˻�
		// 2���˻���3ȫ����أ�|��������94|֧������95|���п���151|���п�������135|���п��ֿ������֤��133|
		// ��Ϣ���96|������155|�Ƿ���89|��ע79|����Ա78|�̻���42|�̻�����81|MAC
		//����20150630��......|����Ա78|���ױ���|���׽��|���׻���|�������|�̻���42|�̻�����81|MAC

		sendbuf.append(msgtype).append("|");// ��Ϣ����
		sendbuf.append(sdf.format(new Date())).append("|");// ʱ��
		sendbuf.append(bean.getStan()).append("|");// ��ˮ��
		sendbuf.append(bean.getPan()).append("|");// ����
		sendbuf.append(bean.getAmt()).append("|");// ���
		sendbuf.append(bean.getFather_order()).append("|");// ������
		sendbuf.append(bean.getChildren_order()).append("|");// �Ӷ���
		sendbuf.append(bean.getAcct_period()).append("|");// ����
		sendbuf.append("|");// �ۿ���
		sendbuf.append(bean.getArea()).append("|");// ����ID
		sendbuf.append(bean.getId_type()).append("|");// ֤������
		sendbuf.append(bean.getId_number()).append("|");// ֤������
		sendbuf.append(bean.getCell_phone()).append("|");// �ֻ�����
		sendbuf.append(bean.getPhone()).append("|");// �绰
		sendbuf.append(bean.getRb_type()).append("|");// �������
		sendbuf.append("|");// ��������
		sendbuf.append("|");// ֧������
		sendbuf.append(bean.getBank_pan()).append("|");// ���п���
		sendbuf.append("|");// ���п�������
		sendbuf.append(bean.getCard_acceptor_id()).append("|");// ���п��ֿ������֤��
		sendbuf.append(bean.getInterest()).append("|");// ��Ϣ
		sendbuf.append(bean.getFee()).append("|");// ������
		sendbuf.append(bean.getIspaid()).append("|");// �Ƿ���
		sendbuf.append("|");// ��ע
		sendbuf.append("|");// ����Ա
		
//		sendbuf.append(bean.getCurtxn()).append("|");//���ױ���
//		sendbuf.append(bean.getAmttxn()).append("|");//���׽��
//		sendbuf.append(bean.getRateset()).append("|");//���׻���
//		sendbuf.append(bean.getCurrbill()).append("|");//�������						

		
		sendbuf.append("111111111111115").append("|");// �̶��̻�
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
				System.out.println("Redeembal sendbufToUTF8: "+sendbufToUTF8);

				result = callsocket.execShell(ip, port,sendbufToUTF8, recvbuf);
				if (result == 0) {

					String[] rcvtmp = recvbuf.toString().split("\\|", -1);
					sktInfo = "["
							+ rcvtmp[3]
							+ "]"
							+ new String(rcvtmp[4].getBytes("gb18030"), "utf-8");
					System.out.println(sktInfo);
					// ���֪ͨ�ӿ�(�������֪ͨ�ӿ�)-CortexCardRecoveryNotice()
					// ��Ϣ����|ʱ��|������|����|��־(0������أ�1ȷ�ϴ��)|����Ա|MAC
					// Ӧ����Ϣ����|ʱ��|������|���׷�����Ϣ|MAC

					if ("0".equals(rcvtmp[3])) {
						DateUtils du = new DateUtils();
						dao.updRedeembalDescr(
								id,
								UserUtils.getUserName() + ","
										+ du.getFullTime());
						StringBuilder wsbuf = new StringBuilder();
						wsbuf.append("0003|");
						wsbuf.append(sdf.format(new Date())).append("|");// ʱ��
						wsbuf.append(bean.getFather_order()).append("|");// ������
						wsbuf.append(bean.getPan()).append("|");// ����

						if (msgtype.equals("0053")) {
							wsbuf.append("0").append("|");// ֪ͨ��־
						} else {
							wsbuf.append("1").append("|");// ֪ͨ��־
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
				info_tit = "���֪ͨ";
			} else {
				info_tit = "��س���";
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
					ocb_tmp.setTxncode("��ؽ���");
				} 
				if (ocb_tmp.getBatch_stat().equals("01")) {
					ocb_tmp.setTxncode("���֪ͨ");
				} 
				if (ocb_tmp.getBatch_stat().equals("03")) {
					ocb_tmp.setTxncode("��س���");
				} 
			} 

			if (ocb_tmp.getBatch_stat().equals("00"))
				ocb_tmp.setBatch_stat("�Ѵ���");
			if (ocb_tmp.getBatch_stat().equals("01"))
				ocb_tmp.setBatch_stat("δ����");
			if (ocb_tmp.getBatch_stat().equals("03"))
				ocb_tmp.setBatch_stat("�ѳ���");
			
			if (ocb_tmp.getRb_type().equals("1")) ocb_tmp.setRb_type("ʵ�����");
			if (ocb_tmp.getRb_type().equals("2")) ocb_tmp.setRb_type("��������");
			if (ocb_tmp.getRb_type().equals("3")) ocb_tmp.setRb_type("�ϲ����");
			if (ocb_tmp.getRb_type().equals("4")) ocb_tmp.setRb_type("��Ϣ���");
			if (ocb_tmp.getRb_type().equals("5")) ocb_tmp.setRb_type("����������");
			if (ocb_tmp.getRb_type().equals("6")) ocb_tmp.setRb_type("�ƽ����");
			if (ocb_tmp.getRb_type().equals("7")) ocb_tmp.setRb_type("�������");
			if (ocb_tmp.getRb_type().equals("8")) ocb_tmp.setRb_type("������Ϣ���");
			if (ocb_tmp.getRb_type().equals("9")) ocb_tmp.setRb_type("���ﷵ�������");
			if (ocb_tmp.getRb_type().equals("10")) ocb_tmp.setRb_type("�������ͻ������");

			
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

			String[] headers = { "ID", "��������","�������", "����ʱ��", "��ˮ��", "����",
					"���", "��������", "�Ӷ�����", "���۵�", "����", "����Ա", "��ע", "�̻���", "�Ƿ���", "����״̬", 
					"����", "���Ա", "���ʱ��","֤������","֤������","�ֻ�","�绰","��������","֧������","���п���","���п�������","���п��ֿ������֤��","��Ϣ","������","���˻�","ʵ���˻�","��Ϣ�˻�","�����������˻�","�ƽ��˻�","�����˻�","������Ϣ�˻�","�����˻�","�������ͻ���"};
			expexl.exportExcel("��ع����", headers, lst, out, "yyyy-MM-dd");

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
