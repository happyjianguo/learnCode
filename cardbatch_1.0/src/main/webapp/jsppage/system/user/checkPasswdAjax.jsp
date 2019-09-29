<%@ page contentType="text/html;charset=gb2312" import="com.pay.util.ParamUtils"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.io.IOException" %>
<%
	PrintWriter myout = null;
    try {
        myout = response.getWriter();
		try {
	        request.setCharacterEncoding("UTF-8");
	    } catch (UnsupportedEncodingException e) {
	    }
		String result = ParamUtils.getStringAttribute(request,"result");
		if(myout != null)
			myout.println(result);
    } catch (IOException e) {
    }
%>