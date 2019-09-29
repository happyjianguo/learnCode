import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XmlUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xmlStr="fsadfasdfasdasdf";
		try {
			System.out.println(getXmlStr(xmlStr));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String  getXmlStr(String xmlStr) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		// 字符串转XML
		String str="";
		StringReader sr = new StringReader(xmlStr); 
		InputSource is = new InputSource(sr); 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(is);
		str=doc.toString();
		//XML转字符串
//		TransformerFactory  tf  =  TransformerFactory.newInstance();
//		Transformer t = tf.newTransformer();
//		t.setOutputProperty("encoding","GB23121");//解决中文问题，试过用GBK不行
//		ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();
//		t.transform(new DOMSource(doc), new StreamResult(bos));
//		str = bos.toString();
		return str;
	}
	
}
