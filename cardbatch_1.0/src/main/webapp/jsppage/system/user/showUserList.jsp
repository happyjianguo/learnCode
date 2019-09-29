

<%@ page language="java" pageEncoding="gbk"%>
<%@include file="/jsppage/common/checkSession.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/ngp-pageTag.tld" prefix="util"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>显示管理员列表页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/new_look.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script src="<%=path%>/js/eposcc.js"></script>
<script language="javascript">
	function query(){
		var userForm = document.all("userForm");
		userForm.action = "<%=path%>/user.do?method=queryAgentList";
		userForm.submit();
	}
	
	function addConfirm(){
		var userForm = document.all("userForm");
		userForm.action ="<%=path %>/user.do?method=preAddUser";
		userForm.submit();
	}
	
	function delConfirm(){
		var answer = window.confirm("您确定要删除吗？删除之后将不能恢复!");
		if(answer == true)
		{
			return true;
		}
		return false;
	}
	
	function resetUser(){
		var answer = window.confirm("您确定要重置吗？\n重置后该管理员将恢复初始状态!");
		if(answer == true)
		{
			return true;
		}
		return false;
	}
</script>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0" >
<html:form action="/user?method=getUserList" method="post" styleId="userForm">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" >
		<tr>
			<td align="center" valign="top" height="87%">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 系统管理 &gt; 管理员管理 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
							<table  cellpadding="0" cellspacing="0" width="100%" class="serch" style="padding:5px;"  align="left"  >
								<tr>
									<td align="right" >管理员编号：</td>
									<td align="left" ><html:text property="usercodeQ" maxlength="20" size="10"/></td>
									<td align="right" >用户组名称：</td>
									<td align="left">
										<logic:present name="deptList">
											<html:select property="deptnoQ">
												<html:option value="">－请选择－</html:option>
												<html:optionsCollection name="deptList" label="deptname" value="deptno" />
											</html:select>
										</logic:present>
									</td>
									<td align="right" >角色名称：</td>
									<td align="left">
										<logic:present name="roleList">
											<html:select property="rolenoQ">
												<html:option value="">－请选择－</html:option>
												<html:optionsCollection name="roleList" label="rolename" value="roleno" />
											</html:select>
										</logic:present>
									</td>
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
				<div class="dlist"  style="top: 132px ;height:70%;" >
				<table  cellpadding="0" cellspacing="0" width="100%" border="0" >
					<tr  id="tl" height="35" align="center" style="top: expression(this.offsetParent.scrollTop);position: relative;border-style: none" >
						<td height="25">
							管理员编号 
						</td>
						<td height="24">
							管理员名称
						</td>
						<td >
							所属用户组
						</td>
						<td>
							所属角色
						</td>
						<td>
							管理员状态
						</td>
						<td>
							密码重置
						</td>
						<td>
							查看
						</td>
						<logic:match name="menu_level" value="2">
						<td>
							修改
						</td>
						</logic:match>
						<logic:match name="menu_level" value="3">
						<td>
							删除
						</td>
						</logic:match>
					</tr>
					<tbody  class="list" >
					<logic:present name="userList">
						<logic:iterate id="userBean" name="userList">
							<tr align="center" onMouseOver="changeClass(this,1)" onMouseOut="changeClass(this,2)">
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
								<td class=box2 height="28" onClick="return resetUser();">
									<html:link action="/user?method=resetUser" paramId="usercode" paramName="userBean" paramProperty="usercode">重置</html:link>
								</td>
								<td class=box2 height="28">
									<html:link action="/user?method=queryUser" paramId="usercode" paramName="userBean" paramProperty="usercode"><img border="0" src="<%=path %>/image1/border/query.png" /></html:link>
								</td>
								<logic:match name="menu_level" value="2">
								<td class=box2 height="28">
									<html:link action="/user?method=preModUser" paramId="usercode" paramName="userBean" paramProperty="usercode"><img border="0" src="<%=path %>/image1/border/mod.png" /></html:link>
								</td>
								</logic:match>
								<logic:match name="menu_level" value="3">
								<td class=box2 height="28" onClick="return delConfirm();">
									<html:link action="/user?method=deleteUser" paramId="usercode" paramName="userBean" paramProperty="usercode"><img border="0" src="<%=path %>/image1/border/del.png" /></html:link>
								</td>
								</logic:match>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:notPresent name="userList">
						<%int i=7; %>
						<logic:match name="menu_level" value="2">
						<%i++; %>
						</logic:match>
						<logic:match name="menu_level" value="3">
						<%i++; %>
						</logic:match>
						<tr align="center"><td  class=box3 colspan="<%=i %>">暂无管理员信息</td></tr>
					</logic:notPresent>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td height="30">
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
					<tr align="right">
						<td height="25">
							<util:page uri="/user.do?method=queryAgentList" />
						</td>
				    </tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
