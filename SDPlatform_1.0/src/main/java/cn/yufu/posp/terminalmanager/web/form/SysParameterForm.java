package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.core.web.form.BaseForm;

public class SysParameterForm extends BaseForm {
	
	private SysParameterId id=new SysParameterId();
	private String paramChinese;//������������
	private String paramValue;//����ֵ
	private String paramNotes;//����˵��
	private String enable;//���ñ�־0�������� 1������
	
	
	//���ñ�־��������   ҳ����ʾ��
	private String ch_enable;
	
	
	public String getCh_enable() {
		return ch_enable;
	}
	public void setCh_enable(String chEnable) {
		ch_enable = chEnable;
	}
	public SysParameterId getId() {
		return id;
	}
	public void setId(SysParameterId id) {
		this.id = id;
	}
	public String getParamChinese() {
		return paramChinese;
	}
	public void setParamChinese(String paramChinese) {
		this.paramChinese = paramChinese;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamNotes() {
		return paramNotes;
	}
	public void setParamNotes(String paramNotes) {
		this.paramNotes = paramNotes;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
}
