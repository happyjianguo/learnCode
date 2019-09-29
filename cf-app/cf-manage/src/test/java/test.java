import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.graphbuilder.struc.LinkedList;

import cn.com.jansh.core.security.authentication.dao.LoginAuthenticationProvider;
import cn.com.jansh.core.util.HttpClientRequest;
import cn.com.jansh.core.util.HttpClientUtil;
import cn.com.jansh.core.util.JsonUtil;
import cn.com.jansh.core.util.StringUtil;
import sun.misc.Queue;

public class test {
	public String unicodeToUtf8 (String s) throws Exception {
		return new String( s.getBytes("utf-8") , "utf-8");
		}
	public static void main(String[] args) throws Exception {
		Map map = new HashMap();
		map.put("sf", "sdf");
		System.out.println(map.hashCode());
		System.out.println("dfdag".hashCode());
		main5(args);
	}
	public static void main5(String[] args) throws Exception {
		String doc = "<?xml version='1.0' encoding=\"UTF-8\"?><TX><REQUEST_SN>201567764045217</REQUEST_SN><CUST_ID/><USER_ID/><PASSWORD/><TX_CODE>6WB002</TX_CODE><LANGUAGE>CN</LANGUAGE><TX_INFO><BacalAcctId>11001016100058000020</BacalAcctId><BacalAcctName>中国社会科学院大学</BacalAcctName><BacalBranchName>中国建设银行</BacalBranchName><DraweeBkNo>00000</DraweeBkNo><FanalInAcctId>6227000132080575867</FanalInAcctId><FanalInUserName>赵永恒</FanalInUserName><FanalInBranchName>中国建设银行股份有限公司石家庄河北师大分理处</FanalInBranchName><PayeeBkNo>00000</PayeeBkNo><FundType>2</FundType><Subject>2050205</Subject><EcoType>50502</EcoType><PayOutType>2</PayOutType><BudgetType>2</BudgetType><Item/><ControlCode>022</ControlCode><PayMethod>0006</PayMethod><CFinanceVoucherId>0</CFinanceVoucherId><RemRoute>1</RemRoute><EmgFlag>3</EmgFlag><TxAmount>0.01</TxAmount><PurPose>测试备注</PurPose><DivDAC/><Memo/><CiqVoucherId/><PuFlag>P</PuFlag></TX_INFO><SIGN_INFO/><SIGNCERT/></TX>";
//		String doc = "<?xml version='1.0' encoding='UTF-8'?><TX><REQUEST_SN>201567764045217</REQUEST_SN><CUST_ID/><USER_ID/><PASSWORD/><TX_CODE>6WB002</TX_CODE><LANGUAGE>CN</LANGUAGE><TX_INFO><BacalAcctId>11001016100058000020</BacalAcctId><BacalAcctName>中国社会科学院大学</BacalAcctName><BacalBranchName>中国建设银行</BacalBranchName><DraweeBkNo>00000</DraweeBkNo><FanalInAcctId>6227000132080575867</FanalInAcctId><FanalInUserName>赵永恒</FanalInUserName><FanalInBranchName>中国建设银行股份有限公司石家庄河北师大分理处</FanalInBranchName><PayeeBkNo>00000</PayeeBkNo><FundType>2</FundType><Subject>2050205</Subject><EcoType>50502</EcoType><PayOutType>2</PayOutType><BudgetType>2</BudgetType><Item/><ControlCode>022</ControlCode><PayMethod>0006</PayMethod><CFinanceVoucherId>0</CFinanceVoucherId><RemRoute>1</RemRoute><EmgFlag>3</EmgFlag><TxAmount>0.01</TxAmount><PurPose>测试备注</PurPose><DivDAC/><Memo/><CiqVoucherId/><PuFlag>P</PuFlag></TX_INFO><SIGN_INFO/><SIGNCERT/></TX>";
		doc=new String(doc.getBytes("GB2312"), "GBK");
		System.out.println(doc);
	String  content = doc.toString().replace("encoding=\'UTF-8\'", "encoding=\'GBK\'");
	String s2 = doc.toString().replace("encoding=\"UTF-8\"", "encoding=\"GBK\"");
//	<?xml version="1.0" encoding="UTF-8"?>
	System.out.println(s2);
	}
	public static void main2(String[] args) throws Exception {
		HashMap m = new HashMap();
		ConcurrentHashMap mc = new ConcurrentHashMap<>();
		LoginAuthenticationProvider enCoder = new LoginAuthenticationProvider();
		String pwd_y = RandomStringUtils.randomAlphanumeric(6);
		String pwd = enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd("admin", "admin"));
		System.out.println(pwd_y);
		System.out.println(pwd);
		int [] a ;
		
		String s11 = "锟叫癸拷锟斤拷锟斤拷锟斤拷";
		
		String gbkStr = "你好哦!"; //源码文件是GBK格式，或者这个字符串是从GBK文件中读取出来的, 转换为string 变成unicode格式
		//利用getBytes将unicode字符串转成UTF-8格式的字节数组
//		byte[] utf8Bytes = s11.getBytes("UTF-8"); 
		byte[] utf8Bytes = s11.getBytes("GB2312"); 
		//然后用utf-8 对这个字节数组解码成新的字符串
		String utf8Str = new String(utf8Bytes, "UTF-8");
//		简化后就是:
		System.out.println(utf8Str);
//		UTF-8 转GBK原理也是一样
//		return new String( s.getBytes("GBK") , "GBK");
		
		LinkedList s = new LinkedList();
//		ArrayList 
//		Arrays s = new Arrays();
		StringBuffer b = new StringBuffer() ;
		 final int MAXIMUM_CAPACITY = 1 << 30;
		 System.out.println(MAXIMUM_CAPACITY);
		//初始化密码
		/*LoginAuthenticationProvider enCoder = new LoginAuthenticationProvider();
		String pwd = enCoder.getPasswordEncoder().encode(enCoder.proccessPasswd("admin", "admin"));
		System.out.println(pwd);*/
	}
	public static void main1(String[] args) {
		try {
			Map<String, String> paramMap = new HashMap<String,String>();
			paramMap.put("sysid", "pJy0Jt04RGAY");
			paramMap.put("cporder", "1465461435456");
			paramMap.put("phone", "13337459638");
			//paramMap.put("ispno", "lt");
			paramMap.put("isptype", "ll");
			paramMap.put("facevalue", "20M");
			//paramMap.put("province", "qg");
			Queue q = new Queue<Object> ();
//			String orderUrl = "http://192.168.23.12:8080/cf-server/rechargeinter/order";
			String orderUrl = "http://127.0.0.1:8080/cf-manage/qcell/qcell";
			//String queryOrderUrl = "http://192.168.23.12:8080/cf-manage/rechargeinter/queryorder";
			
			HttpClientRequest request = gethttpClientRequest(paramMap);
			String sss = HttpClientUtil.httpPost(orderUrl, request);
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++"+sss);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static HttpClientRequest gethttpClientRequest(Map<String, String> map) throws JsonProcessingException{
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "130213";
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		return request;
	}
}

