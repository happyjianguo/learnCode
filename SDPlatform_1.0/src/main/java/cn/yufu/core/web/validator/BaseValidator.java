package cn.yufu.core.web.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionMessages;

/**
 * 自定义web端数据校验的抽象类
 */
public abstract class BaseValidator {

	/**
	 * @roseuid 43BDEF23003A
	 */
	public BaseValidator() {

	}

	/**
	 * 自定义校验规则
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param errors
	 *            ActionMessages
	 * @param validator
	 *            Validator
	 * @param request
	 *            HttpServletRequest
	 * @return Boolean
	 * @roseuid 43B3583F0015
	 */

	public static boolean validate(Object bean, ValidatorAction va, Field field, ActionMessages errors, Validator validator, HttpServletRequest request) {
		return true;
	}

	/**
	 * @roseuid 43B4D0110214
	 */
	public void Basevalidator() {

	}
}
