package cn.yufu.posp.terminalmanager.domain.model;

/**
 * EdcTerminal entity. @author MyEclipse Persistence Tools
 */

public class EdcTerminal implements java.io.Serializable {

	// Fields

	private EdcTerminalId id;
	
	/**EDC�豸���˵��<p>�ն������õ�EDC�豸�Ĺ�񡢳��ҡ����ܵ�˵�������֣���������ֲ�ͬ�����ͺŵ�EDC�豸��</p>**/
    private String edcDoc;
    
    /**��ӡ������<p>EDC�ն������õĴ�ӡ���ͺ�</p>*/
    private String printerType;
    
    /**PIN PAD����*/
    private String pinpadType;
    
    /**��װ����   YYYYMMDD*/
    private String setDate;
    
    /**��װ�ص�<p>EDC�������̻�λ�õ�˵�������֣�����ά��ʱ�ο���</p>*/
    private String setAddr;
    
    /**EDC�ն�״̬<p>Y��-����,��N��-����<br><br>�����ᡱ��־��EDC�ն˷��ϵĽ��׾ܾ�<br></p>*/
    private String terminalStat;
    
    /**EDC�豸�ͺ�<p>Լ����EDC�豸���ͱ�ʶ�����ݴ˿��б��EDCʹ���������͵ĸ�ʽ���н���������<p>*/
    private String edcType;
    
    /**EDC����汾*/
    private String softVer;
    
    /**�������ر�־*/
    private String downloadFlag;
    
    /**��������ģʽ*/
    private String downloadMode;
    
    /**������*/
    private String updateOper;
    
    /**��������    YYYYMMDD*/
    private String updateDate;
    
    /**����ʱ�� hhmmss*/
    private String updateTime;
    
    
    /**�ն�״̬  ---ҳ����ʾ��---*/
    private String ch_terminalStat;
    
    /**��ǰ��ʱ�����  ---ҳ����ʾ��---*/
    private String ch_dateAndTime;

    /**ҵ���ɫ  ---ҳ����ʾ��---*/
    private String busRoleId;

    /**ҵ���ɫ��������  ---ҳ����ʾ��---*/
    private String busRoleName;
	// Constructors

    public String getCh_terminalStat() {
		return ch_terminalStat;
	}

	public void setCh_terminalStat(String chTerminalStat) {
		if(chTerminalStat!=null){
			if("Y".equals(chTerminalStat))
				ch_terminalStat="����";
			if("N".equals(chTerminalStat))
				ch_terminalStat="����";
		}else
		    ch_terminalStat = chTerminalStat;
	}

	public String getTerminalStat() {
		return this.terminalStat;
	}

	public void setTerminalStat(String terminalStat) {
		this.terminalStat = terminalStat;
		setCh_terminalStat(terminalStat);
	}

	public String getEdcType() {
		return this.edcType;
	}

	public void setEdcType(String edcType) {
		this.edcType = edcType;
	}
	
	public String getSoftVer() {
		return this.softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public String getDownloadFlag() {
		return this.downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public String getDownloadMode() {
		return this.downloadMode;
	}

	public void setDownloadMode(String downloadMode) {
		this.downloadMode = downloadMode;
	}
	
	public String getUpdateOper() {
		return this.updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;		
	}

	public String getUpdateDate() {
		return this.updateDate;		
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
		setCh_dateAndTime(updateDate+"#1");
	}

	public String getCh_dateAndTime() {
		return ch_dateAndTime;
	}

	public String getBusRoleId() {
		return busRoleId;
	}

	public void setBusRoleId(String busRoleId) {
		this.busRoleId = busRoleId;
	}

	public String getBusRoleName() {
		return busRoleName;
	}

	public void setBusRoleName(String busRoleName) {
		this.busRoleName = busRoleName;
	}
	
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		setCh_dateAndTime(updateTime+"#2");
	}
    public void setCh_dateAndTime(String chDateAndTime) {
		if(chDateAndTime!=null){
			String[] tt=chDateAndTime.split("#");
			if(tt.length==2){
				if(tt[1].equals("1")){//tag==1 ʱ Ϊ��ǰ
					if(ch_dateAndTime!=null)
						this.ch_dateAndTime=tt[0]+" "+ch_dateAndTime;
					else
						this.ch_dateAndTime=tt[0]+" ";
				}
				if(tt[1].equals("2")){//tag==2 ʱ Ϊʱ��
					if(ch_dateAndTime!=null)
						this.ch_dateAndTime=ch_dateAndTime+tt[0];
					else
						this.ch_dateAndTime=tt[0];
				}
			}else
				this.ch_dateAndTime=chDateAndTime;
		}else
			 this.ch_dateAndTime=chDateAndTime;
	}
    
	public String getEdcDoc() {
		return edcDoc;
	}

	public void setEdcDoc(String edcDoc) {
		this.edcDoc = edcDoc;
	}

	public String getPrinterType() {
		return printerType;
	}

	public void setPrinterType(String printerType) {
		this.printerType = printerType;
	}

	public String getPinpadType() {
		return pinpadType;
	}

	public void setPinpadType(String pinpadType) {
		this.pinpadType = pinpadType;
	}

	public String getSetDate() {
		return setDate;
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}

	public String getSetAddr() {
		return setAddr;
	}

	public void setSetAddr(String setAddr) {
		this.setAddr = setAddr;
	}

	public EdcTerminalId getId() {
		return this.id;
	}
	public void setId(EdcTerminalId id) {
		this.id = id;
	}
	
	/** default constructor */
	public EdcTerminal() {
	}

	/** minimal constructor */
	public EdcTerminal(EdcTerminalId id) {
		this.id = id;
	}
	/** full constructor */
	
	public EdcTerminal(EdcTerminalId id, String edcDoc, String printerType,
			String pinpadType, String setDate, String setAddr,
			String terminalStat, String edcType, String softVer,
			String downloadFlag, String downloadMode, String updateOper,
			String updateDate, String updateTime) {
		super();
		this.id = id;
		this.edcDoc = edcDoc;
		this.printerType = printerType;
		this.pinpadType = pinpadType;
		this.setDate = setDate;
		this.setAddr = setAddr;
		this.terminalStat = terminalStat;
		this.edcType = edcType;
		this.softVer = softVer;
		this.downloadFlag = downloadFlag;
		this.downloadMode = downloadMode;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "EdcTerminal [id=" + id + ", edcDoc=" + edcDoc + ", printerType=" + printerType + ", pinpadType="
				+ pinpadType + ", setDate=" + setDate + ", setAddr=" + setAddr + ", terminalStat=" + terminalStat
				+ ", edcType=" + edcType + ", softVer=" + softVer + ", downloadFlag=" + downloadFlag + ", downloadMode="
				+ downloadMode + ", updateOper=" + updateOper + ", updateDate=" + updateDate + ", updateTime="
				+ updateTime + ", ch_terminalStat=" + ch_terminalStat + ", ch_dateAndTime=" + ch_dateAndTime
				+ ", busRoleId=" + busRoleId + ", busRoleName=" + busRoleName + "]";
	}

}