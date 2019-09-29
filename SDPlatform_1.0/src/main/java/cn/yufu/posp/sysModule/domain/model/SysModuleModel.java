package cn.yufu.posp.sysModule.domain.model;

import java.math.BigDecimal;

/**
 * SysModuleId entity. @author MyEclipse Persistence Tools
 */

public class SysModuleModel implements java.io.Serializable {

	// Fields

	private BigDecimal moduleId;
	private String progName;
	private String progDesc;
	private String progParam;
	private String startMode;

	// ²éÑ¯Ìõ¼þ
	private BigDecimal queryModuleId;
	private String queryProgName;

	// Constructors

	public BigDecimal getQueryModuleId() {
		return queryModuleId;
	}

	public void setQueryModuleId(BigDecimal queryModuleId) {
		this.queryModuleId = queryModuleId;
	}

	public String getQueryProgName() {
		return queryProgName;
	}

	public void setQueryProgName(String queryProgName) {
		this.queryProgName = queryProgName;
	}

	/** default constructor */
	public SysModuleModel() {
	}

	/** minimal constructor */
	public SysModuleModel(BigDecimal moduleId, String progName, String startMode) {
		this.moduleId = moduleId;
		this.progName = progName;
		this.startMode = startMode;
	}

	/** full constructor */
	public SysModuleModel(BigDecimal moduleId, String progName, String progDesc, String progParam, String startMode) {
		this.moduleId = moduleId;
		this.progName = progName;
		this.progDesc = progDesc;
		this.progParam = progParam;
		this.startMode = startMode;
	}

	// Property accessors

	public BigDecimal getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(BigDecimal moduleId) {
		this.moduleId = moduleId;
	}

	public String getProgName() {
		return this.progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getProgDesc() {
		return this.progDesc;
	}

	public void setProgDesc(String progDesc) {
		this.progDesc = progDesc;
	}

	public String getProgParam() {
		return this.progParam;
	}

	public void setProgParam(String progParam) {
		this.progParam = progParam;
	}

	public String getStartMode() {
		return this.startMode;
	}

	public void setStartMode(String startMode) {
		this.startMode = startMode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysModuleModel))
			return false;
		SysModuleModel castOther = (SysModuleModel) other;

		return ((this.getModuleId() == castOther.getModuleId()) || (this.getModuleId() != null && castOther.getModuleId() != null && this.getModuleId().equals(
				castOther.getModuleId())))
				&& ((this.getProgName() == castOther.getProgName()) || (this.getProgName() != null && castOther.getProgName() != null && this.getProgName()
						.equals(castOther.getProgName())))
				&& ((this.getProgDesc() == castOther.getProgDesc()) || (this.getProgDesc() != null && castOther.getProgDesc() != null && this.getProgDesc()
						.equals(castOther.getProgDesc())))
				&& ((this.getProgParam() == castOther.getProgParam()) || (this.getProgParam() != null && castOther.getProgParam() != null && this
						.getProgParam().equals(castOther.getProgParam())))
				&& ((this.getStartMode() == castOther.getStartMode()) || (this.getStartMode() != null && castOther.getStartMode() != null && this
						.getStartMode().equals(castOther.getStartMode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result + (getProgName() == null ? 0 : this.getProgName().hashCode());
		result = 37 * result + (getProgDesc() == null ? 0 : this.getProgDesc().hashCode());
		result = 37 * result + (getProgParam() == null ? 0 : this.getProgParam().hashCode());
		result = 37 * result + (getStartMode() == null ? 0 : this.getStartMode().hashCode());
		return result;
	}

}