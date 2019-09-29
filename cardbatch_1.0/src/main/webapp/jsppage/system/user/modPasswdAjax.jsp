<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.pay.util.ParamUtils" %>


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