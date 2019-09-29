package cn.yufu.core.web.form;

import org.apache.struts.validator.ValidatorForm;

/**
form基类
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
   获取该Form包含的数据。具体由子类实现。
   @return Object
   @roseuid 43BB2B58037F
    */
   public Object getData()
   {
    return null;
   }

   /**
   将数据保存到Form的各域。由子类具体实现。
   @param object Object
   @return void
   @roseuid 43BB2B8800A3
    */
   public void setData(Object object)
   {

   }

   /**
   清空各域。 由各子类实现
   @return void
   @roseuid 43BB2BFC026C
    */
   public void clear()
   {

   }
}
