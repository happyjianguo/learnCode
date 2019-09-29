package cn.yufu.fs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.fs.entity.TWankeCardBin;
import cn.yufu.fs.service.TWankeCardBinService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;

/**
 * 机构/卡BIN分润Controller
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:ZQK
 * 时间:2018年05月25日
 * 描述:万科卡BIN Controller
 * */
@Controller
@RequestMapping(value="/wankeCardBin")
public class TWankeCardBinController {
	
	Log log = Log.getLog(TWankeCardBinController.class);
	
	@Autowired
	@Qualifier("fs.TWankeCardBinService")
	private TWankeCardBinService wankeCardBinService;
	
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest request, HttpServletResponse response,
			Model model, TWankeCardBin queryModel) {
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/parmeterManage/wankeCardBinList";
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
	@RequestMapping(value = "list")
	public String getList(HttpServletRequest request, HttpServletResponse response, 
			Model model, int curPage, int pageSize, TWankeCardBin queryModel) {
		// 分页设置
		int counts = wankeCardBinService.queryCount(queryModel);
		
		Page page = new Page(curPage, pageSize, counts);
		curPage = page.getCurPage();
		page.style1();
		String url = request.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(request);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		//获取实名认证列表数据
		List<TWankeCardBin> list = wankeCardBinService.selectPageList(queryModel, startResult, endResult);
		if (list == null || list.size() == 0) list = new ArrayList<>();
		model.addAttribute("wankeCardBinList", list);
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/parmeterManage/wankeCardBinList";
	}
	
	/**
	 * 跳转修改或添加页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toShow" })
	public String toShow(HttpServletRequest req, HttpServletResponse resp, 
			Model model,TWankeCardBin queryModel) {
		if (StringUtils.isNotEmpty(queryModel.getCardId())) {
			//更新
			queryModel = wankeCardBinService.selectByPrimaryKey(queryModel.getCardId());
			model.addAttribute("flag", "1");
		}else {
			String cardId = wankeCardBinService.getCardId();
			queryModel.setCardId(cardId);
			model.addAttribute("flag", "0");
		}
		model.addAttribute("info", queryModel);		
		return "modules/fs/parmeterManage/wankeCardBinForm";
	}
	
	/**
	 * 添加信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"save"})
	@ResponseBody
	public String save(HttpServletRequest req, HttpServletResponse resp, 
			Model model, TWankeCardBin info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TWankeCardBin result = wankeCardBinService.selectByPrimaryKey(info.getCardId());
			if ("0".equals(info.getFlag())) {
				//执行添加
				if (result != null) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "ID已存在，无法进行添加操作!");
					return JsonUtil.getJsonString(map);
				}
				map = wankeCardBinService.insertCardBin(info);
			}else{
				if (result == null) {
					map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
					map.put(SysConst.RESULT_MSG, "ID不存在，无法进行更新操作!");
					return JsonUtil.getJsonString(map);
				}else{
					map = wankeCardBinService.updateCardBin(info);
				}
			}
		} catch (Exception e) {
			log.info("保存万科卡BIN信息失败：", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "保存万科卡BIN信息失败。");
		}
		return JsonUtil.getJsonString(map);
	}
	
	/**
	 * 删除信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"delete"})
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest req, HttpServletResponse resp, 
			Model model, TWankeCardBin info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = wankeCardBinService.deleteByPrimaryKey(info);
		} catch (Exception e) {
			log.info("删除万科卡BIN信息失败：", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "删除万科卡BIN信息失败。");
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, 
			TWankeCardBin queryModel) {
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="万科卡BIN("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path = req.getSession().getServletContext().getRealPath("/")+ "reportTemp/wankeCardBin.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet = work.getSheetAt(0);

			//设置表头和制表时间
			HSSFRow row = sheet.createRow((short) 1);
			HSSFCellStyle style = work.createCellStyle();
			DataFormat format = work.createDataFormat();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			HSSFFont font = work.createFont(); 
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);// 设置字体大小
            style.setFont(font);
            
            POIUtils.createCell(row, (short) 2, headName,style);
			row.setHeight((short)(27 * 20));
			
			//导出符合查询条件的全部信息
			List<TWankeCardBin> list = wankeCardBinService.selectByExample(queryModel);
			if (list == null) list = new ArrayList<>();

			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始		
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();	
			style.setDataFormat(format.getFormat("@")); //文本格式
			
			TWankeCardBin model = null;
			for (int i = 0; i <list.size(); i++) {
				model = list.get(i);
				if(null != model){
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,model.getCardId() == null ? "" : model.getCardId(),style);
					POIUtils.createCell(row, (short) 2,model.getCardBin() == null ? "" : model.getCardBin(),style);
				}
			}
			
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
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TransactionSplittingController.exportExcel()调用出现异常。");
		}
		
		return null;
	}
	
	/**
	 * 取后月日期
	 * @param pattern
	 * @return
	 */
	public String getMonthDay(String pattern,int flag) {
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, flag); 
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
