package cn.yufu.core.domain.logic;

import java.util.HashMap;

import cn.yufu.core.common.exception.ThtfException;

public interface CallBack
{

  /**
      @param hm - 业务处理对象集
      @throws cn.yufu.posp.core.common.exception.pospException
      @roseuid 43BC7FF70056
   */
  public HashMap execute() throws ThtfException;
}
