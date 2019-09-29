package cn.yufu.bak.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

import cn.yufu.bak.service.MerchantXService;
import cn.yufu.bak.service.TermposXService;
import cn.yufu.cortex.entity.CortexArea;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.TClearMerstlBook;
import cn.yufu.fs.service.TClearMerstlBookService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;

@Controller
@RequestMapping(value="/MerchantActiveReach")
public class MerchantActiveReachController {
	
	Log log = Log.getLog(MerchantActiveReachController.class);
	
	@Autowired
	@Qualifier("cortex.CortexService")
	private CortexService cortexService;
	
	@Autowired
	@Qualifier("fs.TClearMerstlBookService")
	private TClearMerstlBookService tClearMerstlBookService;
	
	@Autowired
	@Qualifier("bak.TermposXService")
	private TermposXService termposXService;
	
	@Autowired
	@Qualifier("bak.MerchantXService")
	private MerchantXService merchantXService;
	
	/**
	 * 加载初始页(商户活跃率)
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param queryModel
	 * @return
	 */
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response,
			Model model, TClearMerstlBook queryModel) {
		queryModel.setStartStlDate(getLastMonthDay("yyyyMMdd"));		
		queryModel.setEndStlDate(getNowDt("yyyyMMdd",0));
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = cortexService.getCortexAreaList(cortexArea);
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		model.addAttribute("provinceList", provinceList);
		return "modules/cortexs/merchantActiveReachList";
	}
	
	/**
	 * 分页查询明细
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest request, HttpServletResponse response, 
			Model model, int curPage, int pageSize, TClearMerstlBook queryModel) {
		//区域设置
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = cortexService.getCortexAreaList(cortexArea);
		
		if (queryModel.getProvinceCode() != null) {
			CortexArea province = new CortexArea();
			province.setId(queryModel.getProvinceCode());
			province.setIsuse("1");
			List<CortexArea> provinceName = cortexService.getCortexAreaList(province);
			queryModel.setProvinceName(provinceName.get(0).getProvinceCity());
			
			cortexArea.setFid(queryModel.getProvinceCode());
			cortexArea.setIsuse("1");
			List<CortexArea> cityList = cortexService.getCortexAreaList(cortexArea);
			
			model.addAttribute("cityList", cityList);
		}
		model.addAttribute("provinceList", provinceList);
		
		// 分页设置
		int count = tClearMerstlBookService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = request.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		//获取列表数据
		List<TClearMerstlBook> list = this.getMerchantActiveList(queryModel, startResult, endResult);
		
		model.addAttribute("tClearMerstlBookList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("query", queryModel);
		
		return "modules/cortexs/merchantActiveReachList";
	}
	
	/**
	 * 导出EXCEL
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "exportExcel")
	@ResponseBody
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, TClearMerstlBook info) {
		if (info.getProvinceCode() != null) {
			CortexArea province = new CortexArea();
			province.setId(info.getProvinceCode());
			province.setIsuse("1");
			List<CortexArea> provinceName = cortexService.getCortexAreaList(province);
			info.setProvinceName(provinceName.get(0).getProvinceCity());
		}
		//导出符合查询条件的全部信息
		List<TClearMerstlBook> list = this.getMerchantActiveList(info,0,0);
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="活跃商户报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/merchantActiveReach.xls"; // excel模板
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
			TClearMerstlBook cce = null;
			String area = "";
			for (int i = 0; i <list.size(); i++) {
				cce = list.get(i);
				area = "";
				if(cce!=null){
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getConsumNum()==null?"0":cce.getConsumNum().toString(),style);
					POIUtils.createCell(row, (short) 4,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
					area = cce.getProvinceName() + "-" + cce.getCityName();
					POIUtils.createCell(row, (short) 5,area,style);
					POIUtils.createCell(row, (short) 6,cce.getRate()==null||"".equals(cce.getRate())?"":cce.getRate(),style);
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
			log.error("MerchantActiveReachController.exportExcel()调用出现异常。");
		}
		return null;
	}
	
	/**
	 * 封装数据(备库)(匹配费率、商户名称)
	 * @param queryModel
	 * @param startResult
	 * @param endResult
	 * @return ClearMerStlBook
	 */
	private List<TClearMerstlBook> getMerchantActiveList(TClearMerstlBook queryModel, int startResult, int endResult){
		List<TClearMerstlBook> list = null;
		if (startResult == 0 && endResult == 0) {
			list = tClearMerstlBookService.queryAllList(queryModel);
		}else{
			list = tClearMerstlBookService.queryList(queryModel, startResult, endResult);
		}
		for (TClearMerstlBook clearMerStlBook : list) {
			//匹配商户名称(一个商户号可能会有多个商户名称 )
			try {
				List<String> mrchtName = merchantXService.getMrchtName(clearMerStlBook.getMerNo());
				if (mrchtName != null) {
					clearMerStlBook.setMerName(mrchtName.get(0));
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("MerchantXService的getMrchtName方法出现异常。");
			}
			
			//匹配费率
			List<String> timezoneList = termposXService.getTimezoneList(clearMerStlBook.getMerNo());
			if (timezoneList == null) {
				clearMerStlBook.setRate("0");
				continue;
			}
			String rate = "";
			for (int i = 0; i < timezoneList.size(); i++) {
				if (i == 0) {
					rate += timezoneList.get(i);
				}else{
					rate += ";" + timezoneList.get(i);
				}
			}
			clearMerStlBook.setRate(rate);
			
		}
		
		return list;
	}
	
	/**
	 * 根据省份ID获取所辖城市
	 * @param provinceId
	 * @return
	 */
	@RequestMapping(value = { "getCityList" })
	@ResponseBody
	public List<CortexArea> getCityList(Long provinceId) {
		if (provinceId == null) {
			return null;
		}
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(provinceId);
		cortexArea.setIsuse("1");
		List<CortexArea> cityList = cortexService.getCortexAreaList(cortexArea);
    	return cityList;
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
	
}
