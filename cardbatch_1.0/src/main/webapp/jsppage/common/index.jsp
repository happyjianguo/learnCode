<%@ page language="java" pageEncoding="GB2312" import="com.pay.util.ParamUtils"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
    
    session.setAttribute("usercode",ParamUtils.getStringAttribute(request, "usercode"));
    session.setAttribute("username",ParamUtils.getStringAttribute(request, "username"));
    session.setAttribute("teamno",ParamUtils.getStringAttribute(request, "teamno"));
    session.setAttribute("teamname",ParamUtils.getStringAttribute(request, "teamname"));
    session.setAttribute("roleno",ParamUtils.getStringAttribute(request, "roleno"));
    session.setAttribute("rolename",ParamUtils.getStringAttribute(request, "rolename"));
    session.setAttribute("deptno",ParamUtils.getStringAttribute(request, "deptno"));
	//System.out.println("index=deptno="+ParamUtils.getStringAttribute(request, "deptno"));
    session.setAttribute("deptname",ParamUtils.getStringAttribute(request, "deptname"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>管理控制台---主页面</title>
	<base href="<%=basePath%>">
	<script type="text/javascript">
		var usercode = "<%=ParamUtils.getStringAttribute(request, "usercode")%>";
		var username = "<%=ParamUtils.getStringAttribute(request, "username")%>";
		var teamno = "<%=ParamUtils.getStringAttribute(request, "teamno")%>";
		var teamname = "<%=ParamUtils.getStringAttribute(request, "teamname")%>";
		var roleno = "<%=ParamUtils.getStringAttribute(request, "roleno")%>";
		var rolename = "<%=ParamUtils.getStringAttribute(request, "rolename")%>";
		var deptno = "<%=ParamUtils.getStringAttribute(request, "deptno")%>";
		var deptname = "<%=ParamUtils.getStringAttribute(request, "deptname")%>";
		var currentDate = "<%=ParamUtils.getStringAttribute(request, "currentDate")%>";
		var currentDate8 = "<%=ParamUtils.getStringAttribute(request, "currentDate8")%>";
		top.usercode = usercode;
		top.username = username;
		top.deptname = deptname;
		top.rolename = rolename;
		top.teamno = teamno;
		top.teamname = teamname;
		top.currentDate = currentDate;
		top.currentDate8 = currentDate8;
		top.commpath = "&usercode="+usercode+"&username="+username+"&teamno="+teamno+"&teamname="+teamname+"&roleno="+roleno+"&rolename="+rolename+"&deptno="+deptno+"&deptname="+deptname;
		var nowop;
		function checkPasswddays(){
			var nextdays = <%=ParamUtils.getLongAttribute(request, "nextdays",-1)%>;
			if(nextdays != -1 && nextdays < 4){
				alert("您的密码还有" + ( nextdays ) + "天过期,请尽快修改您的密码!");
			}
		}
	</script>
</head>
<%System.out.println("==============="+ParamUtils.getStringAttribute(request, "usercode") );%>
<frameset onload="checkPasswddays();" rows="60,*" frameborder="1" framespacing="0">
  <frame src="jsppage/common/head.jsp" name="topFrame" scrolling="NO" noresize>
  <frameset cols="180,*" frameborder="1" framespacing="0">
		<frame src="jsppage/common/left.jsp" name="leftFrame" scrolling="NO" noresize>
		<frame src="jsppage/common/right.jsp" name="mainFrame" frameborder="0" scrolling="no"	noresize="true">
	</frameset>
</frameset>
<noframes></noframes>
</html:html>