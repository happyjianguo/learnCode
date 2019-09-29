package cn.yufu.core.web.validator;

import org.apache.commons.validator.Field;
import org.apache.struts.action.ActionMessages;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.validator.Resources;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import java.io.*;

import cn.yufu.core.common.util.Debug;

public class CustomValidator extends BaseValidator
    implements Serializable
{
  public CustomValidator()
  {
    super();
    Debug.println("in CustomValidator");
  }
  /**
   * 校验方法
   * @param bean Object
   * @param va ValidatorAction
   * @param field Field
   * @param errors ActionMessages
   * @param validator Validator
   * @param request HttpServletRequest
   * @return boolean
   */
  public static boolean validate(Object bean,
                                 ValidatorAction va,
                                 Field field,
                                 ActionMessages errors,
                                 org.apache.commons.validator.Validator
                                 validator,
                                 HttpServletRequest request)
  {
    Debug.println("in CustomValidator");
    try
    {
      String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
      String sProperty2 = field.getVarValue("secondProperty");
      String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
      if (!GenericValidator.isBlankOrNull(value))
      {
        try
        {
          if (!value.equals(value2))
          {
            errors.add(field.getKey(),
                       Resources.getActionMessage(request, va, field));
            return false;
          }
        }
        catch (Exception e)
        {
          errors.add(field.getKey(),
                     Resources.getActionMessage(request, va, field));
          return false;
        }
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }

    return true;
  }
}
