package cn.yufu.posp.terminalmanager.web.action;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import cn.yufu.posp.merchant.domain.logic.TabBusRoleMenuLogicInterface;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcTerminalLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalForm;
import cn.yufu.posp.terminalmanager.web.form.EdcTerminalUploadForm;
import net.sf.json.JSONObject;


/**
 * @author zhouya 终端资料设定
 * 
 */
public class EdcTerminalDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTerminal");

	public EdcTerminalDispatchAction() {
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
			ActionForward actionForward = mapping.findForward("showAllEdcTerminal");
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
	public org.apache.struts.action.ActionForward queryEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()开始调用：查找符合条件的记录。");

			// 得到Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");

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
			EdcTerminal queryModel = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					id.setMerchantId(merchantId);

				}

			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					id.setTerminalId(terminalId);
				}

			}

			queryModel.setId(id);

			// 按终端类型查询
			String softVer = request.getParameter("_softVer");
			if (terminalId != null) {
				queryModel.setSoftVer(softVer);
			}
			
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllEdcTerminal");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcTerminalDispatchAction.queryEdcTerminal()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除终端基本资料 */
	public org.apache.struts.action.ActionForward deleteEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		List<EdcTerminal> keysList = new ArrayList<EdcTerminal>();
		try {
			log.info("EdcTerminalDispatchAction.deleteEdcTerminal()开始调用：删除记录。");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("删除记录 条件参数==" + strId);

				if (tt.length == 16) {

					EdcTerminal edcTerminal = new EdcTerminal();
					EdcTerminalId id = new EdcTerminalId();
					id.setMerchantId(tt[0].trim());
					id.setTerminalId(tt[1].trim());
					edcTerminal.setId(id);

					keysList.add(edcTerminal);
				}
			}
			if (keysList.size() > 0) {
				EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcTerminalDispatchAction.deleteEdcTerminal()结束调用：删除记录。");
		} catch (Exception e) {

			log.info("EdcTerminalDispatchAction.deleteEdcTerminal()删除记录，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteEdcTerminal");
	}

	/**
	 * 跳转到输入页面
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("edcTerminalSr");
	}

	/**
	 * 创建
	 */
	public org.apache.struts.action.ActionForward createEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTerminalDispatchAction.createEdcTerminal()开始调用：创建一条新的信息。");

			UserData ud = getSessionUserData(request);
			// 得到Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// 得到Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			// 新建一个Model
			EdcTerminal edcTerminal = new EdcTerminal();

			if (ud != null) {
				if(ud.getUserId().length()>6){
					edcTerminalForm.setUpdateOper(ud.getUserId().substring(0,6));
				}else{
					edcTerminalForm.setUpdateOper(ud.getUserId());
				}
				edcTerminalForm.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
				edcTerminalForm.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.createItem(edcTerminal, ud);
			}

			log.info("EdcTerminalDispatchAction.createEdcTerminal()结束调用：创建一条新的信息。");

		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.createEdcTerminal()创建一条新的信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createEdcTerminal");
	}

	/**
	 * 显示修改终端资料设定信息界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcTerminalByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTerminalDispatchAction.queryEdcTerminalByKey()开始调用：显示修改终端资料设定信息界面。");
			System.out.println("0-----------EdcTerminalDispatchAction.queryEdcTerminalByKey()开始调用：显示修改终端资料设定信息界面。");

			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("#");
			System.out.println("1--------"+tt.toString()+"---分解参数");

			// 得到Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcTerminal edcTerminal = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();

			if (tt.length == 16) {
				id.setMerchantId(tt[0].trim());
				id.setTerminalId(tt[1].trim());
				edcTerminal.setId(id);
				System.out.println("2-----------设置查询参数");

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
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// 得到Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			System.out.println("3-----------logic.findItemByKey---------"+edcTerminalForm.toString()+"--------before");
			map = logic.findItemByKey(edcTerminal, ud);
			System.out.println("4-----------logic.findItemByKey---"+map.toString()+"--------------end");
			EdcTerminal model = (EdcTerminal) map.get("Infolist");
			System.out.println("5-----------Infolist-"+model.toString()+"----------------before");
			BeanUtils.copyProperties(edcTerminalForm, model);
//			edcTerminalForm =  edcTerminalModelToFrom(model);
			System.out.println("6-------"+edcTerminalForm.toString()+"----EdcTerminalDispatchAction.queryEdcTerminalByKey()结束调用：显示修改终端资料设定信息界面。");
			log.info("EdcTerminalDispatchAction.queryEdcTerminalByKey()结束调用：显示修改终端资料设定信息界面。");

		} catch (Exception e) {

			log.info("EdcTerminalDispatchAction.queryEdcTerminalByKey()显示修改终端资料设定信息界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("queryEdcTerminalByKey");
	}

	/**
	 * EdcTerminal TO EdcTerminalForm
	 * @param model
	 * @return
	 */
	public EdcTerminalForm edcTerminalModelToFrom(EdcTerminal model) {
		EdcTerminalForm froms = new EdcTerminalForm();
		froms.setId(model.getId());
		/**
		 * EDC设备规格说明
		 * <p>
		 * 终端所配置的EDC设备的规格、厂家、性能等说明性文字，以区别各种不同厂家型号的EDC设备。
		 * </p>
		 **/
		froms.setEdcDoc(model.getEdcDoc());
		/**
		 * 打印机类型
		 * <p>
		 * EDC终端所配置的打印机型号
		 * </p>
		 */
		froms.setPrinterType(model.getPrinterType());
		/** PIN PAD类型 */
		froms.setPinpadType(model.getPinpadType());
		/** 安装日期 YYYYMMDD */
		froms.setSetDate(model.getSetDate());
		/**
		 * 安装地点
		 * <p>
		 * EDC所放置商户位置的说明性文字，便于维护时参考。
		 * </p>
		 */
		froms.setSetAddr(model.getSetAddr());
		/**
		 * EDC终端状态
		 * <p>
		 * Y’-正常,’N’-冻结<br>
		 * <br>
		 * “冻结”标志的EDC终端发上的交易拒绝<br>
		 * </p>
		 */
		froms.setTerminalStat(model.getTerminalStat());
		/**
		 * EDC设备型号
		 * <p>
		 * 约定的EDC设备类型标识串，据此刻判别此EDC使用哪种类型的格式进行解包、组包。
		 * <p>
		 */
		froms.setEdcType(model.getEdcType());
		/** EDC软件版本 */
		froms.setSoftVer(model.getSoftVer());
		/** 参数下载标志 */
		froms.setDownloadFlag(model.getDownloadFlag());
		/** 参数下载模式 */
		froms.setDownloadMode(model.getDownloadMode());
		/** 创建者 */
		froms.setUpdateOper(model.getUpdateOper());
		/** 创建日期 YYYYMMDD */
		froms.setUpdateDate(model.getUpdateDate());
		/** 创建时间 hhmmss */
		froms.setUpdateTime(model.getUpdateTime());
		/** 终端状态 ---页面显示用--- */
		froms.setCh_terminalStat(model.getCh_terminalStat());
		/** 日前和时间组合 ---页面显示用--- */
		froms.setCh_dateAndTime(model.getCh_dateAndTime());
		/** 业务角色 ---页面显示用--- */
		froms.setBusRoleId(model.getBusRoleId());
		/** 业务角色中文名字 ---页面显示用--- */
		froms.setBusRoleName(model.getBusRoleName());
		return froms;
	}
	/**
	 * 显示终端资料信息界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryDetailByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTerminalDispatchAction.queryDetailByKey()开始调用：显示终端资料信息界面。");
			
			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("\\|");
			
			// 得到Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcTerminal edcTerminal = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();
			
			if (tt.length == 16) {
				id.setMerchantId(tt[0].trim());
				id.setTerminalId(tt[1].trim());
				edcTerminal.setId(id);
			}
			
			UserData ud = getSessionUserData(request);
			
			// 得到Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// 得到Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			
			map = logic.findItemByKey(edcTerminal, ud);
			
			EdcTerminal model = (EdcTerminal) map.get("Infolist");
			
			BeanUtils.copyProperties(edcTerminalForm, model);
			
			log.info("EdcTerminalDispatchAction.queryDetailByKey()结束调用：显示终端资料信息界面。");
			
		} catch (Exception e) {
			
			log.info("EdcTerminalDispatchAction.queryDetailByKey()显示终端资料信息界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		
		return mapping.findForward("queryDetail");
	}

	/**
	 * 修改终端资料设定
	 */
	public org.apache.struts.action.ActionForward saveEdcTerminal(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// 新建一个Model
		EdcTerminal edcTerminal = new EdcTerminal();
		EdcTerminalId id = new EdcTerminalId();
		try {
			log.info("EdcTerminalDispatchAction.saveEdcTerminal()开始调用：修改终端资料设定。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
			// 得到Form
			EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;

			if (ud != null) {

				id.setMerchantId(edcTerminalForm.getId().getMerchantId());
				id.setTerminalId(edcTerminalForm.getId().getTerminalId());

				edcTerminal.setId(id);

				edcTerminal.setTerminalStat(edcTerminalForm.getTerminalStat());
				edcTerminal.setEdcType(edcTerminalForm.getEdcType());
				edcTerminal.setSoftVer(edcTerminalForm.getSoftVer());
				edcTerminal.setDownloadFlag(edcTerminalForm.getDownloadFlag());
				edcTerminal.setDownloadMode(edcTerminalForm.getDownloadMode());
				edcTerminal.setEdcDoc(edcTerminalForm.getEdcDoc());
				edcTerminal.setPrinterType(edcTerminalForm.getPrinterType());
				edcTerminal.setPinpadType(edcTerminalForm.getPinpadType());
				edcTerminal.setSetDate(edcTerminalForm.getSetDate());
				edcTerminal.setSetAddr(edcTerminalForm.getSetAddr());
				edcTerminal.setUpdateOper(ud.getUserId());
				edcTerminal.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
				edcTerminal.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

				//创建数据到终端业务角色信息表
				
				edcTerminal.setBusRoleId(edcTerminalForm.getBusRoleId());
				
				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcTerminalDispatchAction.saveEdcTerminal()结束调用：修改终端资料设定。");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.saveEdcTerminal()修改终端资料设定，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveEdcTerminal");
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
			cell.setCellValue("请按模板整理数据！商户编号、终端编号、终端状态(Y-正常,N-冻结)必填且商户号必须已存在！终端类型只能为普通终端(common)");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// 行高
			sheet.setDefaultRowHeightInPoints(15);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "商户编号", "终端编号", "终端状态", "设备型号", "设备规格说明", "打印机类型", "终端类型", "安装日期", "安装地点" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createCell(row, (short) 0, "123456789012345");
			POIUtils.createCell(row, (short) 1, "00000001");
			POIUtils.createCell(row, (short) 2, "Y");
			POIUtils.createCell(row, (short) 3, "STD");
			POIUtils.createCell(row, (short) 4, "");
			POIUtils.createCell(row, (short) 5, "950");
			POIUtils.createCell(row, (short) 6, "common");
			POIUtils.createCell(row, (short) 7, "20141001");
			POIUtils.createCell(row, (short) 8, "福卡中心");
			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("商户终端导入模板", "UTF-8");
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
			EdcTerminalUploadForm uf = (EdcTerminalUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			List<String> list=new ArrayList<String>();
			List<String> list1=new ArrayList<String>();
			List<Integer> list2=new ArrayList<Integer>();
			List<Integer> list3=new ArrayList<Integer>();
			label1:
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "上传失败！");
				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
			} else {
				List<EdcTerminal> edcTerminalList = new ArrayList<EdcTerminal>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// for (int i = 0; i <row.getLastCellNum(); i++) {
					// System.out.print(row.getCell(i)+"\t");
					// }
					// 新建一个Model
					EdcTerminal edcTerminal = new EdcTerminal();
					edcTerminal.setUpdateOper(ud.getUserId());
					edcTerminal.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
					edcTerminal.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
					EdcTerminalId edcTerminalId = new EdcTerminalId();
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					if (StringUtil.isNull(merchantId)) {
						break;
					}
					edcTerminalId.setMerchantId(merchantId);
					edcTerminalId.setTerminalId((POIUtils.getStringFromExcelCell(row.getCell(1))));
					edcTerminal.setId(edcTerminalId);
					edcTerminal.setTerminalStat(POIUtils.getStringFromExcelCell(row.getCell(2)));
					edcTerminal.setEdcType(POIUtils.getStringFromExcelCell(row.getCell(3)));
					edcTerminal.setEdcDoc(POIUtils.getStringFromExcelCell(row.getCell(4)));
					edcTerminal.setPrinterType(POIUtils.getStringFromExcelCell(row.getCell(5)));
					edcTerminal.setSoftVer("common");
					edcTerminal.setSetDate(POIUtils.getStringFromExcelCell(row.getCell(7)));
					edcTerminal.setSetAddr(POIUtils.getStringFromExcelCell(row.getCell(8)));
					edcTerminal.setDownloadMode("0");
					edcTerminalList.add(edcTerminal);
				}
				int temp=0,temp1=0,temp2=0;
				for(int i = 0; i < edcTerminalList.size(); i++){
					if((edcTerminalList.get(i).getId().getTerminalId().indexOf("A")!=0)&&(edcTerminalList.get(i).getEdcType().indexOf("APP")==0)||
							(edcTerminalList.get(i).getId().getTerminalId().indexOf("A")==0)&&(edcTerminalList.get(i).getEdcType().indexOf("APP")!=0)){
						temp=i;
						list.add(String.valueOf(temp+3)); 
					}
					 for(int j=edcTerminalList.size()-1;j>i;j--){
		                    if(edcTerminalList.get(i).getId().getTerminalId().equals(edcTerminalList.get(j).getId().getTerminalId())&&
		                    		edcTerminalList.get(i).getId().getMerchantId().equals(edcTerminalList.get(j).getId().getMerchantId())){
		                    	temp1=j;
		                    	temp2=i;
		                    	list1.add(String.valueOf(temp2+3)); 
		                    	list1.add(String.valueOf(temp1+3)); 
		                    	//去除List<Object>集合中重复的元素,目的：减少循环次数。
		                    	edcTerminalList.remove(j);
		                    }
		                }
					
				}
				
				if(list.size()>0){
					for(int j = 0;j < 1;){
						jsonObj.put("result", "操作失败！Excel文件中第"+list+"行的终端编号和终端设备型号之间规则不匹配");
						break label1;
					}
				}
				if(list1.size()>0){
					for(int j = 0;j < list1.size();j++){
						list2.add(Integer.valueOf(list1.get(j)));
						if(j+1>=list1.size()){
							Collections.sort(list2);
					        for(Iterator<Integer> it=list2.iterator();it.hasNext();){
					            Object obj=it.next();
					            if(!(list3.contains(obj))){
					            	list3.add((Integer) obj);
					            }
					        }
					        list2.clear();
					        list2.addAll(list3);
							jsonObj.put("result", "操作失败！Excel文件中第"+list2+"行的商户编号和终端编号相同违反唯一索引规则");
							break label1;
						}
					}
				}
				// 得到Logic
				EdcTerminalLogicInterface edcTerminaLogic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
				edcTerminaLogic.saveUploadItem(edcTerminalList, ud);
				// 保存
				jsonObj.put("result", "操作成功！ 已成功导入 " + edcTerminalList.size() + " 条记录！");
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

	/*** 联机处理要求 **/
	public org.apache.struts.action.ActionForward queryEdcTerminalMode(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()开始调用：查找符合条件的记录。");

			// 得到Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");

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
			EdcTerminal queryModel = new EdcTerminal();
			EdcTerminalId id = new EdcTerminalId();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					id.setMerchantId(merchantId);

				}

			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					id.setTerminalId(terminalId);
				}

			}
			String _downloadMode = request.getParameter("_downloadMode");
			if (_downloadMode != null) {
				if (!_downloadMode.trim().equals("")) {
					queryModel.setDownloadMode(_downloadMode);
				}
				
			}
			queryModel.setId(id);
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));
			request.setAttribute("pageInfoResult", pageInfo);
			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllEdcTerminalMode");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.queryEdcTerminal()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/*** 联机处理要求跳转 **/
	public org.apache.struts.action.ActionForward toQueryEdcTerminalMode(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		PageInfoModel pageInfo = new PageInfoModel();
		request.setAttribute("pageInfoResult", pageInfo);
		return mapping.findForward("showAllEdcTerminalMode");
	}

	/*** 联机处理要求列表 **/
	public org.apache.struts.action.ActionForward queryEdcTerminalModeList(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		PageInfoModel pageInfo = new PageInfoModel();
		return mapping.findForward("showEdcTerminalModeList");
	}

	/**
	 * 联机处理要求设定
	 */
	public org.apache.struts.action.ActionForward setEdcTerminalMode(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		try {
			log.info("EdcTerminalDispatchAction.setEdcTerminalMode()开始调用：联机处理要求设定。");

			// 得到Logic
			EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			String mode = request.getParameter("mode");

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");
				if (tt.length == 16) {

					EdcTerminal edcTerminal = new EdcTerminal();
					EdcTerminalId id = new EdcTerminalId();
					id.setMerchantId(tt[0].trim());
					id.setTerminalId(tt[1].trim());
					edcTerminal.setId(id);
					map = logic.findItemByKey(edcTerminal, ud);
					EdcTerminal model = (EdcTerminal) map.get("Infolist");
					model.setDownloadMode(mode);
					model.setUpdateOper(ud.getUserId());
					model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
					model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
					logic.saveItem(model, ud);

				}
			}

			log.info("EdcTerminalDispatchAction.setEdcTerminalMode()结束调用：联机处理要求设定。");
		} catch (Exception e) {
			log.info("EdcTerminalDispatchAction.setEdcTerminalMode()联机处理要求设定，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("queryEdcTerminalMode");
	}
	
	/**
	 * 获取业务角色菜单列表
	 */
	public org.apache.struts.action.ActionForward findBusRoleList(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TabBusRoleMenuAction.findBusRoleList()开始调用:获取业务角色菜单列表。" + getSessionUserData(request).getUserId());
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			UserData ud = getSessionUserData(request);
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
			List<TabBusRoleMenuModel> list = logic.findBusRoleList(ud);
			String areaStr = "";
			for (int i = 0; i < list.size(); i++) {
				TabBusRoleMenuModel areaCodeInfo = list.get(i);
				areaStr = areaStr + areaCodeInfo.getBusRoleName().trim()+ "|"+ areaCodeInfo.getBusRoleId().trim() + "|";
			}
			
			// 存在
			if((areaStr!="")&&(areaStr!=null)){
				out.print(areaStr);		
			}else{
				out.print(false);//不存在
			}

			log.info("TabBusRoleMenuAction.findBusRoleList()结束调用:获取业务角色菜单列表。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.findBusRoleList()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward(null);

	}
}
