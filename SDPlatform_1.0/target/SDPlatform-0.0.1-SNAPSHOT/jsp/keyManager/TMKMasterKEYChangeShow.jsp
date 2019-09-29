<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>÷’∂À÷˜√‹‘ø</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript">
	function backClick()
	{
		document.forms[0].method.value="queryChangeAll";
	}
	</script>
</head>

<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				÷’∂À÷˜√‹‘ø
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="btsKeyForm" />
				<html:errors />
				<html:form action="/TMKMasterKEY">

					<html:hidden property="method" value="saveItem" />
					<html:hidden property="masterKey" />
					<html:hidden property="pinKey" />
					<html:hidden property="macKey" />
					<html:hidden property="posKey" />
					<html:hidden property="logonKey"  />
					<html:hidden property="settleFlag" />
					<html:hidden property="batchNo" />
					<html:hidden property="querySettleFlag" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								…Ãªß∫≈:
							</td>
							<td class="table2_td">
								<html:text property="merchantId" size="30" maxlength="15" disabled="true"/>
								<font color="red">*</font>
								<html:hidden property="merchantId" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								…Ãªß√˚≥∆:
							</td>
							<td class="table2_td">
								<html:text property="merchantName" size="30" disabled="true" maxlength="10" />
								<font color="red">*</font>
								<html:hidden property="merchantName"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								÷’∂À∫≈£∫
							</td>
							<td class="table2_td">
								<html:text property="terminalId" size="30" disabled="true" maxlength="8"/>
								<font color="red">*</font>
								<html:hidden property="terminalId"/>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								OPER_NO£∫
							</td>
							<td class="table2_td">
								<html:text property="operNo" size="30" maxlength="6" disabled="true"/>
								<font color="red">*</font>
							</td>
						</tr>						
						<tr>
							<td class="table2_td_title">
								OPER_PASSWD£∫
							</td>
							<td class="table2_td">
								<html:text property="operPasswd" size="30" maxlength="8" disabled="true"/>
								<font color="red">*</font>
							</td>
						</tr>						
						<tr>
							<td class="table2_td_title">
								MASTER_KEY£∫
							</td>
							<td class="table2_td">
								<html:text property="masterKey" size="45" maxlength="32" disabled="true"/>
								<font color="red">*</font>
							</td>
						</tr>						
						<tr>
							<td class="table2_td_title">
								TMKÕ®—∂÷˜√‹‘ø£∫
							</td>
							<td class="table2_td">
								<html:text property="tmkmasterKey" size="45" maxlength="32" disabled="true" />
								<font color="red">*</font>
							</td>
						</tr>						
						<tr>
							<td align="center" colspan="2" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()">
							</td>
						</tr>
					</table>
					<!-- Œ¨ª§ ”Õº◊¥Ã¨µƒ“˛≤ÿ”Ú -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- Œ¨ª§ ”Õº◊¥Ã¨µƒ“˛≤ÿ”Ú -->
					</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>



