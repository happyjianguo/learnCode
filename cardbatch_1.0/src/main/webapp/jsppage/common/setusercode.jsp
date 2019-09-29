<%@ page language="java" pageEncoding="GB2312"
	import="com.pay.util.ParamUtils"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
    String path = request.getContextPath();
	String usercode = ParamUtils.getParameter(request,"usercode");
	if(usercode.intern() == "".intern() && session.getAttribute("usercode") != null)
	    usercode = (String) session.getAttribute("usercode");
	
	String username = ParamUtils.getParameter(request,"username");
	if(username.intern() == "".intern() && session.getAttribute("username") != null)
	    username = (String) session.getAttribute("username");
	
	String teamno = ParamUtils.getParameter(request,"teamno");
	if(teamno.intern() == "".intern() && session.getAttribute("teamno") != null)
	    teamno = (String) session.getAttribute("teamno");
	
	String teamname = ParamUtils.getParameter(request,"teamname");
	if(teamname.intern() == "".intern() && session.getAttribute("teamname") != null)
	    teamname = (String) session.getAttribute("teamname");
	    
	String roleno = ParamUtils.getParameter(request,"roleno");
	if(roleno.intern() == "".intern() && session.getAttribute("roleno") != null)
	    roleno = (String) session.getAttribute("roleno");
	    
	String rolename = ParamUtils.getParameter(request,"rolename");
	if(rolename.intern() == "".intern() && session.getAttribute("rolename") != null)
	    rolename = (String) session.getAttribute("rolename");
	    
	String deptno = ParamUtils.getParameter(request,"deptno");
	if(deptno.intern() == "".intern() && session.getAttribute("dept_no_node") != null)
	    deptno = (String) session.getAttribute("dept_no_node");
	    
	String deptname = ParamUtils.getParameter(request,"deptname");
	if(deptname.intern() == "".intern() && session.getAttribute("deptname") != null)
	    deptname = (String) session.getAttribute("deptname");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>特色系统管理平台---菜单</title>

	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />

	<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<%
	    session.setAttribute("usercode",usercode);
	    session.setAttribute("username",username);
	    session.setAttribute("teamno",teamno);
	    session.setAttribute("teamname",teamname);
	    session.setAttribute("roleno",roleno);
	    session.setAttribute("rolename",rolename);
	  //  session.setAttribute("deptno",deptno);
		//System.out.println("setuser=deptno="+deptno);
	    session.setAttribute("deptname",deptname);
	%>
</head>
<body>
</body>
</html:html>
