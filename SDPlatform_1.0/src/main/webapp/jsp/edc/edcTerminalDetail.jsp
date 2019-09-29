<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>�ն�������ϸ</title>
	<fmt:setBundle basename="MyResource"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<fmt:message key='StylePath' />style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<fmt:message key='JavaScriptPath' />common.js"></script>
	<script language="javascript" src="<fmt:message key='JavaScriptPath' />meizzDate.js"></script>
	<script language="JavaScript" src="<fmt:message key='JavaScriptPath' />GetDate.js"></script>
	<script language="javascript">
	
	function saveClick()
	{
		document.forms[0].method.value="saveEdcTerminal";
		return validateEdcTerminalForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryEdcTerminal";
	}
	</script>
</head>
<shiro:lacksPermission name="posp:terminal:view">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:terminal:view">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�ն�������ϸ
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="edcTerminalForm" />
				<html:errors />
				<html:form action="/edcTerminal">

					<html:hidden property="method" value="saveEdcTerminal" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								�̻����:
							</td>
							<td class="table2_td">
								${edcTerminalForm.id.merchantId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�̻�����:
							</td>
							<td class="table2_td">
								${edcTerminalForm.id.merchanName }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								 �ն˺ţ�
							</td>
							<td class="table2_td">
								${edcTerminalForm.id.terminalId }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն�״̬��
							</td>
							<td class="table2_td">
								<html:select property="terminalStat" disabled="true">
								 <html:option value="Y">����</html:option>
								 <html:option value="N">����</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն��豸�ͺţ�
							</td>
							<td class="table2_td">
								<html:select property="edcType" disabled="true">
								  <html:option value=""></html:option>
								  <html:option value="STD">STD</html:option>
							      <html:option value="CMB">CMB</html:option>
							      <html:option value="CITIC">CITIC</html:option>
							      <html:option value="CITIC1">CITIC1</html:option>
							      <html:option value="HYPT7">HYPT7</html:option>
							      <html:option value="SPECS900">SPECS900</html:option>
							      <html:option value="STAR">STAR</html:option>
							      <html:option value="SHARE">SHARE</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�豸���˵����
							</td>
							<td class="table2_td">
								${edcTerminalForm.edcDoc }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ӡ�����ͣ�
							</td>
							<td class="table2_td">
								${edcTerminalForm.printerType }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								PIN PAD���ͣ�
							</td>
							<td class="table2_td">
								${edcTerminalForm.pinpadType }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�ն�����汾��
							</td>
							<td class="table2_td">
								${edcTerminalForm.softVer }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�������ر�־��
							</td>
							<td class="table2_td">
								<html:select property="downloadFlag" disabled="true">
								 <html:option value="Y">��Ҫ��װ</html:option>
								 <html:option value="N">���谲װ</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������ģʽ��
							</td>
							<td class="table2_td">${edcTerminalForm.downloadMode}-
								<c:if test="${edcTerminalForm.downloadMode eq 0}">��</c:if>
								<c:if test="${edcTerminalForm.downloadMode eq 4}">���¹�Կ</c:if>
								<c:if test="${edcTerminalForm.downloadMode eq 5}">����IC������</c:if>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��װ���ڣ�
							</td>
							<td class="table2_td">
								${edcTerminalForm.setDate }
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��װ�ص㣺
							</td>
							<td class="table2_td">
								${edcTerminalForm.setAddr }
							</td>
						</tr>
					</table>
					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<input type="hidden" name="search_sort" value="<c:out value="${param.search_sort}"/>" />
					<input type="hidden" name="search_name" value="<c:out value="${param.search_name}"/>" />
					<!-- ά����ͼ״̬�������� -->
					</html:form>
			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>



