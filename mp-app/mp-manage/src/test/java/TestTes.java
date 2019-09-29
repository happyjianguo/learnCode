import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.JsonUtil;
import com.jansh.comm.util.StringUtil;
public class TestTes{
	/**
	 * yyyyMMddHHmmss
	 */
	public static final DateTimeFormatter formatter_DateTimestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final DateTimeFormatter formatter_DateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * TODO
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) throws Exception{
//		//total1();
//		//判断文件名称是否合法	1、XXX_XXX_XXX.zip格式	2、文件名只能由数字、字母下划线.号（最后）组成
//		String ss = "aaa_bbb_ccc.zip";
//		System.out.println(charAtnum(ss));
//		System.out.println(ss.length());
//	}
	 public static void main(String[] args) throws Exception {
		 total();
	    }
	public static void deletefile() {
		File file = new File("H:\\ztree.zip"); 
		  if(file.exists()){
		     file.delete(); 
		     System.out.println("ss");
		   }
		   else{
		     System.out.println("文件不存在");
		   }
	}
	    //返回压缩包内文件的名称 TODO
	    @SuppressWarnings("resource")
		public static String readZipFile(String file) throws Exception {  
//	           ZipFile zf = new ZipFile(file);  
	           InputStream in = new BufferedInputStream(new FileInputStream(file));  
	           ZipInputStream zin = new ZipInputStream(in);  
	           ZipEntry ze;  
	           List<String> list = new LinkedList<String>();
	           while ((ze = zin.getNextEntry()) != null) {  
	               if (ze.isDirectory()) {
	               } else {  
	            	   list.add(ze.getName());
//	                   System.err.println("file - " + ze.getName() + " : "  
//	                           + ze.getSize() + " bytes");  
//	                   long size = ze.getSize();  
//	                   if (size > 0) {  
//	                       BufferedReader br = new BufferedReader(  
//	                               new InputStreamReader(zf.getInputStream(ze)));  
//	                       String line;  
//	                       while ((line = br.readLine()) != null) {  
////	                           System.out.println(line);  
//	                       }  
//	                       br.close();  
//	                   }  
	               }  
	           }  
	           zin.closeEntry();  
//	           System.out.println(list);
	           String names ="";
	           if(!(null == list || list.size() ==0 )){
	        	   names=list.get(0).substring(0,list.get(0).indexOf("/"));
	           }
	           boolean b = false;
	           for(int i=0;i<list.size();i++){
	        	   b = names.equals(list.get(i).substring(0,list.get(i).indexOf("/")));
	           }
	           if(b){
	        	   return names;
	           }
			return null;
	       }  
	
	public static Boolean charAtnum(String ss) {
		String strExp="^[A-Za-z0-9_]+$";
		if(!ss.substring(0, ss.length()-4).matches(strExp)){
			return false;
		}
		int count=0;
		for(int i=0;i<ss.length();i++){
			if(ss.charAt(i)=='_'){
				count++;
			}
		}
		if(count != 2){
			return false;
		}else{
			
			if(ss.indexOf("_") == 0	|| 
				ss.lastIndexOf("_") == ss.length()-5 ||
				ss.lastIndexOf("_")==ss.lastIndexOf("_")-1){
				return false;
			}else{
				return true;
			}
		}
	}


	/**
	 * 返回好看的时间格式
	 * @return
	 */
	public static String returnDateTime() {
		return LocalDateTime.now().format(formatter_DateTime);
	}


	/**
	 * 返回当前时间
	 * @return
	 */
	public static LocalTime returntime() {
		return LocalTime.now();
	}


	/**
	 * 返回当前日期
	 * @return
	 */
	public static LocalDate returnDate() {
		return LocalDate.now();
	}


	/**
	 * 返回当前yyyyMMddHHmmss时间
	 * @return
	 */
	public static String returnnowLong() {
		return returnnow().format(formatter_DateTimestamp);
	}


	/**
	 * 返回当前时间
	 * @return
	 */
	public static LocalDateTime returnnow() {
		return LocalDateTime.now();
	}

	public static void total2() throws JsonProcessingException, Exception {
		String  url="http://192.168.23.160:8080/mp-manage/blackinter/acquireblack";
		Map<String,String> map=new HashMap<String,String>();
		map.put("actionid", "2");
		map.put("blackvalue", "1333745963w");
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "465";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		String wxStateJson = HttpClientUtil.httpPost(url,request);
		System.out.println(wxStateJson);
		Map<String, Object> newStateMap = JsonUtil.readMapObject(wxStateJson);
		System.out.println(newStateMap.get("data"));
	}
	
	
	public static void total1() throws JsonProcessingException, Exception {
		String  url="http://cs.ftsafe.com/gmc/dzpa/acquireward";
		Map<String,String> map=new HashMap<String,String>();
		map.put("gameid", "Q7tGUXgdCu3L");
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "465";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		String wxStateJson = HttpClientUtil.httpPost(url,request);
		System.out.println(wxStateJson);
		Map<String, Object> newStateMap = JsonUtil.readMapObject(wxStateJson);
		System.out.println(newStateMap.get("data"));
	}
	public static void total() throws JsonProcessingException, Exception {
		String  url="http://cs.ftsafe.com/gmc/dzpa/acquirePvUv";
		Map<String,String> map=new HashMap<String,String>();
		map.put("gameid", "Q7tGUXgdCu3L");
		map.put("startDate", "2016-10-10 15:56");	
		map.put("endDate", "2017-01-13 15:56");
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "465";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		String wxStateJson = HttpClientUtil.httpPost(url,request);
		System.out.println(wxStateJson);
		Map<String, Object> newStateMap = JsonUtil.readMapObject(wxStateJson);
		System.out.println(newStateMap.get("data"));
	}

	/**
	 * 总 pv
	 */
	public static void totalPV() throws JsonProcessingException, Exception {
		String  url="http://192.168.23.160:8081/llg_game_dzp/singleaction/acquireTotalPv";
		Map<String,String> map=new HashMap<String,String>();
		map.put("gameid", "Q23k5EUPRM5W");
		map.put("startDate", "2016-11-10");
		map.put("endDate", "2016-12-01");
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "465";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		String wxStateJson = HttpClientUtil.httpPost(url,request);
		System.out.println(wxStateJson);
		Map<String, Object> newStateMap = JsonUtil.readMapObject(wxStateJson);
		System.out.println(newStateMap.get("data"));
	}

	/**
	 * 总 uv
	 */
	public static void totalUv() throws JsonProcessingException, Exception {
		String  url="http://192.168.23.160:8081/llg_game_dzp/singleaction/acquireTotalUv";
		Map<String,String> map=new HashMap<String,String>();
		map.put("gameid", "Q23k5EUPRM5W");
		map.put("startDate", "2016-11-10");
		map.put("endDate", "2016-12-01");
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "465";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		String wxStateJson = HttpClientUtil.httpPost(url,request);
		System.out.println(wxStateJson);
		Map<String, Object> newStateMap = JsonUtil.readMapObject(wxStateJson);
		System.out.println(newStateMap.get("data"));
	}
}
