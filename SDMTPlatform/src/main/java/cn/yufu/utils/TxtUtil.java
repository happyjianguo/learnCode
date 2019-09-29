/**
 *包名:cn.yufu.utils
 *描述:package cn.yufu.utils;
 */
package cn.yufu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TxtUtil.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年7月28日
 * 描述:TODO
 */
public class TxtUtil {
	
	public static Map<String,Object> readTxtFile(String st){
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> list = new LinkedList<String>();
		boolean flag = true;
        try {
            File file=new File(st);

            if(file.isFile() && file.exists()){ //判断文件是否存在

                InputStreamReader read = new InputStreamReader(

                new FileInputStream(file));//考虑到编码格式

                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null){

                    if(!"".equals(lineTxt)){
                    	list.add(lineTxt);
                    }
                }

                read.close();

	        }else{
	            System.out.println("找不到指定的文件");
	            flag=false;
	        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            flag=false;
        }
        map.put("flag", flag);
        map.put("list", list);
        return map;
    }
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		Map<String,Object> map = readTxtFile("E:\\test\\jilu.txt");
		if((Boolean)map.get("flag")){
			List<String> list = (List<String>) map.get("list");
			for (String string : list) {
				System.out.println(string);
			}
		}
	}

}
