package cn.yufu.posp.terminalmanager.web.action;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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

import cn.yufu.core.common.util.CommonDomain;
import cn.yufu.core.common.util.POIUtils;
import cn.yufu.core.common.util.ShellUtils;
import cn.yufu.core.common.util.StringUtil;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.dao.hibernate.hql.EdcTerminalOrmDaoHibernateHQL;
import cn.yufu.posp.terminalmanager.domain.logic.EdcTerminalOrmLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalOrmForm;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalOrmUploadForm;

public class EdcTerminalOrmDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTerminal");

	public EdcTerminalOrmDispatchAction() {
	}

	/**
	 * ��ʼҳ��
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
	public ActionForward queryEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalOrm()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("id.merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("id.terminalId");
				if (sortField.equals("3"))
					pageInfo.setOrderField("terminalStat");
				if (sortField.equals("4"))
					pageInfo.setOrderField("edcType");
				if (sortField.equals("5"))
					pageInfo.setOrderField("updateOper");
				if (sortField.equals("6"))
					pageInfo.setOrderField("updateDate");
			}

			// ���ò�ѯ����
			EdcTerminalOrm queryModel = new EdcTerminalOrm();
			EdcTerminalOrmForm queryForm = (EdcTerminalOrmForm) form;
			// ���̻���Ų�ѯ
			//String merchantId = request.getParameter("_merchantId");
			String merchantId = queryForm.getQueryMerchantId();
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// ���ն˱�Ų�ѯ
			//String terminalId = request.getParameter("_terminalId");
			String terminalId = queryForm.getQueryTerminalId();
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			// �������ն˺Ų�ѯ
			String bankTerminalId = queryForm.getQueryBankTerminalId();
			if (terminalId != null) {
				if (!bankTerminalId.trim().equals("")) {
					queryModel.setBankTerminalId(bankTerminalId);
				}
			}
			
			//String logonStatus = request.getParameter("_logonStatus");
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

			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalOrm()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalOrm()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ���ն˻������� */
	public ActionForward deleteEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		List<EdcTerminalOrm> keysList = new ArrayList<EdcTerminalOrm>();
		try {
			log.info("EdcTerminalOrmDispatchAction.deleteEdcTerminalOrm()��ʼ���ã�ɾ����¼��");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("ɾ����¼ ��������==" + strId);

				if (tt.length == 4) {

					EdcTerminalOrm edcTerminal = new EdcTerminalOrm();
					edcTerminal.setMerchantId(tt[0].trim());
					edcTerminal.setTerminalId(tt[1].trim());
					edcTerminal.setModuleId(tt[2].trim());

					// edcTerminal.setTerminalStat(tt[2].trim());
					// edcTerminal.setEdcType(tt[3].trim());
					// edcTerminal.setSoftVer(tt[4].trim());
					// edcTerminal.setDownloadFlag(tt[5].trim());
					// edcTerminal.setDownloadMode(Integer.parseInt(tt[6].trim()));
					// edcTerminal.setUpdateOper(tt[7].trim());
					// edcTerminal.setUpdateDate(tt[8].trim());
					// edcTerminal.setUpdateTime(tt[9].trim());

					keysList.add(edcTerminal);
				}
			}
			if (keysList.size() > 0) {
				EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcTerminalOrmDispatchAction.deleteEdcTerminalOrm()�������ã�ɾ����¼��");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDispatchAction.deleteEdcTerminalOrm()ɾ����¼�������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ����
	 */
	public ActionForward createEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTerminalOrmDispatchAction.createEdcTerminalOrm()��ʼ���ã�����һ���µ���Ϣ��");

			UserData ud = getSessionUserData(request);
			// �õ�Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
			// �õ�Form
			EdcTerminalOrmForm edcTerminalForm = (EdcTerminalOrmForm) form;
			// �½�һ��Model
			EdcTerminalOrm edcTerminal = new EdcTerminalOrm();

			if (ud != null) {

				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.createItem(edcTerminal, ud);
			}

			log.info("EdcTerminalOrmDispatchAction.createEdcTerminalOrm()�������ã�����һ���µ���Ϣ��");

		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.createEdcTerminalOrm()����һ���µ���Ϣ�������쳣��");
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
	public ActionForward queryEdcTerminalOrmByKey(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()��ʼ���ã���ʾ�޸��ն������趨��Ϣ���档");
			EdcTerminalOrmForm newForm = (EdcTerminalOrmForm) form;
			// System.out.println("form::" + form);
			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()�������ã���ʾ�����ն������趨��Ϣ���档");
				return mapping.findForward("add");
			}
			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("#");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcTerminalOrm edcTerminal = new EdcTerminalOrm();

			if (tt.length == 4) {
				edcTerminal.setMerchantId(tt[0].trim());
				edcTerminal.setTerminalId(tt[1].trim());
				edcTerminal.setModuleId(tt[2].trim());

				// edcTerminal.setTerminalStat(tt[2].trim());
				// edcTerminal.setEdcType(tt[3].trim());
				// edcTerminal.setSoftVer(tt[4].trim());
				// edcTerminal.setDownloadFlag(tt[5].trim());
				// edcTerminal.setDownloadMode(Integer.parseInt(tt[6].trim()));
				// edcTerminal.setUpdateOper(tt[7].trim());
				// edcTerminal.setUpdateDate(tt[8].trim());
				// edcTerminal.setUpdateTime(tt[9].trim());

			}

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
			// �õ�Form
			EdcTerminalOrmForm edcTerminalForm = (EdcTerminalOrmForm) form;

			map = logic.findItemByKey(edcTerminal, ud);

			EdcTerminalOrm model = (EdcTerminalOrm) map.get("Infolist");

			BeanUtils.copyProperties(edcTerminalForm, model);

			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()�������ã���ʾ�޸��ն������趨��Ϣ���档");

		} catch (Exception e) {

			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()��ʾ�޸��ն������趨��Ϣ���棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}

	/**
	 * �޸��ն������趨
	 */
	public ActionForward saveEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		// �½�һ��Model
		EdcTerminalOrm edcTerminal = new EdcTerminalOrm();
		try {
			log.info("EdcTerminalOrmDispatchAction.saveEdcTerminalOrm()��ʼ���ã��޸��ն������趨��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
			// �õ�Form
			EdcTerminalOrmForm edcTerminalForm = (EdcTerminalOrmForm) form;

			if (ud != null) {
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcTerminalOrmDispatchAction.saveEdcTerminalOrm()�������ã��޸��ն������趨��");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.saveEdcTerminalOrm()�޸��ն������趨�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	// ����
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
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
			cell.setCellValue("�밴ģ���������ݣ��̻���š��̻��ն˱��ϵͳ������ڣ�");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "�̻����", "�̻��ն˱��", "���б�ʶ", "�����̻���", "�����ն˺�", "ģ��ID", "ϵͳ��ˮ��", "������ˮ��", "���κ�" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "00000001");
			POIUtils.createTextCell(book, row, (short) 2, "99999999");
			POIUtils.createTextCell(book, row, (short) 3, "007110154114660");
			POIUtils.createTextCell(book, row, (short) 4, "01047321");
			POIUtils.createTextCell(book, row, (short) 5, "66");
			POIUtils.createTextCell(book, row, (short) 6, "0");
			POIUtils.createTextCell(book, row, (short) 7, "0");
			POIUtils.createTextCell(book, row, (short) 8, "1");
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("�ɸ����ն˵���ģ��", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
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
	public ActionForward fileUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		// �õ�Logic
		EdcTerminalOrmLogicInterface edcTerminaOrmLogic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
		log.info("��ʼ��������");
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// �ļ���С���ܳ���5M
		Set edcTerminalOrmPKEYSet = new LinkedHashSet();
		try {
			writer = response.getWriter();
			EdcTerminalOrmUploadForm uf = (EdcTerminalOrmUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
			} else {
				int successTotal=0;
				Sheet sheet = POIUtils.readExcel(byteFile);
				String isExistOne="";
				String isExistTwo="";
				String merchantId=""; 
				String terminalId=""; 
				String bankMerchantId=""; 
				String bankTerminalId=""; 
				String moduleId=""; 
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// for (int i = 0; i <row.getLastCellNum(); i++) {
					// System.out.print(row.getCell(i)+"\t");
					// }
					// �½�һ��Model
					EdcTerminalOrm edcTerminalOrm = new EdcTerminalOrm();
					List<EdcTerminalOrm> edcTerminalOrmList  = new ArrayList<EdcTerminalOrm>();
					     merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					     terminalId = POIUtils.getStringFromExcelCell(row.getCell(1));
					     bankMerchantId = POIUtils.getStringFromExcelCell(row.getCell(3));
					     bankTerminalId = POIUtils.getStringFromExcelCell(row.getCell(4));
					     moduleId = POIUtils.getStringFromExcelCell(row.getCell(5));
					//����LogicУ����������Ψһ��PKEY��MERCHANT_ID, TERMINAL_ID, MODULE_ID��
					if (!StringUtil.isNull(merchantId) || !StringUtil.isNull(terminalId)|| !StringUtil.isNull(moduleId)){
						isExistOne = edcTerminaOrmLogic.checkEdcTerminalOrmPKEY(merchantId,terminalId,moduleId);	
					}
					//��������PKEY�����ظ�
					if (isExistOne.equals("1")) {
						edcTerminalOrmPKEYSet.add("<br />��" + (index+1) + "�У��̻����=" + merchantId + ",�̻��ն˱��=" + merchantId  + ",ģ��ID=" + moduleId+ "���Ѿ����ڣ�Υ����������Ψһ�ԣ�<br />");
						continue;
					}
					
					//����LogicУ����������ORMΨһ�ԣ�BANK_MERCHANT_ID, BANK_TERMINAL_ID, MODULE_ID��
					if (!StringUtil.isNull(bankMerchantId) || !StringUtil.isNull(bankTerminalId)|| !StringUtil.isNull(moduleId)){
						isExistTwo = edcTerminaOrmLogic.checkEdcTerminalOrmORM(bankMerchantId,bankTerminalId,moduleId);
					}
					
					//У����������ORMΨһ��
					if (isExistTwo.equals("1")) {
						edcTerminalOrmPKEYSet.add("<br />��" + (index+1) + "�У������̻���=" + bankMerchantId + ",�����ն˺�=" + bankTerminalId  + ",ģ��ID=" + moduleId+ "���Ѿ����ڣ�Υ������Ψһ�ԣ�<br />");
						continue;
					}
					edcTerminalOrm.setMerchantId(merchantId);
					edcTerminalOrm.setTerminalId(terminalId);
					edcTerminalOrm.setBankId(POIUtils.getStringFromExcelCell(row.getCell(2)));
					edcTerminalOrm.setBankMerchantId(bankMerchantId);
					edcTerminalOrm.setBankTerminalId(bankTerminalId);
					edcTerminalOrm.setModuleId(moduleId);
					edcTerminalOrm.setSysTrace(POIUtils.getStringFromExcelCell(row.getCell(6)));
					edcTerminalOrm.setBankTrace(POIUtils.getStringFromExcelCell(row.getCell(7)));
					String batchNo = POIUtils.getStringFromExcelCell(row.getCell(8));
					edcTerminalOrm.setBatchNo(batchNo.equals("0") ? "1" : batchNo);
					
					
					
					edcTerminalOrm.setPinFmt("2");// PIN�㷨��ʶ 1: ANSI
					// X98��ʽ���������˺ţ� 2: ANSI
					// X98�㷨�������˺ţ�
					edcTerminalOrm.setEncMethod("6");// �����㷨 0: DES 6: 3DES
					edcTerminalOrm.setMacFlag("1");// MAC�����־ 0: �����ն˲��� 1: ����
					edcTerminalOrm.setSettStatus("0");// ����״̬ 0: ��������״̬ 1: ��Ҫ����
					// 2: ���ʽ�����
					edcTerminalOrm.setLogonStatus("0");// �������POS�ն�ǩ��״̬ 0: ǩ��״̬
					// 1: ��ǩ�� 2:ǩ���쳣
					edcTerminalOrm.setFlag("1");// ��ͨ��־ 1��������ͨ 0��δ��ͨ
					edcTerminalOrmList.add(edcTerminalOrm);
					//����Logic�����µ���Ϣ
					edcTerminaOrmLogic.saveUploadItem(edcTerminalOrmList, ud);
					successTotal++;
				}
				
				// ����
				String returnVal = "�����ɹ��� �ѳɹ����� " + successTotal + " ����¼��<br>";
				if (edcTerminalOrmPKEYSet.size() > 0) {
					returnVal += "�ɸ����ն���Ϣʧ�ܼ�¼��" + edcTerminalOrmPKEYSet + "<br>";
				}
				jsonObj.put("result", returnVal);
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

	/*** ͬ���ɸ������� **/
	public ActionForward syncParam(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.syncParam()��ʼ���ã�ͬ���ɸ���������");

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("syncParam");
			String fPath = actionForward.getPath();
			pathForward = fPath;

			log.info("EdcTerminalOrmDispatchAction.syncParam()�������ã�ͬ���ɸ���������");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.syncParam()ͬ���ɸ��������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/*** ���� **/
	public ActionForward settle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.syncParam()��ʼ���ã�ͬ���ɸ���������");
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");

			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_bankmerchantId");
			String terminalId = request.getParameter("_bankterminalId");
			String syncParam = request.getParameter("syncParam");
			if (StringUtil.isNull(merchantId) || StringUtil.isNull(terminalId)) {
				request.setAttribute("msg", "����д�����̻��ź������ն˺š�");
			} else {
				EdcTerminalOrm orm = logic.queryModualBy(merchantId, terminalId);
				String moduleId = orm.getModuleId();
				if (StringUtil.isNull(moduleId)) {
					request.setAttribute("msg", "�����ն˺Ų����ڡ�");
				} else {
					String host = CommonDomain.CONST_SYNC_OLD_FK_HOST;
					String username = CommonDomain.CONST_SYNC_OLD_FK_USER;
					String password = CommonDomain.CONST_SYNC_OLD_FK_PWD;
					int port = CommonDomain.CONST_SYNC_OLD_FK_PORT;
					ShellUtils shell = new ShellUtils(host, username, password, port);
					// FKSettle [ģ���] [�̻���] [�ն˺�]
					String command = CommonDomain.CONST_SYNC_OLD_FK_SETTLE_COMMAND + " " + moduleId + " " + merchantId + " " + terminalId;
					log.info(command);
					shell.connect();
					shell.execCmd(command);
					request.setAttribute("msg", "����ű���ִ��");
				}
			}

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("syncParam");
			if (StringUtil.isNull(syncParam)) {
				actionForward = mapping.findForward("editToQuery");
			}
			String fPath = actionForward.getPath();
			pathForward = fPath;
			log.info("EdcTerminalOrmDispatchAction.syncParam()�������ã�ͬ���ɸ���������");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.syncParam()ͬ���ɸ��������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ǩ�� **/
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.syncParam()��ʼ���ã�ͬ���ɸ���������");
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");

			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_bankmerchantId");
			String terminalId = request.getParameter("_bankterminalId");
			String syncParam = request.getParameter("syncParam");
			if (StringUtil.isNull(merchantId) || StringUtil.isNull(terminalId)) {
				request.setAttribute("msg", "����д�����̻��ź������ն˺š�");
			} else {
				EdcTerminalOrm orm = logic.queryModualBy(merchantId, terminalId);
				String moduleId = orm.getModuleId();
				if (StringUtil.isNull(moduleId)) {
					request.setAttribute("msg", "�����ն˺Ų����ڡ�");
				} else {
					String host = CommonDomain.CONST_SYNC_OLD_FK_HOST;
					String username = CommonDomain.CONST_SYNC_OLD_FK_USER;
					String password = CommonDomain.CONST_SYNC_OLD_FK_PWD;
					int port = CommonDomain.CONST_SYNC_OLD_FK_PORT;
					ShellUtils shell = new ShellUtils(host, username, password, port);
					// FKLogin [ģ���] [��������--84] [�̻���] [�ն˺�]
					String command = CommonDomain.CONST_SYNC_OLD_FK_LOGIN_COMMAND + " " + moduleId + " 84 " + merchantId + " " + terminalId;
					log.info(command);
					shell.connect();
					shell.execCmd(command);
					request.setAttribute("msg", "ǩ���ű���ִ�С�");
				}
			}

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("syncParam");
			if (StringUtil.isNull(syncParam)) {
				actionForward = mapping.findForward("editToQuery");
			}
			String fPath = actionForward.getPath();
			pathForward = fPath;
			log.info("EdcTerminalOrmDispatchAction.syncParam()�������ã�ͬ���ɸ���������");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.syncParam()ͬ���ɸ��������������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}
}
