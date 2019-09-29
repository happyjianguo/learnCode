/**
 *����:cn.yufu.posp.util
 *����:package cn.yufu.posp.util;
 */
package cn.yufu.posp.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpRequestUtil.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��19��
 * ����:http������
 */
public class HttpRequestUtil {

	static boolean proxySet = false;
    static String proxyHost = "127.0.0.1";
    static int proxyPort = 8087;
    /** 
     * ���� 
     * @param source 
     * @return 
     */  
    public static String urlEncode(String source,String encode) {  
        String result = source;  
        try {  
            result = java.net.URLEncoder.encode(source,encode);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return "0";  
        }  
        return result;  
    }
    public static String urlEncodeGBK(String source) {  
        String result = source;  
        try {  
            result = java.net.URLEncoder.encode(source,"GBK");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return "0";  
        }  
        return result;  
    }
    /** 
     * ����http�����ȡ���ؽ�� 
     * @param req_url �����ַ 
     * @return 
     */  
    public static String httpRequest(String req_url) {
        StringBuffer buffer = new StringBuffer();  
        try {  
            URL url = new URL(req_url);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  

            httpUrlConn.setDoOutput(false);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  

            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  

            // �����ص�������ת�����ַ���  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  

            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // �ͷ���Դ  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  

        } catch (Exception e) {  
            System.out.println(e.getStackTrace());  
        }  
        return buffer.toString();  
    }  

    /** 
     * ����http����ȡ�÷��ص������� 
     * @param requestUrl �����ַ 
     * @return InputStream 
     */  
    public static InputStream httpRequestIO(String requestUrl) {  
        InputStream inputStream = null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
            // ��÷��ص�������  
            inputStream = httpUrlConn.getInputStream();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return inputStream;  
    }


    /**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
//            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @param isproxy
     *               �Ƿ�ʹ�ô���ģʽ
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url, String param,boolean isproxy) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            if(isproxy){//ʹ�ô���ģʽ
                @SuppressWarnings("static-access")
                Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            }else{
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // �򿪺�URL֮�������

            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST����


            // ����ͨ�õ���������

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();

            // ��ȡURLConnection�����Ӧ�������
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // �����������
            out.write(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST ��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

	public static void main(String[] args) {
        String url = "http://jsphone.janshcrp.com/phonenum/phonenum/phonenum";
        String url1 = "http://service.tvkcrm.vankeservice.com/partner/commercial/upload?token=8_B2MwsSmudV8akzy-NR98a2E8o=&ts=1510717001799&appId=d6d43d983a1144d893987c9151e96770";
        String para = "phone=13034199557";
        String result = "[{\"createTime\": \"2017-11-13 00:00:00\",\"updateTime\": \"2017-11-13 00:00:00\",\"address\": \"��ַ\",\"operaterName\": \"����\",\"isValid\": \"1\",\"mainCardCustomerNo\": \"test00000000001\"}]";
//        String sr=HttpRequestUtil.sendPost(url,para,false);
        String sr2=HttpRequestUtil.sendPost(url1,result,false);
        System.out.println(sr2);
        
       
    }
    /**
	 * http����jsonתMap
	 * ���������ն�����Կ
	 * @param sr
	 * @return
	 */
	public static Map<String,String> jsonTarray(){
		
		String url = "http://192.168.10.74:8600";
        String chanel="FUKAWEB1";
        String trace_no="20170721143300";
        String pay_type="Crt_Key";
        String para = "Chanel="+chanel+"&Trace_no="+trace_no+"&Pay_type="+pay_type;
		
		Map<String,String> map = new HashMap<String,String>();
		
		try{
			String sr=HttpRequestUtil.sendGet(url,para);
			
			int code = sr.indexOf("Resp_code")+"Resp_code".length()+3;
	        String resp_code = sr.substring(code, code+3);
	        map.put("resp_code",resp_code);
	        
	        int tmk = sr.indexOf("sTmk")+"sTmk".length()+3;
	        String sTmk = sr.substring(tmk,tmk+32);
	        map.put("sTmk",sTmk);
	        
	        int lmk = sr.indexOf("sLmk")+"sLmk".length()+3;
	        String sLmk = sr.substring(lmk,lmk+32);
	        map.put("sLmk",sLmk);
	        
	        map.put("isboolean", "true");
		}catch(Exception e){
			map.put("isboolean", "false");
		}
		
        return map;
        
	}
}
