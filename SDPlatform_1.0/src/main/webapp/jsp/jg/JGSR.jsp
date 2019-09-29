<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>机构管理</title>
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
		document.forms[0].method.value="createJg";
		return validateJgForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryJg";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:Jg:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:Jg:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				新增机构
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="jgForm" />
				<html:errors />
				<html:form action="/Jg">

					<html:hidden property="method" value="createJg" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">


						<tr>
							<td class="table2_td_title">
								机构代码:

							</td>
							<td class="table2_td">
								<html:text property="jgId" size="19" maxlength="10" />
								<font color="red">*</font>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								机构名称:

							</td>
							<td class="table2_td">
								<html:text property="jgName" size="19" maxlength="40" />
								<font color="red">*</font>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								机构说明:

							</td>
							<td class="table2_td">
								<html:text property="jgSm" size="19" maxlength="40" />
								<font color="red">*</font>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								联系地址:

							</td>
							<td class="table2_td">
								<html:text property="jgDz" size="19" maxlength="40" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								联系电话:

							</td>
							<td class="table2_td">
								<html:text property="jgTel" size="19" maxlength="15" />
								<font color="red">*</font>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								传真号码:

							</td>
							<td class="table2_td">
								<html:text property="jgCz" size="19" maxlength="15" />
								<font color="red">*</font>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								联系人:

							</td>
							<td class="table2_td">
								<html:text property="jgLxr" size="19" maxlength="10" />
								<font color="red">*</font>
							</td>
						</tr><tr>
							<td class="table2_td_title">
								邮编:

							</td>
							<td class="table2_td">
								<html:text property="jgYb" size="19" maxlength="6" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font>
							</td>
						</tr>


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
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>



</body>
</shiro:hasPermission>
</html:html>




