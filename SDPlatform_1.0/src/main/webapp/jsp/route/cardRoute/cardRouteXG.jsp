<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>卡段路由</title>
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
		document.forms[0].method.value="saveTranRoute";
		
		return validateCardRouteForm(document.forms[0]);
	
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryCardRoute";
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
<shiro:lacksPermission name="posp:cardroute:edit">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:cardroute:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				修改路由信息
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="cardRouteForm" />
				<html:errors />
				<html:form action="/cardRoute">

					<html:hidden property="method" value="saveTranRoute" />
					

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
<tr>
							<td class="table2_td_title">
								交易位图:

							</td>
							<td class="table2_td">
								<html:text property="tranBit" size="19" maxlength="64" />
								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								卡类型:

							</td>
							<td class="table2_td">
								<html:select styleId="cardType" property="cardType">
<c:forEach var="model" items="${cardTypeList}">
<option value="<c:out value="${model.id.cardType}"/>"><c:out value="${model.id.typeName}" /></option>
</c:forEach>
</html:select>
							</td>
						</tr>
<tr>
							<td class="table2_td_title">
								起始卡段:

							</td>
							<td class="table2_td">
								<html:text property="firstCardNo" size="19" maxlength="19" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								结束卡段:

							</td>
							<td class="table2_td">
								<html:text property="lastCardNo" size="19" maxlength="19" />
								
							</td>
						</tr><tr>
							<td class="table2_td_title">
								目标行:

							</td>
							<td class="table2_td">
										<html:select onchange="setMbh()"  property="updateOper">
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
					<html:hidden property="iid" />
					<!-- 维护视图状态的隐藏域 -->
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>