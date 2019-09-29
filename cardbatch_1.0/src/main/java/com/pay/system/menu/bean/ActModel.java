package com.pay.system.menu.bean;

import com.pay.util.JSONUtil;

public class ActModel {
	//资源ID
	public String resourceId;
	
	//操作ID
	public String actId;
	
	//描述
	public String descr;
	
	//标志
	public String flag;
	
	//提示确认信息
	public String confirmMsg;

	public String toString(int tabCount){
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < tabCount; i++)
			buf.append("\t");
		
		buf.append("<CMISAct actId=\""+this.actId+"\"");
		buf.append(" resourceId=\""+this.resourceId+"\"");
		if(this.descr != null)
			buf.append(" descr=\""+this.descr+"\"");
		if(this.flag != null)
			buf.append(" flag=\""+this.flag+"\"");
		if(this.confirmMsg != null)
			buf.append(" confirmMsg=\""+this.confirmMsg+"\"");
		
		buf.append("/>");
		
		return buf.toString();
	}
	
	public String toString(){
		return this.toString(0);
	}
	
	public String toJSONString(){
		StringBuffer buf = new StringBuffer();
		buf.append("{id:'").append(JSONUtil.normalizeString(this.actId));
		buf.append("',label:'").append(JSONUtil.normalizeString(this.descr));
		buf.append("'}");
		return buf.toString();
	}
}
