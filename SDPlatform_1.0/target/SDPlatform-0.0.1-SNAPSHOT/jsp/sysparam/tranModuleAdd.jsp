<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>����ģ����Ϣ</title>
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
		document.forms[0].method.value="createItem";
		return validateTranModuleInfForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	
	</script>
</head>
<shiro:lacksPermission name="posp:tranmodule:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:tranmodule:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				��������ģ����Ϣ
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="tranModuleInfForm" />
				<html:errors />
				<html:form action="/tranModule">

					<html:hidden property="method" value="saveItem" />

					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">

						<tr>
							<td class="table2_td_title">
								����ģ���:
							</td>
							<td class="table2_td">
								<html:text property="moduleId" size="19" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								�������ʹ���:
							</td>
							<td class="table2_td">
								<html:text property="tranType" size="19" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								ԭ��������:
							</td>
							<td class="table2_td">
								<html:text property="voidTranType" size="19" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"  />
								<font color="red">*</font><br>������������
							</td>
							<td class="table2_td_title">
								ԭԭ��������:
							</td>
							<td class="table2_td">
								<html:text property="voidOldTranType" size="19" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								<font color="red">*</font><br>���������
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����л�ȡMessgeId:
							</td>
							<td class="table2_td">
								<html:text property="msgId" size="19" maxlength="4"  />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								����25���ȡ:
							</td>
							<td class="table2_td">
								<html:text property="serviceCode" size="19" maxlength="2" />
								<br>�����������
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���������/�������ͱ���:
							</td>
							<td class="table2_td">
								<html:text property="typeCode" size="19" maxlength="3"  />
								<font color="red">*</font><br>�����ཻ�� ȡ8583����60.3��<br>���� ȡ8583����60.1 
							</td>
							<td class="table2_td_title">
								8583����3�� ���״�����:
							</td>
							<td class="table2_td">
								<html:text property="procCode" size="19" maxlength="6" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:text property="tranName" size="19" maxlength="32" />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								������λͼ:
							</td>
							<td class="table2_td">
								<html:text property="reqBitmap" size="19" maxlength="32" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��Ӧ����λͼ:
							</td>
							<td class="table2_td">
								<html:text property="respBitmap" size="19" maxlength="32" />
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								ԭ�������ʹ�����:
							</td>
							<td class="table2_td">
								<html:text property="revOrgProcCode" size="19" maxlength="6" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�μӽ����־ :
							</td>
							<td class="table2_td">
								<html:select property="settFlag">
								 <html:option value="0" >���μ�</html:option>
								 <html:option value="1" >�μӣ���ǣ�</html:option>
								 <html:option value="2" >�μӣ����ǣ�</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								�Ƿ��¼��ˮ :
							</td>
							<td class="table2_td">
								<html:select property="writeLsFlag">
								 <html:option value="0" >����¼</html:option>
								 <html:option value="1" >��¼</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								MACУ���־:
							</td>
							<td class="table2_td">
								<html:select property="checkMacFlag">
								 <html:option value="0" >��У��</html:option>
								 <html:option value="1" >У��</html:option>
								</html:select>
								<font color="red">*</font>
							</td>
							<td class="table2_td_title">
								��ͨ��־:
							</td>
							<td class="table2_td">
								<html:select property="flag">
								 <html:option value="0" >δ��ͨ</html:option>
								 <html:option value="1" >������ͨ</html:option>
								</html:select>
								<font color="red">*</font>
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