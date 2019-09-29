//package cn.yufu.posp.common.common.util;
//
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Map;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
//import javax.xml.namespace.QName;
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
//import org.dom4j.Document;
//import org.dom4j.DocumentFactory;
//import org.dom4j.Element;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.eprobiti.trs.TRSConnection;
//import com.eprobiti.trs.TRSResultSet;
//import com.trs.net.ftp.FtpURL;
//import com.trs.net.ftp.impl.FtpClientImpl;
//import cn.yufu.posp.common.domain.model.PageInfoModel;
//import cn.yufu.posp.common.domain.model.WebServiceDocModel;
//import cn.yufu.posp.common.domain.model.WebServiceFileModel;
//
//
//public class WebServiceSendDocUtil {
//	private static String sTargetEndpointAddress = "http://10.11.2.156/wcm/services/trswcm:ImportService";
//	private static String sTargetPublisNoticeAddress = "http://10.11.2.156/wcm/services/trswcm:WCMDocumentService";
//	private static String filePath="";//存放零时文件的路径
//	private static String username="bjtjoa";//访问trs的用户名
//	private static String password="bjtjoa";//访问trs的密码
//	private static String trsIp="10.11.249.102";//访问trs的ip地址
//	private static String port="8123";//访问trs的端口
//	private static String trsDBName="BJTJOA";//完成全文索引的目标数据库名
//	private static String trsDBOwner="SYSTEM";//完成全文索引的目标数据库所有者
//	private static String ftpServer="10.11.2.156";
//	private static String ftpUser="trs";
//	private static String ftpPass="trs123";
//	private static String ftpPath="/";
//	
//	//private static String filePath="";
//	public static void main(String[] args) throws Exception{
//        Calendar c=Calendar.getInstance();
//        System.out.println(c.getTime());
//        long threadInterval=1000*60;
//        c.add(Calendar.SECOND, -2 * (int) (threadInterval / 1000));
//        System.out.println(c.getTime());
////		BASE64Decoder base64De=new BASE64Decoder();
////		
////		BASE64Encoder base64En=new BASE64Encoder();
////		StringBuffer sb=new StringBuffer();
////		sb.append(base64En.encodeBuffer("MLH='T1'".getBytes()));
////		sb.append(base64En.encodeBuffer(" and DQZT='01' And LJDWBH='00105'".getBytes()));
////		String test=sb.toString();
////		sb=new StringBuffer();
////		sb.append(new String(base64De.decodeBuffer(test)));
////		System.out.println(sb.toString());
////		updateArchiveToFullTextIndex("test12356","test","123");
//		
//		WebServiceDocModel newWebServiceDocModel=new WebServiceDocModel();
//		newWebServiceDocModel.setChannelID("1000");
//		newWebServiceDocModel.setDeptName("部门");
//		newWebServiceDocModel.setDocNum("文号");
//		newWebServiceDocModel.setDocType("10");
//		newWebServiceDocModel.setSender("发送者");
//		newWebServiceDocModel.setSendTime(Calendar.getInstance().getTime());
//		newWebServiceDocModel.setTitle("标题");
//		newWebServiceDocModel.setPublishStatus("0");
//		newWebServiceDocModel.setDocEndTime(Calendar.getInstance().getTime());
//		List list1=new ArrayList();
//		WebServiceFileModel newWebServiceFileModel=new WebServiceFileModel();
//		newWebServiceFileModel.setName("概要设计说明书-辅助办公.doc");
//		File attfile=new File("c:/概要设计说明书-辅助办公.doc");
//		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(attfile));
//        byte[] contents=new byte[bis.available()];
//        int count=bis.available()/50;
//        for(int i=0;i<count+1;i++){
//        	if(count==i){
//        		bis.read(contents,i*50,bis.available());       
//        	}
//        	else{
//        		bis.read(contents,i*50,50);        	
//        	}        	
//        }
//        newWebServiceFileModel.setContent(contents);
////		newWebServiceFileModel.setContent("test".getBytes());
//		list1.add(newWebServiceFileModel);
//		newWebServiceFileModel=new WebServiceFileModel();
//		newWebServiceFileModel.setName("test1.txt");
//		newWebServiceFileModel.setContent("test1".getBytes());
//		list1.add(newWebServiceFileModel);	
//		newWebServiceDocModel.setFiles(list1);
////		byte[] xmlbytes=generateWebDocXML(newWebServiceDocModel);
////		File file1=new File("c:/zhangst123.txt");
//		try{
////			FileOutputStream fos=new FileOutputStream(file1);
////			fos.write(xmlbytes);
////			fos.flush();
////			fos.close();
////			publisNotice("1000");
////			System.out.println("123");
//			sendDocToWeb(newWebServiceDocModel);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//	}
//	public static void deleteNotices(String ids) throws Exception, IOException{
//        Service service = new Service();
//        Call call = null;
//        try {
//            call = (Call) service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(sTargetPublisNoticeAddress));
//            call.setOperationName(new QName("http://service.wcm.trs.com","deleteDocument"));
//            Boolean flag=(Boolean)call.invoke(new Object[]{ids,new Boolean(true), new Boolean(true)});
//            if(!flag.booleanValue()){
//              throw new Exception("撤消失败！");
//            }
//        } 
//        catch (Exception exc) {        	
//            exc.printStackTrace();
//            throw new Exception();
//        }		
//	}
//	public static void publisNotice(String channelIds) throws Exception, IOException{
//        Service service = new Service();
//        Call call = null;
//        try {
//            /*call = (Call) service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(sTargetPublisNoticeAddress));
//            call.setOperationName(new QName("http://service.wcm.trs.com","publishDocument"));
//            Boolean flag=(Boolean)call.invoke(new Object[]{(Object)channelIds});
//            if(!flag.booleanValue()){
//              throw new Exception("发布失败！");
//            }*/
//        } 
//        catch (Exception exc) {        	
//            exc.printStackTrace();
//            throw new Exception();
//        }	
//	}
//	public static void sendDocToWeb(WebServiceDocModel newWebServiceDocModel) throws Exception, IOException{
//		ByteArrayOutputStream out=new ByteArrayOutputStream();
//		String fileName=Calendar.getInstance().getTimeInMillis()+".xml";
//		ZipOutputStream zout =new ZipOutputStream(out);
//		ZipEntry e = new ZipEntry(fileName);
//		zout.putNextEntry(e);
//        byte[] xmlbytes=generateWebDocXML(newWebServiceDocModel);
////        File file2=new File("c:/zhangst123.txt");
////        FileOutputStream fos=new FileOutputStream(file2);
////		fos.write(xmlbytes);
////		fos.flush();
////		fos.close();
//		zout.write(xmlbytes);
//		zout.closeEntry();
//		System.out.println("123");
//		if(newWebServiceDocModel.getFiles()!=null && newWebServiceDocModel.getFiles().size()>0){
//			for(int i=0;i<newWebServiceDocModel.getFiles().size();i++){
//				WebServiceFileModel file1=(WebServiceFileModel)newWebServiceDocModel.getFiles().get(i);
//				String filename="";
//				if(file1.getName().indexOf(".")!=-1){
//					filename=i+file1.getName().substring(file1.getName().indexOf("."));
//				}
//				else{
//					filename=i+"";
//				}	
//				ZipEntry e1 = new ZipEntry(filename);
//				zout.putNextEntry(e1);
//				zout.write(file1.getContent());
//				zout.closeEntry();
//			}
//		}
//		zout.close();
//		System.out.println("234");
//		ByteArrayInputStream bais=new ByteArrayInputStream(out.toByteArray());
//		FtpClientImpl m_oFtpClient = null;
//		String ftpfileName=newWebServiceDocModel.getTitle()+Calendar.getInstance().getTimeInMillis()+".zip";
//        try
//        {
//            FtpURL url = new FtpURL("ftp://" + ftpUser+ ":" + ftpPass + "@"
//                    + ftpServer);
//            url.setHost(ftpServer);
//            url.setPort(21);
//            url.setUserName(ftpUser);
//            url.setPassWord(ftpPass);
//            m_oFtpClient = new FtpClientImpl(url);
//            m_oFtpClient.open();
//            boolean isSuc = m_oFtpClient.upload(bais, ftpfileName, null, FtpClientImpl.MODE_OVERWRITE);
//            if (!isSuc)
//            {
//                throw new Exception("上传Ftp file 失败!");
//            }
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//            throw ex;
//        }
//        finally
//        {
//            try
//            {
//                if (m_oFtpClient != null)
//                    m_oFtpClient.close();
//            }
//            catch (Exception ex)
//            {
//            }
//        }
//
//		String sResult = null;
//        Service service = new Service();
//        Call call = null;
//        try {        	
//            call = (Call) service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(sTargetPublisNoticeAddress));
//            call.setOperationName(new QName(sTargetPublisNoticeAddress,"importDocument"));
//            Boolean flag = (Boolean) call.invoke(new Object[]{ftpfileName,new Integer(newWebServiceDocModel.getChannelID())});
//            if(!flag.booleanValue()){
//            	throw new Exception("插入trs失败");
//            }
////            SAXReader s=new SAXReader();
////            Document document=s.read(new StringReader(sResult));
////			Element REPORTS=document.getRootElement();
////			String result=REPORTS.element("IS-SUCCESS").getText();
////			if(result.equals("false")){
////				String errorMsg=REPORTS.element("TITLE").getText();
////				Iterator REPORT=REPORTS.elementIterator("REPORT");
////				while(REPORT.hasNext()){
////					Element report=(Element)REPORT.next();
////					errorMsg=errorMsg+report.element("TITLE").getText();
////					if(report.element("IS-SUCCESS").getText().equals("false")){
////						errorMsg=errorMsg+report.element("ERROR-INFO").getText();
////					}
////				}
////				throw new Exception(errorMsg);
////			}		
//        } 
//        catch (Exception exc) {        	
//            exc.printStackTrace();
//            throw new Exception();
//        }	
//
//
//
//		
//		
//	}
//	private static byte[] generateWebDocXML(WebServiceDocModel newWebServiceDocModel) throws Exception{
//		DocumentFactory df = new DocumentFactory();
//		Document docs=df.createDocument();
//		//docs.setXMLEncoding("UTF-8");
//		Element doccs=docs.addElement("WCMDOCUMENTS");
//		Element doc=doccs.addElement("WCMDOCUMENT");
//		doc.addAttribute("Version","5.2");
//		Element properties=doc.addElement("PROPERTIES");
//		Element e=properties.addElement("DOCTYPE");
//		e.addText("10");
//		e=properties.addElement("DOCCHANNEL");
//		e.addText(newWebServiceDocModel.getChannelID());
//		e=properties.addElement("DOCTITLE");
//		e.addCDATA(newWebServiceDocModel.getTitle());
//		e=properties.addElement("DOCRELTIME");
//	    String strDate = "";
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        if (newWebServiceDocModel.getSendTime() != null)
//	    {
//	        strDate = sdf.format(newWebServiceDocModel.getSendTime());
//	    }
//		e.addText(strDate);
//		e=properties.addElement("DOCABSTRACT");
//		if(newWebServiceDocModel.getDocType()!=null){
//			e.addCDATA(newWebServiceDocModel.getDocType());
//		}	
//		else{
//			e.addCDATA("");
//		}
//		e=properties.addElement("DOCKEYWORDS");
//		e.addCDATA(newWebServiceDocModel.getDeptName());
//		e=properties.addElement("DOCSOURCE");
//		if(newWebServiceDocModel.getSourceDeptName()!=null){
//			e.addCDATA(newWebServiceDocModel.getSourceDeptName());
//		}	
//		else{
//			e.addCDATA("");
//		}
//		e=properties.addElement("DOCCONTENT");
//		if(newWebServiceDocModel.getDocNum()!=null){
//			e.addCDATA(newWebServiceDocModel.getDocNum());
//		}
//		else{
//			e.addCDATA("");
//		}
//		//编辑状态
//		
//		if(newWebServiceDocModel.getEditStatus()!=null){
//			e=properties.addElement("DOCSTATUS");
//			e.addCDATA(newWebServiceDocModel.getEditStatus());
//		}
//		else{
//		}
//		
//		if(newWebServiceDocModel.getDocEndTime()!=null){
//			e=properties.addElement("DOCENDTIME");
//			e.addText(sdf.format(newWebServiceDocModel.getDocEndTime()));
//		}	
//		if(newWebServiceDocModel.getPublishStatus()!=null){
//			e=properties.addElement("OAID");
//			e.addText(newWebServiceDocModel.getPublishStatus());
//		}
//		e=properties.addElement("DOCAUTHOR");		
//		e.addText(newWebServiceDocModel.getSender());
//		Element appendixs=doc.addElement("WCMAPPENDIXS");
//		if(newWebServiceDocModel.getFiles()!=null && newWebServiceDocModel.getFiles().size()>0){
//			for(int i=0;i<newWebServiceDocModel.getFiles().size();i++){
//				WebServiceFileModel file1=(WebServiceFileModel)newWebServiceDocModel.getFiles().get(i);
//				Element appendix=appendixs.addElement("WCMAPPENDIX");
//				appendix.addAttribute("Version","5.2");
//				Element appendixprops=appendix.addElement("PROPERTIES");
//				e=appendixprops.addElement("APPFLAG");
//				e.addText("10");
//				e=appendixprops.addElement("USEDVERSIONS");
//				e.addText("1");
//				e=appendixprops.addElement("APPFILE");	
//				if(file1.getName().indexOf(".")!=-1){
//					e.addCDATA(i+file1.getName().substring(file1.getName().indexOf(".")));
//				}
//				else{
//					e.addCDATA(i+"");
//				}		
//				e=appendixprops.addElement("APPDESC");
//				e.addCDATA(file1.getName());				
//			}
//		}
//		byte[] result;
//		try{
//			result=docs.asXML().getBytes("UTF-8");
//		}
//		catch(Exception ex){
//			throw new Exception("文档转码错误！");
//		}
//		
//		return  result;
//	}
//	public static void insertArchiveToFullTextIndex(String topic,String ArchiveTablename,String id)throws Exception{
////		导入全文索引的sql
//		StringBuffer trssb=new StringBuffer();
//		trssb.append("题名=");
//		trssb.append(topic);
//		trssb.append("\u00ff表名=");
//		trssb.append(ArchiveTablename);
//		trssb.append("\u00ffid=");
//		trssb.append(id);
//		trssb.append("\u00ff正文=");
////导入到trs的全文索引库中		 
//		TRSConnection conn = new TRSConnection();
//		conn.connect(trsIp,port,username,password);
//		int num=conn.executeInsert(trsDBName, trsDBOwner, trssb.toString()); //插入信息
//		if(num==0){
//				throw new Exception("插入全文检索库失败");
//		}
//	}
//	public static void updateArchiveToFullTextIndex(String topic,String ArchiveTablename,String id)throws Exception{
////导入全文索引的sql
//		StringBuffer trssb=new StringBuffer();
//		trssb.append("题名=");
//		trssb.append(topic);
//		StringBuffer trswheresb=new StringBuffer();
//		trswheresb.append("表名=");
//		trswheresb.append(ArchiveTablename);
//		trswheresb.append(" and id=");
//		trswheresb.append(id);
////导入到trs的全文索引库中		 
//		TRSConnection conn = new TRSConnection();
//		conn.connect(trsIp,port,username,password);
//		System.out.println(trssb.toString());
//		System.out.println(trswheresb.toString());
//		int num=conn.executeUpdate(trsDBName, trsDBOwner, trssb.toString(),trswheresb.toString()); //插入信息
//		if(num==0){
//			throw new Exception("刷新全文检索库失败");
//		}
//	}
//	public static void insertArchiveToFullTextIndex(JdbcTemplate st,String ArchiveTablename,String id)throws Exception{
//		//导入全文索引的sql
//		StringBuffer trssb=new StringBuffer();
//		StringBuffer sb=new StringBuffer();
//		//读取案卷信息
//		sb.append("select TM from ");
//		sb.append(ArchiveTablename);
//		sb.append(" where id=");
//		sb.append(id);
//		Map map=st.queryForMap(sb.toString());
//		String topic="";
//		 if(!map.isEmpty() && map.get("TM")!=null){
//			topic=(String)map.get("TM");
//		 }
//		 trssb.append("题名=");
//		 trssb.append(topic);
//		 trssb.append("\u00ff表名=");
//		 trssb.append(ArchiveTablename);
//		 trssb.append("\u00ffid=");
//		 trssb.append(id);
//		 
//		 
//		 //读取相关文件信息
//		 sb=new StringBuffer();
//		 sb.append("select id from ");
//		 sb.append(ArchiveTablename.substring(0,ArchiveTablename.length()-2));
//		 sb.append("WJ");
//		 sb.append(" where REFERID=");
//		 sb.append(id);
//		 List map1=st.queryForList(sb.toString());
//		 String fileId="0";
//		 if(map1!=null && !map1.isEmpty()){
//			 fileId=((Long)((Map)map1.get(0)).get("ID1")).toString();
//		 }
//		 //读取附件信息
//		 sb=new StringBuffer();
//		 sb.append("select fjmc,fjlx,FJWJ from ");
//		 sb.append(ArchiveTablename.substring(0,ArchiveTablename.length()-2));
//		 sb.append("FJ where (REFERID=");
//		 sb.append(id);
//		 sb.append(" and glst='AJ') or (REFERID=");
//		 sb.append(fileId);
//		 sb.append(" and glst='WJ')");
//		 List list1=st.queryForList(sb.toString());
//		 trssb.append("\u00ff正文=");
//		 List fileList=new ArrayList();
//		 boolean flag=false;
////		 if(list1!=null && list1.size()!=0){
////			 for(int i=0;i<list1.size();i++){
////				 Map map2=(Map)list1.get(i);
////				 String type=(String)map2.get("FJLX");
////				 if(type.equalsIgnoreCase("tif") || type.equalsIgnoreCase("gif") ||
////							type.equalsIgnoreCase("jpg")|| type.equalsIgnoreCase("bmp")|| 
////							type.equalsIgnoreCase("png")){						
////				 }
////				 else{	
////					 String fileName=(String)map2.get("FJMC");
////					 byte[] content=(byte[])map2.get("FJWJ");
////					 String path=filePath+fileName;
////					 FileOutputStream fos=new FileOutputStream(path);
////					 fos.write(content);
////					 fos.flush();
////					 fos.close();	
////					 fileList.add(path);
////					 if(flag){
////							trssb.append(";@");
////							trssb.append(path);
////						}
////						else{
////							trssb.append("@");
////							trssb.append(path);
////							flag=true;
////						}
////				 }
////			 }
////			 
////		 }
//		//导入到trs的全文索引库中		 
//		 TRSConnection conn = new TRSConnection();
//		 conn.connect(trsIp,port,username,password);
//		 int num=conn.executeInsert(trsDBName, trsDBOwner, trssb.toString()); //插入信息
//		 if(fileList.size()!=0){
//			 for(int i=0;i<fileList.size();i++){
//				 String path1=(String)fileList.get(0);
//				 File file1=new File(path1);
//				 file1.delete();
//			 }
//			 
//		 }
//		 if(num==0){
//			throw new Exception("插入全文检索库失败");
//		 }		 
//	}
//	public static void updateArchiveToFullTextIndex(JdbcTemplate st,String ArchiveTablename,String newType,String id)throws Exception{
////		导入全文索引的sql
//		String type1="";
//		StringBuffer trssb=new StringBuffer();
//		StringBuffer sb=new StringBuffer();
//		if(newType.equals("WJ")){
//			//读取案卷信息
//			sb.append("select REFERID as id1 from ");
//			sb.append(ArchiveTablename);
//			sb.append(" where id=");
//			sb.append(id);
//			type1="AJ";
//		}
//		else{
////			读取相关文件信息			 
//			sb.append("select id as id1 from ");
//			sb.append(ArchiveTablename.substring(0,ArchiveTablename.length()-2));
//			sb.append("WJ");
//			sb.append(" where REFERID=");
//			sb.append(id);
//			 type1="WJ";
//		}
//		//读取案卷信息
////		sb.append("select TM from ");
////		sb.append(ArchiveTablename);
////		sb.append(" where id=");
////		sb.append(id);
////		Map map1=st.queryForMap(sb.toString());
////		String topic="";
////		 if(!map1.isEmpty() && map1.get("TM")!=null){
////			topic=(String)map1.get("TM");
////		 }
////		 trssb.append("题名=");
////		 trssb.append(topic);
////		 trssb.append("\u00ff表名=");
////		 trssb.append(ArchiveTablename);
////		 trssb.append("\u00ffid=");
////		 trssb.append(id);
//		 List map1=st.queryForList(sb.toString());
//		 String fileId="0";
//		 if(map1!=null && !map1.isEmpty()){
//			 fileId=((Long)((Map)map1.get(0)).get("ID1")).toString();
//		 }
//		 StringBuffer trswheresb=new StringBuffer();
//		 if(newType.equals("WJ")){			 
//			 trswheresb.append("表名=");
//			 trswheresb.append(ArchiveTablename.substring(0,ArchiveTablename.length()-2));
//			 trswheresb.append("AJ");
//			 trswheresb.append(" and id=");
//			 trswheresb.append(fileId);
//		 }
//		 else{
//			 trswheresb.append("表名=");
//			 trswheresb.append(ArchiveTablename);
//			 trswheresb.append(" and id=");
//			 trswheresb.append(id);			 
//		 }
//		 
//		 //读取附件信息
//		 sb=new StringBuffer();
//		 sb.append("select fjmc,fjlx,FJWJ from ");
//		 sb.append(ArchiveTablename.substring(0,ArchiveTablename.length()-2));
//		 sb.append("FJ where (REFERID=");
//		 sb.append(id);
//		 sb.append(" and glst='");
//		 sb.append(newType);
//		 sb.append("') or (REFERID=");
//		 sb.append(fileId);
//		 sb.append(" and glst='");
//		 sb.append(type1);
//		 sb.append("')");
//		 List list1=st.queryForList(sb.toString());
//		 trssb.append("正文=");
//		 List fileList=new ArrayList();
//		 boolean flag=false;
////		 if(list1!=null && list1.size()!=0){
////			 for(int i=0;i<list1.size();i++){
////				 Map map2=(Map)list1.get(i);
////				 String type=(String)map2.get("FJLX");
////				 if(type.equalsIgnoreCase("tif") || type.equalsIgnoreCase("gif") ||
////							type.equalsIgnoreCase("jpg")|| type.equalsIgnoreCase("bmp")|| 
////							type.equalsIgnoreCase("png")){						
////				 }
////				 else{	
////					 String fileName=(String)map2.get("FJMC");
////					 byte[] content=(byte[])map2.get("FJWJ");
////					 String path=filePath+fileName;
////					 FileOutputStream fos=new FileOutputStream(path);
////					 fos.write(content);
////					 fos.flush();
////					 fos.close();	
////					 fileList.add(path);
////					 if(flag){
////							trssb.append(";@");
////							trssb.append(path);
////					 }
////					 else{
////							trssb.append("@");
////							trssb.append(path);
////							flag=true;
////					}
////				 }
////			 }
////			 
////		 }
//		//导入到trs的全文索引库中		 
//		 TRSConnection conn = new TRSConnection();
//		 
//		 conn.connect(trsIp,port,username,password);
//		 int num=conn.executeUpdate(trsDBName, trsDBOwner, trssb.toString(),trswheresb.toString()); //插入信息
////		 if(fileList.size()!=0){
////			 for(int i=0;i<fileList.size();i++){
////				 String path1=(String)fileList.get(0);
////				 File file1=new File(path1);
////				 file1.delete();
////			 }
////			 
////		 }
//		 if(num==0){
//			throw new Exception("刷新全文检索库失败");
//		 }	
//	}
//	static public PageInfoModel queryArchivesByFullText(String topic,PageInfoModel pageInfo) throws Exception{
//		//执行全文索引查询
//		int startIndex = 0;
//		int currentPage = pageInfo.getCurrentPage();
//		int pageSize = pageInfo.getPageSize();
//		long totalCount = 0;
//		//从全文索引库中查询 
//		 TRSConnection conn = new TRSConnection();
//		 conn.connect(trsIp,port,username,password);
//		 StringBuffer sb=new StringBuffer();
//		 sb.append("题名='%");
//		 sb.append(topic);
//		 sb.append("%' or 正文='");
//		 sb.append(topic);
//		 sb.append("'");
//		 TRSResultSet trsResultSet=conn.executeSelect(trsDBName,sb.toString(),false);
//		 totalCount=trsResultSet.getRecordCount();
//		//防止最后一页没有东西
//		if(currentPage!=1 && (currentPage*pageSize-totalCount>=pageSize))
//		{
//			currentPage = totalCount / pageSize + 1;
//			pageInfo.setCurrentPage(currentPage);
//		}
//		pageInfo.setTotalCount(new Long(totalCount).intValue());
//		if(totalCount>0){
//			startIndex = (currentPage-1)*pageSize;
//			trsResultSet.moveTo(0,new Long(startIndex).longValue());
//			trsResultSet.setBufferSize(pageSize*5,pageSize);
//			List result=new ArrayList();
////			for(int i=0;i<pageSize;i++){
////				ArchiveCommonModel acm=new ArchiveCommonModel();
////				acm.setTopic(trsResultSet.getString("题名"));
////				acm.setType(trsResultSet.getString("表名"));
////				acm.setId(new Long(trsResultSet.getString("id")));
////				result.add(acm);
////				if(!trsResultSet.moveNext()){
////					break;
////				}
////			}		
//			pageInfo.setResultItems(result);
//		}		
//		return pageInfo;
//	}
//}