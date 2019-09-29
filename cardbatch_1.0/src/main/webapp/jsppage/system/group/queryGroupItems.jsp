
<%@ page language="java" pageEncoding="gbk"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>特色平台管理端-查看管理员组权限信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
</head>

<body bottommargin="0" leftmargin="0" topmargin="0" rightmargin="0">
	<table border="0" cellpadding="0" cellspacing="0" width="862" height="474" background="<%=path%>/images/background/welcome2.gif">
		<tr>
			<td align="center" valign="top">
				<table border="0" cellpadding="0" cellspacing="0" width="90%">
				    <tr>
						<td height="20"></td>
					</tr>
					<tr>
						<td height="23" align="center">
							<font color="blue" class="big">管理员组权限信息</font>
						</td>
					</tr>
					<tr>
						<td height="20" align="right">
							<input class="button" type="button" style="cursor: hand" value="关闭" onClick="history.go(-1)">
						</td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
				</table>
				<div style="position:absolute; overflow:auto; overflow-x:hidden; width:90%; height:362px; border:1px none #000000; left: 50px;">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr align="center" style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td height="25" class=box1>
							权限编号
						</td>
						<td height="25" class=box1>
							权限名称
						</td>
						<td height="25" class=box1>
							权限描述
						</td>
					</tr>
					<logic:present name="groupItemList">
						<logic:iterate id="itemBean" name="groupItemList">
							<tr align="center">
								<td class=box2 height="28">
									<bean:write name="itemBean" property="itemno"/>
								</td>
								<td class=box2 height="28">
									<bean:write name="itemBean" property="itemname"/>
								</td>
								<td class=box2 height="28">
									<bean:write name="itemBean" property="itemdescrip"/>&nbsp;
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:notPresent name="groupItemList">
						<tr align="center"><td  class=box3 colspan="3">该管理员组暂无权限信息</td></tr>
					</logic:notPresent>
				</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html:html>
