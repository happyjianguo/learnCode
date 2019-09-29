<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>缺省路由</title>
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
		document.forms[0].method.value="createTranRoute";
	
		return validateDefaultRouteForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryDefaultRoute";
	}
	function setMbh()
	{
		var sss = document.forms[0].updateOper.value;
var wz=sss.indexOf("_")
document.forms[0].rcvHostId.value=sss.substr(wz+1);

document.forms[0].rcvBankId.value=sss.substr(0,wz);

		
	}
	</script>
</head>
<shiro:lacksPermission name="posp:defaultroute:add">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:defaultroute:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				新增缺省路由
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="defaultRouteForm" />
				<html:errors />
				<html:form action="/defaultRoute">

					<html:hidden property="method" value="createTranRoute" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">


						<tr>
							<td class="table2_td_title">
								目标行:

							</td>
							<td class="table2_td">
							
								<html:select onchange="setMbh()"  property="updateOper">
								<option></option>
								<c:forEach var="model" items="${bankInfoList}">
							     <option value="<c:out value="${model.bankId}"/>_<c:out value="${model.hostId}" />" ><c:out value="${model.bankName}" />
							     </option>
							   </c:forEach>
								</html:select>
								<html:hidden property="rcvBankId" />
							</td>
						</tr><tr>
							<td class="table2_td_title">
								主机号:

							</td>
							<td class="table2_td">
								<html:text property="rcvHostId" size="19" maxlength="40" readonly="true" />
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								程序模板号:

							</td>
							<td class="table2_td">
								<html:text property="moduleId" size="19" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'');"/>
								
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
					<%@include file="../../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>