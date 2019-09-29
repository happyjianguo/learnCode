package cn.com.jansh.core.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel处理
 * @author duanmuyn
 *
 */
public class ExcelUtil {
	// 对外提供读取excel文件的接口
    public static List<List<String>> readExcel(MultipartFile myfile)throws IOException {
        String extension = (myfile.getOriginalFilename().lastIndexOf(".") == -1) ? "": myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf(".") + 1);
        if ("xlsx".equals(extension)) { // 2007
            return read2007Excel(myfile.getInputStream());
        } else {
        	throw new IOException("不支持的文件类型:" + extension);
        }
    }

//    读取2003excel
//    private static List<List<Object>> read2003Excel(InputStream inputStream)throws IOException {
//        List<List<Object>> dataList = new ArrayList<List<Object>>();
//        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
//        HSSFSheet sheet = wb.getSheetAt(0);
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        Object val = null;
//        DecimalFormat df = new DecimalFormat("##############0.00");// 格式化数字
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
//
//        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
//            row = sheet.getRow(i);
//            if (row == null) {
//                continue;
//            }
//            List<Object> objList = new ArrayList<Object>();
//            for (int j = row.getFirstCellNum(); j < row.getLastCellNum();j++) {
//                cell = row.getCell(j);
//                if (cell == null) {
//                    val = null;
//                    objList.add(val);
//                    continue;
//                }
//                switch (cell.getCellType()) {
//                case HSSFCell.CELL_TYPE_STRING:
//                    val = cell.getStringCellValue();
//                    break;
//                case HSSFCell.CELL_TYPE_NUMERIC:
//                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {
//                        val = df.format(cell.getNumericCellValue());
//                    } else if ("General".equals(cell.getCellStyle() .getDataFormatString())) {
//                        val = df.format(cell.getNumericCellValue());
//                    } else {
//                        val = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
//                    }
//                    break;
//                case HSSFCell.CELL_TYPE_BOOLEAN:
//                    val = cell.getBooleanCellValue();
//                    break;
//                case HSSFCell.CELL_TYPE_BLANK:
//                    val = "";
//                    break;
//                default:
//                    val = cell.toString();
//                    break;
//                }
//                objList.add(val);
//            }
//            dataList.add(objList);
//        }
//        return dataList;
//    }

   /**
    * 读取excel表头
    * @param file
    * @return
    * @throws IOException
    */
    public static String[] readExcelHead(File file) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        row = sheet.getRow(0);
        String[] buff = new String[row.getLastCellNum()];
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            cell = row.getCell(i);
            buff[i] = cell.getStringCellValue();
        }
        return buff;
    }

    /**
    * 读取2007excel
    * 
    * @param file
    * @return
    */
    private static List<List<String>> read2007Excel(InputStream inputStream)
        throws IOException {
        List<List<String>> dataList = new ArrayList<List<String>>();
        XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xwb.getSheetAt(0);
        XSSFRow row = null;
        XSSFCell cell = null;
        String val = null;
        DecimalFormat df = new DecimalFormat("0");// 格式化数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
        int lastRowNum = sheet.getLastRowNum()+1;
        for (int i = 0;i <= lastRowNum; i++) {
            row = sheet.getRow(i);
            if (row == null) {
            	dataList.add(null);
                continue;
            }
            List<String> objList = new ArrayList<String>();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum();j++) {
            	
            	cell = row.getCell(j);
                if (cell == null) {
                    val = null;
                    objList.add(val);
                    continue;
                }
                row.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING:
                    val = cell.getRichStringCellValue().getString();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                        val = df.format(cell.getNumericCellValue());
                    } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                        val = df.format(cell.getNumericCellValue());
                    } else {
                        val = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                    }
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    val = String.valueOf(cell.getBooleanCellValue());
                    break;
                case XSSFCell.CELL_TYPE_BLANK:
                    val = "";
                    break;
                default:
                    val = cell.toString();
                    break;
                }
                objList.add(val);
            }
            dataList.add(objList);
        }
        return dataList;
    }
}
