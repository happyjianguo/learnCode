package cn.yufu.fs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import org.apache.poi.hssf.usermodel.HSSFFont;
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
import cn.yufu.fs.entity.ClearMermccBook;
import cn.yufu.fs.entity.ClearMermccDetailBook;
import cn.yufu.fs.entity.SysParameter;
import cn.yufu.fs.service.ClearMermccBookService;
import cn.yufu.fs.service.ClearMermccDetailBookService;
import cn.yufu.fs.service.SysParameterService;
import cn.yufu.system.common.Page;
import cn.yufu.system.common.utils.HttpServletRequestUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.POIUtils;
import cn.yufu.system.common.utils.StringUtils;

@Controller
@RequestMapping(value = "/ClearMermccBook")
public class ClearMermccBookController {
	Log log = Log.getLog(ClearMermccBookController.class);

	@Autowired
	@Qualifier("fs.ClearMermccBookService")
	private ClearMermccBookService ClearMermccBookService;

	@Autowired
	@Qualifier("fs.ClearMermccDetailBookService")
	private ClearMermccDetailBookService ClearMermccDetailBookService;

	@Autowired
	@Qualifier("cortex.CortexService")
	private CortexService CortexService;
	
	@Autowired
	@Qualifier("fs.SysParameterService")
	private SysParameterService SysParameterService;

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
	public String getList(HttpServletRequest req, HttpServletResponse resp, Model model, ClearMermccBook queryModel) {
		queryModel.setStartDt(getNowDt("yyyyMMdd",-1));
		queryModel.setEndDt(getNowDt("yyyyMMdd",-1));
		
		SysParameter sysParameter=new SysParameter();
		//sysParameter.setParamType("CARDBATCH_MER_TYPE");
		List<SysParameter> sysParamList = SysParameterService.queryList(sysParameter);
		Map<Integer, String> consumTypes = new TreeMap<Integer, String>();
		for (SysParameter sysParam : sysParamList) {
			consumTypes.put(sysParam.getParamId(), sysParam.getParamName());
		}
		
		// 获取省份LIST
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = this.CortexService.getCortexAreaList(cortexArea);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("queryListLenght", 0);
		model.addAttribute("consumTypes", consumTypes);	
		model.addAttribute("queryModel", queryModel);
		model.addAttribute("queryListLenght",0);
		return "modules/fs/clearMermccBook/clearMermccBookList";
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
			ClearMermccBook queryModel) {

		// 获取省份LIST
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = this.CortexService.getCortexAreaList(cortexArea);

		List<ClearMermccBook> queryList = ClearMermccBookService.queryList(queryModel);
		SysParameter sysParameter=new SysParameter();
		//sysParameter.setParamType("CARDBATCH_MER_TYPE");
		List<SysParameter> sysParamList = SysParameterService.queryList(sysParameter);
		model.addAttribute("queryListLenght", queryList.size());
		// 有序存储消费类型列表和消费城市列表
		Map<Integer, String> consumTypes = new TreeMap<Integer, String>();
		Map<Integer, String> consumTypes1 = new TreeMap<Integer, String>();
		Map<Integer, String> comCity = new TreeMap<Integer, String>();
		for (ClearMermccBook ClearMermccBook : queryList) {
			consumTypes1.put(Integer.valueOf(ClearMermccBook.getConsumType()), ClearMermccBook.getConsumTypeName());
			comCity.put(Integer.valueOf(ClearMermccBook.getConsumCity()),ClearMermccBook.getConsumCityName());

		}
		
		for (SysParameter sysParam : sysParamList) {
			consumTypes.put(sysParam.getParamId(), sysParam.getParamName());
		}
		model.addAttribute("consumCityLenght", comCity.size());
		// 存储各个消费区域中 各个购卡区域消费数据 的集合
		Map<Integer, TreeMap<Integer, TreeMap<Integer, String>>> allMap = new TreeMap<Integer, TreeMap<Integer, TreeMap<Integer, String>>>();

		// 判断是否 是新区域 要合并数据的标志
		Map<String, String> map5 = new HashMap<String, String>();
		// 保存对应消费类型的合计数据
		Map<Integer, String> sumDataMap = new TreeMap<Integer, String>();
		Set<Integer> keySet = consumTypes1.keySet();
		// 标记是否 是最后一条的标志
		int t = 0;
		for (Integer key : keySet) {

			BigDecimal sumNum = new BigDecimal(0);
			BigDecimal sumAmt = new BigDecimal(0);

			TreeMap<Integer, TreeMap<Integer, String>> listSales = MyMap(comCity);
			for (int i = 0, j = 0; i < queryList.size(); j++) {

				ClearMermccBook clearMermccBook = queryList.get(i);

				TreeMap<Integer, String> dataMap = new TreeMap<Integer, String>();
				String consumCity = clearMermccBook.getConsumCity();
				String consumType=clearMermccBook.getConsumType();
				if (j == 0) {
					// 将消费区域作为kEY存入一个map,用于判断是否是新的消费区域数据
					map5.put(consumType, "Y");
				}
				// 如果当前数据 与上条数据是 同一消费类型   数据
				if (StringUtils.isNotBlank(map5.get(consumType))&& key.toString().equals(consumType)) {
					// 将数据以购卡区域--和消费数据格式存储到map中
					map5.put(consumType, "Y");
					
					BigDecimal consumTranNum=clearMermccBook.getTranNum();
					BigDecimal consumTranAmt=clearMermccBook.getTranAmt();
					
					if(j!=0){
						TreeMap<Integer, String> dataMap1 = listSales.get(Integer.valueOf(clearMermccBook.getConsumCity()));
						String string = dataMap1.get(Integer.valueOf(clearMermccBook.getConsumCity()));
						if(StringUtils.isNoneBlank(string)){
							String[] tran = string.split(":");
							BigDecimal t1=new BigDecimal(tran[0]);
							BigDecimal t2=new BigDecimal(tran[1]);
							consumTranNum=consumTranNum.add(t1);
							consumTranAmt=consumTranAmt.add(t2);
						}
					}

					// 将每个消费区域数据存入一个map
					dataMap.put(Integer.valueOf(consumCity),consumTranNum.toString() + ":"+ consumTranAmt.toString());
					// 将词条数据的 消费区域map 存入当前购卡区域map
					listSales.put(Integer.valueOf(consumCity), dataMap);
					queryList.remove(clearMermccBook);

					sumNum = sumNum.add(clearMermccBook.getTranNum());
					sumAmt = sumAmt.add(clearMermccBook.getTranAmt());
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
		model.addAttribute("consumTypes", consumTypes);
		model.addAttribute("consumTypes1", consumTypes1);
		model.addAttribute("comCity", comCity);
		model.addAttribute("queryModel", queryModel);

		return "modules/fs/clearMermccBook/clearMermccBookList";
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
	public String exportExcel(HttpServletRequest req,HttpServletResponse resp, Model model, ClearMermccBook info) {
		List<ClearMermccBook> queryList = ClearMermccBookService.queryList(info);
		
		// 有序存储售卡城市列表和消费城市列表
		Map<Integer, String> consumTypes = new TreeMap<Integer, String>();
		Map<Integer, String> comCity = new TreeMap<Integer, String>();
		for (ClearMermccBook ClearMermccBook : queryList) {
			consumTypes.put(Integer.valueOf(ClearMermccBook.getConsumType()),ClearMermccBook.getConsumTypeName());
			comCity.put(Integer.valueOf(ClearMermccBook.getConsumCity()),ClearMermccBook.getConsumCityName());

		}
	
		//存储各个消费区域中    各个购卡区域消费数据     的集合
		Map<Integer,TreeMap<Integer,TreeMap<Integer,String>>> allMap=new TreeMap<Integer, TreeMap<Integer,TreeMap<Integer,String>>>();
		
		//判断是否   是新区域  要合并数据的标志
		Map<String,String> map5=new HashMap<String, String>();
		
		//保存对应购卡区域  的合计数据
		Map<Integer, String> sumDataMap =new TreeMap<Integer, String>();
		Set<Integer> keySet = consumTypes.keySet();
		Set<Integer> comCityKeySet = comCity.keySet();
		
		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try{
			String headName="消费类型与消费区域分析表 ("+this.getNowDt("yyyyMMddHHmmss",0)+")";
			// 选择模板文件：				
			String path =req.getSession().getServletContext().getRealPath("/")+ "reportTemp/ClearMermccBookReport.xls"; // excel模板
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
			/*style = row.getCell(1).getCellStyle();*/
			String typeName="";
			if(info!=null&&"--请选择--".equals(info.getConsumTypeName())){
				typeName="全部";
			}else if(info!=null){
				typeName=info.getConsumTypeName();
			}
			POIUtils.createCell(row, (short) 0, "商户类型："+typeName, style);
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
			POIUtils.createCell(row, colum++,"消费类型",style1);
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
				for (int i=0,j=0;i<queryList.size();j++) {
					
					ClearMermccBook clearMermccBook=queryList.get(i);
					
					TreeMap<Integer, String> dataMap =new TreeMap<Integer, String>();
					String ConsumCity2 = clearMermccBook.getConsumCity();
					String consumType = clearMermccBook.getConsumType();
					if(j==0){//首条数据时map必须有值
						//将消费区域作为key存入一个map,用于判断是否是新的消费区域数据
						map5.put(consumType, "Y");
					}
					
					//如果是首条数据。。或者当前数据  与上条数据是  同一购卡区域   数据
					if(StringUtils.isNotBlank(map5.get(consumType))&&key.toString().equals(consumType)){
						//将数据以购卡区域--和消费数据格式存储到map中
						map5.put(ConsumCity2, "Y");
						
						
						BigDecimal consumTranNum=clearMermccBook.getTranNum();
						BigDecimal consumTranAmt=clearMermccBook.getTranAmt();
						
						if(j!=0){
							TreeMap<Integer, String> dataMap1 = listSales.get(Integer.valueOf(clearMermccBook.getConsumCity()));
							String string = dataMap1.get(Integer.valueOf(clearMermccBook.getConsumCity()));
							if(StringUtils.isNoneBlank(string)){
								String[] tran = string.split(":");
								BigDecimal t1=new BigDecimal(tran[0]);
								BigDecimal t2=new BigDecimal(tran[1]);
								consumTranNum=consumTranNum.add(t1);
								consumTranAmt=consumTranAmt.add(t2);
							}
						}

						
						//将每个消费区域数据存入一个map
						String consumCity = clearMermccBook.getConsumCity();
						dataMap.put(Integer.valueOf(consumCity), consumTranNum.toString()+":"+consumTranAmt.toString());
						//将词条数据的  消费区域map   存入当前购卡区域map
						listSales.put(Integer.valueOf(consumCity),dataMap);
						queryList.remove(clearMermccBook);
						
						sumNum=sumNum.add(clearMermccBook.getTranNum());
						sumAmt=sumAmt.add(clearMermccBook.getTranAmt());
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
				POIUtils.createCell(row, colum++,consumTypes.get(key),style);
				
				TreeMap<Integer,TreeMap<Integer,String>> ConsumCityData = allMap.get(key);
				for(Integer comKey: comCityKeySet){//购卡城市消费中每个消费城市消费信息
					TreeMap<Integer, String> comCityData = ConsumCityData.get(comKey);
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
			resp.setHeader("Content-disposition","attachment;filename=" + filename+ ".xls");
			work.write(os);
			if(in!=null){
				in.close();
			}			
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			    log.error("ClearMermccBookController.exportExcel()调用出现异常。");
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
	 * 跳转查看页面ClearMermccBookService
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "toShow" })
	public String toShow(HttpServletRequest req, HttpServletResponse resp, Model model,
			String ClearMermccBookId) {
		ClearMermccBook info = ClearMermccBookService.queryInfo(ClearMermccBookId);

		model.addAttribute("info", info);

		return "modules/fs/ClearMermccBook/ClearMermccBookShow";
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
			 ClearMermccDetailBook queryModel) {
		
		List<ClearMermccDetailBook> list = ClearMermccDetailBookService.queryList(queryModel);

		if (list != null && list.size()>0) {
			queryModel.setConsumTypeName(list.get(0).getConsumTypeName());
		}

		// 合计
		BigDecimal tran_amt = new BigDecimal(0);
		BigDecimal tran_num = new BigDecimal(0);
		for (ClearMermccDetailBook ClearMermccDetailBook : list) {
			tran_num = tran_num.add(ClearMermccDetailBook.getTranNum());
			tran_amt = tran_amt.add(ClearMermccDetailBook.getTranAmt());

		}
		model.addAttribute("tran_amt", tran_amt);
		model.addAttribute("tran_num", tran_num);
		model.addAttribute("size", list.size());
		model.addAttribute("ViewClearMermccBook", list);
		model.addAttribute("queryModel", queryModel);
		return "modules/fs/clearMermccBook/viewMermccBookDetailList";
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
			 ClearMermccDetailBook queryModel) {
		
		List<ClearMermccDetailBook> list = ClearMermccDetailBookService.queryList(queryModel);
		List<ClearMermccDetailBook> listSum = ClearMermccDetailBookService
				.queryListSum(queryModel);
		if (list != null && list.get(0) != null) {
			queryModel.setConsumTypeName(list.get(0).getConsumTypeName());
		}

		// 合计
		BigDecimal tran_amt = new BigDecimal(0);
		BigDecimal tran_num = new BigDecimal(0);
		for (ClearMermccDetailBook ClearMermccDetailBook : list) {
			tran_num = tran_num.add(ClearMermccDetailBook.getTranNum());
			tran_amt = tran_amt.add(ClearMermccDetailBook.getTranAmt());

		}
		// 消费区域合计
		Map<Long, ClearMermccDetailBook> sumMap = new HashMap<Long, ClearMermccDetailBook>();

		for (ClearMermccDetailBook ClearMermccDetailBook : listSum) {
			sumMap.put(ClearMermccDetailBook.getConsumCity(), ClearMermccDetailBook);
		}

		resp.setContentType("application/vnd.ms-excel;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		try {
			String headName = queryModel.getConsumTypeName() + "类型--消费区域统计表 ("
					+ this.getNowDt("yyyyMMddHHmmss", 0) + ")";
			// 选择模板文件：
			String path = req.getSession().getServletContext().getRealPath("/")
					+ "reportTemp/clearMermccDetailReport.xls"; // excel模板
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
			/*style = row.getCell(1).getCellStyle();*/
			
			POIUtils.createCell(row, (short) 0, "商户类型："+queryModel.getConsumTypeName(), style);
			POIUtils.createCell(row, (short) 1, "交易时间："+queryModel.getStartDt()+"--"+queryModel.getEndDt(), style);
			int startRow = 4;// 表头往上多少行,开始循环填数据行数，从0开始
			// 获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();
			ClearMermccDetailBook cce = null;
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

						ClearMermccDetailBook ClearMermccDetailBook = sumMap.get(cityCode);
						changeRowNum = changeRowNum + 1;
						citySum(sheet, style, startRow, ClearMermccDetailBook, i + changeRowNum
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
					POIUtils.createCell(row, (short) 4, cce.getConsumCityName()== null || "".equals(cce.getConsumCityName()) ? "":cce.getConsumCityName(), style);
				}

			}

			// 得到最后一个城市合计信息并 设置全国合计
			if (list.size() > 0) {
				ClearMermccDetailBook ClearMermccDetailBook = sumMap.get(list.get(list.size() - 1).getConsumCity());
				// 得到最后一个城市合计信息
				citySum(sheet, style, startRow, ClearMermccDetailBook,list.size() + changeRowNum, n);
				
				// 设置全国合计
				ClearMermccDetailBook ClearMermccDetailBookAll = new ClearMermccDetailBook();
				ClearMermccDetailBookAll.setConsumCityName("全国");
				ClearMermccDetailBookAll.setTranAmt(tran_amt);
				ClearMermccDetailBookAll.setTranNum(tran_num);
				citySum(sheet, style, startRow, ClearMermccDetailBookAll, list.size()
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
			log.error("ClearMermccBookController.exportExcel()调用出现异常。");
		}
		return null;
	}

	// 每个城市合计信息
	private void citySum(HSSFSheet sheet, HSSFCellStyle style, int startRow,
			ClearMermccDetailBook ClearMermccDetailBook, int i, int n) {
		HSSFRow row;
		// 创建每个城市合计行

		row = sheet.createRow(startRow + i);
		row.setHeight((short) (25 * 20));
		// 合并单元格 四个参数分别是：起始行，结束行，起始列，结束列

		POIUtils.createCell(row, (short) 0, ClearMermccDetailBook.getConsumCityName() + "合计",
				style);
		POIUtils.createCell(row, (short) 1, n+"", style);
		POIUtils.createCell(row, (short) 2, ClearMermccDetailBook.getTranNum().toString(), style);
		POIUtils.createCell(row, (short) 3, ClearMermccDetailBook.getTranAmt().toString(), style);
		POIUtils.createCell(row, (short) 4, "", style);

	}
	
	/**
	 * 加载初始页
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @param queryModel
	 * @return
	 */
	@RequestMapping(value = "initPage")
	public String initPage(HttpServletRequest req, HttpServletResponse resp, Model model, 
			ClearMermccBook queryModel) {
		// 获取省份LIST
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = this.CortexService.getCortexAreaList(cortexArea);
		model.addAttribute("provinceList", provinceList);
		
		model.addAttribute("curPage", 1);
		model.addAttribute("pageSize", 30);
		model.addAttribute("queryModel", queryModel);
		
		return "modules/fs/clearMermccBook/clearMermccTotalList";
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
	@RequestMapping(value = "list")
	public String list(HttpServletRequest req, HttpServletResponse resp, Model model,
			int curPage, int pageSize, ClearMermccBook queryModel) {

		// 获取省份LIST
		CortexArea cortexArea = new CortexArea();
		cortexArea.setFid(0l);
		cortexArea.setIsuse("1");
		List<CortexArea> provinceList = this.CortexService.getCortexAreaList(cortexArea);
		model.addAttribute("provinceList", provinceList);
		
		if (StringUtils.isNotBlank(queryModel.getConsumProvince())) {
			cortexArea.setFid(Long.valueOf(queryModel.getConsumProvince()));
			List<CortexArea> cityList = CortexService.getCortexAreaList(cortexArea);
			model.addAttribute("cityList", cityList);
		}
		
		// 分页设置
		int count = ClearMermccBookService.getCount(queryModel);
		Page page = new Page(curPage, pageSize, count);
		curPage = page.getCurPage();
		page.style1();
		String url = req.getRequestURI().toString();
		Map<String, String> param = HttpServletRequestUtil.getNotEmptyRepuestParameterToMap(req);
		String pageBar = page.createPageBar(url, param);
		int startResult = (curPage - 1) * pageSize;
		int endResult = curPage * pageSize;
		
		List<ClearMermccBook> queryList = ClearMermccBookService.getTotalAmtPage(queryModel, startResult, endResult);
		model.addAttribute("queryList", queryList);
		
		//总笔数, 总金额
		ClearMermccBook total = ClearMermccBookService.getTotal(queryModel);
		if (total == null) {
			total = new ClearMermccBook();
			total.setTranNum(new BigDecimal(0));
			total.setTranAmt(new BigDecimal(0));
		}
		if (total != null && total.getTranNum() == null) {
			total.setTranNum(new BigDecimal(0));
		}
		if (total != null && total.getTranAmt() == null) {
			total.setTranAmt(new BigDecimal(0));
		}
		model.addAttribute("total", total);
		model.addAttribute("curPage", curPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageBar", pageBar);	
		model.addAttribute("queryModel", queryModel);
		
		return "modules/fs/clearMermccBook/clearMermccTotalList";
	}
	
	/**
	 * 导出EXCEL
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "exportExcelTotal")
	@ResponseBody
	public String exportExcel(HttpServletRequest request,HttpServletResponse response, 
				int curPage, int pageSize, ClearMermccBook info) {
		List<ClearMermccBook> list = ClearMermccBookService.getTotalAmt(info);
		int listSize = (list == null ? 0 : list.size());
		
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			String headName="售卡消费统计报表 ("+this.getNowDt("yyyy-MM-dd HH:mm:ss",0)+")";
			// 选择模板文件：				
			String path =request.getSession().getServletContext().getRealPath("/")+ "reportTemp/ClearMermccTotalReport.xls"; // excel模板
			InputStream inputStream = new FileInputStream(new File(path));
			HSSFWorkbook work = new HSSFWorkbook(inputStream);
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
			
			int startRow = 3;//表头往上多少行,开始循环填数据行数，从0开始			
			//获取样式
			row = sheet.getRow(startRow);
			style = row.getCell(2).getCellStyle();		

			ClearMermccBook soen = null;	
			for (int i = 0; i < listSize; i++) {
				soen = list.get(i);
				if(soen!=null){					
					row = sheet.createRow(startRow+i);
					row.setHeight((short)(25 * 20));
					POIUtils.createCell(row, (short) 0,i+1+"",style);
					POIUtils.createCell(row, (short) 1,soen.getConsumCityName()==null||"".equals(soen.getConsumCityName())?"":soen.getConsumCityName(),style);
					POIUtils.createCell(row, (short) 2,soen.getTranNum()==null?"0":soen.getTranNum().toString(),style);
					POIUtils.createCell(row, (short) 3,soen.getTranAmt()==null?"0":soen.getTranAmt().toString(),style);
				}
			}
							
			/**************************** 输出流 *****************************************/
			OutputStream os = response.getOutputStream();// 取得输出流
			String filename = POIUtils.encodeFileName(request, headName);
			response.setHeader("Content-disposition","attachment;filename=" + filename + ".xls");
			work.write(os);
			if(inputStream!=null){
				inputStream.close();
			}
			os.close();
		}catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("ClearMermccBookController.exportExcel()调用出现异常。");
		}
		return null;
	}
}
