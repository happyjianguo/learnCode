package cn.yufu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 当部署到linux/windows系统上时获取远程MAC
 * @author Administrator
 *
 */
public class MACUtil {
	
	public static String getMACAddressInLinux(String ip) {
    	String line = "";
    	String macAddress = "";
    	BufferedReader bufferedReader = null;
    	Process process = null;
    	try {
    		process = Runtime.getRuntime().exec("arp -a " + ip);
    		bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
    		int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.split(":").length == 6) {
					index = line.indexOf(":");
				}
				if (index != -1) {
					/**
					 * 取出mac地址并去除2边空格,转为大写
					 */
					if ((index -2) >= 0 && (index + 15) <= line.length()) {
						macAddress = line.substring(index - 2, index + 15).trim().toUpperCase();
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
    	
		return macAddress;
    }
	
	public static String getMACAddressInWin(String ip) {
    	String line = "";
    	String macAddress = "";
    	BufferedReader bufferedReader = null;
    	Process process = null;
    	try {
    		process = Runtime.getRuntime().exec("nbtstat -a " + ip);
    		bufferedReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
    		int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				//确保MAC没有乱码
				line = new String(line.getBytes(), "ISO-8859-1");
				if (line.split("-").length == 6) {
					index = line.indexOf("-");
				}
				if (index != -1) {
					/**
					 * 取出mac地址并去除2边空格,转为大写
					 */
					if ((index -2) >= 0 && (index + 15) <= line.length()) {
						macAddress = line.substring(index - 2, index + 15).trim().toUpperCase();
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
    	
		return macAddress;
    }
    
    public static void main(String[] args) {
		System.out.println(getMACAddressInWin("192.168.10.45"));
	}
}
