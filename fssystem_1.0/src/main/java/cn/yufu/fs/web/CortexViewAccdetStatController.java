package cn.yufu.fs.web;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.cortex.entity.CortexCrdProduct;
import cn.yufu.cortex.entity.CortexCrdStatus;
import cn.yufu.cortex.entity.CortexViewAccdetStat;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.MerstlErrorSet;
import cn.yufu.fs.entity.OldCardTranDaily;
import cn.yufu.fs.service.MerstlErrorSetService;
import cn.yufu.fs.service.OldCardTranDailyService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value = "/CortexViewAccdetStat")

public class CortexViewAccdetStatController {
	Log log = Log.getLog(CortexViewAccdetStatController.class);
	
	@Autowired
	@Qualifier("cortex.CortexService")	
	private CortexService CortexService;
	
	@Autowired
	@Qualifier("fs.MerstlErrorSetService")	
	private MerstlErrorSetService merstlErrorSetService;
	
	@Autowired
	@Qualifier("fs.OldCardTranDailyService")	
	private OldCardTranDailyService oldCardTranDailyService;
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, CortexViewAccdetStat queryModel) {
		//设置卡产品(卡类型)码表		
		List<CortexCrdProduct>  cortexCrdProductList=this.CortexService.getCortexCrdProductList(null);
		model.addAttribute("cortexCrdProductList",cortexCrdProductList);
		//设置卡状态码表	
		List<CortexCrdStatus>  cortexCrdStatusList=this.CortexService.getCortexCrdStatusList(null);
		model.addAttribute("cortexCardStatusList",cortexCrdStatusList);		
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/cortexViewAccdetStat/cortexViewAccdetStatList";
	}

	/**
	 * 分页查询明细
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, CortexViewAccdetStat queryModel) {
		// 分页设置
		int count = CortexService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<CortexViewAccdetStat> list = CortexService.queryList(queryModel, startResult, endResult);		
		model.addAttribute("CortexViewAccdetStatList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		//设置卡产品(卡类型)码表		
		List<CortexCrdProduct>  cortexCrdProductList=this.CortexService.getCortexCrdProductList(null);
		model.addAttribute("cortexCrdProductList",cortexCrdProductList);
		//设置卡状态码表	
		List<CortexCrdStatus>  cortexCrdStatusList=this.CortexService.getCortexCrdStatusList(null);
		model.addAttribute("cortexCardStatusList",cortexCrdStatusList);		
		
		//合计
		String card_cnt_sum="0";
		String card_bal_sum="0";
		String true_bal_sum="0";
		String inst_bal_sum="0";
		String total_acc_sum="0";
		String total_bal_sum="0";
		
		if(list!=null&&list.size()>0){
			String sumAmt=CortexService.getSumAmt(queryModel);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				card_cnt_sum=arr[0].toString();
				card_bal_sum=arr[1].toString();
				true_bal_sum=arr[2].toString();
				inst_bal_sum=arr[3].toString();
				total_acc_sum=arr[4].toString();
				total_bal_sum=arr[5].toString();
			}
		}
		model.addAttribute("card_cnt_sum", card_cnt_sum);
		model.addAttribute("card_bal_sum", card_bal_sum);
		model.addAttribute("true_bal_sum", true_bal_sum);
		model.addAttribute("inst_bal_sum", inst_bal_sum);
		model.addAttribute("total_acc_sum", total_acc_sum);	
		model.addAttribute("total_bal_sum", total_bal_sum);
		return "modules/fs/cortexViewAccdetStat/cortexViewAccdetStatList";
	}
	
	/**
	 * 实时卡余额汇总信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getBakList")
	public String getBakList(HttpServletRequest req, HttpServletResponse resp, Model model) {

		//合计总卡片数
		String card_cnt_sum = "0";
		BigDecimal cardSum = null;
		//总卡余额
		String card_bal_sum = "0";
		BigDecimal cardBal = null;
		
		String sumAmt = CortexService.getSumAmtBak(new CortexViewAccdetStat());
		if(sumAmt != null && !"".equals(sumAmt) && sumAmt.contains("#")){
			String[] arr = sumAmt.split("#");
			card_cnt_sum = arr[0].toString();
			card_bal_sum = arr[1].toString();
			//List<CortexSysParameter> list = CortexService.getSysParamListByParamType("VIEW_ACCDET_STAT");
			List<MerstlErrorSet> list = merstlErrorSetService.selectByExample(new MerstlErrorSet());
			List<OldCardTranDaily> list2 = oldCardTranDailyService.selectByExample(new OldCardTranDaily());
			
			if (list != null && list.size() > 0) {
				try {
					cardSum = new BigDecimal(card_cnt_sum);
					cardSum = cardSum.add(new BigDecimal(list.get(0).getErrorCardNum()));
					
					cardBal = new BigDecimal(card_bal_sum);
					cardBal = cardBal.add(new BigDecimal(list.get(0).getErrorCardAmt()));
					cardBal = cardBal.subtract(list2.get(0).getTransactionmoneyAcc());
							
				} catch (Exception e) {}
			}
		} else {
			model.addAttribute("falg", "0");
			return "modules/fs/cortexViewAccdetStat/cortexViewAccdetStatBakList";
		}
		if (cardSum != null) {
			card_cnt_sum = cardSum.toString();
		}
		if (cardBal != null) {
			card_bal_sum = cardBal.toString();
		}
		model.addAttribute("card_cnt_sum", card_cnt_sum);
		model.addAttribute("card_bal_sum", card_bal_sum);
		return "modules/fs/cortexViewAccdetStat/cortexViewAccdetStatBakList";
	}
	
	/**
	 * 取前一月日期
	 * @param pattern
	 * @return
	 */
	public String getLastMonthDay(String pattern) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一月
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, CortexViewAccdetStat info) {
		List<CortexViewAccdetStat> list = CortexService.queryList(info);
		int listSize=list==null?0:list.size();
		//合计
		String card_cnt_sum="0";
		String card_bal_sum="0";
		String true_bal_sum="0";
		String inst_bal_sum="0";
		String total_acc_sum="0";
		String total_bal_sum="0";
		
		if(list!=null&&list.size()>0){
			String sumAmt=CortexService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				card_cnt_sum=arr[0].toString();
				card_bal_sum=arr[1].toString();
				true_bal_sum=arr[2].toString();
				inst_bal_sum=arr[3].toString();
				total_acc_sum=arr[4].toString();
				total_bal_sum=arr[5].toString();
			}
		}
		model.addAttribute("card_cnt_sum", card_cnt_sum);
		model.addAttribute("card_bal_sum", card_bal_sum);
		model.addAttribute("true_bal_sum", true_bal_sum);
		model.addAttribute("inst_bal_sum", inst_bal_sum);
		model.addAttribute("total_acc_sum", total_acc_sum);
		model.addAttribute("total_bal_sum", total_bal_sum);				
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—实时卡余额报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/cortexViewAccdetStat.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet =work.getSheetAt(0);

			//设置表头和制表时间
			HSSFRow row = sheet.createRow((short) 1);
			HSSFCellStyle style = work.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = work.createFont(); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
            style.setFont(font);
            POIUtils.createCell(row, (short) 4, headName,style);
			row.setHeight((short)(27 * 20));			
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			CortexViewAccdetStat cce = null;	
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getCardBin(),style);
					POIUtils.createCell(row, (short) 2,cce.getCardStatusName()==null||"".equals(cce.getCardStatusName())?"":cce.getCardStatusName(),style);
					POIUtils.createCell(row, (short) 3,cce.getCrdproduct()==null||"".equals(cce.getCrdproduct())?"":cce.getCrdproduct(),style);
					
					POIUtils.createCell(row, (short) 4,"1".equals(cce.getIsExclusive())?"是":"否",style);
					POIUtils.createCell(row, (short) 5,cce.getCardTypeName()==null||"".equals(cce.getCardTypeName())?"":cce.getCardTypeName(),style);
					String stlFlag="--";
					if("1".equals(cce.getIsExclusive())&&"1".equals(cce.getStlFlag())){
						stlFlag="需要";
					}else if("1".equals(cce.getIsExclusive())&&"0".equals(cce.getStlFlag())){
						stlFlag="不需要";
					}
					POIUtils.createCell(row, (short) 6,stlFlag,style);
					POIUtils.createCell(row, (short) 7,cce.getCardCnt()==null||"".equals(cce.getCardCnt())?"0":cce.getCardCnt().toString(),style);
					POIUtils.createCell(row, (short) 8,cce.getCardBal()==null||"".equals(cce.getCardBal())?"0":cce.getCardBal().toString(),style);
					POIUtils.createCell(row, (short) 9,cce.getTrueBal()==null||"".equals(cce.getTrueBal())?"0":cce.getTrueBal().toString(),style);
					POIUtils.createCell(row, (short) 10,cce.getInstBal()==null||"".equals(cce.getInstBal())?"0":cce.getInstBal().toString(),style);
					POIUtils.createCell(row, (short) 11,cce.getInstBal9()==null||"".equals(cce.getInstBal9())?"0":cce.getInstBal9().toString(),style);
					POIUtils.createCell(row, (short) 12,cce.getTotalBal()==null||"".equals(cce.getTotalBal())?"0":cce.getTotalBal().toString(),style);
				}
			}
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			row.setHeight((short)(27 * 20));
			String sumStr="合计    卡张数："+card_cnt_sum+"		卡账户余额:"+card_bal_sum+"		实名账户余额:"+true_bal_sum+"		积分账户余额:"+inst_bal_sum+"		联名卡账户余额:"+total_acc_sum+"		账户总余额:"+total_bal_sum;
			POIUtils.createCell(row, (short) 4,	sumStr,null);	
									
			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CortexViewAccdetStatController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
}
