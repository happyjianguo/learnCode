package cn.com.jansh.util;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AppException extends Exception  implements Serializable{

	private static final long serialVersionUID = -754023769141877915L;
	
	private String errCode = "DEFAULT";

	public AppException(String errCode) {
		this.errCode = errCode;
	}

	public AppException(String errCode, String msg) {
		super( msg );
		this.errCode = errCode;
	}

	public AppException(String errCode, String msg, Throwable rootCause) {
		super( msg, rootCause );
		this.errCode = errCode;
	}

	public String getErrCode()
	{
		return errCode;
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	public int compareTo(Object object) {
		AppException myClass = (AppException) object;
		return new CompareToBuilder().append(this.errCode, myClass.errCode)
				.toComparison();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AppException)) {
			return false;
		}
		AppException rhs = (AppException) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.errCode, rhs.errCode).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1256520217, 343241553).appendSuper(
				super.hashCode()).append(this.errCode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("errCode", this.errCode)
				.append("cause",this.getCause()).append("localizedMessage",
						this.getLocalizedMessage()).append("message",
						this.getMessage()).append("stackTrace",
						this.getStackTrace()).toString();
	}

}
