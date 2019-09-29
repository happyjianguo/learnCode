/**
 *包名:cn.yufu.posp.queryManager.web.action
 *描述:package cn.yufu.posp.queryManager.web.action;
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
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年7月20日
 * 描述:微信支付宝查询流水
 */
public class CurTranLsWechatAlipayAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("query");

	/**
	 * 跳流水页
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
			log.info("CurTranLsDispatchAction.queryAll()开始调用：查找微信支付宝流水");
			PageInfoModel pageInfo = new PageInfoModel();
			request.setAttribute("pageInfoResult", pageInfo);
			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(new Date());//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
			Date dBefore = calendar.getTime();  //得到前一天的时间
			pathForward = fPath + "?_startDate="+String.format("%1$tY%1$tm%1$td", dBefore);
			log.info("CurTranLsDispatchAction.queryAll()结束调用：查找微信支付宝信息");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

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
			CurTranLsWechatAlipayLogicInterface CurTranLsWechatAlipayLogicInterface = (CurTranLsWechatAlipayLogicInterface) getBean("curTranLsWechatAlipayLogic");

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
//			String orderType = "1";
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			pageInfo.setOrderField("cardNo");

			// 设置查询条件
			TblExpCurTranLog queryModel = new TblExpCurTranLog();
			// 按车查询
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
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public org.apache.struts.action.ActionForward exportCurTranLs(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// 得到Logic
			CurTranLsWechatAlipayLogicInterface CurTranLsWechatAlipayLogic = (CurTranLsWechatAlipayLogicInterface) getBean("curTranLsWechatAlipayLogic");

			// 设置查询条件
			TblExpCurTranLog queryModel = new TblExpCurTranLog();
			// 按车查询
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
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			
			HSSFCellStyle titleStyle = book.createCellStyle();

			// 设置边框
			titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// 设置背景色
			titleStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// 设置居中
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 设置字体
			HSSFFont font = book.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 11); // 设置字体大小
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
			titleStyle.setFont(font);// 选择需要用到的字体格式
			// 设置自动换行
			titleStyle.setWrapText(true);
			// 设置列宽 ,第一个参数代表列id(从0开始),第2个参数代表宽度值
			sheet.setColumnWidth(0, 7000);
			sheet.setColumnWidth(1, 4000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 7000);
			sheet.setColumnWidth(5, 7000);//MCC
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 4000);//省份
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 7000);//城市
			sheet.setColumnWidth(10, 4000);
//			sheet.setColumnWidth(11, 4000);//区域
	//		sheet.setColumnWidth(12, 4000);
			
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			String[] headCol = { "商户号", "终端号", "参考号 ", "交易类型", "业务订单号", "原业务订单号", "订单明细 ", "交易金额 ", "原交易金额", "时间戳","交易结果" };
			for (int i = 0; i < headCol.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headCol[i]);
			}
			// 生成数据
			for (int i = 0; i < list.size(); i++) {
				TblExpCurTranLog curTranLs = (TblExpCurTranLog) list.get(i);
				String tranTypes = "";
				if("1".equals(curTranLs.getTranType().toString())){
					tranTypes = "消费";
				}else if("9".equals(curTranLs.getTranType().toString())){
					tranTypes = "退货";
				}else{
					tranTypes = "冲正";
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

			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("支付宝微信交易流水明细", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// 得到输出流
			out = response.getOutputStream();
			book.write(out);
			// //刷新输出流
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsWechatAlipayLogic.queryDetail()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}

}
