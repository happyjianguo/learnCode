package cn.yufu.system.common.utils.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.mail.Message.RecipientType;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.Mail;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.entity.FTPAndSFTP;
import cn.yufu.system.common.utils.ftp.FTPDirUtil;
import cn.yufu.system.common.utils.ftp.FTPUtils;
import cn.yufu.system.common.utils.sftp.SFTPLogin;
import cn.yufu.system.modules.sys.utils.DictUtils;
import sun.net.ftp.FtpClient;

/**
 * 财务结算初表对账明细PDF模板
 * 
 * @author ZQK 2018-02-22
 */
@SuppressWarnings({"restriction", "unused"})
public class SendMailPDFUtil {
	
	private Document document;		//文档对象
	private File file;				//文件
	private PdfPTable table;		//文档数据表格对象
	private Font songtiSFivefont;	// 数据字体样式
	private String rootPath;		//根路径
	private String fileDirectory; 	//商户对账明细文件所在目录
	private String fileName;		//生成的文件名
	
	/**
	 * 外部获取此类实例对象
	 * @throws Exception 
	 * @throws IOException 
	 * 
	 */
	public static SendMailPDFUtil getInstance(ViewStlBookDetail viewStlBookDetail, String now) throws IOException, Exception{
		return new SendMailPDFUtil(viewStlBookDetail, now);
	}
	
	public static SendMailPDFUtil getInstance(String now) {
		return new SendMailPDFUtil(now);
	}
	
	private SendMailPDFUtil(String now) {
		this.rootPath = getRootPath();
		this.fileDirectory = rootPath + "/PDFFile/" + now;
	}
	
	private SendMailPDFUtil(ViewStlBookDetail viewStlBookDetail, String now) throws IOException, Exception{
		this.rootPath = getRootPath();
		this.fileDirectory = rootPath + "/PDFFile/" + now;
		//确保文件名唯一
		this.fileName = viewStlBookDetail.getMerNo() + "--" + System.currentTimeMillis();
		createFile(viewStlBookDetail);
		createDocument();
	}
	
	/**
	 * @return 项目根路径--> webapp
	 */
	private String getRootPath() {
		String rootFile = SendMailPDFUtil.class.getClassLoader().getResource("").getPath();
		rootFile = rootFile.replace("file:", ""); 	//去掉file:  
		rootFile = rootFile.replace("WEB-INF/classes/", "");
		//rootFile = rootFile.substring(1); 	//去掉第一个/
		rootFile += "pdfTemp"; 				//目录下文件夹名
		return rootFile;
	}
	
	/**
	 * 仅当不存在时创建文件
	 * @param rootPath 项目根路径
	 * @param fileName 文件名
	 * 
	 */
	private void createFile(ViewStlBookDetail viewStlBookDetail) throws IOException, Exception {
		if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(rootPath)) {
			throw new Exception("文件路径或文件名为空");
		}
		//此路径建立单个商户
		//String fileDirName = viewStlBookDetail.getMerNo() + "#" + viewStlBookDetail.getMerchantEmail()
		//				+ "#" + viewStlBookDetail.getStartStlDate() + "-" + viewStlBookDetail.getEndStlDate();
		//此路径  同一父商户下的商户PDF文件会放在一个文件夹下
		String fileDirName = viewStlBookDetail.getFmrchNo() + "#" + viewStlBookDetail.getMerchantEmail()
						+ "#" + viewStlBookDetail.getStartStlDate() + "-" + viewStlBookDetail.getEndStlDate();
		System.out.println("文件目录 ---> " + fileDirectory + "/" + fileDirName);
		file = new File(fileDirectory + "/" + fileDirName);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(fileDirectory + "/" + fileDirName + "/" + fileName + ".PDF");
		file.createNewFile();
	}
	
	/**
	 * 创建PDF Document
	 */
	private void createDocument() throws DocumentException, FileNotFoundException {
		// 创建一个Document 左右上下
		document = new Document(PageSize.A4, 10, 10, 20, 10);
		// 创建书写器(Writer) 与document对象关联，通过书写器可以将文档写入磁盘中
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		// 设置PDF版本（默认1.4）
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
		// 文档属性
		document.addTitle("Title@财务结算系统-结算初表明细单"); 	// 标题
		document.addAuthor("Author@llg");					// 作者
		document.addSubject("Subject@财务结算系统-结算初表明细单");// 主题
		document.addKeywords("Keywords@结算初表");			// 关键字
		document.addCreator("Creator@llg");					// 创建者
	}
	
	/**
	 * 创建PDF Document 内容数据
	 * @param tranAmt 交易金额合计
	 * @param fee 手续费合计
	 */
	private void createPDF(String tranAmt, String fee) throws DocumentException, IOException{
		document.open(); // 打开文档
		// 设置字体
		//String songtizi = req.getSession().getServletContext().getRealPath("/")+ "pdfTemp/simsun.ttc,1"; // 宋体字 模板
		String songtizi = rootPath + "/simsun.ttc,1"; 	// 宋体字 模板
		BaseFont chinese = BaseFont.createFont(songtizi,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);		
		songtiSFivefont = new Font(chinese, 9f);		//宋体小五号字
		Font songtiSTitlefont = new Font(chinese, 13f); //宋体小五号字
		Font songtiSToufont = new Font(chinese, 10f);		//宋体小五号字
		
		Paragraph title = new Paragraph("商户交易明细单", songtiSTitlefont);// 设置标题
		title.setAlignment(Element.ALIGN_CENTER);	// 设置对齐方式
		title.setLeading(1f);	// 设置行间距
		document.add(title);
		
		//创建段落对象
		Anchor anchorTarget = new Anchor("交易金额合计:" + tranAmt + " 		手续费合计：" + fee, songtiSToufont);
		anchorTarget.setName("BackToTop");
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setSpacingBefore(5f);
		paragraph1.add(anchorTarget);
		document.add(paragraph1);
		
		createTable(songtiSToufont);
	}
	
	/**
	 * 创建文档数据表格,必须在document文档使用open()方法之后进行
	 */
	private void createTable(Font songtiSToufont) {
		float[] widths = {5f,20f,15f,15f,18f,10f,10f,10f,10f,7f,10f};// 设置表格的列宽和列数 默认是4列  
        table = new PdfPTable(widths);// 建立一个pdf表格  
        table.setSpacingBefore(10f);
		table.setWidthPercentage(100);// 设置表格宽度为100%
		table.setSpacingAfter(30);
		// 字段名
		PdfPCell c1 = new PdfPCell(new Phrase("NO",songtiSToufont));
		table.addCell(c1);			
		PdfPCell c2 = new PdfPCell(new Phrase("商户编号",songtiSToufont));
		table.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("商户名称",songtiSToufont));
		table.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("终端号",songtiSToufont));
		table.addCell(c4);		
		PdfPCell c5 = new PdfPCell(new Phrase("卡号",songtiSToufont));
		table.addCell(c5);
		PdfPCell c6 = new PdfPCell(new Phrase("交易类型",songtiSToufont));
		table.addCell(c6);
		PdfPCell c7 = new PdfPCell(new Phrase("交易日期",songtiSToufont));
		table.addCell(c7);
		PdfPCell c8 = new PdfPCell(new Phrase("交易时间",songtiSToufont));
		table.addCell(c8);
		PdfPCell c9 = new PdfPCell(new Phrase("交易金额（元）",songtiSToufont));
		table.addCell(c9);
		PdfPCell c10 = new PdfPCell(new Phrase("费率(%)",songtiSToufont));
		table.addCell(c10);				
		PdfPCell c11 = new PdfPCell(new Phrase("手续费（元）",songtiSToufont));
		table.addCell(c11);
	}
	
	/**
	 * 关闭 Document
	 * */
	private void close() {
		document.close();
	}
	
	/**
	 * 封装数据
	 * @param list 数据源
	 * @param tranAmt 交易金额合计
	 * @param fee 手续费合计
	 * @return int 0 --> 生成PDF成功； 1 --> 生成PDF失败,或不存在对账明细
	 * */
	public int setTableData(List<ViewStlBookDetail> list, String tranAmt, String fee) {
		if (list == null || list.size() == 0) return 1;
		try {
			createPDF(tranAmt, fee);
			ViewStlBookDetail info = null;
			for (int i = 0;i < list.size(); i++) {
				info = list.get(i);
				table.addCell(new PdfPCell(new Phrase(String.valueOf(i+1),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getMerNo(),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getMerName(),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getTermNo(),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getCardNo(),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getTranTypeDesc(),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getTranDate(),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getTranTime(),songtiSFivefont)));
				table.addCell(new PdfPCell(new Phrase(info.getTranAmt() == null ? "0" : info.getTranAmt()
						.toString(),songtiSFivefont)));
				if (StringUtils.isEmpty(info.getTimezone())) {
					table.addCell(new PdfPCell(new Phrase("0",songtiSFivefont)));
				} else if (info.getTimezone().startsWith(".")) {
					table.addCell(new PdfPCell(new Phrase("0" + info.getTimezone(),songtiSFivefont)));
				} else {
					table.addCell(new PdfPCell(new Phrase(info.getTimezone(),songtiSFivefont)));
				}
				table.addCell(new PdfPCell(new Phrase(info.getFee() == null ? "0" : info.getFee()
						.toString(),songtiSFivefont)));
			}
			document.add(table);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 1;
	}
	
	/**
	 * 上传PDF至FTP服务器
	 * @return int 0 --> 上传PDF成功； 1 --> 上传PDF失败
	 * */
	public int pdfUploadToFTP(FTPAndSFTP ftpAndSFTP) {
		FTPDirUtil ftp = null;
		try {
			ftp = new FTPDirUtil(ftpAndSFTP);
			ftp.ftpLogin();
			//仅上传当日需要发送邮件的文件夹
			boolean uploadFlag = ftp.uploadDirectory(fileDirectory, ftpAndSFTP.getTargetPath()); 
			if (uploadFlag) return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ftp.ftpLogOut();
		}
		return 1;
	}
	
	/**
	 * 通过SFTP上传PDF至LINUX服务器
	 * @return int 0 --> 上传PDF成功； 1 --> 上传PDF失败
	 * */
	public int sftpFileUpload(FTPAndSFTP ftpAndSFTP) {
		ftpAndSFTP.setSourcePath(fileDirectory);
		//仅上传当日需要发送邮件的文件夹
		boolean uploadFlag = SFTPLogin.login(ftpAndSFTP);
		if (uploadFlag) return 0;
		return 1;
	}
	
	/**
	 * 发送邮件并携带PDF文件
	 * 		当生成PDF后，直接发送邮件时调用
	 * @param 接收方邮箱
	 * @return int 0 --> 发送邮件成功； 1 --> 发送邮件失败或不存在对账明细
	 * */
	public int sendMailAndPDF(String[] receive) {
		if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(rootPath)) return 1;
		try {
			if (null != file) {
				String sendEmail = DictUtils.getDictValue("发送者邮箱", "SEND_EMAIL", "");
				String sendPassword = DictUtils.getDictValue("发送者邮箱密码", "SEND_PASSWORD", "");
				Mail mail = new Mail(sendEmail, sendPassword, receive,
						RecipientType.TO, fileName, fileName, file);
				mail.send();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
