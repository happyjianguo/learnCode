package cn.yufu.fs.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.fs.entity.ClearMerStlFinalBook;
import cn.yufu.fs.service.ClearMerStlFinalBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

/**
 * 扣款汇总Controller
 * @author ZQK
 * @version 2017-08-01
 */

@Controller
@RequestMapping(value = "/CrdfeeTotal")
public class CrdfeeTotalController {
	Log log = Log.getLog(CrdfeeTotalController.class);
	
	@Autowired
	@Qualifier("fs.ClearMerStlFinalBookService")	
	private ClearMerStlFinalBookService ClearMerStlFinalBookService;
	
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, 
						ClearMerStlFinalBook queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		
		model.addAttribute("query", queryModel);
		
		return "modules/fs/crdfeeManager/crdfeeTotalList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, 
			Model model, int curPage, int pageSize, ClearMerStlFinalBook queryModel) {
		try{
			//获取扣款商户
			String config = Global.getConfig("merchantNumber");
			if(StringUtils.isEmpty(config)) {
				model.addAttribute("shareBenefitReportList", new ArrayList<>());
				model.addAttribute("curPage", curPage);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("query", queryModel);
				return "modules/fs/crdfeeManager/crdfeeTotalList";
	        }
	        String[] merchantNumberArray = config.split(",");
	        List<String> asList = Arrays.asList(merchantNumberArray);
	        String startStlDate = queryModel.getStartStlDate();
	        if (cn.yufu.system.common.utils.StringUtils.isNotBlank(queryModel.getStartStlDate())
	        		&& cn.yufu.system.common.utils.StringUtils.isEmpty(queryModel.getStlDate())) {
	        	queryModel.setEndStlDate(queryModel.getStartStlDate()+"31");
	        	queryModel.setStartStlDate(queryModel.getStartStlDate()+"00");
			}
			// 分页设置
			int count = ClearMerStlFinalBookService.crdfeeTotalCount(asList,queryModel);
			Page page = new Page(curPage, pageSize, count);
			curPage = page.getCurPage();
			page.style1();
			String url = req.getRequestURI().toString();
			Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
			String pageBar = page.createPageBar(url, param);
			int startResult = (curPage - 1) * pageSize;
			int endResult = curPage * pageSize;
			
			List<ClearMerStlFinalBook> list = ClearMerStlFinalBookService.crdfeeTotalList(asList,queryModel, startResult, endResult);
			
			queryModel.setStartStlDate(startStlDate);
			model.addAttribute("crdfeeTotalList", list);
			model.addAttribute("curPage", curPage);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageBar", pageBar);	
			model.addAttribute("query", queryModel);
	
		}catch(Exception e){
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ShareBenefitReportController.getList()调用出现异常。");
		}
		
		return "modules/fs/crdfeeManager/crdfeeTotalList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMerStlFinalBook info) {
		//获取扣款商户
		String config = Global.getConfig("merchantNumber");
		if(StringUtils.isEmpty(config)) {
			return null;
        }
        String[] merchantNumberArray = config.split(",");
        List<String> asList = Arrays.asList(merchantNumberArray);
        if (cn.yufu.system.common.utils.StringUtils.isNotBlank(info.getStartStlDate())
        		&& cn.yufu.system.common.utils.StringUtils.isEmpty(info.getStlDate())) {
        	info.setEndStlDate(info.getStartStlDate()+"31");
        	info.setStartStlDate(info.getStartStlDate()+"00");
		}
        
		//导出符合查询条件的全部信息
		List<ClearMerStlFinalBook> list = ClearMerStlFinalBookService.crdfeeTotalList(asList, info);
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="扣款汇总 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/crdfeeTotal.xls"; // excel模板
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
			ClearMerStlFinalBook cce = null;
			for (int i = 0; i <list.size(); i++) {
				cce = list.get(i);
				if(cce!=null){
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getConsumAmt()==null?"0":cce.getConsumAmt().toString(),style);
					POIUtils.createCell(row, (short) 4,cce.getConsumNum()==null?"0":cce.getConsumNum().toString(),style);
					POIUtils.createCell(row, (short) 5,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getStlDate()==null||"".equals(cce.getStlDate())?"":cce.getStlDate(),style);
				}
			}
		
			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename=" + filename+".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}
			os.close();
		}catch (Exception e) {
			e.printStackTrace();
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ShareBenefitReportController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
}
