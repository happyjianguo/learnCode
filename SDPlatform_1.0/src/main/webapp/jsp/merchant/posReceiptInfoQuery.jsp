<%@ page contentType="text/html;charset=GBK" import="cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>POSǩ����Ҫ�����ù���</title>
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

	function editClicks(params,method)
	{
		document.forms[0].method.value=method;
		//��editClicks()���������õĲ���status
		document.forms[0].status.value=params;
		document.forms[0].submit();
	}
	</script>
	
</head>
<shiro:lacksPermission name="posp:posReceiptInfo:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:posReceiptInfo:view">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				POSǩ����Ҫ�������б�  
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:form action="/posReceiptInfo" method="post">
					<input type="hidden" name="method" value="queryAll" />
				<!-- ��Diaplay Tag����ʾ -->
					<display:table name="pageInfoResult.resultItems" partialList="true" size="pageInfoResult.totalCount" pagesize="8" class="dpTable" id="displayTable" requestURI="/posReceiptInfo.do">
						<display:column title="�������ߵ绰" style="width:15%;text-align:center" property="hotline" headerClass="sortable" sortable="true" />
						<display:column title="����֧�ֵ绰" style="width:11%;text-align:center" property="telSupport" headerClass="sortable" sortable="true" />
						<display:column title="�������" style="width:18%;text-align:center" property="adInfo" headerClass="sortable" sortable="true" />
						<display:column title="��ӡ����־" style="width:10%;text-align:center" property="adYesNoFlag" headerClass="sortable" sortable="true" />
						<display:column title="״̬��ʶ" style="width:10%;text-align:center" property="status" headerClass="sortable" sortable="true" />
						<display:column title="��������ʱ��" style="width:14%;text-align:center" property="createDate" headerClass="sortable" sortable="true" />
						<display:column title="���¹�Ա" style="width:10%;text-align:center" property="updateOper" headerClass="sortable" sortable="true" />
						<display:column title="��������ʱ��" style="width:12%;text-align:center" property="updateDate" headerClass="sortable" sortable="true" />

						<display:column title="����" style="width:10%;text-align:center">
						<shiro:hasPermission name="posp:posReceiptInfo:edit">
							<a href="#" onclick="editClicks('<c:out value="${displayTable.status}"/>','findItem')">�༭</a>
						</shiro:hasPermission>
						</display:column>
					</display:table>
					<!-- ��Diaplay Tag����ʾ -->		
					<!-- ��editClicks()���������õĲ��� -->	
					<input type="hidden" name="status" />

			</td>
		</tr>
	</table>
	<%@include file="../common/getDisplayParams1.jsp"%>
	</html:form>
	<br>

</body>
</shiro:hasPermission>
</html:html>
