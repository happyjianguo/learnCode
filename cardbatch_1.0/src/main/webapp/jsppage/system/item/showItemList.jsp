
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

	<title>显示权限列表页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=path%>/css/style_a.css" />
</head>
<script src="<%=path%>/js/sorttable.js"></script>
<script language="javascript">
	function addConfirm(){
		window.location.href="<%=path %>/jsppage/item/addItem.jsp";
	}
	function delConfirm(){
		var answer = window.confirm("您确定要删除吗？删除之后将不能恢复!");
		if(answer == true)
		{
			return true;
		}
		return false;
	}
</script>

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
							<font color="blue" class="big">权 限 管 理</font>
						</td>
					</tr>
					<tr>
						<td height="25" align="right">
							<input type="button" class="button" onClick='return addConfirm()' style="cursor:hand" value="新增">
						</td>
					</tr>
				</table>
				<div style="position:absolute; overflow:auto; overflow-x:hidden; width:90%; height:362px; border:1px none #000000; left: 50px;">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" class="sortable">
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
						<td height="25" class=box1>
							修改
						</td>
						<td height="25" class=box1>
							删除
						</td>
					</tr>
					<logic:present name="itemList">
						<logic:iterate id="itemBean" name="itemList">
							<tr align="center">
								<td class=box2 height="28">
									<bean:write name="itemBean" property="itemno"/>&nbsp;
								</td>
								<td class=box2 height="28">
									<bean:write name="itemBean" property="itemname"/>&nbsp;
								</td>
								<td class=box2 height="28">
									<bean:write name="itemBean" property="itemdescrip"/>&nbsp;
								</td>
								<td class=box2 height="28">
									<html:link action="/item?method=preModItem" paramId="itemno" paramName="itemBean" paramProperty="itemno"><img border="0" src="<%=path %>/images/button/mod.gif"/></html:link>
								</td>
								<td class=box2 height="28" onclick="return delConfirm();">
									<html:link action="/item?method=deleteItem" paramId="itemno" paramName="itemBean" paramProperty="itemno"><img border="0" src="<%=path %>/images/button/del.gif"/></html:link>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:notPresent name="itemList">
						<tr align="center"><td  class=box3 colspan="5">暂无权限信息</td></tr>
					</logic:notPresent>
				</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html:html>
