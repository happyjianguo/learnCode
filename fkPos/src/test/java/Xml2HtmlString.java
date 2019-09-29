import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
  
/** 
 * 通过xsl文件转换相应的xml数据文件为html格式字符串 
 * @author hsy 
 * 
 */  
public class Xml2HtmlString  
{  
    private Logger log = Logger.getLogger(this.getClass().getName());  
      
    public Xml2HtmlString()  
    {  
        super();  
    }  
      
    /** 
     * 将xml以xsl样式转化为html字符串  
     * @param xmlString xml字符串 
     * @param xslPath xsl路径 
     * @return 
     */  
    public String getHtmlString(String xmlString,String xslPath){  
        log.info("开始执行getHtmlString(...)方法");  
        String returnDocStr = "";  
        try {  
            SAXReader reader = new SAXReader();  
            ByteArrayInputStream bais = new ByteArrayInputStream(xmlString.getBytes());  
            Document doc = reader.read(bais);  
            Document transformDoc = this.transformDocument(doc,xslPath);  
            returnDocStr = this.write2String(transformDoc);  
            log.info("getHtmlString(...)执行成功!");  
        } catch (Exception e) {  
            log.info("getHtmlString(...)方法执行失败,提示信息["+e.getMessage()+"]");  
        }  
        return returnDocStr;  
    }  
      
    /** 
     * 通过xsl将xml数据文件转化doc对象 
     * @param doc xml文档对象 
     * @param xslPath xls文件路径 
     * @return 
     */  
    private Document transformDocument(Document doc,String xslPath){  
        log.info("开始执行 transformDocument(...)方法");  
        TransformerFactory factory = TransformerFactory.newInstance();  
        Document transformerDoc = null;  
        try {  
            Transformer transformer = factory.newTransformer(new StreamSource(xslPath));  
            DocumentSource docSource = new DocumentSource(doc);  
            DocumentResult docResult = new DocumentResult();  
            transformer.transform(docSource, docResult);  
            transformerDoc = docResult.getDocument();  
            log.info("transformDocument(...)执行成功!");  
        } catch (Exception e) {  
            log.info("transformDocument(...)方法执行失败,提示信息["+e.getMessage()+"]");  
        }  
        return transformerDoc;  
    }  
      
    /** 
     * 将doc文档对象转化为html字符串 
     * @param transformDoc doc文档 
     * @return 
     */  
    private String write2String(Document transformDoc){  
        log.info("开始执行 write2String(...)方法");  
        StringWriter strWriter = new StringWriter();  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        format.setEncoding("GBK");  
        format.setXHTML(true);  
        HTMLWriter htmlWriter = new HTMLWriter(strWriter,format);  
        format.setExpandEmptyElements(false);  
        try {  
            htmlWriter.write(transformDoc);  
            htmlWriter.flush();  
            log.info("write2String(...)执行成功!");  
        } catch (IOException e) {  
            log.info("write2String(...)方法执行失败,提示信息["+e.getMessage()+"]");  
        }  
        return strWriter.toString();  
    }  
      
    public static void main(String[] args){  
        Xml2HtmlString obj = new Xml2HtmlString();  
        String xmlString = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +  
                "<?xml-stylesheet type=\"text/xsl\" href=\"d:/CUSTOMER_Query.xsl\"?>" +  
                "<rows tableName=\"HSY_T_CUSTOMER\" token=\"80C3ED58-28DB-264F-5E3C-0CB174373E6C\" pageType=\"query-page\" pageNo=\"1\" pageSize=\"20\" recordsCount=\"9\" pagesCount=\"1\" title=\"大客户查询\" workflow=\"false\" xsl=\"xedit/xsl/CUSTOMER_Query.xsl\" print-template=\"xedit/xsl/CUSTOMER_Query.xsl\" >" +  
                    "<queryRow>" +  
                        "<START_DATE align=\"right\" operator=\"ge\" fieldTitle=\"创建时间\" dataType=\"dateTime\" >2013-02-19 15:44:50.0</START_DATE>" +  
                        "<STOP_DATE align=\"right\" operator=\"le\" fieldTitle=\"停用时间\" dataType=\"dateTime\" >2030-02-19 15:44:52.0</STOP_DATE>" +  
                        "<CUSTOMER_NAME align=\"right\" operator=\"eq\" fieldTitle=\"大用户名称\" dataType=\"string\" >时代复分</CUSTOMER_NAME>" +  
                    "</queryRow>" +  
                    "<row>" +  
                        "<CUSTOMER_ID align=\"right\" fieldTitle=\"大用户ID\" dataType=\"NUMBER\" pk=\"true\" hidden=\"false\">2</CUSTOMER_ID>" +  
                        "<CUSTOMER_NAME align=\"left\" fieldTitle=\"大用户名称\" dataType=\"VARCHAR2\" pk=\"false\" hidden=\"false\">时代复分</CUSTOMER_NAME>" +  
                        "<START_DATE align=\"center\" fieldTitle=\"创建时间\" dataType=\"DATE\" pk=\"false\" hidden=\"false\">2013-02-19 15:44:50.0</START_DATE>" +  
                        "<STOP_DATE align=\"center\" fieldTitle=\"停用时间\" dataType=\"DATE\" pk=\"false\" hidden=\"false\">2030-02-19 15:44:52.0</STOP_DATE>" +  
                        "<X align=\"left\" fieldTitle=\"X坐标\" dataType=\"VARCHAR2\" pk=\"false\" hidden=\"false\">456312</X>" +  
                        "<Y align=\"left\" fieldTitle=\"Y坐标\" dataType=\"VARCHAR2\" pk=\"false\" hidden=\"false\">456789</Y>" +  
                    "</row>" +  
               "</rows>";  
        String projectPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();// /X:/dev_env/server/tomcat-5.0.28/webapps/adam.workflow.frame/WEB-INF/classes/  
        projectPath = projectPath.substring(0, projectPath.lastIndexOf("/"));  
        projectPath = projectPath.substring(0, projectPath.lastIndexOf("/"));  
        projectPath = projectPath.substring(0, projectPath.lastIndexOf("/") + 1);// WebRoot根路径  
        String xlsPath = "d:/CUSTOMER_Query.xsl";  
        System.out.println("xlsPath:"+xlsPath);
        String str = obj.getHtmlString(xmlString, xlsPath);  
        System.out.println("str=="+str);  
    }   
      
}  