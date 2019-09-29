package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;
import cn.yufu.core.web.form.BaseForm;

public class EdcTerminalForm extends BaseForm {
	private EdcTerminalId id=new EdcTerminalId();
	
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

	public EdcTerminalId getId() {
		return id;
	}

	public void setId(EdcTerminalId id) {
		this.id = id;
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
		if(setDate!=null&&setDate!=""){
			String [] tt=setDate.split("-");
			   String str="";
			   for(int i=0;i<tt.length;i++){
			     str=str+tt[i];
			   }
			   this.setDate=str;
		}else
		this.setDate = setDate;
	}

	public String getSetAddr() {
		return setAddr;
	}

	public void setSetAddr(String setAddr) {
		this.setAddr = setAddr;
	}

	public String getTerminalStat() {
		return terminalStat;
	}

	public void setTerminalStat(String terminalStat) {		
		this.terminalStat = terminalStat;
	}

	public String getEdcType() {
		return edcType;
	}

	public void setEdcType(String edcType) {
//		this.edcType = edcType.trim();
		this.edcType = edcType == null ? "" : edcType.trim();
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer == null ? "" : softVer.trim();
//		this.softVer = softVer.trim();
	}

	public String getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public String getDownloadMode() {
		return downloadMode;
	}

	public void setDownloadMode(String downloadMode) {
		this.downloadMode = downloadMode;
	}

	public String getUpdateOper() {
		return updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCh_terminalStat() {
		return ch_terminalStat;
	}

	public void setCh_terminalStat(String chTerminalStat) {
		ch_terminalStat = chTerminalStat;
	}

	public String getCh_dateAndTime() {
		return ch_dateAndTime;
	}

	public void setCh_dateAndTime(String chDateAndTime) {
		ch_dateAndTime = chDateAndTime;
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

	@Override
	public String toString() {
		return "EdcTerminalForm [id=" + id + ", edcDoc=" + edcDoc + ", printerType=" + printerType + ", pinpadType="
				+ pinpadType + ", setDate=" + setDate + ", setAddr=" + setAddr + ", terminalStat=" + terminalStat
				+ ", edcType=" + edcType + ", softVer=" + softVer + ", downloadFlag=" + downloadFlag + ", downloadMode="
				+ downloadMode + ", updateOper=" + updateOper + ", updateDate=" + updateDate + ", updateTime="
				+ updateTime + ", ch_terminalStat=" + ch_terminalStat + ", ch_dateAndTime=" + ch_dateAndTime
				+ ", busRoleId=" + busRoleId + ", busRoleName=" + busRoleName + "]";
	}
}
