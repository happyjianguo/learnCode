/**
 * DecompositionJsonMsg.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:yangfan 2015-8-10
 */
package cn.com.jansh.service.impl.tools;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cn.com.jansh.model.ReceiveJsonModel;
import cn.com.jansh.model.ReceiveXmlModel;

/**
 *  解析微信JSON消息
 * @author yangfan
 * @version 1.0.0
 */
public class DecompositionMsg {

	/**
	 * 
	 * 把JSON字符串转换为JAVA 对象
	 * @param source JSON字符串
	 * @return 解析后的实体类
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static ReceiveJsonModel DecompositionJson(String source) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(source, ReceiveJsonModel.class);
	}
	
	/**
	 * 把Xml字符串转换为JAVA 对象
	 * @param xmlMsg
	 * @return
	 */
	public static ReceiveXmlModel xmlToObject(String xmlMsg){
		XStream xs = new XStream(new DomDriver());   
	    xs.processAnnotations(new Class[]{ReceiveXmlModel.class});  
	    return (ReceiveXmlModel) xs.fromXML(xmlMsg);
	}
}
