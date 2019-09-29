package com.pay.merchant.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class Mrch_classBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	 private String inst_id;
	 private String mrchno;
	 private String classify;
	 private String fmrchno;

	public Mrch_classBean() {
		
	}
	public Mrch_classBean (HashMap record) throws Exception{
		for (Object key : record.keySet()) {
			//System.out.println("--------key=[" + key + "]" + record.get(key));
			
			Class[] cargs = new Class[1];
			String realArgs = "";
			cargs[0] = realArgs.getClass();
			Method method = this.getClass().getMethod(
					"set"
							+ StringUtils.toUpperCaseFirstOne(((String) key)
									.toLowerCase()), cargs);
			Object arglist[] = new Object[1];
			if (record.get(key) == null) {
				arglist[0] = "";
			} else {
				arglist[0] = StringUtils.innerToOuter((String) record.get(key))
						.trim();
			}
			method.invoke(this, arglist);
		}
		
	}
	
	public String getInst_id() {
		return inst_id;
	}
	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}
	public String getMrchno() {
		return mrchno;
	}
	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getFmrchno() {
		return fmrchno;
	}
	public void setFmrchno(String fmrchno) {
		this.fmrchno = fmrchno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 

}
