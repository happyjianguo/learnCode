package cn.yufu.core.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.springframework.web.context.WebApplicationContext;

import cn.yufu.core.common.log.Log;
import cn.yufu.posp.common.domain.model.UserData;


public abstract class BaseAction
    extends Action
{

  private WebApplicationContext wac;

  /**
      @roseuid 43BCD5C300EF
   */
  public BaseAction()
  {

  }

  /**
      上下文;
      super.setServlet(actionServlet);
    if (actionServlet != null) {
     ServletContext servletContext = actionServlet.getServletContext();
      wac=
   WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

    }
      @param actionServlet ActionServlet
      @roseuid 43B5DCDF00FB
   */
  @Override
public void setServlet(ActionServlet actionServlet)
  {
    super.setServlet(actionServlet);
    if (actionServlet != null)
    {
      ServletContext servletContext = actionServlet.getServletContext();
      wac =
          org.springframework.web.context.support.WebApplicationContextUtils.
          getRequiredWebApplicationContext(servletContext);
 
    }

  }



  /**
      try{

      //页面流转逻辑处理
      return prossecor(mapping,form,request,response);

      }catch(Exception e){

      return mapping.findForward("error");

      }
      @param mapping ActionMapping
      @param form ActionForm
      @param request HttpServletRequest
      @param response HttpServletResponse
      @return ActionForward
      @roseuid 43BC9BDC03E4
   */
  @Override
public ActionForward execute(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response)
  {
    try
    {
      //页面流转逻辑处理
      ActionForward af = processor(mapping, form, request, response);
      return af;

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
  }

  /**
      //页面流转逻辑处理
      @param mapping ActionMapping
      @param form ActionForm
      @param request HttpServletRequest
      @param response HttpServletResponse
      @return ActionForward
      @roseuid 43BC9E3D01AE
   */
  protected ActionForward processor(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws
      Exception
  {

   return null;
  }



  public WebApplicationContext getWac()
  {
    return wac;
  }

  /**
   *释放资源
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
    UserData ud=null;//(UserData)session.getAttribute(SessionKey.USER_DATA);
    return ud;
  }
}
