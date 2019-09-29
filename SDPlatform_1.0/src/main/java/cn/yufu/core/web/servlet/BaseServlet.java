package cn.yufu.core.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yufu.core.common.log.Log;

public class BaseServlet
    extends HttpServlet
{

  /**
      @roseuid 43BCA1780135
   */
  public BaseServlet()
  {

  }

  /**
   *   try{

      //ҳ����ת�߼�����
      processor(request,response);

      }catch(Exception e){

      response.sendRedirect("error.jsp");//����ҳ��

      }

   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @throws IOException
   */
  @Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      IOException
  {
    try
    {

      //ҳ����ת�߼�����
      processor(request, response);

    }
    catch (Exception e)
    {
      Log.log(e, "", null);
      e.printStackTrace();
      response.sendRedirect(request.getRequestURI()); //����

    }

  }

  /**
      try{

      //ҳ����ת�߼�����
      processor(request,response);

      }catch(Exception e){

      response.sendRedirect("error.jsp");//����ҳ��

      }
      @param request HttpServletRequest
      @param response HttpServletResponse
      @return void
      @roseuid 43BCA22C0315
   */
  @Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      IOException
  {
    try
    {
      //ҳ����ת�߼�����
      processor(request, response);
    }
    catch (Exception e)
    {
      Log.log(e, "", null);
      e.printStackTrace();
      response.sendRedirect(request.getRequestURI()); //����
    }
  }

  /**
      ҳ����ת����
      @param request HttpServletRequest
      @param response HttpServletResponse
      @return void
      @roseuid 43BCA3860395
   */
  protected void processor(HttpServletRequest request,
                           HttpServletResponse response) throws Exception
  {

  }
}

