<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.pay.util.ParamUtils" %>
<%
	PrintWriter myout = null;
    try {
        myout = response.getWriter();
        if(myout != null){
	    	try {
	            request.setCharacterEncoding("UTF-8");
	        } catch (UnsupportedEncodingException e) {
	        }
	    	String message = ParamUtils.getStringAttribute(request,"ajaxresult","exchange");
	    	myout.println(message);
        }
    } catch (IOException e) {
    }
%>