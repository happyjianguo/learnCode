package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;
import cn.yufu.core.web.form.BaseForm;

public class EdcTerminalForm extends BaseForm {
	private EdcTerminalId id=new EdcTerminalId();
	
	/**EDC设备规格说明<p>终端所配置的EDC设备的规格、厂家、性能等说明性文字，以区别各种不同厂家型号的EDC设备。</p>**/
    private String edcDoc;
    
    /**打印机类型<p>EDC终端所配置的打印机型号</p>*/
    private String printerType;
    
    /**PIN PAD类型*/
    private String pinpadType;
    
    /**安装日期   YYYYMMDD*/
    private String setDate;
    
    /**安装地点<p>EDC所放置商户位置的说明性文字，便于维护时参考。</p>*/
    private String setAddr;
    
    /**EDC终端状态<p>Y’-正常,’N’-冻结<br><br>“冻结”标志的EDC终端发上的交易拒绝<br></p>*/
    private String terminalStat;
    
    /**EDC设备型号<p>约定的EDC设备类型标识串，据此刻判别此EDC使用哪种类型的格式进行解包、组包。<p>*/
    private String edcType;
    
    /**EDC软件版本*/
    private String softVer;
    
    /**参数下载标志*/
    private String downloadFlag;
    
    /**参数下载模式*/
    private String downloadMode;
    
    /**创建者*/
    private String updateOper;
    
    /**创建日期    YYYYMMDD*/
    private String updateDate;
    
    /**创建时间 hhmmss*/
    private String updateTime;
    
    /**终端状态  ---页面显示用---*/
    private String ch_terminalStat;
    
    /**日前和时间组合  ---页面显示用---*/
    private String ch_dateAndTime;
    
    /**业务角色  ---页面显示用---*/
    private String busRoleId;
    
    /**业务角色中文名字  ---页面显示用---*/
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
