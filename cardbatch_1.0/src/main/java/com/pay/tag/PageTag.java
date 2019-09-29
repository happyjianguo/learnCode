package com.pay.tag;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.pay.util.PageBean;

public class PageTag extends TagSupport {

    private static final long serialVersionUID = -4590229319494353211L;

    private PageContext pageContext = null;

    private String uri = null;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        PageBean pageBean = new PageBean();
        if (request.getAttribute("pageBean") != null)
            pageBean = (PageBean) request.getAttribute("pageBean");
        //System.out.println(request.getParameter("txtype"));
        if (null != request.getParameter("txtype"))
        	this.uri = request.getContextPath() + uri + "&txtype=" + request.getParameter("txtype");
        else {
        	this.uri = request.getContextPath() + uri;
        }
        JspWriter out = pageContext.getOut();
        try {
            //out.println("<font size=3>");
            out.println("<p>共"+pageBean.getTotalRecords()+"条&nbsp;当前第 " + pageBean.getCurrentPage() + "/" + pageBean.getTotalPage()
                    + " 页");
            out.println("<a href=\"" + uri + "&currentPage=1\")\">-首页-</a> ");

            if (pageBean.hasUppage()) {
                out.println("<a href=\"" + uri + "&currentPage=" + (pageBean.getCurrentPage() - 1)
                        + "\">-上一页-</a>");
            } else {
                out.println("-上一页-");
            }
            if (pageBean.hasNextPage()) {
                out.println("<a href=\"" + uri + "&currentPage=" + (pageBean.getCurrentPage() + 1)
                        + "\">-下一页-</a>");
            } else {
                out.println("-下一页-");
            }
            out.println("<a href=\"" + uri + "&currentPage=" + pageBean.getTotalPage()
                    + "\">-尾页-</a>");
            out.println("<input type=\"text\" name=\"txtPage\" maxlength=\"6\" size=\"5\" value=\"\">");
            out.println("<input type=\"button\" class=\"nbutton\" onClick=\"return pagegotosubmit('"+uri+"')\" style=\"cursor:hand\" size=\"4\" value=\"跳  转\">");           
            //out.println("</font>");
        } catch (IOException e) {
        }
        return super.doStartTag(); 
    }
}
