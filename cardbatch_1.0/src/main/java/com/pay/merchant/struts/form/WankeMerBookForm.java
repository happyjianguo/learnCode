package com.pay.merchant.struts.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class WankeMerBookForm extends ActionForm {
	private String yard_id;
	private String yard_mer_no;
	private String yard_mer_name;
	private String yard_mer_type;
	private String yard_mer_type_name;
	private String yard_ter_no;
	private String yard_scene_name;
	private String yard_scene_id;
	
	public String getYard_id() {
		return yard_id;
	}
	public void setYard_id(String yard_id) {
		this.yard_id = yard_id;
	}
	public String getYard_mer_no() {
		return yard_mer_no;
	}
	public void setYard_mer_no(String yard_mer_no) {
		this.yard_mer_no = yard_mer_no;
	}
	public String getYard_mer_name() {
		return yard_mer_name;
	}
	public void setYard_mer_name(String yard_mer_name) {
		this.yard_mer_name = yard_mer_name;
	}
	public String getYard_mer_type() {
		return yard_mer_type;
	}
	public void setYard_mer_type(String yard_mer_type) {
		this.yard_mer_type = yard_mer_type;
	}
	public String getYard_mer_type_name() {
		return yard_mer_type_name;
	}
	public void setYard_mer_type_name(String yard_mer_type_name) {
		this.yard_mer_type_name = yard_mer_type_name;
	}
	public String getYard_ter_no() {
		return yard_ter_no;
	}
	public void setYard_ter_no(String yard_ter_no) {
		this.yard_ter_no = yard_ter_no;
	}
	

	public String getYard_scene_name() {
		return yard_scene_name;
	}
	public void setYard_scene_name(String yard_scene_name) {
		this.yard_scene_name = yard_scene_name;
	}
	public String getYard_scene_id() {
		return yard_scene_id;
	}
	public void setYard_scene_id(String yard_scene_id) {
		this.yard_scene_id = yard_scene_id;
	}

}
