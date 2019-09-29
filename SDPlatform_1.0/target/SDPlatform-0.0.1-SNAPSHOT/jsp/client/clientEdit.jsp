<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>客户端管理</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveItem";
		return validateClientForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:clientInfo:edit">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:clientInfo:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				修改客户端
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="clientForm" />
				<html:errors />
				<html:form action="/clientInfo">

					<html:hidden property="method" value="saveItem" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">


						<tr>
							<td class="table2_td_title">
								MAC地址:

							</td>
							<td class="table2_td">
								<html:text property="macAddr" size="19" maxlength="20" disabled="true"/>
								<font color="red">*</font>
								<html:hidden property="macAddr" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								IP地址:

							</td>
							<td class="table2_td">
								<html:text property="ipAddr" size="19" maxlength="20" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								主机名:

							</td>
							<td class="table2_td">
								<html:text property="hostName" size="19" maxlength="20" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								用户标识:

							</td>
							<td class="table2_td">
								<html:text property="userId" size="19" maxlength="20" disabled="true"/>
								<font color="red">*</font>
								<html:hidden property="userId" />
							</td>
						</tr>
						<html:hidden property="createUser"/>
						<html:hidden property="createTimestamp"/>
						<html:hidden property="clientId"/>

						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">


							</td>
						</tr>
					</table>

					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name=macAddr value="<c:out value="${param.macAddr}"/>" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>

</body>
</shiro:hasPermission>
</html:html>



