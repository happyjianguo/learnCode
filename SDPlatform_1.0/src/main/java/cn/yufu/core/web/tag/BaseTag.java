package cn.yufu.core.web.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class BaseTag
    extends TagSupport
{

  /**
      =pageContext.getSession()
   */
  private HttpSession session;

  /**
      @roseuid 43BDE546013D
   */
  public BaseTag()
  {

  }

  /**
      Tag�ĳ�ʼ����
      @return int
      @roseuid 43BBDD93004E
   */
  @Override
public int doStartTag()
  {
    return 0;
  }

  /**
      Tag�����������
      @return int
      @roseuid 43BBDE61033C
   */
  @Override
public int doAfterBody()
  {
    return 0;
  }

  /**
      Tag�Ľ�������
      @return int
      @roseuid 43BBDE8A009C
   */
  @Override
public int doEndTag()
  {
    return 0;
  }
}
