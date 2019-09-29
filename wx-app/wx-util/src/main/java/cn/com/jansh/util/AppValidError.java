package cn.com.jansh.util;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.ObjectError;

@SuppressWarnings("serial")
public class AppValidError extends ObjectError {

	private final String field;

	private final Object rejectedValue;

	private final boolean bindingFailure;

	/**
	 * @param code the message to be used to resolve this message
	 */
	public AppValidError(String code) {
		this("com", "com", null, false, new String[]{code}, null, null);
	}
	
	/**
	 * @param objectName the name of the affected object
	 * @param field the affected field of the object
	 * @param code the message to be used to resolve this message
	 */
	public AppValidError(String objectName, String field, String code) {
		this(objectName, field, null, false, new String[]{code}, null, null);
	}
	
	/**
	 * @param objectName the name of the affected object
	 * @param code the message to be used to resolve this message
	 */
	public AppValidError(String objectName, String code) {
		this(objectName, "com", null, false, new String[]{code}, null, null);
	}
	
	/**
	 * Create a new FieldError instance.
	 * 
	 * @param objectName
	 *            the name of the affected object
	 * @param field
	 *            the affected field of the object
	 * @param rejectedValue
	 *            the rejected field value
	 * @param bindingFailure
	 *            whether this error represents a binding failure (like a type
	 *            mismatch); else, it is a validation failure
	 * @param codes
	 *            the codes to be used to resolve this message
	 * @param arguments
	 *            the array of arguments to be used to resolve this message
	 * @param defaultMessage
	 *            the default message to be used to resolve this message
	 */
	public AppValidError(String objectName, String field, Object rejectedValue, boolean bindingFailure, String[] codes, Object[] arguments, String defaultMessage) {

		super(objectName, codes, arguments, defaultMessage);
		Assert.notNull(field, "Field must not be null");
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.bindingFailure = bindingFailure;
	}

	/**
	 * Return the affected field of the object.
	 */
	public String getField() {
		return this.field;
	}

	/**
	 * Return the rejected field value.
	 */
	public Object getRejectedValue() {
		return this.rejectedValue;
	}

	/**
	 * Return whether this error represents a binding failure (like a type
	 * mismatch); otherwise it is a validation failure.
	 */
	public boolean isBindingFailure() {
		return this.bindingFailure;
	}

	@Override
	public String toString() {
		return "Field error in object '" + getObjectName() + "' on field '" + this.field + "': rejected value [" + this.rejectedValue + "]; " + resolvableToString();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!super.equals(other)) {
			return false;
		}
		AppValidError otherError = (AppValidError) other;
		return getField().equals(otherError.getField())
				&& ObjectUtils.nullSafeEquals(getRejectedValue(), otherError.getRejectedValue())
				&& isBindingFailure() == otherError.isBindingFailure();
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = 29 * hashCode + getField().hashCode();
		hashCode = 29 * hashCode + ObjectUtils.nullSafeHashCode(getRejectedValue());
		hashCode = 29 * hashCode + (isBindingFailure() ? 1 : 0);
		return hashCode;
	}
}
