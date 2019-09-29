<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>通知路由</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._tranType}" >
		var _tranType = '<c:out value="${param._tranType}"/>';
		document.forms[0]._tranType.value=_tranType;
		</c:if>
		<c:if test="${!empty param._rcvBankId}" >
		var _rcvBankId = '<c:out value="${param._rcvBankId}"/>';
		document.forms[0]._rcvBankId.value=_rcvBankId;
		</c:if>
	}
	
	function addClick()
	{
		document.forms[0].method.value="showSr";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].jgId.value=id;
		document.forms[0].submit();
		
	}
	function saveClick()
	{
		alert("aaa");
		//document.forms[0].method.value="createTranRoute";
		return validateAdvRouteForm(document.forms[0]);
		
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:advroute:view">
    <div class="permission_error">用户[${fns:getUserName()}]没有该权限<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:advroute:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				通知路由列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
			<html:javascript formName="advRouteForm" />
				<html:form action="/advRoute" method="post" >
					<input type="hidden" name="method" value="queryAdvRoute" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								交易类型:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_tranType" maxlength="20"  style="width:135px;" />
							</td>
<td class="table2_td_title" width="10%">
								目标主机号行:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_rcvBankId" maxlength="20"  style="width:135px;" />
							</td>
							
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryAdvRoute');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="清空" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------Table为查询form结束-------->


					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								查询结果
							</td>
						</tr>
					</table>

					<!-- 用Diaplay Tag来显示 -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/advRoute.do">
						<display:column title="选择" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id.tranType}"/>_<c:out value="${displayTable.id.cardType}"/>_<c:out value="${displayTable.id.rcvBankId}"/>_<c:out value="${displayTable.id.rcvHostId}"/>_<c:out value="${displayTable.id.moduleId}"/>_<c:out value="${displayTable.id.updateOper}"/>_<c:out value="${displayTable.id.updateDate}"/>_<c:out value="${displayTable.id.updateTime}"/>">
							
						</display:column>
						<display:column title="交易类型" style="width:20%;text-align:center" property="id.tranType" headerClass="sortable" sortable="true" />
						<display:column title="卡类型" style="width:20%;text-align:center" property="id.cardTypeName" headerClass="sortable" sortable="true" />
						<display:column title="目标行" style="width:20%;text-align:center" property="id.rcvBankName" headerClass="sortable" sortable="true" />
						<display:column title="目标行主机号" style="width:20%;text-align:center" property="id.rcvHostId" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:15%;text-align:center">
 						<shiro:hasPermission name="posp:advroute:edit">
						<a href="#" onclick="editClicks('<c:out value="${displayTable.id.tranType}"/>_<c:out value="${displayTable.id.cardType}"/>_<c:out value="${displayTable.id.rcvBankId}"/>_<c:out value="${displayTable.id.rcvHostId}"/>_<c:out value="${displayTable.id.moduleId}"/>_<c:out value="${displayTable.id.updateOper}"/>_<c:out value="${displayTable.id.updateDate}"/>_<c:out value="${displayTable.id.updateTime}"/>','queryTranRouteByKey')">编辑</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="jgId" />
					
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
								<shiro:hasPermission name="posp:advroute:delete">
								<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteTranRoute');"></a>
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:advroute:add">
								<a href="#" onclick="addClick()">
									<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0"></a>
								</shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<!----------TTable用操作选择结束-------->
					
			</td>
		</tr>
	</table>
	<%@include file="../../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</shiro:hasPermission>
</html:html>