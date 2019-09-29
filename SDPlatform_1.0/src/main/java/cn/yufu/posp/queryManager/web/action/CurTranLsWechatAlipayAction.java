/**
 *����:cn.yufu.posp.queryManager.web.action
 *����:package cn.yufu.posp.queryManager.web.action;
 */
package cn.yufu.posp.queryManager.web.action;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.POIUtils;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.queryManager.domain.logic.CurTranLsWechatAlipayLogicInterface;
import cn.yufu.posp.queryManager.domain.model.TblExpCurTranLog;

/**
 * CurTranLsWechatAlipayAction.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��20��
 * ����:΢��֧������ѯ��ˮ
 */
public class CurTranLsWechatAlipayAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("query");

	/**
	 * ����ˮҳ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward toQuery(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";
		try {
			log.info("CurTranLsDispatchAction.queryAll()��ʼ���ã�����΢��֧������ˮ");
			PageInfoModel pageInfo = new PageInfoModel();
			request.setAttribute("pageInfoResult", pageInfo);
			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			Calendar calendar = Calendar.getInstance(); //�õ�����
			calendar.setTime(new Date());//�ѵ�ǰʱ�丳������
			calendar.add(Calendar.DAY_OF_MONTH, -1);  //����Ϊǰһ��
			Date dBefore = calendar.getTime();  //�õ�ǰһ���ʱ��
			pathForward = fPath + "?_startDate="+String.format("%1$tY%1$tm%1$td", dBefore);
			log.info("CurTranLsDispatchAction.queryAll()�������ã�����΢��֧������Ϣ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * ������ˮ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("CurTranLsDispatchAction.queryAll()��ʼ���ã�������ˮ");

			// �õ�Logic
			CurTranLsWechatAlipayLogicInterface CurTranLsWechatAlipayLogicInterface = (CurTranLsWechatAlipayLogicInterface) getBean("curTranLsWechatAlipayLogic");

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
//			String orderType = "1";
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			pageInfo.setOrderField("cardNo");

			// ���ò�ѯ����
			TblExpCurTranLog queryModel = new TblExpCurTranLog();
			// ������ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			String _terminalId = request.getParameter("_terminalId");
			if (_terminalId != null) {
				if (!_terminalId.trim().equals("")) {
					queryModel.setTerminalId(_terminalId);
				}
			}
			String startDate = request.getParameter("_startDate");
			String endDate = request.getParameter("_endDate");
			String _tranRrn = request.getParameter("_tranRrn");
			if (_tranRrn != null) {
				if (!_tranRrn.trim().equals("")) {
					queryModel.setTranRrn(_tranRrn);
				}
			}
			String scanCode = request.getParameter("_scanCode");
			if (scanCode != null) {
				if (!scanCode.trim().equals("")) {
					queryModel.setScanCode(scanCode);
				}
			}
			String tranType = request.getParameter("_tranType");
			if (tranType != null) {
				if (!tranType.trim().equals("")) {
					queryModel.setTranType(new BigDecimal(tranType));
				}
			}
			
			String _queryType = request.getParameter("_queryType");
			if (_queryType != null) {
				if (!_queryType.trim().equals("")) {
					queryModel.setAcqRespCode(new BigDecimal(_queryType));
				}
			}
			
			pageInfo = CurTranLsWechatAlipayLogicInterface.queryAll(queryModel, pageInfo, getSessionUserData(request),startDate,endDate);
			request.setAttribute("pageInfoResult", pageInfo);
			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("CurTranLsDispatchAction.queryAll()�������ã����һ�����Ϣ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	// ����
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public org.apache.struts.action.ActionForward exportCurTranLs(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// �õ�Logic
			CurTranLsWechatAlipayLogicInterface CurTranLsWechatAlipayLogic = (CurTranLsWechatAlipayLogicInterface) getBean("curTranLsWechatAlipayLogic");

			// ���ò�ѯ����
			TblExpCurTranLog queryModel = new TblExpCurTranLog();
			// ������ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			String _terminalId = request.getParameter("_terminalId");
			if (_terminalId != null) {
				if (!_terminalId.trim().equals("")) {
					queryModel.setTerminalId(_terminalId);
				}
			}
			String startDate = request.getParameter("_startDate");
			String endDate = request.getParameter("_endDate");
			String _tranRrn = request.getParameter("_tranRrn");
			if (_tranRrn != null) {
				if (!_tranRrn.trim().equals("")) {
					queryModel.setTranRrn(_tranRrn);
				}
			}
			String scanCode = request.getParameter("_scanCode");
			if (scanCode != null) {
				if (!scanCode.trim().equals("")) {
					queryModel.setScanCode(scanCode);
				}
			}
			String tranType = request.getParameter("_tranType");
			if (tranType != null) {
				if (!tranType.trim().equals("")) {
					queryModel.setTranType(new BigDecimal(tranType));
				}
			}
			
			String _queryType = request.getParameter("_queryType");
			if (_queryType != null) {
				if (!_queryType.trim().equals("")) {
					queryModel.setAcqRespCode(new BigDecimal(_queryType));
				}
			}


			List list = CurTranLsWechatAlipayLogic.queryExport(queryModel, getSessionUserData(request), startDate, endDate);
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			
			HSSFCellStyle titleStyle = book.createCellStyle();

			// ���ñ߿�
			titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// ���ñ���ɫ
			titleStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// ���þ���
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ��������
			HSSFFont font = book.createFont();
			font.setFontName("����");
			font.setFontHeightInPoints((short) 11); // ���������С
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ������ʾ
			titleStyle.setFont(font);// ѡ����Ҫ�õ��������ʽ
			// �����Զ�����
			titleStyle.setWrapText(true);
			// �����п� ,��һ������������id(��0��ʼ),��2������������ֵ
			sheet.setColumnWidth(0, 7000);
			sheet.setColumnWidth(1, 4000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 7000);
			sheet.setColumnWidth(5, 7000);//MCC
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 4000);//ʡ��
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 7000);//����
			sheet.setColumnWidth(10, 4000);
//			sheet.setColumnWidth(11, 4000);//����
	//		sheet.setColumnWidth(12, 4000);
			
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			String[] headCol = { "�̻���", "�ն˺�", "�ο��� ", "��������", "ҵ�񶩵���", "ԭҵ�񶩵���", "������ϸ ", "���׽�� ", "ԭ���׽��", "ʱ���","���׽��" };
			for (int i = 0; i < headCol.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headCol[i]);
			}
			// ��������
			for (int i = 0; i < list.size(); i++) {
				TblExpCurTranLog curTranLs = (TblExpCurTranLog) list.get(i);
				String tranTypes = "";
				if("1".equals(curTranLs.getTranType().toString())){
					tranTypes = "����";
				}else if("9".equals(curTranLs.getTranType().toString())){
					tranTypes = "�˻�";
				}else{
					tranTypes = "����";
				}
				row = sheet.createRow(i + 1);
				POIUtils.createTextsCell(book, row, (short) 0, curTranLs.getMerchantId().toString(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 1, curTranLs.getTerminalId(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 2, curTranLs.getTranRrn(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 3, tranTypes,titleStyle);
				POIUtils.createTextsCell(book, row, (short) 4, curTranLs.getSysOrderId(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 5, curTranLs.getSysVoidOrderId(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 6, curTranLs.getSysOrderDtl(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 7, curTranLs.getTranAmt().toString(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 8, curTranLs.getTranVoidAmt().toString(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 9, curTranLs.getSysTimeStamp(),titleStyle);
				POIUtils.createTextsCell(book, row, (short) 10, curTranLs.getAcqRespMsg(),titleStyle);
//				POIUtils.createTextCell(book, row, (short) 0, curTranLs.getMerchantId().toString());
//				POIUtils.createTextCell(book, row, (short) 1, curTranLs.getTerminalId());
//				POIUtils.createTextCell(book, row, (short) 2, curTranLs.getTranRrn());
//				POIUtils.createTextCell(book, row, (short) 3, tranTypes);
//				POIUtils.createTextCell(book, row, (short) 4, curTranLs.getSysOrderId());
//				POIUtils.createTextCell(book, row, (short) 5, curTranLs.getSysVoidOrderId());
//				POIUtils.createTextCell(book, row, (short) 6, curTranLs.getSysOrderDtl());
//				POIUtils.createTextCell(book, row, (short) 7, curTranLs.getTranAmt().toString());
//				POIUtils.createTextCell(book, row, (short) 8, curTranLs.getTranVoidAmt().toString());
//				POIUtils.createTextCell(book, row, (short) 9, curTranLs.getSysTimeStamp());
//				POIUtils.createTextCell(book, row, (short) 10, curTranLs.getAcqRespMsg());
			}

			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("֧����΢�Ž�����ˮ��ϸ", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsWechatAlipayLogic.queryDetail()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}

}
