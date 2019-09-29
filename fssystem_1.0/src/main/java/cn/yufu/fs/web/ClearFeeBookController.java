package cn.yufu.fs.web;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.cortex.entity.CortexSysParameter;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearFeeBook;
import cn.yufu.fs.entity.ViewMerFeeDetail;
import cn.yufu.fs.service.ClearFeeBookService;
import cn.yufu.fs.service.ViewMerFeeDetailService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.SysConst;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.JsonUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.modules.sys.entity.User;
import cn.yufu.system.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "/ClearFeeBook")

public class ClearFeeBookController {
	Log log = Log.getLog(ClearFeeBookController.class);
	
	@Autowired
	@Qualifier("fs.ClearFeeBookService")	
	private ClearFeeBookService ClearFeeBookService;
	
	@Autowired
	@Qualifier("cortex.CortexService")	
	private CortexService CortexService;
	
	@Autowired
	@Qualifier("fs.ViewMerFeeDetailService")	
	private ViewMerFeeDetailService ViewMerFeeDetailService;
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
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model, ClearFeeBook queryModel) {
		queryModel.setStlDate(getNowDt("yyyyMMdd",-1));	
		//设置消费场景码表
		CortexSysParameter sysParamter=new CortexSysParameter();
		sysParamter.setParamType("CONSUMP_CATEGORY");		
		List<CortexSysParameter>  sysParamList=this.CortexService.getCortexSysParameterList(sysParamter);
		model.addAttribute("consumpCategoryList",sysParamList);
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("query", queryModel);
		return "modules/fs/clearFeeBook/clearFeeBookList";
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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, int curPage, int pageSize, ClearFeeBook queryModel) {
		// 分页设置
		int count = ClearFeeBookService.queryCnt(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;

		List<ClearFeeBook> list = ClearFeeBookService.queryList(queryModel, startResult, endResult);		
		model.addAttribute("ClearFeeBookList", list);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);		
		model.addAttribute("query", queryModel);
		//合计
		String tran_amt="0";
		String fee="0";

		
		if(list!=null&&list.size()>0){
			String sumAmt=ClearFeeBookService.getSumAmt(queryModel);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				fee=arr[1].toString();
			}
		}
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("fee", fee);
		return "modules/fs/clearFeeBook/clearFeeBookList";
	}
	
	/**
	 * 跳转查看页面
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toEdit" })
	public String toEdit(HttpServletRequest req, HttpServletResponse resp, Model model,String ClearFeeBookId) {
		ClearFeeBook info = ClearFeeBookService.queryInfo(ClearFeeBookId);
		model.addAttribute("info", info);	
		return "modules/fs/clearFeeBook/clearFeeBookEdit";
	}

	/**
	 * 修改信息
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "edit" }, method = { RequestMethod.POST })
	@ResponseBody
	public String edit(HttpServletRequest req, HttpServletResponse resp, Model model,ClearFeeBook info) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			info.setStatus("1");
			User currentUser = UserUtils.getUser();
			String now = DateUtil.getNowDefault();
			info.setUpdateDT(now);
			info.setOperNo(currentUser.getId());
			info.setRealFee(info.getFee().add(info.getCheckFee()));
			map = ClearFeeBookService.edit(info);
		} catch (Exception e) {
			log.info("更新手续费信息异常：{}", e);
			map.put(SysConst.RESULT, SysConst.RESULT_FAIL);
			map.put(SysConst.RESULT_MSG, "更新手续费信息失败。");
		}
		return JsonUtil.getJsonString(map);
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearFeeBook info) {
		List<ClearFeeBook> list = ClearFeeBookService.queryList(info);
		int listSize=list==null?0:list.size();
		//合计
		String tran_amt="0";
		String fee="0";		
		if(list!=null&&listSize>0){
			String sumAmt=ClearFeeBookService.getSumAmt(info);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				fee=arr[1].toString();
			}
		}		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="财务报表—手续费报表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/clearFeeBookReport.xls"; // excel模板
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
			
			String isKpStr="";
			
			int startRow=3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			ClearFeeBook cce = null;	
			for (int i = 0; i < listSize; i++) {
				cce = list.get(i);
				if(cce!=null){	
					if(cce.getIsKp()!=null){
						if("0".equals(cce.getIsKp().trim())){
							isKpStr="不需要开票";
						}else if("1".equals(cce.getIsKp().trim())){
							isKpStr="需要开票";
						}
					}
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,cce.getMerNo(),style);
					POIUtils.createCell(row, (short) 2,cce.getMerName()==null||"".equals(cce.getMerName())?"":cce.getMerName(),style);
					POIUtils.createCell(row, (short) 3,cce.getHeadOffice()==null||"".equals(cce.getHeadOffice())?"":cce.getHeadOffice(),style);
					POIUtils.createCell(row, (short) 4,cce.getTranAmt()==null?"0":cce.getTranAmt().toString(),style);
					POIUtils.createCell(row, (short) 5,cce.getStartDate()==null||"".equals(cce.getStartDate())?"":cce.getStartDate(),style);
					POIUtils.createCell(row, (short) 6,cce.getEndDate()==null||"".equals(cce.getEndDate())?"":cce.getEndDate(),style);
					POIUtils.createCell(row, (short) 7,cce.getFee()==null?"0":cce.getFee().toString(),style);
					POIUtils.createCell(row, (short) 8,cce.getRealFee()==null?"0":cce.getRealFee().toString(),style);
					POIUtils.createCell(row, (short) 9,isKpStr,style);
					POIUtils.createCell(row, (short) 10,cce.getExpressDate()==null?"":cce.getExpressDate().toString(),style);
					POIUtils.createCell(row, (short) 11,cce.getOfficeNo()==null?"":cce.getOfficeNo().toString(),style);
					POIUtils.createCell(row, (short) 12,cce.getExpressNo()==null?"":cce.getExpressNo().toString(),style);
					POIUtils.createCell(row, (short) 13,cce.getComments()==null||"".equals(cce.getComments())?"":cce.getComments(),style);
				}
			}
			//设置合计交易金额、手续费金额和条数
			row = sheet.createRow(list.size()+startRow);
			row.setHeight((short)(27 * 20));
			POIUtils.createCell(row, (short) 2, "合计     消费金额："+tran_amt+"  交易手续费："+fee,null);	
									
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
			log.error("ClearFeeBookController.exportExcel()调用出现异常。");
		}
		
		return null;
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
	@RequestMapping(value = { "getDetailList" })
	public String getDetailList(HttpServletRequest req, HttpServletResponse resp, Model model, String id) {	
		ViewMerFeeDetail queryModel=new ViewMerFeeDetail();
		queryModel.setId(id);
		List<ViewMerFeeDetail> list = ViewMerFeeDetailService.queryList(queryModel, 0, 9999999);
		//设置交易类型名称
		Map<String,String> consumpCategoryMap=this.CortexService.getSysParamMapByParamType("FS_TRAN_TYPE");		
		String tranType="";
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(ViewMerFeeDetail cmcb:list){
				tranType=cmcb.getTranType();
				if(tranType!=null&&!"".equals(tranType)){
					cmcb.setTranTypeDesc(consumpCategoryMap.get(tranType));
				}
			}		
		}		
		model.addAttribute("ViewMerFeeDetailList", list);
		model.addAttribute("id", id);
		model.addAttribute("query", queryModel);
		//合计
		String tran_amt="0";
		String fee="0";		
		if(list!=null&&list.size()>0){
			String sumAmt=ViewMerFeeDetailService.getSumAmt(id);
			if(sumAmt!=null&&!"".equals(sumAmt)&&sumAmt.contains("#")){
				String[] arr=sumAmt.split("#");
				tran_amt=arr[0].toString();
				fee=arr[1].toString();
			}
		}
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("fee", fee);
		return "modules/fs/clearFeeBook/viewMerFeeDetailList";
	}
}
