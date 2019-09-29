package cn.yufu.core.domain.logic;

import java.util.HashMap;

/**
 业务实现范例
 */
public interface LogicInterface
{

  /**
      用户自定义的业务处理方法


      @param hm - 业务处理对象集
      @return HashMap
      @roseuid 43BC814300B8
   */
  public HashMap creat(HashMap hm) throws cn.yufu.core.common.exception.
      ThtfException;
}
