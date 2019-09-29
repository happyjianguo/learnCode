package cn.yufu.posp.terminalmanager.domain.model;

/**
 * EdcTerminal entity. @author MyEclipse Persistence Tools
 */

public class EdcTerminal implements java.io.Serializable {

	// Fields

	private EdcTerminalId id;
	
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
	// Constructors

    public String getCh_terminalStat() {
		return ch_terminalStat;
	}

	public void setCh_terminalStat(String chTerminalStat) {
		if(chTerminalStat!=null){
			if("Y".equals(chTerminalStat))
				ch_terminalStat="正常";
			if("N".equals(chTerminalStat))
				ch_terminalStat="冻结";
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
				if(tt[1].equals("1")){//tag==1 时 为日前
					if(ch_dateAndTime!=null)
						this.ch_dateAndTime=tt[0]+" "+ch_dateAndTime;
					else
						this.ch_dateAndTime=tt[0]+" ";
				}
				if(tt[1].equals("2")){//tag==2 时 为时间
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