package cn.yufu.posp.terminalmanager.web.action;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.POIUtils;
import cn.yufu.core.common.util.StringUtil;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcAlipayWeChatLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.EdcNewfkterminalOrmLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcAlipayWeChat;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;
import cn.yufu.posp.terminalmanager.web.form.EdcAlipayWeChatForm;
import cn.yufu.posp.terminalmanager.web.form.EdcAlipayWeChatUploadForm;
import cn.yufu.posp.terminalmanager.web.form.EdcNewfkterminalOrmForm;
import net.sf.json.JSONObject;

public class EdcAlipayWeChatDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTerminal");

	public EdcAlipayWeChatDispatchAction() {
	}
	/**
	 * ֧������΢���ն˹������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public ActionForward page(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";
		try {
			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath;

			log.info("MerchantAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}
	/*** ��ѯ�ն������趨 **/
	public org.apache.struts.action.ActionForward queryEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChat()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic(����service��)
			EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {  
				cPage = Integer.parseInt(request.getParameter(dParams[0])); //dpPageName
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]); //dpPageOrder
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]); //dpPageSort
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("id.merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("id.terminalId");
				if (sortField.equals("3"))
					pageInfo.setOrderField("bankMerchantId");
				if (sortField.equals("4"))
					pageInfo.setOrderField("bankTerminalId");
				if (sortField.equals("5"))
					pageInfo.setOrderField("batchNo");
				if (sortField.equals("6"))
					pageInfo.setOrderField("sysTrace");
			}

			// ���ò�ѯ����
			EdcAlipayWeChat queryModel = new EdcAlipayWeChat();
			EdcAlipayWeChatForm queryForm = (EdcAlipayWeChatForm) form;
			// ���̻���Ų�ѯ
			String merchantId = queryForm.getQueryMerchantId();
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
			String terminalId = queryForm.getQueryTerminalId();
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			//��ȡ״̬
			String logonStatus = queryForm.getQueryLogonStatus();
			if (logonStatus != null) {
				if (!logonStatus.trim().equals("")) {
					queryModel.setLogonStatus(logonStatus);
				}
			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();
			
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChat()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChat()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ���ն˻������� */
	public org.apache.struts.action.ActionForward deleteEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		List<EdcAlipayWeChat> keysList = new ArrayList<EdcAlipayWeChat>();
		try {
			log.info("EdcAlipayWeChatDispatchAction.deleteEdcAlipayWeChat()��ʼ���ã�ɾ����¼��");
			
			UserData ud = getSessionUserData(request);
			
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("ɾ����¼ ��������==" + strId);

				if (tt.length == 4) {

					EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();
					edcAlipayWeChat.setMerchantId(tt[0].trim());
					edcAlipayWeChat.setTerminalId(tt[1].trim());
					edcAlipayWeChat.setModuleId(tt[2].trim());

					keysList.add(edcAlipayWeChat);
				}
			}
			for (EdcAlipayWeChat list : keysList) {
				System.out.println(list.toString());
			}
			
			if (keysList.size() > 0) {
				EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
				logic.deleteItem(keysList, ud);
			}
	
			log.info("EdcAlipayWeChatDispatchAction.deleteEdcAlipayWeChat()�������ã�ɾ����¼��");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDispatchAction.deleteEdcAlipayWeChat()ɾ����¼�������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ����
	 */
	public org.apache.struts.action.ActionForward createEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			log.info("EdcAlipayWeChatDispatchAction.createEdcNewfkterminalOrm()��ʼ���ã�����һ���µ���Ϣ��");

			UserData ud = getSessionUserData(request);
			// �õ�Logic
			EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
			// �õ�Form
			EdcAlipayWeChatForm edcAlipayWeChatForm = (EdcAlipayWeChatForm) form;
			// �½�һ��Model
			EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();

			if (ud != null) {

				BeanUtils.copyProperties(edcAlipayWeChat, edcAlipayWeChatForm);

				logic.createItem(edcAlipayWeChat, ud);
			}

			log.info("EdcAlipayWeChatDispatchAction.createEdcNewfkterminalOrm()�������ã�����һ���µ���Ϣ��");

		} catch (Exception e) {
			log.info("EdcAlipayWeChatDispatchAction.createEdcNewfkterminalOrm()����һ���µ���Ϣ�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸��ն������趨��Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcAlipayWeChatByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChatByKey()��ʼ���ã���ʾ�޸��ն������趨��Ϣ���档");
			EdcAlipayWeChatForm newForm = (EdcAlipayWeChatForm) form;
			// System.out.println("form::" + form);
			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChatByKey()�������ã���ʾ�����ն������趨��Ϣ���档");
				return mapping.findForward("add");
			}
			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("#");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();

			if (tt.length == 4) {
				edcAlipayWeChat.setMerchantId(tt[0].trim());
				edcAlipayWeChat.setTerminalId(tt[1].trim());
				edcAlipayWeChat.setModuleId(tt[2].trim());
			}

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
			// �õ�Form
			EdcAlipayWeChatForm edcAlipayWeChatForm = (EdcAlipayWeChatForm) form;

			map = logic.findItemByKey(edcAlipayWeChat, ud);

			EdcAlipayWeChat model = (EdcAlipayWeChat) map.get("Infolist");

			BeanUtils.copyProperties(edcAlipayWeChatForm, model);

			log.info("EdcAlipayWeChatDispatchAction.queryEdcTerminalByKey()�������ã���ʾ�޸��ն������趨��Ϣ���档");

		} catch (Exception e) {

			log.info("EdcAlipayWeChatDispatchAction.queryEdcTerminalByKey()��ʾ�޸��ն������趨��Ϣ���棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}
	
	/**
	 * ��֤�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ��
	 */
	public org.apache.struts.action.ActionForward checkMerTerMod(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = null;
		try {
			log.info("EdcAlipayWeChatDispatchAction.checkMerTerMod()��ʼ���ã���֤�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ�ԡ�");
			
			String merchantId = request.getParameter("merchantId");
			String terminalId = request.getParameter("terminalId");
			String moduleId = request.getParameter("moduleId");
			
			//��װ
			EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();
			edcAlipayWeChat.setMerchantId(merchantId);
			edcAlipayWeChat.setTerminalId(terminalId);
			edcAlipayWeChat.setModuleId(moduleId);
			
			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
			map = logic.findItemByKey(edcAlipayWeChat, ud);
			EdcAlipayWeChat model = (EdcAlipayWeChat) map.get("Infolist");
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (model == null || model.getMerchantId() == null) {
				out.print(true);
			}else {
				out.print(false);
			}

			log.info("EdcAlipayWeChatDispatchAction.checkMerTerMod()��ʼ���ã���֤�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ�ԡ�");

		} catch (Exception e) {

			log.info("EdcAlipayWeChatDispatchAction.checkMerTerMod()��ʼ���ã���֤�̻���š��ն˺� ��ģ��ID���ߵ�Ψһ�ԡ�");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
	
	/**
	 * �޸��ն������趨
	 */
	public org.apache.struts.action.ActionForward saveEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// ??????Model
		EdcNewfkterminalOrm edcTerminal = new EdcNewfkterminalOrm();
		try {
			log.info("EdcAlipayWeChatDispatchAction.saveEdcNewfkterminalOrm()��ʼ���ã��޸��ն������趨��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcNewfkterminalOrmLogicInterface logic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");
			// �õ�Form
			EdcNewfkterminalOrmForm edcTerminalForm = (EdcNewfkterminalOrmForm) form;

			if (ud != null) {
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcAlipayWeChatDispatchAction.saveEdcNewfkterminalOrm()�������ã��޸��ն������趨��");
		} catch (Exception e) {
			log.info("EdcAlipayWeChatDispatchAction.saveEdcNewfkterminalOrm()�޸��ն������趨�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	// ����
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("��ʼ����excelģ��");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("�밴ģ���������ݣ��̻���š��̻��ն˱�š������̻��š�ģ��ID�����κš�������Կ����MAC_KEY��������ڣ�");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = {"�̻����","�̻��ն˱��","�����̻���","ģ��ID","ϵͳ��ˮ��","������ˮ��","���κ�","������Կ����MAC_KEY"};
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "00000001");
			POIUtils.createTextCell(book, row, (short) 2, "007110154114660");
			POIUtils.createTextCell(book, row, (short) 3, "134");
			POIUtils.createTextCell(book, row, (short) 4, "1");
			POIUtils.createTextCell(book, row, (short) 5, "1");
			POIUtils.createTextCell(book, row, (short) 6, "1");
			POIUtils.createTextCell(book, row, (short) 7, "MD5_KEY");
			// ���ļ������б���
			String headName = "֧����/΢���ն˵���ģ��";
			String fileName = POIUtils.encodeFileName(request, headName);
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CurTranLsDispatchAction.queryDetail()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}

	// �ϴ�
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("��ʼ��������");
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// �ļ���С���ܳ���5M
		try {
			writer = response.getWriter();
			EdcAlipayWeChatUploadForm uf = (EdcAlipayWeChatUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
			} else {
				List<EdcAlipayWeChat> edcAlipayWeChatList = new ArrayList<EdcAlipayWeChat>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// for (int i = 0; i <row.getLastCellNum(); i++) {
					// System.out.print(row.getCell(i)+"\t");
					// }
					// �½�һ��Model
					EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();
					//��װ����
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					if (StringUtil.isNull(merchantId)) {
						break;
					}
					edcAlipayWeChat.setMerchantId(merchantId);
					
					String terminalId = POIUtils.getStringFromExcelCell(row.getCell(1));
					if (StringUtil.isNull(terminalId)) {
						break;
					}
					edcAlipayWeChat.setTerminalId(terminalId);
					
					String bankMerchantId = POIUtils.getStringFromExcelCell(row.getCell(2));
					if (StringUtil.isNull(bankMerchantId)) {
						break;
					}
					edcAlipayWeChat.setBankMerchantId(bankMerchantId);
					
					String moduleId = POIUtils.getStringFromExcelCell(row.getCell(3));
					if (StringUtil.isNull(moduleId)) {
						break;
					}
					edcAlipayWeChat.setModuleId(moduleId);
					
					edcAlipayWeChat.setSysTrace(POIUtils.getStringFromExcelCell(row.getCell(4)));
					edcAlipayWeChat.setBankTrace(POIUtils.getStringFromExcelCell(row.getCell(5)));
					
					String batchNo = POIUtils.getStringFromExcelCell(row.getCell(6));
					if (StringUtil.isNull(batchNo)) {
						break;
					}
					edcAlipayWeChat.setBatchNo(batchNo);
					
					String makTmk = POIUtils.getStringFromExcelCell(row.getCell(7));
					if (StringUtil.isNull(makTmk)) {
						break;
					}
					edcAlipayWeChat.setMakTmk(makTmk);
					
					edcAlipayWeChat.setPinFmt("2");//PIN�㷨��ʶ 1: ANSI X98��ʽ���������˺ţ� 2: ANSI X98�㷨�������˺ţ�
					edcAlipayWeChat.setEncMethod("6");//�����㷨 0: DES 6: 3DES  7:MD5
					edcAlipayWeChat.setMacFlag("1");//MAC�����־ 0: �����ն˲��� 1: ����
					edcAlipayWeChat.setSettStatus("0");//����״̬ 0: ��������״̬ 1: ��Ҫ���� 2: ���ʽ�����
					edcAlipayWeChat.setLogonStatus("1");//�������POS�ն�ǩ��״̬ 0: ǩ��״̬ 1: ��ǩ�� 2:ǩ���쳣
					edcAlipayWeChat.setFlag("1");//��ͨ��־ 1��������ͨ  0��δ��ͨ
					
					edcAlipayWeChatList.add(edcAlipayWeChat);
				}
				// �õ�Logic
				EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
				logic.saveUploadItem(edcAlipayWeChatList, ud);
				// ����
				jsonObj.put("result", "�����ɹ��� �ѳɹ����� " + edcAlipayWeChatList.size() + " ����¼��");
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("���������̻��ն˱���Excel�ļ�Action�쳣: {}", e);
			try {
				jsonObj.put("result", "����ʧ�ܣ�");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("���������̻��ն˱���Excel�ļ�Action�쳣: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("���������̻��ն˱���Excel�ļ�,�ر�IO��");
				} catch (Exception e) {
					log.error("���������̻��ն˱���Excel�ļ�Action�쳣: {}", e);
				}
			}
		}
		return null;
	}
	
}