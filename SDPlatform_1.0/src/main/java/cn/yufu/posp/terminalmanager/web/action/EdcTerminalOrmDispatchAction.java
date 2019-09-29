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
	 * 初始页面
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
			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath;

			log.info("MerchantAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/*** 查询终端资料设定 **/
	public ActionForward queryEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalOrm()开始调用：查找符合条件的记录。");

			// 得到Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");

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

			// 设置查询条件
			EdcTerminalOrm queryModel = new EdcTerminalOrm();
			EdcTerminalOrmForm queryForm = (EdcTerminalOrmForm) form;
			// 按商户编号查询
			//String merchantId = request.getParameter("_merchantId");
			String merchantId = queryForm.getQueryMerchantId();
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// 按终端编号查询
			//String terminalId = request.getParameter("_terminalId");
			String terminalId = queryForm.getQueryTerminalId();
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			// 按银行终端号查询
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

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalOrm()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalOrm()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除终端基本资料 */
	public ActionForward deleteEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		List<EdcTerminalOrm> keysList = new ArrayList<EdcTerminalOrm>();
		try {
			log.info("EdcTerminalOrmDispatchAction.deleteEdcTerminalOrm()开始调用：删除记录。");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("删除记录 条件参数==" + strId);

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

			log.info("EdcTerminalOrmDispatchAction.deleteEdcTerminalOrm()结束调用：删除记录。");
		} catch (Exception e) {

			log.info("EdcTerminalOrmDispatchAction.deleteEdcTerminalOrm()删除记录，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建
	 */
	public ActionForward createEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTerminalOrmDispatchAction.createEdcTerminalOrm()开始调用：创建一条新的信息。");

			UserData ud = getSessionUserData(request);
			// 得到Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
			// 得到Form
			EdcTerminalOrmForm edcTerminalForm = (EdcTerminalOrmForm) form;
			// 新建一个Model
			EdcTerminalOrm edcTerminal = new EdcTerminalOrm();

			if (ud != null) {

				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.createItem(edcTerminal, ud);
			}

			log.info("EdcTerminalOrmDispatchAction.createEdcTerminalOrm()结束调用：创建一条新的信息。");

		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.createEdcTerminalOrm()创建一条新的信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 显示修改终端资料设定信息界面
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryEdcTerminalOrmByKey(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()开始调用：显示修改终端资料设定信息界面。");
			EdcTerminalOrmForm newForm = (EdcTerminalOrmForm) form;
			// System.out.println("form::" + form);
			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()线束调用：显示新增终端资料设定信息界面。");
				return mapping.findForward("add");
			}
			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("#");

			// 得到Form
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

			// 得到Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
			// 得到Form
			EdcTerminalOrmForm edcTerminalForm = (EdcTerminalOrmForm) form;

			map = logic.findItemByKey(edcTerminal, ud);

			EdcTerminalOrm model = (EdcTerminalOrm) map.get("Infolist");

			BeanUtils.copyProperties(edcTerminalForm, model);

			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()结束调用：显示修改终端资料设定信息界面。");

		} catch (Exception e) {

			log.info("EdcTerminalOrmDispatchAction.queryEdcTerminalByKey()显示修改终端资料设定信息界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}

	/**
	 * 修改终端资料设定
	 */
	public ActionForward saveEdcTerminalOrm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		// 新建一个Model
		EdcTerminalOrm edcTerminal = new EdcTerminalOrm();
		try {
			log.info("EdcTerminalOrmDispatchAction.saveEdcTerminalOrm()开始调用：修改终端资料设定。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
			// 得到Form
			EdcTerminalOrmForm edcTerminalForm = (EdcTerminalOrmForm) form;

			if (ud != null) {
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcTerminalOrmDispatchAction.saveEdcTerminalOrm()结束调用：修改终端资料设定。");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.saveEdcTerminalOrm()修改终端资料设定，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	// 导出
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		log.info("开始下载excel模板");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("请按模板整理数据！商户编号、商户终端编号系统必须存在！");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// 行高
			sheet.setDefaultRowHeightInPoints(15);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "商户编号", "商户终端编号", "银行标识", "银行商户号", "银行终端号", "模块ID", "系统流水号", "银行流水号", "批次号" };
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
			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("旧福卡终端导入模板", "UTF-8");
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

	// 上传
	public ActionForward fileUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		// 得到Logic
		EdcTerminalOrmLogicInterface edcTerminaOrmLogic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");
		log.info("开始导入数据");
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// 文件大小不能超过5M
		Set edcTerminalOrmPKEYSet = new LinkedHashSet();
		try {
			writer = response.getWriter();
			EdcTerminalOrmUploadForm uf = (EdcTerminalOrmUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "上传失败！");
				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
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
					// 新建一个Model
					EdcTerminalOrm edcTerminalOrm = new EdcTerminalOrm();
					List<EdcTerminalOrm> edcTerminalOrmList  = new ArrayList<EdcTerminalOrm>();
					     merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					     terminalId = POIUtils.getStringFromExcelCell(row.getCell(1));
					     bankMerchantId = POIUtils.getStringFromExcelCell(row.getCell(3));
					     bankTerminalId = POIUtils.getStringFromExcelCell(row.getCell(4));
					     moduleId = POIUtils.getStringFromExcelCell(row.getCell(5));
					//调用Logic校验联合主键唯一性PKEY（MERCHANT_ID, TERMINAL_ID, MODULE_ID）
					if (!StringUtil.isNull(merchantId) || !StringUtil.isNull(terminalId)|| !StringUtil.isNull(moduleId)){
						isExistOne = edcTerminaOrmLogic.checkEdcTerminalOrmPKEY(merchantId,terminalId,moduleId);	
					}
					//联合主键PKEY不能重复
					if (isExistOne.equals("1")) {
						edcTerminalOrmPKEYSet.add("<br />第" + (index+1) + "行，商户编号=" + merchantId + ",商户终端编号=" + merchantId  + ",模块ID=" + moduleId+ "，已经存在，违反联合主键唯一性！<br />");
						continue;
					}
					
					//调用Logic校验联合索引ORM唯一性（BANK_MERCHANT_ID, BANK_TERMINAL_ID, MODULE_ID）
					if (!StringUtil.isNull(bankMerchantId) || !StringUtil.isNull(bankTerminalId)|| !StringUtil.isNull(moduleId)){
						isExistTwo = edcTerminaOrmLogic.checkEdcTerminalOrmORM(bankMerchantId,bankTerminalId,moduleId);
					}
					
					//校验联合索引ORM唯一性
					if (isExistTwo.equals("1")) {
						edcTerminalOrmPKEYSet.add("<br />第" + (index+1) + "行，银行商户号=" + bankMerchantId + ",银行终端号=" + bankTerminalId  + ",模块ID=" + moduleId+ "，已经存在，违反索引唯一性！<br />");
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
					
					
					
					edcTerminalOrm.setPinFmt("2");// PIN算法标识 1: ANSI
					// X98格式（不带主账号） 2: ANSI
					// X98算法（带主账号）
					edcTerminalOrm.setEncMethod("6");// 加密算法 0: DES 6: 3DES
					edcTerminalOrm.setMacFlag("1");// MAC运算标志 0: 特例终端不做 1: 正常
					edcTerminalOrm.setSettStatus("0");// 结帐状态 0: 正常交易状态 1: 需要结帐
					// 2: 结帐进行中
					edcTerminalOrm.setLogonStatus("0");// 受理机构POS终端签到状态 0: 签退状态
					// 1: 已签到 2:签到异常
					edcTerminalOrm.setFlag("1");// 开通标志 1－正常开通 0－未开通
					edcTerminalOrmList.add(edcTerminalOrm);
					//调用Logic创建新的信息
					edcTerminaOrmLogic.saveUploadItem(edcTerminalOrmList, ud);
					successTotal++;
				}
				
				// 保存
				String returnVal = "操作成功！ 已成功导入 " + successTotal + " 条记录！<br>";
				if (edcTerminalOrmPKEYSet.size() > 0) {
					returnVal += "旧福卡终端信息失败记录：" + edcTerminalOrmPKEYSet + "<br>";
				}
				jsonObj.put("result", returnVal);
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("批量增加商户终端保存Excel文件Action异常: {}", e);
			try {
				jsonObj.put("result", "操作失败！");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("批量增加商户终端保存Excel文件Action异常: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("批量增加商户终端保存Excel文件,关闭IO流");
				} catch (Exception e) {
					log.error("批量增加商户终端保存Excel文件Action异常: {}", e);
				}
			}
		}
		return null;
	}

	/*** 同步旧福卡参数 **/
	public ActionForward syncParam(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.syncParam()开始调用：同步旧福卡参数。");

			// 取得路径
			ActionForward actionForward = mapping.findForward("syncParam");
			String fPath = actionForward.getPath();
			pathForward = fPath;

			log.info("EdcTerminalOrmDispatchAction.syncParam()结束调用：同步旧福卡参数。");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.syncParam()同步旧福卡参数，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/*** 结算 **/
	public ActionForward settle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.syncParam()开始调用：同步旧福卡参数。");
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");

			// 按商户编号查询
			String merchantId = request.getParameter("_bankmerchantId");
			String terminalId = request.getParameter("_bankterminalId");
			String syncParam = request.getParameter("syncParam");
			if (StringUtil.isNull(merchantId) || StringUtil.isNull(terminalId)) {
				request.setAttribute("msg", "请填写银行商户号和银行终端号。");
			} else {
				EdcTerminalOrm orm = logic.queryModualBy(merchantId, terminalId);
				String moduleId = orm.getModuleId();
				if (StringUtil.isNull(moduleId)) {
					request.setAttribute("msg", "银行终端号不存在。");
				} else {
					String host = CommonDomain.CONST_SYNC_OLD_FK_HOST;
					String username = CommonDomain.CONST_SYNC_OLD_FK_USER;
					String password = CommonDomain.CONST_SYNC_OLD_FK_PWD;
					int port = CommonDomain.CONST_SYNC_OLD_FK_PORT;
					ShellUtils shell = new ShellUtils(host, username, password, port);
					// FKSettle [模块号] [商户号] [终端号]
					String command = CommonDomain.CONST_SYNC_OLD_FK_SETTLE_COMMAND + " " + moduleId + " " + merchantId + " " + terminalId;
					log.info(command);
					shell.connect();
					shell.execCmd(command);
					request.setAttribute("msg", "结算脚本已执行");
				}
			}

			// 取得路径
			ActionForward actionForward = mapping.findForward("syncParam");
			if (StringUtil.isNull(syncParam)) {
				actionForward = mapping.findForward("editToQuery");
			}
			String fPath = actionForward.getPath();
			pathForward = fPath;
			log.info("EdcTerminalOrmDispatchAction.syncParam()结束调用：同步旧福卡参数。");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.syncParam()同步旧福卡参数，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 签到 **/
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalOrmDispatchAction.syncParam()开始调用：同步旧福卡参数。");
			EdcTerminalOrmLogicInterface logic = (EdcTerminalOrmLogicInterface) getBean("edcTerminalInfoOrmLogic");

			// 按商户编号查询
			String merchantId = request.getParameter("_bankmerchantId");
			String terminalId = request.getParameter("_bankterminalId");
			String syncParam = request.getParameter("syncParam");
			if (StringUtil.isNull(merchantId) || StringUtil.isNull(terminalId)) {
				request.setAttribute("msg", "请填写银行商户号和银行终端号。");
			} else {
				EdcTerminalOrm orm = logic.queryModualBy(merchantId, terminalId);
				String moduleId = orm.getModuleId();
				if (StringUtil.isNull(moduleId)) {
					request.setAttribute("msg", "银行终端号不存在。");
				} else {
					String host = CommonDomain.CONST_SYNC_OLD_FK_HOST;
					String username = CommonDomain.CONST_SYNC_OLD_FK_USER;
					String password = CommonDomain.CONST_SYNC_OLD_FK_PWD;
					int port = CommonDomain.CONST_SYNC_OLD_FK_PORT;
					ShellUtils shell = new ShellUtils(host, username, password, port);
					// FKLogin [模块号] [交易类型--84] [商户号] [终端号]
					String command = CommonDomain.CONST_SYNC_OLD_FK_LOGIN_COMMAND + " " + moduleId + " 84 " + merchantId + " " + terminalId;
					log.info(command);
					shell.connect();
					shell.execCmd(command);
					request.setAttribute("msg", "签到脚本已执行。");
				}
			}

			// 取得路径
			ActionForward actionForward = mapping.findForward("syncParam");
			if (StringUtil.isNull(syncParam)) {
				actionForward = mapping.findForward("editToQuery");
			}
			String fPath = actionForward.getPath();
			pathForward = fPath;
			log.info("EdcTerminalOrmDispatchAction.syncParam()结束调用：同步旧福卡参数。");
		} catch (Exception e) {
			log.info("EdcTerminalOrmDispatchAction.syncParam()同步旧福卡参数，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}
}
