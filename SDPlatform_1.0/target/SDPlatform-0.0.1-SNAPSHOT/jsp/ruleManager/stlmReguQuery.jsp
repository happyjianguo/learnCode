<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>�������</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" language="JavaScript"  src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script type="text/javascript"  language="JavaScript" src="<fmt:message key='JavaScriptPath' />date.js"></script>
	<script type="text/javascript" language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script type="text/javascript" language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>

	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param._mchtNo}" >
		var _mchtNo = '<c:out value="${param._mchtNo}"/>';
		document.forms[0]._mchtNo.value=_mchtNo;
		</c:if>	
		<c:if test="${!empty param._termNo}" >
		var _termNo = '<c:out value="${param._termNo}"/>';
		document.forms[0]._termNo.value=_termNo;
		</c:if>	
	}
	
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].ruleNo.value=id;
		document.forms[0].submit();
		
	}
	
	function resetClick1(){
		document.forms[0]._mchtNo.value="";
		document.forms[0]._termNo.value="";
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:stlmRegu:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:stlmRegu:view">
<body onload="init()">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				��������б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/stlmRegu" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%"> 
								�̻����: 
							</td>
							<td class="table2_td" width="40%">
								<input name="_mchtNo" size="19" maxlength="15"/>
							</td>
							<td class="table2_td_title" width="10%"> 
								�ն˺�: 
							</td>
							<td class="table2_td" width="40%">
								<input name="_termNo" size="19" maxlength="15"/>
							</td>						
						</tr>				
						<tr>
							<td colspan="4" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryAll');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="resetClick1()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/stlmRegu.do">
						<display:column title="ѡ��" style="width:5%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.ruleNo}"/>">
						
						</display:column>
						<display:column title="�̻����" style="width:15%;text-align:center" property="mchtNo" headerClass="sortable" sortable="true" />
						<display:column title="�̻�����" style="width:12%;text-align:center" property="mccName" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:12%;text-align:center" headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.channelNo eq '00'}">��������</c:if>
						<c:if test="${displayTable.channelNo eq '01'}">POS����</c:if>
						<c:if test="${displayTable.channelNo eq '02'}">ATM����</c:if>
						<c:if test="${displayTable.channelNo eq '03'}">�����յ�</c:if>
						</display:column>
						<display:column title="���α�־" style="width:8%;text-align:center"  headerClass="sortable" sortable="true" >
						<c:if test="${displayTable.g1CrDscFlg eq 0}">ÿ�ʽ��׽��</c:if>
						<c:if test="${displayTable.g1CrDscFlg eq 1}">�̻�ǩԼ����</c:if>
						</display:column>
						<display:column title="��Ч��ʼ���� " style="width:10%;text-align:center" property="startDate" headerClass="sortable" sortable="true" />
						<display:column title="��Ч�������� " style="width:10%;text-align:center" property="endDate" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:8%;text-align:center">
						<shiro:hasPermission name="posp:stlmRegu:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.ruleNo}"/>','findItem')">�༭</a>
						</shiro:hasPermission>
						</display:column>
						
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="ruleNo" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
								<shiro:hasPermission name="posp:stlmRegu:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:stlmRegu:add">
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
