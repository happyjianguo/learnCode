package cn.yufu.core.domain.logic;

import java.util.HashMap;

import cn.yufu.core.common.exception.ThtfException;

/**
 业务处理模版，封装了异常处理
 */
public class Template
{

  /**
      @roseuid 43BC825E0119
   */
  public Template()
  {

  }

  /**
      业务处理对象集
      try{
      java.util.HashMap o=callback.execute();
      } catch (Exception e) {
               //记录异常日志
                throw new pospException("业务处理出错");
           } finally {
           }
      @param callback
      @return HashMap
      @roseuid 43BC7F0700C9
   */
  public HashMap perform(CallBack callback) throws ThtfException
  {
    try
    {
      java.util.HashMap o = callback.execute();
      return o;
    }
    catch (Exception e)
    {
      //记录异常日志
      return null;
      //throw new ThtfException("业务处理出错");
    }
    finally
    {
    }
  }

}
