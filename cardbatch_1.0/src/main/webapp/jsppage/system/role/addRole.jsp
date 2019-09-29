
<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>角色增加页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
	<script type="text/javascript" src="<%=path%>/js/checkform.js"></script>
</head>
<script language="javascript">
	window.history.forward(1);
	function commit(){
		var roleno = document.roleForm.roleno.value;
		var rolename = document.roleForm.rolename.value;
		if(trim(roleno) == ""){
			alert("请输入角色编号！");
			document.roleForm.roleno.focus();
			return false;
		}
		if(trim(rolename) == ""){
			alert("请输入角色名称！");
			document.roleForm.rolename.focus();
			return false;
		}
		document.roleForm.submit();
	}
</script>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="roleForm" action="/role?method=addRole" method="post" focus="roleno">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%">
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 系统管理 &gt;角色管理 &gt;增加角色 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td width="80" align="right" class="box1">
									<font color="red">角色编号:</font>
								</td>
								<td align="left" class="box3">
									<html:text property="roleno" maxlength="3"></html:text>
								</td>
							</tr>
							<tr>
								<td width="80" align="right" class="box1">
									<font color="red">角色名称:</font>
								</td>
								<td align="left" class="box2">
									<html:text property="rolename" maxlength="20"></html:text>
								</td>
							</tr>
							<tr>
								<td width="80" align="right" class="box1">
									角色描述:
								</td>
								<td align="left" class="box2">
									<html:textarea rows="4" cols="60" property="roledesc" ></html:textarea>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td height="23" align="center" class="box1">
									<input class="button" type="button" onClick="commit();" value="保存">
									&nbsp;&nbsp;&nbsp;
									<input class="button" type="button"  value="关闭" onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</center>
</html:html>
