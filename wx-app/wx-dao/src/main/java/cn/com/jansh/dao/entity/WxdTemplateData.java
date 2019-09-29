package cn.com.jansh.dao.entity;

public class WxdTemplateData {

	private String templateno;
	private String templateid;
	private String templatebkey;
	private String templatebvalue;
	private String templatebcolor;
	private String templatebseq;
	public String getTemplateno() {
		return templateno;
	}
	public void setTemplateno(String templateno) {
		this.templateno = templateno;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getTemplatebkey() {
		return templatebkey;
	}
	public void setTemplatebkey(String templatebkey) {
		this.templatebkey = templatebkey;
	}
	public String getTemplatebvalue() {
		return templatebvalue;
	}
	public void setTemplatebvalue(String templatebvalue) {
		this.templatebvalue = templatebvalue;
	}
	public String getTemplatebcolor() {
		return templatebcolor;
	}
	public void setTemplatebcolor(String templatebcolor) {
		this.templatebcolor = templatebcolor;
	}
	public String getTemplatebseq() {
		return templatebseq;
	}
	public void setTemplatebseq(String templatebseq) {
		this.templatebseq = templatebseq;
	}
	@Override
	public String toString() {
		return "WxdTemplateData [templateno=" + templateno + ", templateid=" + templateid + ", templatebkey="
				+ templatebkey + ", templatebvalue=" + templatebvalue + ", templatebcolor=" + templatebcolor
				+ ", templatebseq=" + templatebseq + "]";
	}
}
