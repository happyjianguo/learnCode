<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.*,cn.yufu.posp.terminalmanager.domain.model.*"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�ն����򿨿�BIN����</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveItem";
		return validateEdcCardBinAreaForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:edcCardBinArea:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:edcCardBinArea:edit">
<body>
	
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle"> 
				�޸��ն����򿨿�BIN
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcCardBinAreaForm" />
				<html:errors />
				<html:form action="/edcCardBinArea" method="post">
					<html:hidden property="method" value="saveItem" />
					<html:hidden property="cardBin" value="${edcCardBinAreaForm.cardBin}" />
					<html:hidden property="merchantId" value="${edcCardBinAreaForm.merchantId}" />
					<html:hidden property="terminalId" value="${edcCardBinAreaForm.terminalId}" />	
					<html:hidden property="queryCardBin" />
					<html:hidden property="queryStatus" />
					<html:hidden property="queryMerchantId" />
					<html:hidden property="queryTerminalId" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								��BIN:
							</td>
							<td class="table2_td">
								${edcCardBinAreaForm.cardBin}
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻���:
							</td>
							<td class="table2_td">
								${edcCardBinAreaForm.merchantId}						
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն�:
							</td>
							<td class="table2_td">
								${edcCardBinAreaForm.terminalId}								
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								״̬:
							</td>
							<td class="table2_td">
								<html:select property="status" style="width:195px;">
									<html:option value="1">��Ч</html:option>
									<html:option value="0">��Ч</html:option>									
								</html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()">
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



