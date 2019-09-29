<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
</head>
<script language="javascript">
	function query(){
		var userForm = document.all("userForm");
		userForm.action = "<%=path%>/user.do?method=queryAgentList";
		userForm.submit();
	}
	
	function addConfirm(){
		window.location.href="<%=path %>/jsppage/terminal/addterminal.jsp";
	}
	
	function delConfirm(){
		var answer = window.confirm("您确定要删除吗？删除之后将不能恢复!");
		if(answer == true) {
			return true;
		}
		return false;
	}
	
	function resetUser(){
		var answer = window.confirm("您确定要重置吗？\n重置后该管理员将恢复初始状态!");
		if(answer == true) {
			return true;
		}
		return false;
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
<html:form action="/user?method=getUserList" method="post" styleId="merchantForm">
	<bean:define id="menu_level" name="menu_level" />
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
		<tr>
			<td align="center" valign="top" height="50">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 商户终端管理 &gt; 商户管理 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
				</table>
				<table  cellpadding="0" cellspacing="0" width="100%" class="serch" style="padding:5px;"  align="left"  >
					<tr>
						<td align="right" >商户ID：</td>
						<td align="left" ><html:text property="usercodeQ" maxlength="20" size="10"/></td>
						<td height="25" align="left">
							<input type="button" class="button" onClick='return query()'  style="background-image: url(<%=path%>/image1/border/Check_button.gif)">									
						</td>
					</tr>
					<tr>
						<logic:match name="menu_level" value="1">
						<td align="center">
							<input type="button" class="button" onClick='return addConfirm()'     style="background-image: url(<%=path%>/image1/border/New_button.gif)">
						</td>
						</logic:match>
					</tr>
				</table>
				<div class="dlist"  style="top: 130px ;height: 76%" >
				<table cellpadding="0" cellspacing="0" width="100%" >
					<tr  id="tl" height="35" align="center" style="top: expression(this.offsetParent.scrollTop);position: relative;border-style: none" >
						<td height="20">
							商户所属机构的机构ID
						</td>
						<td>
							商户号
						</td>
						<td>
							商户名称
						</td>
						<td>
							交易名称
						</td>
						<td>
							排序编号
						</td>
						
						<logic:match name="menu_level" value="2">
						
						</logic:match>
						<td>
							查看
						</td>
						<logic:match name="menu_level" value="2">
						<td>
							修改
						</td>
						</logic:match>
						<logic:match name="menu_level" value="3">
						
						</logic:match>
					</tr>
					<tbody  class="list" >
					<logic:present name="userList">
						<logic:iterate id="userBean" name="userList">
							<tr align="center">
								<td class=box2 height="28">
									<bean:write name="userBean" property="usercode"/>
								</td>
								<td class=box2 height="28">
									<bean:write name="userBean" property="username"/>
								</td>
								<td class=box2 height="28">
									<bean:write name="userBean" property="deptname"/>
									(<bean:write name="userBean" property="deptno"/>)
								</td>
								<td class=box2 height="28">
									<bean:write name="userBean" property="rolename"/>
									(<bean:write name="userBean" property="roleno"/>)
								</td>
								<td class=box2 height="28">
									<logic:equal name="userBean" property="isactive"
										value="0">无效&nbsp;</logic:equal>
									<logic:equal name="userBean" property="isactive"
										value="1">有效&nbsp;</logic:equal>
								</td>
								<td class=box2 height="28" onclick="return resetUser();">
									<html:link action="/user?method=resetUser" paramId="usercode" paramName="userBean" paramProperty="usercode">重置</html:link>
								</td>
								<logic:match name="menu_level" value="2">
								<td class=box2 height="28">
									<html:link action="/user?method=preModPassword" paramId="usercode" paramName="userBean" paramProperty="usercode">修改</html:link>
								</td>
								</logic:match>
								<td class=box2 height="28">
									<html:link action="/user?method=queryUser" paramId="usercode" paramName="userBean" paramProperty="usercode"><img border="0" src="<%=path %>/image1/border/query.png" /></html:link>
								</td>
								<logic:match name="menu_level" value="2">
								<td class=box2 height="28">
									<html:link action="/user?method=preModUser" paramId="usercode" paramName="userBean" paramProperty="usercode"><img border="0" src="<%=path %>/image1/border/mod.png" /></html:link>
								</td>
								</logic:match>
								<logic:match name="menu_level" value="3">
								<td class=box2 height="28" onclick="return delConfirm();">
									<html:link action="/user?method=deleteUser" paramId="usercode" paramName="userBean" paramProperty="usercode"><img border="0" src="<%=path %>/image1/border/del.png" /></html:link>
								</td>
								</logic:match>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:notPresent name="userList">
						<tr align="center"><td  class=box3 colspan="7">暂无管理员信息</td></tr>
					</logic:notPresent>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td height="30">
				<table border="0" cellpadding="0" cellspacing="0" width="98%" >
					<tr align="right">
						<td height="25">
							<util:page uri="/user.do?method=getUserListQuery" />
						</td>
				    </tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
