/**
 *包名:cn.yufu.utils
 *描述:package cn.yufu.utils;
 */
package cn.yufu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * WarUtil.java 版权所有(C) 2017 裕福控股有限公司 创建:gll 时间:2017年7月28日 描述:TODO
 */
public class WarUtil {
	public static void main(String[] args) {

		Map<String, Object> map = TxtUtil.readTxtFile("E://test//jilu.txt");
		List<String> list = (List<String>) map.get("list");
		if ((Boolean) map.get("flag")) {
			for (String string : list) {
				System.out.println(string);
			}
		}
		if(list.size() ==0 || null == list){
			
		}else{
//			CreateFileAndJar(list);
			outJar(list);
		}
	}

	public static void outJar(List<String> list){
		JarOutputStream zout = null;
		try {
			zout = new JarOutputStream(new FileOutputStream("D://Jar//goujianfei.jar"));
			
				for(int i=0;i<list.size();i++){
					File inFile = new File(list.get(i));
					JarEntry zipEntry = new JarEntry(inFile.getName());
					
					InputStream ins = new FileInputStream(inFile);
					byte[] datas = new byte[ins.available()];
					ins.read(datas);
					ins.close();
	
					zout.putNextEntry(zipEntry);
					zout.write(datas);
				}
				
				zout.closeEntry();
				zout.finish();
				zout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void CreateFileAndJar(List<String> list) {
		String s="";
		for(int i=0;i<list.size();i++){
			s = list.get(i);
//			File inFile = new File(list.get(i));
//			JarEntry zipEntry = new JarEntry(inFile.getName());
		}
		File inFile = new File(s);
		JarEntry zipEntry = new JarEntry(inFile.getName());

		try {
			InputStream ins = new FileInputStream(inFile);
			byte[] datas = new byte[ins.available()];
			ins.read(datas);
			ins.close();

			JarOutputStream zout = new JarOutputStream(new FileOutputStream("D:/Jar/goujianfei.jar"));

			zout.putNextEntry(zipEntry);
			zout.write(datas);

			zout.closeEntry();
			zout.finish();
			zout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
