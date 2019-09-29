package cn.com.jansh.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.util.FileUtil;

public class ZipUtil {

	private static final Logger logger = LogManager.getLogger(ZipUtil.class);

	public static void main(String[] args) {
		try {
			decompressZipfile("F:\\gameres\\temp\\8192819\\Q2hlG3SKQiAp\\temp.zip",
					"F:\\gameres\\temp\\8192819\\Q2hlG3SKQiAp\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解压zip文件
	 * 
	 * @param file
	 * @param outputDir
	 * @throws IOException
	 */
	public static void decompressZipfile(String file, String outputDir) throws IOException {
		File outputdirfile = new File(outputDir);
		if (!outputdirfile.exists()) {
			outputdirfile.mkdirs();
		}
		ZipFile zipFile = new ZipFile(file, "GBK");
		Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
		while (entries.hasMoreElements()) {
			ZipArchiveEntry entry = entries.nextElement();
			logger.info("==========" + entry.getName());
			File entryDestination = new File(outputDir, entry.getName());
			if (entry.isDirectory()) {
				entryDestination.mkdirs();
			} else {
				InputStream in = null;
				OutputStream out = null;
				try {
					in = zipFile.getInputStream(entry);
					out = new FileOutputStream(entryDestination);
					FileUtil.copyStream(in, out);
				} catch (IOException e) {
					logger.error(e);
					IOUtils.closeQuietly(zipFile);
					throw e;
				} finally {
					IOUtils.closeQuietly(in);
					IOUtils.closeQuietly(out);
				}

			}
		}
		IOUtils.closeQuietly(zipFile);
	}
	/**
	 * 读取压缩包内总文件夹名，如果没有总文件夹则返回null
	 * @param file
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("resource")
	public static String readZipFileName(String file) throws Exception {  
           InputStream in = new BufferedInputStream(new FileInputStream(file));  
           ZipInputStream zin = new ZipInputStream(in);  
           ZipEntry ze;  
           List<String> list = new LinkedList<String>();
           while ((ze = zin.getNextEntry()) != null) {  
               if (ze.isDirectory()) {
               } else {  
            	   list.add(ze.getName());
               }  
           }
           zin.closeEntry(); 
           boolean b = false;
           boolean c = false;
           //list不会为空
           String names ="";
           //判断压缩包下面是否有文件夹
           //判断list的元素是否带横杠“/”
           for(int i=0;i<list.size();i++){
        	   b = list.get(i).contains("/");
        	   if(!b){
        		   break;
        	   }
           }
           if(b){
        	   //取出list中的第一个元素list.get(0)的第一个“/”前的部分
        	   names=list.get(0).substring(0,list.get(0).indexOf("/"));
           }else{
        	   return "";
           }
           //使用list.get(0)的第一个“/”前的部分和list中的其他元素list.get(i)的第一个“/”前的部分比较，看是否相同，若不同返回false，否则返回true。
           for(int i=0;i<list.size();i++){
        	   c = names.equals(list.get(i).substring(0,list.get(i).indexOf("/")));
        	   if(!c){
        		   break;
        	   }
           }
           //判断c是否为true
           if(c){
        	   logger.info("==========" + names);
        	   return names;
           }else{
        	   return "";
           }
       } 
    /**
     * 删除压缩包
     * @param zipname
     */
    public static void deleteZip(String zipname){
    	File file = new File(zipname); 
        if(file.exists()){
        	logger.info("====删除压缩包======" + zipname);
        	file.delete(); 
        }else{
        	logger.error("文件不存在");
         }
    }
}
