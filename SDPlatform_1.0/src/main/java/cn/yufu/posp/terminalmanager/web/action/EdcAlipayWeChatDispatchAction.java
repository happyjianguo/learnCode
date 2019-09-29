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
	 * 支付宝、微信终端管理界面
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
	public org.apache.struts.action.ActionForward queryEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChat()开始调用：查找符合条件的记录。");

			// 得到Logic(就是service层)
			EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {  
				cPage = Integer.parseInt(request.getParameter(dParams[0])); //dpPageName
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]); //dpPageOrder
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
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

			// 设置查询条件
			EdcAlipayWeChat queryModel = new EdcAlipayWeChat();
			EdcAlipayWeChatForm queryForm = (EdcAlipayWeChatForm) form;
			// 按商户编号查询
			String merchantId = queryForm.getQueryMerchantId();
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);
				}
			}
			// 按终端编号查询
			String terminalId = queryForm.getQueryTerminalId();
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}
			}
			//获取状态
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
			
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChat()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChat()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除终端基本资料 */
	public org.apache.struts.action.ActionForward deleteEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		List<EdcAlipayWeChat> keysList = new ArrayList<EdcAlipayWeChat>();
		try {
			log.info("EdcAlipayWeChatDispatchAction.deleteEdcAlipayWeChat()开始调用：删除记录。");
			
			UserData ud = getSessionUserData(request);
			
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("删除记录 条件参数==" + strId);

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
	
			log.info("EdcAlipayWeChatDispatchAction.deleteEdcAlipayWeChat()结束调用：删除记录。");
		} catch (Exception e) {

			log.info("EdcAlipayWeChatDispatchAction.deleteEdcAlipayWeChat()删除记录，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建
	 */
	public org.apache.struts.action.ActionForward createEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			log.info("EdcAlipayWeChatDispatchAction.createEdcNewfkterminalOrm()开始调用：创建一条新的信息。");

			UserData ud = getSessionUserData(request);
			// 得到Logic
			EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
			// 得到Form
			EdcAlipayWeChatForm edcAlipayWeChatForm = (EdcAlipayWeChatForm) form;
			// 新建一个Model
			EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();

			if (ud != null) {

				BeanUtils.copyProperties(edcAlipayWeChat, edcAlipayWeChatForm);

				logic.createItem(edcAlipayWeChat, ud);
			}

			log.info("EdcAlipayWeChatDispatchAction.createEdcNewfkterminalOrm()结束调用：创建一条新的信息。");

		} catch (Exception e) {
			log.info("EdcAlipayWeChatDispatchAction.createEdcNewfkterminalOrm()创建一条新的信息，出现异常。");
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
	public org.apache.struts.action.ActionForward queryEdcAlipayWeChatByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChatByKey()开始调用：显示修改终端资料设定信息界面。");
			EdcAlipayWeChatForm newForm = (EdcAlipayWeChatForm) form;
			// System.out.println("form::" + form);
			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				log.info("EdcAlipayWeChatDispatchAction.queryEdcAlipayWeChatByKey()线束调用：显示新增终端资料设定信息界面。");
				return mapping.findForward("add");
			}
			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("#");

			// 得到Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();

			if (tt.length == 4) {
				edcAlipayWeChat.setMerchantId(tt[0].trim());
				edcAlipayWeChat.setTerminalId(tt[1].trim());
				edcAlipayWeChat.setModuleId(tt[2].trim());
			}

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
			// 得到Form
			EdcAlipayWeChatForm edcAlipayWeChatForm = (EdcAlipayWeChatForm) form;

			map = logic.findItemByKey(edcAlipayWeChat, ud);

			EdcAlipayWeChat model = (EdcAlipayWeChat) map.get("Infolist");

			BeanUtils.copyProperties(edcAlipayWeChatForm, model);

			log.info("EdcAlipayWeChatDispatchAction.queryEdcTerminalByKey()结束调用：显示修改终端资料设定信息界面。");

		} catch (Exception e) {

			log.info("EdcAlipayWeChatDispatchAction.queryEdcTerminalByKey()显示修改终端资料设定信息界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}
	
	/**
	 * 验证商户编号、终端号 和模块ID三者的唯一性
	 */
	public org.apache.struts.action.ActionForward checkMerTerMod(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = null;
		try {
			log.info("EdcAlipayWeChatDispatchAction.checkMerTerMod()开始调用：验证商户编号、终端号 和模块ID三者的唯一性。");
			
			String merchantId = request.getParameter("merchantId");
			String terminalId = request.getParameter("terminalId");
			String moduleId = request.getParameter("moduleId");
			
			//封装
			EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();
			edcAlipayWeChat.setMerchantId(merchantId);
			edcAlipayWeChat.setTerminalId(terminalId);
			edcAlipayWeChat.setModuleId(moduleId);
			
			UserData ud = getSessionUserData(request);

			// 得到Logic
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

			log.info("EdcAlipayWeChatDispatchAction.checkMerTerMod()开始调用：验证商户编号、终端号 和模块ID三者的唯一性。");

		} catch (Exception e) {

			log.info("EdcAlipayWeChatDispatchAction.checkMerTerMod()开始调用：验证商户编号、终端号 和模块ID三者的唯一性。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
	
	/**
	 * 修改终端资料设定
	 */
	public org.apache.struts.action.ActionForward saveEdcAlipayWeChat(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// ??????Model
		EdcNewfkterminalOrm edcTerminal = new EdcNewfkterminalOrm();
		try {
			log.info("EdcAlipayWeChatDispatchAction.saveEdcNewfkterminalOrm()开始调用：修改终端资料设定。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcNewfkterminalOrmLogicInterface logic = (EdcNewfkterminalOrmLogicInterface) getBean("edcNewfkerminalInfoOrmLogic");
			// 得到Form
			EdcNewfkterminalOrmForm edcTerminalForm = (EdcNewfkterminalOrmForm) form;

			if (ud != null) {
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcAlipayWeChatDispatchAction.saveEdcNewfkterminalOrm()结束调用：修改终端资料设定。");
		} catch (Exception e) {
			log.info("EdcAlipayWeChatDispatchAction.saveEdcNewfkterminalOrm()修改终端资料设定，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

	// 导出
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
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
			cell.setCellValue("请按模板整理数据！商户编号、商户终端编号、银行商户号、模块ID、批次号、工作密钥密文MAC_KEY必须均存在！");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// 行高
			sheet.setDefaultRowHeightInPoints(15);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = {"商户编号","商户终端编号","银行商户号","模块ID","系统流水号","银行流水号","批次号","工作密钥密文MAC_KEY"};
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
			// 对文件名进行编码
			String headName = "支付宝/微信终端导入模板";
			String fileName = POIUtils.encodeFileName(request, headName);
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
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
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("开始导入数据");
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// 文件大小不能超过5M
		try {
			writer = response.getWriter();
			EdcAlipayWeChatUploadForm uf = (EdcAlipayWeChatUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "上传失败！");
				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
			} else {
				List<EdcAlipayWeChat> edcAlipayWeChatList = new ArrayList<EdcAlipayWeChat>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// for (int i = 0; i <row.getLastCellNum(); i++) {
					// System.out.print(row.getCell(i)+"\t");
					// }
					// 新建一个Model
					EdcAlipayWeChat edcAlipayWeChat = new EdcAlipayWeChat();
					//封装数据
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
					
					edcAlipayWeChat.setPinFmt("2");//PIN算法标识 1: ANSI X98格式（不带主账号） 2: ANSI X98算法（带主账号）
					edcAlipayWeChat.setEncMethod("6");//加密算法 0: DES 6: 3DES  7:MD5
					edcAlipayWeChat.setMacFlag("1");//MAC运算标志 0: 特例终端不做 1: 正常
					edcAlipayWeChat.setSettStatus("0");//结帐状态 0: 正常交易状态 1: 需要结帐 2: 结帐进行中
					edcAlipayWeChat.setLogonStatus("1");//受理机构POS终端签到状态 0: 签退状态 1: 已签到 2:签到异常
					edcAlipayWeChat.setFlag("1");//开通标志 1－正常开通  0－未开通
					
					edcAlipayWeChatList.add(edcAlipayWeChat);
				}
				// 得到Logic
				EdcAlipayWeChatLogicInterface logic = (EdcAlipayWeChatLogicInterface) getBean("edcAlipayWeChatInfoLogic");
				logic.saveUploadItem(edcAlipayWeChatList, ud);
				// 保存
				jsonObj.put("result", "操作成功！ 已成功导入 " + edcAlipayWeChatList.size() + " 条记录！");
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
	
}