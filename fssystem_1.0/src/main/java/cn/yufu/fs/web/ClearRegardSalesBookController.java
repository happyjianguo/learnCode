package cn.yufu.fs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yufu.cortex.entity.CortexArea;
import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearRegardSalesBook;
import cn.yufu.fs.entity.ClearRegardSalesDetBook;
import cn.yufu.fs.service.ClearRegardSalesBookService;
import cn.yufu.fs.service.ClearRegardSalesDetBookService;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value = "/ClearRegardSalesBook")
public class ClearRegardSalesBookController {
	Log log = Log.getLog(ClearRegardSalesBookController.class);

	@Autowired
	@Qualifier("fs.ClearRegardSalesBookService")
	private ClearRegardSalesBookService ClearRegardSalesBookService;

	@Autowired
	@Qualifier("fs.ClearRegardSalesDetBookService")
	private ClearRegardSalesDetBookService ClearRegardSalesDetBookService;

	@Autowired
	@Qualifier("cortex.CortexService")
	private CortexService CortexService;

	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "getList" })
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, ClearRegardSalesBook queryModel) {
		List<ClearRegardSalesBook> queryList = ClearRegardSalesBookService.queryList(queryModel);
		queryModel.setStartDt(getNowDt("yyyyMMdd",-1));
		queryModel.setEndDt(getNowDt("yyyyMMdd",-1));
		
		// 获取省份LIST
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = this.CortexService.getCortexAreaList(cortexArea);
		
		Map<Integer,String> cardType=new TreeMap<Integer, String>();
		for (ClearRegardSalesBook clearRegardSalesBook : queryList) {
			cardType.put(Integer.valueOf(clearRegardSalesBook.getCrdproduct()), clearRegardSalesBook.getCardTypeName());
		}
		
		model.addAttribute("cardType", cardType);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("query", queryModel);
		model.addAttribute("queryListLenght",0);
		
		return "modules/fs/clearRegardSalesBook/clearRegardSalesBookList";
	}
	
	
	/**
	 * 查询内容
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "page" })
	public String page(HttpServletRequest req, HttpServletResponse resp, Model model,
			ClearRegardSalesBook queryModel) {

		// 获取省份LIST
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = this.CortexService.getCortexAreaList(cortexArea);
		List<ClearRegardSalesBook> queryList = ClearRegardSalesBookService.queryList(queryModel);
		ClearRegardSalesBook queryModel2=new ClearRegardSalesBook();
		List<ClearRegardSalesBook> queryList2 = ClearRegardSalesBookService.queryList(queryModel2);
		model.addAttribute("queryListLenght", queryList.size());
		
		// 有序存储售卡城市列表和消费城市列表
		Map<Integer, String> salesCity = new TreeMap<Integer, String>();
		Map<Integer, String> comCity = new TreeMap<Integer, String>();
		Map<Integer,String> cardType=new TreeMap<Integer, String>();
		
		for (ClearRegardSalesBook clearRegardSalesBook : queryList) {
			salesCity.put(Integer.valueOf(clearRegardSalesBook.getSalesCity()),
					clearRegardSalesBook.getSalesCityName());
			comCity.put(Integer.valueOf(clearRegardSalesBook.getConsumCity()),
					clearRegardSalesBook.getConsumCityName());
			
		}
		
		for (ClearRegardSalesBook clearRegardSalesBook : queryList2) {
			cardType.put(Integer.valueOf(clearRegardSalesBook.getCrdproduct()), clearRegardSalesBook.getCardTypeName());
		}
		model.addAttribute("consumCityLenght", comCity.size());
		// 存储各个消费区域中 各个购卡区域消费数据 的集合
		Map<Integer, TreeMap<Integer, TreeMap<Integer, String>>> allMap = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, String>>>();
		// 判断是否 是新购卡区域 要合并数据的标志
		Map<String, String> map5 = new HashMap<String, String>();
				
		// 保存对应购卡区域 的合计数据
		Map<Integer, String> sumDataMap = new TreeMap<Integer, String>();
		Set<Integer> keySet = salesCity.keySet();
		// 标记是否 是最后一条的标志
		int t = 0;
		for (Integer key : keySet) {

			BigDecimal sumNum = new BigDecimal(0);
			BigDecimal sumAmt = new BigDecimal(0);

			TreeMap<Integer, TreeMap<Integer, String>> listSales = MyMap(comCity);
			for (int i = 0, j = 0; i < queryList.size(); j++) {

				ClearRegardSalesBook clearRegardSalesBook = queryList.get(i);

				TreeMap<Integer, String> dataMap = new TreeMap<Integer, String>();
				  
				if (j == 0) {
					// 将消费区域作为kEY存入一个map,用于判断是否是新的消费区域数据
					map5.put(clearRegardSalesBook.getSalesCity(), "Y");
					
				}
				// 如果当前数据 与上条数据是 同一购卡区域 数据
				if (StringUtils.isNotBlank(map5.get(clearRegardSalesBook.getSalesCity()))
						&& key.toString().equals(clearRegardSalesBook.getSalesCity())
						) {
					// 将数据以购卡区域--和消费数据格式存储到map中
					map5.put(clearRegardSalesBook.getSalesCity(), "Y");
					
					BigDecimal consumTranNum=clearRegardSalesBook.getTranNum();
					BigDecimal consumTranAmt=clearRegardSalesBook.getTranAmt();
					
					if(j!=0){
						TreeMap<Integer, String> dataMap1 = listSales.get(Integer.valueOf(clearRegardSalesBook.getConsumCity()));
						String string = dataMap1.get(Integer.valueOf(clearRegardSalesBook.getConsumCity()));
						if(StringUtils.isNoneBlank(string)){
							String[] tran = string.split(":");
							BigDecimal t1=new BigDecimal(tran[0]);
							BigDecimal t2=new BigDecimal(tran[1]);
							consumTranNum=consumTranNum.add(t1);
							consumTranAmt=consumTranAmt.add(t2);
						}
					}
					
					// 将每个消费区域数据存入一个map
					dataMap.put(Integer.valueOf(clearRegardSalesBook.getConsumCity()),
							consumTranNum.toString() + ":"
									+ consumTranAmt.toString());
					// 将词条数据的 消费区域map 存入当前购卡区域map
					listSales.put(Integer.valueOf(clearRegardSalesBook.getConsumCity()), dataMap);
					queryList.remove(clearRegardSalesBook);

					sumNum = sumNum.add(clearRegardSalesBook.getTranNum());
					sumAmt = sumAmt.add(clearRegardSalesBook.getTranAmt());
					
					continue;
				}

				// 当前数据是 新的消费区域 数据
				sumDataMap.put(key, sumNum.toString() + ":" + sumAmt.toString());
				// 重置数据
				sumNum = new BigDecimal(0);
				sumAmt = new BigDecimal(0);
				break;
			}

			t++;
			// 当数前数据为最后一条时
			if (t == keySet.size()) {
				sumDataMap.put(key, sumNum.toString() + ":" + sumAmt.toString());
			}

			// 向allMap存储当前购卡区域的数据
			allMap.put(key, listSales);
		}

		model.addAttribute("provinceList", provinceList);
		
		model.addAttribute("allMap", allMap);
		model.addAttribute("sumDataMap", sumDataMap);
		model.addAttribute("salesCity", salesCity);
		model.addAttribute("comCity", comCity);
		model.addAttribute("query", queryModel);
		model.addAttribute("cardType", cardType);

		return "modules/fs/clearRegardSalesBook/clearRegardSalesBookList";
	}

	/**
	 * 导出EXCEL
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = { "exportExcel" })
	@ResponseBody
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearRegardSalesBook info) {
		List<ClearRegardSalesBook> list = ClearRegardSalesBookService.queryList(info);
		List<ClearRegardSalesBook> listBak=new ArrayList<ClearRegardSalesBook>();
		listBak.addAll(list);
		//有序存储售卡城市列表和消费城市列表
		Map<Integer, String> salesCity =new TreeMap<Integer, String>();
		Map<Integer, String> comCity =new TreeMap<Integer, String>();
		for (ClearRegardSalesBook clearRegardSalesBook : list) {
			salesCity.put(Integer.valueOf(clearRegardSalesBook.getSalesCity()), clearRegardSalesBook.getSalesCityName());
			comCity.put(Integer.valueOf(clearRegardSalesBook.getConsumCity()), clearRegardSalesBook.getConsumCityName());
			
		}
		
		//存储各个消费区域中    各个购卡区域消费数据     的集合
		Map<Integer,TreeMap<Integer,TreeMap<Integer,String>>> allMap=new TreeMap<Integer, TreeMap<Integer,TreeMap<Integer,String>>>();
		
		//判断是否   是新区域  要合并数据的标志
		Map<String,String> map5=new HashMap<String, String>();
		
		//保存对应购卡区域  的合计数据
		Map<Integer, String> sumDataMap =new TreeMap<Integer, String>();
		Set<Integer> keySet = salesCity.keySet();
		Set<Integer> comCityKeySet = comCity.keySet();
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="售卡与消费区域分析表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/ClearRegardSalesBookReport.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet =work.getSheetAt(0);
			//设置表头和制表时间
			HSSFRow row = sheet.createRow((short) 1);
			
			short border=0;
			short cellBackgroundColor=HSSFColor.WHITE.index;
			String fontStyle="宋体";
			short  fontSize=16;
			
			HSSFCellStyle style = POIUtils.setStyle(work, cellBackgroundColor, fontStyle, fontSize,border);

            CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 0, 15);
            POIUtils.createCell(row, (short) 7, headName,style);
			row.setHeight((short)(27 * 20));
			
			row = sheet.getRow(2);
			border=1;
			cellBackgroundColor=HSSFColor.WHITE.index;
			fontStyle="宋体";
			fontSize=12;
			style = POIUtils.setStyle(work, cellBackgroundColor, fontStyle, fontSize, border);
			String typeName="";
			if(info!=null&&"--请选择--".equals(info.getCardTypeName())){
				typeName="全部";
			}else if(info!=null){
				typeName=info.getCardTypeName().trim();
			}
			POIUtils.createCell(row, (short) 0, "卡类型："+typeName, style);
			POIUtils.createCell(row, (short) 1, "交易时间："+info.getStartDt()+"--"+info.getEndDt(), style);
			
			
			int startRow=3;//多少行开始循环填数据行数，从0开始	
			//获取样式
			row = sheet.getRow(startRow);
			
			HSSFCell cell = row.getCell(0);
			style = cell.getCellStyle();
			//输出表头部分
			row = sheet.createRow(startRow);
			//列数
			short colum=0;
			border=1;
			cellBackgroundColor=HSSFColor.LIGHT_GREEN.index;
			
			fontSize=14;
			
			HSSFCellStyle style1 = POIUtils.setStyle(work, cellBackgroundColor,fontStyle, fontSize, border);
			
			POIUtils.createCell(row, colum,"消费区域",style1);
			
			
			row.setHeight((short)(25 * 20));
			
			for(Integer key: comCityKeySet){
				// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列
				cellRangeAddress = new CellRangeAddress(startRow, startRow, colum*2+1, (colum+1)*2);
				sheet.addMergedRegion(cellRangeAddress);	
				POIUtils.createCell(row, (short) (colum*2+1),comCity.get(key),style1);
				//设置合并之后的单元格格式
				setBorder(work, sheet, cellRangeAddress,border);
				row.setHeight((short)(25 * 20));
				colum++;
			}
			cellRangeAddress =new CellRangeAddress(startRow, startRow, colum*2+1, (colum+1)*2);
			sheet.addMergedRegion(cellRangeAddress);
			
			POIUtils.createCell(row, (short) (colum*2+1),"合计",style1);
			
			row = sheet.createRow(startRow+1);
			colum=0;
			POIUtils.createCell(row, colum++,"购卡区域",style1);
			cell.setCellStyle(style1);
			setBorder(work, sheet, cellRangeAddress,border);
			
			row.setHeight((short)(25 * 20));
			for(Integer key: comCityKeySet){
				POIUtils.createCell(row, colum++,"笔数",style1);
				POIUtils.createCell(row, colum++,"金额",style1);
			}
			
			POIUtils.createCell(row, colum++,"笔数",style1);
			POIUtils.createCell(row, colum++,"金额",style1);
			
			//标记是否    是最后一条的标志
			int o=0,t=0;
			
			for ( Integer key: keySet) {
				BigDecimal sumNum=new BigDecimal(0);
				BigDecimal sumAmt=new BigDecimal(0);
				
				TreeMap<Integer, TreeMap<Integer, String>> listSales = MyMap(comCity);
				for (int i=0,j=0;i<list.size();j++) {
					
					ClearRegardSalesBook clearRegardSalesBook=list.get(i);
					
					TreeMap<Integer, String> dataMap =new TreeMap<Integer, String>();
					
					/*if(j==0){//首条数据时map必须有值
						//将消费区域作为key存入一个map,用于判断是否是新的消费区域数据
						map5.put(clearRegardSalesBook.getSalesCity(), "Y");
					}*/
					String salesCity2 = clearRegardSalesBook.getSalesCity();
					//如果是首条数据。。或者当前数据  与上条数据是  同一购卡区域   数据
					if(j==0||(StringUtils.isNotBlank(map5.get(salesCity2))&&key.toString().equals(salesCity2))){
						//将数据以购卡区域--和消费数据格式存储到map中
						map5.put(salesCity2, "Y");
						
						BigDecimal consumTranNum=clearRegardSalesBook.getTranNum();
						BigDecimal consumTranAmt=clearRegardSalesBook.getTranAmt();
						if(j!=0){
							TreeMap<Integer, String> dataMap1 = listSales.get(Integer.valueOf(clearRegardSalesBook.getConsumCity()));
							String string = dataMap1.get(Integer.valueOf(clearRegardSalesBook.getConsumCity()));
							if(StringUtils.isNoneBlank(string)){
								String[] tran = string.split(":");
								BigDecimal t1=new BigDecimal(tran[0]);
								BigDecimal t2=new BigDecimal(tran[1]);
								consumTranNum=consumTranNum.add(t1);
								consumTranAmt=consumTranAmt.add(t2);
							}
						}
						
						
						
						// 将每个消费区域数据存入一个map
						dataMap.put(Integer.valueOf(clearRegardSalesBook.getConsumCity()),
								consumTranNum.toString() + ":"
										+ consumTranAmt.toString());
						//将词条数据的  消费区域map   存入当前购卡区域map
						listSales.put(Integer.valueOf(clearRegardSalesBook.getConsumCity()),dataMap);
						list.remove(clearRegardSalesBook);
						
						sumNum=sumNum.add(clearRegardSalesBook.getTranNum());
						sumAmt=sumAmt.add(clearRegardSalesBook.getTranAmt());
						continue;
					}
					
					//当前数据是   新的消费区域   数据
					sumDataMap.put(key, sumNum.toString()+":"+sumAmt.toString());
					//重置数据
					sumNum=new BigDecimal(0);
					sumAmt=new BigDecimal(0);
					break;
				}
				
				o++;
				//当数前数据为最后一条时
				if(o==keySet.size()){
					sumDataMap.put(key, sumNum.toString()+":"+sumAmt.toString());
				}		
				//向allMap存储当前购卡区域的数据
				allMap.put(key,listSales);
			}	
			
			for (Integer key: keySet) {
				row = sheet.createRow(startRow+2+t++);
				row.setHeight((short)(25 * 20));
				//每次遍历列数置空
				colum=0;
				POIUtils.createCell(row, colum++,salesCity.get(key),style);
				
				TreeMap<Integer,TreeMap<Integer,String>> salesCityData = allMap.get(key);
				for(Integer comKey: comCityKeySet){//购卡城市消费中每个消费城市消费信息
					TreeMap<Integer, String> comCityData = salesCityData.get(comKey);
					String string = comCityData.get(comKey);
					String[] data = string.split(":");
					POIUtils.createCell(row, colum++,data[0],style);
					POIUtils.createCell(row, colum++,data[1],style);
				}
				//购卡城市合计信息
				String[] sumData = sumDataMap.get(key).split(":");
				POIUtils.createCell(row, colum++,sumData[0],style);
				POIUtils.createCell(row, colum++,sumData[1],style);
			}
			
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename="+filename+".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}			
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			    log.error("ClearRegardSalesBookController.exportExcel()调用出现异常。");
		}		
		
		return null;
	}


	/**
	 * 
	 * @param work  工作簿
	 * @param sheet  工作sheet
	 * @param cellRangeAddress  合并后的单元格区域
	 * @param border  边框宽度
	 */
	private void setBorder(HSSFWorkbook work, HSSFSheet sheet, CellRangeAddress cellRangeAddress,short border) {
		RegionUtil.setBorderBottom(border, cellRangeAddress, sheet, work);
		RegionUtil.setBorderLeft(border, cellRangeAddress, sheet, work);
		RegionUtil.setBorderRight(border, cellRangeAddress, sheet, work);
		RegionUtil.setBorderTop(border, cellRangeAddress, sheet, work);
	}

	

	/**
	 * 初始化数据
	 * 
	 * @param comCity
	 * @return
	 */
	private TreeMap<Integer, TreeMap<Integer, String>> MyMap(Map<Integer, String> comCity) {
		TreeMap<Integer, TreeMap<Integer, String>> list = new TreeMap<Integer, TreeMap<Integer, String>>();
		Set<Integer> keySet = comCity.keySet();

		for (Integer key : keySet) {
			TreeMap<Integer, String> dataMap = new TreeMap<Integer, String>();
			dataMap.put(key, "0:0");
			list.put(key, dataMap);
		}

		return list;
	}

	/**
	 * 跳转查看页面ClearRegardSalesBookService
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toShow" })
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,
			String ClearRegardSalesBookId) {
		ClearRegardSalesBook info = ClearRegardSalesBookService.queryInfo(ClearRegardSalesBookId);

		model.addAttribute("info", info);

		return "modules/fs/ClearRegardSalesBook/ClearRegardSalesBookShow";
	}

	/**
	 * 取前一月日期
	 * 
	 * @param pattern
	 * @return
	 */
	public String getLastMonthDay(String pattern) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 得到前一月
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat(pattern);// 设置日期格式
		return df.format(date);
	}

	/**
	 * 获取当前时间
	 * 
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public String getNowDt(String pattern, int flag) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, flag); // 得到前一月
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat(pattern);// 设置日期格式
		return df.format(date);
	}

	/**
	 * 查询单条明细
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
	public String getDetailList(HttpServletRequest req, HttpServletResponse resp, Model model,
			ClearRegardSalesDetBook queryModel) {
		
		List<ClearRegardSalesDetBook> list = ClearRegardSalesDetBookService.queryList(queryModel);

		if (list != null && list.size()>0) {
			queryModel.setSalesCityName(list.get(0).getSalesCityName());
		}

		// 合计
		BigDecimal tran_amt = new BigDecimal(0);
		BigDecimal tran_num = new BigDecimal(0);
		for (ClearRegardSalesDetBook clearRegardSalesDetBook : list) {
			tran_num = tran_num.add(clearRegardSalesDetBook.getTranNum());
			tran_amt = tran_amt.add(clearRegardSalesDetBook.getTranAmt());

		}
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("tran_num", tran_num);
		model.addAttribute("size", list.size());
		model.addAttribute("ViewClearRegardSalesBook", list);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/clearRegardSalesBook/viewRegardSalesBookDetailList";
	}

	/**
	 * 导出单条明细
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param curPage
	 * @param pageSize
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = { "exportDetailList" })
	@ResponseBody
	public String exportDetailList(HttpServletRequest req, HttpServletResponse resp, Model model,
			ClearRegardSalesDetBook queryModel) {
		
		List<ClearRegardSalesDetBook> list = ClearRegardSalesDetBookService.queryList(queryModel);
		List<ClearRegardSalesDetBook> listSum = ClearRegardSalesDetBookService
				.queryListSum(queryModel);
		if (list != null && list.get(0) != null) {
			queryModel.setSalesCityName(list.get(0).getSalesCityName());
		}

		// 合计
		BigDecimal tran_amt = new BigDecimal(0);
		BigDecimal tran_num = new BigDecimal(0);
		for (ClearRegardSalesDetBook clearRegardSalesDetBook : list) {
			tran_num = tran_num.add(clearRegardSalesDetBook.getTranNum());
			tran_amt = tran_amt.add(clearRegardSalesDetBook.getTranAmt());

		}
		// 消费区域合计
		Map<Long, ClearRegardSalesDetBook> sumMap = new HashMap<Long, ClearRegardSalesDetBook>();

		for (ClearRegardSalesDetBook clearRegardSalesDetBook : listSum) {
			sumMap.put(clearRegardSalesDetBook.getConsumCity(), clearRegardSalesDetBook);
		}

		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		try {
			String headName = queryModel.getSalesCityName() + "购卡消费区域统计表 ("
					+ this.getNowDt("yyyyMMddHHmmss", 0) + ")";
			// 选择模板文件：
			String path = req.getSession().getServletContext().getRealPath("/")
					+ "reportTemp/clearRegardSalesDetReport.xls"; // excel模板
			InputStream in = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(in);
			// 得到excel的第1个sheet
			HSSFSheet sheet = work.getSheetAt(0);
			// 设置表头和制表时间
			HSSFRow row = sheet.createRow((short) 1);
			short border=0;
			short cellBackgroundColor=HSSFColor.WHITE.index;
			String fontStyle="宋体";
			short  fontSize=16;
			HSSFCellStyle style = POIUtils.setStyle(work, cellBackgroundColor, fontStyle, fontSize, border);
			
			POIUtils.createCell(row, (short) 4, headName, style);
			row.setHeight((short) (27 * 20));

			row = sheet.getRow(2);
			border=1;
			cellBackgroundColor=HSSFColor.WHITE.index;
			fontStyle="宋体";
			fontSize=12;
			style = POIUtils.setStyle(work, cellBackgroundColor, fontStyle, fontSize, border);
			
			POIUtils.createCell(row, (short) 0, "购卡区域："+queryModel.getSalesCityName(), style);
			POIUtils.createCell(row, (short) 1, "交易时间："+queryModel.getStartDt()+"--"+queryModel.getEndDt(), style);
			
			int startRow = 4;// 表头往上多少行,开始循环填数据行数，从0开始
			// 获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();
			ClearRegardSalesDetBook cce = null;
			int changeRowNum = 0;// 增加城市合计 的行数
			Long cityCode = 0l;// 记录上一条记录的消费区域编号
			Long newCityCode = 0l;// 记录当前一条记录的消费区域编号
			// 记录在每个消费区域有消费记录的商户数量
			int n = 0;
			for (int i = 0; i < list.size(); i++) {
				cce = list.get(i);

				if (cce != null) {

					if (i == 0) {
						cityCode = newCityCode = list.get(i).getConsumCity();
					}
					newCityCode = cce.getConsumCity();

					// 如果当前消费区域不等于上一消费记录消费区域 则记录一条合计信息
					if (cityCode - newCityCode != 0) {

						ClearRegardSalesDetBook clearRegardSalesDetBook = sumMap.get(cityCode);
						changeRowNum = changeRowNum + 1;
						citySum(sheet, style, startRow, clearRegardSalesDetBook, i + changeRowNum
								- 1, n);
						cityCode = newCityCode;
						n = 0;
					}

					n++;

					row = sheet.createRow(startRow + i + changeRowNum);
					row.setHeight((short) (25 * 20));

					POIUtils.createCell(row, (short) 0, cce.getMerNo(), style);
					POIUtils.createCell(row,(short) 1,cce.getMerName() == null || "".equals(cce.getMerName()) ? "" : cce.getMerName(), style);
					POIUtils.createCell(row, (short) 2,cce.getTranNum() == null || "".equals(cce.getTranNum()) ? "" : cce.getTranNum().toString(), style);
					POIUtils.createCell(row, (short) 3,cce.getTranAmt() == null || "".equals(cce.getTranAmt()) ? "" : cce.getTranAmt().toString(), style);
					POIUtils.createCell(row,(short) 4,cce.getConsumCityName() == null || "".equals(cce.getConsumCityName()) ? "": cce.getConsumCityName(), style);
				}

			}

			// 得到最后一个城市合计信息并 设置全国合计
			if (list.size() > 0) {
				ClearRegardSalesDetBook clearRegardSalesDetBook = sumMap.get(list.get(
						list.size() - 1).getConsumCity());
				citySum(sheet, style, startRow, clearRegardSalesDetBook,
						list.size() + changeRowNum, n);
				// 设置全国合计
				ClearRegardSalesDetBook clearRegardSalesDetBookAll = new ClearRegardSalesDetBook();
				clearRegardSalesDetBookAll.setConsumCityName("全国");
				clearRegardSalesDetBookAll.setTranAmt(tran_amt);
				clearRegardSalesDetBookAll.setTranNum(tran_num);
				citySum(sheet, style, startRow, clearRegardSalesDetBookAll, list.size()
						+ changeRowNum + 1, list.size());
			}

			/**************************** 输出流 *****************************************/
			OutputStream os = resp.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(req, headName);
			resp.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
			work.write(os);
			if (in != null) {
				in.close();
			}
			os.close();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ClearRegardSalesBookController.exportExcel()调用出现异常。");
		}
		return null;
	}

	// 每个城市合计信息
	private void citySum(HSSFSheet sheet, HSSFCellStyle style, int startRow,
			ClearRegardSalesDetBook clearRegardSalesDetBook, int i, int n) {
		HSSFRow row;
		// 创建每个城市合计行

		row = sheet.createRow(startRow + i);
		row.setHeight((short) (25 * 20));
		// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列

		POIUtils.createCell(row, (short) 0, clearRegardSalesDetBook.getConsumCityName() + "合计",
				style);
		POIUtils.createCell(row, (short) 1, n + "", style);
		POIUtils.createCell(row, (short) 2, clearRegardSalesDetBook.getTranNum().toString(), style);
		POIUtils.createCell(row, (short) 3, clearRegardSalesDetBook.getTranAmt().toString(), style);
		POIUtils.createCell(row, (short) 4, "", style);

	}

}
