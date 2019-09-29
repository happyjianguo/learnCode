package cn.com.jansh.core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelWrite {

	private static final Logger logger = LogManager.getLogger(ExcelWrite.class);

	private SXSSFWorkbook wb;

	// private int lastRowNum = 0;

	/**
	 * 
	 * default. keep 100 rows in memory,exceeding rows will be flushed to disk
	 */
	public ExcelWrite() {
		// keep 100 rows in memory,
		// exceeding rows will be
		// flushed to disk
		this(100);
	}

	/**
	 * keep rowAccessWindowSize rows in memory,exceeding rows will be flushed to
	 * disk
	 * 
	 * @param rowAccessWindowSize
	 */
	public ExcelWrite(int rowAccessWindowSize) {
		logger.info("new SXSSFWorkbook rowAccessWindowSize {}", rowAccessWindowSize);
		this.wb = new SXSSFWorkbook(rowAccessWindowSize);
	}

	/**
	 * 新建sheet并写入数据
	 * 
	 * @param list
	 * @return
	 */
	public String append(List<List<String>> list) {
		logger.info("新建sheet并写入数据,共{}条数据", list.size());
		return append(null, list);
	}

	/**
	 * 将数据追加至指定sheet，如果sheet不存在，则新建sheet
	 * 
	 * @param sheetName
	 * @param list
	 * @return
	 */
	public String append(String sheetName, List<List<String>> list) {
		SXSSFSheet sh = null;
		if (StringUtils.isNotBlank(sheetName)) {
			sh = wb.getSheet(sheetName);
		}
		if (sh == null) {
			if (StringUtils.isNotBlank(sheetName)) {
				sh = wb.createSheet(sheetName);
			} else {
				sh = wb.createSheet();
			}
		}
		logger.info("sheet:{},追加{}条数据", sh.getSheetName(), list.size());
		int lastRowNum = sh.getLastRowNum();
		lastRowNum = lastRowNum == 0 ? 0 : lastRowNum + 1;
		int listSize = list.size();
		for (int rownum = 0; rownum < listSize; rownum++) {
			Row row = sh.createRow(rownum + lastRowNum);
			List<String> cellList = list.get(rownum);
			int cellListSize = cellList.size();
			for (int cellnum = 0; cellnum < cellListSize; cellnum++) {
				Cell cell = row.createCell(cellnum);
				// String address = new CellReference(cell).formatAsString();
				cell.setCellValue(cellList.get(cellnum));
			}
		}
		logger.info("sheet:{}，写入数据完成", sh.getSheetName());
		return sh.getSheetName();
	}

	/**
	 * 将创建的excel写入指定文件
	 * 
	 * @param name
	 * @throws IOException
	 */
	public void write(String name) throws IOException {
		logger.info("写入文件{}", name);
		FileOutputStream out = new FileOutputStream(name);
		wb.write(out);
		out.close();
		// dispose of temporary files backing this workbook on disk
		wb.dispose();
		wb.close();
	}

}
