<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>���չ���</title>
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
		<c:if test="${!empty param._ruleTempNo}" >
		var ruleTempNo = '<c:out value="${param._ruleTempNo}"/>';
		document.forms[0]._ruleTempNo.value=ruleTempNo;
		</c:if>
		<c:if test="${!empty param._configId}" >
		var _configId = '<c:out value="${param._configId}"/>';
		document.forms[0]._configId.value=_configId;
		</c:if>
		<c:if test="${!empty param.read}" >
		document.getElementById('operTable').style.display = "none";
		document.getElementById('back').style.display = "none";
		document.forms[0].read.value=<c:out value="${param.read}"/>;
		</c:if>
	}
	
	function addClick()
	{
		document.forms[0].method.value="findRuleItem";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].jgId.value=id;
		document.forms[0].submit();
		
	}
	function addRuleClick(id)
	{
		document.forms[0].method.value="findRuleItem";
		document.forms[0]._ruleTempNo.value=id;
		document.forms[0].submit();
	}
	function returnClick(id)
	{
		document.forms[0].method.value=id;
		document.forms[0]._ruleTempNo.value='';
		document.forms[0].submit();
	}
	function qingkong(){
		document.forms[0]._configId.value="";
	}
	function queryRuleOne(id)
	{
		document.forms[0].method.value="queryRuleOne";
		document.forms[0]._configId.value=id;
		document.forms[0].submit();
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:ruleManager:viewQueryRuleAll">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:ruleManager:viewQueryRuleAll">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				���չ����б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/ruleManager" method="post">
					<input type="hidden" name="_ruleTempNo"  maxlength="20"  style="width:135px;" />
					<input type="hidden" name="method" value="queryRuleList" />
					<input type="hidden" name="read" value="" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								������:
							</td>
							<td class="table2_td" width="40%">
							<input type="text" name="_configId"  maxlength="20"  style="width:135px;" />
							</td>
							<td class="table2_td_title" width="10%">
								ģ��״̬:
							</td>
							<td class="table2_td" width="40%">
								<select id="_status">
									<option>--��ѡ��--</option>
									<option value="S">����</option>
								</select>
							</td>
						</tr>		
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryRuleList');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="qingkong()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
								&nbsp;&nbsp;&nbsp;&nbsp; <input id="back" name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" alt="����" width="65" height="20" border="0" onclick="return returnClick('queryTempAll');">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/ruleManager.do">
						<display:column title="ѡ��" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.configId}"/>">
							
						</display:column>
						<display:column title="������" style="width:10%;text-align:center" property="configId" headerClass="sortable" sortable="true" />
						<display:column title="ģ����" style="width:10%;text-align:center" property="ruleTempNo" headerClass="sortable" sortable="true" />
						<display:column title="�������" style="text-align:center" property="configTitle" headerClass="sortable" sortable="true" />
						<display:column title="������" style="text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.actionStatus eq '0'}">�޶���</c:if>
						<c:if test="${displayTable.actionStatus eq '1'}">����</c:if>
						<c:if test="${displayTable.actionStatus eq '2'}">�ƽ�</c:if>
						<c:if test="${displayTable.actionStatus eq '3'}">����</c:if>
						<c:if test="${displayTable.actionStatus eq '4'}">�ܾ�</c:if>
						<c:if test="${displayTable.actionStatus eq '5'}">�ܿ�</c:if>
						</display:column>
						<display:column title="����״̬" style="width:10%;text-align:center"  headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.status eq 'S'}">����</c:if>
						</display:column>
						<display:column title="����" style="width:10%;text-align:center">
						<shiro:hasPermission name="posp:ruleManager:viewQueryOne">
						<a href="#" onclick="queryRuleOne('<c:out value="${displayTable.configId}"/>')">�鿴</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					<input type="hidden" name="configId" />
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="jgId" />
					
					<!----------����Table�ò���ѡ��-------->
					<table id="operTable" width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
								<shiro:hasPermission name="posp:ruleManager:deleteQueryRuleAll">
								<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteRule');"></a>
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:ruleManager:addRule">
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
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>

</body>
</shiro:hasPermission>
</html:html>
