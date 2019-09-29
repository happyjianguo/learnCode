/**
 * FormatJsonProcess.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:yangfan 2015-7-29
 */
package cn.com.jansh.service.impl.tools;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.jansh.model.SendJsonModel;


/**
 * 封装请求报文JSON
 * @author yangfan
 * @version 1.0.0
 */
public class FormatJsonProcess {

	/**
	 * 交易提醒通知
	 * 模板消息类的请求数据
	 * @param senddate 发送实体类
	 * @return 返回封装后的JSON格式结果
	 * @throws JsonProcessingException 
	 */
    public static String formatJsonDate286(SendJsonModel senddate) throws JsonProcessingException { 
    	
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonCommon  = new HashMap<String, Object>();
        //创建jsonCommon
        jsonCommon.put("touser", senddate.getTouser());
        jsonCommon.put("template_id", senddate.getTemplate_id());
        jsonCommon.put("url", senddate.getUrl());
        jsonCommon.put("topcolor", senddate.getTopcolor());
        System.out.println("jsonObj:"+jsonCommon);
        //创建jsonBody
        Map<String, Map<String, String>> jsonBody  = new HashMap<String, Map<String, String>>();
        Map<String, String> jsonFirstDate  = new HashMap<String, String>();
        jsonFirstDate.put("value", senddate.getFirst());
        jsonFirstDate.put("color", senddate.getFirstcolor());
        
        Map<String, String> jsonTimeDate  = new HashMap<String, String>();
        jsonTimeDate.put("value", senddate.getTime());
        jsonTimeDate.put("color", senddate.getTimecolor());
        
        Map<String, String> jsonTypeDate  = new HashMap<String, String>();
        jsonTypeDate.put("value", senddate.getType());
        jsonTypeDate.put("color", senddate.getTypecolor());
        
        Map<String, String> jsonNumberDate  = new HashMap<String, String>();
        jsonNumberDate.put("value", senddate.getNumber());
        jsonNumberDate.put("color", senddate.getNumbercolor());
        
        Map<String, String> jsonBalanceDate  = new HashMap<String, String>();
        jsonBalanceDate.put("value", senddate.getBalance());
        jsonBalanceDate.put("color", senddate.getBalancecolor());
        
        Map<String, String> jsonRemarkDate  = new HashMap<String, String>();
        jsonRemarkDate.put("value", senddate.getRemark());
        jsonRemarkDate.put("color", senddate.getRemarkcolor());
        
        jsonBody.put("first", jsonFirstDate);
        jsonBody.put("time", jsonTimeDate);
        jsonBody.put("type", jsonTypeDate);
        jsonBody.put("number", jsonNumberDate);
        jsonBody.put("balance", jsonBalanceDate);
        jsonBody.put("remark", jsonRemarkDate);
        System.out.println("jsonObj:"+jsonBody);
        
        //在jsonCommon后面追加一个jsonBody
        jsonCommon.put("data", jsonBody);
        System.out.println("jsonObj:"+jsonCommon);
        //把JSONObject转换为 字符
        return mapper.writeValueAsString(jsonCommon);
    }
    
    
    
}
