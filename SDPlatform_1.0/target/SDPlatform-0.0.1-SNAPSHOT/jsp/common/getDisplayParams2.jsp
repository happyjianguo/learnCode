
<%@ page import="java.util.*,org.displaytag.tags.*,org.displaytag.util.*" pageEncoding="GBK"%>
<%@ page import="org.displaytag.util.ParamEncoder" %>
<%@ page import="org.displaytag.tags.TableTagParameters" %>

<%
String displayTable = request.getParameter("tableId");

String dpPageName = new ParamEncoder(displayTable).encodeParameterName(TableTagParameters.PARAMETER_PAGE);
String dpPageOrder = new ParamEncoder(displayTable).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
String dpPageSort = new ParamEncoder(displayTable).encodeParameterName(TableTagParameters.PARAMETER_SORT);
if(request.getParameter(dpPageName)!=null)
{
	out.println("<input type=hidden name="+dpPageName +" value="+request.getParameter(dpPageName)+">");
}
if(request.getParameter(dpPageOrder)!=null)
{
	out.println("<input type=hidden name="+dpPageOrder +" value="+request.getParameter(dpPageOrder)+">");
}
if(request.getParameter(dpPageSort)!=null)
{
	out.println("<input type=hidden name="+dpPageSort +" value="+request.getParameter(dpPageSort)+">");
}

%>


