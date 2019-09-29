package cn.yufu.core.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.yufu.core.common.util.Debug;

/**
 页面字符集设置
 */
public class EncodingFilter
    implements Filter
{
  private String encoding;
  private FilterConfig filterConfig;

  /**
      @roseuid 43B4BFF70357
   */
  public EncodingFilter()
  {

  }


     /**
      设置编码类型
        if (encoding != null)
        {
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);

      * @param request ServletRequest
      * @param response ServletResponse
      * @param chain FilterChain
      * @throws UnsupportedEncodingException
      * @throws ServletException
      * @throws IOException
      */
     public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws UnsupportedEncodingException,
      ServletException, IOException
  {
    //设置编码类型
    if (encoding != null)
    {
      request.setCharacterEncoding(encoding);
    }
    chain.doFilter(request, response);
  }

  /**
      @roseuid 43C2248C0293
   */
  public void destroy()
  {

  }

  /**
      初始化设置编码类型
   filterConfig = filterConfig;
          //读取encoding编码
          encoding = filterConfig.getInitParameter("encoding");
      @param filterConfig FilterConfig
      @return void
      @roseuid 43C2258A020C
   */
  public void init(FilterConfig filterConfig)
  {
    filterConfig = filterConfig;
    Debug.println("in EncodingFilter");
    //读取encoding编码
    encoding = filterConfig.getInitParameter("encoding");
  }

  /**
      @return FilterConfig
      @roseuid 43C225ED01FB
   */
  public FilterConfig getFilterConfig()
  {
    return null;
  }

  /**
      @param filterConfig FilterConfig
      @return void
      @roseuid 43C225ED022D
   */
  public void setFilterConfig(FilterConfig filterConfig)
  {

  }
}

