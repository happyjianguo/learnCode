package cn.yufu.posp.queryManager.web.action;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.POIUtils;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MccParamLogicInterface;
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.queryManager.domain.logic.CurTranLsLogicInterface;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;
import cn.yufu.posp.queryManager.web.form.CurTranLsForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;

public class CurTranLsDispatchAction extends OABaseDispatchAction {
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
			log.info("CurTranLsDispatchAction.queryAll()开始调用：查找流水");
			PageInfoModel pageInfo = new PageInfoModel();
			request.setAttribute("pageInfoResult", pageInfo);
			// 查枚举值
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
			String[] tranTypes = new String[100];
			for (int i = 0; i < tranTypeList.size(); i++) {
				SysParameter parameter = (SysParameter) tranTypeList.get(i);
				tranTypes[Integer.valueOf(parameter.getParamValue()).intValue()] = parameter.getId().getParamName();
			}
			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			Calendar calendar = Calendar.getInstance(); //得到日历
			calendar.setTime(new Date());//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
			Date dBefore = calendar.getTime();  //得到前一天的时间
			pathForward = fPath + "?_startDate="+String.format("%1$tY%1$tm%1$td", dBefore);
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
			CurTranLsLogicInterface curTranLsLogic = (CurTranLsLogicInterface) getBean("curTranLsLogic");

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
			String orderType = "1";
//			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
//			String sortField = "7";
			pageInfo.setOrderField("localSysDate");
//			String sortField = request.getParameter(dParams[2]);
//			if (sortField != null) {
//				if (sortField.equals("1"))
//					pageInfo.setOrderField("traceNo");
//				else if (sortField.equals("2"))
//					pageInfo.setOrderField("cardNo");
//				else if (sortField.equals("3"))
//					pageInfo.setOrderField("merchantName");
//				else if (sortField.equals("4"))
//					pageInfo.setOrderField("tranType");
//				else if (sortField.equals("5"))
//					pageInfo.setOrderField("tranAmt");
//				else if (sortField.equals("6"))
//					pageInfo.setOrderField("tranSysTime");
//			}

			// 设置查询条件
			CurTranLs queryModel = new CurTranLs();
			// 按车查询
			String traceNo = request.getParameter("_traceNo");
			if (traceNo != null) {
				if (!traceNo.trim().equals("")) {
					queryModel.setTraceNo(new BigDecimal(traceNo));
				}
			}
			String _batchNo = request.getParameter("_batchNo");
			if (_batchNo != null) {
				if (!_batchNo.trim().equals("")) {
					queryModel.setBatchNo(new BigDecimal(_batchNo));
				}
			}
			String cardNo = request.getParameter("_cardNo");
			if (cardNo != null) {
				if (!cardNo.trim().equals("")) {
					queryModel.setCardNo(cardNo);
				}
			}
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
			String _merchantName = request.getParameter("_merchantName");
			if (_merchantName != null) {
				if (!_merchantName.trim().equals("")) {
					queryModel.setMerchantName(_merchantName);
				}
			}
			String tranType = request.getParameter("_tranType");
			if (tranType != null) {
				if (!tranType.trim().equals("")) {
					queryModel.setTranType(new BigDecimal(tranType));
				}
			}
			String startDate = request.getParameter("_startDate");
			if (startDate != null) {
				if (!startDate.trim().equals("")) {
					queryModel.setLocalSysDate(startDate);
				}
			}
			String startTime = request.getParameter("_startTime");
			if (startTime != null) {
				if (!startTime.trim().equals("")) {
					queryModel.setLocalSysTimeS(startTime);
				}
			}
			String endDate = request.getParameter("_endDate");
			if (endDate != null) {
				if (!endDate.trim().equals("")) {
					queryModel.setLocalSysDateE(endDate);
				}
			}
			String endTime = request.getParameter("_endTime");
			if (endTime != null) {
				if (!endTime.trim().equals("")) {
					queryModel.setLocalSysTimeE(endTime);
				}
			}
			String _queryType = request.getParameter("_queryType");
			if (_queryType != null) {
				if (!_queryType.trim().equals("")) {
					queryModel.setQueryType(_queryType);
				}
			}
			String _bankTraceNo = request.getParameter("_bankTraceNo");
			if (_bankTraceNo != null) {
				if (!_bankTraceNo.trim().equals("")) {
					queryModel.setTrace1(_bankTraceNo);
				}
			}
			String _bankBatchNo = request.getParameter("_bankBatchNo");
			if (_bankBatchNo != null) {
				if (!_bankBatchNo.trim().equals("")) {
					queryModel.setBankBatchNo(_bankBatchNo);
				}
			}
			String _bankRan = request.getParameter("_bankRan");
			if (_bankRan != null) {
				if (!_bankRan.trim().equals("")) {
					queryModel.setTrace2(_bankRan);
				}
			}
			pageInfo = curTranLsLogic.queryAll(queryModel, pageInfo, getSessionUserData(request));
			request.setAttribute("pageInfoResult", pageInfo);
			// 查枚举值
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
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

	/**
	 * 查找当日流水
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryCurTranLs(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("CurTranLsDispatchAction.queryAll()开始调用：查找流水");

			// 得到Logic
			CurTranLsLogicInterface curTranLsLogic = (CurTranLsLogicInterface) getBean("curTranLsLogic");

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
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("traceNo");
				else if (sortField.equals("2"))
					pageInfo.setOrderField("cardNo");
				else if (sortField.equals("3"))
					pageInfo.setOrderField("merchantId");
				else if (sortField.equals("4"))
					pageInfo.setOrderField("tranType");
				else if (sortField.equals("5"))
					pageInfo.setOrderField("tranAmt");
				else if (sortField.equals("6"))
					pageInfo.setOrderField("tranSysTime");

			}

			// 设置查询条件
			CurTranLs queryModel = new CurTranLs();
			// 按车查询
			String traceNo = request.getParameter("_traceNo");
			if (traceNo != null) {
				if (!traceNo.trim().equals("")) {
					queryModel.setTraceNo(new BigDecimal(traceNo));
				}
			}
			String cardNo = request.getParameter("_cardNo");
			if (cardNo != null) {
				if (!cardNo.trim().equals("")) {
					queryModel.setCardNo(cardNo);
				}
			}
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			String _merchantName = request.getParameter("_merchantName");
			if (_merchantName != null) {
				if (!_merchantName.trim().equals("")) {
					queryModel.setMerchantName(_merchantName);
				}
			}
			String tranType = request.getParameter("_tranType");
			if (tranType != null) {
				if (!tranType.trim().equals("")) {
					queryModel.setTranType(new BigDecimal(tranType));
				}
			}
			String startDate = String.format("%1$tY%1$tm%1$td", new Date());
			queryModel.setLocalSysDateS(startDate);
			String startTime = request.getParameter("_startTime");
			if (startTime != null) {
				if (!startTime.trim().equals("")) {
					queryModel.setLocalSysTimeS(startTime);
				}
			}
			String endTime = request.getParameter("_endTime");
			if (endTime != null) {
				if (!endTime.trim().equals("")) {
					queryModel.setLocalSysTimeE(endTime);
				}
			}

			pageInfo = curTranLsLogic.queryAll(queryModel, pageInfo, getSessionUserData(request));
			request.setAttribute("pageInfoResult", pageInfo);
			// 查枚举值
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
			// 取得路径
			ActionForward actionForward = mapping.findForward("queryDay");
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

	public org.apache.struts.action.ActionForward queryDetail(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "detail";
		try {
			log.info("CurTranLsDispatchAction.queryDetail()开始调用：查找明细");
			// 得到Logic
			CurTranLsLogicInterface curTranLsLogic = (CurTranLsLogicInterface) getBean("curTranLsLogic");
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			// 设置查询条件
			CurTranLs queryModel = new CurTranLs();
			// 按条件查询
			String traceNo = request.getParameter("_traceNo");
			if (traceNo != null) {
				if (!traceNo.trim().equals("")) {
					queryModel.setTraceNo(new BigDecimal(traceNo));
				}
			}
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
			String _batchNo = request.getParameter("_batchNo");
			if (_batchNo != null) {
				if (!_batchNo.trim().equals("")) {
					queryModel.setBatchNo(new BigDecimal(_batchNo));
				}
			}
			String localSysDate = request.getParameter("localSysDate");
			if (localSysDate != null) {
				if (!localSysDate.trim().equals("")) {
					queryModel.setLocalSysDate(localSysDate);
				}
			}			
			String localSysTime = request.getParameter("localSysTime");
			if (localSysTime != null) {
				if (!localSysTime.trim().equals("")) {
					queryModel.setLocalSysTime(localSysTime);
				}
			}
			
			UserData ud = getSessionUserData(request);
			CurTranLsForm newForm = (CurTranLsForm) form;
			CurTranLs model = curTranLsLogic.queryDetail(queryModel, ud);
			BeanUtils.copyProperties(newForm, model);
			MccParamLogicInterface mccLogic = (MccParamLogicInterface) getBean("mccLogic");
			HashMap mccMap = mccLogic.findItem(model.getMcc(), ud);
			MccParamModel mcc = (MccParamModel) mccMap.get("Infolist");
			if(mcc!=null)
				newForm.setMccName(mcc.getMccName());
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);
			request.setAttribute("cardTypeList", cardtypeList);
			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			request.setAttribute("bankTypeList", banktypeList);
			//少表 目前只展示码
//			List<SysRespCode> respCodeList = commonLogic.queryAllRespCodeItem(null, null);
//			request.setAttribute("respCodeList", respCodeList);
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
			id.setParamType("MODULE_ID");
			sysParameter.setId(id);
			List moduleIdList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("moduleIdList", moduleIdList);
			
			log.info("CurTranLsDispatchAction.queryDetail()结束调用：查找明细");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryDetail()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward(pathForward);
	}

	// 导出
	public org.apache.struts.action.ActionForward exportCurTranLs(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// 得到Logic
			CurTranLsLogicInterface curTranLsLogic = (CurTranLsLogicInterface) getBean("curTranLsLogic");
			// 设置查询条件
			CurTranLs queryModel = new CurTranLs();
			// 按车查询
			String traceNo = request.getParameter("_traceNo");
			if (traceNo != null) {
				if (!traceNo.trim().equals("")) {
					queryModel.setTraceNo(new BigDecimal(traceNo));
				}
			}
			String cardNo = request.getParameter("_cardNo");
			if (cardNo != null) {
				if (!cardNo.trim().equals("")) {
					queryModel.setCardNo(cardNo);
				}
			}
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			String _merchantName = request.getParameter("_merchantName");
			if (_merchantName != null) {
				if (!_merchantName.trim().equals("")) {
					queryModel.setMerchantName(_merchantName);
				}
			}
			String tranType = request.getParameter("_tranType");
			if (tranType != null) {
				if (!tranType.trim().equals("")) {
					queryModel.setTranType(new BigDecimal(tranType));
				}
			}
			String startDate = request.getParameter("_startDate");
			if (startDate != null) {
				if (!startDate.trim().equals("")) {
					queryModel.setLocalSysDate(startDate);
				}
			}
			String startTime = request.getParameter("_startTime");
			if (startTime != null) {
				if (!startTime.trim().equals("")) {
					queryModel.setLocalSysTimeS(startTime);
				}
			}
			String endTime = request.getParameter("_endTime");
			if (endTime != null) {
				if (!endTime.trim().equals("")) {
					queryModel.setLocalSysTimeE(endTime);
				}
			}
			String _queryType = request.getParameter("_queryType");
			if (_queryType != null) {
				if (!_queryType.trim().equals("")) {
					queryModel.setQueryType(_queryType);
				}
			}
			List list = curTranLsLogic.queryExport(queryModel, getSessionUserData(request));
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			HSSFCellStyle cellStyle = book.createCellStyle();
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			String[] headCol = { "交易流水号", "商户编号", "商户名称 ", "卡号", "交易类型", "本地系统日期", "本地系统时间", "交易金额 ", "系统参考号", "交易标志" };
			for (int i = 0; i < headCol.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headCol[i]);
			}
			String[] tranTypes = new String[100];
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			for (int i = 0; i < tranTypeList.size(); i++) {
				SysParameter parameter = (SysParameter) tranTypeList.get(i);
				tranTypes[Integer.valueOf(parameter.getParamValue()).intValue()] = parameter.getId().getParamName();
			}
			String[] tranFlags = new String[] { "正常", "已撤消", "已确认", "已调整", "已退货", "", "", "", "初登记" };

			// 生成数据
			for (int i = 0; i < list.size(); i++) {
				CurTranLs curTranLs = (CurTranLs) list.get(i);
				row = sheet.createRow(i + 1);
				POIUtils.createTextsCell(book, row, (short) 0, curTranLs.getTraceNo().toString(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 1, curTranLs.getMerchantId(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 2, curTranLs.getMerchantName(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 3, curTranLs.getCardNo(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 4, tranTypes[Integer.valueOf(curTranLs.getTranType().toString())],cellStyle);
				POIUtils.createTextsCell(book, row, (short) 5, curTranLs.getLocalSysDate(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 6, curTranLs.getLocalSysTime(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 7, curTranLs.getTranAmt().toString(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 8, curTranLs.getTranRrn(),cellStyle);
				POIUtils.createTextsCell(book, row, (short) 9, tranFlags[Integer.valueOf(curTranLs.getTranFlag())],cellStyle);
//				POIUtils.createTextCell(book, row, (short) 0, curTranLs.getTraceNo().toString());
//				POIUtils.createTextCell(book, row, (short) 1, curTranLs.getMerchantId());
//				POIUtils.createTextCell(book, row, (short) 2, curTranLs.getMerchantName());
//				POIUtils.createTextCell(book, row, (short) 3, curTranLs.getCardNo());
//				POIUtils.createTextCell(book, row, (short) 4, tranTypes[Integer.valueOf(curTranLs.getTranType().toString())]);
//				POIUtils.createTextCell(book, row, (short) 5, curTranLs.getLocalSysDate());
//				POIUtils.createTextCell(book, row, (short) 6, curTranLs.getLocalSysTime());
//				POIUtils.createTextCell(book, row, (short) 7, curTranLs.getTranAmt().toString());
//				POIUtils.createTextCell(book, row, (short) 8, curTranLs.getTranRrn());
//				POIUtils.createTextCell(book, row, (short) 9, tranFlags[Integer.valueOf(curTranLs.getTranFlag())]);
			}

			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("交易流水明细", "UTF-8");
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

}
