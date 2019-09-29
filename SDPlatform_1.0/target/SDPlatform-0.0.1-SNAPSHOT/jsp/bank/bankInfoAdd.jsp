<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>���л�������</title>
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
		return validateBankInfoForm(document.forms[0]);
	}
	function backClick()
	{
		document.forms[0].method.value="queryAll";
	}
	</script>

</head>
<shiro:lacksPermission name="posp:bankinfo:add">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:bankinfo:add">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">  
				�������л�������
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="bankInfoForm" />
				<html:errors />
				<html:form action="/bankInfo" method="post">

					<html:hidden property="method" value="createItem" />
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								�����к�:
							</td>
							<td class="table2_td">
								<html:text property="bankId" size="30" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								������:
							</td>
							<td class="table2_td">
								<html:text property="hostId" size="30" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:text property="bankName" size="30" maxlength="20" /><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:select property="bankType" style="width:195px;" value="">
								  <html:option value="">- ��ѡ�� -</html:option>
								  <c:forEach var="model" items="${bankTypeList}">
							         <option value="<c:out value="${model.bankType}"/>"><c:out value="${model.typeName}" /></option>
							      </c:forEach>
							      </html:select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��Ͻ�к�:
							</td>
							<td class="table2_td">
								<html:text property="admBankId" size="30" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
							<td class="table2_td_title">
								��Ͻ��������:
							</td>
							<td class="table2_td">
								<html:text property="admHostId" size="30" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��ַ:
							</td>
							<td class="table2_td">
								<html:text property="address" size="30" maxlength="40"  />
							</td>
							<td class="table2_td_title">
								��������:
							</td>
							<td class="table2_td">
								<html:text property="postCode" size="30" maxlength="6" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								Email�ʼ�:
							</td>
							<td class="table2_td">
								<html:text property="email" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								����:
							</td>
							<td class="table2_td">
								<html:text property="fax" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�紫:
							</td>
							<td class="table2_td">
								<html:text property="telex" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								��Ȩ�绰:
							</td>
							<td class="table2_td">
								<html:text property="authTel" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����绰:
							</td>
							<td class="table2_td">
								<html:text property="settleTel" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
							</td>
							<td class="table2_td">
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								�����г�:
							</td>
							<td class="table2_td">
								<html:text property="gmName" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								�����г��绰:
							</td>
							<td class="table2_td">
								<html:text property="gmTel" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������1:
							</td>
							<td class="table2_td">
								<html:text property="mngName1" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								������1�绰:
							</td>
							<td class="table2_td">
								<html:text property="mngTel1" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������2:
							</td>
							<td class="table2_td">
								<html:text property="mngName2" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								������2�绰:
							</td>
							<td class="table2_td">
								<html:text property="mngTel2" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								������3:
							</td>
							<td class="table2_td">
								<html:text property="mngName3" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								������3�绰:
							</td>
							<td class="table2_td">
								<html:text property="mngTel3" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��Ȩ������:
							</td>
							<td class="table2_td">
								<html:text property="authMngName" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								��Ȩ�����˵绰:
							</td>
							<td class="table2_td">
								<html:text property="authMngTel" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								���㸺����:
							</td>
							<td class="table2_td">
								<html:text property="settMngName" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								���㸺���˵绰:
							</td>
							<td class="table2_td">
								<html:text property="settMngTel" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����������:
							</td>
							<td class="table2_td">
								<html:text property="cardMngName" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								���������˵绰:
							</td>
							<td class="table2_td">
								<html:text property="cardMngTel" size="30" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								����������:
							</td>
							<td class="table2_td">
								<html:text property="nasMngName" size="30" maxlength="20" />
							</td>
							<td class="table2_td_title">
								���������˵绰:
							</td>
							<td class="table2_td">
								<html:text property="nasMngTel" size="30" maxlength="15" />
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