package cn.yufu.core.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.context.WebApplicationContext;

import cn.yufu.core.common.exception.ThtfException;
import cn.yufu.core.common.log.Log;
import cn.yufu.core.common.util.Debug;
import cn.yufu.posp.common.domain.model.UserData;


/**
 dispatchAction基类
 */
public abstract class BaseDispatchAction
    extends DispatchAction
{
  private WebApplicationContext wac;

  /**
      @roseuid 43BDE4EA0312
   */
  public BaseDispatchAction()
  {
  }

  /**
      super.setServlet(actionServlet);
    if (actionServlet != null) {
     ServletContext servletContext = actionServlet.getServletContext();
      wac=
   WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

    }
      @param actionServlet ActionServlet
      @roseuid 43BB219C0321
   */
  @Override
public void setServlet(ActionServlet actionServlet)
  {
    super.setServlet(actionServlet);
    if (actionServlet != null)
    {
      //2006－3－5，YH修改，否则StrutsMock无法测试。
      /*ServletContext servletContext = actionServlet.getServletContext();
      wac =
          org.springframework.web.context.support.WebApplicationContextUtils.
          getRequiredWebApplicationContext(servletContext);*/
    }
  }
  /**
   *
   * @param mapping ActionMapping
   * @param form ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ActionForward
   * @throws ThtfException
   */
  @Override
public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws
      ThtfException
  {
    Debug.println("in BaseDispatchAction");
    ActionForward forward;
    try
    {
      if (request.getParameter(mapping.getParameter()) == null)
      {
        forward = doShow(mapping, form, request, response);
      }
      else
      {
       // Debug.println("in BaseDispatchAction:"+(String)request.getAttribute("method"));
        forward = super.execute(mapping, form, request, response);
      }
    }
    catch (Exception ex)
    {
      Log.log(ex, "", null);
      ex.printStackTrace();
      return mapping.getInputForward();
    }
    finally
    {
      //释放资源
      removeFormBean(mapping, request);
    }
    return forward;
  }


  /**
   *
   * @param mapping ActionMapping
   * @param form ActionForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ActionForward
   * @throws Exception
   */
  public ActionForward doShow(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception
  {
    return null;
  }

  /**
   *
   * @return
   */
  public WebApplicationContext getWac()
  {
    return wac;
  }


  /**
   *  *释放资源
   * @param mapping ActionMapping
   * @param request HttpServletRequest
   */
  protected void removeFormBean(
      ActionMapping mapping,
      HttpServletRequest request)
  {
    if (mapping.getAttribute() != null)
    {
      if ("request".equals(mapping.getScope()))
      {
        request.removeAttribute(mapping.getAttribute());
      }
      else
      {
        HttpSession session = request.getSession();
        session.removeAttribute(mapping.getAttribute());
      }
    }
  }
  /**
     * 获取当前用户信息
     * @param request HttpServletRequest
     * @return UserData
     */
    protected UserData getSessionUserData(HttpServletRequest request)
    {
       HttpSession session = request.getSession() ;
      //获取当前用户信息
      //UserData ud=(UserData)session.getAttribute(SessionKey.USER_DATA);
       UserData ud= null; 
      return ud;
  }
}
