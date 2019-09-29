import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jansh.comm.util.HttpClientRequest;
import com.jansh.comm.util.HttpClientUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.comm.util.JsonUtil;
import com.jansh.comm.util.StringUtil;

public class test {
	/*@Autowired
	private static CloudATSurplusMapper surplusmapper;*/
	
	public static void main(String[] args) {
		System.out.println(IDUtils.getTimeRandon());
		//queryPack();
//		chongzhi();//直充接口
		//chaxun();//查询接口
		/*String ss = "浙江金华";
		String s2 = "内蒙古呼和浩特";*/
//		List<String> list = new LinkedList<String>();
//		list.add("浙江省");
//		list.add("内蒙古自治区");
//		list.add("新疆维吾尔自治区");
//		list.add("甘肃省");
//		list.add("宁夏回族自治区");
//		list.add("北京");
//		list.add("天津");
//		list.add("河北");
//		list.add("山西");
//		list.add("辽宁");
//		list.add("吉林");
//		list.add("黑龙江");
//		list.add("上海");
//		list.add("江苏");
//		list.add("浙江");
//		list.add("安徽");
//		list.add("福建");
//		list.add("江西");
//		list.add("山东");
//		list.add("河南");
//		list.add("湖北");
//		list.add("湖南");
//		list.add("广东");
//		list.add("广西壮族自治区");
//		list.add("海南");
//		list.add("重庆");
//		list.add("四川");
//		list.add("贵州");
//		list.add("云南");
//		list.add("西藏自治区");
//		list.add("陕西");
//		list.add("青海");
//		list.add("台湾");
		
//		for (int i = 0; i < 100; i++) {
//			
//			new Thread(new Runnable() {
//				public void run() {
//					Map<String, Object> account = new HashMap<>();
//					account.put("orgid", "000000");
//					account.put("coinBuy", 1);
//					surplusmapper.updateBalance(account);
//				}
//			}).start();
//			
//		}
	/*	long starTime=System.currentTimeMillis();
		  for(int i=0;i<100000;i++){
			  querytwo(ss,list);
		  }
		long endTime=System.currentTimeMillis();
		long Time=endTime-starTime;
		System.out.println(Time);
		String s4 = querytwo(ss,list).get("provice");
		System.out.println(s4+"+1");
		System.out.println(querytwo(ss,list).get("city")+"+1");
		System.out.println("---------------4----------------");
		starTime=System.currentTimeMillis();
		  for(int i=0;i<100000;i++){
			  querytwo(s2,list);
		  }
		endTime=System.currentTimeMillis();
		Time=endTime-starTime;
		System.out.println(Time);
		System.out.println(querytwo(s2,list).get("provice")+"+2");
		System.out.println(querytwo(s2,list).get("city")+"+2");
		
		System.out.println("------------------------------------------------------------");
		
		starTime=System.currentTimeMillis();
		  for(int i=0;i<100000;i++){
			  query(ss);
		  }
		endTime=System.currentTimeMillis();
		Time=endTime-starTime;
		System.out.println(Time);
		System.out.println(query(ss).get("provice")+"+3");
		System.out.println(query(ss).get("city")+"+3");
		System.out.println("---------------3----------------");
		starTime=System.currentTimeMillis();
		  for(int i=0;i<100000;i++){
			  query(s2);
		  }
		endTime=System.currentTimeMillis();
		Time=endTime-starTime;
		System.out.println(Time);
		System.out.println(query(s2).get("provice")+"+4");
		System.out.println(query(s2).get("city")+"+4");*/
	}

	
	public static  Map<String,String> querytwo(String string , List<String> list){
		Map<String,String> map = new HashMap<String,String>();
		String s1 = string.substring(0,2);
		for(String s : list){
			if(s.contains(s1)){
//				String provice = theSame(string,s);
				String[] pc = subProviceCity(string,s);
				map.put("provice",pc[0]);
				map.put("city", pc[1]);
			}
		}
		return map;
	}
	
	public static Map<String,String> query(String string){
		Map<String,String> map = new HashMap<String,String>();
		String s1 = string.substring(0,2);
		if("黑龙江".contains(s1) || "内蒙古".contains(s1)){
			map.put("provice", string.substring(0,3));
			map.put("city",string.replace(string.substring(0, 3),""));
		}else{
			map.put("provice", string.substring(0,2));
			map.put("city",string.replace(string.substring(0, 2),""));
		}
		return map;
	}

	/**
	 * me
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String theSame(String str1,String str2){
		String s="";
		for(int i=0;i<str1.length();i++)
			for(int j=0;j<str2.length();j++){
				if(str1.charAt(i)==str2.charAt(j))
					s=s+str1.charAt(i);
			}
		return s;
	}
	/**
	 * 加毛
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String getSubString(String s1, String s2) {
		if (s1.length() > s2.length()) {
			String temp = s1;
			s1 = s2;
			s2 = temp;
		}
		int n = s1.length();
		int index = 0;
		ok: for (; n > 0; n--) {
			for (int i = 0; i < s1.length() - n + 1; i++) {
				String s = s1.substring(i, i + n);
				if (s2.indexOf(s) != -1) {
					index = i;
					break ok;
				}
			}
		}
		return s1.substring(index, index + n);
	} 
	/**
	 * nie
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String[] subProviceCity(String str1, String str2) {
		char[] stringArr1 = str1.toCharArray();
		char[] stringArr2 = str2.toCharArray();
		int l1 = stringArr1.length;
		int l2 = stringArr2.length;
		int len = l1 - 2 > l2 ? l2 : l1 - 2;
		char[] stringArr = new char[len];
		int i = 0;
		for (; i < len; i++) {
			if (stringArr2[i] == stringArr1[i]) {
				stringArr[i] = stringArr2[i];
			} else {
				break;
			}
		}
		return new String[] { String.valueOf(stringArr).trim(), str1.substring(i) };
	}

	@SuppressWarnings("unused")
	private static List<List<String>> delLastEmptyRow(List<List<String>> list) {
		System.out.println("处理末尾空行");
		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		int size = list.size() - 1;
		for (int i = size; i >= 0; i--) {
			List<String> row = list.get(i);
			boolean del = true;
			if (row == null || row.size() <= 0) {
				del = true;
			} else {
				for (String string : row) {
					if (StringUtils.isBlank(string)) {
						del = true;
					} else {
						del = false;
						break;
					}
				}
			}
			if (del) {
				System.out.println("删除空行：{}"+ i + 1);
				list.remove(i);
			} else {
				break;
			}
		}

		return list;
	}
	public static void queryPack(){
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("sysid", "PpHsaw7Uwpmu"); // 接入者id

			String orderUrl = "http://192.168.23.153:8080/cf-server/accessPackIner/queryPack";

			HttpClientRequest request = gethttpClientRequest(paramMap);
			String sss = HttpClientUtil.httpPost(orderUrl, request);

			System.out.println("+++++++++++++++++++++++++++++++++++++++" + sss);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void chongzhi() {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("sysid", "PpHsaw7Uwpmu"); // 接入者id
			paramMap.put("cporder", "10029933133227");// 接入者订单号
			paramMap.put("phone", "18811791221");// 手机号
			paramMap.put("localtype", "0");//0-全国，1-本地
			paramMap.put("isptype", "ll");// 充值类型
			paramMap.put("facevalue", "10M");// 面额

			String orderUrl = "http://192.168.23.153:8081/cf-server/rechargeinter/order";

			HttpClientRequest request = gethttpClientRequest(paramMap);
			String sss = HttpClientUtil.httpPost(orderUrl, request);

			System.out.println("+++++++++++++++++++++++++++++++++++++++" + sss);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void chaxun() {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("sysid", "pJ3QvgEvVGJs");
			paramMap.put("cporder", "pNkaDzyGz1QL");
			// paramMap.put("province", "qg");

			String orderUrl = "http://192.168.23.153:8081/cf-server/rechargeinter/queryorder";
			HttpClientRequest request = gethttpClientRequest(paramMap);
			String sss = HttpClientUtil.httpPost(orderUrl, request);

			System.out.println("+++++++++++++++++++++++++++++++++++++++" + sss);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static HttpClientRequest gethttpClientRequest(Map<String, String> map) throws JsonProcessingException {
		String json = JsonUtil.obj2json(map);
		String salt = StringUtil.randomCharNum(8);
		String source = salt + json + "2zOX9p24";//最后一位是接入者密钥
		String digest = DigestUtils.md5Hex(source);
		HttpClientRequest request = new HttpClientRequest();
		Map<String, String> headers = new HashMap<>();
		headers.put("JANSHAUTH", salt + digest);
		request.setHeaders(headers);
		request.setBody(json);
		return request;
	}
}
