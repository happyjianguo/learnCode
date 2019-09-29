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

	<title>管理员查看页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/cssem.css" />
</head>
<center>
	<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
		<html:form styleId="userForm" action="/user?method=modUser" method="post">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" height="99%" >
				<tr>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
				    <tr>
						<td width="28" height="10"></td>
					</tr>
					<tr>
						<td  align="left"   width="28" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/left1.gif) ">
						
						</td>
						<td   height="28" style=" background:url(<%=path%>/image1/Navigation_bar/middle.gif) repeat-x;"  > &nbsp;&nbsp;当前位置： 系统管理 &gt;管理员管理 &gt;管理员详细信息 </td>
						<td   width="7" height="28" style=" background:url(<%=path%>/image1/Navigation_bar/right1.gif) "> </td>
					</tr>
					 <tr>
						<td width="28" height="5" colspan="3"></td>
					</tr>
					</table>
						<table border="0" cellpadding="0" cellspacing="0" width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">管理员编号：</font>
								</td>
								<td align="left" class="box3">
									<bean:write name="userForm" property="usercode"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">管理员名称：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="username"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">所属用户组：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="deptname"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">所属角色：</font>
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="rolename"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">在线状态：</font>
								</td>
								<td align="left" class="box2">
									<logic:equal name="userForm" property="loginflag" value="0">离开</logic:equal>
									<logic:equal name="userForm" property="loginflag" value="1">在线</logic:equal>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									通讯地址：
								</td>
								<td align="left" class="box2">
									<html:textarea rows="2" cols="60" property="address"  readonly="true"></html:textarea>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									邮政编码：
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="postalcode"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									EMAIL地址：
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="mail"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									传真：
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="fax"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									联系电话：
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="phone"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									上次登录时间：
								</td>
								<td align="left" class="box2">
									<bean:write name="userForm" property="lastintime"/>&nbsp;
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									<font color="#FF0000">密码有效期：</font>
								</td>
								<td align="left" class="box2">
									<logic:equal name="userForm" property="validdays" value="-1">
										永不过期
									</logic:equal>
									<logic:notEqual name="userForm" property="validdays" value="-1">
										<bean:write name="userForm" property="validdays"/>天
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									密码失效日期：
								</td>
								<td align="left" class="box2">
									<logic:equal name="userForm" property="passwddays" value="-1">
										永不过期
									</logic:equal>
									<logic:notEqual name="userForm" property="passwddays" value="-1">
										<bean:write name="userForm" property="passwddays"/>&nbsp;
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td width="100" align="right" class="box1">
									管理员状态：
								</td>
								<td align="left" class="box2">
									<logic:equal name="userForm" property="isactive"
										value="0">无效&nbsp;</logic:equal>
									<logic:equal name="userForm" property="isactive"
										value="1">有效&nbsp;</logic:equal>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0"  width="70%" align="center" style="background-color:#D2E8F3">
							<tr>
								<td height="23" align="center" class="box1">
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
