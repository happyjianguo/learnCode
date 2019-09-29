<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>�ɸ��������ն˹���</title>
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
	function init(){
		<c:if test="${!empty param.queryMerchantId}" >
		var queryMerchantId = '<c:out value="${param.queryMerchantId}"/>';
		document.forms[0].queryMerchantId.value=queryMerchantId;
		</c:if>	
		<c:if test="${!empty param.queryTerminalId}" >
		var queryTerminalId = '<c:out value="${param.queryTerminalId}"/>';
		document.forms[0].queryTerminalId.value=queryTerminalId;
		</c:if>	
		<c:if test="${!empty param.queryBankTerminalId}" >
		var queryBankTerminalId = '<c:out value="${param.queryBankTerminalId}"/>';
		document.forms[0].queryBankTerminalId.value=queryBankTerminalId;
		</c:if>	
		<c:if test="${!empty param.queryLogonStatus}" >
		var queryLogonStatus = '<c:out value="${param.queryLogonStatus}"/>';
		document.forms[0].queryLogonStatus.value=queryLogonStatus;
		</c:if>		
	}
	function addClick()
	{
		document.forms[0].method.value="queryEdcTerminalOrmByKey";
		document.forms[0].submit();
	}
	function editClicks(id,method)
	{
		document.forms[0].method.value=method;
		document.forms[0].merchantId.value=id;
		document.forms[0]._id.value=id;
		document.forms[0].submit();		
	}
	function downloadTemplate(){
		document.forms[0].method.value="downloadTemplate";
		document.forms[0].submit();
	}
	function fileUpload(){
		var fileName = document.getElementById("formShowData").upload.value;
		 if(!fileName){
			 alert("��ѡ��Ҫ������ļ�!");
	         return false;
		 }
	     var strRegex = "(.xlsx|.xls)$";
	     var re=new RegExp(strRegex);
	     if (!re.test(fileName.toLowerCase())){
	         alert("�ļ���ʽ���Ϸ�������ΪEXCEL�ĵ�!");
	         return false;
	     }
	     document.getElementById("formShowData").submit();
	}
	function setSettle(_bankmerchantId,_bankterminalId)
	{
		document.forms[0]._bankmerchantId.value=_bankmerchantId;
		document.forms[0]._bankterminalId.value=_bankterminalId;
		document.forms[0].method.value="settle";
		document.forms[0].submit();
	}
	function setLogin(_bankmerchantId,_bankterminalId)
	{
		document.forms[0]._bankmerchantId.value=_bankmerchantId;
		document.forms[0]._bankterminalId.value=_bankterminalId;
		document.forms[0].method.value="login";
		document.forms[0].submit();
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminalorm:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminalorm:view">
<body onload="init();">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�ɸ��������ն��б�
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/edcTerminalOrm" method="post">
					<input type="hidden" name="method" value="queryEdcTerminalOrm" />
					<input type="hidden" name="_bankmerchantId" value="" />
					<input type="hidden" name="_bankterminalId" value="" />
					<!----------����TableΪ��ѯform-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title" width="10%">
								�̻����:
							</td>
							<td class="table2_td" width="20%">
							<input name="queryMerchantId" type="text"  maxlength="15"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="10%">
								�ն˺�:
							</td>
							<td class="table2_td" width="20%">							
							<input name="queryTerminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
							<td class="table2_td_title" width="10%">
								�����ն˺�:
							</td>
							<td class="table2_td" width="20%">							
							<input name="queryBankTerminalId" type="text"  maxlength="8"  style="width:135px;">
							</td>
                            <td class="table2_td_title" width="10%">
								״̬:
							</td>
							<td class="table2_td" width="20%">							
								<select name="queryLogonStatus">
								 <option value="">--��ѡ��-- </option>
								 <option value="0">ǩ�� </option>
								 <option value="1">ǩ��</option>
								 <option value="2">�쳣</option>
								</select>
							</td>
						</tr>				
						<tr>
							<td colspan="8" class="table2_btn">
								<input name="imageField" type="image" src="<fmt:message key='CommonImagePath' />btnQuery.gif" alt="��ѯ" width="65" height="20" border="0" onclick="return searchClick('queryEdcTerminalOrm');">
								&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:resetClick()"><img src="<fmt:message key='CommonImagePath' />btnClear.gif" alt="���" width="65" height="20" border="0"></a>
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
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/edcTerminalOrm.do">
						<display:column title="ѡ��" style="width:10%;text-align:center">
							
						<input type="checkbox" name="checkItem" value="<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#${displayTable.moduleId}#end"/>">
							
						</display:column>
						<display:column title="�̻����" style="width:10%;text-align:center" property="merchantId" headerClass="sortable" sortable="true" />
						<display:column title="�ն˱��" style="width:12%;text-align:center" property="terminalId" headerClass="sortable" sortable="true" />
						<display:column title="�����̻���" style="width:13%;text-align:center" property="bankMerchantId" headerClass="sortable" sortable="true" />
						<display:column title="�����ն˺�" style="width:15%;text-align:center" property="bankTerminalId" headerClass="sortable" sortable="true" />
						<display:column title="���κ�" style="width:10%;text-align:center" property="batchNo" headerClass="sortable" sortable="true" />
						<display:column title="��ˮ��" style="width:10%;text-align:center" property="sysTrace" headerClass="sortable" sortable="true" />
						<display:column title="״̬" style="width:10%;text-align:center" headerClass="sortable" sortable="true">
							<c:if test="${displayTable.logonStatus eq '0'}">ǩ��</c:if>
							<c:if test="${displayTable.logonStatus eq '1'}">ǩ��</c:if>
							<c:if test="${displayTable.logonStatus eq '2'}">�쳣</c:if>
						</display:column>
						<display:column title="����" style="width:10%;text-align:center">
                        <shiro:hasPermission name="posp:terminalorm:edit">
						   <a href="#" onclick="javascript:editClicks('<c:out value="${displayTable.merchantId}#${displayTable.terminalId}#${displayTable.moduleId}#end"/>','queryEdcTerminalOrmByKey')">�鿴</a>
						   <a href="#" onclick="javascript:setSettle('${displayTable.bankMerchantId}','${displayTable.bankTerminalId}')">����</a>
						   <a href="#" onclick="javascript:setLogin('${displayTable.bankMerchantId}','${displayTable.bankTerminalId}')">ǩ��</a>
                        </shiro:hasPermission>
						</display:column>
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					
					<input type="hidden" name="_id" />		
					<input type="hidden" name="selectItems" />
					<input type="hidden" name="merchantId" />
					
					<!----------����Table�ò���ѡ��-------->
					<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_border">
						<tr>
							<td width="100" align="center">
								<input type="checkbox" name="selectAll" onclick="checkAll(this, 'checkItem')" />
								�Ƿ�ȫѡ
							</td>
							<td>
                                <shiro:hasPermission name="posp:terminalorm:delete">
								<a href="javascript:deleteClick('selectItems','checkItem','deleteEdcTerminalOrm');"><img border=0 name="imageField33" src="<fmt:message key='CommonImagePath' />btnDelete.gif" alt="ɾ��" width="65" height="20" border="0"></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="posp:terminalorm:add">
								<a href="javascript:addClick()">
								<img border=0 name="imageField322" src="<fmt:message key='CommonImagePath' />btnAdd.gif" alt="����" width="65" height="20" border="0" ></a>
                                </shiro:hasPermission>
							</td>
							<td width="240" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<!----------TTable�ò���ѡ�����-------->
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- ά����ͼ״̬�������� -->
					</html:form>
					
			</td>
		</tr>
	</table>
	<shiro:hasPermission name="posp:terminalorm:upload">
	<center>			
	<form id='formShowData' name="formShowData" enctype='multipart/form-data' method='post' action="edcTerminalOrmUpload.do" >
	<input type="hidden" name="method" value="fileUpload" />
	<input type="file" name='upload' id='upload' /><input type="button" value="����" onclick="fileUpload();" />
	<a href="javascript:;" onclick="downloadTemplate();">���ص���ģ��</a>
	</form>
	</center>
	</shiro:hasPermission>
</body>
</shiro:hasPermission>
</html:html>