package cn.yufu.core.web.form;

import org.apache.struts.validator.ValidatorForm;

/**
form����
 */
public abstract class BaseForm extends ValidatorForm
{

   /**
   @roseuid 43BDDF4C0332
    */
   public BaseForm()
   {
   }

   /**
   ��ȡ��Form���������ݡ�����������ʵ�֡�
   @return Object
   @roseuid 43BB2B58037F
    */
   public Object getData()
   {
    return null;
   }

   /**
   �����ݱ��浽Form�ĸ������������ʵ�֡�
   @param object Object
   @return void
   @roseuid 43BB2B8800A3
    */
   public void setData(Object object)
   {

   }

   /**
   ��ո��� �ɸ�����ʵ��
   @return void
   @roseuid 43BB2BFC026C
    */
   public void clear()
   {

   }
}
