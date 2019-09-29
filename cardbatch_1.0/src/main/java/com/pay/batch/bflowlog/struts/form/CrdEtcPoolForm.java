//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.batch.bflowlog.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
 
/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.form name="roleForm"
 */
public class CrdEtcPoolForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String verno_ctx;
    private String id;
	private String etc_pan;
	private String flag;
	private String crdbatch_batch;
	private String batchtask_ticket_no;
	
	private String insertdate; 
	private String inserttime; 
	
	private String updatedate;
	private String updatetime;
	
	
	//²éÑ¯Ìõ¼þ
	private String start_etc_pan;
	private String end_etc_pan;
	
	
    /** 
     * Method validate
     * @param mapping
     * @param request
     * @return ActionErrors
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * Method reset
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        // TODO Auto-generated method stub
    }

	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEtc_pan() {
		return etc_pan;
	}

	public void setEtc_pan(String etc_pan) {
		this.etc_pan = etc_pan;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCrdbatch_batch() {
		return crdbatch_batch;
	}

	public void setCrdbatch_batch(String crdbatch_batch) {
		this.crdbatch_batch = crdbatch_batch;
	}

	public String getBatchtask_ticket_no() {
		return batchtask_ticket_no;
	}

	public void setBatchtask_ticket_no(String batchtask_ticket_no) {
		this.batchtask_ticket_no = batchtask_ticket_no;
	}

	public String getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStart_etc_pan() {
		return start_etc_pan;
	}

	public void setStart_etc_pan(String start_etc_pan) {
		this.start_etc_pan = start_etc_pan;
	}

	public String getEnd_etc_pan() {
		return end_etc_pan;
	}

	public void setEnd_etc_pan(String end_etc_pan) {
		this.end_etc_pan = end_etc_pan;
	}
	
	

}
