import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	      GregorianCalendar d = new GregorianCalendar();
	      int today = d.get(Calendar.DAY_OF_MONTH);
	      int month = d.get(Calendar.MONTH);
	      d.set(Calendar.DAY_OF_MONTH, 1);

	      int weekday = d.get(Calendar.DAY_OF_WEEK);

	      // get first day of week (Sunday in the U.S.)
	      int firstDayOfWeek = d.getFirstDayOfWeek();

	      // determine the required indentation for the first line
	      int indent = 0;
	      while (weekday != firstDayOfWeek)
	      {
	         indent++;
	         d.add(Calendar.DAY_OF_MONTH, -1);
	         weekday = d.get(Calendar.DAY_OF_WEEK);
	      }

	      // print weekday names
	      String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
	      do
	      {
	         System.out.printf("%4s", weekdayNames[weekday]);
	         d.add(Calendar.DAY_OF_MONTH, 1);
	         weekday = d.get(Calendar.DAY_OF_WEEK);
	      }
	      while (weekday != firstDayOfWeek);
	      System.out.println();

	      for (int i = 1; i <= indent; i++)
	         System.out.print("    ");

	      d.set(Calendar.DAY_OF_MONTH, 1);
	      do
	      {
	         // print day
	         int day = d.get(Calendar.DAY_OF_MONTH);
	         System.out.printf("%3d", day);

	         // mark current day with *
	         if (day == today) System.out.print("*");
	         else System.out.print(" ");

	         // advance d to the next day
	         d.add(Calendar.DAY_OF_MONTH, 1);
	         weekday = d.get(Calendar.DAY_OF_WEEK);

	         // start a new line at the start of the week
	         if (weekday == firstDayOfWeek) System.out.println();
	      }
	      while (d.get(Calendar.MONTH) == month);
	      // the loop exits when d is day 1 of the next month

	      // print final end of line if necessary
	      if (weekday != firstDayOfWeek) System.out.println();
	}

}