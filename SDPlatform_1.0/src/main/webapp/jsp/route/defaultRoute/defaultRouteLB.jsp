<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>ȱʡ·��</title>
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
		<c:if test="${!empty param._rcvBankId}" >
		var _rcvBankId = '<c:out value="${param._rcvBankId}"/>';
		document.forms[0]._rcvBankId.value=_rcvBankId;
		</c:if>
		<c:if test="${!empty param._rcvHostId}" >
		var _rcvHostId = '<c:out value="${param._rcvHostId}"/>';
		document.forms[0]._rcvHostId.value=_rcvHostId;
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
	
	</script>
	
</head>
<shiro:lacksPermission name="posp:defaultroute:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:defaultroute:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				ȱʡ·���б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/defaultRoute" method="post">
					<input type="hidden" name="method" value="queryDefaultRoute" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								Ŀ���к�:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_rcvBankId"  maxlength="20"  style="width:135px;" />
							</td>
<td class="table2_td_title" width="14%">
								Ŀ����������:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_rcvHostId"  maxlength="20"  style="width:135px;" />
							</td>
							
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryDefaultRoute');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
							</td>
						</tr>
					</table>
					<!----------TableΪ��ѯform����-------->


					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table3_border">
						<tr>
							<td class="table3_title">
								��ѯ���
							</td>
						</tr>
					</table>

					<!-- ��Diaplay Tag����ʾ -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/defaultRoute.do">
						<display:column title="ѡ��" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.id.rcvBankId}"/>_<c:out value="${displayTable.id.rcvHostId}"/>_<c:out value="${displayTable.id.moduleId}"/>_<c:out value="${displayTable.id.updateOper}"/>_<c:out value="${displayTable.id.updateDate}"/>_<c:out value="${displayTable.id.updateTime}"/>">
							
						</display:column>
					
						<display:column title="Ŀ���к�" style="width:20%;text-align:center" property="id.rcvBankId" headerClass="sortable" sortable="true" />
						<display:column title="Ŀ����" style="width:20%;text-align:center" property="id.rcvBankName" headerClass="sortable" sortable="true" />
						<display:column title="Ŀ����������" style="width:20%;text-align:center" property="id.rcvHostId" headerClass="sortable" sortable="true" />
						<display:column title="����ģ���" style="width:20%;text-align:center" property="id.moduleId" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:15%;text-align:center">
 						<shiro:hasPermission name="posp:defaultroute:edit"> 
						<a href="#" onclick="editClicks('<c:out value="${displayTable.id.rcvBankId}"/>_<c:out value="${displayTable.id.rcvHostId}"/>_<c:out value="${displayTable.id.moduleId}"/>_<c:out value="${displayTable.id.updateOper}"/>_<c:out value="${displayTable.id.updateDate}"/>_<c:out value="${displayTable.id.updateTime}"/>','queryTranRouteByKey')">�༭</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="jgId" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
								<shiro:hasPermission name="posp:defaultroute:delete">
								<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteTranRoute');"></a>
								</shiro:hasPermission> 
								<shiro:hasPermission name="posp:defaultroute:add">
								<a href="#" onclick="addClick()">
									<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0"></a>
								</shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<!----------TTable�ò���ѡ�����-------->
					
			</td>
		</tr>
	</table>
	<%@include file="../../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>
</body>
</shiro:hasPermission>
</html:html>