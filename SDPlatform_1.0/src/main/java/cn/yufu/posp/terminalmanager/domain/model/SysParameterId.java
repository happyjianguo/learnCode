package cn.yufu.posp.terminalmanager.domain.model;

/**
 * SysParameterId entity. @author MyEclipse Persistence Tools
 */

public class SysParameterId implements java.io.Serializable {

	// Fields

	private String paramType;//参数类型
	private String paramName;//参数名称

	// Constructors

	/** default constructor */
	public SysParameterId() {
	}

	/** full constructor */
	public SysParameterId(String paramType, String paramName) {
		this.paramType = paramType;
		this.paramName = paramName;
	}

	// Property accessors

	public String getParamType() {
		return this.paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysParameterId))
			return false;
		SysParameterId castOther = (SysParameterId) other;

		return ((this.getParamType() == castOther.getParamType()) || (this
				.getParamType() != null
				&& castOther.getParamType() != null && this.getParamType()
				.equals(castOther.getParamType())))
				&& ((this.getParamName() == castOther.getParamName()) || (this
						.getParamName() != null
						&& castOther.getParamName() != null && this
						.getParamName().equals(castOther.getParamName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getParamType() == null ? 0 : this.getParamType().hashCode());
		result = 37 * result
				+ (getParamName() == null ? 0 : this.getParamName().hashCode());
		return result;
	}

}