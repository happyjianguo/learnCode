package cn.yufu.system.modules.cortexs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.system.common.persistence.Page;
import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.web.BaseController;
import cn.yufu.system.modules.cortexs.entity.TTranType;
import cn.yufu.system.modules.cortexs.entity.Tlog;
import cn.yufu.system.modules.cortexs.service.TTranTypeService;
import cn.yufu.system.modules.cortexs.service.TlogService;
import cn.yufu.system.modules.sys.utils.DictUtils;

/**
 * 交易流水Controller
 * @author LLG
 * @version 2016-08-24
 */
@Controller
@RequestMapping(value = "/cortexs/tlog")
public class TlogController extends BaseController {

	@Autowired
	private TlogService tlogService;
	@Autowired
	private TTranTypeService tTranTypeService;
	
	@ModelAttribute
	public Tlog get(@RequestParam(required=false) String id) {
		Tlog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tlogService.get(id);
		}
		if (entity == null){
			entity = new Tlog();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Tlog tlog, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(tlog.getBeginDatelocal()==null){
			tlog.setBeginDatelocal(DateUtils.getDate("yyyyMMdd"));
		}
		if(tlog.getEndDatelocal()==null){
			tlog.setEndDatelocal(DateUtils.getDate("yyyyMMdd"));
		}		
		Tlog tlogSum=this.tlogService.findSum(tlog);
		model.addAttribute("tlogSum", tlogSum);

		Page<Tlog> pg=new Page<Tlog>(request, response);
		pg.setOrderBy(" a.datelocal desc,a.timelocal desc,a.id desc");
		Page<Tlog> page = tlogService.findPage(pg, tlog); 
		model.addAttribute("page", page);
		//要显示的交易类型
		TTranType tTranType=new TTranType();
		tTranType.setShowFlag("1");
		List<TTranType> tranTypeList=this.tTranTypeService.findList(tTranType);
		model.addAttribute("tranTypeList", tranTypeList);	
		model.addAttribute("Tlog", tlog);	
		return "modules/cortexs/tlogList";
	}

	
	@RequestMapping(value = "form")
	public String form(Tlog tlog, Model model) {
		model.addAttribute("tlog", tlog);
		return "modules/cortexs/tlogForm";
	}
	
	/**
	 * 获取需要显示的交易类型MAP
	 * @return
	 */
	public Map<String,String> getTranTypeNeedShowMap(){
		Map<String,String> map=new HashMap<String,String>();	
		TTranType tTranType=new TTranType();
		tTranType.setShowFlag("1");
		List<TTranType> list=this.tTranTypeService.findList(tTranType);
		for(TTranType tt:list){
			map.put(tt.getFncode()+tt.getTxncode()+tt.getSubTxncode(), tt.getTranTypeDesc()+","+tt.getAmtFlag());
		}		
		return map;
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, Tlog tlog) {
		if(tlog.getBeginDatelocal()==null){
			tlog.setBeginDatelocal(DateUtils.getDate("yyyyMMdd"));
		}
		if(tlog.getEndDatelocal()==null){
			tlog.setEndDatelocal(DateUtils.getDate("yyyyMMdd"));
		}		
		Tlog tlogSum=this.tlogService.findSum(tlog);
		model.addAttribute("tlogSum", tlogSum);
		
		List<Tlog> list = this.tlogService.findExportList(tlog);
		int listSize=list==null?0:list.size();
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="流水查询 ("+DateUtils.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/tlogReport.xls"; // excel模板
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
            POIUtils.createCell(row, (short) 6, headName,style);
			row.setHeight((short)(27 * 20));	
						
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			Tlog cce = null;	
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getPan(),style);
					POIUtils.createCell(row, (short) 2,cce.getCrdacptid(),style);
					POIUtils.createCell(row, (short) 3,cce.getTermcode(),style);
					POIUtils.createCell(row, (short) 4,DateUtils.formatDate(cce.getDatelocal(), "yyyyMMdd"),style);
					POIUtils.createCell(row, (short) 5,cce.getTimelocal().toString(),style);
					POIUtils.createCell(row, (short) 6,cce.getTxnsrc()==null||"".equals(cce.getTxnsrc())?"":DictUtils.getDictLabel(cce.getTxnsrc(), "TXN_SRC", ""),style);
					POIUtils.createCell(row, (short) 7,cce.getTxnstatus()==null?"":DictUtils.getDictLabel(cce.getTxnstatus().toString(), "TXN_STATUS", ""),style);
					POIUtils.createCell(row, (short) 8,cce.getAmttxn()==null||"".equals(cce.getAmttxn())?"0.00":new DecimalFormat("#,###,###,###,###,##0.00").format(Double.valueOf(cce.getAmttxn())),style);
					POIUtils.createCell(row, (short) 9,cce.getAmttax()==null||"".equals(cce.getAmttax())?"0.00":new DecimalFormat("#,###,###,###,###,##0.00").format(Double.valueOf(cce.getAmttax())),style);
					POIUtils.createCell(row, (short) 10,cce.getTxnTypeDesc(),style);
					POIUtils.createCell(row, (short) 11,cce.getCrdproduct(),style);
					POIUtils.createCell(row, (short) 12,cce.getAmtEachCrd(),style);
					POIUtils.createCell(row, (short) 13,cce.getFatherOrder(),style);
					POIUtils.createCell(row, (short) 14,cce.getTime(),style);
					POIUtils.createCell(row, (short) 15,cce.getSalesPoint(),style);
					POIUtils.createCell(row, (short) 16,cce.getPayerName(),style);
					
				}
			}
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			row.setHeight((short)(27 * 20));
			POIUtils.createCell(row, (short) 0,"合计",style);
			POIUtils.createCell(row, (short) 1,"合计",style);
			POIUtils.createCell(row, (short) 2,"合计",style);
			POIUtils.createCell(row, (short) 3,"合计",style);
			POIUtils.createCell(row, (short) 4,"合计",style);
			POIUtils.createCell(row, (short) 5,"合计",style);
			POIUtils.createCell(row, (short) 6,"合计",style);
			POIUtils.createCell(row, (short) 7,"合计",style);
			POIUtils.createCell(row, (short) 8,tlogSum.getAmttxn()==null||"".equals(tlogSum.getAmttxn())?"":new DecimalFormat("#,###,###,###,###,##0.00").format(Double.valueOf(tlogSum.getAmttxn())),style);
			POIUtils.createCell(row, (short) 9,tlogSum.getAmttax()==null||"".equals(tlogSum.getAmttax())?"":new DecimalFormat("#,###,###,###,###,##0.00").format(Double.valueOf(tlogSum.getAmttax())),style);
			POIUtils.createCell(row, (short) 10,"",style);
			POIUtils.createCell(row, (short) 11,"",style);
			POIUtils.createCell(row, (short) 12,"",style);
			POIUtils.createCell(row, (short) 13,"",style);
			POIUtils.createCell(row, (short) 14,"",style);
			POIUtils.createCell(row, (short) 15,"",style);
			POIUtils.createCell(row, (short) 16,"",style);

			sheet.addMergedRegion(new Region(list.size()+startRow,(short) (0), list.size()+startRow, (short) (7)));											
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
			e.printStackTrace();
		}
		
		return null;
	}
}