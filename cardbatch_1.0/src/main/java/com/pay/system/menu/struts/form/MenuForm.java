//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.menu.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.form name="itemForm"
 */
public class MenuForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String itemdescrip;
    private String itemname;
    private String itemno;
   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return null;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public String getItemdescrip() {
        return itemdescrip;
    }

    public void setItemdescrip(String itemdescrip) {
        this.itemdescrip = itemdescrip;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
  
    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }
}

