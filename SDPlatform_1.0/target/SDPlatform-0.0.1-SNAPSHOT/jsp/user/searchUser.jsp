<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>

	<title>用户管理</title>

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
		<c:if test="${!empty param._userId}" >
		var _userId = '<c:out value="${param._userId}"/>';
		document.forms[0]._userId.value=_userId;
		</c:if>
		
		<c:if test="${!empty param._userName}" >
		var _userName = '<c:out value="${param._userName}"/>';
		document.forms[0]._userName.value=_userName;
		</c:if>		
	}
	
	function addClick()
	{
		document.forms[0].method.value="showcreateUserScreen";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].id.value=id;
		document.forms[0].submit();
		
	}
	
	</script>
	
</head>

<body onload="init()">
<html:form action="/User" method="post">
	<!--------------以下Table为路径-------->
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" class="place">
				<img src="<fmt:message key='CommonImagePath' />place_btn.gif" width="12" height="11">
				当前位置：收单系统管理平台 >> 权限管理 >> 用户管理 
			</td>

		</tr>
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>
	<!--------------Table为路径结束-------->


	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				用户列表
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
					<input type="hidden" name="method" value="queryUser" />
					<!----------以下Table为查询form-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								用户ID:
							</td>
							<td class="table2_td" width="40%">
								<input name="_userId" type="text"  maxlength="20"  style="width:135px;">
							</td>
							<td class="table2_td_title" width="10%">
								姓名:
							</td>
							<td class="table2_td" width="40%">
							<input name="_userName" type="text"  maxlength="20"  style="width:135px;">
							</td>							
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="查询" width="65" height="20" border="0" onclick="return searchClick('queryUser');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/User.do">
						<display:column title="选择" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id.userId}"/>">
							
						</display:column>
						<display:column title="用户名" style="width:20%;text-align:center" property="id.userId" headerClass="sortable" sortable="true" />
						<display:column title="用户描述" style="width:20%;text-align:center" property="userName" headerClass="sortable" sortable="true" />
						<display:column title="角色" style="width:20%;text-align:center" property="groupId" headerClass="sortable" sortable="true" />
						
						<display:column title="状态" style="width:20%;text-align:center" property="_status" headerClass="sortable" sortable="true" />
						<display:column title="操作" style="width:10%;text-align:center">
						<c:if test="${currentUserData.groupMap.x010802x02=='yes'}">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.id.userId}"/>','queryUserByKey')">编辑</a>
						</c:if> 
						</display:column>
						
					</display:table>
					<!-- 用Diaplay Tag来显示 -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					
					<!----------以下Table用操作选择-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								是否全选
							</td>
							<td>
								<c:if test="${currentUserData.groupMap.x010802x03=='yes'}">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="删除" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteUser');"></a>
								</c:if>
								<c:if test="${currentUserData.groupMap.x010802x01=='yes'}">
								<a href="#" onclick="addClick()">
									<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="增加" width="65" height="20" border="0"></a>
								</c:if>
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
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>

</body>
</html:html>
