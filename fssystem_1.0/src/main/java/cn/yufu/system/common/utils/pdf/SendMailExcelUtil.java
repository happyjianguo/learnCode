package cn.yufu.system.common.utils.pdf;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.excel.ExportExcelMoreSheet;

public class SendMailExcelUtil {
	
	public static int createExcel(List<ViewStlBookDetail> list, 
			ViewStlBookDetail viewStlBookDetail, String tranAmt,
			String fee, String now) {
		try {
			String localPath = Global.getConfig("SEND_MAIL_EXCEL_LOCAL");
			localPath = localPath + "/" + now;
			//创建当日文件目录
			String fileDirName = viewStlBookDetail.getFmrchNo() + "#" + viewStlBookDetail.getMerchantEmail()
				+ "#" + viewStlBookDetail.getStartStlDate() + "-" + viewStlBookDetail.getEndStlDate();
			localPath = localPath + "/" + fileDirName;
			
			System.out.println("文件目录 ---> " + localPath);
			File localFile = new File(localPath);
			if (!localFile.exists()) {
				localFile.mkdirs();
			}
			
			String titleName = viewStlBookDetail.getMerNo() + "--" + System.currentTimeMillis();
			String[] headers = {"序号","商户编号","商户名称","终端号","卡号",
					"交易类型","交易日期","交易时间","交易金额（元）","费率(%)","手续费（元）"};
			
			ExportExcelMoreSheet exportExcel = new ExportExcelMoreSheet("结算明细单", "结算明细", headers);
			titleName = titleName + ".xls";
			if (null == list || list.isEmpty()) {
				exportExcel.writeFile(titleName).dispose();
				return -1;
			}
			
			Row row = null;
			int headrLen = 0;
			int count = 0;
			for (ViewStlBookDetail detail : list) {
				headrLen = headers.length;
				row = exportExcel.addRow();
				exportExcel.addCell(row, --headrLen, detail.getFee() == null ?"0":detail.getFee().toPlainString());
				if (StringUtils.isEmpty(detail.getTimezone())) {
					exportExcel.addCell(row, --headrLen, "0");
				} else if (detail.getTimezone().startsWith(".")) {
					exportExcel.addCell(row, --headrLen, "0" + detail.getTimezone());
				} else {
					exportExcel.addCell(row, --headrLen, detail.getTimezone());
				}
				exportExcel.addCell(row, --headrLen, detail.getTranAmt() == null ?"0.00":detail.getTranAmt().toPlainString());
				exportExcel.addCell(row, --headrLen, detail.getTranTime() == null ?"":detail.getTranTime().trim());
				exportExcel.addCell(row, --headrLen, detail.getTranDate() == null ?"":detail.getTranDate().trim());
				exportExcel.addCell(row, --headrLen, detail.getTranTypeDesc() == null ?"":detail.getTranTypeDesc().trim());
				exportExcel.addCell(row, --headrLen, detail.getCardNo() == null ?"":detail.getCardNo().trim());
				exportExcel.addCell(row, --headrLen, detail.getTermNo() == null ?"":detail.getTermNo().trim());
				exportExcel.addCell(row, --headrLen, detail.getMerName() == null ?"":detail.getMerName().trim());
				exportExcel.addCell(row, --headrLen, detail.getMerNo() == null ?"":detail.getMerNo().trim());
				exportExcel.addCell(row, --headrLen, ++count + "");
			}
			
			Sheet sheet = exportExcel.getSheet();
			row = exportExcel.addRow();
			int rownum = exportExcel.getRownum();
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, headers.length - 1));
			exportExcel.addCell(row, (int)(headers.length/2), "交易金额合计：" + tranAmt + "    手续费合计：" + fee);
			
			exportExcel.writeFile(localPath + "/" + titleName).dispose();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}
