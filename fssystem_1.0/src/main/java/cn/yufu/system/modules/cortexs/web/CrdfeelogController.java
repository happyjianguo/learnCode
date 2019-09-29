package cn.yufu.system.modules.cortexs.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.CrdFeeRefundOrder;
import cn.yufu.system.modules.cortexs.entity.Crdfeelog;
import cn.yufu.system.modules.cortexs.service.CrdFeeRefundOrderService;
import cn.yufu.system.modules.cortexs.service.CrdfeelogService;
import cn.yufu.system.modules.sys.utils.DictUtils;
import cn.yufu.system.modules.sys.utils.UserUtils;

/**
 * 新福卡扣款明细Controller
 * @author ZQK
 * @version 2017-07-31
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping(value = "/cortexs/crdfeelog")
public class CrdfeelogController extends BaseController {
	
	@Autowired
	private CrdfeelogService crdfeelogService;
	
	@Autowired
	private CrdFeeRefundOrderService crdFeeRefundOrderService;
	
	@ModelAttribute
	public Crdfeelog get(@RequestParam(required=false) String crdFeeLogId) {
		Crdfeelog entity = null;
		if (StringUtils.isNotBlank(crdFeeLogId)){
			entity = crdfeelogService.get(crdFeeLogId);
		}
		if (entity == null){
			entity = new Crdfeelog();
		}else {
			entity.setId(crdFeeLogId); //更新
		}
		return entity;
	}
	
	@RequestMapping(value = {"page"})
	public String page(Crdfeelog crdfeelog, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		Page<Crdfeelog> page = new Page<Crdfeelog>(request, response); 
		model.addAttribute("consumeAmt", 0);
		model.addAttribute("consumeSum", 0);
		model.addAttribute("refundSum", 0);
		model.addAttribute("count", 0);
		model.addAttribute("page", page);
		model.addAttribute("crdfeelog", crdfeelog);
		return "modules/cortexs/crdfeelogList";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Crdfeelog crdfeelog, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String year = crdfeelog.getYear();
		String month = crdfeelog.getMonth();
		int flag = 0;
		if (StringUtils.isNotBlank(year)) {
			if (StringUtils.isNotBlank(month)) {
				crdfeelog.setBegainLocalDate(year+month+"00");
				crdfeelog.setEndLocalDate(year+month+"31");
			}else{
				crdfeelog.setBegainLocalDate(year+"0000");
				crdfeelog.setEndLocalDate(year+"1231");
			}
			flag = 1;
		}
		BigDecimal consumeSum = crdfeelogService.getConsumeSum(crdfeelog);
		if (consumeSum == null) consumeSum = new BigDecimal(0);
		
		BigDecimal refundSum = crdfeelogService.getRefundSum(crdfeelog);
		if (refundSum == null) refundSum = new BigDecimal(0);
		
		Page<Crdfeelog> page = crdfeelogService.findPage(new Page<Crdfeelog>(request, response), crdfeelog); 
		List<Crdfeelog> list = page.getList();
		double result = 0;
		DecimalFormat dFormat = new DecimalFormat("#.00");
		for (Crdfeelog crd : list) {
			//匹配未有交易的年数
			if (StringUtils.isNotBlank(crd.getNoTranYear())) {
				result = Double.parseDouble(crd.getNoTranYear())/12;
				String format = dFormat.format(result);
				if (format.charAt(0) == '.') {
					format = "0" + format;
				}
				crd.setNoTranYear(format);
			}
			//展示到月份
			if (StringUtils.isNotBlank(crd.getLocalDate())) {
				crd.setLocalDate(crd.getLocalDate().substring(0, 4)+"-"+crd.getLocalDate().substring(4, 6));
			}
		}
		
		if (flag == 1) {
			crdfeelog.setYear(year);
			crdfeelog.setMonth(month);
		}
		model.addAttribute("consumeAmt", consumeSum.add(refundSum));
		model.addAttribute("consumeSum", consumeSum);
		model.addAttribute("refundSum", refundSum);
		model.addAttribute("count", page.getCount());
		model.addAttribute("page", page);
		model.addAttribute("crdfeelog", crdfeelog);
		return "modules/cortexs/crdfeelogList";
	}
	
	@RequestMapping(value = "show")
	public String show(Crdfeelog crdfeelog, Model model) {
		String stat = "";
		double result = 0;
		DecimalFormat dFormat = new DecimalFormat("#.00");
		//匹配未有交易的年数
		if (StringUtils.isNotBlank(crdfeelog.getNoTranYear())) {
			result = Double.parseDouble(crdfeelog.getNoTranYear())/12;
			String format = dFormat.format(result);
			if (format.charAt(0) == '.') {
				format = "0" + format;
			}
			crdfeelog.setNoTranYear(format);
		}
		//展示到月份
		if (StringUtils.isNotBlank(crdfeelog.getLocalDate())) {
			crdfeelog.setLocalDate(crdfeelog.getLocalDate().substring(0, 4)+"-"+crdfeelog.getLocalDate().substring(4, 6));
		}
		//匹配状态
		if (crdfeelog.getTxnstatus() == 7) {
			stat = "7";
		}else if (StringUtils.isNotEmpty(crdfeelog.getTxnstatus()+"")) {
			stat = "0";
		}
		crdfeelog.setReserved4(stat);
		model.addAttribute("crdfeelog", crdfeelog);
		return "modules/cortexs/crdfeelogShow";
	}
	
	/**
	 * 跳转退款页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toRefund" })
	public String toRefund(HttpServletRequest req, HttpServletResponse resp, 
			Model model, Crdfeelog queryModel) {
		List<Crdfeelog> list = null;
		if (StringUtils.isNotEmpty(queryModel.getRefundIDs())) {
			List<String> ids = new ArrayList<String>();
			String refundIDs = queryModel.getRefundIDs();
			String[] split = refundIDs.split("\\|");
			for (String string : split) {
				ids.add(string);
			}
			queryModel.setIds(ids);
			model.addAttribute("crdfeerule", queryModel);
			list = crdfeelogService.selectByPrimaryKey(queryModel);
		}
		model.addAttribute("crdfeelogList", list);		
		return "modules/cortexs/crdfeelogRefund";
	}
	
	@RequestMapping(value = "refund")
	public String refund(Crdfeelog info, Model model,RedirectAttributes redirectAttributes) {
		try {
			List<String> ids = new ArrayList<String>();
			String refundIDs = info.getRefundIDs();
			String[] split = refundIDs.split("\\|");
			for (String string : split) {
				ids.add(string);
			}
			info.setIds(ids);
			List<Crdfeelog> list = crdfeelogService.selectByPrimaryKey(info);
			if (null == list || list.size() == 0) {
				addMessage(redirectAttributes, "保存退款信息失败，退款数据不可为空!");
				return "redirect:/cortexs/crdFeeRule/?repage";
			}
			for (Crdfeelog crdfeelog : list) {
				CrdFeeRefundOrder crdFeeRefundOrder = new CrdFeeRefundOrder();
				crdFeeRefundOrder.setIsNewRecord(true);
				copyData(crdFeeRefundOrder, crdfeelog);
				crdFeeRefundOrderService.save(crdFeeRefundOrder);
			}
			
			info.setReserved2(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
			info.setReserved1("1");
			crdfeelogService.refund(info);
			addMessage(redirectAttributes, "保存退款信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "保存退款信息失败");
		}
		return "redirect:/cortexs/crdfeelog/?repage";
	}
	
	private void copyData(CrdFeeRefundOrder crdFeeRefundOrder, Crdfeelog crdfeelog) {
		crdFeeRefundOrder.setCrdfeelogId(String.valueOf(crdfeelog.getCrdFeeLogId()));
		crdFeeRefundOrder.setMerchantno(crdfeelog.getMerchantno());
		crdFeeRefundOrder.setTermcode(crdfeelog.getTermcode());
		crdFeeRefundOrder.setPan(crdfeelog.getPan());
		crdFeeRefundOrder.setFee(crdfeelog.getFee());
		crdFeeRefundOrder.setTlogId(new BigDecimal(crdfeelog.getTlogId()));
		crdFeeRefundOrder.setTranDate(crdfeelog.getTranDate());
		crdFeeRefundOrder.setTranTime(crdfeelog.getTranTime());
		crdFeeRefundOrder.setStan(new BigDecimal(crdfeelog.getStan()));
		crdFeeRefundOrder.setRrn(crdfeelog.getRrn());
		crdFeeRefundOrder.setTxncode(crdfeelog.getTxncode());
		crdFeeRefundOrder.setTxnsrc(crdfeelog.getTxnsrc());
		crdFeeRefundOrder.setRspcode(crdfeelog.getRspcode());
		crdFeeRefundOrder.setTxnstatus(crdfeelog.getTxnstatus());
		Date date = new Date();
		crdFeeRefundOrder.setAdddate(DateUtils.formatDate(date, "yyyyMMdd"));
		crdFeeRefundOrder.setAddtime(DateUtils.formatDate(date, "HHmmss"));
		crdFeeRefundOrder.setRefundflag(0);
		String loginName = UserUtils.getPrincipal().getLoginName();
		crdFeeRefundOrder.setOper(loginName==null?"system":loginName);
	}

	/**
	 * 获取当前时间
	 * @param pattern	时间格式
	 * @return
	 */
	public String getNowDt(String pattern,int flag){
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, flag);
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}	
	
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcel" })
	@ResponseBody
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Crdfeelog info) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		try{
			String year = info.getYear();
			String month = info.getMonth();
			if (StringUtils.isNotBlank(year)) {
				if (StringUtils.isNotBlank(month)) {
					info.setBegainLocalDate(year+month+"00");
					info.setEndLocalDate(year+month+"31");
				}else{
					info.setBegainLocalDate(year+"0000");
					info.setEndLocalDate(year+"1231");
				}
			}
			//导出符合查询条件的全部信息
			List<Crdfeelog> list = crdfeelogService.findList(info);
			if (list == null || list.size() == 0) list = new ArrayList<Crdfeelog>();
			
			//导出符合查询条件的全部信息
			String titleName="新福卡扣款明细报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			String[] headers = {"序号","卡号","扣款月份","扣款商户号","扣款终端号","原账户余额(元)","交易金额(元)",
            		"费率(%)","交易日期","交易时间","交易类型","交易状态","收取规则ID","流水ID","未有交易年数","退款状态"};
			
			ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet(titleName, "新福卡扣款明细", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.write(req, resp, titleName).dispose();
				return null;
			}
			//得到合计金额值
			BigDecimal consumeSum = crdfeelogService.getConsumeSum(info);
			if (consumeSum == null) consumeSum = new BigDecimal(0);
			
			BigDecimal refundSum = crdfeelogService.getRefundSum(info);
			if (refundSum == null) refundSum = new BigDecimal(0);
			
			Row row = null;
			int count = 0;
			int headrLen = 0;
			String refundState = "";
			for (Crdfeelog model : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				
				double result = 0;
				DecimalFormat dFormat = new DecimalFormat("#.00");
				String format = "";
				//匹配未有交易的年数
				if (StringUtils.isNotBlank(model.getNoTranYear())) {
					result = Double.parseDouble(model.getNoTranYear())/12;
					format = dFormat.format(result);
					if (format.charAt(0) == '.') {
						format = "0" + format;
					}
				}
				//展示到月份
				if (StringUtils.isNotBlank(model.getLocalDate())) {
					model.setLocalDate(model.getLocalDate().substring(0, 4)+"-"+model.getLocalDate().substring(4, 6));
				}
				//匹配状态
				String stat = "";
				if (model.getTxnstatus() == 7) {
					stat = "成功";
				}else if (StringUtils.isNotEmpty(model.getTxnstatus() + "")) {
					stat = "失败";
				}
				//匹配交易类型
				String tranType = "";
				if (0 == model.getTxncode()) {
					tranType = "消费";
				} else if (20 == model.getTxncode()) {
					tranType = "退款";
				}
				
				//倒叙填充单元格数据
				refundState = DictUtils.getDictLabel(model.getReserved1(), "REFUND_STATE_X", "");
				exportExcel.addCell(row, --headrLen, refundState);
				exportExcel.addCell(row, --headrLen, format);
				exportExcel.addCell(row, --headrLen, null == model.getTlogId()?"":model.getTlogId().toString());
				exportExcel.addCell(row, --headrLen, null == model.getFeeRule()?"":model.getFeeRule().toString());
				exportExcel.addCell(row, --headrLen, stat);
				exportExcel.addCell(row, --headrLen, tranType);
				exportExcel.addCell(row, --headrLen, null == model.getTranTime()?"":model.getTranTime().trim());
				exportExcel.addCell(row, --headrLen, null == model.getTranDate()?"":model.getTranDate().trim());
				exportExcel.addCell(row, --headrLen, null == model.getRate()?"":model.getRate().trim());
				exportExcel.addCell(row, --headrLen, null == model.getFee()?"0.00":model.getFee().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getAvlbal()?"0.00":model.getAvlbal().toPlainString());
				exportExcel.addCell(row, --headrLen, null == model.getTermcode()?"":model.getTermcode().trim());
				exportExcel.addCell(row, --headrLen, null == model.getMerchantno()?"":model.getMerchantno().trim());
				exportExcel.addCell(row, --headrLen, null == model.getLocalDate()?"":model.getLocalDate().trim());
				exportExcel.addCell(row, --headrLen, null == model.getPan()?"":model.getPan().trim());
				exportExcel.addCell(row, --headrLen, ++count + "");
			}
			
			Sheet sheet = exportExcel.getSheet();
			row = exportExcel.addRow();
			int rownum = exportExcel.getRownum();
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, headers.length - 1));
			exportExcel.addCell(row, (int)(headers.length/2), "消费金额汇总：   " + consumeSum.add(refundSum).toPlainString() + "  元        " + 
					"扣款金额汇总：   " + consumeSum.toPlainString() + "  元        " +
					"退款金额汇总：   " + refundSum.toPlainString() + "  元        ");
			
			exportExcel.write(req, resp, titleName).dispose();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}