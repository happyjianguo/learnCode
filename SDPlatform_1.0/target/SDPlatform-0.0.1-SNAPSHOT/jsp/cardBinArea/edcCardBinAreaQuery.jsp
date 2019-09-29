<%@ page contentType="text/html;charset=GBK" import="cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>�ն����򿨿�BIN</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />data.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />listPage.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	
	<script language="javascript">
	function init()
	{
		<c:if test="${!empty param.queryCardBin}" >
		var queryCardBin = '<c:out value="${param.queryCardBin}"/>';
		document.forms[0].queryCardBin.value=queryCardBin;
		</c:if>	
		<c:if test="${!empty param.queryStatus}" >
		var queryStatus = '<c:out value="${param.queryStatus}"/>';
		document.forms[0].queryStatus.value=queryStatus;
		</c:if>	
		<c:if test="${!empty param.queryMerchantId}" >
		var queryMerchantId = '<c:out value="${param.queryMerchantId}"/>';
		document.forms[0].queryMerchantId.value=queryMerchantId;
		</c:if>		
		<c:if test="${!empty param.queryTerminalId}" >
		var queryTerminalId = '<c:out value="${param.queryTerminalId}"/>';
		document.forms[0].queryTerminalId.value=queryTerminalId;
		</c:if>				
	}
	
	function addClick()
	{
		document.forms[0].method.value="findItem";
		document.forms[0].submit();
	}
	function editClicks(params,method)
	{
		//alert(params+","+method);
		document.forms[0].method.value=method;
		document.forms[0].edcCardBinAreaId.value=params;
		document.forms[0].submit();
		
	}
	function resetClick1(){
		document.forms[0].queryCardBin.value="";
		document.forms[0].queryStatus.value="";
		document.forms[0].queryMerchantId.value="";
		document.forms[0].queryTerminalId.value="";		
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:edcCardBinArea:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edcCardBinArea:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�ն����򿨿�BIN�б�  
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/edcCardBinArea" method="post">
					<input type="hidden" name="method" value="queryAll" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="12%">  
								��bin:  
							</td>
							<td class="table2_td" width="13%">
								<html:text property="queryCardBin" size="19" maxlength="9" />
							</td>
							<td class="table2_td_title" width="12%">  
								�̻���:  
							</td>
							<td class="table2_td" width="13%">
								<html:text property="queryMerchantId" size="19" maxlength="15" />
							</td>
							<td class="table2_td_title" width="12%">  
								�ն˺�:  
							</td>
							<td class="table2_td" width="13%">
								<html:text property="queryTerminalId" size="19" maxlength="8" />
							</td>														
							<td class="table2_td_title" width="12%">  
								״̬�� 
							</td>
							<td class="table2_td" width="13%">
								<html:select property="queryStatus" style="width:130px;" value="">
									  <option value="">- ��ѡ�� -</option>
									  <option value="1">��Ч</option>
									  <option value="0">��Ч</option>
							    </html:select>
							</td>						
						</tr>				
						<tr>
							<td colspan="8" class="table2_btn">
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/edcCardBinArea.do">
						<display:column title="ѡ��" style="width:5%;text-align:center">							
							<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.cardBin}"/>,<c:out value="${displayTable.merchantId}"/>,<c:out value="${displayTable.terminalId }"/>" />						
						</display:column>
						<display:column title="��bin" style="width:10%;text-align:center" property="cardBin" headerClass="sortable" sortable="true" />
						<display:column title="�̻���" style="width:20%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="�ն˺�" style="width:20%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />
						<display:column title="״̬" style="width:13%;text-align:center" headerClass="sortable" sortable="true" >
							<c:if test="${displayTable.status eq '0'}">��Ч</c:if>
							<c:if test="${displayTable.status eq '1'}">��Ч</c:if>
						</display:column>	
						<display:column title="���¹�Ա" style="width:10%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="��������" style="width:14%;text-align:center" property="updateDate" headerClass="sortable" sortable="true" />
						<display:column title="����" style="width:8%;text-align:center">
						<shiro:hasPermission name="posp:edcCardBinArea:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.cardBin}"/>,<c:out value="${displayTable.merchantId}"/>,<c:out value="${displayTable.terminalId }"/>','findItem')">�༭</a>
						</shiro:hasPermission>	
						</display:column>
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="edcCardBinAreaId" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
								<shiro:hasPermission name="posp:edcCardBinArea:delete">
									<a href="#"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0" onclick="deleteClick('selectItems','checkItem','deleteItem');"></a> 
								</shiro:hasPermission>
								<shiro:hasPermission name="posp:edcCardBinArea:add">
									<a href="#" onclick="addClick()"><img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0"></a>
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
