<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>旧福卡银行终端参数</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript">
	function setSettle()
	{
		document.forms[0].settle.setAttribute("disabled",true);
		document.forms[0].login.setAttribute("disabled",true);
		document.forms[0].method.value="settle";
		document.forms[0].submit();
	}
	function setLogin()
	{
		document.forms[0].settle.setAttribute("disabled",true);
		document.forms[0].login.setAttribute("disabled",true);
		document.forms[0].method.value="login";
		document.forms[0].submit();
	}
	<c:if test="${msg != null}">
	alert("${msg}");
	</c:if>
	</script>
</head>
<shiro:lacksPermission name="posp:edcTerminalOrmSyncParam:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edcTerminalOrmSyncParam:view">
<body>

	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				旧福卡银行终端参数
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:errors />
				<html:form action="/edcTerminalOrm">

					<html:hidden property="method" value="" />
					<input type="hidden" name="syncParam" value="syscParam" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								银行商户编号:
							</td>
							<td class="table2_td">
								<input name="_bankmerchantId" size="25" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								银行 终端号：
							</td>
							<td class="table2_td">
								<input name="_bankterminalId" size="25"  maxlength="8"onkeyup="this.value=this.value.replace(/\W/g,'')"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input name="settle" type="button" value="结算" onclick="setSettle();">
									&nbsp;
								<input name="login" type="button" value="签到" onclick="setLogin();">
							</td>
						</tr>
					</table>
					<!-- 维护视图状态的隐藏域 -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- 维护视图状态的隐藏域 -->
					</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>