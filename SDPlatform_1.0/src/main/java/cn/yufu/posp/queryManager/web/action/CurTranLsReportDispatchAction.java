package cn.yufu.posp.queryManager.web.action;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.queryManager.domain.logic.CurTranLsReportLogicInterface;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;
import cn.yufu.posp.queryManager.domain.model.VCurTranLs;

public class CurTranLsReportDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("query");

	/**
	 * 查找流水
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
			log.info("CurTranLsDispatchAction.queryAll()开始调用：查找流水");

			// 得到Logic
			CurTranLsReportLogicInterface curTranLsLogic = (CurTranLsReportLogicInterface) getBean("curTranLsReportLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			String sortField = request.getParameter(dParams[2]);
			// if (sortField != null) {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("traceNo");
			// else if (sortField.equals("2"))
			// pageInfo.setOrderField("cardNo");
			// else if (sortField.equals("3"))
			// pageInfo.setOrderField("merchantName");
			// }

			// 设置查询条件
			CurTranLs queryModel = new CurTranLs();
			String _merchantId = request.getParameter("_merchantId");
			if (_merchantId != null) {
				if (!_merchantId.trim().equals("")) {
					queryModel.setMerchantId(_merchantId);
				}

			}
			String startDate = request.getParameter("_startDate");
			if (startDate != null) {
				if (!startDate.trim().equals("")) {
					queryModel.setLocalSysDateS(startDate);
				}

			}
			String endDate = request.getParameter("_endDate");
			if (endDate != null) {
				if (!endDate.trim().equals("")) {
					queryModel.setLocalSysDateE(endDate);
				}
			}

			pageInfo = curTranLsLogic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("CurTranLsDispatchAction.queryAll()结束调用：查找机构信息");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	// 导出
	public org.apache.struts.action.ActionForward exportCurTranLsReport(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// 得到Logic
			CurTranLsReportLogicInterface curTranLsLogic = (CurTranLsReportLogicInterface) getBean("curTranLsReportLogic");
			// 设置查询条件
			CurTranLs queryModel = new CurTranLs();
			String _merchantId = request.getParameter("_merchantId");
			if (_merchantId != null) {
				if (!_merchantId.trim().equals("")) {
					queryModel.setMerchantId(_merchantId);
				}

			}
			String startDate = request.getParameter("_startDate");
			if (startDate != null) {
				if (!startDate.trim().equals("")) {
					queryModel.setLocalSysDateS(startDate);
				}

			}
			String endDate = request.getParameter("_endDate");
			if (endDate != null) {
				if (!endDate.trim().equals("")) {
					queryModel.setLocalSysDateE(endDate);
				}
			}

			List list = curTranLsLogic.queryExport(queryModel, getSessionUserData(request));
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			String[] headCol = { "商户编号", "商户名称", "总笔", "总金额", "消费笔", "消费金额", "退货笔", "退货金额" };
			for (int i = 0; i < headCol.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headCol[i]);
			}
			// 生成数据
			for (int i = 0; i < list.size(); i++) {
				VCurTranLs curTranLs = (VCurTranLs) list.get(i);
				row = sheet.createRow(i + 1);
				createCell(row, (short) 0, curTranLs.getMerchantId());
				createCell(row, (short) 1, curTranLs.getMerchantName());
				createCell(row, (short) 2, curTranLs.getSumls().toString());
				createCell(row, (short) 3, curTranLs.getAmtTotal().toString());
				createCell(row, (short) 4, curTranLs.getPurchase().toString());
				createCell(row, (short) 5, curTranLs.getPurchaseTotal().toString());
				createCell(row, (short) 6, curTranLs.getRefund().toString());
				createCell(row, (short) 7, curTranLs.getRefundTotal().toString());
			}

			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode((queryModel.getMerchantId() == null ? "" : queryModel.getMerchantId()) + "流水报表"
					+ (queryModel.getLocalSysDateS() == null ? "" : queryModel.getLocalSysDateS() + "-")
					+ (queryModel.getLocalSysDateE() == null ? "" : queryModel.getLocalSysDateE()), "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// 得到输出流
			out = response.getOutputStream();
			book.write(out);
			// //刷新输出流
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryDetail()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}

	private void createCell(HSSFRow row, short colNum, String value) {
		HSSFCell cell = row.createCell(colNum);
		cell.setCellValue(value);
	}

}
