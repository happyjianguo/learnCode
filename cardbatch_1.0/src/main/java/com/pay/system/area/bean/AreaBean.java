package com.pay.system.area.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.StringUtils;

/**
 * 
 * @TODO √Ë ˆ«¯”Ú–≈œ¢
 *
 * @author zl
 * @created on 2009-11-10
 * @version 1.0
 */
public class AreaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String area_code = "";
	private String area_no = "";
	private String area_name = "";
	private String prov_id = "";
	private String prov_name = "";
	
	public AreaBean(){
		
	}
	
    public AreaBean(HashMap record){
        if(record.get("area_code") == null){
            this.setArea_code("");
        }
        else{
            this.setArea_code(StringUtils.innerToOuter((String)record.get("area_code")).trim());
        }
        if(record.get("area_no") == null){
            this.setArea_no("");
        }
        else{
            this.setArea_no(StringUtils.innerToOuter((String)record.get("area_no")).trim());
        }
        if(record.get("area_name") == null){
            this.setArea_name("");
        }
        else{
            this.setArea_name(StringUtils.innerToOuter((String)record.get("area_name")).trim());
        }
        if(record.get("prov_id") == null){
        	this.setProv_id("");
        }
        else{
        	this.setProv_id(StringUtils.innerToOuter((String)record.get("prov_id")).trim());
        }
        if(record.get("prov_name") == null){
        	this.setProv_name("");
        }
        else{
        	this.setProv_name(StringUtils.innerToOuter((String)record.get("prov_name")).trim());
        }
	}

    public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_no() {
		return area_no;
	}

	public void setArea_no(String area_no) {
		this.area_no = area_no;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getProv_id() {
		return prov_id;
	}

	public void setProv_id(String prov_id) {
		this.prov_id = prov_id;
	}

	public String getProv_name() {
		return prov_name;
	}

	public void setProv_name(String prov_name) {
		this.prov_name = prov_name;
	}

	public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(area_code);
        sb.append("|");
        sb.append(area_no);
        sb.append("|");
        sb.append(area_name);
        sb.append("|");
        sb.append(prov_id);
        sb.append("|");
        sb.append(prov_name);
        sb.append("|");

        return new String(sb);
    }

}
