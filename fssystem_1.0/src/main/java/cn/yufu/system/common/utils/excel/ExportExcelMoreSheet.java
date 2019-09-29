package cn.yufu.system.common.utils.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import cn.yufu.system.common.utils.DateUtils;
import cn.yufu.system.common.utils.POIUtils;

/**
 * 导出Excel文件（导出“XLSX”格式，支持大数据量导出   @see org.apache.poi.ss.SpreadsheetVersion）
 * 
 * @version ZQK 2018-08-03
 */
public class ExportExcelMoreSheet {
	
	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook sxssfWorkBook;
	
	/**
	 * 工作表对象 
	 */
	private Sheet sheet;
	
	/**
	 * 样式列表
	 */
	private Map<String, CellStyle> styles;
	
	/**
	 * 当前行号
	 */
	private int rownum;
	
	public SXSSFWorkbook getSxssfWorkBook() {
		return sxssfWorkBook;
	}
	
	public Sheet getSheet() {
		return sheet;
	}

	public int getRownum() {
		return rownum;
	}

	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headers 表头数组
	 */
	public ExportExcelMoreSheet(String title, String[] headers) {
		initialize(title, "sheet", headers);
	}
	
	/**
	 * 构造函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param sheetName sheet 名称
	 * @param headers 表头数组
	 */
	public ExportExcelMoreSheet(String title, String sheetName, String[] headers) {
		initialize(title, sheetName, headers);
	}
	
	/**
	 * 初始化同一sxssfWorkBook 下的第二个sheet，乃至多个
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headers 表头列表
	 */
	public void initializeMoreSheet(SXSSFWorkbook sxssfWorkBook, 
			String title, String sheetName, String[] headers) {
		// Create header
		if (null == headers){
			throw new RuntimeException("headerList not null!");
		}
		rownum = 0;
		this.sheet = sxssfWorkBook.createSheet(sheetName);
		this.styles = createStyles(sxssfWorkBook);
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headers.length - 1));
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styles.get("header"));
			cell.setCellValue(headers[i]);
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
	}
	
	/**
	 * 初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 * @param headers 表头列表
	 */
	private void initialize(String title, String sheetName, String[] headers) {
		// Create header
		if (null == headers){
			throw new RuntimeException("headerList not null!");
		}
		
		this.sxssfWorkBook = new SXSSFWorkbook(500);
		this.sheet = sxssfWorkBook.createSheet(sheetName);
		this.styles = createStyles(sxssfWorkBook);
				
		// Create title
		if (StringUtils.isNotBlank(title)){
			Row titleRow = sheet.createRow(rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(title);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headers.length - 1));
		}
		Row headerRow = sheet.createRow(rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styles.get("header"));
			cell.setCellValue(headers[i]);
			int colWidth = sheet.getColumnWidth(i)*2;
	        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
		}
	}
	
	/**
	 * 创建表格样式
	 * @param sxssfWorkBook 工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(Workbook sxssfWorkBook) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		DataFormat format = sxssfWorkBook.createDataFormat();
		
		CellStyle style = sxssfWorkBook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = sxssfWorkBook.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		style = sxssfWorkBook.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setDataFormat(format.getFormat("@")); //文本格式
		
		Font dataFont = sxssfWorkBook.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("data", style);
		
		style = sxssfWorkBook.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = sxssfWorkBook.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);
		
		return styles;
	}

	/**
	 * 添加一行
	 * @return 行对象
	 */
	public Row addRow(){
		return sheet.createRow(rownum++);
	}
	
	/**
	 * 获取一行
	 * @return 行对象
	 */
	public Row getRow(int rownum){
		return sheet.getRow(rownum);
	}
	
	/**
	 * 添加一个单元格, 不能合并单元格
	 * @param row 添加的行
	 * @param column 添加列号
	 * @param val 添加值
	 * @return 单元格对象
	 */
	public Cell addCell(Row row, int column, Object val){
		Cell cell = row.createCell(column);
		CellStyle style = styles.get("data");
		try {
			if (null == val || val == ""){
				cell.setCellValue("");
			} else if (val instanceof String) {
				cell.setCellValue((String) val);
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Long) {
				cell.setCellValue((Long) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			} else if (val instanceof Float) {
				cell.setCellValue((Float) val);
			} else if (val instanceof BigDecimal) {
				val = ((BigDecimal) val).toPlainString();
				cell.setCellValue((String)val);
			} else if (val instanceof Date) {
				DateUtils.formatDate((Date)val, "yyyyMMdd");
				cell.setCellValue((Date) val);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			cell.setCellValue(val.toString());
		}
		cell.setCellStyle(style);
		return cell;
	}
	
	/**
	 * 输出数据流
	 * @param os 输出数据流
	 */
	public ExportExcelMoreSheet write(OutputStream os) throws IOException{
		sxssfWorkBook.write(os);
		return this;
	}
	
	/**
	 * 输出到文件
	 * @param fileName 输出文件名
	 */
	public ExportExcelMoreSheet writeFile(String fileName) throws FileNotFoundException, IOException{
		FileOutputStream os = new FileOutputStream(fileName);
		this.write(os);
		return this;
	}
	
	/**
	 * 清理临时文件
	 */
	public ExportExcelMoreSheet dispose(){
		sxssfWorkBook.dispose();
		return this;
	}
	
	/**
	 * 输出到客户端
	 * @param fileName 输出文件名
	 */
	public ExportExcelMoreSheet write(HttpServletRequest requese, HttpServletResponse response, String fileName) throws IOException{
		response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+POIUtils.encodeFileName(requese, fileName));
		write(response.getOutputStream());
		return this;
	}
	
	/**
	 * 导出测试
	 * 单  sheet 页
	 */
	public static void main(String[] args) throws Throwable {
		String[] title = {"万物订单号","福讯商户号","福讯商户名称","渠道商户号","渠道终端号",
         		"交易金额","交易手续费","交易类型","交易状态","支付方式","卡类型","支付账户",
         		"流水号","批次号", "参考号","交易日期","交易时间"};
		
		List<String> dataRowList = new ArrayList<String>();
		for (int i = 1; i <= title.length; i++) {
			dataRowList.add("数据"+i);
		}
		List<List<String>> dataList = new ArrayList<List<String>>();
		for (int i = 1; i <= 100; i++) {
			dataList.add(dataRowList);
		}
		
		ExportExcelMoreSheet ee = new ExportExcelMoreSheet("标题11111", "交易明细", title);
		for (int i = 0; i < dataList.size(); i++) {
			Row row = ee.addRow();
			for (int j = 0; j < title.length; j++) {
				ee.addCell(row, j, dataList.get(i).get(j));
			}
		}
		
		
		//多个sheet时
		
		String[] title2 = {"流水号","批次号", "参考号","交易日期","交易时间"};
		ee.initializeMoreSheet(ee.sxssfWorkBook, "标题22222", "交易汇总", title2);
		
		List<String> dataRowList2 = new ArrayList<String>();
		for (int i = 1; i <= title2.length; i++) {
			dataRowList2.add("数据"+i);
		}
		List<List<String>> dataList2 = new ArrayList<List<String>>();
		for (int i = 1; i <= 10; i++) {
			dataList2.add(dataRowList2);
		}
		
		for (int i = 0; i < dataList2.size(); i++) {
			Row row = ee.addRow();
			for (int j = 0; j < title2.length; j++) {
				ee.addCell(row, j, dataList2.get(i).get(j));
			}
		}
		String localPath = "D:/cer/20180813";
		
		//创建当日对账文件
		File localFile = new File(localPath);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		//若路径不存在 需 mkdir
		ee.writeFile(localPath + "/wwt_20180813.xlsx");
		//localFile = new File(localPath + "sadass.xlsx");
		//ee.write(new FileOutputStream(localFile));
		ee.dispose();
	}

}
