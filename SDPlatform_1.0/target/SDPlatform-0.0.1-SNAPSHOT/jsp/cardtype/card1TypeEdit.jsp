<%@ page language="java" contentType="text/html;charset=GBK" import="java.util.Date" isELIgnored="false"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>���ֹ���</title>
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
		document.forms[0].method.value="saveCardType";
		return validateCard1TypeForm(document.forms[0]);
		
	}
	function backClick()
	{
		document.forms[0].method.value="queryCardType";
	}
	//ɾ������ո�
  	String.prototype.RTrim   =   function(){   
  		return   this.replace(/(\s*$)/g,   "");   
  	} 
	</script>
</head>
<shiro:lacksPermission name="posp:card1type:edit">
    <div class="permission_error">�û�[${fns:getUserName()}]û�и�Ȩ��<br/></div>
</shiro:lacksPermission>
<shiro:hasPermission name="posp:card1type:edit">
<body>
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1_border">
		<tr>
			<td class="table1_head_title">
				<img src="<fmt:message key='CommonImagePath' />table1_head_ico.gif" width="26" height="23" align="absmiddle">
				�޸Ŀ��� 
			</td>
		</tr>
		<tr>
			<td class="table1_head_line">
			</td>
		</tr>
		<tr>
			<td align="center">
				<html:javascript formName="card1TypeForm" />
				<html:errors />
				<html:form action="/card1Type" method="post">

					<html:hidden property="method" value="saveCardType" />
					
					<table align="center" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="table2_border">
						<tr>
							<td class="table2_td_title">
								���ͱ���:
							</td>
							<td class="table2_td">
								<html:text property="cardType" size="30" maxlength="4" readonly="true" disabled="true" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">*</font>
								<html:hidden property="cardType" />
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								��������:

							</td>
							<td class="table2_td">
								<html:text property="typeName" size="30" maxlength="20" onfocus="this.value=this.value.RTrim()"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td class="table2_td_title">
								Ӣ������:

							</td>
							<td class="table2_td">
								<html:text property="typeEname"  size="30" maxlength="20" onfocus="this.value=this.value.RTrim()"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4" class="table2_btn">
								<input type="image" src="<fmt:message key='CommonImagePath' />btnSave.gif" onclick="return saveClick()" />
								&nbsp;
								<input type="image" src="<fmt:message key='CommonImagePath' />btnBack.gif" onclick="return backClick()" />


							</td>
						</tr>
					</table>

					<!-- ά����ͼ״̬�������� -->
					<%@include file="../common/getDisplayParams1.jsp"%>
					<!-- ά����ͼ״̬�������� -->
				</html:form>

			</td>
		</tr>
	</table>
</body>
</shiro:hasPermission>
</html:html>