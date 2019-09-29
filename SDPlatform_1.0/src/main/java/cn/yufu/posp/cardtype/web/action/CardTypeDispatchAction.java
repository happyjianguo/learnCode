package cn.yufu.posp.cardtype.web.action;

import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;

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
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.POIUtils;
import cn.yufu.core.common.util.StringUtil;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.cardtype.domain.logic.CardTypeLogicInterface;
import cn.yufu.posp.cardtype.domain.model.CardType;
import cn.yufu.posp.cardtype.web.form.CardTypeForm;
import cn.yufu.posp.cardtype.web.form.CardTypeUploadForm;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.route.domain.logic.RouteLogicInterface;
import cn.yufu.posp.route.domain.model.CardRoute;
import cn.yufu.posp.route.domain.model.CardRouteId;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class CardTypeDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("cardType");

	/**
	 * @roseuid 44BAF7150119
	 */
	public CardTypeDispatchAction() {

	}

	/**
	 * 查找信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("CardTypeDispatchAction.queryCardType()开始调用：查找");

			// 得到Logic
			CardTypeLogicInterface jgLogic = (CardTypeLogicInterface) getBean("cardTypeLogic");

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
					pageInfo.setOrderField("cardId");
				else if (sortField.equals("2"))
					pageInfo.setOrderField("cardNoId");
				else if (sortField.equals("3"))
					pageInfo.setOrderField("cardName");
				else if (sortField.equals("4"))
					pageInfo.setOrderField("cardType");
				else if (sortField.equals("5"))
					pageInfo.setOrderField("bankType");
				else if (sortField.equals("6"))
					pageInfo.setOrderField("bankCode");

			}

			// 设置查询条件
			CardType queryModel = new CardType();
			// CardTypeId queryModelId = new CardTypeId();
			// 按车牌号查询
			String jgId = request.getParameter("_cardId");
			if (jgId != null) {
				if (!jgId.trim().equals("")) {
					queryModel.setCardId(jgId);
				}

			}
			String jgName = request.getParameter("_cardName");
			if (jgName != null) {
				if (!jgName.trim().equals("")) {
					queryModel.setCardName(jgName);
				}

			}
			// queryModel.setId(queryModelId);

			pageInfo = jgLogic.queryCardType(queryModel, pageInfo, getSessionUserData(request));

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

			List allist = new ArrayList();

			CardType newModel = null;
			Banktype banktype = null;
			Cardtype cardtype = null;
			// System.out.println("pageInfo.getResultItems().size()"+pageInfo.getResultItems().size());
			for (int i = 0; i < pageInfo.getResultItems().size(); i++) {

				newModel = (CardType) pageInfo.getResultItems().get(i);
				// System.out.println(newModel);
				if (newModel != null) {

					for (int k = 0; k < banktypeList.size(); k++) {
						banktype = (Banktype) banktypeList.get(k);
						if (banktype.getBankType().trim().equals(newModel.getBankType().trim()))
							newModel.setBankTypeName(banktype.getTypeName().trim());
					}

					for (int j = 0; j < cardtypeList.size(); j++) {
						cardtype = (Cardtype) cardtypeList.get(j);
						if (cardtype.getId().getCardType().trim().equals(newModel.getCardType()))
							newModel.setCardTypeName(cardtype.getId().getTypeName());
					}

					allist.add(newModel);
				}
			}
			pageInfo.getResultItems().clear();
			pageInfo.getResultItems().addAll(allist);
			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllCardType");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("CardTypeDispatchAction.queryCardType()开始调用：查找信息");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDispatchAction.queryCardType()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除信息
	 */
	public org.apache.struts.action.ActionForward deleteCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardTypeDispatchAction.deleteCardType()开始调用：删除信息。");
			UserData ud = getSessionUserData(request);
			CardTypeLogicInterface jgLogic = (CardTypeLogicInterface) getBean("cardTypeLogic");
			String keyStr = request.getParameter("selectItems");
			// List<String> keysList= new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			// System.out.println(InfoIdStrs.length);
			CardType tr = null;
			// CardTypeId tri = null;
			for (int i = 0; i < InfoIdStrs.length; i++) {
				// tri=new CardTypeId();
				tr = new CardType();
				String[] str = InfoIdStrs[i].split("_");
				tr.setCardId(str[0]);
				tr.setCardLen(str[1]);
				tr.setCardNoId(str[2]);
				tr.setCardType(str[3]);
				tr.setCardName(str[4]);
				tr.setBankType(str[5]);
				tr.setBankCode(str[6]);
				tr.setCardMode(str[7]);
				tr.setCardIdTrack(str[8]);
				tr.setCardIdOff(str[9]);
				tr.setCardNoTrack(str[10]);
				tr.setCardNoOff(str[11]);
				tr.setExpDateOff(str[12]);
				tr.setPinMode(str[13]);
				tr.setInputMode(str[14]);
				tr.setChkCardValid(str[15]);
				tr.setIfLocal(str[16]);
				tr.setIfOffline(str[17]);
				tr.setUpdateOper(str[18]);
				tr.setUpdateDate(str[19]);
				tr.setUpdateTime(str[20]);
				// tr.setId(tri);
				// System.out.println(tr);
				jgLogic.deleteCardType(tr, ud);
				// keysList.add(InfoIdStrs[i]);
			}
			// if(keysList.size()>0){
			// 得到Logic

			// }
			log.info("CardTypeDispatchAction.deleteCardType()结束调用：删除信息。");
		} catch (Exception e) {
			log.error("CardTypeDispatchAction.deleteCardType()结束调用：删除信息。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("createCardType");
	}

	/**
	 * 跳转到输入页面
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

		List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
		List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

		request.setAttribute("bankTypeList", banktypeList);

		request.setAttribute("cardTypeList", cardtypeList);
		return mapping.findForward("addCardType");
	}

	/**
	 * 创建新的信息
	 */
	public org.apache.struts.action.ActionForward createCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardTypeDispatchAction.createCardType()开始调用:创建新的信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			CardTypeLogicInterface officeSuppliesLogic = (CardTypeLogicInterface) getBean("cardTypeLogic");
			// 得到Form
			CardTypeForm jgForm = (CardTypeForm) form;
			// 新建一个Model
			CardType jgModel = new CardType();
			// CardTypeId jgModelid = new CardTypeId();
			BeanUtils.copyProperties(jgModel, jgForm);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat df1 = new SimpleDateFormat("hhmmss");
			Date dt = new Date();
			jgModel.setUpdateOper(ud.getUserId());
			jgModel.setUpdateDate(df.format(dt));
			jgModel.setUpdateTime(df1.format(dt));
			// jgModel.setId(jgModelid);
			// System.out.println("jgModelidjgModelidjgModelid"+jgModelid.getCardType());
			// System.out.println("jgModelidjgModelidjgModelid"+jgModelid.getBankType());
			// 创建新的信息
			officeSuppliesLogic.createCardType(jgModel, ud);

			log.info("CardTypeDispatchAction.createCardType()结束调用:创建新的信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDispatchAction.createCardType()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// /throw new
			// OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardType");
	}

	/**
	 * 显示修改信息界面
	 */
	public org.apache.struts.action.ActionForward queryCardTypeByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String id = request.getParameter("jgId");
		CardTypeForm jgForm = (CardTypeForm) form;
		jgForm.setIid(id);
		if (id != null) {
			String[] str = id.split("_");
			jgForm.setCardId(str[0]);
			jgForm.setCardLen(str[1]);
			jgForm.setCardNoId(str[2]);
			jgForm.setCardType(str[3]);
			jgForm.setCardName(str[4]);
			jgForm.setBankType(str[5]);
			jgForm.setBankCode(str[6]);
			jgForm.setCardMode(str[7]);
			jgForm.setCardIdTrack(str[8]);
			jgForm.setCardIdOff(str[9]);
			jgForm.setCardNoTrack(str[10]);
			jgForm.setCardNoOff(str[11]);
			jgForm.setExpDateOff(str[12]);
			jgForm.setPinMode(str[13]);
			jgForm.setInputMode(str[14]);
			jgForm.setChkCardValid(str[15]);
			jgForm.setIfLocal(str[16]);
			jgForm.setIfOffline(str[17]);
			jgForm.setUpdateOper(str[18]);
			jgForm.setUpdateDate(str[19]);
			jgForm.setUpdateTime(str[20]);

		}
		EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

		List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
		List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

		List<Banktype> blist = new ArrayList<Banktype>();
		Banktype bbb = null;
		boolean bb = true;
		for (int i = 0; i < banktypeList.size(); i++) {
			bbb = (Banktype) banktypeList.get(i);
			if (bb) {
				if (bbb.getBankType().trim().equals(jgForm.getBankType().trim())) {
					bb = false;
					blist.add(0, bbb);
				} else {
					blist.add(bbb);
				}
			} else {
				blist.add(bbb);
			}
		}
		List<Cardtype> clist = new ArrayList<Cardtype>();
		Cardtype ccc = null;
		bb = true;
		for (int i = 0; i < cardtypeList.size(); i++) {
			ccc = (Cardtype) cardtypeList.get(i);
			if (bb) {
				if (ccc.getId().getCardType().trim().equals(jgForm.getCardType().trim())) {
					clist.add(0, ccc);
				} else {
					clist.add(ccc);
				}
			} else {
				clist.add(ccc);
			}
		}

		request.setAttribute("bankTypeList", blist);
		request.setAttribute("cardTypeList", clist);
		return mapping.findForward("showModifyCardType");

	}

	/**
	 * 保存卡类型信息
	 */
	public org.apache.struts.action.ActionForward saveCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("开始调用:创建卡类型信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			CardTypeLogicInterface jgLogic = (CardTypeLogicInterface) getBean("cardTypeLogic");

			CardTypeForm jgForm = (CardTypeForm) form;
			// 新建一个Model
			CardType jgModel = new CardType();
			BeanUtils.copyProperties(jgModel, jgForm);

			// 记录维护信息的三个字段（每个表都有）
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat df1 = new SimpleDateFormat("hhmmss");
			Date dt = new Date();
			jgModel.setUpdateOper(ud.getUserId());
			jgModel.setUpdateDate(df.format(dt));
			jgModel.setUpdateTime(df1.format(dt));

			// 创建新的信息
			jgLogic.saveCardType(jgModel, ud);

			log.info("卡类型管理的修改保存");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDispatchAction.saveJg()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardType");
	}

	// 导出
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet("卡类");
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("请按模板整理数据！卡种、银行类型请填写对应数值！");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			// 行高
			sheet.setDefaultRowHeightInPoints(12);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 12);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "机构代码", "卡名称", "卡种\n(CardType)", "银行类型\n(BankType)", "磁道信息 ", "", "", "主帐号", "", "", "交易位图", "目标行", "主机号", "程序模板号" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			cell = null;
			String[] head2 = { "", "", "", "", "磁道", "起始字节", "磁道长度", "卡BIN", "卡BIN长度", "帐号长度" };
			for (int i = 0; i < head2.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(head2[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));

			}
			// 合并
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0));
			sheet.addMergedRegion(new Region(1, (short) 1, 2, (short) 1));
			sheet.addMergedRegion(new Region(1, (short) 2, 2, (short) 2));
			sheet.addMergedRegion(new Region(1, (short) 3, 2, (short) 3));
			sheet.addMergedRegion(new Region(1, (short) 4, 1, (short) 6));
			sheet.addMergedRegion(new Region(1, (short) 7, 1, (short) 9));
			sheet.addMergedRegion(new Region(1, (short) 10, 2, (short) 10));
			sheet.addMergedRegion(new Region(1, (short) 11, 2, (short) 11));
			sheet.addMergedRegion(new Region(1, (short) 12, 2, (short) 12));
			sheet.addMergedRegion(new Region(1, (short) 13, 2, (short) 13));

			row = sheet.createRow(3);
			POIUtils.createTextCell(book, row, (short) 0, "01000000");
			POIUtils.createTextCell(book, row, (short) 1, "绿卡通");
			POIUtils.createTextCell(book, row, (short) 2, "1");
			POIUtils.createTextCell(book, row, (short) 3, "07");
			POIUtils.createTextCell(book, row, (short) 4, "2");
			POIUtils.createTextCell(book, row, (short) 5, "1");
			POIUtils.createTextCell(book, row, (short) 6, "37");
			POIUtils.createTextCell(book, row, (short) 7, "621098");
			POIUtils.createTextCell(book, row, (short) 8, "6");
			POIUtils.createTextCell(book, row, (short) 9, "19");
			POIUtils.createTextCell(book, row, (short) 10, "00001111");
			POIUtils.createTextCell(book, row, (short) 11, "000001");
			POIUtils.createTextCell(book, row, (short) 12, "00");
			POIUtils.createTextCell(book, row, (short) 13, "90");

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

			HSSFSheet sheetCardType = book.createSheet("卡种(CardType)");
			// 生成标题栏目
			HSSFRow rowCardType = sheetCardType.createRow(0);
			HSSFCell cellCardType = rowCardType.createCell((short) 0);
			// cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
			cellCardType.setCellValue("卡种(CardType)");
			cellCardType.setCellStyle(POIUtils.getRedFontStyle(book));
			for (int i = 0; i < cardtypeList.size(); i++) {
				Cardtype cardType = cardtypeList.get(i);
				row = sheetCardType.createRow(1 + i);
				POIUtils.createCell(row, (short) 0, cardType.getId().getCardType());
				POIUtils.createCell(row, (short) 1, cardType.getId().getTypeName());
			}

			HSSFSheet sheetBankType = book.createSheet("银行类型(BankType)");
			// 生成标题栏目
			HSSFRow rowBankType = sheetBankType.createRow(0);
			HSSFCell cellBankType = rowBankType.createCell((short) 0);
			// cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
			cellBankType.setCellValue("银行类型(BankType)");
			cellBankType.setCellStyle(POIUtils.getRedFontStyle(book));
			for (int i = 0; i < banktypeList.size(); i++) {
				Banktype banktype = banktypeList.get(i);
				row = sheetBankType.createRow(1 + i);
				POIUtils.createCell(row, (short) 0, banktype.getBankType());
				POIUtils.createCell(row, (short) 1, banktype.getTypeName());
			}

			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("卡类导入模板", "UTF-8");
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
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// 文件大小不能超过5M
		// 得到Logic
		Set cardTypeSet = new HashSet();
		Set cardRouteSet = new HashSet();
		try {
			writer = response.getWriter();
			CardTypeUploadForm uf = (CardTypeUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "上传失败！");
				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
			} else {
				List<CardType> cardTypeList = new ArrayList<CardType>();
				List<CardRoute> cardRouteList = new ArrayList<CardRoute>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 3; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// for (int i = 0; i <row.getLastCellNum(); i++) {
					// System.out.print(row.getCell(i)+"\t");
					// }
					// 新建一个Model
					CardType cardType = new CardType();
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat df1 = new SimpleDateFormat("hhmmss");
					Date dt = new Date();
					cardType.setUpdateOper(ud.getUserId());
					cardType.setUpdateDate(df.format(dt));
					cardType.setUpdateTime(df1.format(dt));
					String cardId = POIUtils.getStringFromExcelCell(row.getCell(7));
					if (StringUtil.isNull(cardId)) {
						break;
					}
					cardType.setCardId(cardId);
					cardType.setCardLen(POIUtils.getIntegerFromExcelCell(row.getCell(9)) + "");
					cardType.setCardNoId(POIUtils.getStringFromExcelCell(row.getCell(7)));
					cardType.setCardType(POIUtils.getStringFromExcelCell(row.getCell(2)).length() == 1 ? "0" + POIUtils.getStringFromExcelCell(row.getCell(2))
							: POIUtils.getStringFromExcelCell(row.getCell(2)));
					String cardName = POIUtils.getStringFromExcelCell(row.getCell(1));
					cardType.setCardName(cardName.length() > 10 ? cardName.substring(0, 10) : cardName);
					cardType.setBankType(POIUtils.getStringFromExcelCell(row.getCell(3)));
					cardType.setBankCode(POIUtils.getStringFromExcelCell(row.getCell(0)));
					cardType.setCardMode("");
					cardType.setCardIdTrack(POIUtils.getIntegerFromExcelCell(row.getCell(4)) + "");
					cardType.setCardIdOff(POIUtils.getIntegerFromExcelCell(row.getCell(5)) + "");
					cardType.setCardNoTrack(POIUtils.getIntegerFromExcelCell(row.getCell(4)) + "");
					cardType.setCardNoOff("1");
					cardType.setExpDateOff((POIUtils.getIntegerFromExcelCell(row.getCell(9)) + 2) + "");// 长度+2
					cardType.setPinMode(1 + "");
					cardType.setInputMode(0 + "");
					cardType.setChkCardValid("0");
					cardType.setIfLocal("N");
					cardType.setIfOffline("Y");
					cardTypeList.add(cardType);

					CardRouteId cardRouteId = new CardRouteId();
					CardRoute cardRoute = new CardRoute();
					cardRouteId.setUpdateOper(ud.getUserId());
					cardRouteId.setUpdateDate(df.format(dt));
					cardRouteId.setUpdateTime(df1.format(dt));

					cardRouteId.setCardType(cardType.getCardType());
					int subLen = Integer.valueOf(POIUtils.getStringFromExcelCell(row.getCell(9)))
							- Integer.valueOf(POIUtils.getStringFromExcelCell(row.getCell(8)));
					cardRouteId.setFirstCardNo(POIUtils.getStringFromExcelCell(row.getCell(7)) + "00000000000000000000".substring(0, subLen));
					cardRouteId.setLastCardNo(POIUtils.getStringFromExcelCell(row.getCell(7)) + "99999999999999999999".substring(0, subLen));
					cardRouteId.setModuleId(new BigDecimal(POIUtils.getStringFromExcelCell(row.getCell(13))));
					cardRouteId.setRcvBankId(POIUtils.getStringFromExcelCell(row.getCell(11)));
					cardRouteId.setRcvHostId(POIUtils.getStringFromExcelCell(row.getCell(12)));
					cardRouteId.setTranBit(POIUtils.getStringFromExcelCell(row.getCell(10)));

					cardRoute.setId(cardRouteId);
					cardRouteList.add(cardRoute);
				}

				CardTypeLogicInterface officeSuppliesLogic = (CardTypeLogicInterface) getBean("cardTypeLogic");
				RouteLogicInterface routeLogic = (RouteLogicInterface) getBean("routeLogic");
				for (int i = 0; i < cardTypeList.size(); i++) {
					// 创建新的信息
					try {
						officeSuppliesLogic.createCardType(cardTypeList.get(i), ud);
					} catch (Exception e) {
						cardTypeSet.add(cardTypeList.get(i).getCardId());
						cardTypeSet.add(e.getMessage());
						log.error("批量增加卡类异常: ", e);

					}
					// 创建新的信息
					try {
						routeLogic.createRoute(cardRouteList.get(i), ud);
					} catch (Exception e) {
						cardRouteSet.add(cardTypeList.get(i).getCardId());
						cardRouteSet.add(e.getMessage());
						log.error("批量增加段路由异常: ", e);
					}
				}
				String returnVal = "操作成功！ 已成功导入 " + (cardTypeList.size() - cardTypeSet.size()) + " 条记录！<br><br><br>";
				if (cardTypeSet.size() > 0) {
					returnVal += "卡类表失败记录：" + cardTypeSet + "<br>";
				}
				if (cardRouteSet.size() > 0) {
					returnVal += "卡段路由表失败记录：" + cardRouteSet + "<br>";
				}
				// 保存
				jsonObj.put("success", returnVal);
				// jsonObj.put("batchId", taxerImpBatchDto.getBatchId());
				// jsonObj.put("msg", "导入成功,共导入"+taxerImpBatchDto.getTotalNum()
				// +"条,批次号为:"+taxerImpBatchDto.getBatchId()+",其中成功"+taxerImpBatchDto.getSuccNum()+"条,失败"+taxerImpBatchDto.getFailNum()+"条");
			}
			writer.write(jsonObj.toString());
			writer.flush();

		} catch (Exception e) {
			log.error("批量增加卡类保存Excel文件Action异常: {}", e);
			try {
				jsonObj.put("success", "操作失败！");
				jsonObj.put("msg", "卡类表失败记录：" + cardTypeSet + "卡段路由表失败记录：" + cardRouteSet + "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("批量增加卡类保存Excel文件Action异常: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("批量增加卡类保存Excel文件,关闭IO流");
				} catch (Exception e) {
					log.error("批量增加卡类保存Excel文件Action异常: {}", e);
				}
			}
		}
		return null;
	}

}
