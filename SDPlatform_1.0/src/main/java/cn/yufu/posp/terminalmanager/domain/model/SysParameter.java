package cn.yufu.posp.terminalmanager.domain.model;

/**
 * SysParameter entity. @author MyEclipse Persistence Tools
 */

public class SysParameter implements java.io.Serializable {

	// Fields

	private SysParameterId id;
	private String paramChinese;//������������
	private String paramValue;//����ֵ
	private String paramNotes;//����˵��
	private String enable;//���ñ�־0�������� 1������

	//���ñ�־��������   ҳ����ʾ��
	private String ch_enable;
	
	
	// Constructors

	public String getCh_enable() {
		return ch_enable;
	}

	public void setCh_enable(String chEnable) {
		if(chEnable!=null&&chEnable!=""){
			if(chEnable.equals("0"))
				this.ch_enable="������";
			if(chEnable.equals("1"))
				this.ch_enable="����";
		}else
		ch_enable = "����";
	}

	/** default constructor */
	public SysParameter() {
	}

	/** minimal constructor */
	public SysParameter(SysParameterId id, String paramChinese,
			String paramValue) {
		this.id = id;
		this.paramChinese = paramChinese;
		this.paramValue = paramValue;
	}

	/** full constructor */
	public SysParameter(SysParameterId id, String paramChinese,
			String paramValue, String paramNotes, String enable) {
		this.id = id;
		this.paramChinese = paramChinese;
		this.paramValue = paramValue;
		this.paramNotes = paramNotes;
		this.enable = enable;
	}

	// Property accessors

	public SysParameterId getId() {
		return this.id;
	}

	public void setId(SysParameterId id) {
		this.id = id;
	}

	public String getParamChinese() {
		return this.paramChinese;
	}

	public void setParamChinese(String paramChinese) {
		this.paramChinese = paramChinese;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamNotes() {
		return this.paramNotes;
	}

	public void setParamNotes(String paramNotes) {
		this.paramNotes = paramNotes;
	}

	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
		setCh_enable(enable);
	}

}