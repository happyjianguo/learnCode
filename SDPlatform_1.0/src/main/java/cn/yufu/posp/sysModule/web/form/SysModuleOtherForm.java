package cn.yufu.posp.sysModule.web.form;



import cn.yufu.core.web.form.BaseForm;

public class SysModuleOtherForm extends BaseForm 
{
   
	// Fields    

    private String moduleId;
    private String progName;
    private String progDesc;
    private String progParam;
    private String startMode;

    //²éÑ¯Ìõ¼þ
    private String queryModuleId;
    private String queryProgName;
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getProgDesc() {
		return progDesc;
	}
	public void setProgDesc(String progDesc) {
		this.progDesc = progDesc;
	}
	public String getProgParam() {
		return progParam;
	}
	public void setProgParam(String progParam) {
		this.progParam = progParam;
	}
	public String getStartMode() {
		return startMode;
	}
	public void setStartMode(String startMode) {
		this.startMode = startMode;
	}
	public String getQueryModuleId() {
		return queryModuleId;
	}
	public void setQueryModuleId(String queryModuleId) {
		this.queryModuleId = queryModuleId;
	}
	public String getQueryProgName() {
		return queryProgName;
	}
	public void setQueryProgName(String queryProgName) {
		this.queryProgName = queryProgName;
	}
    


	
}
