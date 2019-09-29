/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarkers工具类
 * @author king
 * @version 2013-01-15
 */
public class FreeMarkers {

	public static String renderString(String templateString, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString), new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}
	
	public static void main(String[] args) throws IOException {
		// renderString
		Map<String, String> model = com.google.common.collect.Maps.newHashMap();
		model.put("userName", "模板文件名");
		String result = FreeMarkers.renderString("hello ${userName}", model);
		System.out.println(result);
		// renderTemplate
		Configuration cfg = FreeMarkers.buildConfiguration("classpath:/");
		//System.out.println(cfg);
		Template template = cfg.getTemplate("testTemplate.ftl");
		String result2 = FreeMarkers.renderTemplate(template, model);
		System.out.println(result2);
	
//		Map<String, String> model = com.google.common.collect.Maps.newHashMap();
//		model.put("userName", "calvin");
//		String result = FreeMarkers.renderString("hello ${userName} ${r'${userName}'}", model);
//		System.out.println(result);
		//get classpath
		//String path =req.getSession().getServletContext().getRealPath("/");

		// 静态页面要存放的路径  
        String htmlPath = "d:/11111111.html";  
        File htmlFile = new File(htmlPath);  
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));  
        // 处理模版 map数据 ,输出流
        try {
			template.process(model, out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        out.flush();  
        out.close(); 
	}
	
}
