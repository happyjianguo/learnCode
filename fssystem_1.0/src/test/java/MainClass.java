//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;

//import com.lowagie.text.pdf.BaseFont;

public class MainClass {
	public void createPdf() throws Exception {
//		// step 1
//		String inputFile = "D:/index.html";
//		String url = new File(inputFile).toURI().toURL().toString();
//		String outputFile = "D:/index.pdf";
//		System.out.println(url);
//		// step 2
//		OutputStream os = new FileOutputStream(outputFile);
//		org.xhtmlrenderer.pdf.ITextRenderer renderer = new org.xhtmlrenderer.pdf.ITextRenderer();
//		renderer.setDocument(url);
//
//		// step 3 解决中文支持
//		org.xhtmlrenderer.pdf.ITextFontResolver fontResolver = renderer
//				.getFontResolver();
//		fontResolver.addFont("c:/Windows/Fonts/simsun.ttc",
//				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//
//		renderer.layout();
//		renderer.createPDF(os);
//		os.close();
//
//		System.out.println("create pdf done!!");
	}

	public static void main(String[] args) throws Exception {
		// Calendar calendar = Calendar.getInstance();
		// calendar.add(Calendar.MONTH, -1); // 得到前一月
		// Date date = calendar.getTime();
		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//
		// 设置日期格式
		// System.out.println(df.format(date));
		// MainClass a=new MainClass();
		// a.createPdf();

//		Document doc = null;
//		try {
//			doc = new Document();
//			PdfWriter.getInstance(doc, new FileOutputStream("d:\\index.pdf"));
//			doc.open();
//			doc.add(new Paragraph("Hello World"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		} finally {
//			doc.close();
//		}
	}

}