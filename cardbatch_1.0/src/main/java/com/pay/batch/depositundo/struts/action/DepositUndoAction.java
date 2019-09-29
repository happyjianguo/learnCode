/**
 *����:com.pay.batch.depositundo.struts.action
 *����:package com.pay.batch.depositundo.struts.action;
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
 * DepositUndoAction.java ��Ȩ����(C) 2017 ԣ���ع����޹�˾ ����:gll ʱ��:2017��9��4�� 
 * ����:������ֵ����ģ��
 */
public class DepositUndoAction extends BaseDispatchAction {
	// ��־
	private static final Logger logger = Logger.getLogger(DepositUndoAction.class);

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	public ActionForward getDepositUndoList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("������ֵ������ѯstart");
		try {

	        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
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
				int count = dao.getCount(depositUndoForm,user_code);
				PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
				// �����ҳ������Ϣ
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
	 * ������ֵ����ҳ����ת
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward batchDepositUndo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("������ֵ����ҳ����ת");
		DepositUndoBean bean = new DepositUndoBean();
		String reserved1 = ParamUtils.getParameter(request, "id");
		if(reserved1 != null && reserved1.length() > 0){
			bean.setId(reserved1.trim());
		}
		request.setAttribute("depositBean", bean);
		return mapping.findForward("batchDepositCancel");
	}
	
	/**
	 * System.out.println("������ֵ������̨����");
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
		
//		String sumAmt=dao.getSumAmt(depositUndoForm,user_code);//�ܽ��
//		int a = new BigDecimal(sumAmt).compareTo(new BigDecimal(tmoney));
		//�жϿ������Ƿ���ͬ
		if(cardnumi > 0 && cardnumi == count){
		//�����ܽ���Ƿ����У��
//		if(cardnumi > 0 && cardnumi == count && a == 0){
			// �鱨�� 0028,0069
			// ������Ϣ����|ʱ��|��ˮ��|��ʼ����|��������|����|���|������|�Ӷ���|����|֧����ʽ|֧����ʽ��ϸ��Ϣ|������λ����|�ۿ���|����ID|֤������|֤������|�ֻ���|�绰|��ַ|��ֵ����|�Ƿ���|��ע(֧Ʊ��)|����Ա|�̻���|�̻�����|���κ�|MAC
			//����20150630��......|����Ա78|���ױ���|���׽��|���׻���|�������|�̻���42|�̻�����81|MAC
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
			StringBuffer sendbuf = new StringBuffer();
			msgtype = ParamUtils.getParameter(request, "msgtype");
			
			sendbuf.append(msgtype).append("|");// ��Ϣ����
			sendbuf.append(sdf.format(new Date())).append("|");// ʱ��
			sendbuf.append("").append("|");// ��ˮ��
			sendbuf.append("").append("|");// ��ʼ����
			sendbuf.append("").append("|");// ��������
			sendbuf.append(cardnum).append("|");// ����
			sendbuf.append(tmoney).append("|");// ���
			sendbuf.append("").append("|");// ������
			sendbuf.append("").append("|");// �Ӷ���
			sendbuf.append("").append("|");// ����
			sendbuf.append("").append("|");// ֧����ʽ
			sendbuf.append("").append("|");// ֧����ʽ��ϸ��Ϣ
			sendbuf.append("").append("|");// ������λ����
			sendbuf.append("").append("|");// �ۿ���
			sendbuf.append("").append("|");// ����ID
			sendbuf.append("").append("|");// ֤������
			sendbuf.append("").append("|");// ֤������
			sendbuf.append("").append("|");// �ֻ�����
			sendbuf.append("").append("|");// �绰
			sendbuf.append("").append("|");// ��ַ
			sendbuf.append("").append("|");// ��ֵ����
			sendbuf.append("").append("|");// �Ƿ���
			sendbuf.append("").append("|");// ��ע
			sendbuf.append("").append("|");// ����Ա
			sendbuf.append("111111111111115").append("|");// �̶��̻�
			sendbuf.append("222333").append("|");//�̻�����
			sendbuf.append(id).append("|");// ���κ�
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
					// ��ֵ֪ͨ(������ֵ֪ͨ�ӿ�)-CortexBatchCardChargeNotice ()
//					����:��Ϣ����|ʱ��|�ܽ��|����|���κ�|֪ͨ��־(0: ȷ�ϳ�ֵ,1:������ֵ)|����Ա|MAC
//					����:��Ϣ����|ʱ��|������|���׷�����Ϣ|MAC
					if ("0".equals(rcvtmp[3])) {
						DateUtils du = new DateUtils();
						dao.updCashInBatchDescr(id,
								UserUtils.getUserName() + ","
										+ du.getFullTime());
						StringBuilder wsbuf = new StringBuilder();
						msgtype = ParamUtils.getParameter(request, "msgtype");
						
						wsbuf.append("0069|");
						wsbuf.append(sdf.format(new Date())).append("|");// ʱ��
						wsbuf.append(tmoney).append("|");// ���
						wsbuf.append(cardnum).append("|");// ����
						wsbuf.append(id).append("|");// ���κ�
						if (msgtype.equals("0069")) {
							wsbuf.append("1").append("|");// ֪ͨ��־
						} else {
							wsbuf.append("0").append("|");// ֪ͨ��־
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
					info_tit = "������ֵ����";
				} else {
//					info_tit = "������ֵ����";
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
			
			/*Boolean b = false;
			if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
				String info_tit = msgtype.equals("0069") ? "������ֵ����" : "";
				if(result>=1){
					info = info_tit + "��֤�ɹ�,����ʧ��";
				}else if (result == 0) {
					info = info_tit + sktInfo + wsInfo;
					b = true;
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
				request.setAttribute("info", StringUtils.outerToInner(info));
			} else {
				request.setAttribute("info", "������ʱ�������µ�¼��");
			}
			String flagk = b ? "0":"1";
			request.setAttribute("result", flagk);
			request.setAttribute("flushdo", flushdo);*/
		}else{
			request.setAttribute("info", "��������ƥ�����������룡");
			request.setAttribute("flushdo", "closewindow");
			request.setAttribute("result", "1");
		}
		return mapping.findForward("submitinfo");
		
	}
}
