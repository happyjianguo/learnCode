package cn.yufu.core.domain.logic;

import java.util.HashMap;

import cn.yufu.core.common.exception.ThtfException;

/**
 ҵ����ģ�棬��װ���쳣����
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
      ҵ�������
      try{
      java.util.HashMap o=callback.execute();
      } catch (Exception e) {
               //��¼�쳣��־
                throw new pospException("ҵ�������");
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
      //��¼�쳣��־
      return null;
      //throw new ThtfException("ҵ�������");
    }
    finally
    {
    }
  }

}
