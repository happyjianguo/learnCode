package com.pay.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;

/**
 * POI������ ���ܵ㣺 1��ʵ��excel��sheet���ƣ����Ƶ����ݰ�����Ԫ�����ݡ���ʽ��ע��
 * 2��setMForeColor�޸�HSSFColor.YELLOW��ɫֵ��setMBorderColor�޸�PINK��ɫֵ
 * 3���Ե����Excel��ȡ�����кϷ���У��
 */
public class POIUtils {
	private static final Log log = LogFactory.getLog("util");
	
	/**
	 * ����У��-�����������б�
	 * @param sheet ������
	 * @param row �кţ���0��ʼ
	 * @param line �кţ���0��ʼ
	 * @param strList ������ʾ���ַ����б�
	 */
	public static void creatDropDownLists(HSSFSheet sheet, int row, int line, ArrayList<String> strList){
		//��λ����
		CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(row, row, line, line);
		//�������
		String[] strArr = strList.toArray(new String[]{});
		DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(strArr);
		//��������У��
		DataValidation dataValidation = new HSSFDataValidation(cellRangeAddressList, dvConstraint);
		//����Ϊ������
		dataValidation.setSuppressDropDownArrow(false);
		//��������У��
		sheet.addValidationData(dataValidation);
	}
	
	public static void createCell(HSSFRow row, short colNum, String value) {
		HSSFCell cell = row.createCell(colNum);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(value);
	}
	
	public static void createCellSelect(HSSFRow row, short colNum, HSSFSheet sheet ,String[] DATA_LIST,CellRangeAddressList addressList) {
		HSSFCell cell = row.createCell(colNum);
		cell.setCellValue("��ѡ��");
        DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(DATA_LIST);  
        HSSFDataValidation dataValidation = new HSSFDataValidation(addressList, dvConstraint); 
        dataValidation.setSuppressDropDownArrow(false);
        dataValidation.createPromptBox("������ʾ", "��������б���ѡ��");
        // ��sheetҳ��Ч  
        sheet.addValidationData(dataValidation);  	
	}
	public static void createTextCell(HSSFWorkbook book,HSSFRow row, short colNum, String value) {
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
		// ����
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ����
		font.setColor(HSSFColor.RED.index);
		font.setFontHeightInPoints((short) 10);
		font.setFontName("����");
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
		// font.setFontName("���Ǳ��μ�");
		font.setFontName("����");
		cellStyle.setFont(font);
		return cellStyle;
	}

	public static byte[] convertFileToByte(FormFile formFile) {
		InputStream in = null;
		byte[] arrayByte = null;
		ByteArrayOutputStream bytestream = null;
		try {
			bytestream = new ByteArrayOutputStream();
			in = new BufferedInputStream(formFile.getInputStream());
			int ch;
			while ((ch = in.read()) != -1) {
				bytestream.write(ch);
			}
			arrayByte = bytestream.toByteArray();
		} catch (Exception e) {
			log.error("�����������Excel�ļ�Action�쳣: {}", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					log.error("�����������Excel�ļ�Action�쳣: {}", e);
				}
			}
			if (bytestream != null) {
				try {
					bytestream.close();
				} catch (Exception e) {
					log.error("�����������Excel�ļ�Action�쳣: {}", e);
				}
			}
		}
		return arrayByte;
	}

	/**
	 * �Ե����Excel��ȡ�����кϷ���У��
	 * 
	 * @param Excel������
	 * @return Sheet
	 */
	public static Sheet readExcel(byte[] arrayByte) {
		Workbook workBook = convertByteToWorkbook(arrayByte);
		Sheet sheet = null;
		if (workBook == null) {
			// throw new BmoException(Result.BSS_SYS_ERROR,"ģ�岻����");
		} else {
			sheet = workBook.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() >= 3) {
				return sheet;
			} else {
				// throw new BmoException(Result.BSS_SYS_ERROR,"ģ��Ϊ��");
			}
		}
		return null;
	}

	/**
	 * ���������ֽ���ת����Excel��Workbook
	 * 
	 * @param �������ֽ���
	 * @return Excel��Workbook
	 */
	private static Workbook convertByteToWorkbook(byte[] arrayByte) {
		InputStream fi = null;
		Workbook workBook = null;
		try {
			fi = new ByteArrayInputStream(arrayByte);
			POIFSFileSystem fs = new POIFSFileSystem(fi);
			workBook = new HSSFWorkbook(fs);
		} catch (OfficeXmlFileException e) {
			// ������debug,�������office 2007���½����ļ�
			log.debug("Excel����Office 2007�汾,�ٴν����ļ� {}", e);
			try {
				fi = new ByteArrayInputStream(arrayByte);
				workBook = new XSSFWorkbook(fi);
			} catch (Exception e1) {
				log.error("�����������Excel�ļ��쳣: {}", e1);
				// throw new BmoException(Result.BSS_SYS_ERROR,e1);
			}
		} catch (Exception ex) {
			log.error("�����������Excel�ļ��쳣: {}", ex);
			// throw new BmoException(Result.BSS_SYS_ERROR,ex);
		} finally {
			try {
				if (fi != null) {
					fi.close();
				}
			} catch (Exception e) {
				log.error("�����������Excel�ļ��쳣: {}", e);
				// throw new BmoException(Result.BSS_SYS_ERROR,e);
			}
		}
		return workBook;
	}

	/**
	 * ȡֵ��Ԫ���е�ֵ
	 * 
	 * @param ��Ԫ��
	 * @return ��Ԫ���е�ֵ
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
				value = Long.valueOf((long) cell.getNumericCellValue()).toString();
			}
		} else if (cell != null) {
			value = cell.getStringCellValue().trim();
		}
		return value.trim();
	}

	/**
	 * ȡֵ��Ԫ���е�ֵ
	 * 
	 * @param ��Ԫ��
	 * @return ��Ԫ���е�ֵ
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
	 * ���ܣ�����sheet ʵ�ʵ��� copySheet(targetSheet, sourceSheet, targetWork,
	 * sourceWork, true)
	 * 
	 * @param targetSheet
	 * @param sourceSheet
	 * @param targetWork
	 * @param sourceWork
	 */
	public static void copySheet(HSSFSheet targetSheet, HSSFSheet sourceSheet, HSSFWorkbook targetWork, HSSFWorkbook sourceWork) throws Exception {
		if (targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null) {
			throw new IllegalArgumentException("����PoiUtil.copySheet()����ʱ��targetSheet��sourceSheet��targetWork��sourceWork������Ϊ�գ����׳����쳣��");
		}
		copySheet(targetSheet, sourceSheet, targetWork, sourceWork, true);
	}

	/**
	 * ���ܣ�����sheet
	 * 
	 * @param targetSheet
	 * @param sourceSheet
	 * @param targetWork
	 * @param sourceWork
	 * @param copyStyle
	 *            boolean �Ƿ񿽱���ʽ
	 */
	public static void copySheet(HSSFSheet targetSheet, HSSFSheet sourceSheet, HSSFWorkbook targetWork, HSSFWorkbook sourceWork, boolean copyStyle)
			throws Exception {

		if (targetSheet == null || sourceSheet == null || targetWork == null || sourceWork == null) {
			throw new IllegalArgumentException("����PoiUtil.copySheet()����ʱ��targetSheet��sourceSheet��targetWork��sourceWork������Ϊ�գ����׳����쳣��");
		}

		// ����Դ���е���
		int maxColumnNum = 0;

		Map styleMap = (copyStyle) ? new HashMap() : null;

		HSSFPatriarch patriarch = targetSheet.createDrawingPatriarch(); // ���ڸ���ע��
		for (int i = sourceSheet.getFirstRowNum(); i <= sourceSheet.getLastRowNum(); i++) {
			HSSFRow sourceRow = sourceSheet.getRow(i);
			HSSFRow targetRow = targetSheet.createRow(i);

			if (sourceRow != null) {
				copyRow(targetRow, sourceRow, targetWork, sourceWork, patriarch, styleMap);
				if (sourceRow.getLastCellNum() > maxColumnNum) {
					maxColumnNum = sourceRow.getLastCellNum();
				}
			}
		}

		// ����Դ���еĺϲ���Ԫ��
		mergerRegion(targetSheet, sourceSheet);

		// ����Ŀ��sheet���п�
		for (int i = 0; i <= maxColumnNum; i++) {
			targetSheet.setColumnWidth(i, sourceSheet.getColumnWidth(i));
		}
	}

	/**
	 * ���ܣ�����row
	 * 
	 * @param targetRow
	 * @param sourceRow
	 * @param styleMap
	 * @param targetWork
	 * @param sourceWork
	 * @param targetPatriarch
	 */
	public static void copyRow(HSSFRow targetRow, HSSFRow sourceRow, HSSFWorkbook targetWork, HSSFWorkbook sourceWork, HSSFPatriarch targetPatriarch,
			Map styleMap) throws Exception {
		if (targetRow == null || sourceRow == null || targetWork == null || sourceWork == null || targetPatriarch == null) {
			throw new IllegalArgumentException("����PoiUtil.copyRow()����ʱ��targetRow��sourceRow��targetWork��sourceWork��targetPatriarch������Ϊ�գ����׳����쳣��");
		}

		// �����и�
		targetRow.setHeight(sourceRow.getHeight());

		for (int i = sourceRow.getFirstCellNum(); i <= sourceRow.getLastCellNum(); i++) {
			HSSFCell sourceCell = sourceRow.getCell(i);
			HSSFCell targetCell = targetRow.getCell(i);

			if (sourceCell != null) {
				if (targetCell == null) {
					targetCell = targetRow.createCell(i);
				}

				// ������Ԫ�񣬰������ݺ���ʽ
				copyCell(targetCell, sourceCell, targetWork, sourceWork, styleMap);

				// ������Ԫ��ע��
				copyComment(targetCell, sourceCell, targetPatriarch);
			}
		}
	}

	/**
	 * ���ܣ�����cell������styleMap�Ƿ�Ϊ���ж��Ƿ񿽱���Ԫ����ʽ
	 * 
	 * @param targetCell
	 *            ����Ϊ��
	 * @param sourceCell
	 *            ����Ϊ��
	 * @param targetWork
	 *            ����Ϊ��
	 * @param sourceWork
	 *            ����Ϊ��
	 * @param styleMap
	 *            ����Ϊ��
	 */
	public static void copyCell(HSSFCell targetCell, HSSFCell sourceCell, HSSFWorkbook targetWork, HSSFWorkbook sourceWork, Map styleMap) {
		if (targetCell == null || sourceCell == null || targetWork == null || sourceWork == null) {
			throw new IllegalArgumentException("����PoiUtil.copyCell()����ʱ��targetCell��sourceCell��targetWork��sourceWork������Ϊ�գ����׳����쳣��");
		}

		// ����Ԫ����ʽ
		if (styleMap != null) {
			if (targetWork == sourceWork) {
				targetCell.setCellStyle(sourceCell.getCellStyle());
			} else {
				String stHashCode = "" + sourceCell.getCellStyle().hashCode();
				HSSFCellStyle targetCellStyle = (HSSFCellStyle) styleMap.get(stHashCode);
				if (targetCellStyle == null) {
					targetCellStyle = targetWork.createCellStyle();
					targetCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
					styleMap.put(stHashCode, targetCellStyle);
				}

				targetCell.setCellStyle(targetCellStyle);
			}
		}

		// ����Ԫ������
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
	 * ���ܣ�����comment
	 * 
	 * @param targetCell
	 * @param sourceCell
	 * @param targetPatriarch
	 */
	public static void copyComment(HSSFCell targetCell, HSSFCell sourceCell, HSSFPatriarch targetPatriarch) throws Exception {
		if (targetCell == null || sourceCell == null || targetPatriarch == null) {
			throw new IllegalArgumentException("����PoiUtil.copyCommentr()����ʱ��targetCell��sourceCell��targetPatriarch������Ϊ�գ����׳����쳣��");
		}

		// ����Ԫ��ע��
		HSSFComment comment = sourceCell.getCellComment();
		if (comment != null) {
			HSSFComment newComment = targetPatriarch.createComment(new HSSFClientAnchor());
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
	 * ���ܣ�����ԭ��sheet�ĺϲ���Ԫ���´�����sheet
	 * 
	 * @param sheetCreat
	 * @param sourceSheet
	 */
	public static void mergerRegion(HSSFSheet targetSheet, HSSFSheet sourceSheet) throws Exception {
		if (targetSheet == null || sourceSheet == null) {
			throw new IllegalArgumentException("����PoiUtil.mergerRegion()����ʱ��targetSheet����sourceSheet����Ϊ�գ����׳����쳣��");
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
	 * ���ܣ����¶���HSSFColor.YELLOW��ɫֵ
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
				palette.setColorAtIndex(HSSFColor.YELLOW.index, rgb[0], rgb[1], rgb[2]);
				hssfColor = palette.getColor(HSSFColor.YELLOW.index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hssfColor;
	}

	/**
	 * ���ܣ����¶���HSSFColor.PINK��ɫֵ
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
				palette.setColorAtIndex(HSSFColor.PINK.index, rgb[0], rgb[1], rgb[2]);
				hssfColor = palette.getColor(HSSFColor.PINK.index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hssfColor;
	}
}
