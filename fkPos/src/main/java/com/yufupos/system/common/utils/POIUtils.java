package com.yufupos.system.common.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * POI工具类 功能点： 1、实现excel的sheet复制，复制的内容包括单元的内容、样式、注释
 * 2、setMForeColor修改HSSFColor.YELLOW的色值，setMBorderColor修改PINK的色值
 * 3、对导入的Excel读取并进行合法性校验
 */
public class POIUtils {
	private static final Log log = LogFactory.getLog("util");

	public static void createCell(HSSFRow row, short colNum, String value) {
		HSSFCell cell = row.createCell(colNum);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(value);
	}

	public static void createCell(HSSFRow row, short colNum, String value,HSSFCellStyle cellStyle) {
		if((cellStyle==null||"".equals(cellStyle))&&row!=null){
			 cellStyle = row.getCell(colNum)==null?null:row.getCell(colNum).getCellStyle(); 
		}
		HSSFCell cell = row.createCell(colNum);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);	
		if(cellStyle!=null&&!"".equals(cellStyle)){
			cell.setCellStyle(cellStyle);
		}		
		cell.setCellValue(value);
	}

	public static void createTextCell(HSSFWorkbook book, HSSFRow row,
			short colNum, String value) {
		HSSFCellStyle cellStyle = book.createCellStyle();
		HSSFDataFormat format = book.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));
		HSSFCell cell = row.createCell(colNum);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}

	public static HSSFCellStyle getRedFontStyle(HSSFWorkbook book) {
		HSSFCellStyle cellStyle = book.createCellStyle();
		HSSFFont font = book.createFont();
		// 粗体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 红字
		font.setColor(HSSFColor.RED.index);
		font.setFontHeightInPoints((short) 10);
		font.setFontName("黑体");
		cellStyle.setFont(font);
		return cellStyle;
	}

	public static HSSFCellStyle getTextStyle(HSSFWorkbook book) {
		HSSFCellStyle cellStyle = book.createCellStyle();
		HSSFDataFormat format = book.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));
		return cellStyle;
	}

	public static HSSFCellStyle getBlackFontStyle(HSSFWorkbook book) {
		HSSFCellStyle cellStyle = book.createCellStyle();
		HSSFFont font = book.createFont();
		font.setFontHeightInPoints((short) 10);
		// font.setFontName("汉仪报宋简");
		font.setFontName("黑体");
		cellStyle.setFont(font);
		return cellStyle;
	}

	/**
	 * 对导入的Excel读取并进行合法性校验
	 * 
	 * @param Excel表单数据
	 * @return Sheet
	 */
	public static Sheet readExcel(byte[] arrayByte) {
		Workbook workBook = convertByteToWorkbook(arrayByte);
		Sheet sheet = null;
		if (workBook == null) {
			// throw new BmoException(Result.BSS_SYS_ERROR,"模板不存在");
		} else {
			sheet = workBook.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() >= 3) {
				return sheet;
			} else {
				// throw new BmoException(Result.BSS_SYS_ERROR,"模板为空");
			}
		}
		return null;
	}

	/**
	 * 将二进制字节流转换成Excel的Workbook
	 * 
	 * @param 二进制字节流
	 * @return Excel的Workbook
	 */
	private static Workbook convertByteToWorkbook(byte[] arrayByte) {
		InputStream fi = null;
		Workbook workBook = null;
		try {
			fi = new ByteArrayInputStream(arrayByte);
			POIFSFileSystem fs = new POIFSFileSystem(fi);
			workBook = new HSSFWorkbook(fs);
		} catch (OfficeXmlFileException e) {
			// 这里用debug,如果不是office 2007重新解析文件
			log.debug("Excel不是Office 2007版本,再次解析文件 {}", e);
			try {
				fi = new ByteArrayInputStream(arrayByte);
				workBook = new XSSFWorkbook(fi);
			} catch (Exception e1) {
				log.error("批量受理解析Excel文件异常: {}", e1);
				// throw new BmoException(Result.BSS_SYS_ERROR,e1);
			}
		} catch (Exception ex) {
			log.error("批量受理解析Excel文件异常: {}", ex);
			// throw new BmoException(Result.BSS_SYS_ERROR,ex);
		} finally {
			try {
				if (fi != null) {
					fi.close();
				}
			} catch (Exception e) {
				log.error("批量受理解析Excel文件异常: {}", e);
				// throw new BmoException(Result.BSS_SYS_ERROR,e);
			}
		}
		return workBook;
	}

	/**
	 * 取值单元格中的值
	 * 
	 * @param 单元格
	 * @return 单元格中的值
	 */
	public static String getStringFromExcelCell(Cell cell) {
		String value = "";
		if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
				// date logic
				Date date = cell.getDateCellValue();
				String partten = "yyyy-MM-dd";
				SimpleDateFormat sdf = new SimpleDateFormat(partten);
				value = sdf.format(date);
			} else {
				// non date logic
				value = Long.valueOf((long) cell.getNumericCellValue())
						.toString();
			}
		} else if (cell != null) {
			value = cell.getStringCellValue().trim();
		}
		return value.trim();
	}

	/**
	 * 取值单元格中的值
	 * 
	 * @param 单元格
	 * @return 单元格中的值
	 */
	public static Integer getIntegerFromExcelCell(Cell cell) {
		Integer value = null;
		String sValue = getStringFromExcelCell(cell);
		try {
			value = Integer.parseInt(sValue);
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	/**
	 * 功能：拷贝sheet 实际调用 copySheet(targetSheet, sourceSheet, targetWork,
	 * sourceWork, true)
	 * 
	 * @param targetSheet
	 * @param sourceSheet
	 * @param targetWork
	 * @param sourceWork
	 */
	public static void copySheet(HSSFSheet targetSheet, HSSFSheet sourceSheet,
			HSSFWorkbook targetWork, HSSFWorkbook sourceWork) throws Exception {
		if (targetSheet == null || sourceSheet == null || targetWork == null
				|| sourceWork == null) {
			throw new IllegalArgumentException(
					"调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
		}
		copySheet(targetSheet, sourceSheet, targetWork, sourceWork, true);
	}

	/**
	 * 功能：拷贝sheet
	 * 
	 * @param targetSheet
	 * @param sourceSheet
	 * @param targetWork
	 * @param sourceWork
	 * @param copyStyle
	 *            boolean 是否拷贝样式
	 */
	public static void copySheet(HSSFSheet targetSheet, HSSFSheet sourceSheet,
			HSSFWorkbook targetWork, HSSFWorkbook sourceWork, boolean copyStyle)
			throws Exception {

		if (targetSheet == null || sourceSheet == null || targetWork == null
				|| sourceWork == null) {
			throw new IllegalArgumentException(
					"调用PoiUtil.copySheet()方法时，targetSheet、sourceSheet、targetWork、sourceWork都不能为空，故抛出该异常！");
		}

		// 复制源表中的行
		int maxColumnNum = 0;

		Map styleMap = (copyStyle) ? new HashMap() : null;

		HSSFPatriarch patriarch = targetSheet.createDrawingPatriarch(); // 用于复制注释
		for (int i = sourceSheet.getFirstRowNum(); i <= sourceSheet
				.getLastRowNum(); i++) {
			HSSFRow sourceRow = sourceSheet.getRow(i);
			HSSFRow targetRow = targetSheet.createRow(i);

			if (sourceRow != null) {
				copyRow(targetRow, sourceRow, targetWork, sourceWork,
						patriarch, styleMap);
				if (sourceRow.getLastCellNum() > maxColumnNum) {
					maxColumnNum = sourceRow.getLastCellNum();
				}
			}
		}

		// 复制源表中的合并单元格
		mergerRegion(targetSheet, sourceSheet);

		// 设置目标sheet的列宽
		for (int i = 0; i <= maxColumnNum; i++) {
			targetSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
		}
	}

	/**
	 * 功能：拷贝row
	 * 
	 * @param targetRow
	 * @param sourceRow
	 * @param styleMap
	 * @param targetWork
	 * @param sourceWork
	 * @param targetPatriarch
	 */
	public static void copyRow(HSSFRow targetRow, HSSFRow sourceRow,
			HSSFWorkbook targetWork, HSSFWorkbook sourceWork,
			HSSFPatriarch targetPatriarch, Map styleMap) throws Exception {
		if (targetRow == null || sourceRow == null || targetWork == null
				|| sourceWork == null || targetPatriarch == null) {
			throw new IllegalArgumentException(
					"调用PoiUtil.copyRow()方法时，targetRow、sourceRow、targetWork、sourceWork、targetPatriarch都不能为空，故抛出该异常！");
		}

		// 设置行高
		targetRow.setHeight(sourceRow.getHeight());

		for (int i = sourceRow.getFirstCellNum(); i <= sourceRow
				.getLastCellNum(); i++) {
			HSSFCell sourceCell = sourceRow.getCell(i);
			HSSFCell targetCell = targetRow.getCell(i);

			if (sourceCell != null) {
				if (targetCell == null) {
					targetCell = targetRow.createCell(i);
				}

				// 拷贝单元格，包括内容和样式
				copyCell(targetCell, sourceCell, targetWork, sourceWork,
						styleMap);

				// 拷贝单元格注释
				copyComment(targetCell, sourceCell, targetPatriarch);
			}
		}
	}

	/**
	 * 功能：拷贝cell，依据styleMap是否为空判断是否拷贝单元格样式
	 * 
	 * @param targetCell
	 *            不能为空
	 * @param sourceCell
	 *            不能为空
	 * @param targetWork
	 *            不能为空
	 * @param sourceWork
	 *            不能为空
	 * @param styleMap
	 *            可以为空
	 */
	public static void copyCell(HSSFCell targetCell, HSSFCell sourceCell,
			HSSFWorkbook targetWork, HSSFWorkbook sourceWork, Map styleMap) {
		if (targetCell == null || sourceCell == null || targetWork == null
				|| sourceWork == null) {
			throw new IllegalArgumentException(
					"调用PoiUtil.copyCell()方法时，targetCell、sourceCell、targetWork、sourceWork都不能为空，故抛出该异常！");
		}

		// 处理单元格样式
		if (styleMap != null) {
			if (targetWork == sourceWork) {
				targetCell.setCellStyle(sourceCell.getCellStyle());
			} else {
				String stHashCode = "" + sourceCell.getCellStyle().hashCode();
				HSSFCellStyle targetCellStyle = (HSSFCellStyle) styleMap
						.get(stHashCode);
				if (targetCellStyle == null) {
					targetCellStyle = targetWork.createCellStyle();
					targetCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
					styleMap.put(stHashCode, targetCellStyle);
				}

				targetCell.setCellStyle(targetCellStyle);
			}
		}

		// 处理单元格内容
		switch (sourceCell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			targetCell.setCellValue(sourceCell.getRichStringCellValue());
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			targetCell.setCellValue(sourceCell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			targetCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			targetCell.setCellValue(sourceCell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			targetCell.setCellFormula(sourceCell.getCellFormula());
			break;
		default:
			break;
		}
	}

	/**
	 * 功能：拷贝comment
	 * 
	 * @param targetCell
	 * @param sourceCell
	 * @param targetPatriarch
	 */
	public static void copyComment(HSSFCell targetCell, HSSFCell sourceCell,
			HSSFPatriarch targetPatriarch) throws Exception {
		if (targetCell == null || sourceCell == null || targetPatriarch == null) {
			throw new IllegalArgumentException(
					"调用PoiUtil.copyCommentr()方法时，targetCell、sourceCell、targetPatriarch都不能为空，故抛出该异常！");
		}

		// 处理单元格注释
		HSSFComment comment = sourceCell.getCellComment();
		if (comment != null) {
			HSSFComment newComment = targetPatriarch
					.createComment(new HSSFClientAnchor());
			newComment.setAuthor(comment.getAuthor());
			newComment.setColumn((short) comment.getColumn());
			newComment.setFillColor(comment.getFillColor());
			newComment.setHorizontalAlignment(comment.getHorizontalAlignment());
			newComment.setLineStyle(comment.getLineStyle());
			newComment.setLineStyleColor(comment.getLineStyleColor());
			newComment.setLineWidth(comment.getLineWidth());
			newComment.setMarginBottom(comment.getMarginBottom());
			newComment.setMarginLeft(comment.getMarginLeft());
			newComment.setMarginTop(comment.getMarginTop());
			newComment.setMarginRight(comment.getMarginRight());
			newComment.setNoFill(comment.isNoFill());
			newComment.setRow(comment.getRow());
			newComment.setShapeType(comment.getShapeType());
			newComment.setString(comment.getString());
			newComment.setVerticalAlignment(comment.getVerticalAlignment());
			newComment.setVisible(comment.isVisible());
			targetCell.setCellComment(newComment);
		}
	}

	/**
	 * 功能：复制原有sheet的合并单元格到新创建的sheet
	 * 
	 * @param sheetCreat
	 * @param sourceSheet
	 */
	public static void mergerRegion(HSSFSheet targetSheet, HSSFSheet sourceSheet)
			throws Exception {
		if (targetSheet == null || sourceSheet == null) {
			throw new IllegalArgumentException(
					"调用PoiUtil.mergerRegion()方法时，targetSheet或者sourceSheet不能为空，故抛出该异常！");
		}

		// for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
		// CellRangeAddress oldRange =
		// (CellRangeAddress)sourceSheet.getMergedRegion(i);
		// CellRangeAddress newRange = new CellRangeAddress(
		// oldRange.getFirstRow(), oldRange.getLastRow(),
		// oldRange.getFirstColumn(), oldRange.getLastColumn());
		// targetSheet.addMergedRegion(newRange);
		// }
	}

	/**
	 * 功能：重新定义HSSFColor.YELLOW的色值
	 * 
	 * @param workbook
	 * @return
	 */
	public static HSSFColor setMForeColor(HSSFWorkbook workbook) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		byte[] rgb = { (byte) 221, (byte) 241, (byte) 255 };
		try {
			hssfColor = palette.findColor(rgb[0], rgb[1], rgb[2]);
			if (hssfColor == null) {
				palette.setColorAtIndex(HSSFColor.YELLOW.index, rgb[0], rgb[1],
						rgb[2]);
				hssfColor = palette.getColor(HSSFColor.YELLOW.index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hssfColor;
	}

	/**
	 * 功能：重新定义HSSFColor.PINK的色值
	 * 
	 * @param workbook
	 * @return
	 */
	public static HSSFColor setMBorderColor(HSSFWorkbook workbook) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		byte[] rgb = { (byte) 0, (byte) 128, (byte) 192 };
		try {
			hssfColor = palette.findColor(rgb[0], rgb[1], rgb[2]);
			if (hssfColor == null) {
				palette.setColorAtIndex(HSSFColor.PINK.index, rgb[0], rgb[1],
						rgb[2]);
				hssfColor = palette.getColor(HSSFColor.PINK.index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hssfColor;
	}
	
	/**
	 * 下载文件名中含有中文的处理
	 * @param req
	 * @param headName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeFileName(HttpServletRequest req, String headName)
			throws UnsupportedEncodingException {
		String filename="";
		String header = req.getHeader("User-Agent");
		if (header.toUpperCase().indexOf("MSIE") >= 0 || (header.toUpperCase().indexOf("TRIDENT") >= 0 
				&& header.toUpperCase().indexOf("RV") >= 0)) {  
		    filename = URLEncoder.encode(headName, "UTF-8");  
		}else if (header.indexOf("Mozilla") >= 0) {
			filename = new String(headName.getBytes("UTF-8"), "ISO8859-1");
		}else {  
			filename = URLEncoder.encode(headName, "UTF-8");  
		}
		return filename;
	}
}
