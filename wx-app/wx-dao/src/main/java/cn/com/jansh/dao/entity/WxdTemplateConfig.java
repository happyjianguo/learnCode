package cn.com.jansh.dao.entity;

public class WxdTemplateConfig {
	
	private String templateno;
	private String templatetitle;
	private String templateid;
	private String templateurl;
	private String templatecolor;
	private String templatehfirst;
	private String templatefremark;
	private String appid;
	public String getTemplateno() {
		return templateno;
	}
	public void setTemplateno(String templateno) {
		this.templateno = templateno;
	}
	public String getTemplatetitle() {
		return templatetitle;
	}
	public void setTemplatetitle(String templatetitle) {
		this.templatetitle = templatetitle;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getTemplateurl() {
		return templateurl;
	}
	public void setTemplateurl(String templateurl) {
		this.templateurl = templateurl;
	}
	public String getTemplatecolor() {
		return templatecolor;
	}
	public void setTemplatecolor(String templatecolor) {
		this.templatecolor = templatecolor;
	}
	public String getTemplatehfirst() {
		return templatehfirst;
	}
	public void setTemplatehfirst(String templatehfirst) {
		this.templatehfirst = templatehfirst;
	}
	public String getTemplatefremark() {
		return templatefremark;
	}
	public void setTemplatefremark(String templatefremark) {
		this.templatefremark = templatefremark;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	@Override
	public String toString() {
		return "WxdTemplateConfig [templateno=" + templateno + ", templatetitle=" + templatetitle + ", templateid="
				+ templateid + ", templateurl=" + templateurl + ", templatecolor=" + templatecolor + ", templatehfirst="
				+ templatehfirst + ", templatefremark=" + templatefremark + ", appid=" + appid + "]";
	}
}
